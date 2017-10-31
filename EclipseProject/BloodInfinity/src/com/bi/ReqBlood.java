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

public class ReqBlood extends Activity implements OnClickListener {
	
	private Spinner spinner4;
	private Button btnSubmit;
	private WebView webView;
	
	EditText arr;	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webz);
		
		btnSubmit = (Button) findViewById(R.id.btnSubmit);		
		btnSubmit.setOnClickListener(this);
		
		webView = (WebView) findViewById(R.id.webView2);
		webView.getSettings().setJavaScriptEnabled(true);
		
		arr = (EditText) findViewById(R.id.arr);
		
		addItemsOnSpinner1();
		
	}
	
	// add items into spinner dynamically
	  public void addItemsOnSpinner1() {
	 
		spinner4 = (Spinner) findViewById(R.id.spinner4);
		List<String> list = new ArrayList<String>();
		list.add("Select-BloodType");
		list.add("A+");
		list.add("A-");
		list.add("B+");
		list.add("B-");
		list.add("O+");
		list.add("O-");
		list.add("AB+");
		list.add("AB-");
		
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner4.setAdapter(dataAdapter1);
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
						
			String url = ClientSettings.httpServerUrl+"mobBloodInfo.jsp?bt="+spinner4.getSelectedItem()+"&area="+arr.getText();
			webView.loadUrl(url);		
		}
		

}
