package com.example.protosilent;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Button silent;
	private Button normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        silent = (Button)findViewById(R.id.silent);
        normal = (Button)findViewById(R.id.normal);
        final AudioManager mode = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        silent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				Toast.makeText(getBaseContext(), "Silent Mode Activated", Toast.LENGTH_SHORT).show();
			}
		});
        normal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mode.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				Toast.makeText(getBaseContext(), "Normal Mode Activated", Toast.LENGTH_SHORT).show();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
