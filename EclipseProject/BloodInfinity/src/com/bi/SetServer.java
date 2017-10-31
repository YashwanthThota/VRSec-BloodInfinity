//<!-- copyrighted content owned by Android Arena (www.androidarena.co.in)-->
package com.bi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SetServer extends Activity implements OnClickListener{
	
	EditText ip;
	Button reg;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.setserver);
        
        ip = (EditText) findViewById(R.id.sip);        
        ip.setText(ClientSettings.serverIp);
        
        reg = (Button)findViewById(R.id.ipsave);
        reg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == reg) {
			// TODO Auto-generated method stub				
			ClientSettings.serverIp = "" + ip.getText();
			Intent nxt = new Intent(getApplicationContext(),Login.class);
			nxt.putExtra("msg", "Server IP Set with:"+ClientSettings.serverIp);
			startActivity(nxt);
		}		
	}
}
