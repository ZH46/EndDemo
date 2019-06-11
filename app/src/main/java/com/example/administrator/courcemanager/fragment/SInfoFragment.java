package com.example.administrator.courcemanager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.student.StudentAttendActivity;
import com.example.administrator.courcemanager.student.StudentScoreActivity;
import com.example.administrator.courcemanager.student.StudentSevaluaActivity;

public class SInfoFragment extends Fragment {

    private RelativeLayout rl1,rl2,rl3,rl4;
    public SInfoFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.view_information_student,container,false);
        rl1=view.findViewById(R.id.sattend);
        rl2=view.findViewById(R.id.sevalua);
        rl3=view.findViewById(R.id.sgold);
        rl4=view.findViewById(R.id.usualgold);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), StudentAttendActivity.class);
                startActivity(intent);
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), StudentSevaluaActivity.class);
                startActivity(intent);
            }
        });
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), StudentScoreActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
