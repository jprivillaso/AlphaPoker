package TVPC.poker;

import control.ControlPokerMobile;
import DAO.DAOEngine;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPin extends Activity {

	private String nickName = "";
	private String email = "";
	
	private EditText pin1;
	private EditText pin2;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerpin);
		init();
	}
	
	public void init(){
		Bundle datas = getIntent().getExtras();
		if(datas != null){
			nickName = datas.getString("nick");
			email = datas.getString("email");
		}
		
		Button eventRegisterPin = (Button) findViewById(R.id.enterPIN);
		eventRegisterPin.setOnClickListener(buttonPressRegisterPin);
		
		pin1 = (EditText) findViewById(R.id.etpin1);
		pin2 = (EditText) findViewById(R.id.etpin2);
		
	}
	
	/** events **/ 
	private OnClickListener buttonPressRegisterPin = new OnClickListener() {
		public void onClick(View v) {
			
			String strPin1 = pin1.getText().toString();
			String strPin2 = pin2.getText().toString();
				
			if(strPin1.equals(strPin2)){
					//OK
					int selectIdFace = random(1, 8);
					String face = "face_" + selectIdFace;
					if(DAOEngine.register(nickName, strPin1, face, email)){
						alert("Created Successfully");
					}else{
						alert("Error With NickName");
					}
					
				}else{
					alert("Pin values must be equals");
				}
		}
	};
	
	
	public int random(int max,int min){
		return (int)(Math.random()*(max-min))+min;		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logOut:
			Intent intent = new Intent();
			intent.setClass(RegisterPin.this, main.class);
			startActivity(intent);
			return true;
		case R.id.help:
			Context context = getApplicationContext();
			CharSequence text = "Insert your new Pin";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
			toast.show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void alert(String msg){
		Context context = getApplicationContext();
		CharSequence text = msg;
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
		toast.show();
	}
}