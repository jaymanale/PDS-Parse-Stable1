package com.PDS.stackbase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.PDS.readme.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainFeedActivity extends AppCompatActivity {

    //	public static final String TAG = MainFeedActivity.class.getSimpleName();
//
    protected ProgressBar mProgressBar;
    Button submit, verify;

    //    static MainFeedActivity INSTANCE;
    String data, recCardtype, recFmember, recFname, recLname, recCardno, recMobileno, recAddress;
    String Message, randString, getVerifiyString, objedtid, time;
    EditText usercardno, editVerification;
    Date createdDate;
    int cardNo, rand, min = 1000, max = 9999, CheckCode;
    boolean doubleBackToExitPressedOnce = false;
    ParseUser currentUser;
    Button Cancel;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);


        currentUser = ParseUser.getCurrentUser();
        objedtid = currentUser.getObjectId();

        usercardno = (EditText) findViewById(R.id.editText);
        editVerification = (EditText) findViewById(R.id.editText7);
        submit = (Button) findViewById(R.id.button);
        Cancel = (Button) findViewById(R.id.cancel);
        verify = (Button) findViewById(R.id.codeVerification);


        mainCode();

    }

    private void mainCode() {


        //hiding fields
        editVerification.setVisibility(View.INVISIBLE);
        verify.setVisibility(View.INVISIBLE);
        Cancel.setVisibility(View.INVISIBLE);

        usercardno.setVisibility(View.VISIBLE);
        submit.setVisibility(View.VISIBLE);
        usercardno.getText().clear();
        //random number generator


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String userCardNoTest = usercardno.getText().toString();
                int cardNoLength = userCardNoTest.length();
                if (!userCardNoTest.equals("") && cardNoLength == 6) {


                    usercardno.setCursorVisible(false);
                    mProgressBar.setVisibility(View.VISIBLE);

                    // getting random number
                    Random r = new Random();
                    rand = r.nextInt(max - min + 1) + min;
                    randString = Integer.toString(rand);
                    Message = "Your PDS verification code is " + " " + randString;

                    final ParseQuery query = new ParseQuery("Customer");
                    query.whereEqualTo("CardNo", usercardno.getText().toString());

                    query.findInBackground(new FindCallback() {
                        public void done(List<ParseObject> results, ParseException e) {
                            query.findInBackground(new FindCallback() {
                                @Override
                                public void done(List<ParseObject> list, ParseException e) {
                                    mProgressBar.setVisibility(View.INVISIBLE);
                                    if (e == null) {
                                        if (list.size() > 0) {

                                            //code for sms

                                            recFname = list.get(0).getString("First_Name");
                                            recLname = list.get(0).getString("Last_name");
                                            recCardno = list.get(0).getString("CardNo");
                                            recCardtype = list.get(0).getString("CardType");
                                            recFmember = list.get(0).getString("FamilyMember");
                                            recMobileno = list.get(0).getString("MobileNo");
                                            recAddress = list.get(0).getString("Address");

                                            sendSMS(recMobileno, Message);
                                            retrieveDate();

                                            codeVerify();

//                                    String name = pds.getString("First_Name");
                                            cancleButton();
//                                        check.putExtra("CardTypeCheck",recCardtype);
//                                        startActivity(check);
                                        } else {
                                            Toast.makeText(MainFeedActivity.this,
                                                    "Invalid Card Number",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(MainFeedActivity.this,
                                                "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                        }
                    });

                } else {
                    Toast.makeText(MainFeedActivity.this, "Please enter 6 digits Ration Card No", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    public void cancleButton() {
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainCode();
            }
        });


    }

    public void retrieveDate() {
        final ParseQuery querydate = new ParseQuery("AllocationDate");
        querydate.whereEqualTo("CardNo", usercardno.getText().toString());
        querydate.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    createdDate = list.get(0).getUpdatedAt();
                    time = createdDate.toString();

                }
            }
        });

    }

    public void codeVerify() {
        //hiding view
        usercardno.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);

//        showing view
        editVerification.setVisibility(View.VISIBLE);
        editVerification.getText().clear();
        verify.setVisibility(View.VISIBLE);
        Cancel.setVisibility(View.VISIBLE);

//        Toast.makeText(MainFeedActivity.this,"code:" + randString,Toast.LENGTH_LONG).show();

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editVerification.setCursorVisible(false);

                getVerifiyString = editVerification.getText().toString();


//                Toast.makeText(MainFeedActivity.this,getVerifiyString + "" + randString,Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.VISIBLE);

                if (getVerifiyString.equals(randString)) {

                    mProgressBar.setVisibility(View.INVISIBLE);

                    Intent submitbtn = new Intent(MainFeedActivity.this, Retrieve_data.class);

                    submitbtn.putExtra("fName", recFname);
                    submitbtn.putExtra("lName", recLname);
                    submitbtn.putExtra("cardNumber", recCardno);
                    submitbtn.putExtra("cardType", recCardtype);
                    submitbtn.putExtra("fmember", recFmember);
                    submitbtn.putExtra("mobileNo", recMobileno);
                    submitbtn.putExtra("address", recAddress);
                    submitbtn.putExtra("time", time);

                    startActivity(submitbtn);

                } else {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainFeedActivity.this,
                            "Invalid Verification Code, Try Again.",
                            Toast.LENGTH_LONG).show();


                }


            }
        });


    }

    public void sendSMS(String phoneNumber, String message) {

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getApplicationContext(), "Verification Code Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                doubleBackToExitPressedOnce = false;
                System.exit(1);

            }
        }, 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addButton:
                startActivity(new Intent(this, AddLinkActivity.class));
                return true;
            case R.id.refreshButton:
                startActivity(new Intent(this, Dashboard.class));
                return true;


            case R.id.followButton:
                startActivity(new Intent(this, SelectUsersActivity.class));
                return true;
            case R.id.logoutButton:
            /*
			 * Log current user out using ParseUser.logOut()
			 */
                ParseUser.logOut();
                Intent intent = new Intent(this, AuthenticateActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}