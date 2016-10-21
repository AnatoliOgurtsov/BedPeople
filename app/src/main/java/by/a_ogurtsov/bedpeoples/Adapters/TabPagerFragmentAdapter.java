package by.a_ogurtsov.bedpeoples.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import by.a_ogurtsov.bedpeoples.Fragments.FragmentAll;
import by.a_ogurtsov.bedpeoples.Fragments.FragmentMy;

public class TabPagerFragmentAdapter extends FragmentPagerAdapter{
    private String[] tabs;
    public TabPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{
                "Показать всех",
                "Показать моих"
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
               return FragmentAll.getInstance();
            case 1:
                return FragmentMy.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }
}
