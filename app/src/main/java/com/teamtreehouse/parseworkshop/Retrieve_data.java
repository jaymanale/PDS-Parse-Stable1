package com.teamtreehouse.parseworkshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.teamtreehouse.readme.R;


public class Retrieve_data extends Activity {


    TextView getfName,getlName,getCardno,getCardtype,getMobile,getAddress;
    Button getAllocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_data);
        init();
        Allocation();

    }

    private void Allocation() {
        getAllocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           Intent allocation = new Intent(Retrieve_data.this, AllocationFair.class);
                startActivity(allocation);

            }
        });
    }


    private void init() {
        getfName=(TextView)findViewById(R.id.textView2);
        getlName=(TextView)findViewById(R.id.textView4);
        getCardno=(TextView)findViewById(R.id.textView6);
        getCardtype=(TextView)findViewById(R.id.textView8);
        getMobile=(TextView)findViewById(R.id.textView10);
        getAddress=(TextView)findViewById(R.id.textView12);
        getAllocation=(Button)findViewById(R.id.button2);



        Intent submitbtn=getIntent();

        String recfName=submitbtn.getStringExtra("fName");
        String reclName=submitbtn.getStringExtra("lName");
        String reccName=submitbtn.getStringExtra("cardNumber");
        String reccType=submitbtn.getStringExtra("cardType");
        String recMobileno=submitbtn.getStringExtra("mobileNo");
        String recAddress=submitbtn.getStringExtra("address");



        getfName.setText(recfName);
        getlName.setText(reclName);
        getCardno.setText(reccName);
        getCardtype.setText(reccType);
        getMobile.setText(recMobileno);
        getAddress.setText(recAddress);




    }

//    private void Allocation() {
//
//        getAllocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final ParseQuery query = new ParseQuery("Allocation");
//                query.whereEqualTo("objectId","dy3keagcCC" );
//                query.findInBackground(new FindCallback() {
//                    @Override
//                    public void done(List<ParseObject> list, ParseException e) {
//                        if (e == null) {
//                            String recWheat = list.get(0).getString("Wheat");
//
//                            Intent allocation = new Intent(Retrieve_data.this, AllocationFair.class);
//
//                            allocation.putExtra("wheat", recWheat);
//
//
//                            startActivity(allocation);
//                        } else {
//                            Toast.makeText(Retrieve_data.this,
//                                    "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//
//
//
//
//
//
//            }
//        });
//
//    }




}
