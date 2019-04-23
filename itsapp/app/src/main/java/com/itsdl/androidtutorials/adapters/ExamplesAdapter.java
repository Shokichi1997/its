package com.itsdl.androidtutorials.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.Example;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ExamplesAdapter extends RecyclerView.Adapter<ExamplesAdapter.MyViewHoder> {
    private List<Example> examples;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private View.OnClickListener onItemClickListener;

    public ExamplesAdapter(Context context, List<Example>examples) {
        this.mContext = context;
        this.examples = examples;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class MyViewHoder extends RecyclerView.ViewHolder{
        public TextView lblExampleItem;
        public ImageView imgExampleItem;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            this.lblExampleItem = itemView.findViewById(R.id.lblExampleItem);
            this.imgExampleItem = itemView.findViewById(R.id.imgExampleItem);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = mLayoutInflater.inflate(R.layout.list_item_examples,viewGroup,false);
        return new MyViewHoder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder myViewHoder, int i) {
        Example example = examples.get(i);
        Picasso.get()
                .load("https://itstutorials.000webhostapp.com/wp-content/uploads/"+example.getIcon())
                .error(R.drawable.ic_launcher_background)
                .into(myViewHoder.imgExampleItem);
        myViewHoder.lblExampleItem.setText(example.getName());
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }



}
