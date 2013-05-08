package TVPC.poker;



import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import control.ControlPokerMobile;

import DAO.DAOEngine;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.res.Resources;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends Activity implements SeekBar.OnSeekBarChangeListener {
	
	
	public static String SERVERIP = "";
	private boolean serverRunning = false;
	
	// designate a port
	public static final int SERVERPORT = 8080;
	private Handler handler = new Handler();
	private ServerSocket serverSocket;
	private DataInputStream input;
	private static String request = "";

	
	private SeekBar mSeekBar;
	private TextView mProgressText;
	private TextView mTrackingText;
	
	
	private Button btnCheck;
	private Button btnFold;
	private Button btnRaise;
	private Button btnCall;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		init();
	}

	private void init() {
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
		mProgressText = (TextView) findViewById(R.id.textView4);
		mTrackingText = (TextView) findViewById(R.id.textView7);
		
		Button eventCheck = (Button) findViewById(R.id.btnCheck);
		eventCheck.setOnClickListener(buttonPressCheck);
		Button eventCall = (Button) findViewById(R.id.btnCall);
		eventCall.setOnClickListener(buttonPressCall);
		Button eventRaise = (Button) findViewById(R.id.btnRaise);
		eventRaise.setOnClickListener(buttonPressRaise);
		Button eventFold = (Button) findViewById(R.id.btnFold);
		eventFold.setOnClickListener(buttonPressFold);
		
		btnCheck = (Button) findViewById(R.id.btnCheck);
		btnFold = (Button) findViewById(R.id.btnFold);
		btnRaise = (Button) findViewById(R.id.btnRaise);
		btnCall = (Button) findViewById(R.id.btnCall);
		setEnableButtons(false, false, false, false);
		serverRunning = true;
		setAvatar();
		initServerThread();
	}
	

	private void setEnableButtons(boolean check, boolean fold, boolean raise, boolean call){
		btnCheck.setEnabled(check);
		btnFold.setEnabled(fold);
		btnRaise.setEnabled(raise);
		btnCall.setEnabled(call);
	}
	
	
	
	private void initServerThread() {
        try {
			SERVERIP = getLocalIpAddress(); //InetAddress.getByName(null).toString();
		} catch (Exception e) { }
        Thread t = new Thread(new ServerThread());
        t.start();
	}



	/** Class Server **/
	public class ServerThread implements Runnable {

		public void run() {
			try {
				if (SERVERIP != null) {
					handler.post(new Runnable() {
						@Override
						public void run() {
							//alert("Listening on IP: " + SERVERIP);
						}
					});
					serverSocket = new ServerSocket(SERVERPORT);
					while (serverRunning) {
						// listen for incoming clients
						Socket client = serverSocket.accept();
						handler.post(new Runnable() {
							@Override
							public void run() {
								//alert("Connected.");
							}
						});

						try {
							input = new DataInputStream(client.getInputStream());
							request = input.readUTF();
							
							handler.post(new Runnable() {
								@Override
								public void run() {
									checkRequest(request);
								}
							});
						} catch (Exception e) {
							handler.post(new Runnable() {
								@Override
								public void run() {
									alert("Connection interrupted. Please reconnect your phones.");
								}
							});
							e.printStackTrace();
						}
					}
				} else {
					handler.post(new Runnable() {
						@Override
						public void run() {
							alert("Couldn't detect internet connection.");
						}
					});
				}
			} catch (Exception e) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						alert("Error");
					}
				});
				e.printStackTrace();
			}
		}
	}
	
	
	//request
	private void checkRequest(String request) {
		try{
			String [] datas = request.split("&");
			
			if(datas[0].equals("register")){
				if(datas[1].equals("ok")){
					//setEnableButtons(true, true, true, true);
				}
			}else if(datas[0].equals("play")){
				paintCards(datas[1], datas[2]);
			}else if(datas[0].equals("isTurn")){
				setEnableButtons(true, true, true, true);
			}
		}catch (Exception e) {
			alert("Err : " + e);
		}
	}
	
	/** events **/ 
	private OnClickListener buttonPressCheck = new OnClickListener() {
		public void onClick(View v) {
			alert("Check");
			ControlPokerMobile.getInstance().getComunicationControl().getProtocolClient().sendPlay("check", "-");
		}
	};
	
	private OnClickListener buttonPressCall = new OnClickListener() {
		public void onClick(View v) {
			alert("Call");
			ControlPokerMobile.getInstance().getComunicationControl().getProtocolClient().sendPlay("call",  "-");
		}
	};
	
	private OnClickListener buttonPressRaise = new OnClickListener() {
		public void onClick(View v) {
			alert("Raise");
			String chips =  (String) mProgressText.getText();
			alert(chips);
			ControlPokerMobile.getInstance().getComunicationControl().getProtocolClient().sendPlay("raise", chips);
		}
	};
	
	private OnClickListener buttonPressFold = new OnClickListener() {
		public void onClick(View v) {
			alert("Fold");
			ControlPokerMobile.getInstance().getComunicationControl().getProtocolClient().sendPlay("fold", "-");
		}
	};
	
	

	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
		mProgressText.setText(progress + " " + getString(R.string.valorChips));
	}

	public void onStartTrackingTouch(SeekBar seekBar) {
		mTrackingText.setText(getString(R.string.seekbar_tracking_on));
	}

	public void onStopTrackingTouch(SeekBar seekBar) {
		mTrackingText.setText(getString(R.string.seekbar_tracking_off));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_play, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.logOut:
			Intent intent = new Intent();
			intent.setClass(Play.this, main.class);
			startActivity(intent);
			return true;
		case R.id.help:
			Context context = getApplicationContext();
			CharSequence text = "Use the buttons for: Call => macth the pot \n Fold => Retire of Table Poker \n " +
					"Raise => Increase the bet \n Check => Pass the hand  ";
			int duration = Toast.LENGTH_LONG;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
			toast.show();
			return true;
		case R.id.lobby:
			Intent intent1 = new Intent();
			intent1.setClass(Play.this, Lobby.class);
			startActivity(intent1);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	public void paintCards(String card1, String card2) {
		ImageView imgCard1 = (ImageView)findViewById(R.id.imageView1);
		ImageView imgCard2 = (ImageView)findViewById(R.id.imageView2);
		
		Resources res = getResources();
		
		card1 = "card_" + card1;
		card2 = "card_" + card2;
		
		int idCard1 = getResources().getIdentifier(card1, "drawable", getPackageName());
		int idCard2 = getResources().getIdentifier(card2, "drawable", getPackageName());
		
		Drawable drawableCard1 = res.getDrawable(idCard1);
		Drawable drawableCard2 = res.getDrawable(idCard2);
		
		imgCard1.setImageDrawable(drawableCard1);
		imgCard2.setImageDrawable(drawableCard2);
	}

	private void setAvatar() {
		String nick = ControlPokerMobile.getInstance().getPlayerControl().getPlayer().getNickName();
		try{
			String face = DAOEngine.getAvatar(nick);
			if(face != null){
				ImageView imgFace = (ImageView)findViewById(R.id.imageView3);
				Resources res = getResources();
				int idFace = getResources().getIdentifier(face, "drawable", getPackageName());
				Drawable drawableFace = res.getDrawable(idFace);
				imgFace.setImageDrawable(drawableFace);
			}
		}catch (Exception e) { }
	}
	
	public void alert(String msg){
		Context context = getApplicationContext();
		CharSequence text = msg;
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
		toast.show();
	}
	
	
	// gets the IP address of your phone's network
    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress())
                        return inetAddress.getHostAddress().toString();
                }
            }
        } catch (SocketException ex) {}
        return null;
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	alert("me destroy :(");
    	try{
    		stopServer();
    		ControlPokerMobile.getInstance().getComunicationControl().getProtocolClient().logout();
    		serverSocket.close();
    	}catch (Exception e) {
			alert("Err " + e);
		}
    }
    
    
    private void stopServer() {
    	serverRunning = false;
	}

	@Override
    protected void onStop() {
    	super.onStop();
    }
    
    
}