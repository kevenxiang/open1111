package com.open1111.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * properties配置文件工具类
 * @author xiang
 *
 */
public class PropertiesUtil {
	
	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		Properties prop = new Properties();
		InputStream in = new PropertiesUtil().getClass().getResourceAsStream("/open1111.properties");
		try {
			prop.load(in);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return prop.getProperty(key);
	}

}






























