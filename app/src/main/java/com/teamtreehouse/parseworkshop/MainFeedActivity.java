package com.teamtreehouse.parseworkshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.teamtreehouse.readme.R;

import java.util.List;

public class MainFeedActivity extends Activity {

//	public static final String TAG = MainFeedActivity.class.getSimpleName();
//
//	protected ProgressBar mProgressBar;
    Button submit;
    EditText usercardno;
    int cardNo;

	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        init();




//		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
	}

    private void init()
{

        usercardno=(EditText)findViewById(R.id.editText);
        submit=(Button)findViewById(R.id.button);


        final ParseObject pds=new ParseObject("Customer");


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                final ParseQuery query = new ParseQuery("Customer");
                query.whereEqualTo("CardNo", usercardno.getText().toString());
                query.findInBackground(new FindCallback() {
                    public void done(List<ParseObject> results, ParseException e) {
                        query.findInBackground(new FindCallback() {
                            @Override
                            public void done(List<ParseObject> list, ParseException e) {
                                if (e == null) {
                                    if(list.size()>0) {

                                        String recFname = list.get(0).getString("First_Name");
                                        String recLname = list.get(0).getString("Last_name");
                                        String recCardno = list.get(0).getString("CardNo");
                                        String recCardtype=list.get(0).getString("CardType");
                                        String recMobileno=list.get(0).getString("MobileNo");
                                        String recAddress=list.get(0).getString("Address");


//                                    String name = pds.getString("First_Name");
                                        Intent submitbtn = new Intent(MainFeedActivity.this, Retrieve_data.class);
                                        submitbtn.putExtra("fName", recFname);
                                        submitbtn.putExtra("lName", recLname);
                                        submitbtn.putExtra("cardNumber",recCardno);
                                        submitbtn.putExtra("cardType",recCardtype);
                                        submitbtn.putExtra("mobileNo",recMobileno);
                                        submitbtn.putExtra("address",recAddress);

                                        startActivity(submitbtn);
                                    }else{
                                        Toast.makeText(MainFeedActivity.this,
                                                "Invalid Card Number",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                                else
                                {
                                    Toast.makeText(MainFeedActivity.this,
                                            "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });





                    }
                });

            }
        });
}

            //	@Override
//	public void onResume() {
//		super.onResume();
//		getLatestPosts();
//	}
//
//	protected void getLatestPosts() {
//		mProgressBar.setVisibility(View.VISIBLE);
//
//		/*
//		 * Use ParseQuery to get latest posts
//		 */
//		ParseQuery query = new ParseQuery(AddLinkActivity.POSTS);
//		query.setLimit(100);
//		query.orderByDescending("createAt");
//		query.findInBackground(new FindCallback() {
//			public void done(List<ParseObject> results, ParseException e) {
//				mProgressBar.setVisibility(View.INVISIBLE);
//
//				if (e == null) {
//					ArrayList<HashMap<String, String>> articles = new ArrayList<HashMap<String, String>>();
//					for (ParseObject result : results) {
//						HashMap<String, String> article = new HashMap<String, String>();
//						article.put(AddLinkActivity.firstName,
//								result.getString(AddLinkActivity.firstName));
//						article.put(AddLinkActivity.lastName,
//								result.getString(AddLinkActivity.lastName));
//						articles.add(article);
//					}
//					SimpleAdapter adapter = new SimpleAdapter(
//							MainFeedActivity.this, articles,
//							android.R.layout.simple_list_item_2, new String[] {
//									AddLinkActivity.firstName,
//									AddLinkActivity.lastName }, new int[] {
//									android.R.id.text1, android.R.id.text2 });
//					setListAdapter(adapter);
//				} else {
//					Log.e(TAG, "Exception caught!", e);
//				}
//			}
//		});
//	}
//
//	@Override
//	protected void onListItemClick(ListView l, View v, int position, long id) {
//		TextView urlLabel = (TextView) v.findViewById(android.R.id.text2);
//		Intent intent = new Intent(Intent.ACTION_VIEW);
//		intent.setData(Uri.parse(urlLabel.getText().toString()));
//		startActivity(intent);
//	}
//
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