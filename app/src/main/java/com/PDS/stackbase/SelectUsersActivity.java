package com.PDS.stackbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.parse.ParseObject;
import com.PDS.readme.R;

public class SelectUsersActivity extends AppCompatActivity {
	
	public static final String TAG = SelectUsersActivity.class.getSimpleName();
	
	protected ParseObject[] mUsers;	
	protected ProgressBar mProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_users);
		
//		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
		
//		getAllUsers();
	}
//
//	private void getAllUsers() {
//		mProgressBar.setVisibility(View.VISIBLE);
//
//    	/*
//    	 * Get ParseUsers using ParseUser.getQuery();
//    	 */
//		ParseQuery query = ParseUser.getQuery();
//		query.orderByDescending("createdAt");
//		query.findInBackground(new FindCallback() {
//			public void done(List<ParseObject> objects, ParseException e) {
//				mProgressBar.setVisibility(View.INVISIBLE);
//
//				if (e == null) {
//					objects = removeCurrentUser(objects);
//					mUsers = objects.toArray(new ParseObject[0]);
//
//					// Get user relations
//					ParseRelation userRelations = ParseUser.getCurrentUser().getRelation("UserRelation");
//					userRelations.getQuery().findInBackground(new FindCallback() {
//						public void done(List<ParseObject> results, ParseException e) {
//							if (e == null) {
//								UsersAdapter adapter = new UsersAdapter(SelectUsersActivity.this, mUsers, new ArrayList<ParseObject>(results));
//								setListAdapter(adapter);
//						    }
//							else {
//								Log.e(TAG, "Exception caught!", e);
//						    }
//						}
//					});
//				}
//				else {
//					// Something went wrong.
//					Toast.makeText(SelectUsersActivity.this, "Sorry, there was an error getting users!", Toast.LENGTH_LONG).show();
//				}
//			}
//		});
//	}
//
//	/*
//	 * Helper method to remove the logged-in user from the list of all users
//	 */
//	private List<ParseObject> removeCurrentUser(List<ParseObject> objects) {
//		ParseObject userToRemove = null;
//
//		for (ParseObject user : objects) {
//			if (user.getObjectId().equals(ParseUser.getCurrentUser().getObjectId())) {
//				userToRemove = user;
//			}
//		}
//
//		if (userToRemove != null) {
//			objects.remove(userToRemove);
//		}
//
//		return objects;
//	}
}
