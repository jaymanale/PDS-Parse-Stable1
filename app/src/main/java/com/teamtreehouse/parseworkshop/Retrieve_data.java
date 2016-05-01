//package com.teamtreehouse.parseworkshop;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.teamtreehouse.readme.R;
//
//
//public class Retrieve_data extends Activity {
//
//    protected ProgressBar mProgressBar;
//
//    TextView getfName,getlName,getCardno,getCardtype,getFmember,getMobile,getAddress,checkCardType;
//    Button getAllocation;
//    int num=0;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.retrieve_data);
//        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
//
//        init();
//        Allocation();
//
//
//    }
//
//
//    private void init() {
//
//        String s1,s2;
//        int n1;
//        getfName=(TextView)findViewById(R.id.textView2);
//        getlName=(TextView)findViewById(R.id.textView4);
//        getCardno=(TextView)findViewById(R.id.textView6);
//        getCardtype=(TextView)findViewById(R.id.textView8);
//        getFmember=(TextView)findViewById(R.id.fmemberview);
//        getMobile=(TextView)findViewById(R.id.textView10);
//        getAddress=(TextView)findViewById(R.id.textView12);
//        getAllocation=(Button)findViewById(R.id.button2);
////converting string to integer
////            s1=getFmember.getText().toString();
////            n1=Integer.parseInt(s1);
//
//
//        Intent submitbtn=getIntent();
//
//        String recfName=submitbtn.getStringExtra("fName");
//        String reclName=submitbtn.getStringExtra("lName");
//        String reccName=submitbtn.getStringExtra("cardNumber");
//        String reccType=submitbtn.getStringExtra("cardType");
//        String recFmember=submitbtn.getStringExtra("fmember");
//        String recMobileno=submitbtn.getStringExtra("mobileNo");
//        String recAddress=submitbtn.getStringExtra("address");
//
//
//
//        getfName.setText(recfName);
//        getlName.setText(reclName);
//        getCardno.setText(reccName);
//        getCardtype.setText(reccType);
//        getFmember.setText(recFmember);
//        getMobile.setText(recMobileno);
//        getAddress.setText(recAddress);
//
////       String rectype=getCardtype.getText().toString();
//////        num = Integer.parseInt(getFmember.getText().toString());
////
////        Allocation(rectype, n1);
////
//
//
//
//
//    }
//
//
//
//   private void Allocation() {
//        getAllocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                checkCardType=(TextView)findViewById(R.id.fmemberview);
////                String card= (String) checkCardType.getText();
//                Intent allocate=new Intent(Retrieve_data.this,AllocationFair.class);
////                allocate.putExtra("cardTypecheck",card);
//
////                Intent allocation = new Intent(Retrieve_data.this, AllocationFair.class);
//////                allocation.putExtra("checkType",rectype);
//////                allocation.putExtra("fmember",num);
//////                Intent myintent=getIntent();
//////                String recFmembercheck=myintent.getStringExtra("CardTypeCheck");
////
//             startActivity(allocate);
////
//           }
//      });
//}
//
////
////    public void Allocation(View view) {
////        mProgressBar.setVisibility(View.VISIBLE);
////
////        checkCardType=(TextView)findViewById(R.id.fmemberview);
////        String card= (String) checkCardType.getText();
////        Intent allocate=new Intent(Retrieve_data.this,AllocationFair.class);
////        allocate.putExtra("cardTypecheck",card);
////        startActivity(allocate);
////        mProgressBar.setVisibility(View.INVISIBLE);
////
////
////    }
//
////    private void Allocation() {
////
////        getAllocation.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////
////                final ParseQuery query = new ParseQuery("Allocation");
////                query.whereEqualTo("objectId","dy3keagcCC" );
////                query.findInBackground(new FindCallback() {
////                    @Override
////                    public void done(List<ParseObject> list, ParseException e) {
////                        if (e == null) {
////                            String recWheat = list.get(0).getString("Wheat");
////
////                            Intent allocation = new Intent(Retrieve_data.this, AllocationFair.class);
////
////                            allocation.putExtra("wheat", recWheat);
////
////
////                            startActivity(allocation);
////                        } else {
////                            Toast.makeText(Retrieve_data.this,
////                                    "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
////
////                        }
////                    }
////                });
////
////
////
////
////
////
////            }
////        });
////
////    }
//
//
//
//
//}
package com.teamtreehouse.parseworkshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.teamtreehouse.readme.R;


public class Retrieve_data extends Activity {


    TextView getfName, getlName, getCardno, getCardtype, getMobile, getAddress,getFamilyMember;
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
        getfName = (TextView) findViewById(R.id.textView2);
        getlName = (TextView) findViewById(R.id.textView4);
        getCardno = (TextView) findViewById(R.id.textView6);
        getCardtype = (TextView) findViewById(R.id.textView8);
        getFamilyMember=(TextView)findViewById(R.id.fmemberview);
        getMobile = (TextView) findViewById(R.id.textView10);
        getAddress = (TextView) findViewById(R.id.textView12);
        getAllocation = (Button) findViewById(R.id.button2);


        Intent submitbtn = getIntent();

        String recfName = submitbtn.getStringExtra("fName");
        String reclName = submitbtn.getStringExtra("lName");
        String reccName = submitbtn.getStringExtra("cardNumber");
        String reccType = submitbtn.getStringExtra("cardType");
        String recFmember=submitbtn.getStringExtra("fmember");
        String recMobileno = submitbtn.getStringExtra("mobileNo");
        String recAddress = submitbtn.getStringExtra("address");


        getfName.setText(recfName);
        getlName.setText(reclName);
        getCardno.setText(reccName);
        getCardtype.setText(reccType);
        getFamilyMember.setText(recFmember);
        getMobile.setText(recMobileno);
        getAddress.setText(recAddress);


    }
}
