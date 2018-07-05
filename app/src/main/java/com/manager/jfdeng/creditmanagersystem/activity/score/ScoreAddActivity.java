package com.manager.jfdeng.creditmanagersystem.activity.score;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.activity.ScoreManagerActivity;

public class ScoreAddActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mStuAdd;
    private Button mStuModify;
    private Button mStuSea;
    private Button mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_add);
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
                Intent intent = new Intent(ScoreAddActivity.this, DianMingActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.stuModify:
                Intent intent2 = new Intent(ScoreAddActivity.this, DianMingActivity.class);
                intent2.putExtra("type","2");
                startActivity(intent2);
                break;
            case R.id.stuSea:
                Intent intent3 = new Intent(ScoreAddActivity.this, DianMingActivity.class);
                intent3.putExtra("type","3");
                startActivity(intent3);
                break;
            case R.id.back:
                onBackPressed();
                break;
        }
    }
}
