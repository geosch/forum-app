package com.example.forum_app;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class RegisterActivity extends Activity {
	
	//private Button btRegister;
	private EditText etNickname;
	private EditText etPassword;
	private EditText etPasswordConfirm;
	private Spinner spCountry;
	private EditText etEmail;
	private Spinner spGender;
	private TextView tvRegisterError; 
	private int userid;
	private Pattern regexp_pattern;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		this.setTitle(R.string.register);
		
		//this.btRegister = (Button) this.findViewById(com.example.forum_app.R.id.btRegister);
		this.tvRegisterError = (TextView) this.findViewById(com.example.forum_app.R.id.tvRegisterError);
		
		this.etNickname = (EditText) this.findViewById(com.example.forum_app.R.id.etNickname);
		this.etPassword = (EditText) this.findViewById(com.example.forum_app.R.id.etPassword);
		this.etPasswordConfirm = (EditText) this.findViewById(com.example.forum_app.R.id.etPasswordConfirm);
		this.spCountry = (Spinner) this.findViewById(com.example.forum_app.R.id.spCountry);
		this.etEmail = (EditText) this.findViewById(com.example.forum_app.R.id.etEmail);
		this.spGender = (Spinner) this.findViewById(com.example.forum_app.R.id.spGender); 
		
		Locale[] locales = Locale.getAvailableLocales();
		String[] countries = new String[locales.length];
		Set<String> strings = new HashSet<String>();

		for (int i = 0; i < countries.length; i++)
		{
			String country = locales[i].getDisplayCountry(new Locale(this.getString(R.string.country_language),this.getString( R.string.country_language_state))).trim();
			if (!country.isEmpty())
			{
				strings.add(country);
			}
		}
		
		countries = strings.toArray(new String[0]);
		java.util.Arrays.sort(countries);
		
		ArrayAdapter<String> country_adapter = new ArrayAdapter<String>(this,
		            android.R.layout.simple_spinner_item, countries);
		spCountry.setAdapter(country_adapter);
		
		this.regexp_pattern = Patterns.EMAIL_ADDRESS;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void clickRegister(View view) {
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(etNickname != null)
            imm.hideSoftInputFromWindow(etNickname.getWindowToken(), 0);  
        
		this.tvRegisterError.setText("");
		if(this.etNickname.getText().toString().trim().isEmpty())
		{
			this.tvRegisterError.setText(R.string.err_missing_nickname);
		}
		else if(this.etPassword.getText().toString().isEmpty() || this.etPasswordConfirm.getText().toString().isEmpty())
		{
			this.tvRegisterError.setText(R.string.err_missing_password);
		}
		else if(this.etPassword.getText().toString().length() < 6 || this.etPasswordConfirm.getText().toString().length() < 6 )
		{
			this.tvRegisterError.setText(R.string.err_too_short_passwords);
		}
		else if(!this.etPassword.getText().toString().equals(this.etPasswordConfirm.getText().toString()))
		{
			this.tvRegisterError.setText(R.string.err_unequal_passwords);
		}
		else if(this.etEmail.getText().toString().trim().isEmpty())
		{
			this.tvRegisterError.setText(R.string.err_missing_email);
		}
		else if(!this.regexp_pattern.matcher(this.etEmail.getText().toString()).matches())
		{
			this.tvRegisterError.setText(R.string.err_invalid_email);
		}
		else
		{
			String insert_user = "INSERT INTO ForumUser(NickName, Password, Country, Gender, Email) " +
					             "VALUES (" + 
					                      "'" + this.etNickname.getText().toString().trim() + "', " + 
					                      "'" + this.etPassword.getText().toString().trim() + "', " +
					                      "'" + this.spCountry.getSelectedItem().toString().trim() + "', " +
					                      "'" + this.spGender.getSelectedItem().toString().trim() + "', " +
					                      "'" + this.etEmail.getText().toString().trim() + "') RETURNING userid;";
			DBOperator dboperator = DBOperator.getInstance();
			List<JSONObject> answer = dboperator.sendInsert(insert_user);
			try {
							
				if(answer != null)
				{
					if(answer.get(0).getInt("success") == 0)
					{
						if(answer.get(0).getInt("error_code") == -1)
						{
							Log.d("Deb: ", getString(R.string.err_duplicate_entry));
							this.tvRegisterError.setText(R.string.err_duplicate_entry);
						}
						else
						{
							Log.d("DBErr", answer.get(0).getString("message"));
						}
					}
					else if(answer.get(0).getInt("success") == 1)
					{
						Log.d("Deb: ", insert_user);
						Log.d("Deb: ", answer.toString());
						this.userid = answer.get(0).getInt("userid");
						AlertDialog.Builder builder = new AlertDialog.Builder(this);
						
						builder.setMessage(R.string.registration_complete_message)
						       .setTitle(R.string.registration_complete_title);
				
						builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
						           public void onClick(DialogInterface dialog, int id) {
						        	   User.createInstance(userid);
						        	   Intent returnIntent = new Intent();
						        	   setResult(RESULT_OK, returnIntent);     
						        	   finish();
						           }
						       });
						AlertDialog dialog = builder.create();
						dialog.show();
						
					}
					
				}
				else
				{
					Log.d("Deb: ", "Answer = null");
				}
			} catch(Exception e) {
				Log.d("Deb: ", e.getMessage());
				Log.d("Deb: ", answer.toString());
			}
			
			
			
		}
	}

}

