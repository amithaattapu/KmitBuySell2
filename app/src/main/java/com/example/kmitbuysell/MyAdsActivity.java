package com.example.kmitbuysell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyAdsActivity extends AppCompatActivity {
     DatabaseReference databaseReference;
     List<Book> list;
     FirebaseUser firebaseUser;
     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads);
        databaseReference= FirebaseDatabase.getInstance().getReference("uploads");
        list= new ArrayList<>();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        recyclerView=findViewById(R.id.recycler_view);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {    Book book=snapshot.getValue(Book.class);
                    if(firebaseUser.getUid().equals(book.getUid()))
                    {
                        list.add(book);
                    }
                }
                Toast.makeText(MyAdsActivity.this,"i am here"+list.size(),Toast.LENGTH_LONG).show();
                MyAdAdapter myAdAdapter=new MyAdAdapter(MyAdsActivity.this,list);
                recyclerView.setAdapter(myAdAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
