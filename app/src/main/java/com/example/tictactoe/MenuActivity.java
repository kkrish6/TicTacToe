package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void startGame_SinglePlayer(View view){
        Intent i=new Intent(this, GameActivity.class);
        startActivity(i);
    }
    public void EndGame(View view){
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }
    public void ShowAboutNote(View view){
        Intent i =new Intent (this,AboutActivity.class);
        startActivity(i);
    }
    public  void startGameonline(View view){
        Intent i = new Intent(this,OnlineLoginActivity.class);
        startActivity(i);
    }

    public void stn(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://kkrish6.github.io/kkrish6.githyb.io/"));
        startActivity(i);
    }
    public void fast(View view){
        Intent i = new Intent(MenuActivity.this,fastactivity.class);
        startActivity(i);

    }
}
