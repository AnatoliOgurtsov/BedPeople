package by.a_ogurtsov.bedpeoples.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.HashMap;
import java.util.Map;

import by.a_ogurtsov.bedpeoples.Fragments.AbstractFragment;
import by.a_ogurtsov.bedpeoples.Fragments.FragmentAll;
import by.a_ogurtsov.bedpeoples.Fragments.FragmentMy;

public class TabPagerFragmentAdapter extends FragmentPagerAdapter{
    private Map <Integer, AbstractFragment> tabs;
    private Context context;
    public TabPagerFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        inittabs(context);

    }

    @Override
    public Fragment getItem(int position) {
       return tabs.get(position);
    }

    @Override
    public int getCount() {
        return tabs.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabs.get(position).getTITLE();
    }

    private void inittabs(Context context) {
        tabs = new HashMap<>();
        tabs.put(0, FragmentAll.getInstance(context));
        tabs.put(1, FragmentMy.getInstance(context));
          }
}
