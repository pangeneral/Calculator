package com.pangeneral.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


/**
 * 实现全部的计算操作
 * @author Pangeneral
 *
 */
public class Calculate {
	
	private List<Object> expressionList= new ArrayList<Object>();//存储转化的后缀表达式
	private Stack<Character> operStack = new Stack<Character>();//存储运算符，在中缀表达式转化为后缀表达式时使用
	private Stack<Double> resultStack = new Stack<Double>();//存储运算结果
	
	private Map<Character,Integer> leftPri = new HashMap<Character,Integer>();//左运算符的优先级
	private Map<Character,Integer> rightPri = new HashMap<Character,Integer>();//右运算符的优先级
	
	
	public Calculate(){
		this.initialization();
		leftPri.put('+',2);
		leftPri.put('-',2);
		leftPri.put('*',4);
		leftPri.put('/',4);
		rightPri.put('+',1);
		rightPri.put('-',1);
		rightPri.put('*',3);
		rightPri.put('/',3);
	}
	
	/**
	 * 计算给定的输入串的结果
	 * @param inputStr
	 * @return
	 */
	public String calculate_Input_String(String inputStr){
		this.transform_From_Middle_To_Appendix(inputStr);
		String result = this.calculate_Appendix_Expression()+"";
		if( result.substring(result.indexOf(".")+1).equals("0"))
			return result.substring(0,result.length()-2);
		else
			return result;
	}
	
	/**
	 * 初始化计算类
	 */
	public void initialization(){
		expressionList.clear();
		operStack.clear();
		resultStack.clear();
	}
	
	/**
	 * 将中缀表达式转化为后缀表达式
	 * @param inputStr 中缀表达式
	 */
	private void transform_From_Middle_To_Appendix(String inputStr){
		int begin = 0;
		for(int i=0;i < inputStr.length();i++ ){
			if( Justify.isOperation(inputStr.charAt(i))){//当前的字符是运算符，即+,-,*,/中的一个
				if( inputStr.charAt(i) == '-' && Justify.isOperation(inputStr.charAt(i-1)) )
					continue;
				expressionList.add(Double.valueOf(inputStr.substring(begin, i)));
				begin=i+1;
				//如果操作数栈不为空且表达式运算符优先级比栈顶运算符优先级小，那么一直进行出栈操作，并将运算符加入到后缀表达式中
				while(!this.operStack.isEmpty() && rightPri.get(inputStr.charAt(i)) < leftPri.get(this.operStack.peek()))
					expressionList.add(this.operStack.pop());
				this.operStack.push(inputStr.charAt(i));
			}
			if( i == inputStr.length()-1){//最后一个操作数
				expressionList.add(Double.valueOf(inputStr.substring(begin, i+1)));
			}
		}
		while( !this.operStack.isEmpty())
			expressionList.add(this.operStack.pop());
	}
	
	/**
	 * 计算后缀表达式的值
	 */
	private Double calculate_Appendix_Expression(){
		try{
			for(int i=0;i < expressionList.size();i++){
				if( expressionList.get(i) instanceof Double){
					resultStack.push((Double)expressionList.get(i));
				}
				else{
					Double secondNumber = resultStack.pop();
					Double firstNumber = resultStack.pop();
					resultStack.push(this.calculate_During_Process_Appendix_Expression(firstNumber,secondNumber,(Character)expressionList.get(i)));		
				}
			}
			return resultStack.pop();
		}
		catch(Exception e){
			return null;
		}
		
	}
	
	/**
	 * 计算两个数的运算结果
	 */
	private Double calculate_During_Process_Appendix_Expression(Double firstNumber,Double secondNumber,Character operation){
		switch(operation){
			case '+':
				return firstNumber+secondNumber;
			case '-':
				return firstNumber-secondNumber;
			case '*':
				return firstNumber*secondNumber;
			case '/':
				return firstNumber/secondNumber;
			default:
				return null;
		}
	}
}
