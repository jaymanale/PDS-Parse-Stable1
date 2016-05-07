//package com.teamtreehouse.parseworkshop;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.parse.GetCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.teamtreehouse.readme.R;
//
//public class AllocationFair extends Activity {
//    protected ProgressBar mProgressBar;
//TextView getWheat,getRice,getSugar,getWheatFair,getRiceFair,getSugarFair,costView;
//    String CardTyperec,fmember;
//    String s1,s2;
//    String oCard="ORANGE",yCard="YELLOW",wCard="WHITE";
//    int familyMembercount;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.allocation_fair);
//
//
////        final ParseObject gameScore = new ParseObject("Allocation");
////        Intent allocation=getIntent();
////         CardTyperec=allocation.getStringExtra("checkType");
//////         fmember=allocation.getStringExtra("fmember");
////            familyMembercount=allocation.getIntExtra("fmember",0);
//
//
////        if (CardTyperec=="O"){
////            orangeCard(fmember);
////        }else if(CardTyperec=="Y"){
////            yellowCard(fmember);
////        }
////        else{
////            whiteCard(fmember);
////        }    getWheat=(TextView)findViewById(R.id.wheatQuantity);
//
//
////        Intent c1=getIntent();
////        String cardType=c1.getStringExtra("CardTypeCheck");
////        if(cardType.equals(oCard)){
////            init();
////        }
//
//
////            ParseObject cardOption=new ParseObject("Customer");
////            String cardTypeCheck = cardOption.getString("CardType");
////            if(cardTypeCheck=="ORANGE"){
////                init();
////            }
//
////            Intent i=getIntent();
////            String cardOrange=i.getStringExtra("cardTypecheck");
////
////            if(cardOrange.equals(oCard)){
////                init();
////            }
////
//
//init();
//
//    }
//
//
//
//
//    private void init() {
//        getRice = (TextView) findViewById(R.id.riceQuantity);
//        getSugar = (TextView) findViewById(R.id.sugarQuantity);
//        getWheatFair = (TextView) findViewById(R.id.FairWheatQuantity);
//        getRiceFair = (TextView) findViewById(R.id.FairRiceQuantity);
//        getSugarFair = (TextView) findViewById(R.id.FairSugarQuantity);
//        costView = (TextView) findViewById(R.id.totalCost1);
//
//
//                        final ParseQuery query = new ParseQuery("Allocation");
//
//                            query.getInBackground("dy3keagcCC", new GetCallback() {
//
//                                @Override
//                                public void done(ParseObject parseObject, ParseException e) {
//                                    if (e == null) {
//
////                                        int recWheatUser=familyMembercount;
//
//                                        int recWheat = parseObject.getInt("Wheat");
//                                        int recRice = parseObject.getInt("Rice");
//                                        int recSugar = parseObject.getInt("Sugar");
//                                        int recWheatFair = parseObject.getInt("WheatFair");
//                                        int recRiceFair = parseObject.getInt("RiceFair");
//                                        int recSugarFair = parseObject.getInt("SugarFair");
//
//
//
//                                        getWheat.setText(recWheat + " Kg");
//                                        getRice.setText(recRice + " Kg");
//                                        getSugar.setText(recSugar + " Kg");
//                                        getWheatFair.setText(recWheatFair + "/Kg");
//                                        getRiceFair.setText(recRiceFair + "/Kg");
//                                        getSugarFair.setText(recSugarFair + "/Kg");
//
//                                        int costToPay = ((recWheat * recWheatFair) + (recRice * recRiceFair) + (recSugar * recSugarFair));
//                                        costView.setText(costToPay + "Rs");
//
//
//                                    }
//                                }
//                            });
//
//
////                query.whereEqualTo("objectId", "dy3keagcCC");
////            query.findInBackground(new FindCallback() {
////                @Override
////            public void done(List<ParseObject> list, ParseException e) {
////                if (e == null) {
////                    String recWheat
////                    getWheat.setText("wheat kg:" + recWheat);
////
////
////                } else {
////                    Toast.makeText(AllocationFair.this,
////                            "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
////
////                }
////                    }
////                });
//
//
//
//
//
//
//
//    }
//}
package com.teamtreehouse.parseworkshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.teamtreehouse.readme.R;

import java.util.List;

public class AllocationFair extends Activity {
    TextView getWheat, getRice, getSugar, getWheatFair, getRiceFair, getSugarFair, costView;
    String fmember,cctype,wheatConvert,mobileNo,Message;
    int wheatTotal,riceTotal,sugarInt,wheatInt,mem,riceInt,wheatFairInt,riceFairInt,sugarFairInt,costToPay;
    Button getStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allocation_fair);

        final ParseObject gameScore = new ParseObject("Allocation");
        Intent submitbtn = getIntent();
         cctype=submitbtn.getStringExtra("ctype");
        fmember=submitbtn.getStringExtra("fmember");
        mobileNo=submitbtn.getStringExtra("fNumber");

        Toast.makeText(AllocationFair.this, "Card Type:" + mobileNo, Toast.LENGTH_SHORT).show();
        getWheat = (TextView) findViewById(R.id.wheatQuantity);
        getRice = (TextView) findViewById(R.id.riceQuantity);
        getSugar = (TextView) findViewById(R.id.sugarQuantity);
        getWheatFair = (TextView) findViewById(R.id.FairWheatQuantity);
        getRiceFair = (TextView) findViewById(R.id.FairRiceQuantity);
        getSugarFair = (TextView) findViewById(R.id.FairSugarQuantity);
        costView = (TextView) findViewById(R.id.totalCost1);
        getStatus=(Button)findViewById(R.id.checkStatus);


        
        if(cctype.equals("ORANGE")){
        orangeCard();
        }
        else if(cctype.equals("WHITE")){
            whiteCard();
        }
        else if(cctype.equals("YELLOW")){
            yellowCard();
        }


        getStatusButton();

//        cardTtpeCheck=Retrieve_data.getActivityInstance().getData();
//
//        Toast.makeText(AllocationFair.this, "Data from first activity is:" + cardTtpeCheck, Toast.LENGTH_SHORT).show();

    }



    public void getStatusButton() {
        getStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message="Fair /Kg:\n Wheat: "+ wheatFairInt + ", Rice: "+riceFairInt + ", Sugar " +
                        sugarFairInt + "\nTotal Quantity \n Wheat: " +wheatTotal + ", Rice: "+ riceTotal +", Sugar " +
                        sugarInt + "\n Total Fair: "+costToPay +" Rs";
                Toast.makeText(AllocationFair.this, "message" + Message, Toast.LENGTH_SHORT).show();

                sendSMS(mobileNo, Message);
                Intent allocation = new Intent(AllocationFair.this, AllocationStatus.class);
//                wheatConvert =Integer.toString(wheatTotal);

                allocation.putExtra("soldWheat", wheatTotal);
                allocation.putExtra("soldRice",riceTotal);
                allocation.putExtra("soldSugar",sugarInt);
                startActivity(allocation);

            }
        });

    }
    public void sendSMS(String phoneNumber, String message){

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS send Successfully...",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }




    private void yellowCard() {

        //        final ParseObject gameScore = new ParseObject("Allocation");
        final ParseQuery query = new ParseQuery("Allocation");
//       ParseQuery Cquery =new ParseQuery("Customer");
        query.whereEqualTo("CardType",cctype);


        query.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    String recRice = list.get(0).getString("Rice");
                    String recWheat = list.get(0).getString("Wheat");
                    String recSugar = list.get(0).getString("Sugar");
                    String recWheatFair = list.get(0).getString("WheatFair");
                    String recRiceFair = list.get(0).getString("RiceFair");
                    String recSugarFair = list.get(0).getString("SugarFair");

//                      Conrting to integer
                    wheatInt = Integer.parseInt(recWheat);
                    riceInt = Integer.parseInt(recRice);
                     sugarInt = Integer.parseInt(recSugar);
                    wheatFairInt = Integer.parseInt(recWheatFair);
                    riceFairInt = Integer.parseInt(recRiceFair);
                    sugarFairInt = Integer.parseInt(recSugarFair);
                     mem = Integer.parseInt(fmember);

                    // getting total quantity
                     wheatTotal = wheatInt * mem;
                     riceTotal = riceInt * mem;



                    getWheat.setText(wheatInt + " * " + mem + " = " + wheatTotal + " Kg");
                    getRice.setText(riceInt + " * " + mem + " =" + riceTotal + " Kg");
                    getSugar.setText(sugarInt + " Kg");

                    //getting total fair
                    int wheatFair = wheatFairInt * wheatTotal;
                    int riceFair = riceFairInt * riceTotal;
                    int sugarFair = sugarFairInt * sugarInt;

                    getWheatFair.setText(wheatFairInt + " * " + wheatTotal + " = " + "Rs. " + wheatFair);
                    getRiceFair.setText(riceFairInt + " * " + riceTotal + " = " + "Rs. " + riceFair);
                    getSugarFair.setText(sugarFairInt + " * " + sugarInt + " = " + "Rs. " + sugarFair);

//                    getRice.setText(riceInt + "Kg" );
//                    getSugar.setText(recSugar + " Kg");
//                    getWheatFair.setText(recWheatFair + "/Kg");
//                    getRiceFair.setText(recRiceFair + "/Kg");
//                    getSugarFair.setText(recSugarFair + "/Kg");

//                    int costToPay = ((wheatInt * wheatFairInt) + (riceInt * riceFairInt) + (sugarInt * sugarFairInt));
                     costToPay = wheatFair + riceFair + sugarFair;
                    costView.setText("Rs. " + costToPay);


                } else {
                    Toast.makeText(AllocationFair.this,
                            "Data not reterived...",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void whiteCard() {

        //        final ParseObject gameScore = new ParseObject("Allocation");
        final ParseQuery query = new ParseQuery("Allocation");
//       ParseQuery Cquery =new ParseQuery("Customer");
        query.whereEqualTo("CardType",cctype);


        query.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    String recRice = list.get(0).getString("Rice");
                    String recWheat = list.get(0).getString("Wheat");
                    String recSugar = list.get(0).getString("Sugar");
                    String recWheatFair = list.get(0).getString("WheatFair");
                    String recRiceFair = list.get(0).getString("RiceFair");
                    String recSugarFair = list.get(0).getString("SugarFair");

//                      Conrting to integer
                    wheatInt = Integer.parseInt(recWheat);
                     riceInt = Integer.parseInt(recRice);
                     sugarInt = Integer.parseInt(recSugar);
                     wheatFairInt = Integer.parseInt(recWheatFair);
                    riceFairInt = Integer.parseInt(recRiceFair);
                     sugarFairInt = Integer.parseInt(recSugarFair);
                     mem = Integer.parseInt(fmember);

                    // getting total quantity
                    wheatTotal = wheatInt * mem;
                     riceTotal = riceInt * mem;



                    getWheat.setText(wheatInt + " * " + mem + "=" + wheatTotal + " Kg");
                    getRice.setText(riceInt + " * " + mem + "=" + riceTotal + " Kg");
                    getSugar.setText(sugarInt + " Kg");

                    //getting total fair
                    int wheatFair = wheatFairInt * wheatTotal;
                    int riceFair = riceFairInt * riceTotal;
                    int sugarFair = sugarFairInt * sugarInt;

                    getWheatFair.setText(wheatFairInt + " * " + wheatTotal + " = " + "Rs. " + wheatFair);
                    getRiceFair.setText(riceFairInt + " * " + riceTotal + " = " + "Rs. " + riceFair);
                    getSugarFair.setText(sugarFairInt + " * " + sugarInt + " = " + "Rs. " + sugarFair);

//                    getRice.setText(riceInt + "Kg" );
//                    getSugar.setText(recSugar + " Kg");
//                    getWheatFair.setText(recWheatFair + "/Kg");
//                    getRiceFair.setText(recRiceFair + "/Kg");
//                    getSugarFair.setText(recSugarFair + "/Kg");

//                    int costToPay = ((wheatInt * wheatFairInt) + (riceInt * riceFairInt) + (sugarInt * sugarFairInt));
                    costToPay = wheatFair + riceFair + sugarFair;
                    costView.setText("Rs. " + costToPay);


                } else {
                    Toast.makeText(AllocationFair.this,
                            "Data not reterived...",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void orangeCard() {
     

//        final ParseObject gameScore = new ParseObject("Allocation");
        final ParseQuery query = new ParseQuery("Allocation");
//       ParseQuery Cquery =new ParseQuery("Customer");
       query.whereEqualTo("CardType",cctype);


        query.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    String recRice = list.get(0).getString("Rice");
                    String recWheat = list.get(0).getString("Wheat");
                    String recSugar = list.get(0).getString("Sugar");
                    String recWheatFair = list.get(0).getString("WheatFair");
                    String recRiceFair = list.get(0).getString("RiceFair");
                    String recSugarFair = list.get(0).getString("SugarFair");

//                      Conrting to integer
                     wheatInt = Integer.parseInt(recWheat);
                    riceInt = Integer.parseInt(recRice);
                    sugarInt = Integer.parseInt(recSugar);
                     wheatFairInt = Integer.parseInt(recWheatFair);
                     riceFairInt = Integer.parseInt(recRiceFair);
                    sugarFairInt = Integer.parseInt(recSugarFair);
                    mem = Integer.parseInt(fmember);

                    // getting total quantity
                    wheatTotal = wheatInt * mem;
                    riceTotal = riceInt * mem;
//                    int sugarTotal = sugarInt * mem;


                            getWheat.setText(wheatInt + " * " + mem + " = " + wheatTotal + " Kg");
                            getRice.setText(riceInt + " * " + mem + " = " + riceTotal + " Kg");
                            getSugar.setText(sugarInt + " Kg");

                            //getting total fair
                            int wheatFair = wheatFairInt * wheatTotal;
                            int riceFair = riceFairInt * riceTotal;
//                            int sugarFair = sugarFairInt * sugarTotal;

                            getWheatFair.setText(wheatFairInt + " * " + wheatTotal + " = " + "Rs. " + wheatFair);
                            getRiceFair.setText(riceFairInt + " * " + riceTotal + " = " + "Rs. " + riceFair);
                            getSugarFair.setText(sugarFairInt + " Kg");

//                    getRice.setText(riceInt + "Kg" );
//                    getSugar.setText(recSugar + " Kg");
//                    getWheatFair.setText(recWheatFair + "/Kg");
//                    getRiceFair.setText(recRiceFair + "/Kg");
//                    getSugarFair.setText(recSugarFair + "/Kg");

//                    int costToPay = ((wheatInt * wheatFairInt) + (riceInt * riceFairInt) + (sugarInt * sugarFairInt));
                            costToPay = wheatFair + riceFair;
                            costView.setText("Rs. " + costToPay);


                } else {
                    Toast.makeText(AllocationFair.this,
                            "Data not reterived...",
                            Toast.LENGTH_LONG).show();
                }
            }
        });



//        query.getInBackground("dy3keagcCC", new GetCallback() {
//            @Override
//            public void done(ParseObject parseObject, ParseException e) {
//                if (e == null) {
//
//
//
//
//                }
//            }
//        });
    }
}