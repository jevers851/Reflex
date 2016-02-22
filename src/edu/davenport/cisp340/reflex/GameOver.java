package edu.davenport.cisp340.reflex;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
public class GameOver extends Activity
{

	public TextView lblSDisplay;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		
		int score = 0;
		lblSDisplay = (TextView) findViewById(R.id.lblSDisplay);
		
		score = this.getIntent().getIntExtra("score", 0);
		lblSDisplay.setText(String.valueOf(score));
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_over, menu);
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
	
	public void btnReplay_Click(View view)
	{
		
		
		Intent splashScreen = new Intent(getApplicationContext(), SplashScreen.class);
		startActivity(splashScreen);
	}
}
