package com.rulaifozu.onlineschool.config;
 
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;
import java.util.Random;

public class SeedUtil {
	private static Long randomNumber;
	private static Long curIndex = Long.valueOf(0L);
 
	public SeedUtil() {
	}
	//生成8位
	 public static String getSeed() {
		 String a= RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz1234567890");
		 return a;
	 }
	 //随机生成18位不相同的字符串
	public synchronized static String getId() throws Exception {
		Long index = null;
		// 从0到999 curIndex*100 curIndex 100-99900
		index = (curIndex = curIndex.longValue() + 1L).longValue() % 1000L;
		if (curIndex.longValue() >= 1000L) {
			curIndex = 0L;
		}
		// 随机数 1-10
		randomNumber = Long.valueOf(new Random().nextInt(100));
		System.out.println((new Date()));
		System.out.println((new Date()).getTime());
		return String.valueOf((new Date()).getTime() * 100000L + index.longValue() * 100L
				+ randomNumber.longValue());
	}
	public static void main(String[] args) throws Exception {
		System.out.println(getId());
	}


}