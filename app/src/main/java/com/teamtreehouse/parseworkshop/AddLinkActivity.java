package com.teamtreehouse.parseworkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.teamtreehouse.readme.R;

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



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_link);

        final ParseUser currentUser = ParseUser.getCurrentUser();

		mfirstname = (EditText) findViewById(R.id.editText1);
		mlastname = (EditText) findViewById(R.id.editText2);
		mcardno = (EditText) findViewById(R.id.editText3);
		mcardtype = (EditText) findViewById(R.id.editText4);
        mfamilymember = (EditText)findViewById(R.id.editText31);
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
                String fmember=mfamilymember.getText().toString();
                String mno = mmobileno.getText().toString();
				String addrs = maddress.getText().toString();
				int cardNoLength=cno.length();
				int mobileNoLength=mno.length();



				if (!fname.equals("") && !lname.equals("") && !cno.equals("") && !ctype.equals("") && !fmember.equals("") && !mno.equals("") && !addrs.equals("")) {
					/*
					 * Save Post ParseObject
					 */
					if(cardNoLength==6 && mobileNoLength==10){
						if(ctype.equals("ORANGE") || ctype.equals("YELLOW") || ctype.equals("WHITE")) {

							ParseObject post = new ParseObject(POSTS);
                            post.put(shopNo,currentUser);
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
						}else{
							Toast.makeText(AddLinkActivity.this,
									"Card Type Must be ORANGE/WHITE/YELLOW ...",
									Toast.LENGTH_LONG).show();
						}
				}else{
						Toast.makeText(AddLinkActivity.this,
								"Wrong card No OR Wrong phone No...",
								Toast.LENGTH_LONG).show();
					}
				}
                else{
                    Toast.makeText(AddLinkActivity.this,
                            "All fields are Mandatory...",
                            Toast.LENGTH_LONG).show();
                }
			}
		});
	}
}
