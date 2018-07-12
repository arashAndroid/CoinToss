package com.example.admin.cointoss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Shop extends AppCompatActivity {

    Button btnBuy;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPrefManager.hasPremium()){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.alreadyPremium),Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
    }
}
