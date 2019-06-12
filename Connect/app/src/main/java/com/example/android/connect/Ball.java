package com.example.android.connect;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//To draw the board and save the coordinates of the circles
public class Ball extends View {

    ballparams[] par;
    Paint mpaint,lightpaint;
    int m=0;
    int []cntcol=new int[8];
    int[] cntrow=new int[7];
    static float x;static float y;
    Path mPath;
    TextView move;
    float r,d,w,left,right,top,bottom;
    int row_tobedropped;
    float[] valy=new float[6];
    float[] valx=new float[7];

//getting the ball number from the move number
public int ballnofrommoveno(int b)
{int l=-1;
    for(int i=0;i<42;i++)
    {if(par[i].movenum==b)
        l=par[i].ballnum;}
        return l;
}
//get the row number from ballno
public int getrowno(int i)
{
    return par[i].rowno;
}
//get the column number from ballno
public int getcolumnno(int i)
{return par[i].columnno;}

//set the move number
public void setmovenum(int moveno,int ballno)
{ for(int i=0;i<42;i++)
{if(par[i].ballnum==ballno)
    par[i].movenum=moveno;}
}
//get the move number
public int getmovenum(float column,float row)
{int q=0;int l=-1;
    for(int i=0;i<=5;i++)
    {
        for(int j=0;j<=6;j++)
        {
            if(par[q].columnno==column&&par[q].rowno==row)
            {
                l= par[q].movenum;
            }
            q++;
        }
    }
return l;
}
//returns the x coordinate of the circles
public float getpositionx(int i)
    { return par[i].xposition;
    }
    //returns the y coordinate of the circles
    public float getpositiony(int i)
    { return par[i].yposition;
    }
//returns the radius of the ball
  public float getradius()
    {return r;}
//returns the ball number if coordinates of the circle is given
    public int getballno(float x,float y,int type)
    {int q=0;int l = -1;
        for(int t=0;t<=5;t++)
        { for(int s=0;s<=6;s++)
            { if(type==0)
            {if(par[q].yposition==(y-4*r)&&par[q].xposition==(x+8*r+w))
                {l=par[q].ballnum;
                  break;}
                }
                else if(type==1)
        {if(par[q].columnno==x&&par[q].rowno==y)
        {l=par[q].ballnum;
        break;}
        }
            q++;}
        }

        return l;
    }

    public void afterundo(int colno)
    {
        cntrow[colno]++;
    }
// returns the y coordinate where the ball must be dropped
    public float checkycoord(int colno)
    { row_tobedropped=cntrow[colno];

    if(row_tobedropped>=0)
    {cntrow[colno]--;
    return valy[row_tobedropped];
    }
    return 0;
    }

    //returns the corresponding x coordinate where the ball must be dropped
    public float checkxcoord(int colno)
    {return valx[colno];}
// stores the ball colour
    public void setballcolour(int colour,int i)
    {par[i].ballcolor=colour;}
// returns the ball colour
    public int getballcolour(int i)
    {return par[i].ballcolor;}
    // Ball constructor.
    public Ball(Context context) {
        super(context);
        mPath=new Path();
        mpaint=new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setStyle(Paint.Style.FILL);
        mpaint.setColor(Color.BLUE);
         par=new ballparams[42];
         for(int i=0;i<42;++i)
         { par[i]=new ballparams();}

        lightpaint=new Paint();
        lightpaint.setColor(Color.GRAY);
        lightpaint.setStyle(Paint.Style.FILL);
        lightpaint.setAntiAlias(true);
        DisplayMetrics displayMetrics=new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        d=pxFromDp(context,5);
        w=pxFromDp(context,6);
        r = pxFromDp(context, 20);
        left=pxFromDp(context,16);
        right=pxFromDp(context,350);
        top=pxFromDp(context,90);
        bottom=pxFromDp(context,420);
        for(int i=1;i<=7;i++)
        {cntrow[i-1]=5;}
        for(int i=1;i<8;i++)
        {cntcol[i-1]=6;}
        }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.save();
       mPath.rewind();
x=left+(r)+2*d;

y= top+r+3*d;

for( int j=0;j<=5;j++)
      {for(int k=0;k<=6;k++)
      { if(j==0&&k==0){m=0;}
         mPath.addCircle(x,y,r,Path.Direction.CCW);
          valx[k]=x-8*r-w;
          par[m].xposition=x;
          par[m].yposition=y;
          par[m].rowno=j;
          par[m].columnno=k;
          par[m].ballnum=m;
          m++;
          x=x+(2*r)+d;
         if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
             canvas.clipPath(mPath, Region.Op.DIFFERENCE);
          } else {
              canvas.clipOutPath(mPath);
              mPath.reset();
         }
         }
        x=left+(r)+2*d;
        valy[j]=y+4*r;
        y=y+(2*r)+d;

      }
        canvas.drawRect(
                left,
                top ,
                right,
                bottom-7*d,mpaint);
canvas.restore();

    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }
}