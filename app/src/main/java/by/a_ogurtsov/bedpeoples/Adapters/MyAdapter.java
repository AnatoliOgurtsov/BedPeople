package by.a_ogurtsov.bedpeoples.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.R;

public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{
    private FaceList m_myData;

    public void setM_myData(FaceList m_myData) {
        this.m_myData = m_myData;

    }

    public MyAdapter(FaceList myData) {
        m_myData = myData;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_id;
        public TextView tv_name;
        public TextView tv_adress;
        public TextView tv_phone;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_id = (TextView) itemView.findViewById(R.id.id);
            tv_name = (TextView) itemView.findViewById(R.id.name);
            tv_adress = (TextView) itemView.findViewById(R.id.adress);
            tv_phone = (TextView) itemView.findViewById(R.id.phone);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_id.setText(m_myData.get(position).getId().toString());
        holder.tv_name.setText(m_myData.get(position).getName());
        holder.tv_adress.setText(m_myData.get(position).getAdress());
        holder.tv_phone.setText(m_myData.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return m_myData.size();
    }
}
