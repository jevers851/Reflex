package edu.davenport.cisp340.reflex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.media.MediaPlayer;


public class SplashScreen extends Activity
{

	private MediaPlayer player;
	private Boolean isPlayerReady;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		
		isPlayerReady = false;
		
		player = MediaPlayer.create(this, R.raw.tada);
		
		player.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
		{
			
			@Override
			public void onPrepared(MediaPlayer mp)
			{
				Log.i(getPackageName(), "OnPrepared()");
				isPlayerReady = true;
				
			}
		});
	
		
		player.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
		{
			
			@Override
			public void onCompletion(MediaPlayer mp)
			{
				Log.i(getPackageName(), "OnCompletion()");
				
			}
		});
	}
	
	@Override
	public void onDestroy()
	{
		
		super.onDestroy();
		
		isPlayerReady = false;
		player.reset();
		player.release();
		player = null;
		
	}
	
	public void btnPlay_Click(View view)
	{
		
	
		if( isPlayerReady )
		{
			
			player.start();
			
		}
		
		Intent playGame = new Intent(getApplicationContext(), PlayGame.class);
		startActivity(playGame);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
