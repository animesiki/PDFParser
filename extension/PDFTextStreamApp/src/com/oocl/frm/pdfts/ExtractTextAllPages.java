package com.oocl.frm.pdfts;

import java.io.File;
import java.io.FileOutputStream;



import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;
import com.snowtide.pdf.VisualOutputTarget;

public class ExtractTextAllPages {
	
	public static void writeText(String pdfPath,String outputPath) throws java.io.IOException {
	    Document pdf = PDF.open(pdfPath);
	    StringBuilder text = new StringBuilder(1024);
	    // pdf.pipe(new OutputTarget(text));
	    pdf.pipe(new VisualOutputTarget(text));
	    pdf.close();
	    File file=new File(outputPath);
	    FileOutputStream outputStream=new FileOutputStream(file);
	    outputStream.write(text.toString().getBytes());
	    outputStream.flush();
	    outputStream.close();
	  }

}
