package com.example.forum_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;

public class ForumView extends Activity implements OnClickListener {
	
	private Button buttonUsers;
	private Button buttonLupe;
	private Button buttonMore;
	private ExpandableListView new_posts_list;
	
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//		this.buttonUsers = (Button) this.findViewById(R.id.b_users);
//		this.buttonLupe = (Button) this.findViewById(R.id.b_lupe);
//		this.buttonMore = (Button) this.findViewById(R.id.b_more);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
	public void onClick(View v) {
//
//		Button clicked = (Button) v;
//
//		String nextCipherFromUser = (String) clicked.getText();
//
//		if (nextCipherFromUser.equals("Users")) {
//			Log.d("TAG", "Button1");
//			
//			this.button_pressed = "First Button";
//			this.editText1.setText(this.button_pressed);
//		}
//		else if (nextCipherFromUser.equals("Button2")) {
//			Log.d("TAG", "Button2");
//			
//			this.button_pressed = "Second Button";
//			this.editText1.setText(this.button_pressed);
//		}
//		else if (nextCipherFromUser.equals("Button3")) {
//			Log.d("TAG", "Button3");
//			
//			this.button_pressed = "Third Button";
//			this.editText1.setText(this.button_pressed);
		//}
    }

}
