package com.example.ib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.paging.LoadingState;

import de.hdodenhof.circleimageview.CircleImageView;

public class MentorsAdapter extends FirebaseRecyclerAdapter<Mentors,MentorsAdapter.myViewHolder>{

    //progress circular animation
    ProgressBar progressBar;

    public MentorsAdapter(@NonNull FirebaseRecyclerOptions<Mentors> options) {
        super(options);
    }

    @Override
    public void onDataChanged() {
        if(progressBar != null){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Mentors model) {

        holder.name.setText(model.getUser());
        holder.discription.setText(model.getMail());
        holder.subject.setText(model.getMentSubj());
        Glide.with(holder.img.getContext()).load(model.getImg()).into(holder.img);
        holder.standard.setText(model.getStd().toString());

        // Add click listener to send email to selected mentors
        holder.slot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recipientMail = model.getMail();
                String MentorName = model.getUser();
                String MentorSubject = model.getMentSubj();

                Intent intent = new Intent(v.getContext(), scheduleMeet.class);
                intent.putExtra("email", recipientMail);
                intent.putExtra("mentorName",MentorName);
                intent.putExtra("mentorSubject",MentorSubject);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachslot,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    /// my view holder class
    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name, discription,subject, standard;
        Button slot;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
//            progressBar = itemView.findViewById(R.id.progressBar);
            img =(CircleImageView)itemView.findViewById(R.id.profpic);
            name = (TextView) itemView.findViewById(R.id.mentorName);
            discription = (TextView) itemView.findViewById(R.id.mentorMail);
            slot = (Button) itemView.findViewById(R.id.viewSlotBtn);
            subject = (TextView) itemView.findViewById(R.id.mentorSubj);
            standard = (TextView) itemView.findViewById(R.id.mentorStd);
        }
    }


}








                           //// <<<<<<<<<<< New Adapter setup >>>>>>>>>>>>////

///// new adapter setup
//import android.content.Context;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.ib.Mentors;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class MentorsAdapter extends RecyclerView.Adapter<MentorsAdapter.MyViewHolder> {
//
//    private List<Mentors> mList = new ArrayList<>();
//    private Context mContext;
//
//    public MentorsAdapter(List<Mentors> list, Context context) {
//        mList = list;
//        mContext = context;
//
//    }
//
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.eachslot, null, false);
//
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//
//        holder.name.setText(mList.get(position).getUser());
//        holder.discription.setText(mList.get(position).getMail());
//        Glide.with(holder.img.getContext()).load(mList.get(position).getImg()).into(holder.img);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        CircleImageView img;
//        TextView name, discription;
//
//        //
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            img = (CircleImageView) itemView.findViewById(R.id.profpic);
//            name = (TextView) itemView.findViewById(R.id.mentorName);
//            discription = (TextView) itemView.findViewById(R.id.mentorDisc);
//        }
//    }
//}

                                //// <<<<<<<<<<< New Adapter setup >>>>>>>>>>>>////