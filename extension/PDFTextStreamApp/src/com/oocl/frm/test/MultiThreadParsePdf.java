package com.oocl.frm.test;


import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadParsePdf {
	
	public static void main(String[] args){
		int corePoolSize=5;
		ScheduledThreadPoolExecutor service=new ScheduledThreadPoolExecutor(corePoolSize);
		for(int i=0;i<corePoolSize;i++){
			ParsePDFTask task=new ParsePDFTask();
			service.scheduleAtFixedRate(task, 0, 5000, TimeUnit.MILLISECONDS);
		}	
	}

}
