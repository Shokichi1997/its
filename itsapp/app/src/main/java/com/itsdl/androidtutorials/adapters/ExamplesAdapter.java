package com.itsdl.androidtutorials.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.Example;

import java.util.List;

public class ExamplesAdapter extends RecyclerView.Adapter<ExamplesAdapter.MyViewHoder> {
    private List<Example> examples;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ExamplesAdapter(Context context, List<Example>examples) {
        this.mContext = context;
        this.examples = examples;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHoder extends RecyclerView.ViewHolder{
        public TextView lblExampleItem;
        public ImageView imgExampleItem;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            this.lblExampleItem = itemView.findViewById(R.id.lblExampleItem);
            this.imgExampleItem = itemView.findViewById(R.id.imgExampleItem);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"Hello",Toast.LENGTH_SHORT).show();
                }
            });
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
        myViewHoder.imgExampleItem.setImageResource(example.getIcon());
        myViewHoder.lblExampleItem.setText(example.getName());
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }


}
