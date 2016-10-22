package by.a_ogurtsov.bedpeoples.Fragments;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

public class AbstractFragment extends Fragment{
    private String TITLE;
    protected Context context;
    protected View view;

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
}
