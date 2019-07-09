package com.example.adminapp.Notifiation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllStudentAdapter  extends RecyclerView.Adapter< AllStudentAdapter.ViewHolder> {

    public interface ChangeStatusListener{

        void onItemChangeListener(int postion, AllStudentstatusModel country);
    }

    public void setlist(ArrayList<AllStudentstatusModel> item) {
        this.item = item;
    }

    private Context context;
    private List<AllStudentstatusModel> item;
    ChangeStatusListener changeStatusListener;

    public  AllStudentAdapter(List<AllStudentstatusModel> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.all_student_list,viewGroup,false);
        return new  ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {

        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllStudentstatusModel student = item.get(viewHolder.position);
                if(student.isSelect()){
                    Toast.makeText(context,"6",Toast.LENGTH_SHORT).show();
                    student.setSelect(false);
                } else {
                    Toast.makeText(context,"7",Toast.LENGTH_SHORT).show();
                    student.setSelect(true);
                }
                item.set(viewHolder.position, student);
                if(changeStatusListener != null){
                    Toast.makeText(context,"8",Toast.LENGTH_SHORT).show();
                    changeStatusListener.onItemChangeListener(viewHolder.position, student);
                }
                notifyItemChanged(viewHolder.position);
            }
        });

//        final AllStudentstatusModel item1=item.get(position);
//        viewHolder.name.setText(item1.getName());
//        final String image1=item1.getImage();
//        if(image1.equals("default")){
////            View v = new CircleImageView(context);
////            viewHolder.image.setImageDrawable(v.getResources().getDrawable(R.drawable.placehldr));
//            viewHolder.image.setImageResource(R.drawable.placehldr);
//        }
//        viewHolder.status.setText(item1.getStatus());
//        Picasso.with(context)
//                .load(image1)
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .placeholder(R.drawable.placeholder )
//                .into(viewHolder.image, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//                        Picasso.with(context)
//                                .load(image1)
//                                .placeholder(R.drawable.placeholder )
//                                .into(viewHolder.image);
//                    }
//                });
       try{
           final AllStudentstatusModel item1=item.get(position);
           viewHolder.position = position;
           viewHolder.name.setText(item1.getName());
           final String image1=item1.getImage();
           if(image1.equals("default")){
//            View v = new CircleImageView(context);
//            viewHolder.image.setImageDrawable(v.getResources().getDrawable(R.drawable.placehldr));
               viewHolder.image.setImageResource(R.drawable.placehldr);
           }
           viewHolder.status.setText(item1.getStatus());
           Picasso.with(context)
                   .load(image1)
                   .networkPolicy(NetworkPolicy.OFFLINE)
                   .placeholder(R.drawable.placeholder )
                   .into(viewHolder.image, new Callback() {
                       @Override
                       public void onSuccess() {

                       }

                       @Override
                       public void onError() {
                           Picasso.with(context)
                                   .load(image1)
                                   .placeholder(R.drawable.placeholder )
                                   .into(viewHolder.image);
                       }
                   });
           if(item1.isSelect()) viewHolder.view.setBackgroundColor(context.getResources().getColor(R.color.grey));
       }catch (Exception e){

       }


    }
    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,status;
        public CircleImageView image;
        public View view;
        public int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            name=(TextView)itemView.findViewById(R.id.AllStudentName);
            image=(CircleImageView)itemView.findViewById(R.id.AllStudentImage);
            status=(TextView)itemView.findViewById(R.id.AllStudentStatus);
        }
    }
}

