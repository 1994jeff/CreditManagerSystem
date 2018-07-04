package com.manager.jfdeng.creditmanagersystem.activity.student;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;

public class UpdateDetialActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSNo;
    private EditText mName;
    private EditText mSClass;
    private Button mOk;

    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detial);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private void initView() {
        mSNo = (EditText) findViewById(R.id.sNo);
        mName = (EditText) findViewById(R.id.name);
        mSClass = (EditText) findViewById(R.id.sClass);
        mOk = (Button) findViewById(R.id.ok);

        mOk.setOnClickListener(this);

        Intent intent = getIntent();
        String sno = intent.getStringExtra("sno");
        String name = intent.getStringExtra("name");
        String aClass = intent.getStringExtra("class");
        id = intent.getStringExtra("id");
        mSNo.setText(sno);
        mName.setText(name);
        mSClass.setText(aClass);

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getWritableDatabase();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String sNoString = mSNo.getText().toString().trim();
        if (TextUtils.isEmpty(sNoString)) {
            Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show();
            return;
        }

        String nameString = mName.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String sClassString = mSClass.getText().toString().trim();
        if (TextUtils.isEmpty(sClassString)) {
            Toast.makeText(this, "请输入班级", Toast.LENGTH_SHORT).show();
            return;
        }

        //  validate success, update
        if(db.isOpen())
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name",nameString);
            contentValues.put("sno",sNoString);
            contentValues.put("class",sClassString);
            db.update("stu", contentValues,"id=?",new String[]{id});
            Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show();
        }

    }
}
