package com.manager.jfdeng.creditmanagersystem.activity.student;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.bean.Student;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;

public class UpdateStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mName;
    private TextView mInfo;
    private Button mQuery;
    private Button mDel;

    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;

    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del);
        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.name);
        mInfo = (TextView) findViewById(R.id.info);
        mQuery = (Button) findViewById(R.id.query);
        mDel = (Button) findViewById(R.id.del);
        mDel.setText("立即更新");

        mQuery.setOnClickListener(this);
        mDel.setOnClickListener(this);

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getReadableDatabase();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                submit();
                break;
            case R.id.del:
                if(mStudent!=null)
                {
                    Intent intent = new Intent(UpdateStudentActivity.this,UpdateDetialActivity.class);
                    intent.putExtra("sno",mStudent.getsNo());
                    intent.putExtra("name",mStudent.getName());
                    intent.putExtra("class",mStudent.getsClass());
                    intent.putExtra("id",mStudent.getId());
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "请先查看是否存在此学生！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void submit() {
        // validate
        String nameString = mName.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "查询条件不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String sql = "select * from stu where sno=?";
        Cursor cursor = db.rawQuery(sql,new String[]{nameString});
        if(cursor!=null)
        {
            while (cursor.moveToNext())
            {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String sno = cursor.getString(cursor.getColumnIndex("sno"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String aClass = cursor.getString(cursor.getColumnIndex("class"));
                mInfo.setText("结果:学号 "+sno+",姓名 "+name+"，班级 "+aClass);
                mStudent = new Student(id+"",sno,name,aClass);
            }
        }
        if(mStudent==null){
            mInfo.setText("结果:查无此人！");
        }


    }
}
