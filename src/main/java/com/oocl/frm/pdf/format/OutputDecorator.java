package com.oocl.frm.pdf.format;

import java.io.IOException;

import org.dom4j.Document;

public class OutputDecorator extends AbstractFormatter {

	private AbstractFormatter formatter;

	public OutputDecorator(AbstractFormatter formatter) {
		this.formatter=formatter;
		
	}

	@Override
	public Document formatPDFText(byte[] pdfContent) throws IOException {
		return this.formatter.formatPDFText(pdfContent);
	}
	
	public String generateResultDoc(byte[] pdfContent) throws IOException{
		return this.formatPDFText(pdfContent).asXML();
		
	}

}
