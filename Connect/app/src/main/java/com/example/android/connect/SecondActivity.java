package com.example.android.connect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    RelativeLayout layout;static int result=1;
    TextView  player;int checkrun=0;
    float toballx, tobally;int b1,b2,b3,b4;
    int colno, rowno, ballcolor, ballno = -1;int numundo=0;
    int [][]a=new int[7][8];
    int cnt = 0;
    float positiony, positionx;
    int moveno = 0;int over=0,status=0;
    ObjectAnimator animatorX, animatorY;
    static Ball ballview;
    Button end,undo;
     ballshape b[] = new ballshape[42];
    LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    MediaPlayer mmedia,mmedia1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.layout);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        player = (TextView) findViewById(R.id.player);
        end = (Button) findViewById(R.id.end);
        undo = (Button) findViewById(R.id.undo);
        mmedia=MediaPlayer.create(this,R.raw.balldrop);
        mmedia1=MediaPlayer.create(this,R.raw.win);
        b[moveno] = new ballshape(this);
        params.topMargin = 350;
        layout.addView(b[moveno], params);
       ballview = new Ball(this);
       params1.topMargin = 250;
       layout.addView(ballview, params1);
        player.setText("PLAYER 1");
        mmedia1.start();
//Initialise an array in which 1 and 0 are filled based on the colour of the ball dropping
        for (int j = 0; j <= 5; j++) {
            for (int k = 0; k <= 6; k++) {
                a[j][k] = 2;
            }
        }

        //Assign a listener
        checktouch();
        }

        //To check if we touch inside a circle
    private boolean checkinside(float x, float y, float CenterX, float CenterY, float Radius, int c) {
        double dx = (x - CenterX) * (x - CenterX);
        double dy = (y - CenterY) * (y - CenterY);

        if ((dx + dy) < Radius * Radius) {
            toballx = CenterX;
            tobally = CenterY;
            colno = c;
            return true;
        } else {
            return false;
        }
    }

    //Checking conditions
    private int checkwin(int i) {
        //VERTICAL CHECK
        if (ballexists(i + 7) && ballexists(i + 14) && ballexists(i + 21)) {
            if (ballcolor == ballview.getballcolour(i + 7) && ballcolor == ballview.getballcolour(i + 14) && ballcolor == ballview.getballcolour(i + 21)) {
                tellresult();
                b1=ballview.getmovenum(colno,rowno);
                b2=ballview.getmovenum(colno,rowno+1);
                b3=ballview.getmovenum(colno,rowno+2);
                b4=ballview.getmovenum(colno,rowno+3);
               changetoblack(b1,b2,b3,b4);
               over=1;return over;

            }
        }

        //HORIZONTAL CHECK if(total no.of columns-4(here 7-4=3) in the same row have same colour)
              for (int k = 0; k <= 3; k++) {
                   if (a[rowno][k] == a[rowno][k + 1] && a[rowno][k] == a[rowno][k + 2] && a[rowno][k] == a[rowno][k + 3] && a[rowno][k] != 2) {

                       Toast.makeText(this,"horizontal",Toast.LENGTH_SHORT).show();
                       tellresult();
                       b1 = ballview.getmovenum(k, rowno);
                       b2 = ballview.getmovenum(k + 1, rowno);
                       b3 = ballview.getmovenum(k + 2, rowno);
                       b4 = ballview.getmovenum(k + 3, rowno);
                       changetoblack(b1, b2, b3, b4);

                       over = 1;
                       return over;
                   }
               }


        //DIAGONAL CHECK

 for (int j = 0; j <= 5; j++) {
            for (int k = 0; k <= 6; k++) {
               if(a[j][k]!=2)
               { if(j>=3&&k<=3)
               {
                   if(a[j][k]==a[j-1][k+1]&&a[j][k]==a[j-2][k+2]&&a[j][k]==a[j-3][k+3])
                   {Toast.makeText(this,"up",Toast.LENGTH_SHORT).show();
                       tellresult();
                       b1=ballview.getmovenum(k,j);
                       b2=ballview.getmovenum(k+1,j-1);
                       b3=ballview.getmovenum(k+2,j-2);
                       b4=ballview.getmovenum(k+3,j-3);
                       changetoblack(b1,b2,b3,b4);
                       over=1;return over;

                   }

               }
               else if(j<3&&k<=3)
               {
                   if(a[j][k]==a[j+1][k+1]&&a[j][k]==a[j+2][k+2]&&a[j][k]==a[j+3][k+3])
                   { Toast.makeText(this,"down",Toast.LENGTH_SHORT).show();
                       tellresult();
                       b1=ballview.getmovenum(k,j);
                       b2=ballview.getmovenum(k+1,j+1);
                       b3=ballview.getmovenum(k+2,j+2);
                       b4=ballview.getmovenum(k+3,j+3);
                       changetoblack(b1,b2,b3,b4);
                       over=1;return over;

                   }
               }

               }}}
    return over;
    }



private void tellresult() {
    if (ballcolor == 1) {
        result = 1;

    } else {
        result = 2;
    }

}
private  boolean ballexists(int b) {
    int lbl = 0;
    for (int j = 0; j < 42; j++) {
        if (b == j) lbl++;
    }
    if (lbl > 0)
        return true;
    else
        return false;
}

private  void checktouch()
{
     View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if(status==0&&checkrun==0)
                    {  for (int i = 0; i < 42; i++) {
                        if (checkinside(event.getX(), event.getY(), ballview.getpositionx(i), ballview.getpositiony(i), ballview.getradius(), ballview.getcolumnno(i))) {
                             numundo=0;
                            positiony = ballview.checkycoord(colno);
                            positionx = ballview.checkxcoord(colno);
                            if (positiony != 0) {
                                if (moveno == 0) {
                                    cnt = 1;
                                }
                                animatorX = ObjectAnimator.ofFloat(b[moveno], "x", positionx);
                                animatorX.setDuration(500);
                                animatorY = ObjectAnimator.ofFloat(b[moveno], "y", positiony);
                                animatorY.setDuration(500);
                                AnimatorSet animatorSet = new AnimatorSet();
                                animatorSet.playSequentially(animatorX, animatorY);
                                animatorSet.start();
                                checkrun=1;
                                if (ballview.getballno(positionx, positiony, 0) != -1) {
                                    ballno = ballview.getballno(positionx, positiony, 0);
                                }
                                ballview.setmovenum(moveno,ballno);
                                moveno++;
                                colno = ballview.getcolumnno(ballno);
                                rowno = ballview.getrowno(ballno);
                                ballview.setballcolour(cnt, ballno);
                                ballcolor = ballview.getballcolour(ballno);
                                animatorSet.addListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        checkrun=0;
                                        if (cnt == 0) {
                                            b[moveno] = new ballshape(getBaseContext());
                                            b[moveno].setcolor(cnt);
                                            layout.addView(b[moveno], params);
                                            player.setText("PLAYER 1");
                                            cnt = 1;mmedia.start();


                                        } else {
                                            b[moveno] = new ballshape(getBaseContext());
                                            b[moveno].setcolor(cnt);
                                            layout.addView(b[moveno], params);
                                            player.setText("PLAYER 2");
                                            cnt = 0;mmedia.start();
                                            }
                                        a[rowno][colno] = cnt;
                                        status = checkwin(ballno);
                                    }
                                });


                            }

                        }
                    }}
                    return true;
            }
            return false;
        }
    };
    ballview.setOnTouchListener(onTouchListener);

}
//If undo button is clicked
public void undo(View view)
{ if(moveno!=0&&numundo==0)
{
  numundo++;
    layout.removeView(b[moveno]);
    moveno=moveno-1;
   int ball=ballview.ballnofrommoveno(moveno);
   int c=ballview.getcolumnno(ball);
    int r=ballview.getrowno(ball);
    a[r][c]=2;
    layout.removeView(b[moveno]);
    ballview.afterundo(colno);
    if (cnt == 0) {
        b[moveno] = new ballshape(getBaseContext());
        b[moveno].setcolor(cnt);
        layout.addView(b[moveno], params);
        player.setText("PLAYER 1");
        cnt = 1;

    } else {
        b[moveno] = new ballshape(getBaseContext());
        b[moveno].setcolor(cnt);
        layout.addView(b[moveno], params);
        player.setText("PLAYER 2");
        cnt = 0;

    }

}}
//If end game button is clicked
public  void endgame(View view)
{    result=3;
    Intent intent1=new Intent(SecondActivity.this,display_result.class);
    startActivity(intent1);
}
//If four balls of same colour are connected then their colour is set to black
private void changetoblack(int b1,int b2,int b3,int b4)
{
b[b1].setcolor(3);b[b2].setcolor(3);b[b3].setcolor(3);b[b4].setcolor(3);
b[b1].invalidate();
b[b2].invalidate();
b[b3].invalidate();
b[b4].invalidate();
new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SecondActivity.this, display_result.class);
            startActivity(intent);
        }
    }, 300);


}


}

