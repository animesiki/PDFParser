package com.oocl.frm.pdf.parser;

import java.io.IOException;

public interface IPdfParser {
	
	String parsePDF(byte[] pdfContent) throws IOException;

}
