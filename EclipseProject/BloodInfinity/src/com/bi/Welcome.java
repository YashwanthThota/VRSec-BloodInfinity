//<!-- copyrighted content owned by Android Arena (www.androidarena.co.in)-->
package com.bi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends Activity implements OnClickListener{
	/** Private members of the class */
	private TextView pDisplayDate;
	private Button pPickDate;
	private Button ds;
	private Button feedbut;

	private int pYear;
	private int pMonth;
	private int pDay;
		
	
	/**
	 * This integer will uniquely define the dialog to be used for displaying
	 * date picker.
	 */
	static final int DATE_DIALOG_ID = 0;

	/** Callback received when the user "picks" a date in the dialog */
	private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			pYear = year;
			pMonth = monthOfYear;
			pDay = dayOfMonth;
			updateDisplay();
			displayToast();
		}
	};

	/** Updates the date in the TextView */
	private void updateDisplay() {
		pDisplayDate.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(pMonth + 1).append("-").append(pDay).append("-")
				.append(pYear));
	}

	/** Displays a notification when the date is updated */
	private void displayToast() {
		Log.d("Flag0:", ""+pDisplayDate.getText());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcome);
		
		TextView details = (TextView) findViewById(R.id.details);
		
		Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        if(msg!=null){
        	if(msg.trim().length()>0){
        		details.setText(msg);
        	}
        }

		/** Capture our View elements */
		pDisplayDate = (TextView) findViewById(R.id.displayDate);
		pPickDate = (Button) findViewById(R.id.pickDate);
		ds = (Button) findViewById(R.id.ds);
		ds.setOnClickListener(this);
		
		feedbut = (Button) findViewById(R.id.feedbut);
        feedbut.setOnClickListener(this);

		/** Listener for click event of the button */
		pPickDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		/** Get the current date */
		final Calendar cal = Calendar.getInstance();
		pYear = cal.get(Calendar.YEAR);
		pMonth = cal.get(Calendar.MONTH);
		pDay = cal.get(Calendar.DAY_OF_MONTH);

		/** Display the current date in the TextView */
		//updateDisplay();

	}

	/** Create a new dialog for date picker */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, pDateSetListener, pYear, pMonth,
					pDay);
		}
		return null;
	}

	@Override
	public void onClick(View v) {
		Log.d("Flag1:", ""+pDisplayDate.getText());		
		if(v==ds){			
			updateUserBlood(""+pDisplayDate.getText());
		}
		if(v==feedbut){			
			Intent intent = new Intent(Welcome.this, FeedBack.class);	
			startActivity(intent);
		}
		
	}
	
	public void updateUserBlood(String date) {
		Log.d("Flag2:", ""+pDisplayDate.getText());
		ConnectToServer task = new ConnectToServer();

		ClientSettings.httpServerUrl = "http://" + ClientSettings.serverIp
				+ ":" + ClientSettings.serverPort + "/"
				+ ClientSettings.project + "/";

		String params = "?un="+ClientSettings.un+"&date="+date;
				
		task.execute(new String[] { ClientSettings.httpServerUrl
				+ "mobDonationUpdate.jsp"+params });
	}
	
	private class ConnectToServer extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {

			InputStream is = null;
			try {
				Log.d("Flag3:", urls[0]);
				
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(urls[0]);

				// to send parameters to servlet

				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();

				is = entity.getContent();

			} catch (Exception e) {
				Log.d("Flag3: Ex1", e.toString());
				return "Connection Exception : " + e.toString();
			}
			// convert response to string
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();

				return sb.toString();
			} catch (Exception e) {
				Log.d("Flag3: Ex2", e.toString());
				return "Conversion Exception : " + e.toString();
			}

		}

		@Override
		protected void onPostExecute(String result) {
			String parts[] = result.split("[$$$]+");
			if (parts[0].trim().equals("YES")) {				
					Intent intent = new Intent(Welcome.this, Login.class);
					intent.putExtra("msg", parts[1].trim());
					startActivity(intent);								
			} else {
				Toast.makeText(Welcome.this, parts[1], Toast.LENGTH_SHORT).show();
			}
		}
	}


}
