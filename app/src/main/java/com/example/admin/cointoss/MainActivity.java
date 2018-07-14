
package com.example.admin.cointoss;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.EnumMap;
import java.util.Locale;

import ir.adad.client.Adad;

public class MainActivity extends AppCompatActivity {

    // debugging tag
    private static final String TAG = MainActivity.class.getSimpleName();


    EnumMap<Animation.ResultState, Drawable> coinImagesMap;

    private Drawable heads = null;

    private Drawable tails = null;

    private Drawable edge = null;

    private Drawable background = null;

    private final Coin theCoin = Coin.getInstance();

    private ShakeListener shaker;

    private OnClickListener tapper;

    private Boolean currentResult = true;

    private Boolean previousResult = true;

    private ImageView coinImage;

    private LinearLayout mainLayout;

    private CustomAnimationDrawable coinAnimation;

    private TextView resultText;

    private TextView headsStatText;

    private TextView tailsStatText;

    private MyTextView txtLanguage;

    private MyTextView txtCoin;

    private MyTextView txtCustomize;

    private MyTextView txtHeadsTitle;

    private MyTextView txtTailsTitle;

    private CardView cardMain ;

    private CardView cardBot ;

    private RelativeLayout relMain;

    private ImageButton statsResetButton;

    private LinearLayout statsLayout;

    private SoundPool soundPool;

    private int soundCoin;

    private int soundOneUp;

    private int flipCounter = 0;

    private int headsCounter = 0;

    private int tailsCounter = 0;

    private Locale locale;

    private SharedPrefManager sharedPrefManager;
    @Override
    public void onResume() {
        Log.d(TAG, "onResume()");

        resetCoin();
        loadResources();
        updateStatsText();
        resumeListeners();


        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause()");

        pauseListeners();

        if (coinAnimation != null) {
            // shut down the animation thread, otherwise the callback will resume the shake
            // listener in the background even though the app is supposed to be suspended
            coinAnimation.removeCallbacks();
        }


        super.onPause();
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        Adad.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        if (sharedPrefManager.isFirstRun()){
            LanguageDialog languageDialog = new LanguageDialog(MainActivity.this);
            languageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            languageDialog.show();
            sharedPrefManager.setFirstRun();
        }
        if (sharedPrefManager.hasPremium()){
            Adad.disableBannerAds();
        }else{
            //Adad.enableBannerAds();
        }
        initViews();

        setThemeById(sharedPrefManager.getTheme());


        coinImage.setImageDrawable(getResources().getDrawable(sharedPrefManager.getTailsId()));

        txtLanguage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LanguageDialog languageDialog = new LanguageDialog(MainActivity.this);
                languageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                languageDialog.show();
            }
        });
        txtCoin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CoinDialog coinDialog = new CoinDialog(MainActivity.this);
                coinDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                coinDialog.show();
            }
        });
        txtCustomize.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemeDialog themeDialog = new ThemeDialog(MainActivity.this);
                themeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                themeDialog.show();
            }
        });

        // initialize the sounds
        initSounds();

        // initialize the coin maps
        Animation.init();
        coinImagesMap = new EnumMap<Animation.ResultState, Drawable>(Animation.ResultState.class);

        // initialize the shake listener
        if (shaker == null) {
            shaker = new ShakeListener(this);
            shaker.pause();
            shaker.setOnShakeListener(new ShakeListener.OnShakeListener() {
                public void onShake() {
                    flipCoin();
                }
            });
        }

        // initialize the onclick listener
        if (tapper == null) {
            tapper = new OnClickListener() {
                @Override public void onClick(View v) {
                    flipCoin();
                }
            };
        }

        statsResetButton.setOnClickListener(new OnClickListener() {
            public void onClick(final View v) {
                resetStatistics();
            }
        });
    }
    public void setCoinImage(int headsID,int tailsID) {
        heads = getResources().getDrawable(headsID);
        tails = getResources().getDrawable(tailsID);
        Animation.generateAnimations(heads, tails, edge, background);
        coinImagesMap.put(Animation.ResultState.HEADS_HEADS, heads);
        coinImagesMap.put(Animation.ResultState.HEADS_TAILS, tails);
        coinImagesMap.put(Animation.ResultState.TAILS_HEADS, heads);
        coinImagesMap.put(Animation.ResultState.TAILS_TAILS, tails);
        displayCoinImage(true);
        displayCoinAnimation(false);
        resultText.setText("");
        sharedPrefManager.setTailsId(tailsID);
        sharedPrefManager.setHeadsId(headsID);
        coinImage.setImageDrawable(getResources().getDrawable(R.drawable.f100));
    }

    public void setLocaleLang(String localeKey){
        Resources resources = getResources();
        locale = new Locale(localeKey);
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration,
                getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    private void flipCoin() {
        Log.d(TAG, "flipCoin()");

        flipCounter++;
        Log.d(TAG, "flipCounter=" + flipCounter);


        // we're in the process of flipping the coin
        Animation.ResultState resultState = Animation.ResultState.HEADS_HEADS;

        // pause the shake listener until the result is rendered
        pauseListeners();

        // flip the coin and update the state with the result
        boolean flipResult = theCoin.flip();
        if (flipResult) {
            headsCounter++;
        } else {
            tailsCounter++;
        }
        Log.d(TAG, "headsCounter=" + headsCounter + " | tailsCounter=" + tailsCounter);
        resultState = updateState(flipResult);

        // update the screen with the result of the flip
        renderResult(resultState);

    }

    private void resetCoin() {
        Log.d(TAG, "resetCoin()");

        // hide the animation and draw the reset image
        displayCoinAnimation(false);
        displayCoinImage(true);
        //coinImage.setImageDrawable(getResources().getDrawable(R.drawable.unknown));
        resultText.setText(" ");
        Animation.clearAnimations();
        coinImagesMap.clear();
    }


    private Animation.ResultState updateState(final boolean flipResult) {
        // Analyze the current coin state and the new coin state and determine
        // the proper transition between the two.
        // true = HEADS | false = TAILS

        Log.d(TAG, "updateState()");

        Animation.ResultState resultState = Animation.ResultState.HEADS_HEADS;
        currentResult = flipResult;

        // this is easier to read than the old code
        if (previousResult == true && currentResult == true) {
            resultState = Animation.ResultState.HEADS_HEADS;
        }
        if (previousResult == true && currentResult == false) {
            resultState = Animation.ResultState.HEADS_TAILS;
        }
        if (previousResult == false && currentResult == true) {
            resultState = Animation.ResultState.TAILS_HEADS;
        }
        if (previousResult == false && currentResult == false) {
            resultState = Animation.ResultState.TAILS_TAILS;
        }

        // update the previousResult for the next flip
        previousResult = currentResult;

        return resultState;
    }

    // check the coin preference and determine how to load its resources
    private void loadResources() {
        loadInternalResources();
    }

    // load resources internal to the CoinFlip package
    private void loadInternalResources() {
        Log.d(TAG, "loadInternalResources()");

        // load the images
        heads = getResources().getDrawable(sharedPrefManager.getHeadsId());
        tails = getResources().getDrawable(sharedPrefManager.getTailsId());
        edge = getResources().getDrawable(R.drawable.edge);
        background = getResources().getDrawable(R.drawable.background);

        Animation.generateAnimations(heads, tails, edge, background);


        // add the appropriate image for each result state to the images map
        // WTF? There's some kind of rendering bug if you use the "heads" or
        // "tails" variables here...
        coinImagesMap.put(Animation.ResultState.HEADS_HEADS, heads);
        coinImagesMap.put(Animation.ResultState.HEADS_TAILS, tails);
        coinImagesMap.put(Animation.ResultState.TAILS_HEADS, heads);
        coinImagesMap.put(Animation.ResultState.TAILS_TAILS, tails);
    }

    private void renderResult(final Animation.ResultState resultState) {
        Log.d(TAG, "renderResult()");


        // hide the static image and clear the text
        displayCoinImage(false);
        displayCoinAnimation(false);
        resultText.setText("");


        coinAnimation = Animation.getAnimation(resultState);
        coinAnimation.setAnimationCallback(new CustomAnimationDrawable.AnimationCallback() {
                @Override public void onAnimationFinish() {
                    playCoinSound();
                    updateResultText(resultState);
                    resumeListeners();
                }
            });

        // hide the static image and render the animation
        displayCoinImage(false);
        displayCoinAnimation(true);
        coinImage.setBackgroundDrawable(coinAnimation);
        coinAnimation.stop();
        coinAnimation.start();

    }

    private void initSounds() {
        // MediaPlayer was causing ANR issues on some devices.
        // SoundPool should be more efficient.

        Log.d(TAG, "initSounds()");
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 100);
        soundCoin = soundPool.load(this, R.raw.coin, 1);
        soundOneUp = soundPool.load(this, R.raw.oneup, 1);

    }

    private void playSound(final int sound) {
        Log.d(TAG, "playSound()");

            final AudioManager mgr = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
            final float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
            final float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            final float volume = streamVolumeCurrent / streamVolumeMax;

            soundPool.play(sound, volume, volume, 1, 0, 1f);

    }

    private void playCoinSound() {
        Log.d(TAG, "playCoinSound()");

        synchronized (this) {
            if (flipCounter < 100) {
                playSound(soundCoin);
            } else {
                // Happy Easter! (For Ryan)
                // Toast.makeText(this, "1-UP", Toast.LENGTH_SHORT).show();
                playSound(soundOneUp);
                flipCounter = 0;
            }
        }
    }

    private void updateResultText(final Animation.ResultState resultState) {
        Log.d(TAG, "updateResultText()");
            switch (resultState) {
                case HEADS_HEADS:
                case TAILS_HEADS:
                    resultText.setText(R.string.heads);
                    break;
                case HEADS_TAILS:
                case TAILS_TAILS:
                    resultText.setText(R.string.tails);
                    break;
                default:
                    resultText.setText(R.string.unknown);
                    break;
            }


        updateStatsText();

    }

    private void updateStatsText() {
        Log.d(TAG, "updateStatsText()");
        statsLayout.setVisibility(View.VISIBLE);
        headsStatText.setText(Integer.toString(headsCounter));
        tailsStatText.setText(Integer.toString(tailsCounter));
    }

    private void resetStatistics() {
        Log.d(TAG, "resetStatistics()");
        headsCounter = 0;
        tailsCounter = 0;
        updateStatsText();
    }

    private void displayCoinAnimation(final boolean flag) {
        Log.d(TAG, "displayCoinAnimation()");

        // safety first!
        if (coinAnimation != null) {
            if (flag) {
                coinAnimation.setAlpha(255);
            } else {
                coinAnimation.setAlpha(0);
            }
        }
    }

    private void displayCoinImage(final boolean flag) {
        Log.d(TAG, "displayCoinImage()");

        // safety first!
        if (coinImage != null) {
            if (flag) {
                // get rid of the animation background
                coinImage.setBackgroundDrawable(null);
                coinImage.setAlpha(255);
            } else {
                coinImage.setAlpha(0);
            }
        }
    }

    private void initViews() {
        Log.d(TAG, "initViews()");

        coinImage = (ImageView) findViewById(R.id.coin_image_view);
        resultText = (TextView) findViewById(R.id.result_text_view);
        mainLayout = (LinearLayout) findViewById(R.id.main_layout);
        headsStatText = (TextView) findViewById(R.id.heads_stat_text_view);
        tailsStatText = (TextView) findViewById(R.id.tails_stat_text_view);
        txtLanguage = (MyTextView) findViewById(R.id.txtLanguage);
        txtCustomize = (MyTextView) findViewById(R.id.txtCustomize);
        txtCoin = (MyTextView) findViewById(R.id.txtCoin);
        statsResetButton = (ImageButton) findViewById(R.id.stats_reset_button);
        statsLayout = (LinearLayout) findViewById(R.id.statistics_layout);

        //for theme
        relMain = findViewById(R.id.rel_main);
        txtTailsTitle = findViewById(R.id.txtTailsTitle);
        txtHeadsTitle = findViewById(R.id.txtHeadsTitle);
        cardMain = findViewById(R.id.card_main);
        cardBot = findViewById(R.id.card_bot);
    }
    private void setThemeById(int themeId) {
        if (themeId==1){
            relMain.setBackgroundColor(getResources().getColor(R.color.lime_dark));
            cardMain.setCardBackgroundColor(getResources().getColor(R.color.lime));
            cardBot.setCardBackgroundColor(getResources().getColor(R.color.green));
            DrawableCompat.setTint(statsResetButton.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCoin.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtLanguage.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCustomize.getCompoundDrawables()[0], ContextCompat.getColor(getApplicationContext(), R.color.white));
            txtHeadsTitle.setTextColor(getResources().getColor(R.color.white));
            txtTailsTitle.setTextColor(getResources().getColor(R.color.white));
            headsStatText.setTextColor(getResources().getColor(R.color.white));
            tailsStatText.setTextColor(getResources().getColor(R.color.white));
            resultText.setTextColor(getResources().getColor(R.color.white));
            txtCoin.setTextColor(getResources().getColor(R.color.white));
            txtLanguage.setTextColor(getResources().getColor(R.color.white));
            txtCustomize.setTextColor(getResources().getColor(R.color.white));

        }else if(themeId==2){
            relMain.setBackgroundColor(getResources().getColor(R.color.myBlue_dark));
            cardMain.setCardBackgroundColor(getResources().getColor(R.color.myBlue));
            cardBot.setCardBackgroundColor(getResources().getColor(R.color.myBlue_light));
            DrawableCompat.setTint(statsResetButton.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCoin.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtLanguage.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCustomize.getCompoundDrawables()[0], ContextCompat.getColor(getApplicationContext(), R.color.white));
            txtHeadsTitle.setTextColor(getResources().getColor(R.color.white));
            txtTailsTitle.setTextColor(getResources().getColor(R.color.white));
            headsStatText.setTextColor(getResources().getColor(R.color.white));
            tailsStatText.setTextColor(getResources().getColor(R.color.white));
            resultText.setTextColor(getResources().getColor(R.color.white));
            txtCoin.setTextColor(getResources().getColor(R.color.white));
            txtLanguage.setTextColor(getResources().getColor(R.color.white));
            txtCustomize.setTextColor(getResources().getColor(R.color.white));
        }else if(themeId==3){
            relMain.setBackgroundColor(getResources().getColor(R.color.red_dark));
            cardMain.setCardBackgroundColor(getResources().getColor(R.color.red));
            cardBot.setCardBackgroundColor(getResources().getColor(R.color.red_light));
            DrawableCompat.setTint(statsResetButton.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCoin.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtLanguage.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCustomize.getCompoundDrawables()[0], ContextCompat.getColor(getApplicationContext(), R.color.white));
            txtHeadsTitle.setTextColor(getResources().getColor(R.color.white));
            txtTailsTitle.setTextColor(getResources().getColor(R.color.white));
            headsStatText.setTextColor(getResources().getColor(R.color.white));
            tailsStatText.setTextColor(getResources().getColor(R.color.white));
            resultText.setTextColor(getResources().getColor(R.color.white));
            txtCoin.setTextColor(getResources().getColor(R.color.white));
            txtLanguage.setTextColor(getResources().getColor(R.color.white));
            txtCustomize.setTextColor(getResources().getColor(R.color.white));
        }else if(themeId==4){
            relMain.setBackgroundColor(getResources().getColor(R.color.black_dark));
            cardMain.setCardBackgroundColor(getResources().getColor(R.color.black));
            cardBot.setCardBackgroundColor(getResources().getColor(R.color.black_light));
            DrawableCompat.setTint(statsResetButton.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCoin.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtLanguage.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCustomize.getCompoundDrawables()[0], ContextCompat.getColor(getApplicationContext(), R.color.white));
            txtHeadsTitle.setTextColor(getResources().getColor(R.color.white));
            txtTailsTitle.setTextColor(getResources().getColor(R.color.white));
            headsStatText.setTextColor(getResources().getColor(R.color.white));
            tailsStatText.setTextColor(getResources().getColor(R.color.white));
            resultText.setTextColor(getResources().getColor(R.color.white));
            txtCoin.setTextColor(getResources().getColor(R.color.white));
            txtLanguage.setTextColor(getResources().getColor(R.color.white));
            txtCustomize.setTextColor(getResources().getColor(R.color.white));
        }else if(themeId==5){
            relMain.setBackgroundColor(getResources().getColor(R.color.golden_dark));
            cardMain.setCardBackgroundColor(getResources().getColor(R.color.golden));
            cardBot.setCardBackgroundColor(getResources().getColor(R.color.golden2));
            DrawableCompat.setTint(statsResetButton.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCoin.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtLanguage.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.white));
            DrawableCompat.setTint(txtCustomize.getCompoundDrawables()[0], ContextCompat.getColor(getApplicationContext(), R.color.white));
            txtHeadsTitle.setTextColor(getResources().getColor(R.color.white));
            txtTailsTitle.setTextColor(getResources().getColor(R.color.white));
            headsStatText.setTextColor(getResources().getColor(R.color.white));
            tailsStatText.setTextColor(getResources().getColor(R.color.white));
            resultText.setTextColor(getResources().getColor(R.color.white));
            txtCoin.setTextColor(getResources().getColor(R.color.white));
            txtLanguage.setTextColor(getResources().getColor(R.color.white));
            txtCustomize.setTextColor(getResources().getColor(R.color.white));
        }else{
            DrawableCompat.setTint(statsResetButton.getDrawable(), ContextCompat.getColor(getApplicationContext(), R.color.gray));
            DrawableCompat.setTint(txtCoin.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.gray));
            DrawableCompat.setTint(txtLanguage.getCompoundDrawables()[2], ContextCompat.getColor(getApplicationContext(), R.color.gray));
            DrawableCompat.setTint(txtCustomize.getCompoundDrawables()[0], ContextCompat.getColor(getApplicationContext(), R.color.gray));
            txtHeadsTitle.setTextColor(getResources().getColor(R.color.gray));
            txtTailsTitle.setTextColor(getResources().getColor(R.color.gray));
            headsStatText.setTextColor(getResources().getColor(R.color.gray));
            tailsStatText.setTextColor(getResources().getColor(R.color.gray));
            resultText.setTextColor(getResources().getColor(R.color.gray));
            txtCoin.setTextColor(getResources().getColor(R.color.gray));
            txtLanguage.setTextColor(getResources().getColor(R.color.gray));
            txtCustomize.setTextColor(getResources().getColor(R.color.gray));
        }
    }

    private void pauseListeners() {
        Log.d(TAG, "pauseListeners()");
        if (shaker != null) {
            shaker.pause();
        }
        if (tapper != null) {
            mainLayout.setOnClickListener(null);
        }
    }

    private void resumeListeners() {
        Log.d(TAG, "resumeListeners()");


        if (tapper != null) {
            mainLayout.setOnClickListener(tapper);
        }
    }
}
