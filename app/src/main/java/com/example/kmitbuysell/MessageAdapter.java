package com.example.kmitbuysell;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kmitbuysell.MessageActivity;
import com.example.kmitbuysell.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.Holder>{
    private static final int MSG_TYPE_LEFT=0;
    private static final int MSG_TYPE_RIGHT=1;

    private Context mcontext;
    private List<Chat> mChat;
    FirebaseUser firebaseUser;
    public  MessageAdapter(Context mcontext,List<Chat> mChat)
    {
        this.mcontext=mcontext;
        this.mChat=mChat;
    }

    @NonNull
    @Override
    public MessageAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT) {
            //View view = LayoutInflater.from(mcontext).inflate(R.layout.left, parent, false);
            View view= LayoutInflater.from(mcontext).inflate(R.layout.chat_right,parent,false);
            return new MessageAdapter.Holder(view);
        }
        else
        {
            View view = LayoutInflater.from(mcontext).inflate(R.layout.chat_left, parent, false);
            return new MessageAdapter.Holder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.Holder holder, int position) {
        Chat chat=mChat.get(position);
        holder.show_message.setText(chat.getMessage());
    }


    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class Holder extends RecyclerView.ViewHolder
    {
        public TextView show_message;

        public Holder(@NonNull View itemView) {
            super(itemView);
            show_message=itemView.findViewById(R.id.show_message);
        }
    }
    @Override
    public int getItemViewType(int position)
    {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(firebaseUser.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else
        {
            return MSG_TYPE_LEFT;
        }
    }
}
