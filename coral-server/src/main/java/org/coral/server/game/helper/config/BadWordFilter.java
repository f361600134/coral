package org.coral.server.game.helper.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

public class BadWordFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(BadWordFilter.class);
	
	//敏感字符过滤,键盘可见字符全部存在.
	//public static final Pattern PATTERN = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？丶]");
	//敏感字符过滤,只允许汉字,字母,数字.
	public static final Pattern PATTERN2 = Pattern.compile("[^\u4e00-\u9fa5a-zA-Z0-9]*");
	
	private static final int MinMatchType = 1;      //最小匹配规则
	private static final int MaxMatchType = 2;      //最大匹配规则
	
	@SuppressWarnings("rawtypes")
	private static HashMap sensitiveWordMap = new HashMap();
	
	/**
	 * 用于检测名字是否存在敏感字
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return 如果存在，则返回敏感词字符的长度，不存在返回0
	 */
	@SuppressWarnings({ "rawtypes"})
	private static int checkName(String txt, int beginIndex, int matchType){
		boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0;     //匹配标识数默认为0
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for(int i = beginIndex; i < txt.length() ; i++){
			word = txt.charAt(i);
			if (!nowMap.containsKey(word)) {
				//如果有字符.则找不到map,跳过这次执行
				matchFlag++;
				continue;
			}
			nowMap = (Map) nowMap.get(word);     //获取指定key
			if(nowMap != null){     //存在，则判断是否为最后一个
				matchFlag++;     //找到相应key，匹配标识+1 
				if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true;       //结束标志位为true   
					if(MinMatchType == matchType){    //最小规则，直接返回,最大规则还需继续查找
						break;
					}
				}
			}
		}
		if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词 
			matchFlag = 0;
		}
		return matchFlag;
	}
	
	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 大 = {
	 *      isEnd = 0
	 *      蠢= {<br>
	 *      	 isEnd = 1
	 *           货 = {
	 *           	isEnd = 1
	 *           	}
	 *           }
	 *      二  = {
	 *           isEnd = 0
	 *           货 = {
	 *           	isEnd = 1
	 *           	}
	 *           }
	 *           
	 *      }
	 * @param keyWordSet  敏感词库
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void addSensitiveWord(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
		String key = null;  
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取
				
				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}
				else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					nowMap = newWorMap;
				}
				
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
		}
	}
	
	/**
	 * 获取名字中的敏感词
	 * @param txt 文字
	 * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return
	 */
	private static boolean hasBadWord(String txt , int matchType){
		for(int i = 0 ; i < txt.length(); i++){
			int length = checkName(txt, i, matchType);    //判断是否包含敏感字符
			if(length > 0){    //存在,加入list中
				LOGGER.info("hasBadWord  {}", txt.substring(i, i+length));
				return true;
			}
		}
		return false;
	}
	
	/////////////////////////// 以下是聊天相关 //////////////
	/**
	 * 获取替换字符串
	 * @param replaceChar
	 * @param length
	 * @return 获取到的敏感字符
	 */
	private static String getReplaceChars(String replaceChar,int length){
		String resultReplace = replaceChar;
		for(int i = 1 ; i < length ; i++){
			resultReplace += replaceChar;
		}
		return resultReplace;
	}
	
//	/**
//	 * 替换敏感字字符
//	 * @param txt
//	 * @param matchType
//	 * @param replaceChar 替换字符，默认*
//	 */
//	public static String doChatFilter(String txt){
//		//先去除 " " 和 "&"
////		txt = removecCharacter(txt);
//		//替换
//		String resultTxt = doChatFilter(txt, 1, "*");
//		return resultTxt;
//	}
	
//	/**
//	 * 替换敏感字字符
//	 * @param txt
//	 * @param matchType
//	 * @param replaceChar 替换字符，默认*
//	 */
//	public static boolean doChatBooleanFilter(String txt){
//		Set<String> set = getBadChatWord(txt, 1);     //获取所有的敏感词
//		if (set.isEmpty()) {
//			return false;
//		}
//		return true;
//	}

	/**
	 * 获取名字中的敏感词
	 * @param txt 文字
	 * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
	 * @return
	 */
	private static Set<String> getBadWord(String txt , int matchType){
		Set<String> sensitiveWordSet = new HashSet<String>();
		for(int i = 0 ; i < txt.length() ; i++){
			int length = checkBadWord(txt, i, matchType);    //判断是否包含敏感字符
			if(length > 0){    //存在,加入list中
				sensitiveWordSet.add(txt.substring(i, i+length));
				i = i + length - 1;    //减1的原因，是因为for会自增
			}
		}
		return sensitiveWordSet;
	}
	
	/**
	 * 检查聊天中是否存在敏感字
	 * @param txt
	 * @param beginIndex
	 * @param matchType
	 * @return 如果存在，则返回敏感词字符的长度，不存在返回0
	 */
	@SuppressWarnings({ "rawtypes"})
	private static int checkBadWord(String txt,int beginIndex,int matchType){
		boolean  flag = false;    //敏感词结束标识位：用于敏感词只有1位的情况
		int matchFlag = 0;     //匹配标识数默认为0
		char word = 0;
		Map nowMap = sensitiveWordMap;
		for(int i = beginIndex; i < txt.length() ; i++){
			word = txt.charAt(i);
			nowMap = (Map) nowMap.get(word);     //获取指定key
			if(nowMap != null){     //存在，则判断是否为最后一个
				matchFlag++;     //找到相应key，匹配标识+1 
				if("1".equals(nowMap.get("isEnd"))){       //如果为最后一个匹配规则,结束循环，返回匹配标识数
					flag = true;       //结束标志位为true   
					if(MinMatchType == matchType){    //最小规则，直接返回,最大规则还需继续查找
						break;
					}
				}
			}
			else{     //不存在，直接返回
				break;
			}
		}
		if(matchFlag < 2 || !flag){        //长度必须大于等于1，为词 
			matchFlag = 0;
		}
		return matchFlag;
	}
	
	/**
	 * 是否有特殊字符
	 * @param txt 文本
	 * @return
	 */
	public static boolean hasSpecialchar(String txt) {
		Matcher matcher = PATTERN2.matcher(txt);
		return matcher.find(); 
	}
	
	/**
	 * 是否有敏感字
	 * @param txt
	 * @return
	 */
	public static boolean hasBadChar(String txt) {
		return hasBadWord(txt, MinMatchType);
	}
	
	/**
	 * 对文本进行过滤
	 * @param txt
	 * @return  
	 * @return String  
	 * @date 2020年8月26日下午8:43:06
	 */
	public static String doFilter(String txt) {
		txt = specialCharFilter(txt);
		txt = badCharFilter(txt);
		return txt;
	}
	
	/**
	 * 去掉特殊字符
	 * @param txt
	 */
	public static String specialCharFilter(String txt) {
		Matcher matcher = PATTERN2.matcher(txt);
		txt = matcher.replaceAll("");
		return txt;
	}
	
	/**
	 * 去掉敏感字符, 默认匹配类型, 默认替换成*
	 * @param txt
	 */
	public static String badCharFilter(String txt) {
		return badCharFilter(txt, MinMatchType, "*");
	}
	
	/**
	 * 替换敏感字字符
	 * @param txt 文本
	 * @param matchType 匹配类型
	 * @param replaceChar 替换字符
	 */
	public static String badCharFilter(String txt, int matchType, String replaceChar){
		String resultTxt = txt;
		Set<String> set = getBadWord(txt, matchType);     //获取所有的敏感词
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			resultTxt = resultTxt.replaceAll(word, replaceString);
		}
		return resultTxt;
	}


	public static void loadSensitiveWordTxt()throws Exception{
		Set<String> set = Sets.newHashSet();
		File file = new File("/SensitiveWord.txt");    //读取文件
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");
		try {
			if(file.isFile() && file.exists()){      //文件流是否存在
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while((txt = bufferedReader.readLine()) != null){
					String words[] = txt.split(",");
					for (String string : words) {
						set.add(string);
					}
				}
				BadWordFilter.addSensitiveWord(set);
				bufferedReader.close();
			}
		}catch (Exception e) {
			LOGGER.error("loadSensitiveWordTxt error. e:", e);
		}finally {
			read.close();
		}
	}
	
	public static void main(String[] args) {
		addSensitiveWord(Sets.newHashSet("傻逼"));
		String txt = "aa你好!!!!!!@傻①逼";
//		String txt = "aa你好!!@大傻逼";
		boolean bool  = hasSpecialchar(txt);
		System.out.println("是否非法:"+bool);
		txt = doFilter(txt);
		System.out.println(txt);
	}

}
