package it.moondroid.activities_example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PickContactActivity extends Activity {

	public static final String EXTRA_NAME = "EXTRA_NAME";
	
	private Button btnOK;
	private EditText etContactName;
	
	private Intent intentResult = new Intent();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pickcontact);
		
		etContactName = (EditText)findViewById(R.id.etContactName);
		
		btnOK = (Button) findViewById(R.id.btnOK);
		btnOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String name = etContactName.getText().toString();
				if (!name.isEmpty()){
					intentResult.putExtra(EXTRA_NAME, name);
					setResult(RESULT_OK, intentResult);
				}else{
					setResult(RESULT_CANCELED);
				}
				
				finish();
			}
		});
		
		
		
	}
	
	
}
