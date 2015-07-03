package com.pangeneral.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


/**
 * ʵ��ȫ���ļ������
 * @author Pangeneral
 *
 */
public class Calculate {
	
	private List<Object> expressionList= new ArrayList<Object>();//�洢ת���ĺ�׺���ʽ
	private Stack<Character> operStack = new Stack<Character>();//�洢�����������׺���ʽת��Ϊ��׺���ʽʱʹ��
	private Stack<Double> resultStack = new Stack<Double>();//�洢������
	
	private Map<Character,Integer> leftPri = new HashMap<Character,Integer>();//������������ȼ�
	private Map<Character,Integer> rightPri = new HashMap<Character,Integer>();//������������ȼ�
	
	
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
	 * ������������봮�Ľ��
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
	 * ��ʼ��������
	 */
	public void initialization(){
		expressionList.clear();
		operStack.clear();
		resultStack.clear();
	}
	
	/**
	 * ����׺���ʽת��Ϊ��׺���ʽ
	 * @param inputStr ��׺���ʽ
	 */
	private void transform_From_Middle_To_Appendix(String inputStr){
		int begin = 0;
		for(int i=0;i < inputStr.length();i++ ){
			if( Justify.isOperation(inputStr.charAt(i))){//��ǰ���ַ������������+,-,*,/�е�һ��
				if( inputStr.charAt(i) == '-' && Justify.isOperation(inputStr.charAt(i-1)) )
					continue;
				expressionList.add(Double.valueOf(inputStr.substring(begin, i)));
				begin=i+1;
				//���������ջ��Ϊ���ұ��ʽ��������ȼ���ջ����������ȼ�С����ôһֱ���г�ջ������������������뵽��׺���ʽ��
				while(!this.operStack.isEmpty() && rightPri.get(inputStr.charAt(i)) < leftPri.get(this.operStack.peek()))
					expressionList.add(this.operStack.pop());
				this.operStack.push(inputStr.charAt(i));
			}
			if( i == inputStr.length()-1){//���һ��������
				expressionList.add(Double.valueOf(inputStr.substring(begin, i+1)));
			}
		}
		while( !this.operStack.isEmpty())
			expressionList.add(this.operStack.pop());
	}
	
	/**
	 * �����׺���ʽ��ֵ
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
	 * ������������������
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
