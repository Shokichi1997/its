package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.ContentMainAdapter;
import com.itsdl.androidtutorials.utils.NonSwipeableViewPager;

public class ContentMainFragment extends Fragment {
    private NonSwipeableViewPager vpContent;
    private Toolbar toolbar;
    private TabLayout tabContent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_content_main,container,false);

        addControl(root);
        return root;
    }

    /**
     * addControl: add controls with layout
     * @param root view contain control in layout*/
    private void addControl(View root) {
        //add tool bar
        toolbar = root.findViewById(R.id.add_content_toolbar);
        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Learning");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        vpContent = root.findViewById(R.id.vpContent);
        tabContent = root.findViewById(R.id.tabContent);
        ContentMainAdapter adapter = new ContentMainAdapter(getActivity().getSupportFragmentManager());
        vpContent.setAdapter(adapter);
        vpContent.setOffscreenPageLimit(2);
        tabContent.setupWithViewPager(vpContent);
    }

}
