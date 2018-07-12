package com.example.admin.cointoss;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ThemeDialog extends Dialog {

    Drawable heads ;
    Drawable tails;

    public Activity c;
    public Dialog d;
    public RelativeLayout relThemeDefault,relThemeGreen,relThemeBlue,relThemeRed,relThemeBlack,relThemeGolden;
    public LinearLayout lockThemeBlue,lockThemeRed,lockThemeBlack,lockThemeGolden;
    SharedPrefManager sharedPrefManager;
    public ThemeDialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.theme_dialog);
        sharedPrefManager = new SharedPrefManager(c.getApplicationContext());

        relThemeDefault = findViewById(R.id.relThemeDefault);
        relThemeGreen = findViewById(R.id.relThemeGreen);
        relThemeBlue = findViewById(R.id.relThemeBlue);
        relThemeRed = findViewById(R.id.relThemeRed);
        relThemeBlack = findViewById(R.id.relThemeBlack);
        relThemeGolden = findViewById(R.id.relThemeGolden);

        lockThemeBlue = findViewById(R.id.lockThemeBlue);
        lockThemeRed = findViewById(R.id.lockThemeRed);
        lockThemeBlack = findViewById(R.id.lockThemeBlack);
        lockThemeGolden = findViewById(R.id.lockThemeGolden);

        if (sharedPrefManager.hasPremium()){
            lockThemeBlue.setClickable(false);
            lockThemeBlue.setFocusable(false);
            lockThemeBlue.setVisibility(View.GONE);
            lockThemeRed.setClickable(false);
            lockThemeRed.setFocusable(false);
            lockThemeRed.setVisibility(View.GONE);
            lockThemeBlack.setClickable(false);
            lockThemeBlack.setFocusable(false);
            lockThemeBlack.setVisibility(View.GONE);
            lockThemeGolden.setClickable(false);
            lockThemeGolden.setFocusable(false);
            lockThemeGolden.setVisibility(View.GONE);

            relThemeBlue.setClickable(true);
            relThemeBlue.setFocusable(true);
            relThemeRed.setClickable(true);
            relThemeRed.setFocusable(true);
            relThemeBlack.setClickable(true);
            relThemeBlack.setFocusable(true);
            relThemeGolden.setClickable(true);
            relThemeGolden.setFocusable(true);

            relThemeBlue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPrefManager.setTheme(2);
                    dismiss();
                    c.recreate();
                }
            });
            relThemeRed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPrefManager.setTheme(3);
                    dismiss();
                    c.recreate();
                }
            });
            relThemeBlack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPrefManager.setTheme(4);
                    dismiss();
                    c.recreate();
                }
            });
            relThemeGolden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharedPrefManager.setTheme(5);
                    dismiss();
                    c.recreate();
                }
            });

        }else{
            relThemeBlue.setClickable(false);
            relThemeBlue.setFocusable(false);
            relThemeRed.setClickable(false);
            relThemeRed.setFocusable(false);
            relThemeBlack.setClickable(false);
            relThemeBlack.setFocusable(false);
            relThemeGolden.setClickable(false);
            relThemeGolden.setFocusable(false);

            lockThemeBlue.setClickable(true);
            lockThemeBlue.setFocusable(true);
            lockThemeBlue.setVisibility(View.VISIBLE);
            lockThemeRed.setClickable(true);
            lockThemeRed.setFocusable(true);
            lockThemeRed.setVisibility(View.VISIBLE);
            lockThemeBlack.setClickable(true);
            lockThemeBlack.setFocusable(true);
            lockThemeBlack.setVisibility(View.VISIBLE);
            lockThemeGolden.setClickable(true);
            lockThemeGolden.setFocusable(true);
            lockThemeGolden.setVisibility(View.VISIBLE);

            lockThemeBlue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
            lockThemeRed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });

            lockThemeBlack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });
            lockThemeGolden.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(c,Shop.class);
                    dismiss();
                    c.startActivity(intent);
                }
            });

        }

        relThemeDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.setTheme(0);
                dismiss();
                c.recreate();
            }
        });
        relThemeGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPrefManager.setTheme(1);
                dismiss();
                c.recreate();
            }
        });





    }



}