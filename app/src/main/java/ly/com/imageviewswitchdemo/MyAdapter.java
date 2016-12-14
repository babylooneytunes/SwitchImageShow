package ly.com.imageviewswitchdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter
{
    private Context mContext;
    private List<DataBean> list;
    private LayoutInflater inflater;

    public MyAdapter(Context mContext, List<DataBean> list)
    {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tv01 = (TextView) convertView.findViewById(R.id.text01);
            viewHolder.tv02 = (TextView) convertView.findViewById(R.id.text02);
            viewHolder.tv03 = (TextView) convertView.findViewById(R.id.text03);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv01.setText(""+list.get(position).getID());
        viewHolder.tv02.setText(list.get(position).getTextTitle());
        viewHolder.tv03.setText(list.get(position).getPageDesp());

        return convertView;
    }

    static class ViewHolder{
        TextView tv01,tv02,tv03;
    }
}
