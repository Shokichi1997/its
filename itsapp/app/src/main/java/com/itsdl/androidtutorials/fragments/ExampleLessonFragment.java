package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.ExamplesAdapter;
import com.itsdl.androidtutorials.networks.GetExamplesRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.Example;
import com.itsdl.androidtutorials.utils.Global;
import com.itsdl.androidtutorials.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExampleLessonFragment extends Fragment {
    private RecyclerView recExamples;
    private ExamplesAdapter adapter;
    private List<Example> examples;
    private ProgressBar progressExample;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = ( RecyclerView.ViewHolder ) v.getTag();
            int position = viewHolder.getAdapterPosition();
            Example example = examples.get(position);
            int example_id = example.getId();

            Toast.makeText(getContext(),"You click "+position+" has example_id = "+example_id,Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment fragment = new ExampleContentFragment();
            Bundle args = new Bundle();
            args.putInt("example_id",example_id);
            fragment.setArguments(args);
            transaction
                    .add(R.id.frContainer,fragment, Global.EXAMPLE_CONTENT)
                    .addToBackStack(null)
                    .commit();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_examples,container,false);
        addControls(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handleRequest();
    }

    private void addControls(View root) {
        examples = new ArrayList<>();
        recExamples = root.findViewById(R.id.recExample);
        recExamples.setHasFixedSize(true);
        progressExample = root.findViewById(R.id.progressExample);
        progressExample.setVisibility(View.INVISIBLE);
    }

    private void handleRequest(){
        Map<String, String> parameter = new HashMap<>();
        progressExample.setVisibility(View.VISIBLE);
        GetExamplesRequest request = new GetExamplesRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if(obj!=null){
                    Result res = (Result) obj;
                    if(res.getError()==0){
                        Object examples = res.getData();
                        prepareListExample(examples);
                    }
                    else {
                        Toast.makeText(getContext(),"Add score not successfully!",Toast.LENGTH_LONG).show();
                    }
                }
                progressExample.setVisibility(View.GONE);
            }
        });
        request.execute(parameter);
    }

    private void prepareListExample(Object object) {
        examples = (ArrayList<Example>)object;
        adapter = new ExamplesAdapter(getContext(),examples);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recExamples.setLayoutManager(layoutManager);
        recExamples.setItemAnimator(new DefaultItemAnimator());
        recExamples.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);
    }

}
