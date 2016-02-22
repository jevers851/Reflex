package edu.davenport.cisp340.reflex;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import java.util.Random;
import android.widget.*;

public class PlayGameView extends View
{
	
	
	private Context m_context;
	private Bitmap m_logo;
	private ReflexTimer m_timer;
	private String m_message;
	private Paint p = new Paint();
	private Random m_random;
	private int swidth;
	private int sheight;
	private int m_randomx;
	private int m_randomy;
	private int vwidth;
	private int vheight;
	private int m_score;
	private int m_miss;
	
	public PlayGameView(Context context)
	{
		
		super(context);
		m_context = context;
		getScreenInfo(context);
		
		m_random = new Random(System.currentTimeMillis());
		
		p.setColor(Color.RED);
		p.setTextSize(12);
		

		m_timer = new ReflexTimer(2500, this);
		
		drawLogo();
		m_score = 0;

		
	}

	
	public void drawLogo()
	{
		
		m_logo = BitmapFactory.decodeResource(getResources(), R.drawable.du);
		m_timer.cancel();
		m_timer.start();

		
	}
	
	@Override
	public void onDraw(Canvas c)
	{

		

		m_randomx = m_random.nextInt(vwidth - m_logo.getWidth());
		m_randomy = m_random.nextInt(vheight- m_logo.getHeight());		
		
		c.drawBitmap(m_logo, m_randomx ,m_randomy , null);

		
	}
	
	public void setMessage(String message)
	{
		m_message = message;
		invalidate();
	}
	
	public void getScreenInfo(Context context)
	{
		//WINDOW_SERVICE to get screen size
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		
		swidth = wm.getDefaultDisplay().getWidth();
		sheight = wm.getDefaultDisplay().getHeight();
		

		//Toast.makeText(context, "w=" + String.valueOf(width) + "; h=" + String.valueOf(height), Toast.LENGTH_SHORT).show();
		
	}
	
	
	@Override
	public void onSizeChanged( int newW, int newH, int oldW, int oldH)
	{
		
		vwidth = newW;
		vheight = newH;
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getRawX();
		float y = event.getRawY() ;
		
		//Toast.makeText(m_context, "x=" + String.valueOf(x) + "; y=" + String.valueOf(y), Toast.LENGTH_SHORT).show();
		
		m_timer.cancel();
		invalidate();
		
		if(hitDetect(x,y))
		{
			m_score++;
		}
		else if(!hitDetect(x,y))
		{
			incmiss();
		}
		
		m_timer.start();
		return false;
		
	}
	public boolean hitDetect(double x, double y)
	{
		
		if(x >= m_randomx && x <= (m_randomx + m_logo.getWidth()) && y >= (m_randomy + 115) && y <= ((m_randomy + 115) + m_logo.getHeight()))
		{
			return true;
		}
		else
			return false;
		
	}
	public boolean checkEnd()
	{
		
		if(m_miss == 3)
		{
			return true;
		}
		
		return false;
		
	}
	public void incmiss()
	{
		
		m_miss++;
		if(checkEnd())
		{
			Intent gameOver = new Intent(m_context, GameOver.class);
			gameOver.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			gameOver.putExtra("score", m_score);
			m_context.startActivity(gameOver);
		}
		m_timer.start();
	}
	
}
