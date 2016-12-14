package ly.com.imageviewswitchdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

public class ImageShowActivity extends AppCompatActivity implements View.OnClickListener, ViewSwitcher.ViewFactory
{
    private int[] imgIdArray;
    private Button btn_back, btn_start, btn_next;
    private ImageSwitcher myImageSwitch;
    /**
     * 当前选中的图片id序号 默认显示第一张
     */
    private int currentPosition = 0;
    private Timer timer = new Timer();
    private GestureDetector myGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);

        imgIdArray = getIntent().getIntArrayExtra("image");

        btn_back = (Button) findViewById(R.id.back);
        btn_start = (Button) findViewById(R.id.start);
        btn_next = (Button) findViewById(R.id.forward);
        myImageSwitch = (ImageSwitcher) findViewById(R.id.myImageSwitch);

        btn_back.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        myImageSwitch.setFactory(this);
        myGestureDetector = new GestureDetector(this, new myOnGestureListener());
        myImageSwitch.setImageResource(imgIdArray[currentPosition]);

        myImageSwitch.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                myGestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    class myOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY)
        {
            if (e1.getX() - e2.getX() > 50)
            {
                //下一个
                nextFun();

            } else if (e2.getX() - e1.getX() > 50)
            {
                //上一个
                backFun();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    TimerTask task = new TimerTask()
    {
        @Override
        public void run()
        {
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if (msg.what == 1)
            {
                if (currentPosition == (imgIdArray.length - 1))
                {
                    currentPosition = 0;
                    animRight();
                    myImageSwitch.setImageResource(imgIdArray[0]);
                } else
                {
                    animRight();
                    myImageSwitch.setImageResource(imgIdArray[currentPosition + 1]);
                    currentPosition++;
                }
            }
            super.handleMessage(msg);
        }
    };

    public static void setDataIntent(Context context, int[] array)
    {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(context, ImageShowActivity.class);
        bundle.putIntArray("image", array);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back:
                backFun();
                break;
            case R.id.start:
                if (timer == null)
                {
                    timer = new Timer();
                    timer.schedule(task, 1000, 2000); // 1s后执行task,经过1s再次执行
                }
                break;
            case R.id.forward:

                 nextFun();
                break;
            default:
                break;
        }

    }

    @Override
    public View makeView()
    {
        final ImageView i = new ImageView(this);
        i.setBackgroundColor(0xff000000);
        i.setScaleType(ImageView.ScaleType.CENTER_CROP);
        i.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return i;
    }

    /**
     * 取消定时器
     */
    public void cancleTimer()
    {
        if (timer != null)
        {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    /**
     * 右侧动画
     */
    public void animRight()
    {
        myImageSwitch.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_out));
        myImageSwitch.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_in));
    }

    /**
     * 左侧动画
     */
    public void animLeft()
    {
        myImageSwitch.setInAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.left_in));
        myImageSwitch.setOutAnimation(AnimationUtils.loadAnimation(getApplication(), R.anim.right_out));
    }


    /**
     * 上一个
     */
    public void backFun(){
        cancleTimer();
        if (currentPosition == 0)
        {
            animLeft();
            currentPosition = imgIdArray.length - 1;
            myImageSwitch.setImageResource(imgIdArray[currentPosition]);

        } else
        {
            animLeft();
            myImageSwitch.setImageResource(imgIdArray[currentPosition - 1]);
            currentPosition--;
        }
    }


    /**
     * 下一个
     */
    public void nextFun(){
        cancleTimer();
        if (currentPosition == (imgIdArray.length - 1))
        {
            animRight();
            currentPosition = 0;
            myImageSwitch.setImageResource(imgIdArray[currentPosition]);
        } else
        {
            animRight();
            myImageSwitch.setImageResource(imgIdArray[currentPosition + 1]);
            currentPosition++;
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        cancleTimer();
    }

}
