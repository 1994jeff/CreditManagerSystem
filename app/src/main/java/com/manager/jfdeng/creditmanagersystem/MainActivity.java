package com.manager.jfdeng.creditmanagersystem;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.manager.jfdeng.creditmanagersystem.activity.ScoreManagerActivity;
import com.manager.jfdeng.creditmanagersystem.activity.StudentActivity;
import com.manager.jfdeng.creditmanagersystem.activity.mail.AddMailActivity;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStudent;
    private Button mScore;
    private Button mBackup;
    private Button mRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        mStudent = (Button) findViewById(R.id.student);
        mScore = (Button) findViewById(R.id.score);
        mBackup = (Button) findViewById(R.id.backup);
        mRank = (Button) findViewById(R.id.rank);

        mStudent.setOnClickListener(this);
        mScore.setOnClickListener(this);
        mBackup.setOnClickListener(this);
        mRank.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.student:
                startActivity(new Intent(MainActivity.this, StudentActivity.class));
                break;
            case R.id.score:
                startActivity(new Intent(MainActivity.this, ScoreManagerActivity.class));
                break;
            case R.id.backup:
                startActivity(new Intent(MainActivity.this, AddMailActivity.class));
                break;
            case R.id.rank:
                clear();
                break;
        }
    }

    private void clear() {
        deleteDatabase("system.db");
        Toast.makeText(this,"清除数据库成功！",Toast.LENGTH_SHORT).show();
    }
}
