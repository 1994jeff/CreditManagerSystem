package com.manager.jfdeng.creditmanagersystem.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.manager.jfdeng.creditmanagersystem.R;

public class ScoreManagerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStuAdd;
    private Button mStuModify;
    private Button mStuSea;
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_manager);
        initView();
    }

    private void initView() {
        mStuAdd = (Button) findViewById(R.id.stuAdd);
        mStuModify = (Button) findViewById(R.id.stuModify);
        mStuSea = (Button) findViewById(R.id.stuSea);
        mBack = (Button) findViewById(R.id.back);

        mStuAdd.setOnClickListener(this);
        mStuModify.setOnClickListener(this);
        mStuSea.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stuAdd:

                break;
            case R.id.stuModify:

                break;
            case R.id.stuSea:

                break;
            case R.id.back:

                break;
        }
    }
}
