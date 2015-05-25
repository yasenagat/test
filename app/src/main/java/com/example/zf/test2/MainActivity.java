package com.example.zf.test2;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by zf on 2015/3/17.
 */
public class MainActivity extends Activity {

    double nLenStart = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int nCnt = event.getPointerCount();

        int n = event.getAction();
        if( (event.getAction()&MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN && 2 == nCnt)//<span style="color:#ff0000;">2表示两个手指</span>
        {

            for(int i=0; i< nCnt; i++)
            {
                float x = event.getX(i);
                float y = event.getY(i);

                Point pt = new Point((int)x, (int)y);

            }

            int xlen = Math.abs((int)event.getX(0) - (int)event.getX(1));
            int ylen = Math.abs((int)event.getY(0) - (int)event.getY(1));

            nLenStart = Math.sqrt((double)xlen*xlen + (double)ylen * ylen);


        }else if( (event.getAction()&MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_UP  && 2 == nCnt)
        {

            for(int i=0; i< nCnt; i++)
            {
                float x = event.getX(i);
                float y = event.getY(i);

                Point pt = new Point((int)x, (int)y);

            }

            int xlen = Math.abs((int)event.getX(0) - (int)event.getX(1));
            int ylen = Math.abs((int)event.getY(0) - (int)event.getY(1));

            double nLenEnd = Math.sqrt((double)xlen*xlen + (double)ylen * ylen);

            if(nLenEnd > nLenStart)//通过两个手指开始距离和结束距离，来判断放大缩小
            {
                Toast.makeText(getApplicationContext(), "放大", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "缩小", Toast.LENGTH_SHORT).show();
            }
        }



        return super.onTouchEvent(event);
    }
}
