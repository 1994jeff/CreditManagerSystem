package com.manager.jfdeng.creditmanagersystem.activity.score;

import android.content.ContentValues;
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
import com.manager.jfdeng.creditmanagersystem.bean.Score;
import com.manager.jfdeng.creditmanagersystem.bean.Student;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;

public class DelScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mName;
    private TextView mInfo;
    private Button mQuery;
    private Button mDel;

    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;

    Score score;
    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_score);
        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.name);
        mInfo = (TextView) findViewById(R.id.info);
        mQuery = (Button) findViewById(R.id.query);
        mDel = (Button) findViewById(R.id.del);

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getReadableDatabase();

        mQuery.setOnClickListener(this);
        mDel.setOnClickListener(this);
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
                del();
                break;
        }
    }
    private void del() {
        if(mStudent!=null){
            ContentValues contentValues = getContentValues();
            db.update("score",contentValues,"sno=?",new String[]{mStudent.getsNo()});
            Toast.makeText(this, "删除学分成功！", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "请先查看是否存在此学生！", Toast.LENGTH_SHORT).show();
        }
        mStudent = null;
    }

    private ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        String sql = "select * from score where sno=?";
        Cursor cursor = db.rawQuery(sql, new String[]{mStudent.getsNo()});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String sno = cursor.getString(cursor.getColumnIndex("sno"));
                String dianming1 = cursor.getString(cursor.getColumnIndex("dianming1"));
                String dianming2 = cursor.getString(cursor.getColumnIndex("dianming2"));
                String dianming3 = cursor.getString(cursor.getColumnIndex("dianming3"));
                String dianming4 = cursor.getString(cursor.getColumnIndex("dianming4"));
                String dianming5 = cursor.getString(cursor.getColumnIndex("dianming5"));
                String zuoye1 = cursor.getString(cursor.getColumnIndex("zuoye1"));
                String zuoye2 = cursor.getString(cursor.getColumnIndex("zuoye2"));
                String zuoye3 = cursor.getString(cursor.getColumnIndex("zuoye3"));
                String zuoye4 = cursor.getString(cursor.getColumnIndex("zuoye4"));
                String zuoye5 = cursor.getString(cursor.getColumnIndex("zuoye5"));
                String shangji1 = cursor.getString(cursor.getColumnIndex("shangji1"));
                String shangji2 = cursor.getString(cursor.getColumnIndex("shangji2"));
                String shangji3 = cursor.getString(cursor.getColumnIndex("shangji3"));
                String shangji4 = cursor.getString(cursor.getColumnIndex("shangji4"));
                String shangji5 = cursor.getString(cursor.getColumnIndex("shangji5"));
                score = new Score(id + "", sno, dianming1, dianming2, dianming3, dianming4, dianming5,
                        zuoye1, zuoye2, zuoye3, zuoye4, zuoye5, shangji1, shangji2, shangji3, shangji4, shangji5);
                contentValues.put("dianming1", 0);
                contentValues.put("dianming2", 0);
                contentValues.put("dianming3", 0);
                contentValues.put("dianming4", 0);
                contentValues.put("dianming5", 0);
                contentValues.put("zuoye1", 0);
                contentValues.put("zuoye2", 0);
                contentValues.put("zuoye3", 0);
                contentValues.put("zuoye4", 0);
                contentValues.put("zuoye5", 0);
                contentValues.put("shangji1", 0);
                contentValues.put("shangji2", 0);
                contentValues.put("shangji3", 0);
                contentValues.put("shangji4", 0);
                contentValues.put("shangji5", 0);
            }
        }
        return contentValues;
    }

    private void submit() {
        // validate
        String nameString = mName.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "请输入要删除学分学生的学号", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
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
