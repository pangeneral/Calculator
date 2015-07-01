package com.pangeneral.calculator;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements OnClickListener{

	private StringBuilder input;//屏幕的输入
	private boolean isInputPoint;//是否能输入小数点
	private char preview;
	private char current;
	
	
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
        this.setOnClickListener();
        this.setAttribute();
    }
	
	/**
	 * 设置私有成员变量的值
	 */
	private void setAttribute(){
		this.setInput(new StringBuilder(""));
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
    
    public double calculate(){
    	
    	return 0;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() != R.id.Btn_Equal){
			if( this.isOperation(v.getId())){
				if( !this.isOperation(this.getPreview()) ){
					this.appendInput(v);
					this.setIsInputPoint(true);
				}
				else if( this.getPreview() == R.id.Btn_Mutiply && v.getId() == R.id.Btn_Minus){
					this.appendInput(v);
				}	
			}
			else if( v.getId() == R.id.Btn_Point ){//当前输入的是小数点
				if( this.getIsInputPoint() ){
					this.appendInput(v);
					this.setIsInputPoint(false);
				}
			}
			else{
				this.appendInput(v);
			}
		}
		else{
			calculate();
		}
	}
}
