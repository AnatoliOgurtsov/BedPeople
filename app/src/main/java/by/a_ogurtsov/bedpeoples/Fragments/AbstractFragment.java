package by.a_ogurtsov.bedpeoples.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.Date;

import by.a_ogurtsov.bedpeoples.Entity.Face;
import by.a_ogurtsov.bedpeoples.Entity.FaceList;

public class AbstractFragment extends Fragment{
    private String TITLE;
    protected Context context;
    protected View view;
    private FaceList myData;

    public FaceList mock_myData() {
        myData = new FaceList();
        Face face1 = new Face();
        face1.setId((long) 0);
        face1.setName(" ");
        face1.setCountry(" ");
        face1.setCity(" ");
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

    protected boolean isOnline() {     //проверка подключния к интернету============================
        ConnectivityManager cm =
                (ConnectivityManager) getActivity()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }//=============================================================================================

}
