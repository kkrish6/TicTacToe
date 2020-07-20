package com.example.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserListAdapter extends ArrayAdapter<String> {
    private DatabaseReference userRef;
    public UserListAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
        userRef = FirebaseDatabase.getInstance().getReference().child("users");
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);

        }

        final TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(getItem(position));
        userRef.child(getItem(position)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()&&dataSnapshot.hasChild("status")){
                    String status2=dataSnapshot.child("status").getValue().toString();
                    if(status2.equals("online"))
                    {
                        tvName.setBackgroundResource(R.color.colorAccent);
                    }
                    else
                    {
                        tvName.setBackgroundResource(R.color.coloroffline);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return convertView;
    }
}
