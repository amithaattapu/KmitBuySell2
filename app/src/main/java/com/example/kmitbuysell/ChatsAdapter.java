package com.example.kmitbuysell;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.Holder>

{ private Context mcontext;
    private List<User> mUsers;
    public  ChatsAdapter(Context mcontext,List<User> mUsers)
    {
        this.mcontext=mcontext;
        this.mUsers=mUsers;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.chat_user,parent,false);
        return new ChatsAdapter.Holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final User user=mUsers.get(position);
        //Class<? extends DatabaseReference> user3=databaseReference.getClass();
        //String name=databaseReference.getKey();

        holder.username.setText(user.getName());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mcontext, MessageActivity.class);
                intent.putExtra("userid",user.getUid());
                mcontext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        public TextView username;
        private ItemClickListener itemClickListener;

        public Holder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener=itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
}
