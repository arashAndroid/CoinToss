package com.example.admin.cointoss;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Locale;

public class LanguageDialog extends Dialog {

    public Activity c;
    public Dialog d;
    public RelativeLayout relFarsi,relEng,relSpanish,relFrench,relGerman;
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
        relFarsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
        relEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
        relSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
        relGerman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
        relFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

    }


}