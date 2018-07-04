package com.manager.jfdeng.creditmanagersystem.activity.student;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.manager.jfdeng.creditmanagersystem.R;
import com.manager.jfdeng.creditmanagersystem.bean.Student;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchStudentActivity extends AppCompatActivity {

    private ListView mListView;
    List<Student> mList;
    MyAdapter mAdapter;
    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);
        initView();
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getReadableDatabase();
        mList = getData();
        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);
    }

    private List<Student> getData() {
        List<Student> list = new ArrayList<>();
        list.add(new Student("序号","学号","姓名","班级"));
        Cursor cursor = db.rawQuery("select * from stu",null);
        Student student = null;
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String sno = cursor.getString(cursor.getColumnIndex("sno"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String aClass = cursor.getString(cursor.getColumnIndex("class"));
            student = new Student(id+"",sno,name,aClass);
            list.add(student);
        }
        return list;
    }


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return mList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup group) {

            ViewHolder holder = null;
            if(view==null){
                view = LayoutInflater.from(SearchStudentActivity.this).inflate(R.layout.item_stu, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }
            if(i==0)
            {
                holder.mId.setText("序号");
            }else
                holder.mId.setText(mList.get(i).getId());
            holder.mSno.setText(mList.get(i).getsNo());
            holder.mName.setText(mList.get(i).getName());
            holder.mSclass.setText(mList.get(i).getsClass());
            return view;
        }

        public class ViewHolder {
            public View rootView;
            public TextView mId;
            public TextView mSno;
            public TextView mName;
            public TextView mSclass;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.mId = (TextView) rootView.findViewById(R.id.id);
                this.mSno = (TextView) rootView.findViewById(R.id.sno);
                this.mName = (TextView) rootView.findViewById(R.id.name);
                this.mSclass = (TextView) rootView.findViewById(R.id.sclass);
            }

        }
    }
}
