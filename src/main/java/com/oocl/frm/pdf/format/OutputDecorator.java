package com.oocl.frm.pdf.format;

public class OutputDecorator extends AbstractFormatter {

	private AbstractFormatter formatter;

	public OutputDecorator(AbstractFormatter formatter) {
		this.formatter=formatter;
		
	}

	@Override
	public void formatPDFText(String pdfContent) {

	}

}
