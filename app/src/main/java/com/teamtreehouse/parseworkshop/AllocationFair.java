package com.teamtreehouse.parseworkshop;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.teamtreehouse.readme.R;

public class AllocationFair extends Activity {
TextView getWheat,getRice,getSugar,getWheatFair,getRiceFair,getSugarFair,costView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allocation_fair);
        final ParseObject gameScore = new ParseObject("Allocation");
        init();
    }

    private void init() {
        getWheat=(TextView)findViewById(R.id.wheatQuantity);
        getRice=(TextView)findViewById(R.id.riceQuantity);
        getSugar=(TextView)findViewById(R.id.sugarQuantity);
        getWheatFair=(TextView)findViewById(R.id.FairWheatQuantity);
        getRiceFair=(TextView)findViewById(R.id.FairRiceQuantity);
        getSugarFair=(TextView)findViewById(R.id.FairSugarQuantity);
        costView=(TextView)findViewById(R.id.totalCost1);

                        final ParseQuery query = new ParseQuery("Allocation");
                        query.getInBackground("dy3keagcCC", new GetCallback() {
                            @Override
                            public void done(ParseObject parseObject, ParseException e) {
                                if (e==null){

                                    int recWheat=parseObject.getInt("Wheat");
                                    int recRice=parseObject.getInt("Rice");
                                    int recSugar=parseObject.getInt("Sugar");
                                    int recWheatFair=parseObject.getInt("WheatFair");
                                    int recRiceFair=parseObject.getInt("RiceFair");
                                    int recSugarFair=parseObject.getInt("SugarFair");


                                    getWheat.setText(recWheat +" Kg");
                                    getRice.setText(recRice +" Kg");
                                    getSugar.setText(recSugar +" Kg");
                                    getWheatFair.setText(recWheatFair + "/Kg");
                                    getRiceFair.setText(recRiceFair + "/Kg");
                                    getSugarFair.setText(recSugarFair + "/Kg");

                                    int costToPay=((recWheat*recWheatFair)+(recRice*recRiceFair)+(recSugar*recSugarFair));
                                    costView.setText(costToPay +"Rs");


                                }
                            }
                        });

//                query.whereEqualTo("objectId", "dy3keagcCC");
//            query.findInBackground(new FindCallback() {
//                @Override
//            public void done(List<ParseObject> list, ParseException e) {
//                if (e == null) {
//                    String recWheat
//                    getWheat.setText("wheat kg:" + recWheat);
//
//
//                } else {
//                    Toast.makeText(AllocationFair.this,
//                            "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//                    }
//                });







    }
}
