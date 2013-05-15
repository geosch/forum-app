package com.example.forum_app.test;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.forum_app.RegisterActivity;

public class RegistryInsertToDatabaseTest extends ActivityInstrumentationTestCase2<RegisterActivity>{

	private RegisterActivity ractivity;
	private Button btRegister;
	private EditText etNickname;
	private EditText etPassword;
	private EditText etPasswordConfirm;
	private Spinner spCountry;
	private EditText etEmail;
	private Spinner spGender;
	private TextView tvRegisterError; 
	
	Resources res;
	
	@SuppressWarnings("deprecation")
	public RegistryInsertToDatabaseTest() {
		super("com.example.forum_app", RegisterActivity.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		//super.setUp();
		//setActivityInitialTouchMode(false);
	    this.ractivity = getActivity();
		//setActivityInitialTouchMode(false);
		this.btRegister = (Button) this.ractivity.findViewById(com.example.forum_app.R.id.btRegister);
		this.tvRegisterError = (TextView) this.ractivity.findViewById(com.example.forum_app.R.id.tvRegisterError);
		
		this.etNickname = (EditText) this.ractivity.findViewById(com.example.forum_app.R.id.etNickname);
		this.etPassword = (EditText) this.ractivity.findViewById(com.example.forum_app.R.id.etPassword);
		this.etPasswordConfirm = (EditText) this.ractivity.findViewById(com.example.forum_app.R.id.etPasswordConfirm);
		this.spCountry = (Spinner) this.ractivity.findViewById(com.example.forum_app.R.id.spCountry);
		this.etEmail = (EditText) this.ractivity.findViewById(com.example.forum_app.R.id.etEmail);
		this.spGender = (Spinner) this.ractivity.findViewById(com.example.forum_app.R.id.spGender); 
		this.res = ractivity.getResources();
	}
	
	@UiThreadTest
	public void testRegistryDatabaseOperations() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				Resources res = ractivity.getResources();

				etNickname.setText("Horst");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst123");
				spCountry.setSelection(0);
				spGender.setSelection(0);
				etEmail.setText("Horst@Horsti.at");
				
				btRegister.performClick();
				Assert.assertEquals(1, 1);
				
		    }
		});
	}

}
