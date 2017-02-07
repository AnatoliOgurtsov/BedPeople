package by.a_ogurtsov.bedpeoples;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import by.a_ogurtsov.bedpeoples.Adapters.TabPagerFragmentAdapter;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.Interfaces.Callbacks;

public class MainActivity extends AppCompatActivity {
    private static final int MAINLAYOUT = R.layout.main;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    final int REQUESTCODESTARTACTIVITY = 55;

    public static FaceList databaseAll;

    //menu_fragment_all

    public static final int SEARCH = 101;
    public static final int REFRESH = 102;
    //



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//==========================internet?????
        if (!isOnline())
        {
            Toast toast = Toast.makeText(this, "Отсутствует подключение к интернету\n    " +
                    "Пожалуйста, проверте настройки", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
//==========================internet?????
        else {
            setTheme(R.style.AppThemeDefault);
            Log.d("status", "MAINACTIVITY ONCREATE" + this);

            Intent intent = new Intent(this, StartActivity.class);
            startActivityForResult(intent, REQUESTCODESTARTACTIVITY);

        } //===end else
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODESTARTACTIVITY) {
                setContentView(MAINLAYOUT);
                initToolbar();
                initNavigationView();
                initTabLayout();
            } else return;
        }
    }




    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
       /* toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
        });*/

        //toolbar.inflateMenu(R.menu_fragment_all.menu_fragment_all);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigationView_open, R.string.navigationView_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) drawerLayout.findViewById(R.id.navigation);
        navigationView.inflateMenu(R.menu.menu_navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case (R.id.all):
                        showTabAll();
                        break;
                    case (R.id.my):
                        showTabMy();
                        break;
                }
                return true;
            }
        });
    }

    private void initTabLayout() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabPagerFragmentAdapter adapter = new TabPagerFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void showTabAll() {
        viewPager.setCurrentItem(Constants.OTHERCONST.TAB1);
    }

    private void showTabMy() {
        viewPager.setCurrentItem(Constants.OTHERCONST.TAB2);
    }



 /* @Override
    public boolean onCreateOptionsMenu(Menu menu_fragment_all) {

            menu_fragment_all.add(Menu.NONE, SEARCH, Menu.NONE, R.string.search).setIcon(R.drawable.magnify);
            menu_fragment_all.add(Menu.NONE, REFRESH, Menu.NONE, R.string.refresh).setIcon(R.drawable.refresh);

         return super.onCreateOptionsMenu(menu_fragment_all);
    }*/




    @Override
    protected void onStart() {
        super.onStart();
        Log.d("status", "MAINACTIVITY ONSTART" + this);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("status", "MAINACTIVITY ONSRETART" + this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("status", "MAINACTIVITY ONSTOP" + this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("status", "MAINACTIVITY ONPAUSE" + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("status", "MAINACTIVITY ONDESTROY" + this);
    }
    protected boolean isOnline() {     //проверка подключния к интернету============================
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }//=============================================================================================


}
