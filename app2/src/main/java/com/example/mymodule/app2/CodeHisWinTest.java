package com.example.mymodule.app2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class CodeHisWinTest
{
	public static void main(String[] args) throws Exception
	{
		TestSSQ();
	}
	
	//转成数字集合
	public static void GetCodeNumberSet(String code, String split, HashSet<String> code_set)
	{
		String[] code_str_list = code.split(split);
		for (int i=0; i<code_str_list.length; i++)
		{
			if (code_str_list[i].isEmpty())
			{
				continue;
			}
			
			code_set.add(code_str_list[i]);
		}
	}
	
	//获取随机号码
	static String GetRandomCode(int MinCode, int MaxCode, int CodeCount, int CodeLength, boolean Seq)
	{
		ArrayList<Integer> code_list = new ArrayList<Integer>();
		
		for (int i=MinCode; i<=MaxCode; ++i)
		{
			code_list.add(i);
		}
		
		ArrayList<Integer> win_list = new ArrayList<Integer>();
		
		int k = CodeCount;
		Random r = new Random();
		while(k>0)
		{
			int pos = r.nextInt(code_list.size() + k - CodeCount);
			
			int value = code_list.get(pos);
			int value_end = code_list.get(code_list.size() + k - CodeCount - 1);
			code_list.set(pos, value_end);
			
			win_list.add(value);
			k--;
		}
		
		if (Seq)
		{
			Collections.sort(win_list);
		}
		
		StringBuffer buff = new StringBuffer();
		int i=0;
		for (Iterator<Integer> it = win_list.iterator(); it.hasNext();)
		{
			if (i > 0)
			{
				buff.append(",");
			}
			
			int value = it.next();
			String value_ = String.valueOf(value);
			
			int left = CodeLength - value_.length();

			for (int m=0; m< left; ++m)
			{
				buff.append("0");
			}
			
			buff.append(value_);
			
			i++;
		}
		
		return buff.toString();
	}
	
	//中奖号码集合结构
	static class WinCodeSet
	{
		HashSet<String> red = new HashSet<String>();
		HashSet<String> blue = new HashSet<String>();
		
		String issue = "";
	}
	
	//待校验号码结构
	static class CheckCodeSet
	{
		HashSet<String> red_dan = new HashSet<String>();
		HashSet<String> red_tuo = new HashSet<String>();
		HashSet<String> blue_dan = new HashSet<String>();
		HashSet<String> blue_tuo = new HashSet<String>();
	}
	
	//比对单个奖等结果
	static class CheckResult
	{
		String Desc = "";
		int count = 0;
		String issue = "";
	}
	
	//比对奖等结果列表
	static class CheckResultList
	{
		ArrayList<CheckResult> result_list = new ArrayList<CheckResult>();
		
		//按照比对结果序号更新结果
		public void Update(int seq, String issue)
		{
			CheckResult result = result_list.get(seq);
			result.count++;
			result.issue = issue;
		}
		
		public void Print()
		{
			for (CheckResult result : result_list)
			{
				System.out.println(result.Desc + ":" + result.count + "个,期号:" + result.issue);
			}
		}
		
		public CheckResultList()
		{
			CheckResult result1 = new CheckResult();
			result1.Desc = "一等奖";
			result_list.add(result1);
			
			CheckResult result2 = new CheckResult();
			result2.Desc = "二等奖";
			result_list.add(result2);
			
			CheckResult result3 = new CheckResult();
			result3.Desc = "三等奖";
			result_list.add(result3);
			
			CheckResult result4 = new CheckResult();
			result4.Desc = "四等奖";
			result_list.add(result4);
			
			CheckResult result5 = new CheckResult();
			result5.Desc = "五等奖";
			result_list.add(result5);
			
			CheckResult result6 = new CheckResult();
			result6.Desc = "六等奖";
			result_list.add(result6);
		}
	}
	
	//校验参数
	static class CheckParam
	{
		int fore = 0;
		int after = 0;
		
		int seq = 0;
	}
	
	//校验参数列表
	static class CheckParamList
	{
		ArrayList<CheckParam> param_list = new ArrayList<CheckParam>();
		
		public int GetLevel(int fore, int after)
		{
			for (CheckParam param : param_list)
			{
				if (param.fore == fore && param.after == after)
				{
					return param.seq;
				}
			}
			
			return -1;
		}
		
		public CheckParamList()
		{
			CheckParam item1 = new CheckParam();
			item1.fore = 6;
			item1.after = 1;
			item1.seq = 0;
			param_list.add(item1);
			
			CheckParam item2 = new CheckParam();
			item2.fore = 6;
			item2.after = 0;
			item2.seq = 1;
			param_list.add(item2);
			
			CheckParam item3 = new CheckParam();
			item3.fore = 5;
			item3.after = 1;
			item3.seq = 2;
			param_list.add(item3);
			
			CheckParam item4 = new CheckParam();
			item4.fore = 5;
			item4.after = 0;
			item4.seq = 3;
			param_list.add(item4);
		
			CheckParam item5 = new CheckParam();
			item5.fore = 4;
			item5.after = 1;
			item5.seq = 3;
			param_list.add(item5);
			
			CheckParam item6 = new CheckParam();
			item6.fore = 4;
			item6.after = 0;
			item6.seq = 4;
			param_list.add(item6);
			
			CheckParam item7 = new CheckParam();
			item7.fore = 3;
			item7.after = 1;
			item7.seq = 4;
			param_list.add(item7);
			
			CheckParam item8 = new CheckParam();
			item8.fore = 2;
			item8.after = 1;
			item8.seq = 5;
			param_list.add(item8);
			
			CheckParam item9 = new CheckParam();
			item9.fore = 1;
			item9.after = 1;
			item9.seq = 5;
			param_list.add(item9);
			
			CheckParam item10 = new CheckParam();
			item10.fore = 0;
			item10.after = 1;
			item10.seq = 5;
			param_list.add(item10);
		}
	}
	
	
	static void TestSSQ() throws Exception
	{
		ArrayList<WinCodeSet> his = new ArrayList<WinCodeSet>();
		
		for (int i=0; i< 1000; ++i)
		{
			String win_code = GetRandomCode(1, 33, 6, 2, true) + "|" + GetRandomCode(1, 16, 1, 2, true);
			
			String[] win_code_list = win_code.split("[|]");
			
			WinCodeSet win_code_st = new WinCodeSet();
			GetCodeNumberSet(win_code_list[0], ",", win_code_st.red);
			GetCodeNumberSet(win_code_list[1], ",", win_code_st.blue);
			
			win_code_st.issue = String.valueOf(i);
			
			his.add(win_code_st);
		}
		
		//待处理注码
		
		String check_code = "01,02,03,04,05,06|01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16";
		String[] check_code_list = check_code.split("[|]");
		
		String []fore_code_list = check_code_list[0].split("\\*");
		String []after_code_list = check_code_list[1].split("\\*");
		
		CheckCodeSet check_code_set = new CheckCodeSet();
		
		if (fore_code_list.length >= 2)
		{
			GetCodeNumberSet(fore_code_list[0], ",", check_code_set.red_dan);
			GetCodeNumberSet(fore_code_list[1], ",", check_code_set.red_tuo);
		}
		else
		{
			GetCodeNumberSet(fore_code_list[0], ",", check_code_set.red_tuo);
		}
		
		if (after_code_list.length >= 2)
		{
			GetCodeNumberSet(after_code_list[0], ",", check_code_set.blue_dan);
			GetCodeNumberSet(after_code_list[1], ",", check_code_set.blue_tuo);
		}
		else
		{
			GetCodeNumberSet(after_code_list[0], ",", check_code_set.blue_tuo);
		}
		
		//前面是预处理
		
		CheckParamList check_param = new CheckParamList();
		
		CheckResultList check_result = new CheckResultList();
		
		long start = new Date().getTime();
		
		//计算
		for (WinCodeSet win_set : his)
		{
			int red_count = 0;
			
			if (!check_code_set.red_dan.isEmpty())
			{
				HashSet<String> check = new HashSet<String>();
				check.addAll(win_set.red);
				check.retainAll(check_code_set.red_dan);
				
				red_count+=check.size();
			}
			
			HashSet<String> check_red = new HashSet<String>();
			check_red.addAll(win_set.red);
			check_red.retainAll(check_code_set.red_tuo);
			red_count += check_red.size();
			
			int blue_count = 0;
			
			if (!check_code_set.blue_dan.isEmpty())
			{
				HashSet<String> check = new HashSet<String>();
				check.addAll(win_set.blue);
				check.retainAll(check_code_set.blue_dan);
				
				blue_count += check.size();
			}
			
			HashSet<String> check_blue = new HashSet<String>();
			check_blue.addAll(win_set.blue);
			check_blue.retainAll(check_code_set.blue_tuo);
			blue_count += check_blue.size();
			
			int seq = check_param.GetLevel(red_count, blue_count);
			
			if (seq >= 0)
			{
				check_result.Update(seq, win_set.issue);
			}
		}
		
		long diff = new Date().getTime() - start;
		System.out.println("CheckTime:" + diff);
		
		//输出结果
		check_result.Print();
	}
}
