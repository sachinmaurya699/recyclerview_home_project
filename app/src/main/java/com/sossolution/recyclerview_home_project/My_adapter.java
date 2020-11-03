package com.sossolution.recyclerview_home_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public  class My_adapter extends RecyclerView.Adapter
{

    Context context;
    List<String> list_item;
    private final int VIEW_TYPE_ITEM=0;
    private final int VIEW_TYPE_LOADING=1;


    public  My_adapter(List<String> list_item)
    {

        this.list_item=list_item;
    }
    @Override
    public int getItemViewType(int position)
    {

        return list_item.get(position)==null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        if(viewType==VIEW_TYPE_ITEM)
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.my_design,parent,false);
            Viewholder_item v1=new Viewholder_item(view);
            return v1;
        }else
        {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.my_progressbar,parent,false);
            Viewholder_Loading v2=new Viewholder_Loading(view);
            return v2;

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        /*switch (holder.getItemViewType())
        {
            case VIEW_TYPE_ITEM:
                Viewholder_item viewholder_item= (Viewholder_item) holder;
                final String namev = list_item.get(position);

            case VIEW_TYPE_LOADING:
                Viewholder_Loading viewholder_loading= (Viewholder_Loading) holder;

        }*/

        if(holder instanceof Viewholder_item)
        {
            //populateItemRows((Viewholder_item) holder, position);

            populate((Viewholder_item) holder,position);

        }else if(holder instanceof Viewholder_Loading)
        {
            showLoadingView((Viewholder_Loading) holder, position);
        }





    }

    private void populate(Viewholder_item viewholder,int position)
    {

        String item=list_item.get(position);
        viewholder.textView.setText(item);
    }

    @Override
    public int getItemCount()
    {
        return list_item == null ? 0 : list_item.size();
    }

    class Viewholder_item extends RecyclerView.ViewHolder
    {
        TextView textView;
        public Viewholder_item(@NonNull View itemView)
        {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view);
        }
    }
    class Viewholder_Loading extends RecyclerView.ViewHolder
    {
        ProgressBar progressBar;
        public Viewholder_Loading(@NonNull View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.my_progressbar);
        }
    }
    private void populateItemRows(Viewholder_item viewHolder, int position)
    {

        String item = list_item.get(position);
        viewHolder.textView.setText(item);

    }
    private void showLoadingView(Viewholder_Loading viewHolder, int position)
    {
        //ProgressBar would be displayed

    }

}
