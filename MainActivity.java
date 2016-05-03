package com.example.login;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.net.UrlQuerySanitizer.ValueSanitizer;
import android.os.Bundle;




import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
EditText un,pw,pn;
Button ok;
TextView error;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		un=(EditText)findViewById(R.id.et_un);
		pw=(EditText)findViewById(R.id.et_pw);
    	pw.setTransformationMethod(PasswordTransformationMethod.getInstance());
		pn=(EditText)findViewById(R.id.et_pn);
		ok=(Button)findViewById(R.id.btn_login);
		error=(TextView)findViewById(R.id.tv_error);
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			ArrayList<NameValuePair> postParameter= new ArrayList<NameValuePair>();
			//String username=un.getText().toString();
			//postParameter.add(new BasicNameValuePair("username","25"));
			postParameter.add(new BasicNameValuePair("username",un.getText().toString()));
			postParameter.add(new BasicNameValuePair("password",pw.getText().toString()));
            postParameter.add(new BasicNameValuePair("phonenum",pn.getText().toString()));
            String response = null;
            try {
                response = CustomHttpClient.executeHttpPost("<target page url>", postParameter);
                String res=response.toString();
                res= res.replaceAll("\\s+","");
                if(res.equals("1"))
                    error.setText("Correct Username or Password or Phonenum");
                else
                    error.setText("Sorry!! Incorrect Username or Password or Phonenum");
            } catch (Exception e) {
                un.setText(e.toString());
            }
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
