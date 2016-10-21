package by.a_ogurtsov.bedpeoples.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.a_ogurtsov.bedpeoples.R;

public class FragmentMy extends Fragment{
    private static final int LAYOUT = R.layout.fragment_my;
    private View view;

    public static FragmentMy getInstance() {
        Bundle args = new Bundle();
        FragmentMy fragment = new FragmentMy();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container, false);

        return view;

    }
}