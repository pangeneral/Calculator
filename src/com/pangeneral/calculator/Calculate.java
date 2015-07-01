package com.pangeneral.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;



public class Calculate {
	
	private List<Object> expressionList= new ArrayList<Object>();//�洢ת���ĺ�׺���ʽ
	private Stack<Character> operStack = new Stack<Character>();//�洢�����������׺���ʽת��Ϊ��׺���ʽʱʹ��
	private Stack<Double> resultStack = new Stack<Double>();//�洢������
	
	private Map<Character,Integer> leftPri = new HashMap<Character,Integer>();//������������ȼ�
	private Map<Character,Integer> rightPri = new HashMap<Character,Integer>();//������������ȼ�
	
	
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
	 * ����׺���ʽת��Ϊ��׺���ʽ
	 * @param inputStr ��׺���ʽ
	 */
	public void transform_From_Middle_To_Appendix(String inputStr){
		int begin = 0;
		for(int i=0;i < inputStr.length();i++ ){
			if( Justify.isOperation(inputStr.charAt(i))){
				if( inputStr.charAt(i) == '-' && Justify.isOperation(inputStr.charAt(i-1)) )
					continue;
				expressionList.add(Double.valueOf(inputStr.substring(begin, i-1)));
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
	
	public void calculate_appendix_expression(){
		
	}
}
