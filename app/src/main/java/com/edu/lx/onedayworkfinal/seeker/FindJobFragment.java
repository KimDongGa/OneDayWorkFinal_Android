package com.edu.lx.onedayworkfinal.seeker;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.edu.lx.onedayworkfinal.R;
import com.edu.lx.onedayworkfinal.seeker.recycler_view.SeekerProjectListRecyclerViewAdapter;
import com.edu.lx.onedayworkfinal.util.volley.Base;
import com.edu.lx.onedayworkfinal.vo.ProjectVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

//일감 구하기 Fragment
public class FindJobFragment extends Fragment {

    SeekerMainActivity activity;

    RecyclerView projectListRecyclerView;
    SeekerProjectListRecyclerViewAdapter adapter;
    @Override
    public void onAttach (Context context) {
        super.onAttach(context);
        activity = (SeekerMainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.seeker_find_job_fragment,container,false);
        projectListRecyclerView = rootView.findViewById(R.id.projectListRecyclerView);
        return rootView;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView 의 layoutManager 세팅
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity.getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        projectListRecyclerView.setLayoutManager(layoutManager);

        //projectList 요청
        requestProjectList();

    }

    //projectList 요청
    private void requestProjectList () {
        //TODO 거리기반으로 요청이 가도록 구현
        String url = getResources().getString(R.string.url) + "getProjectList.do";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse (String response) {
                        processProjectResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse (VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                return super.getParams();
            }
        };

        request.setShouldCache(false);
        Base.requestQueue.add(request);
    }

    //서버로부터 받아온 projectList 를 RecyclerView 에 뿌려줌
    private void processProjectResponse (String response) {
        ProjectVO[] projectArray = Base.gson.fromJson(response,ProjectVO[].class);

        ArrayList<ProjectVO> items = new ArrayList<>(Arrays.asList(projectArray));

        //Adapter 할당
        adapter = new SeekerProjectListRecyclerViewAdapter(activity);
        adapter.setItems(items);
        projectListRecyclerView.setAdapter(adapter);
    }

}
