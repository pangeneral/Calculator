package com.pangeneral.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;



public class Calculate {
	
	private List<Object> expressionList= new ArrayList<Object>();//存储转化的后缀表达式
	private Stack<Character> operStack = new Stack<Character>();//存储运算符，在中缀表达式转化为后缀表达式时使用
	private Stack<Double> resultStack = new Stack<Double>();//存储运算结果
	
	private Map<Character,Integer> leftPri = new HashMap<Character,Integer>();//左运算符的优先级
	private Map<Character,Integer> rightPri = new HashMap<Character,Integer>();//右运算符的优先级
	
	
	public Calculate(){
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
	 * 将中缀表达式转化为后缀表达式
	 * @param inputStr 中缀表达式
	 */
	public void transform_From_Middle_To_Appendix(String inputStr){
		int begin = 0;
		for(int i=0;i < inputStr.length();i++ ){
			if( Justify.isOperation(inputStr.charAt(i))){
				if( inputStr.charAt(i) == '-' && Justify.isOperation(inputStr.charAt(i-1)) )
					continue;
				expressionList.add(Double.valueOf(inputStr.substring(begin, i-1)));
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
	
	public void calculate_appendix_expression(){
		
	}
}
