package com.example.admin.cointoss;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.NumberFormat;

public class SelfTestActivity extends Activity {

    // debugging tag
    private static final String TAG = SelfTestActivity.class.getSimpleName();

    private long maxNumberFlips;

    private TextView headsValue;

    private TextView headsRatio;

    private TextView tailsValue;

    private TextView tailsRatio;

    private TextView totalValue;

    private TextView totalRatio;

    private TextView elapsedTime;

    private SelfTestTask backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_test);

        headsValue = (TextView) findViewById(R.id.heads_value_tv);
        headsRatio = (TextView) findViewById(R.id.heads_ratio_tv);
        tailsValue = (TextView) findViewById(R.id.tails_value_tv);
        tailsRatio = (TextView) findViewById(R.id.tails_ratio_tv);
        totalValue = (TextView) findViewById(R.id.total_value_tv);
        totalRatio = (TextView) findViewById(R.id.total_ratio_tv);
        elapsedTime = (TextView) findViewById(R.id.elapsed_time_tv);


        backgroundTask = new SelfTestTask(this);
        backgroundTask.execute(new SelfTestStatus());
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
        backgroundTask.cancel(true);
    }

    // this method is called when the async task reports it has new information
    public void updateDialog(SelfTestStatus taskStatus) {
        //Log.d(TAG, "updateDialog()");

        final NumberFormat percentFormat = NumberFormat.getPercentInstance();
        percentFormat.setMaximumFractionDigits(2);

        headsValue.setText(Integer.toString(taskStatus.getHeads()));
        headsRatio.setText("("
                + percentFormat.format(taskStatus.getHeadsPercentage()) + ")");

        tailsValue.setText(Integer.toString(taskStatus.getTails()));
        tailsRatio.setText("("
                + percentFormat.format(taskStatus.getTailsPercentage()) + ")");

        totalValue.setText(Integer.toString(taskStatus.getTotal()));
        totalRatio.setText("("
                + percentFormat.format((double) taskStatus.getTotal() / maxNumberFlips)
                + ")");

        elapsedTime.setText(Long.toString(taskStatus.getElapsedTime()));
    }

    public long getMaxNumberFlips() {
        return maxNumberFlips;
    }

}
