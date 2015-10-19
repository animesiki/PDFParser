package com.oocl.frm.pdf;

import java.io.IOException;

import org.dom4j.DocumentException;

import com.oocl.frm.pdf.format.XMLOutputFormatter;
import com.oocl.frm.pdf.parser.XMLOutputParser;

public class XMLFormatterTest {
	
	public static void main(String[] args) throws IOException, DocumentException{
		byte[] pdfContent=TextFormatterTest.toByteArray("26.pdf");
		XMLOutputParser parser=new XMLOutputParser();
		XMLOutputFormatter formatter=new XMLOutputFormatter(parser);
		formatter.formatPDFText(pdfContent);
		
	}

}
