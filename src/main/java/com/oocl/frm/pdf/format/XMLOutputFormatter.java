package com.oocl.frm.pdf.format;

import com.oocl.frm.pdf.parser.IPdfParser;
import com.oocl.frm.pdf.parser.XMLOutputParser;

public class XMLOutputFormatter extends AbstractFormatter {

	private IPdfParser pdfParser;

	public XMLOutputFormatter(XMLOutputParser xmlParser) {
		this.pdfParser=xmlParser;
		
	}
	

	@Override
	public void formatPDFText(String pdfContent) {

	}

}
