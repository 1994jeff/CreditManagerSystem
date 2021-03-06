package com.manager.jfdeng.creditmanagersystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.activity.score.DelScoreActivity;
import com.manager.jfdeng.creditmanagersystem.activity.score.DianMingActivity;
import com.manager.jfdeng.creditmanagersystem.activity.score.ScoreAddActivity;
import com.manager.jfdeng.creditmanagersystem.activity.score.SearchActivity;
import com.manager.jfdeng.creditmanagersystem.activity.score.UpdateScoreActivity;

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
                Intent intent = new Intent(ScoreManagerActivity.this, ScoreAddActivity.class);
                startActivity(intent);
                break;
            case R.id.stuModify:
                Intent intent2 = new Intent(ScoreManagerActivity.this, UpdateScoreActivity.class);
                startActivity(intent2);
                break;
            case R.id.stuSea:
                Intent intent3 = new Intent(ScoreManagerActivity.this, SearchActivity.class);
                startActivity(intent3);
                break;
            case R.id.back:
                Intent intent4 = new Intent(ScoreManagerActivity.this, DelScoreActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
