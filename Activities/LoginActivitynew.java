package com.anonarmy.prajwalnayak.myapplication;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivitynew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        Button b=(Button)findViewById(R.id.buttonok);
        b.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View v) {
                                      //  EditText emailid = (EditText) findViewById(R.id.password);
                                      // Toast.makeText(getApplicationContext(), "hi" + emailid.getText().toString(), Toast.LENGTH_SHORT).show();
                                      Intent i=new Intent(LoginActivitynew.this, MainActivity.class);
                                      EditText pass=(EditText)findViewById(R.id.password);
                                      EditText pass2=(EditText)findViewById(R.id.password2);
                                      //if(pass.getText().toString()==pass2.getText().toString())
                                      LoginActivitynew.this.startActivity(i);
                                     // else
                                        //  Toast.makeText(getApplication(), " Wrong Password! Please enter correct one ", Toast.LENGTH_SHORT).show();
                                  }
                              }
        );
    }
}
