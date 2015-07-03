package com.pangeneral.calculator;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;


public class MainActivity extends Activity implements OnClickListener{

	private StringBuilder input;//屏幕的输入
	private boolean isInputPoint;//是否能输入小数点
	private char preview;
	private char current;
	private Calculate calculator = new Calculate();
	private CalculateState calState;
	
	
	
    public CalculateState getCalState() {
		return calState;
	}

	public void setCalState(CalculateState calState) {
		switch(calState){
			case BEGIN:
				this.initialization();
				break;
			case INPUT:
				break;
			case RESULT:
				break;
		}
		this.calState = calState;
	}

	public char getPreview() {
		return preview;
	}

	public void setPreview(char preview) {
		this.preview = preview;
	}

	public char getCurrent() {
		return current;
	}

	public void setCurrent(char current) {
		this.current = current;
	}

	private boolean isOperation(int viewId){
    	if( viewId == R.id.Btn_Plus || viewId == R.id.Btn_Minus ||
    		viewId == R.id.Btn_Mutiply || viewId == R.id.Btn_Divine){
    		return true;
    	}
    	else
    		return false;
    }

	public boolean isNumber(int viewId){
    	if( viewId == R.id.Btn_One || viewId == R.id.Btn_Two ||
    		viewId == R.id.Btn_Three || viewId == R.id.Btn_Four ||
    		viewId == R.id.Btn_Five || viewId == R.id.Btn_Six ||
    		viewId == R.id.Btn_Seven || viewId == R.id.Btn_Eight ||
    		viewId == R.id.Btn_Nine || viewId == R.id.Btn_Zero){
    		return true;
    	}
    	else 
    		return false;
    }

	public StringBuilder getInput() {
		return input;
	}

	public void setInput(StringBuilder input) {
		this.input = input;
	}
	
	public boolean getIsInputPoint() {
		return isInputPoint;
	}

	public void setIsInputPoint(boolean isInputPoint) {
		this.isInputPoint = isInputPoint;
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        originalize_Button();
        this.setOnClickListener();
        this.setCalState(CalculateState.BEGIN);
    }
	
	
	private void originalize_Button(){
		LinearLayout tLayout = (LinearLayout)findViewById(R.id.Llo_Button);
		for(int i=0;i < tLayout.getChildCount();i++){
			LinearLayout row = (LinearLayout)tLayout.getChildAt(i);
			row.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
						LayoutParams.FILL_PARENT,1.0f));
			for(int j=0;j < row.getChildCount();j++){
				row.getChildAt(j).setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
						LayoutParams.FILL_PARENT,1.0f));
			}
		}
	}
	
	/**
	 * 设置私有成员变量的值
	 */
	private void initialization(){
		this.setInput(new StringBuilder("0"));
		this.setIsInputPoint(true);
		this.setPreview('0');
	}
    
    private void setOnClickListener(){
    	findViewById(R.id.Btn_One).setOnClickListener(this);
    	findViewById(R.id.Btn_Two).setOnClickListener(this);
    	findViewById(R.id.Btn_Three).setOnClickListener(this);
    	findViewById(R.id.Btn_Four).setOnClickListener(this);
    	findViewById(R.id.Btn_Five).setOnClickListener(this);
    	findViewById(R.id.Btn_Six).setOnClickListener(this);
    	findViewById(R.id.Btn_Seven).setOnClickListener(this);
    	findViewById(R.id.Btn_Eight).setOnClickListener(this);
    	findViewById(R.id.Btn_Nine).setOnClickListener(this);
    	findViewById(R.id.Btn_Zero).setOnClickListener(this);
    	findViewById(R.id.Btn_Plus).setOnClickListener(this);
    	findViewById(R.id.Btn_Minus).setOnClickListener(this);
    	findViewById(R.id.Btn_Mutiply).setOnClickListener(this);
    	findViewById(R.id.Btn_Divine).setOnClickListener(this);
    	findViewById(R.id.Btn_Equal).setOnClickListener(this);
    	findViewById(R.id.Btn_Cancel).setOnClickListener(this);
    	findViewById(R.id.Btn_Initial).setOnClickListener(this);
    }
    
    
    
    private void appendInput(View v){
    	this.getInput().append(((Button)v).getText());
    	this.setPreview(((Button)v).getText().charAt(0));
    }

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public TextView getResultText(){
    	return (TextView)this.findViewById(R.id.Et_result);
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
    

    /**
     * 按下DEL按钮
     */
    private void cancelButton(){
    	if( this.getInput().length() > 1 ){
    		this.getInput().delete(this.getInput().length()-1, this.getInput().length());
    		this.setPreview(this.getInput().charAt(this.getInput().length()-1));
    		this.showInput(this.getInput().toString());
    	}
    	else if( this.getInput().length() == 1){
    		this.initialization();
    		this.showInput(this.getInput().toString());
    	}
    }
    
    /**
     * 显示结果
     * @param result
     */
    private void showInput(String result){
    	TextView resultText = this.getResultText();
    	resultText.setText(result);
    }

	@Override
	/**
	 * 要重写
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.Btn_Cancel){
			cancelButton();
		}
		else if( v.getId() == R.id.Btn_Initial){
			this.initialization();
			this.showInput(this.getInput().toString());
		}
		else if(v.getId() != R.id.Btn_Equal){
			if( this.isOperation(v.getId())){
				if( !this.isOperation(this.getPreview()) ){//前一个输入符不是运算符
					this.appendInput(v);
					this.setIsInputPoint(true);
				}
				else if( this.getPreview() == R.id.Btn_Mutiply && v.getId() == R.id.Btn_Minus){
					this.appendInput(v);
				}	
			}
			else if( v.getId() == R.id.Btn_Point ){//当前输入的是小数点
				if( this.getIsInputPoint() ){//当前是否可以输入小数点
					this.appendInput(v);
					this.setIsInputPoint(false);//输入小数点后置为false
				}
			}
			else{
				this.appendInput(v);
			}
			this.showInput(this.getInput().toString());
		}
		else{
			try{
				String calculateResult = calculator.calculate_Input_String(input.toString());
				this.showInput(input+"\n"+calculateResult);
				input.delete(0, input.length());
				input.append(calculateResult);
				this.setCalState(CalculateState.RESULT);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				this.initialization();
			}
			
		}
	}
}
