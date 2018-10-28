package com.anonarmy.prajwalnayak.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
    Button b1,b2,b3;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Thank you, monitoring started ",Toast.LENGTH_SHORT).show();
                i=new Intent(MainActivity.this,MyService.class);
                startService(i);
            }
        }
        );
        b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View v) {
                                      i=new Intent(MainActivity.this,MyService.class);
                                      stopService(i);
                                  }
                              }
        );
        b3=(Button)findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener()
                              {
                                  @Override
                                  public void onClick(View v) {
                                        //  EditText emailid = (EditText) findViewById(R.id.password);
                                         // Toast.makeText(getApplicationContext(), "hi" + emailid.getText().toString(), Toast.LENGTH_SHORT).show();
                                      i=new Intent(MainActivity.this,FullscreenActivity.class);
                                      MainActivity.this.startActivity(i);
                                  }
                              }
        );
    }

  
    public static String readFromFile(Context context){
        String ret="";
        try{
            InputStream inputStream=context.openFileInput("data_file.txt");
            if(inputStream!=null)
            try{
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String recieveString="";
                StringBuilder stringBuilder=new StringBuilder();
                while ((recieveString=bufferedReader.readLine())!=null){
                    stringBuilder.append(recieveString);
                }
            inputStream.close();
                ret=stringBuilder.toString();
            }
            catch (FileNotFoundException e){

            }
        }
        catch (IOException e){

        }
        return ret;
    }
    public static void writeToFile(String data,Context context){
        try{
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(context.openFileOutput("data_file.txt",Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
