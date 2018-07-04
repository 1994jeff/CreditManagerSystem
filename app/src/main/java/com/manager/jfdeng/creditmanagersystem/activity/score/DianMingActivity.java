package com.manager.jfdeng.creditmanagersystem.activity.score;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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

    String[] classString;
    String[] stuString;
    String[] selectString;

    String type;//录入成绩类型  1点名，2作业，3上机

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_ming);
        initView();
    }

    private void initView() {

        type = getIntent().getStringExtra("type");

        mSpinClass = (Spinner) findViewById(R.id.spinClass);
        mSpinStu = (Spinner) findViewById(R.id.spinStu);
        mSelect = (Spinner) findViewById(R.id.select);
        mScore = (EditText) findViewById(R.id.score);
        mOk = (Button) findViewById(R.id.ok);

        mOk.setOnClickListener(this);

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getReadableDatabase();

        classString = getClassData();
        stuString = getStudentData("");
        selectString = getSelectData();

        classAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classString);
        selectAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,selectString);
        studentAdapter = new ArrayAdapter<String>(DianMingActivity.this,android.R.layout.simple_list_item_1,stuString);

        mSpinStu.setAdapter(studentAdapter);
        mSpinClass.setAdapter(classAdapter);
        mSelect.setAdapter(selectAdapter);

        mSpinClass.setSelection(0);
        mSpinClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> view, View view1, int i, long l) {
                stuString = getStudentData(classAdapter.getItem(i).toString());
                studentAdapter = new ArrayAdapter<String>(DianMingActivity.this,android.R.layout.simple_list_item_1,stuString);
                mSpinStu.setAdapter(studentAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> view) {

            }
        });
    }

    private String[] getSelectData() {
        if(type.equals("1"))
        {
            return new String[]{"请选择分数类别","点名1","点名2","点名3","点名4","点名5"};
        }
        else if(type.equals("2")){
            return new String[]{"请选择分数类别","作业1","作业2","作业3","作业4","作业5"};
        }else if(type.equals("3")){
            return new String[]{"请选择分数类别","上机1","上机2","上机3","上机4","上机5"};
        }else {
            type = "1";
            return new String[]{"请选择分数类别","点名1","点名2","点名3","点名4","点名5"};
        }
    }

    private String[] getStudentData(String className) {
        mStringsStu.clear();
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
        strings = new String[mStringsStu.size()+1];
        strings[0] = "请选择学生";
        for(int i=0;i<mStringsStu.size();i++){
            strings[i+1] = mStringsStu.get(i);
        }
        return strings;
    }

    private String[] getClassData() {
        String[] strings = null;
        if(db.isOpen()){
            Student student = null;
            Cursor cursor = db.rawQuery("select * from stu group by class",null);
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
        strings = new String[mStringsClass.size()+1];
        strings[0] = "请选择班级";
        for(int i=0;i<mStringsClass.size();i++){
            strings[i+1] = mStringsClass.get(i);
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
        try {
            float score = Float.valueOf(scoreString);
            if(score>10f){
                Toast.makeText(this, "满分10分！", Toast.LENGTH_SHORT).show();
            }
        }catch (NumberFormatException e){
            Toast.makeText(this, "请输入数字成绩", Toast.LENGTH_SHORT).show();
            return;
        }
        String className = mSpinClass.getSelectedItem().toString();
        String stuName = mSpinStu.getSelectedItem().toString();
        String select = mSelect.getSelectedItem().toString();
        if (className.equals("请选择班级")) {
            Toast.makeText(this, "请选择班级", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stuName.equals("请选择学生")) {
            Toast.makeText(this, "请选择学生", Toast.LENGTH_SHORT).show();
            return;
        }
        if (select.equals("请选择分数类别")) {
            Toast.makeText(this, "请选择分数类别", Toast.LENGTH_SHORT).show();
            return;
        }
        updateScore(mSelect.getSelectedItemPosition(),className,stuName,scoreString);
    }

    private void updateScore(int selectedItemPosition, String className, String stuName, String scoreString) {
        String sql = "select * from stu where name=? and class=?";
        Cursor cursor = db.rawQuery(sql,new String[]{stuName,className});
        if (cursor!=null && cursor.moveToFirst()){
            String sno = cursor.getString(cursor.getColumnIndex("sno"));
            cursor.close();
            ContentValues values = new ContentValues();
            if(type.equals("1")){
                values.put("dianming"+selectedItemPosition,scoreString);
            }else if(type.equals("2")){
                values.put("zuoye"+selectedItemPosition,scoreString);
            }else
            {
                values.put("shangji"+selectedItemPosition,scoreString);
            }
            db.update("score",values,"sno=?",new String[]{sno});
            Toast.makeText(this, "分数录入成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
