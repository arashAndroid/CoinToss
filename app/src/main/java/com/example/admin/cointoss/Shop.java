package com.example.admin.cointoss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Inventory;
import com.example.android.trivialdrivesample.util.Purchase;

public class Shop extends AppCompatActivity {
    // Debug tag, for logging // Debug tag, for logging
    static final String TAG = "coinToss";
    // SKUs for our products: the premium upgrade (non-consumable)
    static final String SKU_PREMIUM = "upgrade_to_premium";
    // Does the user have the premium upgrade?
    boolean mIsPremium = false; // (arbitrary) request code for the purchase flow
    static final int RC_REQUEST = 1372; // The helper object
    IabHelper mHelper;
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener;
    Button btnBuy;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        btnBuy = findViewById(R.id.btnBuy);

        String base64EncodedPublicKey = "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwCc6UwyWENw6lmfxSIYRmxN8sTSRjEiNDiOsDG7J/EuKjNNwowr3zArGIy0nXfw6U1fd2SXwPI9buC2BCL/detzByUcYATq19GMnWhEHCid1oWKelKr0Q57uRIvOztF4WET2bv/S4qUUqrV7ukVGFm42nasJCqASiXZyJTF5pfu2UKHtxCo6Rt/+xqbPioV26LaXGItTfZurQoYbGNE7OalPVCDzI7iBmQb73gCzZUCAwEAAQ==";
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
            public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                Log.d(TAG, "Query inventory finished.");
            if (result.isFailure()) {
                Log.d(TAG, "Failed to query inventory: " + result);
                return;
            } else {
                Log.d(TAG, "Query inventory was successful.");
                // does the user have the premium upgrade?
                mIsPremium = inventory.hasPurchase(SKU_PREMIUM);
                if (mIsPremium){
                    sharedPrefManager.setPremium();
                }
                // update UI accordingly
                Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
                Toast.makeText(getApplicationContext(),"قبلا اینو خریدی! بسته دوباره فعال شد!",Toast.LENGTH_LONG);
            }
            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
            }
        };
        mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
                if (result.isFailure()) { Log.d(TAG, "Error purchasing: " + result); return;
                } else if (purchase.getSku().equals(SKU_PREMIUM)) {
                    // give user access to premium content and update the UI
                    Toast.makeText(Shop.this,"خرید موفق",Toast.LENGTH_SHORT).show();
                    sharedPrefManager.setPremium();
                }
            }
        };
        Log.d(TAG, "Starting setup.");
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.d(TAG, "Setup finished.");
                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    Log.d(TAG, "Problem setting up In-app Billing: " + result);
                } // Hooray, IAB is fully set up!
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                }catch (Exception e){

                    Toast.makeText(Shop.this,"بازار نصب نیست!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPrefManager.hasPremium()){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.alreadyPremium),Toast.LENGTH_SHORT).show();
                }else {
                    mHelper.launchPurchaseFlow(Shop.this, SKU_PREMIUM, RC_REQUEST, mPurchaseFinishedListener, "payload-string");
                }
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(TAG, "onActivityResult handled by IABUtil.");
        }
    }
}
