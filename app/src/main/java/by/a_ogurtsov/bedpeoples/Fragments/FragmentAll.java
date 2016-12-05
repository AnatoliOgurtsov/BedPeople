package by.a_ogurtsov.bedpeoples.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import by.a_ogurtsov.bedpeoples.Adapters.MyAdapter;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskViewAll;
import by.a_ogurtsov.bedpeoples.R;

public class FragmentAll extends AbstractFragment{
    private static final int LAYOUT = R.layout.fragment_all;
    private Context context;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter adapter;
    private MyAsyncTaskViewAll myAsyncTaskAll;

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
        myAsyncTaskAll = new MyAsyncTaskViewAll(adapter, context);

        if (isOnline())
        myAsyncTaskAll.execute();
        else {Toast toast = Toast.makeText(this.getActivity(), "Отсутствует подключение к интернету\n    " +
                                                       "Пожалуйста, проверте настройки", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();}



    return view;

    }



    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public Context getContext() {
        return context;
    }

}
