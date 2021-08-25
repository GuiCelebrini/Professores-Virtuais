package com.android.guicelebrini.professoresvirtuais.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.guicelebrini.professoresvirtuais.R;
import com.android.guicelebrini.professoresvirtuais.model.App;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterRecyclerApps extends RecyclerView.Adapter<AdapterRecyclerApps.MyViewHolder> {

    private List<App> appsList;

    public AdapterRecyclerApps(List<App> appsList){
        this.appsList = appsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apps_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterRecyclerApps.MyViewHolder holder, int position) {
        App app = appsList.get(position);
        holder.set(app);
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textDev;
        ImageView imageApp;

        public MyViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textAppItemName);
            textDev = itemView.findViewById(R.id.textAppItemDev);
            imageApp = itemView.findViewById(R.id.imageAppItem);

        }

        public void set(App app){
            this.textName.setText(app.getName());
            this.textDev.setText(app.getDeveloper());
            Picasso.get().load(app.getImagem()).into(this.imageApp);
        }
    }
}
