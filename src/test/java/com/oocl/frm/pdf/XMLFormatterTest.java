package com.oocl.frm.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.DocumentException;

import com.oocl.frm.pdf.format.AbstractFormatter;
import com.oocl.frm.pdf.format.OutputDecorator;
import com.oocl.frm.pdf.format.XMLOutputFormatter;
import com.oocl.frm.pdf.parser.XMLOutputParser;

public class XMLFormatterTest {
	
	public static void main(String[] args) throws IOException, DocumentException{
		byte[] pdfContent=TextFormatterTest.toByteArray("26.pdf");
		XMLOutputParser parser=new XMLOutputParser();
		AbstractFormatter formatter=new XMLOutputFormatter(parser);
		formatter.setWordSpaceSplitCount(2);
		formatter.setFilterSpecialCharRegex(";|：");
		((XMLOutputFormatter) formatter).setKeepPage(true);
		OutputDecorator decorator=new OutputDecorator(formatter);
		String result=decorator.generateResultDoc(pdfContent);
		File outputFile=new File("jiuyan.xml");
		FileOutputStream outputStream=new FileOutputStream(outputFile);
		outputStream.write(result.getBytes());
		outputStream.flush();
		outputStream.close();
		
	}

}
