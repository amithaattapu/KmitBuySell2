package com.example.kmitbuysell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    DatabaseReference databaseReference1;
    FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
    List<String> ids=new ArrayList<>();
    List<User> users=new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        recyclerView=findViewById(R.id.recycler_view);
        bottomNavigationView=findViewById(R.id.nav_view);
        databaseReference= FirebaseDatabase.getInstance().getReference("Chats");
        databaseReference1=FirebaseDatabase.getInstance().getReference("Users");
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.navigation_home:
                        Intent intent=new Intent(ChatsActivity.this,HomeActivity.class);
                        startActivity(intent);
                }
                return true;
            }
        });
        final List<User> list=new ArrayList<>();
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    User user10=dataSnapshot1.getValue(User.class);
                    list.add(user10);
                    //Toast.makeText(ChatsActivity.this,"I am here too"+user10.getName(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (firebaseUser.getUid().equals(chat.getReciever()))
                    {
                       // Toast.makeText(ChatsActivity.this,"I am here too"+chat.getSender(),Toast.LENGTH_LONG).show();
                        ids.add(chat.getSender());
                    }
                    if(firebaseUser.getUid().equals(chat.getSender()))
                    {   // Toast.makeText(ChatsActivity.this,"I am here too "+chat.getReciever(),Toast.LENGTH_LONG).show();
                        ids.add(chat.getReciever());
                    }
                }
                for(String i:ids)
                {
                    for(User user:list)
                    {
                        if(user.getUid().equals(i))
                        {int flag=0;
                            //Toast.makeText(ChatsActivity.this,"I am here too too"+user.getName(),Toast.LENGTH_LONG).show();
                           // if(!users.contains(i))
                            for(User user1:users)
                            {
                                if(user1.getUid().equals(i))
                                {   flag=1;
                                break;}
                            }
                            if(flag==0)
                            users.add(user);
                        }
                    }
                }
               // .makeText(ChatsActivity.this,"I am here"+users.size(),Toast.LENGTH_LONG).show();
                ChatsAdapter chatsAdapter=new ChatsAdapter(ChatsActivity.this,users);
                recyclerView.setAdapter(chatsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
