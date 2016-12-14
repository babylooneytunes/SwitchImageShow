package ly.com.imageviewswitchdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private ListView listView;
    private List<DataBean> list;
    private MyAdapter adapter;
    private int[] array01 = { R.drawable.ic_launcher,R.drawable.one,R.drawable.two};
    private int[] array02 = { R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"22",Toast.LENGTH_LONG).show();


        initView();
    }

    private void initView()
    {
        listView = (ListView) findViewById(R.id.listview);
        list = new ArrayList<>();

        initData();

        adapter = new MyAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    /**
     * 数据加载
     */
    private void initData()
    {
        list.add(new DataBean(1,"广东省","页数有2"));
        list.add(new DataBean(2,"海南省","页数有6"));
        list.add(new DataBean(3,"江西省","页数有2"));
        list.add(new DataBean(4,"广西省","页数有3"));
        list.add(new DataBean(5,"浙江省","页数有4"));
        list.add(new DataBean(6,"湖北省","页数有2"));
        list.add(new DataBean(7,"北京省","页数有7"));
        list.add(new DataBean(8,"上海市","页数有3"));
        list.add(new DataBean(9,"山西省","页数有2"));
        list.add(new DataBean(10,"云南省","页数有2"));
        list.add(new DataBean(11,"天津市","页数有7"));
        list.add(new DataBean(12,"湖南市","页数有3"));
        list.add(new DataBean(13,"河南省","页数有2"));
        list.add(new DataBean(14,"贵州省","页数有2"));
        list.add(new DataBean(15,"合肥市","页数有7"));
        list.add(new DataBean(16,"南京市","页数有3"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        if (position % 2 == 0){
            ImageShowActivity.setDataIntent(MainActivity.this,array01);
        }else {
            ImageShowActivity.setDataIntent(MainActivity.this,array02);
        }
    }
}
