package com.manager.jfdeng.creditmanagersystem.activity.score;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.bean.Student;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;

import java.util.ArrayList;
import java.util.List;

public class DianMingActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner mSpinClass;
    private Spinner mSpinStu;
    private Spinner mSelect;
    private EditText mScore;
    private Button mOk;

    ArrayAdapter classAdapter,studentAdapter,selectAdapter;

    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;

    List<Student> mList = new ArrayList<>();
    List<String> mStringsClass = new ArrayList<>();
    List<String> mStringsStu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_ming);
        initView();
    }

    private void initView() {
        mSpinClass = (Spinner) findViewById(R.id.spinClass);
        mSpinStu = (Spinner) findViewById(R.id.spinStu);
        mSelect = (Spinner) findViewById(R.id.select);
        mScore = (EditText) findViewById(R.id.score);
        mOk = (Button) findViewById(R.id.ok);

        mOk.setOnClickListener(this);

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getReadableDatabase();

        classAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,getClassData());
        selectAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,getSelectData());
        studentAdapter = new ArrayAdapter<String>(DianMingActivity.this,android.R.layout.simple_list_item_multiple_choice,getStudentData(""));

        mSpinStu.setAdapter(studentAdapter);
        mSpinClass.setAdapter(classAdapter);
        mSelect.setAdapter(selectAdapter);

        mSpinClass.setSelection(0);
        mSpinClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> view, View view1, int i, long l) {

                studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> view) {

            }
        });
    }

    private String[] getSelectData() {
        return new String[]{"点名1","点名2","点名3","点名4","点名5"};
    }

    private String[] getStudentData(String className) {
        String[] strings = null;
        if(db.isOpen()){
            Student student = null;
            Cursor cursor = null;
            if(TextUtils.isEmpty(className))
            {
                cursor = db.rawQuery("select * from stu",null);
            }
            else{
                String sql = "select * from stu where class=?";
                cursor = db.rawQuery(sql,new String[]{className});
            }
            while (cursor!=null && cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String sno = cursor.getString(cursor.getColumnIndex("sno"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String aClass = cursor.getString(cursor.getColumnIndex("class"));
                mStringsStu.add(name);
            }
        }
        strings = new String[mStringsStu.size()];
        for(int i=0;i<mStringsStu.size();i++){
            strings[i] = mStringsStu.get(i);
        }
        return strings;
    }

    private String[] getClassData() {
        String[] strings = null;
        if(db.isOpen()){
            Student student = null;
            Cursor cursor = db.rawQuery("select * from stu",null);
            while (cursor!=null && cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String sno = cursor.getString(cursor.getColumnIndex("sno"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String aClass = cursor.getString(cursor.getColumnIndex("class"));
                student = new Student(id+"",sno,name,aClass);
                mList.add(student);
                mStringsClass.add(aClass);
            }
        }
        strings = new String[mStringsClass.size()];
        for(int i=0;i<mStringsClass.size();i++){
            strings[i] = mStringsClass.get(i);
        }
        return strings;
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
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
        String scoreString = mScore.getText().toString().trim();
        if (TextUtils.isEmpty(scoreString)) {
            Toast.makeText(this, "请输入成绩", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
