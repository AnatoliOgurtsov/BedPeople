package by.a_ogurtsov.bedpeoples.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.a_ogurtsov.bedpeoples.Adapters.MyAdapter;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskAll;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.R;

public class FragmentAll extends AbstractFragment{
    private static final int LAYOUT = R.layout.fragment_all;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter adapter;
    private MyAsyncTaskAll myAsyncTaskAll;
    private FaceList myData;
    public static FragmentAll getInstance(Context context) {
        Bundle args = new Bundle();
        FragmentAll fragment = new FragmentAll();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTITLE(context.getString(R.string.all));
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_all);
        layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(mock_myData());
        recyclerView.setAdapter(adapter);
        myAsyncTaskAll = new MyAsyncTaskAll(adapter);
        myAsyncTaskAll.execute();



        return view;

    }

    public FaceList mock_myData() {
        myData = new FaceList();
        Face face1 = new Face();
        face1.setId((long) 1);
        face1.setName("Gitlerr");
        face1.setAdress("Berlin");
        face1.setPhone("666");
        Face face2 = new Face();
        face2.setId((long) 2);
        face2.setName("Stalinn");
        face2.setAdress("Moscow");
        face2.setPhone("555");
        myData.add(face1);
        myData.add(face2);
        return  myData;
    }



    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public Context getContext() {
        return context;
    }

}
