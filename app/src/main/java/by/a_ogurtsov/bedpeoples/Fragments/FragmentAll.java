package by.a_ogurtsov.bedpeoples.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskViewAll;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.Interfaces.Callbacks;
import by.a_ogurtsov.bedpeoples.MainActivity;
import by.a_ogurtsov.bedpeoples.R;

public class FragmentAll extends AbstractFragment {
    private static final int LAYOUT = R.layout.fragment_all;
    private Context context;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private MyAsyncTaskViewAll myAsyncTaskAll;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Snackbar snackbar;

    private Callbacks callbacks = new Callbacks() {
        @Override
        public void returnDatabase(FaceList dataBase) {
            MainActivity.databaseAll = dataBase;
            setMyAdapter(recyclerView, dataBase);
            swipeRefreshLayout.setRefreshing(false);
            snackbar = Snackbar.make(view, getResources().getString(R.string.REFRESHDATA), Snackbar.LENGTH_SHORT);
            snackbar.show();
        }
    };


    public static FragmentAll getInstance(Context context) {
        Bundle args = new Bundle();
        FragmentAll fragment = new FragmentAll();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTITLE(context.getString(R.string.all));
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlue, R.color.colorGreen, R.color.colorYellow, R.color.colorRed);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_all);
        layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        setMyAdapter(recyclerView, MainActivity.databaseAll);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                myAsyncTaskAll = new MyAsyncTaskViewAll(callbacks);
                myAsyncTaskAll.execute();

            }
        });

    return view;
    }

    //==============================menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_all, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
//===============================menu

}
