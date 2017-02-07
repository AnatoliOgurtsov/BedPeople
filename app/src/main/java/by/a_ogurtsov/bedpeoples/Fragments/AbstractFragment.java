package by.a_ogurtsov.bedpeoples.Fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Date;

import by.a_ogurtsov.bedpeoples.Adapters.RecyclerViewAdapter;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;

public class AbstractFragment extends Fragment{
    private String TITLE;
    protected Context context;
    protected View view;
    private FaceList myData;
    private RecyclerViewAdapter adapter;

    public FaceList mock_myData() {
        myData = new FaceList();
        Face face1 = new Face();
        face1.setId((long) 0);
        face1.setName(" ");
        face1.setSurname(" ");
        face1.setCountry(" ");
        face1.setCity(" ");
        face1.setAddress(" ");
        face1.setPhone(" ");
        face1.setWhatdidhedo(" ");
        face1.setUser(" ");
        face1.setPassword(" ");
        face1.setDate(new Date());
        myData.add(face1);

        return  myData;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void setMyAdapter (RecyclerView recyclerView, FaceList faceList){
        adapter = new RecyclerViewAdapter(faceList);
        recyclerView.setAdapter(adapter);
    }
}
