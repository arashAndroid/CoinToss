package com.example.admin.cointoss;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {

    // debugging tag
    private static final String TAG = AboutActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // pull the version name from the manifest so it doesn't have to be manually updated in the strings files
        try {
            String versionName = getPackageManager()
                    .getPackageInfo(getPackageName(), 0).versionName;
            Log.d(TAG, "versionName=" + versionName);
            TextView versionText = (TextView) findViewById(R.id.about_version_text_view);
            versionText.setText(versionName);
        } catch (NameNotFoundException e) {
            // nothing
        }

        // create a link to the Play store so users can easily rate this app
        Button rateButton = (Button) findViewById(R.id.about_rate_button);
        rateButton.setOnClickListener(new OnClickListener() {
            public void onClick(final View v) {
                final Intent goToMarket = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + getPackageName()));
                startActivity(goToMarket);
            }
        });
    }
}
