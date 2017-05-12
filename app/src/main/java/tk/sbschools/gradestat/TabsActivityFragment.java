package tk.sbschools.gradestat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 10018728 on 4/28/2017.
 */

public class TabsActivityFragment extends FragmentPagerAdapter {
    public TabsActivityFragment(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new overviewFragment();
            case 1:
                break;
            case 2:
                break;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "Overview";
        }
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
