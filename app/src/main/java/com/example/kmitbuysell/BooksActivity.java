package com.example.kmitbuysell;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity {
    private RecyclerView mrecyclerview;
    private BookAdapter mbookadapter;
    private DatabaseReference databaseReference;
    Button button;
    private List<Book> list;
    private List<Book> list1;
    private CheckBox checkBox;
    //private SearchView sv;
    private androidx.appcompat.widget.SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        list = new ArrayList<Book>();
        list1 = new ArrayList<Book>();
        button = findViewById(R.id.filter);
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");
        mrecyclerview = findViewById(R.id.rv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BooksActivity.this, FilterActivity.class);
                startActivity(intent);
            }
        });
        /*checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    mbookadapter=new BookAdapter(BooksActivity.this,list1);
                    mrecyclerview.setAdapter(mbookadapter);

                }
                else
                {
                    mbookadapter=new BookAdapter(BooksActivity.this,list);
                    mrecyclerview.setAdapter(mbookadapter);

                }
            }
        });*/
        // sv=findViewById(R.id.searchview);
        mrecyclerview.setHasFixedSize(true);
        mrecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        final Intent intent = getIntent();
        int flag = (int) intent.getExtras().get("flag");

        if (flag == 1)
        {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Book book = dataSnapshot1.getValue(Book.class);
                        list.add(book);

                    }

                    mbookadapter = new BookAdapter(BooksActivity.this, list);
                    mrecyclerview.setAdapter(mbookadapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(BooksActivity.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
    }
        else if(flag==2)
        {
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        Book book=dataSnapshot1.getValue(Book.class);
                        //boolean branch=book.getBranch().equals(intent.getExtras().get("branch").toString());
                        //boolean year=book.getYear().equals(intent.getExtras().get("year").toString());
                        //boolean type=book.getType().equals(intent.getExtras().get("type").toString());
                       // Toast.makeText(BooksActivity.this,"Branch "+branch,Toast.LENGTH_LONG).show();
                        //Toast.makeText(BooksActivity.this,"Year "+year,Toast.LENGTH_LONG).show();
                        //Toast.makeText(BooksActivity.this,"Type "+type,Toast.LENGTH_LONG).show();
                        int flag=0;
                        if(!intent.getExtras().get("branch").toString().equals("")) {
                            if (book.getBranch().equals(intent.getExtras().get("branch").toString())) {
                                flag++;
                            }
                        }
                        else
                            flag++;
                        if(!intent.getExtras().get("year").toString().equals(""))
                        {
                            if(book.getYear().equals(intent.getExtras().get("year").toString()))
                            {
                               flag++;
                            }
                        }
                        else
                            flag++;
                            if(!intent.getExtras().get("type").toString().equals(""))
                            {
                                if(book.getType().equals(intent.getExtras().get("type").toString()))
                                {
                                    flag++;
                                }
                            }
                            else if(intent.getExtras().get("type").toString().equals("")) {
                                flag++;

                            }
                            if(flag==3)
                                list.add(book);
                        //Toast.makeText(BooksActivity.this,"size is"+flag,Toast.LENGTH_LONG).show();
                    }

                    mbookadapter = new BookAdapter(BooksActivity.this, list);
                    mrecyclerview.setAdapter(mbookadapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    //@Override
    /*public void onStart() {
        super.onStart();
        if (sv != null) {
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

        }
    }
    public void search(String s)
    {
        ArrayList<Book> al=new ArrayList<Book>();
        for(Book a:list)
        {
            if(a.getBookName().toLowerCase().contains(s.toLowerCase()))
            {
                al.add(a);
            }
        }
        BookAdapter ba=new BookAdapter(this,al);
        mrecyclerview.setAdapter(ba);
    }*/


   /* public void onCheckboxClicked(View view) {
        // Is the view now checked?
        if (0 !=view.getId())
        {Toast.makeText(BooksActivity.this, "tis is checked", Toast.LENGTH_LONG).show();
        mbookadapter = new BookAdapter(BooksActivity.this, list1);
        mrecyclerview.setAdapter(mbookadapter); }

        }
        /*boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            default:
            if (checked) {
                Toast.makeText(BooksActivity.this, "tis is checked", Toast.LENGTH_LONG).show();
                mbookadapter = new BookAdapter(BooksActivity.this, list1);
                mrecyclerview.setAdapter(mbookadapter);
            } else {
                mbookadapter = new BookAdapter(BooksActivity.this, list);
                mrecyclerview.setAdapter(mbookadapter);
            }
            break;
        }*/
   @Override
     public  boolean onCreateOptionsMenu(Menu menu)
     {

         return true;
     }
    }
