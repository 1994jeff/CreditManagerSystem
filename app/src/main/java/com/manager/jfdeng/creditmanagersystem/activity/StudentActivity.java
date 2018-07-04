package com.manager.jfdeng.creditmanagersystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.manager.jfdeng.creditmanagersystem.MainActivity;
import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.activity.student.AddStudentActivity;
import com.manager.jfdeng.creditmanagersystem.activity.student.DelActivity;
import com.manager.jfdeng.creditmanagersystem.activity.student.SearchStudentActivity;
import com.manager.jfdeng.creditmanagersystem.activity.student.UpdateStudentActivity;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStuAdd;
    private Button mStuModify;
    private Button mStuSea;
    private Button mStuDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        initView();
    }

    private void initView() {
        mStuAdd = (Button) findViewById(R.id.stuAdd);
        mStuModify = (Button) findViewById(R.id.stuModify);
        mStuSea = (Button) findViewById(R.id.stuSea);
        mStuDel = (Button) findViewById(R.id.stuDel);

        mStuAdd.setOnClickListener(this);
        mStuModify.setOnClickListener(this);
        mStuSea.setOnClickListener(this);
        mStuDel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stuAdd:
                startActivity(new Intent(StudentActivity.this, AddStudentActivity.class));
                break;
            case R.id.stuModify:
                startActivity(new Intent(StudentActivity.this, UpdateStudentActivity.class));
                break;
            case R.id.stuSea:
                startActivity(new Intent(StudentActivity.this, SearchStudentActivity.class));
                break;
            case R.id.stuDel:
                startActivity(new Intent(StudentActivity.this, DelActivity.class));
                break;
        }
    }
}
