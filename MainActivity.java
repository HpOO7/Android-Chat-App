package com.example.knight.b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void storeData(View view) {

        EditText ename = (EditText) findViewById(R.id.editText3);
        LogInDetails user = new LogInDetails(ename.getText().toString(), "NO");
        DataBaseHelper db = new DataBaseHelper(this);
        db.addContact(user);
        LogInDetails c =  db.getAllContacts();
        if(c!=null){
            //Toast.makeText(getApplicationContext(), c.getName()+" hey "+ c.getNumber() , Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Chat.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Your nick name is: "+c.getName() , Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Please provide a user name "+ c.getNumber() , Toast.LENGTH_SHORT).show();
        }
    }
}

