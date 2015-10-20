package com.oocl.frm.pdf.parser;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.VisualOutputTarget;

public class TextOutputParser implements IPdfParser {

	public String parsePDF(byte[] pdfContent) throws IOException {
		Document pdf = PDF.open(new ByteArrayInputStream(pdfContent), null);
		StringBuilder text = new StringBuilder(1024);
		pdf.pipe(new VisualOutputTarget(text));
		pdf.close();
		return text.toString();
	}
}
