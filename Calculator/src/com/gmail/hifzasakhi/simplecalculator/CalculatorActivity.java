package com.gmail.hifzasakhi.simplecalculator;

import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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
	private double ans = 0;
	private double input = 0;
	private String[] operators;
	private Map<Button, String> btnMap;
	private Map<Button, String> opMap;
	private boolean isCleared;
	private boolean hasOperator;
	private boolean isEqual;
	private double first;
	private double second;
	private int start;
	private int end;
	private View opView;
	
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
        isCleared = false;
        isEqual = false;
        opView = null;
        first = second = start = end = 0;
        
        //operator array
        operators = new String[4];
        operators[0] = "+";
        operators[1] = "-";
        operators[2] = "*";
        operators[3] = "/";
        
        //operator mappings
        opMap = new HashMap<Button, String>();
        opMap.put(plus, "+");
        opMap.put(minus, "-");
        opMap.put(multiply, "*");
        opMap.put(divide, "/");
        
        //digit mappings
        btnMap = new HashMap<Button, String>();
        btnMap.put(zero, "0");
        btnMap.put(one, "1");
        btnMap.put(two, "2");
        btnMap.put(three, "3");
        btnMap.put(four, "4");
        btnMap.put(five, "5");
        btnMap.put(six, "6");
        btnMap.put(seven, "7");
        btnMap.put(eight, "8");
        btnMap.put(nine, "9");
        
        //hooking up the button listeners
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
			isCleared = true;
			hasOperator = false;
			opView = null;
			first = 0;
			start = 0;
			end = 0;
			second = 0;
			updateDisplay("0");
		} else if (v == exit) {
			System.exit(0);
		} else if (v == equals) {
			if (hasOperator) {
				 double ans = calculate(opView, first, start);
				 updateDisplay(ans + "");
				 hasOperator= false;
			}
			isEqual = true;
		//digit was selected
		} else if (btnMap.get(v) != null) {
			String str = text.getText().toString();
			if (isCleared && text.getText().toString().length() == 1) {
				//changes the first digit from the cleared 0 to user input
				isCleared = false;
				updateDisplay(btnMap.get(v));
				
			 } else if (isEqual) {
				isEqual = false; 
				updateDisplay("");
				updateDisplay(btnMap.get(v));
			 } else {
				/*
				 * If screen was not cleared prior to this digit,
				 * then simply update the screen with the digit
				 */
				updateDisplay(text.getText() + btnMap.get(v));
			}
		/* After the if, we know for sure that the button pressed
		 * was not a digit, =, clear, nor exit
		 * but rather a decimal or a math operator	
		 */
		} else if (v == decimal) {
			String str = text.getText().toString();
			if ((str.charAt(str.length() - 1)) != '.') {
				//makes sure we don't have 2 consecutive decimals
				updateDisplay(text.getText() + ".");
			}
		} else {
			String str = text.getText().toString();
			
			//we now know its an operator
			
			//can't put operator as the first character in display
			if (str.length() == 0) {
					
					
			/* Now check to see if 2 operators were selected consecutively.
			 */
			} else if ((str.charAt(str.length() - 1) + "" == opMap.get(plus)) ||
					(str.charAt(str.length() - 1) + "" == opMap.get(minus)) ||
					(str.charAt(str.length() - 1) + "" == opMap.get(multiply))||
					(str.charAt(str.length() - 1) + "" == opMap.get(divide))) {
						
			   /*
			    * Since operator was selected, we need to store the left 
			    * and right hand side of operator numbers in first and second
			    * variables. we store the start location of the second number. 
			    */
				first = 1.0 * Double.valueOf(text.getText().toString());
				//start refers to beginning location of second number after 
				//an arithmetic operator
				start = text.getText().toString().length();
				hasOperator = true;
				opView = v;
				 //removes the last operator from the displays
			    //update display with the new (recent) operator
			    updateDisplay(str.substring(0, str.length() - 1) + "" + opMap.get(v));
			 } else if (hasOperator) {
				 double ans = calculate(opView, first, start);
				 first = 1.0 * Double.valueOf(text.getText().toString());
				 start = text.getText().toString().length();
				 hasOperator = true;
				 opView = v;
				 updateDisplay(ans + "" + opMap.get(v));
			 } else {
				 first = 1.0 * Double.valueOf(text.getText().toString());
				 start = text.getText().toString().length();
				 hasOperator = true;
				 opView = v;
				 //now, we have no restriction on placing the operator
			   	updateDisplay(text.getText() + opMap.get(v));
				
		     }
			
		}
	}
			
	private void getDefaults() {
		end = text.getText().length();
		second = Double.valueOf((text.getText()).toString().substring(start + 1, end));
		double num = 0;
	}
	
    private double calculate(View v, double first, int start) {
		if (v == plus) {
			getDefaults(); 
			return 1.0 * (first + second);
		} else if (v == minus) {
			getDefaults(); 
			return 1.0 * (first - second);
		} else if (v == multiply) {
			getDefaults(); 
			return 1.0 * (first * second);
		} else {
			getDefaults(); 
			return 1.0 * (first / second);
		}
	}
    
	public void updateDisplay(String option) {
		text.setText(option);
	}
}
