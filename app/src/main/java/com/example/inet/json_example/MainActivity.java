package com.example.inet.json_example;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {
    DatabaseReference rootRef,demoRef;

    EditText ed1;
    Button b1,b2;
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=findViewById(R.id.name);
        b2=findViewById(R.id.fetch);
        txt1=findViewById(R.id.txt2);
        b1=findViewById(R.id.b1);
        rootRef = FirebaseDatabase.getInstance().getReference();
        demoRef = rootRef.child("jeena1").child("demo");
        b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        demoRef.setValue(ed1.getText().toString());

    }
});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String valu = dataSnapshot.getValue(String.class);
                        txt1.setText(valu);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }

}
