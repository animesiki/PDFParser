package com.oocl.frm.test;


import java.io.IOException;

import com.oocl.frm.pdfts.ExtractTextAllPages;
import com.oocl.frm.pdfts.ExtractTextToXML;

public class ParsePDFTask implements Runnable {

	@Override
	public void run() {
		try {
			ExtractTextToXML.writeText("\\\\sha3\\isdc\\Framework\\study\\techStudy\\XSLTtemplate\\jiuyan\\26.pdf",Thread.currentThread().getName()+"_"+System.currentTimeMillis()+".xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
