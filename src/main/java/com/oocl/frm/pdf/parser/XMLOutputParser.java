package com.oocl.frm.pdf.parser;

import java.io.IOException;

import pdfts.examples.XMLOutputTarget;

import com.snowtide.PDF;
import com.snowtide.pdf.Document;

public class XMLOutputParser implements IPdfParser{
	
	public String parsePDF(byte[] pdfContent) throws IOException{
		Document pdf = PDF.open(new String(pdfContent));
		XMLOutputTarget xmlOutputTarget = new XMLOutputTarget();
		pdf.pipe(xmlOutputTarget);
		pdf.close();
		return xmlOutputTarget.getXMLAsString();
	}

}
