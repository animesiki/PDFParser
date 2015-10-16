package com.oocl.frm.pdfts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;


public class ExtractPDFTextNormal {
	
	public static void main(String[] args) throws IOException{
			String pdfPath="\\\\sha3\\isdc\\Framework\\study\\techStudy\\XSLTtemplate\\509945iv\\509945iv.pdf";
			Document pdf = PDF.open(pdfPath);
		    StringBuilder text = new StringBuilder(1024);
		    pdf.pipe(new OutputTarget(text));
		    pdf.close();
		    File file=new File("509945iv.txt");
		    FileOutputStream outputStream=new FileOutputStream(file);
		    outputStream.write(text.toString().getBytes());
		    outputStream.flush();
		    outputStream.close();
	}

}
