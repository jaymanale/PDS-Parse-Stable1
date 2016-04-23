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
TextView getWheat,getRice,getSugar;
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
                        final ParseQuery query = new ParseQuery("Allocation");
                        query.getInBackground("dy3keagcCC", new GetCallback() {
                            @Override
                            public void done(ParseObject parseObject, ParseException e) {
                                if (e==null){

                                    int recWheat=parseObject.getInt("Wheat");
                                    int recRice=parseObject.getInt("Rice");
                                    int recSugar=parseObject.getInt("Sugar");


                                    getWheat.setText(recWheat +" Kg");
                                    getRice.setText(recRice +" Kg");
                                    getSugar.setText(recSugar +" Kg");
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
