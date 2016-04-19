package com.teamtreehouse.parseworkshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;
import com.teamtreehouse.readme.R;

public class AddLinkActivity extends Activity {
	//defination od coloun in parse
	public static final String firstName = "First_Name";
	public static final String lastName = "Last_name";
	public static final String cardNo = "CardNo";
	public static final String cardType = "CardType";
	public static final String mobile = "MobileNo";
	public static final String personAddress = "Address";

	public static final String POSTS = "Customer";

//	protected EditText mUrlField;
//	protected EditText mNotesField;

	protected EditText mfirstname;
	protected EditText mlastname;
	protected EditText mcardno;
	protected EditText mcardtype;
	protected EditText mmobileno;
	protected EditText maddress;

	protected Button mSaveButton;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_link);

		mfirstname = (EditText) findViewById(R.id.editText1);
		mlastname = (EditText) findViewById(R.id.editText2);
		mcardno = (EditText) findViewById(R.id.editText3);
		mcardtype = (EditText) findViewById(R.id.editText4);
		mmobileno = (EditText) findViewById(R.id.editText5);
		maddress = (EditText) findViewById(R.id.editText6);
		mSaveButton = (Button) findViewById(R.id.button1);

		mSaveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String fname = mfirstname.getText().toString();
				String lname = mlastname.getText().toString();
				String cno = mcardno.getText().toString();
				String ctype = mcardtype.getText().toString();
				String mno = mmobileno.getText().toString();
				String addrs = maddress.getText().toString();

				if (!fname.equals("")) {
					/*
					 * Save Post ParseObject
					 */
					ParseObject post = new ParseObject(POSTS);
					post.put(firstName, fname);
					post.put(lastName, lname);
					post.put(cardNo, cno);
					post.put(cardType, ctype);
					post.put(mobile, mno);
					post.put(personAddress, addrs);
					post.saveInBackground();

					finish();
				}
			}
		});
	}
}
