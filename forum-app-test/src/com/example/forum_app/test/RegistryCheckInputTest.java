package com.example.forum_app.test;
 

import com.example.forum_app.*;
//import org.junit.Test;
import junit.framework.Assert;
import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistryCheckInputTest extends ActivityInstrumentationTestCase2<RegisterActivity>{

	private RegisterActivity ractivity;
	private Button btRegister;
	private EditText etNickname;
	private EditText etPassword;
	private EditText etPasswordConfirm;
	private EditText etCountry;
	private EditText etEmail;
	private Spinner spGender;
	private TextView tvRegisterError; 
	
	Resources res;

	/*public RegistryCheckInputTest(Class<RegisterActivity> activityClass)
	{
		super(activityClass);
		this.activity = null;
	}*/
	@SuppressWarnings("deprecation")
	public RegistryCheckInputTest() {
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
		this.etCountry = (EditText) this.ractivity.findViewById(com.example.forum_app.R.id.etCountry);
		this.etEmail = (EditText) this.ractivity.findViewById(com.example.forum_app.R.id.etEmail);
		this.spGender = (Spinner) this.ractivity.findViewById(com.example.forum_app.R.id.spGender); 
		this.res = ractivity.getResources();
	} // end of setUp() method definition
	
	@UiThreadTest
	public void testNicknameMandatory() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst123");
				etCountry.setText("Austria");
				spGender.setSelection(0);
				etEmail.setText("Host@Horsti.at");
				
				btRegister.performClick();
				Resources res = ractivity.getResources();
				//System.out.println("debug___" + tvRegisterError.getText().toString());
				//System.out.println("debug___" + res.getString(com.example.forum_app.R.string.err_missing_nickname));
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_missing_nickname));
				
				etNickname.setText("Horst");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
	}
	
	@UiThreadTest
	public void testPasswordMandatory() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("Horst");
				etPassword.setText("");
				etPasswordConfirm.setText("Horst123");
				etCountry.setText("Austria");
				spGender.setSelection(0);
				etEmail.setText("Host@Horsti.at");
				btRegister.performClick();
				
				System.out.println("debug___" + tvRegisterError.getText().toString());
				System.out.println("debug___" + res.getString(com.example.forum_app.R.string.err_missing_password));
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_missing_password));
				
				etPassword.setText("Horst123");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
	}

	@UiThreadTest
	public void testPasswordConfirmMandatory() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("Horst");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("");
				etCountry.setText("Austria");
				spGender.setSelection(0);
				etEmail.setText("Host@Horsti.at");
				
				btRegister.performClick();
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_missing_passwordconfirm));
				
				etPasswordConfirm.setText("Horst123");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
	}
	
	@UiThreadTest
	public void testPasswordAndPasswordConfirmMandatory() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("Horst");
				etPassword.setText("");
				etPasswordConfirm.setText("");
				etCountry.setText("Austria");
				spGender.setSelection(0);
				etEmail.setText("Host@Horsti.at");
				
				btRegister.performClick();
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_missing_password));
				
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst123");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
	}
	
	@UiThreadTest
	public void testPasswordLength() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("Horst");
				etPassword.setText("Pass");
				etPasswordConfirm.setText("Pass");
				etCountry.setText("Austria");
				spGender.setSelection(0);
				etEmail.setText("Host@Horsti.at");
				btRegister.performClick();
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_too_short_passwords));
				
				etPassword.setText("Pass");
				etPasswordConfirm.setText("Pass123");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_too_short_passwords));
				
				etPassword.setText("Pass123");
				etPasswordConfirm.setText("Pass");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_too_short_passwords));
				
				
				etPassword.setText("Pass123");
				etPasswordConfirm.setText("Pass123");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
	}
	
	@UiThreadTest
	public void testPasswordEqualPasswordConfirm() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("Horst");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst321");
				etCountry.setText("Austria");
				spGender.setSelection(0);
				etEmail.setText("Host@Horsti.at");
				
				btRegister.performClick();
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_unequal_passwords));
				
				etPasswordConfirm.setText("Horst123");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
	}
	
	@UiThreadTest
	public void testCountryMandatory() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("Horst");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst123");
				etCountry.setText("");
				spGender.setSelection(0);
				etEmail.setText("Host@Horsti.at");
				
				btRegister.performClick();
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_missing_country));
				
				etCountry.setText("Austria");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
		
	}
	
	@UiThreadTest
	public void testEmailMandatory() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("Horst");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst123");
				etCountry.setText("Austria");
				spGender.setSelection(0);
				etEmail.setText("");
				
				btRegister.performClick();
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_missing_email));
				
				etEmail.setText("Host@Horsti.at");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
	}
	
	@UiThreadTest
	public void testEmailValid() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				etNickname.setText("Horst");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst123");
				etCountry.setText("Austria");
				spGender.setSelection(0);
				etEmail.setText("test");
				btRegister.performClick();
				String error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_invalid_email));
				
				etEmail.setText("test@test@com");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_invalid_email));
				
				etEmail.setText("test@test");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_invalid_email));
				
				etEmail.setText("test.com");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, res.getString(com.example.forum_app.R.string.err_invalid_email));
				
				etEmail.setText("Host@Horsti.at");
				btRegister.performClick();
				error_message =  tvRegisterError.getText().toString();
				Assert.assertEquals(error_message, "");
		    }
		});
	}
	
}
