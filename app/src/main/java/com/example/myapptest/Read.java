package com.example.myapptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Read extends AppCompatActivity {
    TextView fn,sn;
    Button left, right;
     //1. Create database reference object.
    Query dbref;
    ArrayList<User> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        fn = findViewById(R.id.tv_fn);
        sn = findViewById(R.id.tv_sn);
        right = findViewById(R.id.btn_right);
        left = findViewById(R.id.btn_left);
        String variable= null;
        //2. Link dbref to firebase node we want to read from.
        dbref = FirebaseDatabase.getInstance().getReference("_user_").orderByChild("type").equalTo(variable);
        // Select * From _User_
        // Select * From _user_ where fn= "Lore"

        //4. Attach the ValueEventListener object to our DatabaseRef
        dbref.addListenerForSingleValueEvent(listener);
    }
    //3.Create a ValueEventListener Object
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss:snapshot.getChildren())
            {
                User u = dss.getValue(User.class);
                list.add(u);
            }
            fn.setText(list.get(0).getFn());
            sn.setText(list.get(0).getSn());

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}