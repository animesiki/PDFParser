package com.oocl.frm.pdf.format;

import com.oocl.frm.pdf.parser.IPdfParser;
import com.oocl.frm.pdf.parser.TextOutputParser;

public class TextOutputFormatter extends AbstractFormatter {
	
	private IPdfParser pdfParser;

	public TextOutputFormatter(TextOutputParser textParser) {
		this.pdfParser=textParser;
	}

	@Override
	public void formatPDFText(String pdfContent) {

	}

}
