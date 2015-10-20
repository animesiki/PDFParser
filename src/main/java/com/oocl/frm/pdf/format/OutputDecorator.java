package com.oocl.frm.pdf.format;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;

public class OutputDecorator extends AbstractFormatter {

	private AbstractFormatter formatter;

	public OutputDecorator(AbstractFormatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public Document formatPDFText(byte[] pdfContent) throws IOException,
			DocumentException {
		return this.formatter.formatPDFText(pdfContent);
	}

	public String generateResultDoc(byte[] pdfContent) throws IOException,
			DocumentException {
		return this.formatPDFText(pdfContent).asXML();

	}

}
