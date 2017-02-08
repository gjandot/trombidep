package fr.gjandot.trombidep;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;


public class DepErr extends Activity
{

	private TextView mTextView = null;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.errmsg);

		mTextView = (TextView) findViewById(R.id.errmsg);
		Bundle b = getIntent().getExtras();
		int param = -1;
		if (b != null) {
			param = b.getInt("n");
		}
		switch (param) {
			case DepList.MSG_ERR1:
				mTextView.setText(R.string.err1);
				break;
			case DepList.MSG_ERR2:
				mTextView.setText(R.string.err2);
				break;
			case DepList.MSG_ERR3:
				mTextView.setText(R.string.err3);
				break;
			//default : rien
		}
	}

	public void close(View view)
	{
		finish();
	}


} //ACTIVITY