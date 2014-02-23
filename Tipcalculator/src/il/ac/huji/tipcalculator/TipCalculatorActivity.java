package il.ac.huji.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {
	private static final float TIP_PRECENTAGE = 0.12f;
	private EditText mBillAmountInput;
	private CheckBox mRoundCheckBox;
	private Button mCalcButton;
	private TextView mTipResult;
	private boolean mRoundChecked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		initViews();
	}

	private void initViews() {
		mBillAmountInput = (EditText) findViewById(R.id.ev_bill_amount_in);
		mRoundCheckBox = (CheckBox) findViewById(R.id.cb_round);
		mCalcButton = (Button) findViewById(R.id.b_calculate);
		mCalcButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				float tip = Float
						.valueOf(mBillAmountInput.getText().toString())
						* TIP_PRECENTAGE;
				
				if(mRoundCheckBox.isChecked()){
					float r = (float) Math.round(tip);
					mTipResult.setText("$" + String.valueOf((int)r));
				}else{
					mTipResult.setText("$" + String.format("%.2f",tip));					
				}
				InputMethodManager imm = (InputMethodManager)getSystemService(
					      Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(mBillAmountInput.getWindowToken(), 0);
			}

		});
		mTipResult = (TextView) findViewById(R.id.tv_tip_result);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calculator, menu);
		return true;
	}

}
