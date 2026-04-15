package in.sunilpaulmathew.sCommon.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 13, 2021
 */
public class sPagerAdapter extends FragmentStateAdapter {

    private final List<PageItem> pages = new ArrayList<>();

    public sPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return pages.get(position).fragment;
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public void addFragment(@NonNull Fragment fragment, @NonNull String title) {
        pages.add(new PageItem(fragment, title));
    }

    public CharSequence getPageTitle(int position) {
        return pages.get(position).title;
    }

    private static class PageItem {
        final Fragment fragment;
        final String title;

        PageItem(Fragment fragment, String title) {
            this.fragment = fragment;
            this.title = title;
        }
    }

}