//<!-- copyrighted content owned by Android Arena (www.androidarena.co.in)-->
package com.bi;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{

	private Spinner spinner1,spinner2;
	private Button regbut;
	
	EditText name,area,pin,email,mob,age,un,pw;	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        regbut = (Button) findViewById(R.id.regbut);
        regbut.setOnClickListener(this);
        
        name = (EditText) findViewById(R.id.name);
        area = (EditText) findViewById(R.id.area);
        pin = (EditText) findViewById(R.id.pin);
        email = (EditText) findViewById(R.id.email);
        mob = (EditText) findViewById(R.id.mob);
        age = (EditText) findViewById(R.id.age);
        un = (EditText) findViewById(R.id.un);
        pw = (EditText) findViewById(R.id.pw);
        
        addItemsOnSpinner1();
        addItemsOnSpinner2();
    }
    
 // add items into spinner dynamically
 	  public void addItemsOnSpinner1() {
 	 
 		spinner1 = (Spinner) findViewById(R.id.bt);
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
 		spinner1.setAdapter(dataAdapter1);
 	  }

 	// add items into spinner dynamically
 	  public void addItemsOnSpinner2() {
 	 
 		spinner2 = (Spinner) findViewById(R.id.sex);
 		List<String> list = new ArrayList<String>();
 		list.add("Select-Gender");
 		list.add("Male");
 		list.add("Female"); 		
 		
 		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
 			android.R.layout.simple_spinner_item, list);
 		dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 		spinner2.setAdapter(dataAdapter1);
 	  }

	@Override
	public void onClick(View v) {
		if(v==regbut){
		  userRegister();	
		}		
	}

	public void userRegister() {
		
		String bloodType = String.valueOf(spinner1.getSelectedItem()).toLowerCase();
		String sex = String.valueOf(spinner2.getSelectedItem()).toLowerCase();
		
		ConnectToServer task = new ConnectToServer();

		ClientSettings.httpServerUrl = "http://" + ClientSettings.serverIp
				+ ":" + ClientSettings.serverPort + "/"
				+ ClientSettings.project + "/";

		String params = "?un="+un.getText()+"&pw="+pw.getText()
						+"&name="+name.getText()+"&bt="+bloodType
						+"&area="+area.getText()+"&pin="+pin.getText()
						+"&email="+email.getText()+"&mob="+mob.getText()
						+"&age="+age.getText()+"&sex="+sex;
				
		task.execute(new String[] { ClientSettings.httpServerUrl
				+ "mobRegistration.jsp"+params });
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
					Intent intent = new Intent(MainActivity.this, Login.class);
					intent.putExtra("msg", parts[1].trim());
					startActivity(intent);								
			} else {
				Toast.makeText(MainActivity.this, parts[1], Toast.LENGTH_SHORT).show();
			}
		}
	}

}
