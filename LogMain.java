package com.jipefe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogMain {
	public static void main(String[] args) throws IOException{
		
		int num = 120;  //设置文件数量
		String fileName = "D:\\test\\log\\log_";    //设置文件目录、文件名规则
		
		for(int i=1;i<=num;i++) {
			String file = fileName+i+".txt";
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<Log> logs = new ArrayList<Log>();
			String string = null;
			//读取文件的每一行
			while( (string=reader.readLine()) != null){
				
				//看日志是否匹配蜘蛛列表
				for(SpiderType spider : SpiderType.values()) {
					if(string.contains(spider.getValue())) {
						Log log = LogFactory.getLogInstance(string,spider);
						logs.add(log);
					}
				}
			}
			new LogDao().addLog(logs);
			reader.close();
			System.out.println("第"+i+"个文件读取完成");
		}
	}
}
