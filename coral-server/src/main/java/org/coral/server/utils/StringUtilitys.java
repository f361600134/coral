package org.coral.server.utils;

import java.util.List;

public class StringUtilitys {
	
	/**
	 * 形如字符串：明天{0}的{1}日子,"1-1","好"
	 * 组合成: 明天1-1的好日子
	 */
	public static String formatString(String format, List<Object> args)
	{
		int index = 0;
		for(Object arg : args)
		{
			String data = String.valueOf(arg);
			format = format.replaceAll("\\{"+index+"\\}", data);
			index++;
		}
		return format;
	}
	
	/**
	 * 是否有特殊字符
	 */
	public static boolean invalidFlag(String txt)
	{
		int txtLen = txt.length();
		txt = txt.replaceAll(" ", "");
		if(txt.length()!= txtLen)
			return true;
		else if(txt.indexOf("@")>=0 || txt.indexOf("%")>=0 || txt.indexOf("&")>=0
				 || txt.indexOf(",")>=0 || txt.indexOf("_")>=0
				 || txt.indexOf("'")>=0 || txt.indexOf("\"")>=0
				 || txt.indexOf("`")>=0)
			return true;
		try
		{
//			URI.create(txt);
		}
		catch(Exception ex)
		{
			return true;
		}
		return false;
	}

}
