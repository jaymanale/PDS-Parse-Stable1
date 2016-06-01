package com.PDS.stackbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.PDS.readme.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class AllocationFair extends AppCompatActivity {
    TextView getWheat, getRice, getSugar, getWheatFair, getRiceFair, getSugarFair, costView;
    String fmember, cctype, wheatConvert, mobileNo, Message, cardNumberUpdate;
    int wheatTotal, riceTotal, sugarInt, wheatInt, mem, riceInt, wheatFairInt, riceFairInt, sugarFairInt, costToPay;
    Button getStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allocation_fair);

        final ParseObject gameScore = new ParseObject("Allocation");
        Intent submitbtn = getIntent();
        cctype = submitbtn.getStringExtra("ctype");
        fmember = submitbtn.getStringExtra("fmember");
        mobileNo = submitbtn.getStringExtra("fNumber");
        cardNumberUpdate = submitbtn.getStringExtra("cardNumber");

//        Toast.makeText(AllocationFair.this, "Card Type:" + mobileNo, Toast.LENGTH_SHORT).show();
        getWheat = (TextView) findViewById(R.id.wheatQuantity);
        getRice = (TextView) findViewById(R.id.riceQuantity);
        getSugar = (TextView) findViewById(R.id.sugarQuantity);
        getWheatFair = (TextView) findViewById(R.id.FairWheatQuantity);
        getRiceFair = (TextView) findViewById(R.id.FairRiceQuantity);
        getSugarFair = (TextView) findViewById(R.id.FairSugarQuantity);
        costView = (TextView) findViewById(R.id.totalCost1);
        getStatus = (Button) findViewById(R.id.checkStatus);


        switch (cctype) {
            case "ORANGE":
                orangeCard();
                break;
            case "WHITE":
                whiteCard();
                break;
            case "YELLOW":
                yellowCard();
                break;
        }


        getStatusButton();


//
//        Toast.makeText(AllocationFair.this, "Data from first activity is:" + cardTtpeCheck, Toast.LENGTH_SHORT).show();

    }


    public void getStatusButton() {
        getStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message = "Total Allocated Quantity- \nWheat: " + wheatTotal + " Kg" + ", Rice: " + riceTotal + " Kg" + ", Sugar " +
                        sugarInt + " Kg" + "\nFair /Kg-\nWheat: Rs. " + wheatFairInt + ", Rice: Rs. " + riceFairInt + ", Sugar: Rs. " +
                        sugarFairInt + "\n\n Total Fair: Rs. " + costToPay;
//                Toast.makeText(AllocationFair.this, "message" + Message, Toast.LENGTH_SHORT).show();

                sendSMS(mobileNo, Message);
                Intent allocation = new Intent(AllocationFair.this, AllocationStatus.class);
//                wheatConvert =Integer.toString(wheatTotal);

                allocation.putExtra("soldWheat", wheatTotal);
                allocation.putExtra("soldRice", riceTotal);
                allocation.putExtra("soldSugar", sugarInt);
                allocation.putExtra("cardNumbber", cardNumberUpdate);
                startActivity(allocation);

            }
        });

    }

    public void sendSMS(String phoneNumber, String message) {

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
        query.whereEqualTo("CardType", cctype);


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
                    getRice.setText(riceInt + " * " + mem + " = " + riceTotal + " Kg");
                    getSugar.setText(sugarInt + " Kg");

                    //getting total fair
                    int wheatFair = wheatFairInt * wheatTotal;
                    int riceFair = riceFairInt * riceTotal;
                    int sugarFair = sugarFairInt * sugarInt;

                    getWheatFair.setText(wheatFairInt + " * " + wheatTotal + " = " + "Rs. " + wheatFair);
                    getRiceFair.setText(riceFairInt + " * " + riceTotal + " = " + "Rs. " + riceFair);
                    getSugarFair.setText(sugarFairInt + " * " + sugarInt + " = " + "Rs. " + sugarFair);

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
        query.whereEqualTo("CardType", cctype);


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
        query.whereEqualTo("CardType", cctype);


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
                    costToPay = wheatFair + riceFair;
                    costView.setText("Rs. " + costToPay);


                } else {
                    Toast.makeText(AllocationFair.this,
                            "Data not reterived...",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}