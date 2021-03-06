package com.edu.lx.onedayworkfinal.seeker.recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.lx.onedayworkfinal.R;
import com.edu.lx.onedayworkfinal.util.recycler_view.BaseRecyclerViewAdapter;
import com.edu.lx.onedayworkfinal.util.recycler_view.BaseViewHolder;
import com.edu.lx.onedayworkfinal.vo.ProjectJobListVO;

public class SeekerJobListRecyclerViewAdapter extends BaseRecyclerViewAdapter<ProjectJobListVO> {

    public SeekerJobListRecyclerViewAdapter (Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        return new SeekerProjectListViewHolder(inflateView(super.context, R.layout.seeker_find_job_item,viewGroup));
    }

    class SeekerProjectListViewHolder extends BaseViewHolder<ProjectJobListVO> {
        TextView jobName;
        TextView jobPay;
        TextView jobDate;
        TextView jobCount;

        public SeekerProjectListViewHolder (@NonNull View itemView) {
            super(itemView);
            jobName = itemView.findViewById(R.id.jobName);
            jobPay = itemView.findViewById(R.id.jobPay);
            jobDate = itemView.findViewById(R.id.jobDate);
            jobCount = itemView.findViewById(R.id.jobCount);
        }

        @Override
        public void setItem (ProjectJobListVO item) {

            jobName.setText(item.getJobName());
            jobPay.setText(String.valueOf(item.getJobPay()));
            jobDate.setText(item.getJobStartDate() + " - " + item.getJobEndDate());
            jobCount.setText(item.getJobCurrentCount() + " / " + item.getJobLimitCount());

        }

    }

}
