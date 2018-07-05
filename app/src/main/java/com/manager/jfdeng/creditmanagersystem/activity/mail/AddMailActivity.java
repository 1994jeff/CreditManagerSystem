package com.manager.jfdeng.creditmanagersystem.activity.mail;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.manager.jfdeng.creditmanagersystem.bean.SendMailUtil;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;
import com.manager.jfdeng.creditmanagersystem.utils.ExcelUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddMailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mMailAddress;
    private EditText mMailPsd;
    private EditText mMailAddress2;
    private EditText mMailPsd2;
    private Button mOk;

    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;

    List<ScoreDto> scores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mail);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private void initView() {

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getReadableDatabase();

        mMailAddress = (EditText) findViewById(R.id.mailAddress);
        mMailPsd = (EditText) findViewById(R.id.mailPsd);
        mMailAddress2 = (EditText) findViewById(R.id.mailAddress2);
        mMailPsd2 = (EditText) findViewById(R.id.mailPsd2);
        mOk = (Button) findViewById(R.id.ok);

        mOk.setOnClickListener(this);
    }

    private void getScores() {
        String sql = "select score.*,stu.name as name,stu.class as aClass from score left join stu on stu.sno=score.sno";
        Cursor cursor = db.rawQuery(sql,null);
        ScoreDto dto = null;
        scores.clear();
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
            scores.add(dto);
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

    @Override
    protected void onResume() {
        super.onResume();
        loadAddress();
    }

    private void loadAddress() {
        SharedPreferences preferences = getSharedPreferences("mail", Context.MODE_PRIVATE);
        String address = preferences.getString("address","");
        String address2 = preferences.getString("address2","");
        String psd = preferences.getString("psd","");
        String psd2 = preferences.getString("psd2","");
        mMailAddress.setText(address);
        mMailAddress2.setText(address2);
        mMailPsd.setText(psd);
        mMailPsd2.setText(psd2);
    }

    private void submit() {
        // validate
        String mailAddressString = mMailAddress.getText().toString().trim();
        if (TextUtils.isEmpty(mailAddressString)) {
            Toast.makeText(this, "mailAddressString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String mailPsdString = mMailPsd.getText().toString().trim();
        if (TextUtils.isEmpty(mailPsdString)) {
            Toast.makeText(this, "mailPsdString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String mailAddress2String = mMailAddress2.getText().toString().trim();
        if (TextUtils.isEmpty(mailAddress2String)) {
            Toast.makeText(this, "mailAddress2String不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String mailPsd2String = mMailPsd2.getText().toString().trim();
        if (TextUtils.isEmpty(mailPsd2String)) {
            Toast.makeText(this, "mailPsd2String不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        save();
        sendMail();

    }

    private void save() {
        SharedPreferences preferences = getSharedPreferences("mail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("address",mMailAddress.getText().toString());
        editor.putString("address2",mMailAddress2.getText().toString());
        editor.putString("psd",mMailPsd.getText().toString());
        editor.putString("psd2",mMailPsd2.getText().toString());
        editor.commit();
    }

    private void sendMail() {
        getScores();
        try {
            File file = ExcelUtil.writeExcel(this,scores,"score");
            if(file!=null && file.exists()){
                SendMailUtil.FROM_ADD = mMailAddress.getText().toString();
                SendMailUtil.FROM_PSW = mMailPsd.getText().toString();
                SendMailUtil.send(file,mMailAddress.getText().toString());
                SendMailUtil.FROM_ADD = mMailAddress2.getText().toString();
                SendMailUtil.FROM_PSW = mMailPsd2.getText().toString();
                SendMailUtil.send(file,mMailAddress2.getText().toString());
            }
        } catch (Exception e) {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
    }
}


