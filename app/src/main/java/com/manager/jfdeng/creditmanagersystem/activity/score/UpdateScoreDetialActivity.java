package com.manager.jfdeng.creditmanagersystem.activity.score;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.bean.ScoreDto;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;

public class UpdateScoreDetialActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mSNo;
    private EditText mName;
    private EditText mSClass;
    private EditText mDianming1;
    private EditText mDianming2;
    private EditText mDianming3;
    private EditText mDianming4;
    private EditText mDianming5;
    private EditText mZuoye1;
    private EditText mZuoye2;
    private EditText mZuoye3;
    private EditText mZuoye4;
    private EditText mZuoye5;
    private EditText mShangji1;
    private EditText mShangji2;
    private EditText mShangji3;
    private EditText mShangji4;
    private EditText mShangji5;
    private Button mOk;

    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;

    String sno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_score_detial);
        initView();
    }

    private void initView() {

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getWritableDatabase();

        Intent intent = getIntent();
        sno = intent.getStringExtra("sno");
        String name = intent.getStringExtra("name");
        String aClass = intent.getStringExtra("class");
        String id = intent.getStringExtra("id");

        mSNo = (EditText) findViewById(R.id.sNo);
        mName = (EditText) findViewById(R.id.name);
        mSClass = (EditText) findViewById(R.id.sClass);
        mSNo.setText(sno);
        mName.setText(name);
        mSClass.setText(aClass);

        mDianming1 = (EditText) findViewById(R.id.dianming1);
        mDianming2 = (EditText) findViewById(R.id.dianming2);
        mDianming3 = (EditText) findViewById(R.id.dianming3);
        mDianming4 = (EditText) findViewById(R.id.dianming4);
        mDianming5 = (EditText) findViewById(R.id.dianming5);
        mZuoye1 = (EditText) findViewById(R.id.zuoye1);
        mZuoye2 = (EditText) findViewById(R.id.zuoye2);
        mZuoye3 = (EditText) findViewById(R.id.zuoye3);
        mZuoye4 = (EditText) findViewById(R.id.zuoye4);
        mZuoye5 = (EditText) findViewById(R.id.zuoye5);
        mShangji1 = (EditText) findViewById(R.id.shangji1);
        mShangji2 = (EditText) findViewById(R.id.shangji2);
        mShangji3 = (EditText) findViewById(R.id.shangji3);
        mShangji4 = (EditText) findViewById(R.id.shangji4);
        mShangji5 = (EditText) findViewById(R.id.shangji5);
        mOk = (Button) findViewById(R.id.ok);

        mOk.setOnClickListener(this);

        getScore();
    }

    private void getScore() {
        String sql = "select score.*,stu.name as name,stu.class as aClass from score left join stu on stu.sno=score.sno where stu.sno=?";
        Cursor cursor = db.rawQuery(sql,new String[]{sno});
        ScoreDto dto = null;
        while (cursor!=null && cursor.moveToNext()){
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
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String aClass = cursor.getString(cursor.getColumnIndex("aClass"));
            dto = new ScoreDto(id + "", sno, dianming1, dianming2, dianming3, dianming4, dianming5,
                    zuoye1, zuoye2, zuoye3, zuoye4, zuoye5, shangji1, shangji2, shangji3, shangji4, shangji5,name,aClass);
            mDianming1.setText(dianming1);
            mDianming2.setText(dianming2);
            mDianming3.setText(dianming3);
            mDianming4.setText(dianming4);
            mDianming5.setText(dianming5);
            mZuoye1.setText(zuoye1);
            mZuoye2.setText(zuoye2);
            mZuoye3.setText(zuoye3);
            mZuoye4.setText(zuoye4);
            mZuoye5.setText(zuoye5);
            mShangji1.setText(shangji1);
            mShangji2.setText(shangji2);
            mShangji3.setText(shangji3);
            mShangji4.setText(shangji4);
            mShangji5.setText(shangji5);
        }
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

        String dianming1String = mDianming1.getText().toString().trim();
        if (TextUtils.isEmpty(dianming1String)) {
            Toast.makeText(this, "请输入点名得分1", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            float dianming1 = Float.valueOf(dianming1String);
            if(dianming1>10){
                Toast.makeText(this, "得分必须为数字且小于10", Toast.LENGTH_SHORT).show();
                return;
            }
        }catch (NumberFormatException e){
            Toast.makeText(this, "得分必须为数字且小于10", Toast.LENGTH_SHORT).show();
            return;
        }

        String dianming2String = mDianming2.getText().toString().trim();
        if (TextUtils.isEmpty(dianming2String)) {
            Toast.makeText(this, "请输入点名得分2", Toast.LENGTH_SHORT).show();
            return;
        }

        String dianming3String = mDianming3.getText().toString().trim();
        if (TextUtils.isEmpty(dianming3String)) {
            Toast.makeText(this, "请输入点名得分3", Toast.LENGTH_SHORT).show();
            return;
        }

        String dianming4String = mDianming4.getText().toString().trim();
        if (TextUtils.isEmpty(dianming4String)) {
            Toast.makeText(this, "请输入点名得分4", Toast.LENGTH_SHORT).show();
            return;
        }

        String dianming5String = mDianming5.getText().toString().trim();
        if (TextUtils.isEmpty(dianming5String)) {
            Toast.makeText(this, "请输入点名得分5", Toast.LENGTH_SHORT).show();
            return;
        }

        String zuoye1String = mZuoye1.getText().toString().trim();
        if (TextUtils.isEmpty(zuoye1String)) {
            Toast.makeText(this, "请输入作业得分1", Toast.LENGTH_SHORT).show();
            return;
        }

        String zuoye2String = mZuoye2.getText().toString().trim();
        if (TextUtils.isEmpty(zuoye2String)) {
            Toast.makeText(this, "请输入作业得分2", Toast.LENGTH_SHORT).show();
            return;
        }

        String zuoye3String = mZuoye3.getText().toString().trim();
        if (TextUtils.isEmpty(zuoye3String)) {
            Toast.makeText(this, "请输入作业得分3", Toast.LENGTH_SHORT).show();
            return;
        }

        String zuoye4String = mZuoye4.getText().toString().trim();
        if (TextUtils.isEmpty(zuoye4String)) {
            Toast.makeText(this, "请输入作业得分4", Toast.LENGTH_SHORT).show();
            return;
        }

        String zuoye5String = mZuoye5.getText().toString().trim();
        if (TextUtils.isEmpty(zuoye5String)) {
            Toast.makeText(this, "请输入作业得分5", Toast.LENGTH_SHORT).show();
            return;
        }

        String shangji1String = mShangji1.getText().toString().trim();
        if (TextUtils.isEmpty(shangji1String)) {
            Toast.makeText(this, "请输入上机得分1", Toast.LENGTH_SHORT).show();
            return;
        }

        String shangji2String = mShangji2.getText().toString().trim();
        if (TextUtils.isEmpty(shangji2String)) {
            Toast.makeText(this, "请输入上机得分2", Toast.LENGTH_SHORT).show();
            return;
        }

        String shangji3String = mShangji3.getText().toString().trim();
        if (TextUtils.isEmpty(shangji3String)) {
            Toast.makeText(this, "请输入上机得分3", Toast.LENGTH_SHORT).show();
            return;
        }

        String shangji4String = mShangji4.getText().toString().trim();
        if (TextUtils.isEmpty(shangji4String)) {
            Toast.makeText(this, "请输入上机得分4", Toast.LENGTH_SHORT).show();
            return;
        }

        String shangji5String = mShangji5.getText().toString().trim();
        if (TextUtils.isEmpty(shangji5String)) {
            Toast.makeText(this, "请输入上机得分5", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float dianming2 = Float.valueOf(dianming2String);
            float dianming3 = Float.valueOf(dianming3String);
            float dianming4 = Float.valueOf(dianming4String);
            float dianming5 = Float.valueOf(dianming5String);
            float zuoye1 = Float.valueOf(zuoye1String);
            float zuoye2 = Float.valueOf(zuoye2String);
            float zuoye3 = Float.valueOf(zuoye3String);
            float zuoye4 = Float.valueOf(zuoye4String);
            float zuoye5 = Float.valueOf(zuoye5String);
            float shangji1 = Float.valueOf(shangji1String);
            float shangji2 = Float.valueOf(shangji2String);
            float shangji3 = Float.valueOf(shangji3String);
            float shangji4 = Float.valueOf(shangji4String);
            float shangji5 = Float.valueOf(shangji5String);
            if(dianming2>10 ||dianming3>10 ||dianming4>10 ||dianming5>10){
                Toast.makeText(this, "得分必须为数字且小于10", Toast.LENGTH_SHORT).show();
                return;
            }
            if(zuoye1>10 ||zuoye2>10 ||zuoye3>10 ||zuoye4>10 || zuoye5>10){
                Toast.makeText(this, "得分必须为数字且小于10", Toast.LENGTH_SHORT).show();
                return;
            }
            if(shangji1>10 ||shangji2>10 ||shangji3>10 ||shangji4>10 || shangji5>10){
                Toast.makeText(this, "得分必须为数字且小于10", Toast.LENGTH_SHORT).show();
                return;
            }
        }catch (NumberFormatException e){
            Toast.makeText(this, "得分必须为数字且小于10", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        if(db.isOpen())
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("dianming1", dianming1String);
            contentValues.put("dianming2", dianming2String);
            contentValues.put("dianming3", dianming3String);
            contentValues.put("dianming4", dianming4String);
            contentValues.put("dianming5", dianming5String);
            contentValues.put("zuoye1", zuoye1String);
            contentValues.put("zuoye2", zuoye2String);
            contentValues.put("zuoye3", zuoye3String);
            contentValues.put("zuoye4", zuoye4String);
            contentValues.put("zuoye5", zuoye5String);
            contentValues.put("shangji1", shangji1String);
            contentValues.put("shangji2", shangji2String);
            contentValues.put("shangji3", shangji3String);
            contentValues.put("shangji4", shangji4String);
            contentValues.put("shangji5", shangji5String);
            db.update("score", contentValues,"sno=?",new String[]{sno});
            Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show();
        }

    }
}
