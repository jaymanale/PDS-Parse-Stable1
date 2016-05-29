package com.teamtreehouse.parseworkshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.teamtreehouse.readme.R;

import java.util.List;


public class AllocationStatus extends AppCompatActivity {
    ParseObject companyIdObject;
    TextView getWheatStatus,getRiceStatus,getSugarStatus,getAvaiWheat,getAvaiRice,getAvaiSugar,getuser;
    TextView remainWheat,remainRice,remainSugar;
    int soldWheat1,soldRice1,soldSugar1,avaiWheatInt,avaiRiceInt,avaiSugarInt;
    int remainingWheat,remainingRice,ramainingSugar;
    String id,objectId,user;
    Button successUpdate;
    String updateWheat,updateRice,updateSugar,setUser,cNumber,objectId1;
    ParseUser currentUser;





//    final ParseObject gameScore = new ParseObject("AllocationStatus");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allocation_status);


        Intent submitbtn = getIntent();
//        soldWheat1=Integer.parseInt(submitbtn.getStringExtra("soldWheat"));
        soldWheat1=submitbtn.getIntExtra("soldWheat", 0);
        soldRice1=submitbtn.getIntExtra("soldRice", 0);
        soldSugar1=submitbtn.getIntExtra("soldSugar", 0);
        cNumber=submitbtn.getStringExtra("cardNumbber");



        getWheatStatus=(TextView)findViewById(R.id.totalwheatQuantity);
        getRiceStatus=(TextView)findViewById(R.id.totalriceQuantity);
        getSugarStatus=(TextView)findViewById(R.id.totalsugarQuantity);

        getAvaiWheat=(TextView)findViewById(R.id.AvailableWheatQuantity);
        getAvaiRice=(TextView)findViewById(R.id.AvailableRiceQuantity);
        getAvaiSugar=(TextView)findViewById(R.id.AvailableSugarQuantity);

        remainWheat=(TextView)findViewById(R.id.remainWheatQ);
        remainRice=(TextView)findViewById(R.id.remainRiceQ) ;
        remainSugar=(TextView)findViewById(R.id.remainSugarQ);

//        getuser=(TextView)findViewById(R.id.userid);
        successUpdate=(Button)findViewById(R.id.success);


        currentUser =ParseUser.getCurrentUser();
        user=currentUser.getObjectId();


        init();
        Toast.makeText(AllocationStatus.this, "Id:" + user   , Toast.LENGTH_SHORT).show();
//        userId();
        updateServer();

    }

//    public void userId() {
//
//
//        gameScore.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e==null){
//                    currentUser
//
//                     objectId = gameScore.getObjectId();
//
//                }
//            }
//        });
//
//    }

    public void updateServer() {

        successUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
////                ParseQuery queryupdate = new ParseQuery("AllocationStatus");
//////        currentUser = ParseUser.getCurrentUser();
//////
//////                currentUser = ParseUser.getCurrentUser();
////                user=currentUser.getObjectId();
////                queryupdate.getInBackground(user, new GetCallback() {
////                    @Override
////                    public void done(ParseObject parseObject, ParseException e) {
////
////                        // updating info to server//create  another function*****
////
////                        parseObject.put("WheatQuantityStatus", updateWheat);
////                        parseObject.put("RiceQuantityStatus", updateRice);
////                        parseObject.put("SugarQuantityStatus", updateSugar);
////                        parseObject.saveInBackground();
////                        Intent allocation = new Intent(AllocationStatus.this, MainFeedActivity.class);
////
////                        startActivity(allocation);
//                        Toast.makeText(AllocationStatus.this,
//                                "Record updated successfully ",
//                                Toast.LENGTH_LONG).show();
//
//
//                    }
//
//
//                });
//
//
//
//            }
//        });
                if (user.equals("oHC3vXyeoJ")) {
                    setUser = "kQ5Ktckvhx";
                } else if (user.equals("y7tM7clzr5")) {
                    setUser = "IfnmIC6FaT";
                }

                ParseQuery queryupdate = new ParseQuery("AllocationStatus");
                queryupdate.getInBackground(setUser, new GetCallback() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {
                        //for jaymanale@gmail.com object id //hardcoded
                        if (e == null) {
                            parseObject.put("WheatQuantityStatus", updateWheat);
                            parseObject.put("RiceQuantityStatus", updateRice);
                            parseObject.put("SugarQuantityStatus", updateSugar);
                            parseObject.saveInBackground();
                        }
                    }
                });

                Toast.makeText(AllocationStatus.this,
                        "Record updated successfully: " + currentUser.get("username"),
                        Toast.LENGTH_LONG).show();
                Intent allocation = new Intent(AllocationStatus.this, MainFeedActivity.class);

                startActivity(allocation);

                lastVisit();
            }
        });

    }

    private void lastVisit() {

                dynamicUpdate();
        ParseQuery query = new ParseQuery("AllocationDate");
        query.getInBackground(objectId1, new GetCallback() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.put("CardNo", cNumber);

                    object.saveInBackground();
                } else {
                    Toast.makeText(AllocationStatus.this,
                            "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();


                }
            }
        });


    }
    public void dynamicUpdate(){
        final ParseQuery queryDynamic = new ParseQuery("AllocationDate");
        queryDynamic.whereEqualTo("CardNo", cNumber);
        queryDynamic.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e==null){
                     objectId1=list.get(0).getObjectId();

                }
                else {
                    Toast.makeText(AllocationStatus.this,
                            "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void init() {
        companyIdObject=new ParseObject("AllocationStatus");

        final ParseQuery query = new ParseQuery("AllocationStatus");
        query.include("ShopNo");

//        currentUser = ParseUser.getCurrentUser();


        query.whereEqualTo("ShopNo", currentUser);

        query.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    String recTotalWheat = list.get(0).getString("WheatTotalQuantity");
                    String recTotalRice = list.get(0).getString("RiceTotalQuantity");
                    String recTotalSugar = list.get(0).getString("SugarTotalQuantity");
                    String recAvaiWheat = list.get(0).getString("WheatQuantityStatus");
                    String recAvaiRice = list.get(0).getString("RiceQuantityStatus");
                    String recAvaiSugar = list.get(0).getString("SugarQuantityStatus");


                    companyIdObject.getObjectId();

                    //converting to integer

                    avaiWheatInt = Integer.parseInt(recAvaiWheat);
                    avaiRiceInt = Integer.parseInt(recAvaiRice);
                    avaiSugarInt = Integer.parseInt(recAvaiSugar);


                    getWheatStatus.setText(recTotalWheat + " Kg");
                    getRiceStatus.setText(recTotalRice + " Kg");
                    getSugarStatus.setText(recTotalSugar + " Kg");

                    getAvaiWheat.setText(avaiWheatInt + " Kg");
                    getAvaiRice.setText(avaiRiceInt + " Kg");
                    getAvaiSugar.setText(avaiSugarInt + " Kg");




                    // performing calculation
                    remainingWheat = avaiWheatInt - soldWheat1;
                    remainingRice = avaiRiceInt - soldRice1;
                    ramainingSugar = avaiSugarInt - soldSugar1;


                    remainWheat.setText(remainingWheat + " Kg");
                    remainRice.setText(remainingRice + " Kg");
                    remainSugar.setText(ramainingSugar + " Kg");


                    // converting to string to update on server
                    updateWheat = Integer.toString(remainingWheat);
                    updateRice = Integer.toString(remainingRice);
                    updateSugar = Integer.toString(ramainingSugar);


                } else {
                    Toast.makeText(AllocationStatus.this,
                            "Data not reterived...",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }




}
