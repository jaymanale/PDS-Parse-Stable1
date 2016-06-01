package com.PDS.stackbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.PDS.readme.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Dashboard extends AppCompatActivity {

    ParseObject companyIdObject;
    TextView totalStatus, wheatStatus, riceStatus, sugarStatus;
    int sumWheatInt, sumRiceInt, sumSugarInt, totalAmount, wheatProgress, riceProgress, sugarProgress;
    ParseUser currentUser;
    ProgressBar myprogressBar, progressRice, progressWheat, progressSugar, totalProgress;
    TextView progressingTextView;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        myprogressBar = (ProgressBar) findViewById(R.id.progressBar);

        totalStatus = (TextView) findViewById(R.id.progress_circle_text);
        riceStatus = (TextView) findViewById(R.id.progress_circle_text3);
        wheatStatus = (TextView) findViewById(R.id.progress_circle_text1);
        sugarStatus = (TextView) findViewById(R.id.progress_circle_text4);
        progressRice = (ProgressBar) findViewById(R.id.progressBar3);
        progressWheat = (ProgressBar) findViewById(R.id.progressBar1);
        progressSugar = (ProgressBar) findViewById(R.id.progressBar4);
        totalProgress = (ProgressBar) findViewById(R.id.progressBar);
        currentUser = ParseUser.getCurrentUser();


        init();
    }

    public void init() {
        companyIdObject = new ParseObject("AllocationStatus");

        final ParseQuery query = new ParseQuery("AllocationStatus");
        query.include("ShopNo");


        query.whereEqualTo("ShopNo", currentUser);

        query.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    String statusTotalWheat = list.get(0).getString("WheatTotalQuantity");
                    String statusTotalRice = list.get(0).getString("RiceTotalQuantity");
                    String statusTotalSugar = list.get(0).getString("SugarTotalQuantity");
                    String statusAvaiWheat = list.get(0).getString("WheatQuantityStatus");
                    String statusAvaiRice = list.get(0).getString("RiceQuantityStatus");
                    String statusAvaiSugar = list.get(0).getString("SugarQuantityStatus");


                    companyIdObject.getObjectId();


                    sumWheatInt = Integer.parseInt(statusTotalWheat);
                    sumRiceInt = Integer.parseInt(statusTotalRice);
                    sumSugarInt = Integer.parseInt(statusTotalSugar);
                    wheatProgress = Integer.parseInt(statusAvaiWheat);
                    riceProgress = Integer.parseInt(statusAvaiRice);
                    sugarProgress = Integer.parseInt(statusAvaiSugar);

                    totalAmount = sumRiceInt + sumSugarInt + sumWheatInt;

                    totalStatus.setText(totalAmount + " Kg");
                    wheatStatus.setText(statusAvaiWheat + " Kg");
                    riceStatus.setText(statusAvaiRice + " Kg");
                    sugarStatus.setText(statusAvaiSugar + " Kg");

                    totalProgress.setProgress(totalAmount);
                    progressWheat.setProgress(wheatProgress);
                    progressRice.setProgress(riceProgress);
                    progressSugar.setProgress(sugarProgress);


                } else {
                    Toast.makeText(Dashboard.this,
                            "Data not reterived...",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}