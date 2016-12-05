package by.a_ogurtsov.bedpeoples.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import by.a_ogurtsov.bedpeoples.Entity.Face;
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

    public MyAdapter() {
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
       // public TextView tv_id;
        public TextView tv_name;
        public TextView tv_country;
        public TextView tv_city;
        public TextView tv_phone;
       // public TextView tv_whatdidhedo;
       // public TextView tv_user;
       // public TextView tv_password;
       // public TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
         //   tv_id = (TextView) itemView.findViewById(R.id.id);
            tv_name = (TextView) itemView.findViewById(R.id.name);
            tv_country = (TextView) itemView.findViewById(R.id.country);
            tv_city = (TextView) itemView.findViewById(R.id.city);
            tv_phone = (TextView) itemView.findViewById(R.id.phone);
          //  tv_whatdidhedo = (TextView) itemView.findViewById(R.id.whatdidhedo);
          //  tv_user = (TextView) itemView.findViewById(R.id.user);
          //  tv_password = (TextView) itemView.findViewById(R.id.password);
          //  tv_date= (TextView) itemView.findViewById(R.id.date);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.tv_id.setText(m_myData.get(position).getId().toString());
        holder.tv_name.setText(m_myData.get(position).getName());
        holder.tv_country.setText(m_myData.get(position).getCountry());
        holder.tv_city.setText(m_myData.get(position).getCity());
        holder.tv_phone.setText(m_myData.get(position).getPhone());
        //holder.tv_whatdidhedo.setText(m_myData.get(position).getWhatdidhedo());
        //holder.tv_user.setText(m_myData.get(position).getUser());
        //holder.tv_password.setText(m_myData.get(position).getPassword());
        //holder.tv_date.setText( m_myData.get(position).getDate().toString());
   }

    @Override
    public int getItemCount() {
        return m_myData.size();
    }
}
