package com.teamtreehouse.parseworkshop;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.parse.ParseUser;
import com.teamtreehouse.readme.R;

public class MainFeedActivity extends ListActivity {

	public static final String TAG = MainFeedActivity.class.getSimpleName();

	protected ProgressBar mProgressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
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
		case R.id.followButton:
			startActivity(new Intent(this, SelectUsersActivity.class));
			return true;
		case R.id.logoutButton:
			/*
			 * Log current user out using ParseUser.logOut()
			 */
			ParseUser.logOut();
			Intent intent = new Intent(this, LoginOrSignupActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}