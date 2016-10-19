package by.a_ogurtsov.bedpeoples;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import by.a_ogurtsov.bedpeoples.Adapters.MyAdapter;
import by.a_ogurtsov.bedpeoples.Adapters.TabPagerFragmentAdapter;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskAll;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskDelete;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskInsert;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;

public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT = R.layout.main;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    private TabLayout tabLayout;


    private RecyclerView m_RecyclerView;
    private RecyclerView.LayoutManager m_LayoutManager;
    private FaceList myData;
    private MyAdapter m_adapter;
    private MyAsyncTaskAll myAsyncTaskAll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppThemeDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabLayout();

        m_RecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        m_LayoutManager = new LinearLayoutManager(this);
        m_RecyclerView.setLayoutManager(m_LayoutManager);
        m_adapter = new MyAdapter(mock_myData());
        m_RecyclerView.setAdapter(m_adapter);
        myAsyncTaskAll = new MyAsyncTaskAll(m_adapter);
        myAsyncTaskAll.execute();
        }

    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case (R.id.delete_item):{
                        long id = 8;
                        MyAsyncTaskDelete myAsyncTaskDelete = new MyAsyncTaskDelete(id, m_adapter);
                        myAsyncTaskDelete.execute();
                        break;
                    }
                }
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) drawerLayout.findViewById(R.id.navigation);
        navigationView.inflateMenu(R.menu.menu_navigation);

    }

    private void initTabLayout() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabPagerFragmentAdapter adapter = new TabPagerFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

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
public void insertInTable(View view){
    Face face1 = new Face();
    face1.setName("Bush");
    face1.setAdress("Vashington");
    face1.setPhone("123456789");
    Snackbar.make(view,face1.getName(), Snackbar.LENGTH_INDEFINITE).show();
    MyAsyncTaskInsert myAsyncTaskInsert = new MyAsyncTaskInsert(face1, m_adapter);
    myAsyncTaskInsert.execute();
}



}
