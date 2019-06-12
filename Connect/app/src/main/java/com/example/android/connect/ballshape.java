package com.example.android.connect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
//Used for drawing the ball to be dropped
public class ballshape extends View {
   Paint mpaint;int col;float r;
    public ballshape(Context context) {
        super(context);
        mpaint = new Paint();
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setColor(Color.RED);
        mpaint.setAntiAlias(true);
        col=Color.RED;
        r = pxFromDp(context, 20);


    }
    public void setcolor(int cnt)
    {if(cnt==0)
        col=Color.RED;
    else if(cnt==1)
        col=Color.YELLOW;
    else if(cnt==3)
        col=Color.BLACK;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
mpaint.setColor(col);
canvas.drawCircle(500,10,r,mpaint);

    }
    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}
