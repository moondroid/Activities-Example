package it.moondroid.activities_example;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final int PICK_CONTACT_REQUEST = 0;
	private Button btnStartActivity, btnStartIntent, btnStartActivityForResult;

	/**
	 * onCreate() The system calls this when creating your activity. Within your
	 * implementation, you should initialize the essential components of your
	 * activity. Most importantly, this is where you must call setContentView()
	 * to define the layout for the activity's user interface.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//set onClick listeners for UI buttons
		btnStartActivity = (Button) findViewById(R.id.btnStartActivity);
		btnStartActivity.setOnClickListener(this);
		btnStartIntent = (Button) findViewById(R.id.btnStartIntent);
		btnStartIntent.setOnClickListener(this);
		btnStartActivityForResult = (Button) findViewById(R.id.btnStartActivityForResult);
		btnStartActivityForResult.setOnClickListener(this);
	}

	@Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // The activity is about to be destroyed.
    }
    
	/**
	 * OnClickListener interface, reacting to buttons click
	 */
	@Override
	public void onClick(View v) {

		Intent intent;
		
		switch (v.getId()) {

		case R.id.btnStartActivity:
			//Start a new Activity with an explicit intent
			intent = new Intent(this, NewActivity.class);
			startActivity(intent);
			break;

		case R.id.btnStartIntent:
			//Start a new Activity with an implicit intent.
			//For example, if you want to allow the user to send an email message
			intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"someone@gmail.com"});
			intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
			intent.putExtra(Intent.EXTRA_TEXT, "hello");
			intent.setType("message/rfc822");
			startActivity(intent);
			break;
		
		case R.id.btnStartActivityForResult:
			//Sometimes, you might want to receive a result from the activity that you start. 
			//In that case, start the activity by calling startActivityForResult().
			//To then receive the result from the subsequent activity, implement the onActivityResult() callback method
			intent = new Intent(this, PickContactActivity.class);
			startActivityForResult(intent, PICK_CONTACT_REQUEST);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);
		
		// If the request went well (OK) and the request was PICK_CONTACT_REQUEST
		if (requestCode==PICK_CONTACT_REQUEST && resultCode==RESULT_OK){
			String name = (String) data.getExtras().get(PickContactActivity.EXTRA_NAME);
			Toast.makeText(this, "You selected: "+name, Toast.LENGTH_LONG).show();
		}
		
	}
	
}
