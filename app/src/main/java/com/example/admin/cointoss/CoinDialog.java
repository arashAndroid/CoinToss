package com.example.admin.cointoss;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Locale;

public class CoinDialog extends Dialog {

    Drawable heads ;
    Drawable tails;

    public Activity c;
    public Dialog d;
    public RelativeLayout relCoin500,relCoin200,relCoin100,relCoin50,relCoin25,relCoin10,relCoin5,relCoin52,relCoin2,relCoin1,relCoind1;
    public LinearLayout lockCoin25,lockCoin10,lockCoin5,lockCoin52,lockCoin2,lockCoin1,lockCoind1;
    //For Theme
    public CardView card500,card200,card100,card50,card1d,card25,card10,card5,card52,card2,card1;
    public CardView cardBot500,cardBot200,cardBot100,cardBot50,cardBot1d,cardBot25,cardBot10,cardBot5,cardBot52,cardBot2,cardBot1;
    SharedPrefManager sharedPrefManager;
    public CoinDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.coins_dialog);
        sharedPrefManager = new SharedPrefManager(c.getApplicationContext());

        relCoin500 = findViewById(R.id.relCoin500);
        relCoin200 = findViewById(R.id.relCoin200);
        relCoin100 = findViewById(R.id.relCoin100);
        relCoin50 = findViewById(R.id.relCoin50);
        relCoin25 = findViewById(R.id.relCoin25);
        relCoin10 = findViewById(R.id.relCoin10);
        relCoin5 = findViewById(R.id.relCoin5);
        relCoin52 = findViewById(R.id.relCoin52);
        relCoin2 = findViewById(R.id.relCoin2);
        relCoin1 = findViewById(R.id.relCoin1);
        relCoind1 = findViewById(R.id.relCoind1);

        lockCoin25 = findViewById(R.id.lockCoin25);
        lockCoin10 = findViewById(R.id.lockCoin10);
        lockCoin5 = findViewById(R.id.lockCoin5);
        lockCoin52 = findViewById(R.id.lockCoin52);
        lockCoin2 = findViewById(R.id.lockCoin2);
        lockCoin1 = findViewById(R.id.lockCoin1);
        lockCoind1 = findViewById(R.id.lockCoind1);

        //For Theme
        card500 = findViewById(R.id.card500);
        card200 = findViewById(R.id.card200);
        card100 = findViewById(R.id.card100);
        card50  = findViewById(R.id.card50);
        card1d  = findViewById(R.id.card1d);
        card25  = findViewById(R.id.card25);
        card10  = findViewById(R.id.card10);
        card5   = findViewById(R.id.card5);
        card52  = findViewById(R.id.card52);
        card2   = findViewById(R.id.card2);
        card1   = findViewById(R.id.card1);
        cardBot500  = findViewById(R.id.cardBot500);
        cardBot200   = findViewById(R.id.cardBot200);
        cardBot100   = findViewById(R.id.cardBot100);
        cardBot50   = findViewById(R.id.cardBot50);
        cardBot1d   = findViewById(R.id.cardBot1d);
        cardBot25   = findViewById(R.id.cardBot25);
        cardBot10    = findViewById(R.id.cardBot10);
        cardBot5    = findViewById(R.id.cardBot5);
        cardBot52    = findViewById(R.id.cardBot52);
        cardBot2    = findViewById(R.id.cardBot2);
        cardBot1    = findViewById(R.id.cardBot1);

        setThemeById(sharedPrefManager.getTheme());
        //

        if (sharedPrefManager.hasPremium()){
            lockCoin25.setClickable(false);
            lockCoin25.setFocusable(false);
            lockCoin25.setVisibility(View.GONE);
            lockCoin10.setClickable(false);
            lockCoin10.setFocusable(false);
            lockCoin10.setVisibility(View.GONE);
            lockCoin5.setClickable(false);
            lockCoin5.setFocusable(false);
            lockCoin5.setVisibility(View.GONE);
            lockCoin52.setClickable(false);
            lockCoin52.setFocusable(false);
            lockCoin52.setVisibility(View.GONE);
            lockCoin2.setClickable(false);
            lockCoin2.setFocusable(false);
            lockCoin2.setVisibility(View.GONE);
            lockCoin1.setClickable(false);
            lockCoin1.setFocusable(false);
            lockCoin1.setVisibility(View.GONE);
            lockCoind1.setClickable(false);
            lockCoind1.setFocusable(false);
            lockCoind1.setVisibility(View.GONE);

            relCoin25.setClickable(true);
            relCoin25.setFocusable(true);
            relCoin10.setClickable(true);
            relCoin10.setFocusable(true);
            relCoin5.setClickable(true);
            relCoin5.setFocusable(true);
            relCoin52.setClickable(true);
            relCoin52.setFocusable(true);
            relCoin2.setClickable(true);
            relCoin2.setFocusable(true);
            relCoin1.setClickable(true);
            relCoin1.setFocusable(true);
            relCoind1.setClickable(true);
            relCoind1.setFocusable(true);

            relCoin25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCoinImageId(R.drawable.b25,R.drawable.f25);
                }
            });
            relCoin10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCoinImageId(R.drawable.b10,R.drawable.f10);
                }
            });
            relCoin5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCoinImageId(R.drawable.b5,R.drawable.f5);
                }
            });
            relCoin52.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCoinImageId(R.drawable.b52,R.drawable.f52);
                }
            });
            relCoin2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCoinImageId(R.drawable.b2,R.drawable.f2);
                }
            });
            relCoin1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCoinImageId(R.drawable.b1,R.drawable.f1);
                }
            });
            relCoind1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setCoinImageId(R.drawable.db1,R.drawable.df1);
                }
            });
        }else{
            relCoin25.setClickable(false);
            relCoin25.setFocusable(false);
            relCoin10.setClickable(false);
            relCoin10.setFocusable(false);
            relCoin5.setClickable(false);
            relCoin5.setFocusable(false);
            relCoin52.setClickable(false);
            relCoin52.setFocusable(false);
            relCoin2.setClickable(false);
            relCoin2.setFocusable(false);
            relCoin1.setClickable(false);
            relCoin1.setFocusable(false);
            relCoind1.setClickable(false);
            relCoind1.setFocusable(false);

            lockCoin25.setClickable(true);
            lockCoin25.setFocusable(true);
            lockCoin25.setVisibility(View.VISIBLE);
            lockCoin10.setClickable(true);
            lockCoin10.setFocusable(true);
            lockCoin10.setVisibility(View.VISIBLE);
            lockCoin5.setClickable(true);
            lockCoin5.setFocusable(true);
            lockCoin5.setVisibility(View.VISIBLE);
            lockCoin52.setClickable(true);
            lockCoin52.setFocusable(true);
            lockCoin52.setVisibility(View.VISIBLE);
            lockCoin2.setClickable(true);
            lockCoin2.setFocusable(true);
            lockCoin2.setVisibility(View.VISIBLE);
            lockCoin1.setClickable(true);
            lockCoin1.setFocusable(true);
            lockCoin1.setVisibility(View.VISIBLE);
            lockCoind1.setClickable(true);
            lockCoind1.setFocusable(true);
            lockCoind1.setVisibility(View.VISIBLE);

            lockCoin25.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
            lockCoin10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
            lockCoin5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
            lockCoin52.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
            lockCoin2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
            lockCoin1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
            lockCoind1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
        }

        relCoin500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCoinImageId(R.drawable.b500,R.drawable.f500);
            }
        });
        relCoin200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCoinImageId(R.drawable.b200,R.drawable.f200);
            }
        });
        relCoin100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCoinImageId(R.drawable.b100,R.drawable.f100);
            }
        });
        relCoin50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCoinImageId(R.drawable.b50,R.drawable.f50);
            }
        });




    }

    private void setThemeById(int themeId) {
        if (themeId==1){
            card500.setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card200.setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card100.setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card50 .setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card1d .setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card25 .setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card10 .setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card52 .setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card5  .setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card2  .setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            card1  .setCardBackgroundColor(c.getResources().getColor(R.color.lime));
            cardBot500.setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot200.setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot100.setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot50 .setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot1d .setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot25 .setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot10 .setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot5  .setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot52 .setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot2  .setCardBackgroundColor(c.getResources().getColor(R.color.green));
            cardBot1  .setCardBackgroundColor(c.getResources().getColor(R.color.green));
        }else if (themeId==2){
            card500.setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card200.setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card100.setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card50 .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card1d .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card25 .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card10 .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card52 .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card5  .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card2  .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            card1  .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue));
            cardBot500.setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot200.setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot100.setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot50 .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot1d .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot25 .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot10 .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot5  .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot52 .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot2  .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
            cardBot1  .setCardBackgroundColor(c.getResources().getColor(R.color.myBlue_light));
        }else if (themeId==3){
            card500.setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card200.setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card100.setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card50 .setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card1d .setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card25 .setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card10 .setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card52 .setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card5  .setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card2  .setCardBackgroundColor(c.getResources().getColor(R.color.red));
            card1  .setCardBackgroundColor(c.getResources().getColor(R.color.red));
            cardBot500.setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot200.setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot100.setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot50 .setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot1d .setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot25 .setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot10 .setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot5  .setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot52 .setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot2  .setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
            cardBot1  .setCardBackgroundColor(c.getResources().getColor(R.color.red_light));
        }else if (themeId==4){
            card500.setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card200.setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card100.setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card50 .setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card1d .setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card25 .setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card10 .setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card52 .setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card5  .setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card2  .setCardBackgroundColor(c.getResources().getColor(R.color.black));
            card1  .setCardBackgroundColor(c.getResources().getColor(R.color.black));
            cardBot500.setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot200.setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot100.setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot50 .setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot1d .setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot25 .setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot10 .setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot5  .setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot52 .setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot2  .setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
            cardBot1  .setCardBackgroundColor(c.getResources().getColor(R.color.black_light));
        }else if (themeId==5){
            card500.setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card200.setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card100.setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card50 .setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card1d .setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card25 .setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card10 .setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card52 .setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card5  .setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card2  .setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            card1  .setCardBackgroundColor(c.getResources().getColor(R.color.golden));
            cardBot500.setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot200.setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot100.setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot50 .setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot1d .setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot25 .setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot10 .setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot5  .setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot52 .setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot2  .setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
            cardBot1  .setCardBackgroundColor(c.getResources().getColor(R.color.golden2));
        }else{

        }
    }

    public void setCoinImageId(int headsID,int tailsID) {
        sharedPrefManager.setTailsId(tailsID);
        sharedPrefManager.setHeadsId(headsID);
        dismiss();
        c.recreate();
    }


}