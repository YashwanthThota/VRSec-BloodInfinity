//<!-- copyrighted content owned by Android Arena (www.androidarena.co.in)-->
package com.bi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBack extends Activity implements OnClickListener {
	
	
	private Button btnSubmit;
	
	
	EditText ta;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feeds);
		
		btnSubmit = (Button) findViewById(R.id.feedSubmit);		
		btnSubmit.setOnClickListener(this);		
		
		ta = (EditText) findViewById(R.id.ta);
		
		
	}

	    @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == btnSubmit) {
				sendToServer(v);
			}			
		}
	  
	  public void sendToServer(View view) {
			ClientSettings.httpServerUrl = "http://" + ClientSettings.serverIp + ":"
					+ ClientSettings.serverPort + "/"+ClientSettings.project+"/";
			
			String url = ClientSettings.httpServerUrl+"mobFeedBack.jsp?un="+ClientSettings.un+"&feeds="+ta.getText();
			
			ConnectToServer task = new ConnectToServer();
					
			task.execute(new String[] { url });
				
		}
	  
	  private class ConnectToServer extends AsyncTask<String, Void, String> {

			@Override
			protected String doInBackground(String... urls) {

				InputStream is = null;
				try {
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(urls[0]);

					// to send parameters to servlet

					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();

					is = entity.getContent();

				} catch (Exception e) {
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
					return "Conversion Exception : " + e.toString();
				}

			}

			@Override
			protected void onPostExecute(String result) {
				String parts[] = result.split("[$$$]+");
				if (parts[0].trim().equals("YES")) {				
					Toast.makeText(FeedBack.this, parts[1], Toast.LENGTH_SHORT).show();								
				} else {
					Toast.makeText(FeedBack.this, parts[1], Toast.LENGTH_SHORT).show();
				}
			}
		}

		

}
