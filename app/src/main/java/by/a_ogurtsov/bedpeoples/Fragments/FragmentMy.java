package by.a_ogurtsov.bedpeoples.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import by.a_ogurtsov.bedpeoples.Adapters.MyAdapter;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskViewAll;
import by.a_ogurtsov.bedpeoples.AsyncTasks.MyAsyncTaskViewMy;
import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;
import by.a_ogurtsov.bedpeoples.InputActivity;
import by.a_ogurtsov.bedpeoples.LoginActivity;
import by.a_ogurtsov.bedpeoples.R;

public class FragmentMy extends AbstractFragment{
    private static final int LAYOUT_fragment_my = R.layout.fragment_my;
    private static final int LAYOUT_login = R.layout.login;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private MyAdapter adapter;
    private TextView tv_temp;


    SharedPreferences pref_login;
    private static final String FILE_PREF_LOGIN = "my_login_pref";

    private String user;
    private String password;

    final int REQUEST_CODE_ACTIVITY_LOGIN = 1;
    final int REQUEST_CODE_ACTIVITY_INPUT = 2;





    public static FragmentMy getInstance(Context context) {
        Bundle args = new Bundle();
        FragmentMy fragment = new FragmentMy();
        fragment.setArguments(args);
        fragment.setContext(context);
        fragment.setTITLE(context.getString(R.string.my));

        return fragment;
    }

    public FragmentMy() {
    }

    @Override
    @Nullable
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                view = inflater.inflate(LAYOUT_fragment_my,container, false);
                tv_temp = (TextView) view.findViewById(R.id.tv_temp);
                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_fragment_my);
                layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
                recyclerView.setLayoutManager(layoutManager);
                /*adapter = new MyAdapter(mock_myData());
                recyclerView.setAdapter(adapter);

                MyAsyncTaskViewMy myAsyncTaskViewItem = new MyAsyncTaskViewMy(user, adapter);
                myAsyncTaskViewItem.execute();*/

                floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_fragment_my);
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    Intent intent;
                    @Override
                    public void onClick(View v) {
                        switch (Preferences_TRUE_ore_FALSE()){
                            case 0:
                                intent = new Intent(getContext(), LoginActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_ACTIVITY_LOGIN);
                                break;
                            case 1:
                                intent = new Intent(getContext(), InputActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_ACTIVITY_INPUT);
                                break;
                        }

                      /*  Face face1 = new Face();
                        face1.setId((long) 0);
                        face1.setName("Vashington");
                        face1.setCountry("USA");
                        face1.setCity("NY");
                        face1.setPhone("7654321");
                        face1.setWhatdidhedo("BedBoy");
                        face1.setUser("Lemas");
                        face1.setPassword("ljeans");
                        face1.setDate(new Date());
                        Snackbar.make(view,face1.getName(), Snackbar.LENGTH_INDEFINITE).show();
                        MyAsyncTaskInsertItem myAsyncTaskInsert = new MyAsyncTaskInsertItem(face1, adapter);
                        myAsyncTaskInsert.execute();*/
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


    public void setContext(Context context) {
        this.context = context;
    }
    @Override
    public Context getContext() {
        return context;
    }
    public int Preferences_TRUE_ore_FALSE(){
        pref_login = getContext().getSharedPreferences(FILE_PREF_LOGIN, Context.MODE_PRIVATE);
        int key = (pref_login.contains("PREF_USER")) ? 1 : 0;
        return key;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       switch (requestCode) {
           case REQUEST_CODE_ACTIVITY_LOGIN:
               switch (resultCode){
                   case 1:    //registration
                       tv_temp.setText(data.getStringExtra("user"));
                        int i =1;
                       for (Face face : MyAsyncTaskViewAll.facesAllBase) {
                               if (face.getUser().equals(data.getStringExtra("user"))) {
                               i = 0;
                               Toast.makeText(context, "User with this name already exists", Toast.LENGTH_SHORT).show();
                               break;
                           }
                       }
                       if (i == 1) {
                       save_login(data.getStringExtra("user"), data.getStringExtra("password"));
                       Toast.makeText(context, "Registration succesful", Toast.LENGTH_SHORT).show();
                   }

                       break;
                   case 2:    //login
                       tv_temp.setText(data.getStringExtra("password"));
                       FaceList faceList_sort = new FaceList();

                       for (Face face : MyAsyncTaskViewAll.facesAllBase){
                           if (face.getUser().equals(data.getStringExtra("user")) && face.getPassword().equals(data.getStringExtra("password")))
                           {
                               faceList_sort.add(face);
                               save_login(data.getStringExtra("user"), data.getStringExtra("password"));
                               Toast.makeText(context, "Login succesful", Toast.LENGTH_SHORT).show();
                           }
                       }
                       if (faceList_sort.isEmpty()) Toast.makeText(context, "Login ore password are incorrect", Toast.LENGTH_SHORT).show();
                       else{
                       adapter = new MyAdapter(faceList_sort);
                       recyclerView.setAdapter(adapter);
                       }

                       break;
               }
               break;
           case REQUEST_CODE_ACTIVITY_INPUT:
               tv_temp.setText("Input data");
               break;
        }
    }
}


