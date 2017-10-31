//<!-- copyrighted content owned by Android Arena (www.androidarena.co.in)-->
package com.bi;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	
	private Button loginbut,reqBlood;
	
	EditText un,pw;	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);
        
        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");
        if(msg!=null){
        	if(msg.trim().length()>0){
        		Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
        	}
        }
        
        un = (EditText) findViewById(R.id.lun);
        pw = (EditText) findViewById(R.id.lpw);
        
        loginbut = (Button) findViewById(R.id.logbut);
        loginbut.setOnClickListener(this);
        
        reqBlood = (Button) findViewById(R.id.rbbut);
        reqBlood.setOnClickListener(this);
        
        TextView reg = (TextView)findViewById(R.id.reg);
        reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent nxt = new Intent(getApplicationContext(),MainActivity.class);
				startActivity(nxt);
			}
		});
        
        TextView setserver = (TextView)findViewById(R.id.setserver);
        setserver.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent nxt = new Intent(getApplicationContext(),SetServer.class);
				startActivity(nxt);
			}
		});
	}

	@Override
	public void onClick(View v) {
		if(v==loginbut){
			userLogin();
		}
		if(v==reqBlood){
			reqBlood();
		}		
	}
	
public void userLogin() {
		ConnectToServer task = new ConnectToServer();

		ClientSettings.httpServerUrl = "http://" + ClientSettings.serverIp
				+ ":" + ClientSettings.serverPort + "/"
				+ ClientSettings.project + "/";

		ClientSettings.un = ""+un.getText();
		
		String params = "?un="+ClientSettings.un+"&pw="+pw.getText();
				
		task.execute(new String[] { ClientSettings.httpServerUrl
				+ "mobLogin.jsp"+params });
	}

public void reqBlood() {
	Intent intent = new Intent(Login.this, ReqBlood.class);	
	startActivity(intent);
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
					Intent intent = new Intent(Login.this, Welcome.class);
					intent.putExtra("msg", parts[1].trim());
					startActivity(intent);								
			} else {
				Toast.makeText(Login.this, parts[1], Toast.LENGTH_SHORT).show();
			}
		}
	}


}
