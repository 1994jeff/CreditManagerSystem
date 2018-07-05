package com.manager.jfdeng.creditmanagersystem.activity.mail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.bean.SendMailUtil;

public class AddMailActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mMailAddress;
    private EditText mMailPsd;
    private EditText mMailAddress2;
    private EditText mMailPsd2;
    private Button mOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mail);
        initView();
    }

    private void initView() {
        mMailAddress = (EditText) findViewById(R.id.mailAddress);
        mMailPsd = (EditText) findViewById(R.id.mailPsd);
        mMailAddress2 = (EditText) findViewById(R.id.mailAddress2);
        mMailPsd2 = (EditText) findViewById(R.id.mailPsd2);
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
        sendMail();

    }

    private void sendMail() {
        SendMailUtil.FROM_ADD = mMailAddress.getText().toString();
        SendMailUtil.FROM_PSW = mMailPsd.getText().toString();
        SendMailUtil.send(mMailAddress.getText().toString());
    }
}


