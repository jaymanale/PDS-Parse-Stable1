package com.PDS.stackbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.PDS.readme.R;
import com.parse.ParseObject;


public class Retrieve_data extends AppCompatActivity {


    TextView getfName, getlName, lastVisit, getCardno, getCardtype, getMobile, getAddress, getFamilyMember;
    Button getAllocation;
    String reccType, recFmember, recMobileno, time, reccName;
    ParseObject pds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retrieve_data);
        init();

        Allocation();


    }


    public void Allocation() {
        getAllocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allocation = new Intent(Retrieve_data.this, AllocationFair.class);
                allocation.putExtra("ctype", reccType);
                allocation.putExtra("fmember", recFmember);
                allocation.putExtra("fNumber", recMobileno);
                allocation.putExtra("cardNumber", reccName);
                startActivity(allocation);

            }
        });
    }


    public void init() {
        getfName = (TextView) findViewById(R.id.textView2);
        getlName = (TextView) findViewById(R.id.textView4);
        getCardno = (TextView) findViewById(R.id.textView6);
        getCardtype = (TextView) findViewById(R.id.textView8);
        getFamilyMember = (TextView) findViewById(R.id.fmemberview);
        getMobile = (TextView) findViewById(R.id.textView10);
        getAddress = (TextView) findViewById(R.id.textView12);
        lastVisit = (TextView) findViewById(R.id.lastVisitDate);
        getAllocation = (Button) findViewById(R.id.button2);


        Intent submitbtn = getIntent();

        String recfName = submitbtn.getStringExtra("fName");
        String reclName = submitbtn.getStringExtra("lName");
        reccName = submitbtn.getStringExtra("cardNumber");
        reccType = submitbtn.getStringExtra("cardType");
        recFmember = submitbtn.getStringExtra("fmember");
        recMobileno = submitbtn.getStringExtra("mobileNo");
        String recAddress = submitbtn.getStringExtra("address");
        String visitFlag = submitbtn.getStringExtra("time");


        getfName.setText(recfName);
        getlName.setText(reclName);
        getCardno.setText(reccName);
        getCardtype.setText(reccType);
        getFamilyMember.setText(recFmember);
        getMobile.setText(recMobileno);
        getAddress.setText(recAddress);
        lastVisit.setText(visitFlag);


    }


}

