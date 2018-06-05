package com.example.knight.b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    LogInDetails c;
    ListView lv;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        lv= (ListView)findViewById(R.id.lv);
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Chat.this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(adapter);
        DataBaseHelper db = new DataBaseHelper(this);
        c =  db.getAllContacts();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                arrayList.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i("TAG", "Failed to read value.", error.toException());
            }
        });
    }

    public void sendInfo(View view) {
        EditText m = (EditText) findViewById(R.id.editText2);
        String message = m.getText().toString();
        DatabaseReference mDef=FirebaseDatabase.getInstance().getReference("message");
        mDef.setValue("User: "+c.getName()+"\nMessage: "+message);
        m.setText("");
    }
}
