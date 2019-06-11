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
import com.example.administrator.courcemanager.teacher.TeacherAttendActivity;
import com.example.administrator.courcemanager.teacher.TeacherScoreActivity;
import com.example.administrator.courcemanager.teacher.TeacherSevaluaActivity;

public class TInfoFragment extends Fragment {
    private RelativeLayout rl1,rl2,rl3;

    public TInfoFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.view_information_teacher,container,false);
        rl1=view.findViewById(R.id.tattend);
        rl2=view.findViewById(R.id.tevalua);
        rl3=view.findViewById(R.id.tgold);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TeacherAttendActivity.class);
                startActivity(intent);
            }
        });
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TeacherSevaluaActivity.class);
                startActivity(intent);
            }
        });
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TeacherScoreActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
