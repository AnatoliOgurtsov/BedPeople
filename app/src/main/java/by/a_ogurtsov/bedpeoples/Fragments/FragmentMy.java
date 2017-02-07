package by.a_ogurtsov.bedpeoples.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import by.a_ogurtsov.bedpeoples.Adapters.RecyclerViewAdapter;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskInsertItem;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskViewMy;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.InputActivity;
import by.a_ogurtsov.bedpeoples.LoginActivity;
import by.a_ogurtsov.bedpeoples.MainActivity;
import by.a_ogurtsov.bedpeoples.R;

import static android.app.Activity.RESULT_OK;

public class FragmentMy extends AbstractFragment{
    private static final int LAYOUT_fragment_my = R.layout.fragment_my;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private RecyclerViewAdapter adapter;
    private TextView tv_temp;

    Snackbar snackbar;
    View.OnClickListener snackbarOnClickListener;

    SharedPreferences pref_login;
    private static final String FILE_PREF_LOGIN = "my_login_pref";

    private String user;
    private String password;

    final int REQUEST_CODE_ACTIVITY_LOGIN = 1;
    final int REQUEST_CODE_ACTIVITY_INPUT = 2;

    private Intent intentLoginActivity;
    private Intent intentInputActivity;

    private MyAsyncTaskInsertItem myAsyncTaskInsertItem;
    private MyAsyncTaskViewMy myasyncTaskViewMy;

    public static FaceList faceListMy;


    public static FragmentMy getInstance(Context context) {
        Bundle args = new Bundle();
        FragmentMy fragment = new FragmentMy();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTITLE(context.getString(R.string.my));
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("status", "FRAGMENT  MY  ONCREATE " + this);
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d("status", "FRAGMENT  MY  ONCREATE VIEW " + this);
                view = inflater.inflate(LAYOUT_fragment_my,container, false);
                tv_temp = (TextView) view.findViewById(R.id.tv_temp);
                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_my);
                layoutManager = new LinearLayoutManager(context);
                recyclerView.setLayoutManager(layoutManager);



      //=================================================================

        if (Preferences_TRUE_ore_FALSE())  // если преференсы уже есть то выводим своих лиц
        {
            faceListMy = new FaceList();
            for (Face face : MainActivity.databaseAll) {

                if (face.getUser().equals(loadLoginFromPref())) {
                    faceListMy.add(face);
                }
            }
            adapter = new RecyclerViewAdapter(faceListMy);
            recyclerView.setAdapter(adapter);
        }

        else  //Toast.makeText(context, "Добавьте лицо", Toast.LENGTH_SHORT).show();
        {snackbar = Snackbar.make(view, "Please add new bedman, press + ", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", snackbarOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { snackbar.dismiss(); }                });
        snackbar.show();}
      //==================================================================

                floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_fragment_my);
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Preferences_TRUE_ore_FALSE())
                        {
                            intentInputActivity = new Intent(context, InputActivity.class);
                            startActivityForResult(intentInputActivity, REQUEST_CODE_ACTIVITY_INPUT);}
                        else {
                            intentLoginActivity = new Intent(context, LoginActivity.class);
                            startActivityForResult(intentLoginActivity, REQUEST_CODE_ACTIVITY_LOGIN);
                        }
                    }
                });

        return view;
    }

    private void save_login(String user, String password){
        pref_login = getContext().getSharedPreferences("my_login_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref_login.edit();
        editor.putString("PREF_USER", user);
        editor.putString("PREF_PASSWORD", password);
        editor.commit();
}
    private String loadLoginFromPref(){
        pref_login = getContext().getSharedPreferences("my_login_pref", Context.MODE_PRIVATE);
        return pref_login.getString("PREF_USER", "");
    }
    private String loadPasswordFromPref(){
        pref_login = getContext().getSharedPreferences("my_login_pref", Context.MODE_PRIVATE);
        return pref_login.getString("PREF_PASSWORD", "");
    }

    public boolean Preferences_TRUE_ore_FALSE(){
        pref_login = getContext().getSharedPreferences(FILE_PREF_LOGIN, Context.MODE_PRIVATE);
        return pref_login.contains("PREF_USER"); //  то же самое что и pref_login.contains("PREF_USER") ? true : false;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
       switch (requestCode) {
           case REQUEST_CODE_ACTIVITY_LOGIN:
               switch (resultCode){
                   case 1:    //registration
                       tv_temp.setText(data.getStringExtra("user"));
                        int i =1;
                       for (Face face : MainActivity.databaseAll) {
                               if (face.getUser().equals(data.getStringExtra("user"))) {
                               i = 0;
                               break;
                           }
                       }
                       if (i == 1) {
                       save_login(data.getStringExtra("user"), data.getStringExtra("password"));
                       Toast.makeText(context, "Registration succesful", Toast.LENGTH_SHORT).show();
                   } else {
                           Toast.makeText(context, "User with this name already exists", Toast.LENGTH_LONG).show();
                           startActivityForResult(intentLoginActivity, REQUEST_CODE_ACTIVITY_LOGIN);
                       }
                       break;

                   case 2:    //login
                       tv_temp.setText(data.getStringExtra("password"));
                       faceListMy = new FaceList();

                       for (Face face : MainActivity.databaseAll){
                           if (face.getUser().equals(data.getStringExtra("user")) && face.getPassword().equals(data.getStringExtra("password")))
                           {
                               faceListMy.add(face);
                           }
                       }
                       if (faceListMy.isEmpty()) {
                           startActivityForResult(intentLoginActivity, REQUEST_CODE_ACTIVITY_LOGIN);
                           Toast.makeText(context, "Login ore password are incorrect", Toast.LENGTH_LONG).show();
                       }
                       else{
                           Toast.makeText(context, "Login succesful", Toast.LENGTH_SHORT).show();
                           save_login(data.getStringExtra("user"), data.getStringExtra("password"));

                           adapter = new RecyclerViewAdapter(faceListMy);
                           recyclerView.setAdapter(adapter);
                       }

                       break;
               }
               break;
           case REQUEST_CODE_ACTIVITY_INPUT:

                   tv_temp.setText("Input data!!!!!!!!!!!!!");
                   Face face = new Face();
                   face.setName(data.getStringExtra("name"));
                   face.setSurname(data.getStringExtra("surname"));
                   face.setCountry(data.getStringExtra("country"));
                   face.setCity(data.getStringExtra("city"));
                   face.setAddress(data.getStringExtra("address"));
                   face.setPhone(data.getStringExtra("phone"));
                   face.setWhatdidhedo(data.getStringExtra("whatDidHeDo"));
                   face.setUser(pref_login.getString("PREF_USER", ""));
                   face.setPassword(pref_login.getString("PREF_PASSWORD", ""));
                   face.setDate(Calendar.getInstance().getTime());

                   faceListMy.add(face);
                   adapter.setM_myData(faceListMy);
                   recyclerView.setAdapter(adapter);
                   myAsyncTaskInsertItem = new MyAsyncTaskInsertItem(face);
                   myAsyncTaskInsertItem.execute();
               break;
        }
        }else return;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_my, menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("status", "FRAGMENT  MY  ONATTACH " + this);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("status", "FRAGMENT  MY   onActivityCreated");
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d("status", "FRAGMENT  MY  ONSTART " + this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("status", "FRAGMENT  MY  ONRESUME " + this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("status", "FRAGMENT  MY  ONPAUSE " + this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("status", "FRAGMENT  MY  ONSTOP " + this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("status", "FRAGMENT  MY  ONDESTROYVIEW " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("status", "FRAGMENT  MY  ONDESTROY " + this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("status", "FRAGMENT  MY  ONDETACH " + this);
    }
}


