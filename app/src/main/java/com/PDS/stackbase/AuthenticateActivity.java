package com.PDS.stackbase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.PDS.readme.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class AuthenticateActivity extends AppCompatActivity {

    protected String mAction;

    protected EditText mEmailField;
    protected EditText mPasswordField;
    protected Button mButton;

    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);
        final Context context = this;

        mEmailField = (EditText) findViewById(R.id.editText1);

        mPasswordField = (EditText) findViewById(R.id.editText2);
        mButton = (Button) findViewById(R.id.button1);
//        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);

        if (ParseUser.getCurrentUser() != null) {
            Intent intent = new Intent(this, MainFeedActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
//		Bundle bundle = getIntent().getExtras();
//		mAction = bundle.getString(LoginOrSignupActivity.TYPE);
        else {
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mProgressBar.setVisibility(View.VISIBLE);
                    showProgressDialog(context, new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    String username = mEmailField.getText().toString();
                    String password = mPasswordField.getText().toString();


                    ParseUser.logInInBackground(username, password,
                            new LogInCallback() {
                                public void done(ParseUser user,
                                                 ParseException e) {
//                                    mProgressBar.setVisibility(View.INVISIBLE);
                                    if (user != null) {
                                        // Hooray! The user is logged in.
                                        startActivity(new Intent(
                                                AuthenticateActivity.this,
                                                MainFeedActivity.class));
                                    } else {
                                        // Login failed. Look at the
                                        // ParseException to see what happened.
                                        Toast.makeText(
                                                AuthenticateActivity.this,
                                                "Login failed! Invalid E-mail address OR Password.",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
            });
        }

    }

    private void showProgressDialog(final Context context, final Runnable runnable) {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(context, "Log In", "User Validation...", true);
        //you usually don't want the user to stop the current process, and this will make sure of that
        ringProgressDialog.setCancelable(false);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {

                runnable.run();
                ringProgressDialog.dismiss();
            }
        });
        th.start();
    }


}