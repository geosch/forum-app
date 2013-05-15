package com.example.forum_app;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Patterns;
import android.view.Menu;
import android.view.View;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
//import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity {
	
	//private Button btRegister;
	private EditText etNickname;
	private EditText etPassword;
	private EditText etPasswordConfirm;
	private EditText etCountry;
	private EditText etEmail;
	private Spinner spGender;
	private TextView tvRegisterError; 
	
	private Pattern regexp_pattern;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		//this.btRegister = (Button) this.findViewById(com.example.forum_app.R.id.btRegister);
		this.tvRegisterError = (TextView) this.findViewById(com.example.forum_app.R.id.tvRegisterError);
		
		this.etNickname = (EditText) this.findViewById(com.example.forum_app.R.id.etNickname);
		this.etPassword = (EditText) this.findViewById(com.example.forum_app.R.id.etPassword);
		this.etPasswordConfirm = (EditText) this.findViewById(com.example.forum_app.R.id.etPasswordConfirm);
		this.etCountry = (EditText) this.findViewById(com.example.forum_app.R.id.etCountry);
		this.etEmail = (EditText) this.findViewById(com.example.forum_app.R.id.etEmail);
		this.spGender = (Spinner) this.findViewById(com.example.forum_app.R.id.spGender); 
		
		this.regexp_pattern = Patterns.EMAIL_ADDRESS;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	public void clickRegister(View view) {
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
		else if(this.etCountry.getText().toString().trim().isEmpty())
		{
			this.tvRegisterError.setText(R.string.err_missing_country);
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
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
	
			builder.setMessage(R.string.registration_complete_message)
			       .setTitle(R.string.registration_complete_title);
	
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			        	   Intent switch_to_main = new Intent(RegisterActivity.this, MainActivity.class);
			        	   startActivity(switch_to_main);
			           }
			       });
			AlertDialog dialog = builder.create();
			dialog.show();
			
		}
	}

}

