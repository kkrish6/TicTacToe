package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class OnlineLoginActivity extends AppCompatActivity {
ListView iv_loginUsers;
ArrayList<String> list_loginUsers =new ArrayList<String>();
ArrayAdapter adpt;


  ListView iv_requestedUsers;
  ArrayList<String> list_requestedusers =new ArrayList<String>();
  ArrayAdapter reqUsersAdpt;

  TextView tvUserID ,tvSendRequest ,tvAcceptRequest;
  String LoginUserId, UserName,LoginUID;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

   FirebaseDatabase database = FirebaseDatabase.getInstance();
   DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_login);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        tvSendRequest = (TextView) findViewById(R.id.tvSendRequest);
        tvAcceptRequest = (TextView) findViewById(R.id.tvAcceptRequest);

        tvSendRequest.setText("please Wait ....");
        tvAcceptRequest.setText("Please Wait...");

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAuth = FirebaseAuth.getInstance();

        iv_loginUsers = (ListView) findViewById(R.id.iv_loginUsers);
        adpt= new ArrayAdapter(this,android.R.layout.simple_list_item_1,list_loginUsers);
        iv_loginUsers.setAdapter(adpt);


          iv_requestedUsers = (ListView) findViewById(R.id.iv_requestedUsers);
        reqUsersAdpt = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list_requestedusers);
        iv_requestedUsers.setAdapter(reqUsersAdpt);

        tvUserID = (TextView) findViewById((R.id.tvLoginUser));


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!= null){
                    LoginUID = user.getEmail();
                    Log.d("Auth","onAuthStateChanged:signed_in: "+LoginUID);
                    LoginUserId=user.getEmail();
                    tvUserID.setText(LoginUserId);
                    UserName = convertEmailTOString(LoginUserId);
                    myRef.child("users").child(UserName).child("request").setValue(LoginUID);
                    AcceptIncomingRequests();
                }else{
                    Log.d("Auth Failed","onAuthStatecChanged:signed_out or login");
                    JoinOnlineGame();
            }
            }
        };
        myRef.getRoot().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                updateLoginUsers(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        iv_loginUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String requestToUser = ((TextView) view).getText().toString();
                confirmRequest(requestToUser,"To");
            }
        });

            iv_requestedUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final String requestFromUser = ((TextView)view).getText().toString();
                    confirmRequest(requestFromUser,"From");
                }
            });


        }


        void confirmRequest(final String OtherPlayer ,final String reqType){
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        LayoutInflater inflater =this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.connect_player_dialog,null);
        b.setView(dialogView);

        b.setTitle("Start Game");
        b.setMessage("Connect with"+OtherPlayer);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                myRef.child("users").child(OtherPlayer).child("request").push().setValue(LoginUserId);
                if(reqType.equalsIgnoreCase("From")){
                    StartGame(OtherPlayer + ":" + UserName,OtherPlayer,"From");
                }else {
                    StartGame(UserName + ":"+ OtherPlayer,OtherPlayer,"To");
                }
            }
        });
        b.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.show();
        }

        void StartGame(String PlayerGameID, String OtherPlayer, String requestType){
        myRef.child("playing").child(PlayerGameID).removeValue();
        Intent i =new Intent (getApplicationContext(),OnlineGameActivity.class);
        i.putExtra("player_session", PlayerGameID);
        i.putExtra("user_name",UserName);
        i.putExtra("other_player",OtherPlayer);
        i.putExtra("login_uid",LoginUID);
        i.putExtra("request_type",requestType);
        startActivity(i);
        finish();
        }

        void updateLoginUsers(DataSnapshot dataSnapshot){
        String Key = "";
        Set<String> set =new HashSet<String>();
            Iterator i =dataSnapshot.getChildren().iterator();

            while(i.hasNext()){
                Key =((DataSnapshot) i.next()).getKey();
                if(!Key.equalsIgnoreCase(UserName)){
                    set.add(Key);
                }
            }
            adpt.clear();
            adpt.addAll(set);
            adpt.notifyDataSetChanged();
            tvSendRequest.setText("Accept request from");
        }


            private String convertEmailTOString(String Email){
            String value = Email.substring(0,Email.indexOf('@'));
            value = value.replace("","");
            return value;

            }

            void AcceptIncomingRequests(){
                myRef.child("users").child(UserName).child("request")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                try{
                                    HashMap<String,Object> map=( HashMap<String, Object>) dataSnapshot.getValue();
                                    if(map != null){
                                        String value ="";
                                        for(String key:map.keySet()){
                                            value = (String) map.get(key);
                                            reqUsersAdpt.add(convertEmailTOString(value));
                                            reqUsersAdpt.notifyDataSetChanged();
                                            myRef.child("users").child(UserName).child("request").setValue(LoginUID);

                                        }

                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });





        }

        void JoinOnlineGame(){
            AlertDialog.Builder b =new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.login_dialog,null);
            b.setView(dialogView);

            final EditText etEmail =(EditText) dialogView.findViewById(R.id.etEmail);
            final EditText etPassword =(EditText) dialogView.findViewById(R.id.etPassword);

            b.setTitle("please register");
            b.setMessage("Enter your Email and Password for Registration");
            b.setPositiveButton("Register", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RegisterUser(etEmail.getText().toString(), etPassword.getText().toString());
                }
            });
            b.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i =new Intent(getApplicationContext(), MenuActivity.class);
                    startActivity(i);
                    finish();
                }
            });
            b.show();

        }










    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }



    public void RegisterUser(String email,String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Auth", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Auth", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(),"Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
