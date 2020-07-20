package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderboardActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private DatabaseReference userRef;
    private ArrayList<String> scorelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        listView = findViewById(R.id.listView);
        userRef = FirebaseDatabase.getInstance().getReference().child("users");
        scorelist = new ArrayList<>();
        userRef.orderByChild("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                long count =0;
                for(DataSnapshot snapshot :dataSnapshot.getChildren())
                {

                    if(snapshot.hasChild("score"))
                    {
                        count++;
                        String x=snapshot.getKey() +"\nScore:"+snapshot.child("score").getValue().toString();
                        scorelist.add(0,x);

                    }
                }
                for(int i=0;i<count;i++)
                {
                    String x = scorelist.get(i);
                    scorelist.remove(i);
                    scorelist.add(i,i+1+"."+x);
                }
                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        arrayAdapter = new ArrayAdapter(LeaderboardActivity.this,android.R.layout.simple_list_item_1,scorelist);
        listView.setAdapter(arrayAdapter);


    }
}
