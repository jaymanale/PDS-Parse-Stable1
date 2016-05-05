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
    String cardTtpeCheck,cctype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allocation_fair);

        final ParseObject gameScore = new ParseObject("Allocation");
        Intent submitbtn = getIntent();
         cctype=submitbtn.getStringExtra("ctype");
        Toast.makeText(AllocationFair.this, "Data from first activity is:" + cctype, Toast.LENGTH_SHORT).show();


        init();

//        cardTtpeCheck=Retrieve_data.getActivityInstance().getData();
//
//        Toast.makeText(AllocationFair.this, "Data from first activity is:" + cardTtpeCheck, Toast.LENGTH_SHORT).show();

    }

    private void init() {
        getWheat = (TextView) findViewById(R.id.wheatQuantity);
        getRice = (TextView) findViewById(R.id.riceQuantity);
        getSugar = (TextView) findViewById(R.id.sugarQuantity);
        getWheatFair = (TextView) findViewById(R.id.FairWheatQuantity);
        getRiceFair = (TextView) findViewById(R.id.FairRiceQuantity);
        getSugarFair = (TextView) findViewById(R.id.FairSugarQuantity);
        costView = (TextView) findViewById(R.id.totalCost1);

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
                    int wheatInt=Integer.parseInt(recWheat);
                    int riceInt=Integer.parseInt(recRice);
                    int sugarInt=Integer.parseInt(recSugar);
                    int wheatFairInt=Integer.parseInt(recWheatFair);
                    int riceFairInt=Integer.parseInt(recRiceFair);
                    int sugarFairInt=Integer.parseInt(recSugarFair);





                    getRice.setText(recRice + " Kg");
                    getWheat.setText(recWheat + " Kg");
                    getSugar.setText(recSugar + " Kg");
                    getWheatFair.setText(recWheatFair + "/Kg");
                    getRiceFair.setText(recRiceFair + "/Kg");
                    getSugarFair.setText(recSugarFair + "/Kg");

                    int costToPay = ((wheatInt * wheatFairInt) + (riceInt * riceFairInt) + (sugarInt * sugarFairInt));
                    costView.setText(costToPay + "Rs");



                } else {
                    Toast.makeText(AllocationFair.this,
                            "Data not reterived...",
                            Toast.LENGTH_LONG).show();}
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