package com.example.kmitbuysell;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdAdapter extends RecyclerView.Adapter<MyAdAdapter.Holder> {
    private Context mcontext;
    private List<Book> mBooks;
    public  MyAdAdapter(Context mcontext,List<Book> mBooks)
    {
        this.mcontext=mcontext;
        this.mBooks=mBooks;
    }

    @NonNull
    @Override
    public MyAdAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.bookdelete,parent,false);
        return new MyAdAdapter.Holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdAdapter.Holder holder, int position) {
        final Book book=mBooks.get(position);
        //Class<? extends DatabaseReference> user3=databaseReference.getClass();
        //String name=databaseReference.getKey();

        holder.pname.setText(book.getBookName());
        holder.price.setText(book.getPrice());
        Toast.makeText(mcontext,book.getImageURL(),Toast.LENGTH_LONG).show();
        Picasso.with(mcontext)
                .load(book.getImageURL())
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class Holder extends RecyclerView.ViewHolder
    {
       TextView pname,price;
       Button delete;
       ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            pname= itemView.findViewById(R.id.productname);
            price=itemView.findViewById(R.id.price);
            delete=itemView.findViewById(R.id.delete);
            imageView=itemView.findViewById(R.id.imageView);

        }
    }
}
