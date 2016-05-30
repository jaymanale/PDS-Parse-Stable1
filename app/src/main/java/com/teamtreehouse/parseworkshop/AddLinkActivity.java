package com.teamtreehouse.parseworkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.teamtreehouse.readme.R;

import java.util.ArrayList;
import java.util.List;

public class AddLinkActivity extends AppCompatActivity {
	//defination od coloun in parse
    public static final String shopNo = "ShopNo";
	public static final String firstName = "First_Name";
	public static final String lastName = "Last_name";
	public static final String cardNo = "CardNo";
	public static final String cardType = "CardType";
	public static final String familyMember ="FamilyMember";
	public static final String mobile = "MobileNo";
	public static final String personAddress = "Address";

	public static final String POSTS = "Customer";

//	protected EditText mUrlField;
//	protected EditText mNotesField;

	protected EditText mfirstname;
	protected EditText mlastname;
	protected EditText mcardno;
	protected EditText mcardtype;
    protected EditText mfamilymember;
	protected EditText mmobileno;
	protected EditText maddress;

	protected Button mSaveButton;
	ParseUser currentUser;
	String cno,fname,lname,ctype,fmember,mno,addrs;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_link);

       currentUser = ParseUser.getCurrentUser();

		mfirstname = (EditText) findViewById(R.id.editText1);
		mlastname = (EditText) findViewById(R.id.editText2);
		mcardno = (EditText) findViewById(R.id.editText3);
		mcardtype = (EditText) findViewById(R.id.editText4);
        mfamilymember = (EditText)findViewById(R.id.editText31);
		mmobileno = (EditText) findViewById(R.id.editText5);
		maddress = (EditText) findViewById(R.id.editText6);
		mSaveButton = (Button) findViewById(R.id.button1);
addNewCustomer();
		
	}

	public void addNewCustomer() {
		mSaveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 fname = mfirstname.getText().toString();
				 lname = mlastname.getText().toString();
				cno = mcardno.getText().toString();
				 ctype = mcardtype.getText().toString();
				 fmember = mfamilymember.getText().toString();
				 mno = mmobileno.getText().toString();
				addrs = maddress.getText().toString();
				int cardNoLength = cno.length();
				int mobileNoLength = mno.length();


				if (!fname.equals("") && !lname.equals("") && !cno.equals("") && !ctype.equals("") && !fmember.equals("") && !mno.equals("") && !addrs.equals("")) {
					/*
					 * Save Post ParseObject
					 */
					if (cardNoLength == 6 && mobileNoLength == 10) {
						if (ctype.equals("ORANGE") || ctype.equals("YELLOW") || ctype.equals("WHITE")) {

							cNumberMnumberValidation();


						} else {
							Toast.makeText(AddLinkActivity.this,
									"Card Type Must be ORANGE/WHITE/YELLOW ...",
									Toast.LENGTH_LONG).show();
						}
					} else {
						Toast.makeText(AddLinkActivity.this,
								"Wrong card No OR Wrong phone No...",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(AddLinkActivity.this,
							"All fields are Mandatory...",
							Toast.LENGTH_LONG).show();
				}

			}

		});

	}
	public  void cNumberMnumberValidation(){
		final ParseQuery queryc = new ParseQuery("Customer");
		queryc.whereEqualTo("CardNo", cno);

		final ParseQuery querym = new ParseQuery("Customer");
		querym.whereEqualTo("MobileNo", mno);

		List<ParseQuery> queries = new ArrayList<>();
		queries.add(queryc);
		queries.add(querym);

		ParseQuery mainQuery = ParseQuery.or(queries);

		mainQuery.getFirstInBackground(new GetCallback() {
			@Override
			public void done(ParseObject parseObject, ParseException e) {
				if(e == null)
				{
					//object exists
					Toast.makeText(AddLinkActivity.this,
							"Ration Card No Or Mobile No Exist in Database...",
							Toast.LENGTH_LONG).show();
				}
				else
				{
					if(e.getCode() == ParseException.OBJECT_NOT_FOUND)
					{
						//object doesn't exist
						ParseObject post = new ParseObject(POSTS);
						post.put(shopNo, currentUser);
						post.put(firstName, fname);
						post.put(lastName, lname);
						post.put(cardNo, cno);
						post.put(cardType, ctype);
						post.put(familyMember, fmember);
						post.put(mobile, mno);
						post.put(personAddress, addrs);
						post.saveInBackground();

						finish();
						Toast.makeText(AddLinkActivity.this,
								"Successfully created Customer Record. ",
								Toast.LENGTH_LONG).show();
						lastUpdate();
					}
					else
					{
						//unknown error, debug
						Toast.makeText(AddLinkActivity.this,
								"Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

					}
				}
			}
		});


	}

	public void lastUpdate() {
		ParseObject updateDate = new ParseObject("AllocationDate");
		updateDate.put("ShopNumber", currentUser);
		updateDate.put("CardNo",cno);
		updateDate.saveInBackground();



	}
}
