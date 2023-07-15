package com.lanou.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.*;
import java.util.Properties;

public class DBUtil {
    
    public static DataSource source() {
		DataSource source = null;
		try {
			InputStream inputStream = new FileInputStream(new File("./src/druid.properties"));
			Properties properties = new Properties();
			properties.load(inputStream);
			source = DruidDataSourceFactory.createDataSource(properties);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return source;
	}
    
    public static void main(String[] args) {
		System.out.println(source());
	}

}
