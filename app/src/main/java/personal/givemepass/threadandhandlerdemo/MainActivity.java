package personal.givemepass.threadandhandlerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
	private TextView text;
	private Button button;
	private Handler mHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text = (TextView) findViewById(R.id.text);
		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						while(true){
							try{
								Message msg = new Message();
								msg.what = 1;
								mHandler.sendMessage(msg);
								Thread.sleep(500);
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});

		mHandler = new Handler(){
			int i = 0;
			@Override
			public void handleMessage(Message msg) {
				switch(msg.what){
					case 1:
						i++;
						text.setText(Integer.toString(i));
						break;
				}
				super.handleMessage(msg);
			}
		};
	}

}
