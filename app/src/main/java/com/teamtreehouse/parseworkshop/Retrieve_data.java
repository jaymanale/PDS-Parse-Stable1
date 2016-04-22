package com.teamtreehouse.parseworkshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.teamtreehouse.readme.R;


public class Retrieve_data extends Activity {


    TextView getfName,getlName,getCardno,getCardtype,getMobile,getAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_data);
        init();
    }

    private void init() {
        getfName=(TextView)findViewById(R.id.textView2);
        getlName=(TextView)findViewById(R.id.textView4);
        getCardno=(TextView)findViewById(R.id.textView6);
        getCardtype=(TextView)findViewById(R.id.textView8);
        getMobile=(TextView)findViewById(R.id.textView10);
        getAddress=(TextView)findViewById(R.id.textView12);



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
}
