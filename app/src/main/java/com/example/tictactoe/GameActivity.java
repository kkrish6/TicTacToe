package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    int gamestate;
    TextView score2;
    Integer oppt=0;
    Integer youpt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        score2=findViewById(R.id.score2);

        gamestate = 1;
    }

    public void GameBoardClick(View view) {
        {
            ImageView selectedImage = (ImageView) view;

            int selectedBlock = 0;
            switch (selectedImage.getId()) {
                case R.id.iv_11:
                    selectedBlock = 1;
                    break;
                case R.id.iv_12:
                    selectedBlock = 2;
                    break;
                case R.id.iv_13:
                    selectedBlock = 3;
                    break;

                case R.id.iv_21:
                    selectedBlock = 4;
                    break;
                case R.id.iv_22:
                    selectedBlock = 5;
                    break;
                case R.id.iv_23:
                    selectedBlock = 6;
                    break;

                case R.id.iv_31:
                    selectedBlock = 7;
                    break;
                case R.id.iv_32:
                    selectedBlock = 8;
                    break;
                case R.id.iv_33:
                    selectedBlock = 9;
                    break;
            }
            PlayGame (selectedBlock,selectedImage);
        }
    }
    int activePlayer =1;
    ArrayList<Integer> player1 = new ArrayList<Integer>();
    ArrayList<Integer> player2 = new ArrayList<Integer>();
    private void PlayGame(int selectedBlock, ImageView selectedImage) {

        CheckWinner();

        if(gamestate==1){
            if(activePlayer==1){
                selectedImage.setImageResource(R.drawable.ttt_x);
                player1.add(selectedBlock);
                activePlayer = 2;
                Autoplay();
            }else if(activePlayer==2){
                selectedImage.setImageResource(R.drawable.ttt_o);
                player2.add(selectedBlock);
                activePlayer =1;
            }

            selectedImage.setEnabled(false);
            CheckWinner();
        }
    }

    private void Autoplay() {
        ArrayList<Integer> emptyBlocks =new ArrayList<Integer>();
        for(int i=0;i<=9;i++){
            if(!(player1.contains(i)|| player2.contains(i))){
                emptyBlocks.add(i);
            }
        }
        if(emptyBlocks.size()==0){
            CheckWinner();
            if(gamestate==1){
                AlertDialog.Builder b =new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                showAlert("Draw");
            }
            gamestate=3;
        }else{

            if((player2.contains(1)&&player2.contains(2)&&emptyBlocks.contains(3))){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_13);


                PlayGame(3,selectedImage);
            }
            else if((player2.contains(2)&&player2.contains(3))&&emptyBlocks.contains(1)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_11);


                PlayGame(1,selectedImage);
            }
            else if((player2.contains(1)&&player2.contains(3))&&emptyBlocks.contains(2)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_12);


                PlayGame(2,selectedImage);
            }

            else if((player2.contains(4)&&player2.contains(5))&&emptyBlocks.contains(6)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_23);


                PlayGame(6,selectedImage);
            }
            else if((player2.contains(4)&&player2.contains(6))&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player2.contains(5)&&player2.contains(6))&&emptyBlocks.contains(4)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_21);


                PlayGame(4,selectedImage);
            }

            else if((player2.contains(7)&&player2.contains(8))&&emptyBlocks.contains(9)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_33);


                PlayGame(9,selectedImage);
            }
            else if((player2.contains(7)&&player2.contains(9))&&emptyBlocks.contains(8)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_32);


                PlayGame(8,selectedImage);
            }
            else if((player2.contains(8)&&player2.contains(9))&&emptyBlocks.contains(7)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_31);


                PlayGame(7,selectedImage);
            }

            else if((player2.contains(1)&&player2.contains(4))&&emptyBlocks.contains(7)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_31);


                PlayGame(7,selectedImage);
            }
            else if((player2.contains(4)&&player2.contains(7))&&emptyBlocks.contains(1)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_11);


                PlayGame(1,selectedImage);
            }
            else if((player2.contains(1)&&player2.contains(7))&&emptyBlocks.contains(4)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_21);


                PlayGame(4,selectedImage);
            }

            else if((player2.contains(2)&&player2.contains(5))&&emptyBlocks.contains(8)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_32);


                PlayGame(8,selectedImage);
            }
            else if((player2.contains(2)&&player2.contains(8))&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player2.contains(5)&&player2.contains(8))&&emptyBlocks.contains(2)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_12);


                PlayGame(2,selectedImage);
            }
            else if((player2.contains(3)&&player2.contains(6))&&emptyBlocks.contains(9)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_33);


                PlayGame(9,selectedImage);
            }
            else if((player2.contains(3)&&player2.contains(9))&&emptyBlocks.contains(6)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_23);


                PlayGame(6,selectedImage);
            }
            else if((player2.contains(6)&&player2.contains(9))&&emptyBlocks.contains(3)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_13);


                PlayGame(3,selectedImage);
            }
            else if((player2.contains(1)&&player2.contains(5))&&emptyBlocks.contains(9)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_33);


                PlayGame(9,selectedImage);
            }
            else if((player2.contains(1)&&player2.contains(9))&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player2.contains(5)&&player2.contains(9))&&emptyBlocks.contains(1)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_11);


                PlayGame(1,selectedImage);
            }
            else if((player2.contains(3)&&player2.contains(7))&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player2.contains(3)&&player2.contains(5))&&emptyBlocks.contains(7)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_31);


                PlayGame(7,selectedImage);
            }
            else if((player2.contains(5)&&player2.contains(7))&&emptyBlocks.contains(3)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_13);


                PlayGame(3,selectedImage);
            }














            else if((player1.contains(1)&&player1.contains(2))&&emptyBlocks.contains(3)){

                  ImageView selectedImage=(ImageView) findViewById(R.id.iv_13);


                  PlayGame(3,selectedImage);
            }
            else if((player1.contains(2)&&player1.contains(3))&&emptyBlocks.contains(1)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_11);


                PlayGame(1,selectedImage);
            }
            else if((player1.contains(1)&&player1.contains(3))&&emptyBlocks.contains(2)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_12);


                PlayGame(2,selectedImage);
            }

            else if((player1.contains(4)&&player1.contains(5))&&emptyBlocks.contains(6)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_23);


                PlayGame(6,selectedImage);
            }
            else if((player1.contains(4)&&player1.contains(6))&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player1.contains(5)&&player1.contains(6))&&emptyBlocks.contains(4)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_21);


                PlayGame(4,selectedImage);
            }

            else if((player1.contains(7)&&player1.contains(8))&&emptyBlocks.contains(9)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_33);


                PlayGame(9,selectedImage);
            }
            else if((player1.contains(7)&&player1.contains(9))&&emptyBlocks.contains(8)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_32);


                PlayGame(8,selectedImage);
            }
            else if((player1.contains(8)&&player1.contains(9))&&emptyBlocks.contains(7)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_31);


                PlayGame(7,selectedImage);
            }

            else if((player1.contains(1)&&player1.contains(4))&&emptyBlocks.contains(7)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_31);


                PlayGame(7,selectedImage);
            }
            else if((player1.contains(4)&&player1.contains(7))&&emptyBlocks.contains(1)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_11);


                PlayGame(1,selectedImage);
            }
            else if((player1.contains(1)&&player1.contains(7))&&emptyBlocks.contains(4)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_21);


                PlayGame(4,selectedImage);
            }

            else if((player1.contains(2)&&player1.contains(5))&&emptyBlocks.contains(8)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_32);


                PlayGame(8,selectedImage);
            }
            else if((player1.contains(2)&&player1.contains(8))&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player1.contains(5)&&player1.contains(8))&&emptyBlocks.contains(2)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_12);


                PlayGame(2,selectedImage);
            }
            else if((player1.contains(3)&&player1.contains(6))&&emptyBlocks.contains(9)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_33);


                PlayGame(9,selectedImage);
            }
            else if((player1.contains(3)&&player1.contains(9))&&emptyBlocks.contains(6)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_23);


                PlayGame(6,selectedImage);
            }
            else if((player1.contains(6)&&player1.contains(9))&&emptyBlocks.contains(3)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_13);


                PlayGame(3,selectedImage);
            }
            else if((player1.contains(1)&&player1.contains(5))&&emptyBlocks.contains(9)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_33);


                PlayGame(9,selectedImage);
            }
            else if((player1.contains(1)&&player1.contains(9))&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player1.contains(5)&&player1.contains(9))&&emptyBlocks.contains(1)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_11);


                PlayGame(1,selectedImage);
            }
            else if((player1.contains(3)&&player1.contains(7))&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player1.contains(3)&&player1.contains(5))&&emptyBlocks.contains(7)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_31);


                PlayGame(7,selectedImage);
            }
            else if((player1.contains(5)&&player1.contains(7))&&emptyBlocks.contains(3)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_13);


                PlayGame(3,selectedImage);
            }
            else if(player1.contains(2)&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }
            else if((player1.contains(2)&&player1.contains(3))&&player2.contains(1)&&emptyBlocks.contains(5)){
                ImageView selectedImage=(ImageView) findViewById(R.id.iv_22);


                PlayGame(5,selectedImage);
            }








            else{
            Random r=new Random();
            int randomIndex =r.nextInt(emptyBlocks.size());
            int selectedBlock = emptyBlocks.get(randomIndex);

            ImageView selectedImage =(ImageView)findViewById(R.id.iv_11);
            switch (selectedBlock){
                case 1:selectedImage = (ImageView) findViewById(R.id.iv_11);break;
                case 2:selectedImage = (ImageView) findViewById(R.id.iv_12);break;
                case 3:selectedImage = (ImageView) findViewById(R.id.iv_13);break;

                case 4:selectedImage = (ImageView) findViewById(R.id.iv_21);break;
                case 5:selectedImage = (ImageView) findViewById(R.id.iv_22);break;
                case 6:selectedImage = (ImageView) findViewById(R.id.iv_23);break;

                case 7:selectedImage = (ImageView) findViewById(R.id.iv_31);break;
                case 8:selectedImage = (ImageView) findViewById(R.id.iv_32);break;
                case 9:selectedImage = (ImageView) findViewById(R.id.iv_33);break;
            }
            PlayGame(selectedBlock,selectedImage);}

        }
    }

    private void showAlert(String Title) {
      AlertDialog.Builder b =new AlertDialog.Builder(this,R.style.Theme_AppCompat_DayNight_Dialog);
      b.setTitle(Title)
              .setMessage("start a new game?")
              .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      ResetGame();
                  }
              })
              .setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      Intent i =new Intent(getApplicationContext(),MenuActivity.class);
                  }
              })
              .setIcon(android.R.drawable.ic_dialog_info)
              .show();
    }

    void ResetGame(){
        gamestate=1;
        activePlayer =1;
        player1.clear();
        player2.clear();

        ImageView iv;
        iv=(ImageView) findViewById(R.id.iv_11);iv.setImageResource(0); iv.setEnabled(true);
        iv=(ImageView) findViewById(R.id.iv_12);iv.setImageResource(0); iv.setEnabled(true);
        iv=(ImageView) findViewById(R.id.iv_13);iv.setImageResource(0); iv.setEnabled(true);

        iv=(ImageView) findViewById(R.id.iv_21);iv.setImageResource(0); iv.setEnabled(true);
        iv=(ImageView) findViewById(R.id.iv_22);iv.setImageResource(0); iv.setEnabled(true);
        iv=(ImageView) findViewById(R.id.iv_23);iv.setImageResource(0); iv.setEnabled(true);


        iv=(ImageView) findViewById(R.id.iv_31);iv.setImageResource(0); iv.setEnabled(true);
        iv=(ImageView) findViewById(R.id.iv_32);iv.setImageResource(0); iv.setEnabled(true);
        iv=(ImageView) findViewById(R.id.iv_33);iv.setImageResource(0); iv.setEnabled(true);


    }

    private void CheckWinner() {

        int winner=0;

        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){winner = 1;}
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){winner = 1;}
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){winner = 1;}

        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){winner = 1;}
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){winner = 1;}
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){winner = 1;}

        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){winner = 1;}
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){winner = 1;}



        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){winner = 2;}
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){winner = 2;}
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){winner = 2;}

        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){winner = 2;}
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){winner = 2;}
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){winner = 2;}

        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){winner = 2;}
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){winner = 2;}



        if(winner !=0 && gamestate ==1){
            if(winner==1){
                showAlert("You win the game");
                youpt++;


            }else if (winner==2){
                showAlert("Player 2 is Winner");
                oppt++;

            }
            else{
                AlertDialog.Builder b =new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                showAlert("Draw");

            }

            gamestate=2;
            score2.setText("your point:"+youpt+"   "+"opponent point"+oppt);


        }

    }

    public void resbutton(View view) {
        ResetGame();
    }
}
