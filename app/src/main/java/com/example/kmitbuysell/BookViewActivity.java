package com.example.kmitbuysell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class BookViewActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView,year,price,type,branch;
    private Button chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);
        Intent intent=getIntent();
        String name=intent.getExtras().get("name").toString();
        String imageurl=intent.getExtras().get("image").toString();
        final String userid=intent.getExtras().get("userid").toString();
        imageView=findViewById(R.id.imageView3);
        textView=findViewById(R.id.Bookname);
        textView.setText(name);
        chat=findViewById(R.id.chat);
        year=findViewById(R.id.Year1);
        year.setText(intent.getExtras().get("Year").toString());
        price=findViewById(R.id.price1);
        price.setText(intent.getExtras().get("price").toString());
        branch=findViewById(R.id.branch1);
        branch.setText(intent.getExtras().get("branch").toString());
        type=findViewById(R.id.Type);
        type.setText(intent.getExtras().get("type").toString());
        final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        //imageView.setImageURI(Uri.parse(imageurl));
        //imageView.setImage
       /* Picasso.with(this)
                .load(imageurl)
                .fit()
                .centerCrop()
                .into(imageView);*/
        Glide.with(this)
                .load(imageurl)
                .into(imageView);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firebaseAuth.getCurrentUser()==null)
                {
                    Intent intent=new Intent(BookViewActivity.this,LoginActivity.class);
                    intent.putExtra("flag",2);
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                }
                else
                {
                    Intent intent=new Intent(BookViewActivity.this,MessageActivity.class);
                    intent.putExtra("userid",userid);
                    startActivity(intent);
                }
            }
        });
    }
}
