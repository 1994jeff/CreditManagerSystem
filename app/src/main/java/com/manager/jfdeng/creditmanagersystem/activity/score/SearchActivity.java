package com.manager.jfdeng.creditmanagersystem.activity.score;

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
import com.manager.jfdeng.creditmanagersystem.bean.Score;
import com.manager.jfdeng.creditmanagersystem.bean.ScoreDto;
import com.manager.jfdeng.creditmanagersystem.utils.DataBaseUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ListView mListItem;
    List<ScoreDto> scores = new ArrayList<>();
    MyAdapter adapter = new MyAdapter();

    DataBaseUtils mDataBaseUtils;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mListItem = (ListView) findViewById(R.id.list_item);

        mDataBaseUtils = new DataBaseUtils(this,"system.db",null,1);
        db = mDataBaseUtils.getReadableDatabase();

        getScores();
        mListItem.setAdapter(adapter);
    }

    private void getScores() {
        String sql = "select score.*,stu.name as name,stu.class as aClass from score left join stu on stu.sno=score.sno";
        Cursor cursor = db.rawQuery(sql,null);
        ScoreDto dto = null;
        scores.clear();
        scores.add(new ScoreDto("小计", "学号", "点名分", "", "", "", "",
                "作业分", "", "", "", "", "上机分", "", "", "", "","名字",""));
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
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return scores.size();
        }

        @Override
        public Object getItem(int i) {
            return scores.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(SearchActivity.this).inflate(R.layout.item_score, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }
            if(i>0){
                holder.mSno.setText(scores.get(i).getSno());
                holder.mName.setText(scores.get(i).getName());
                holder.mDianming.setText(getDianMing(i));
                holder.mZuoye.setText(getZuoYe(i));
                holder.mShangji.setText(getShangji(i));
                holder.mXiaoji.setText(getXiaoJi(i));
            }else {
                holder.mSno.setText(scores.get(i).getSno());
                holder.mName.setText(scores.get(i).getName());
                holder.mDianming.setText(scores.get(i).getDianming1());
                holder.mZuoye.setText(scores.get(i).getZuoye1());
                holder.mShangji.setText(scores.get(i).getShangji1());
                holder.mXiaoji.setText(scores.get(i).getId());
            }
            return view;
        }

        private String getXiaoJi(int i) {
            ScoreDto dto = scores.get(i);
            float dianming1 = Float.valueOf(dto.getDianming1());
            float dianming2 = Float.valueOf(dto.getDianming2());
            float dianming3 = Float.valueOf(dto.getDianming3());
            float dianming4 = Float.valueOf(dto.getDianming4());
            float dianming5 = Float.valueOf(dto.getDianming5());
            float zuoye1 = Float.valueOf(dto.getDianming1());
            float zuoye2 = Float.valueOf(dto.getDianming2());
            float zuoye3 = Float.valueOf(dto.getDianming3());
            float zuoye4 = Float.valueOf(dto.getDianming4());
            float zuoye5 = Float.valueOf(dto.getDianming5());
            float shangji1 = Float.valueOf(dto.getDianming1());
            float shangji2 = Float.valueOf(dto.getDianming2());
            float shangji3 = Float.valueOf(dto.getDianming3());
            float shangji4 = Float.valueOf(dto.getDianming4());
            float shangji5 = Float.valueOf(dto.getDianming5());
            float dianming = dianming1+dianming2+dianming3+dianming4+dianming5;
            float zuoye = zuoye1+zuoye2+zuoye3+zuoye4+zuoye5;
            float shangji = shangji1+shangji2+shangji3+shangji4+shangji5;
            float xiaoji = dianming/5.0f*0.2f+shangji/5.0f*0.5f+zuoye/5.0f*0.3f;
            return xiaoji+"";
        }

        private String getShangji(int i) {
            StringBuilder builder = new StringBuilder();
            ScoreDto dto = scores.get(i);
            return builder.append(dto.getShangji1()).append(",").append(dto.getShangji2()).append(",").append(dto.getShangji3())
                    .append(",").append(dto.getShangji4()).append(",").append(dto.getShangji5()).toString();
        }

        private String getZuoYe(int i) {
            StringBuilder builder = new StringBuilder();
            ScoreDto dto = scores.get(i);
            return builder.append(dto.getZuoye1()).append(",").append(dto.getZuoye2()).append(",").append(dto.getZuoye3()).append(",")
                    .append(dto.getZuoye4()).append(",").append(dto.getZuoye5()).toString();
        }

        private String getDianMing(int i) {
            StringBuilder builder = new StringBuilder();
            ScoreDto dto = scores.get(i);
            return builder.append(dto.getDianming1()).append(",").append(dto.getDianming2())
                    .append(",").append(dto.getDianming3()).append(",").append(dto.getDianming4()).append(",").append(dto.getDianming5()).toString();
        }

        public class ViewHolder {
            public View rootView;
            public TextView mSno;
            public TextView mName;
            public TextView mDianming;
            public TextView mZuoye;
            public TextView mShangji;
            public TextView mXiaoji;

            public ViewHolder(View rootView) {
                this.rootView = rootView;
                this.mSno = (TextView) rootView.findViewById(R.id.sno);
                this.mName = (TextView) rootView.findViewById(R.id.name);
                this.mDianming = (TextView) rootView.findViewById(R.id.dianming);
                this.mZuoye = (TextView) rootView.findViewById(R.id.zuoye);
                this.mShangji = (TextView) rootView.findViewById(R.id.shangji);
                this.mXiaoji = (TextView) rootView.findViewById(R.id.xiaoji);
            }

        }
    }
}
