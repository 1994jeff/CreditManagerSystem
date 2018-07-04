package com.manager.jfdeng.creditmanagersystem.activity.student;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;
import com.manager.jfdeng.creditmanagersystem.utils.MyApplication;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {


    private Spinner mSpinClass;
    private Spinner mSpinStu;
    private EditText mSNo;
    private EditText mName;
    private EditText mSClass;
    private Button mOk;

    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initView();

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getWritableDatabase();

    }

    private void initView() {
        mSpinClass = (Spinner) findViewById(R.id.spinClass);
        mSpinStu = (Spinner) findViewById(R.id.spinStu);
        mSNo = (EditText) findViewById(R.id.sNo);
        mName = (EditText) findViewById(R.id.name);
        mSClass = (EditText) findViewById(R.id.sClass);
        mOk = (Button) findViewById(R.id.ok);

        mOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                submit();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
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

        String sql = "insert into stu(sno,name,class) values('"+sNoString+"','"+nameString+"','"+sClassString+"')";
        db.execSQL(sql);
        Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
        mSNo.setText("");
        mName.setText("");
        mSClass.setText("");
    }
}
