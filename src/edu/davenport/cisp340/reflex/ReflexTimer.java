package edu.davenport.cisp340.reflex;

import android.content.Context;
import android.os.CountDownTimer;



public class ReflexTimer extends CountDownTimer
{
	
	PlayGameView pgv;
	

	public ReflexTimer(int milliseconds, PlayGameView playGameView)
	{
		super(milliseconds, milliseconds);
		
		pgv = playGameView;
	
	}



@Override
public void onTick(long millisUntilFinished)
{
	// TODO Auto-generated method stub
	
}

@Override
public void onFinish()
{
	// TODO Auto-generated method stub
	pgv.incmiss();
	pgv.invalidate();
	
	
	
}
}