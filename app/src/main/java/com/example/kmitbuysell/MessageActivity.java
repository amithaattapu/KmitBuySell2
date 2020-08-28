package com.example.kmitbuysell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    TextView textView;
    DatabaseReference databaseReference;
    Intent intent;
    FirebaseUser firebaseUser;
    ImageButton btn_send;
    EditText textmessage;
    MessageAdapter messageAdapter;
    List<Chat> mchat;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        btn_send=findViewById(R.id.btn_send);
        textmessage=findViewById(R.id.textmessage);
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        textView=findViewById(R.id.sellername);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        /*toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/
        //textView=findViewById(R.id.username);
        intent=getIntent();
        final String userid=intent.getStringExtra("userid");
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=textmessage.getText().toString();
                if(msg!="")
                {
                    sendmessage(firebaseUser.getUid(),userid,msg);
                }
                else
                {
                    Toast.makeText(MessageActivity.this,"You can't send empty message",Toast.LENGTH_LONG).show();
                }
                textmessage.setText("");
            }
        });
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference=FirebaseDatabase.getInstance().getReference("Users").child(userid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user=dataSnapshot.getValue(User.class);
                textView.setText(user.getName());
                readMessage(firebaseUser.getUid(),userid);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void sendmessage(String sender,String reciever,String message)
    {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hp=new HashMap<>();
        hp.put("sender",sender);
        hp.put("reciever",reciever);
        hp.put("message",message);
        databaseReference.child("Chats").push().setValue(hp);
    }
    private void readMessage(final String myid, final String userid)
    {
        mchat=new ArrayList<>();
        databaseReference=FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Chat chat=snapshot.getValue(Chat.class);
                    if(chat.getReciever().equals(myid) && chat.getSender().equals(userid) || chat.getReciever().equals(userid) && chat.getSender().equals(myid))
                    {
                        mchat.add(chat);
                    }
                }
                messageAdapter=new MessageAdapter(MessageActivity.this,mchat);
                recyclerView.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
