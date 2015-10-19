package com.oocl.frm.pdf.format;

import org.dom4j.Document;

import com.oocl.frm.pdf.parser.IPdfParser;
import com.oocl.frm.pdf.parser.XMLOutputParser;

public class XMLOutputFormatter extends AbstractFormatter {

	private IPdfParser pdfParser;
	private boolean isKeepPage;
	private static final String CR_CHAR="&#13";

	public boolean isKeepPage() {
		return isKeepPage;
	}


	public void setKeepPage(boolean isKeepPage) {
		this.isKeepPage = isKeepPage;
	}


	public XMLOutputFormatter(XMLOutputParser xmlParser) {
		this.pdfParser=xmlParser;
		
	}
	

	@Override
	public Document formatPDFText(byte[] pdfContent) {
		return null;
	}

}
