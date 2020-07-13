package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


    }

    public void backtomain(View view) {

        Intent i= new Intent(this,MenuActivity.class);
        startActivity(i);
        finish();
    }

    public void goinsta(View view) {
        TextView instatext = findViewById(R.id.instatext);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.instagram.com/kkrish_n/?hl=en"));
        startActivity(i);
    }
}
