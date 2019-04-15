package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.Functions_adapter;
import com.itsdl.androidtutorials.utils.FunctionUser;

import java.util.ArrayList;

public class FunctionsFragment extends Fragment {
    private GridView gridFunction;
    private Toolbar toolbar;
    DrawerLayout drawer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_functions,container,false);

        gridFunction = root.findViewById(R.id.gridFunction);

        ArrayList<FunctionUser> function = new ArrayList<>();
        function.add(new FunctionUser(1,"Learning",true,R.drawable.learning));
        function.add(new FunctionUser(2,"Quiz",true,R.drawable.quiz));
        function.add(new FunctionUser(3,"Manager student",true,R.drawable.manager_student));
        function.add(new FunctionUser(4,"Introduce",true,R.drawable.introduce));
        function.add(new FunctionUser(5,"Logout",true,R.drawable.logout));

        ArrayAdapter adapter = new Functions_adapter(root.getContext(),function);
        gridFunction.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridFunction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        Fragment chapterLessonFragment = new ChapterLessonFragment();
                        showFragment(chapterLessonFragment,"CHAPTER");
                        break;
                    case 1:
                        Fragment testLessonFragment = new SelectLessonTestFragment();
                        showFragment(testLessonFragment,"SELECT_LESSON");
                        break;
                    case 2:
                        //Todo
                        Fragment fragmentStudent = new StudentManagementFragment();
                        showFragment(fragmentStudent,"STUDENT_MANAGER");
                        break;
                    case 3:
                        //Todo
                        Fragment a = new UserProfileFragment();
                        showFragment(a,"USER_PROFILE");
                        break;
                    case 4:
                        if (getFragmentManager() != null) {
                            Fragment loginFragment = getFragmentManager().findFragmentByTag("LOGIN");
                            if(loginFragment!=null){
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.replace(R.id.frContainer,loginFragment,"LOGIN")
                                        .addToBackStack(null)
                                        .commit();
                            }
                            else {
                                LoginFragment fragment = new LoginFragment();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction transaction = fragmentManager.beginTransaction();
                                transaction.replace(R.id.frContainer,fragment,"LOGIN")
                                        .addToBackStack(null)
                                        .commit();
                            }
                        }
                        break;
                    default:
                        //TODO
                        break;
                }



            }
        });
    }

    private void showFragment(Fragment fragment,String tag) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frContainer,fragment,tag)
                .addToBackStack(null)
                .commit();
    }


}
