package com.example.myapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText fn, sn;
    Button submit, nav_button;

    //1. Database reference object
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fn = findViewById(R.id.et_fn);
        sn = findViewById(R.id.et_sn);
        Intent i = getIntent();
        String pk= i.getStringExtra("Pk");
        String url =i.getStringExtra("url");
        submit = findViewById(R.id.btn_submit);
        nav_button = findViewById(R.id.btn_nav);
        nav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Read.class);
                startActivity(i);
            }
        });
        //2, we link db reference to the node we want to search in FB.
        dbref = FirebaseDatabase.getInstance().getReference("_user_");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fn.getText().length() > 0 && sn.getText().length() > 0 )
                {
                    //3. use db ref to save.
                    User user = new User(fn.getText().toString(), sn.getText().toString());
                    dbref.child(dbref.push().getKey()).setValue(user);
                }
            }
        });



    }
}