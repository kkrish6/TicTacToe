package com.example.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends ArrayAdapter<String> {
    public UserListAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);

        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(getItem(position));
        if(getItem(position).contains("online"))
        {
            tvName.setBackgroundResource(R.color.colorAccent);
        }
        else if(getItem(position).contains("offline"))
        {
            tvName.setBackgroundResource(R.color.colorPrimary);
        }
        return convertView;
    }
}
