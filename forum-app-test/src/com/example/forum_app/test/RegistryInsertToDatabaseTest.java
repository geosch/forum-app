package com.example.forum_app.test;

import java.util.List;

import junit.framework.Assert;

import org.json.JSONObject;

import android.content.res.Resources;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.forum_app.DBOperator;
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
	public void testASucessfullRegistration() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				Resources res = ractivity.getResources();

				etNickname.setText("TestuserRegistration");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst123");
				spCountry.setSelection(0);
				spGender.setSelection(0);
				etEmail.setText("TestuserRegistration@TestuserRegistration.at");
				
				btRegister.performClick();
				Assert.assertEquals("", tvRegisterError.getText().toString());
				
		    }
		});
	}
	
	@UiThreadTest
	public void testBDuplicateNickname() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				Resources res = ractivity.getResources();

				etNickname.setText("TestuserRegistration");
				etPassword.setText("Horst1234");
				etPasswordConfirm.setText("Horst1234");
				spCountry.setSelection(0);
				spGender.setSelection(0);
				etEmail.setText("TestuserRegistration222@TestuserRegistration.at");
				
				btRegister.performClick();
				Assert.assertEquals(res.getString(com.example.forum_app.R.string.err_duplicate_entry), tvRegisterError.getText().toString());
				
		    }
		});
	}
	
	@UiThreadTest
	public void testCDuplicateEmail() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				Resources res = ractivity.getResources();

				etNickname.setText("TestuserRegistration222");
				etPassword.setText("Horst123");
				etPasswordConfirm.setText("Horst123");
				spCountry.setSelection(0);
				spGender.setSelection(0);
				etEmail.setText("TestuserRegistration@TestuserRegistration.at");
				
				btRegister.performClick();
				Assert.assertEquals("Nickname or Email already in use", tvRegisterError.getText().toString());
				
		    }
		});
	}
	
	@UiThreadTest
	public void testDReset() throws Throwable {
		this.ractivity.runOnUiThread(new Runnable() {
			public void run() {
				Resources res = ractivity.getResources();

				DBOperator dboperator = DBOperator.getInstance();
				List<JSONObject> answer = dboperator.sendDelete("DELETE FROM ForumUser WHERE NickName = 'TestuserRegistration';");
				Log.d("Deb: ", answer.toString());
				int success = 0;
				try {
					success = answer.get(0).getInt("success");
				}
				catch (Exception e)
				{
					Log.d("Deb: ", e.getMessage());
				}
				Assert.assertEquals(1, success);
				
		    }
		});
	}
}
