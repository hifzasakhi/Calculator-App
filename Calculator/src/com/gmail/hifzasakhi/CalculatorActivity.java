package com.gmail.hifzasakhi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class CalculatorActivity extends ActionBarActivity implements OnClickListener {

	private Button one;
	private Button two;
	private Button three;
	private Button four;
	private Button five;
	private Button six;
	private Button seven;
	private Button eight;
	private Button nine;
	private Button zero;
	private Button equals;
	private Button minus;
	private Button clear;
	private Button decimal;
	private Button multiply;
	private Button plus;
	private Button divide;
	private TextView text;
	private Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        zero = (Button)findViewById(R.id.zero);
        one = (Button)findViewById(R.id.one);
        two = (Button)findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button)findViewById(R.id.four);
        five = (Button)findViewById(R.id.five);
        six = (Button)findViewById(R.id.six);
        seven = (Button)findViewById(R.id.seven);
        eight = (Button)findViewById(R.id.eight);
        nine = (Button)findViewById(R.id.nine);
        decimal = (Button)findViewById(R.id.Button10);
        equals = (Button)findViewById(R.id.eq);
        plus = (Button)findViewById(R.id.add);
        minus = (Button)findViewById(R.id.Button14);
        multiply = (Button)findViewById(R.id.Button13);
        divide = (Button)findViewById(R.id.Button12);
        text = (TextView)findViewById(R.id.textView);
        clear = (Button)findViewById(R.id.clear);
        exit = (Button)findViewById(R.id.button1);
        
        //hooking up the listeners to the buttons
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        plus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        minus.setOnClickListener(this);
        divide.setOnClickListener(this);
        equals.setOnClickListener(this);
        decimal.setOnClickListener(this);
        clear.setOnClickListener(this);
        exit.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onClick(View v) {
		if (v == clear) {
			text.setText("");
		} else if (v == one) {
			text.setText(text.getText() + "1");
		} else if (v == two) {
			text.setText(text.getText() + "2");
		} else if (v == three) {
			text.setText(text.getText() + "3");
		} else if (v == four) {
			text.setText(text.getText() + "4");
		} else if (v == five) {
			text.setText(text.getText() + "5");
		} else if (v == six) {
			text.setText(text.getText() + "6");
		} else if (v == seven) {
			text.setText(text.getText() + "7");
		} else if (v == eight) {
			text.setText(text.getText() + "8");
		} else if (v == nine) {
			text.setText(text.getText() + "9");
		} else if (v == zero) {
			text.setText(text.getText() + "0");
		} else if (v == decimal) {
			text.setText(text.getText() + ".");
		}  else if (v == plus) {
			text.setText(text.getText() + "+");
		} else if (v == minus) {
			text.setText(text.getText() + "-");
		} else if (v == multiply) {
			text.setText(text.getText() + "x");
		} else if (v == divide) {
			text.setText(text.getText() + "/");
		} else if (v == equals) {
			String toFix = ((String)text.getText()).replaceAll("x", "*");
			Double ans = Double.parseDouble(toFix);
			text.setText(ans + "");
		} else if (v == exit) {
			//System.exit(0);
			String toFix = ((String)text.getText()).replaceAll("x", "*");
			Double ans = Double.parseDouble(toFix);
			text.setText(ans + "");
		}
	}
}
