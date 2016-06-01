package com.PDS.stackbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.PDS.readme.R;
import com.parse.ParseObject;

public class SelectUsersActivity extends AppCompatActivity {

    public static final String TAG = SelectUsersActivity.class.getSimpleName();

    protected ParseObject[] mUsers;
    protected ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_users);


    }

}
