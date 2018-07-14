package com.example.admin.cointoss;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Locale;

public class LanguageDialog extends Dialog {

    public Activity c;
    public Dialog d;
    public RelativeLayout relFarsi,relEng,relSpanish,relFrench,relGerman;
    //For Theme
    public CardView cardLanguage;
    public View view1,view2,view3,view4;
    public MyTextView txtFarsi,txtEnglish,txtSpanish,txtGerman,txtFrench;
    SharedPrefManager sharedPrefManager;
    public LanguageDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.language_dialog);
        sharedPrefManager = new SharedPrefManager(c.getApplicationContext());
        relFarsi = findViewById(R.id.relFarsi);
        relEng = findViewById(R.id.relEng);
        relSpanish = findViewById(R.id.relSpain);
        relFrench = findViewById(R.id.relFrench);
        relGerman = findViewById(R.id.relGerman);
        //For Theme
        cardLanguage = findViewById(R.id.cardLanguage);
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        txtFarsi = findViewById(R.id.txtFarsi);
        txtEnglish = findViewById(R.id.txtEnglish);
        txtSpanish = findViewById(R.id.txtSpanish);
        txtGerman = findViewById(R.id.txtGerman);
        txtFrench = findViewById(R.id.txtFrench);

        setThemeById(sharedPrefManager.getTheme());

        relFarsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    sharedPrefManager.setLanguage("fa");
                    Resources resources = c.getResources();
                    Locale locale = new Locale("fa");
                    Configuration configuration = resources.getConfiguration();
                    configuration.setLocale(locale);
                    c.getBaseContext().getResources().updateConfiguration(configuration,
                            c.getBaseContext().getResources().getDisplayMetrics());
                    dismiss();
                    c.recreate();
                }
            }
        });
        relEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    sharedPrefManager.setLanguage("en");
                    Resources resources = c.getResources();
                    Locale locale = new Locale("en");
                    Configuration configuration = resources.getConfiguration();

                    configuration.setLocale(locale);

                    c.getBaseContext().getResources().updateConfiguration(configuration,
                            c.getBaseContext().getResources().getDisplayMetrics());
                    dismiss();
                    c.recreate();
                }
            }
        });
        relSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    sharedPrefManager.setLanguage("es");
                    Resources resources = c.getResources();
                    Locale locale = new Locale("es");
                    Configuration configuration = resources.getConfiguration();
                    configuration.setLocale(locale);
                    c.getBaseContext().getResources().updateConfiguration(configuration,
                            c.getBaseContext().getResources().getDisplayMetrics());
                    dismiss();
                    c.recreate();
                }
            }
        });
        relGerman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    sharedPrefManager.setLanguage("de");
                    Resources resources = c.getResources();
                    Locale locale = new Locale("de");
                    Configuration configuration = resources.getConfiguration();
                    configuration.setLocale(locale);
                    c.getBaseContext().getResources().updateConfiguration(configuration,
                            c.getBaseContext().getResources().getDisplayMetrics());
                    dismiss();
                    c.recreate();
                }
            }
        });
        relFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    sharedPrefManager.setLanguage("fr");
                    Resources resources = c.getResources();
                    Locale locale = new Locale("fr");
                    Configuration configuration = resources.getConfiguration();
                    configuration.setLocale(locale);
                    c.getBaseContext().getResources().updateConfiguration(configuration,
                            c.getBaseContext().getResources().getDisplayMetrics());
                    dismiss();
                    c.recreate();
                }
            }
        });

    }

    private void setThemeById(int themeId) {
        if (themeId==1){
            cardLanguage.setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            view1.setBackgroundColor(c.getResources().getColor(R.color.white));
            view2.setBackgroundColor(c.getResources().getColor(R.color.white));
            view3.setBackgroundColor(c.getResources().getColor(R.color.white));
            view4.setBackgroundColor(c.getResources().getColor(R.color.white));
            txtFarsi.setTextColor(c.getResources().getColor(R.color.white));
            txtEnglish.setTextColor(c.getResources().getColor(R.color.white));
            txtSpanish.setTextColor(c.getResources().getColor(R.color.white));
            txtGerman.setTextColor(c.getResources().getColor(R.color.white));
            txtFrench.setTextColor(c.getResources().getColor(R.color.white));
        }else if (themeId==2){
            cardLanguage.setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            view1.setBackgroundColor(c.getResources().getColor(R.color.white));
            view2.setBackgroundColor(c.getResources().getColor(R.color.white));
            view3.setBackgroundColor(c.getResources().getColor(R.color.white));
            view4.setBackgroundColor(c.getResources().getColor(R.color.white));
            txtFarsi.setTextColor(c.getResources().getColor(R.color.white));
            txtEnglish.setTextColor(c.getResources().getColor(R.color.white));
            txtSpanish.setTextColor(c.getResources().getColor(R.color.white));
            txtGerman.setTextColor(c.getResources().getColor(R.color.white));
            txtFrench.setTextColor(c.getResources().getColor(R.color.white));
        }else if (themeId==3){
            cardLanguage.setCardBackgroundColor(c.getResources().getColor(R.color.red));
            view1.setBackgroundColor(c.getResources().getColor(R.color.white));
            view2.setBackgroundColor(c.getResources().getColor(R.color.white));
            view3.setBackgroundColor(c.getResources().getColor(R.color.white));
            view4.setBackgroundColor(c.getResources().getColor(R.color.white));
            txtFarsi.setTextColor(c.getResources().getColor(R.color.white));
            txtEnglish.setTextColor(c.getResources().getColor(R.color.white));
            txtSpanish.setTextColor(c.getResources().getColor(R.color.white));
            txtGerman.setTextColor(c.getResources().getColor(R.color.white));
            txtFrench.setTextColor(c.getResources().getColor(R.color.white));
        }else if (themeId==4){
            cardLanguage.setCardBackgroundColor(c.getResources().getColor(R.color.black));
            view1.setBackgroundColor(c.getResources().getColor(R.color.white));
            view2.setBackgroundColor(c.getResources().getColor(R.color.white));
            view3.setBackgroundColor(c.getResources().getColor(R.color.white));
            view4.setBackgroundColor(c.getResources().getColor(R.color.white));
            txtFarsi.setTextColor(c.getResources().getColor(R.color.white));
            txtEnglish.setTextColor(c.getResources().getColor(R.color.white));
            txtSpanish.setTextColor(c.getResources().getColor(R.color.white));
            txtGerman.setTextColor(c.getResources().getColor(R.color.white));
            txtFrench.setTextColor(c.getResources().getColor(R.color.white));
        }else if (themeId==5){
            cardLanguage.setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            view1.setBackgroundColor(c.getResources().getColor(R.color.white));
            view2.setBackgroundColor(c.getResources().getColor(R.color.white));
            view3.setBackgroundColor(c.getResources().getColor(R.color.white));
            view4.setBackgroundColor(c.getResources().getColor(R.color.white));
            txtFarsi.setTextColor(c.getResources().getColor(R.color.white));
            txtEnglish.setTextColor(c.getResources().getColor(R.color.white));
            txtSpanish.setTextColor(c.getResources().getColor(R.color.white));
            txtGerman.setTextColor(c.getResources().getColor(R.color.white));
            txtFrench.setTextColor(c.getResources().getColor(R.color.white));
        }else{

        }
    }


}