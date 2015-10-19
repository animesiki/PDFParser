package com.oocl.frm.pdf.format;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.oocl.frm.pdf.constants.PdfFormatConstants;
import com.oocl.frm.pdf.parser.IPdfParser;
import com.oocl.frm.pdf.parser.TextOutputParser;

public class TextOutputFormatter extends AbstractFormatter {
	
	private IPdfParser pdfParser;

	public TextOutputFormatter(TextOutputParser textParser) {
		this.pdfParser=textParser;
	}

	@Override
	public Document formatPDFText(byte[] pdfContent) throws IOException {
		Document document=DocumentHelper.createDocument();
		Element rootElement=this.getRooElement(document);
		Element recordsElement=rootElement.addElement(PdfFormatConstants.RECORDS_ELEMENT);
		String sourceText=this.pdfParser.parsePDF(pdfContent);
		BufferedReader reader=new BufferedReader(new StringReader(sourceText));
		String line=null;
		while((line=reader.readLine())!=null){
			this.handleLine(recordsElement, line);
		}
		return document;
	}
	
	private void handleLine(Element recordsElemet,String line){
		if(!StringUtils.isEmpty(line.trim())){
			Element recordElement=recordsElemet.addElement(PdfFormatConstants.RECORD_ELEMENT);
			String[] words=line.split(this.generateSpaceSplitRegex());
			for(String word:words){
				if(!StringUtils.isEmpty(word.trim())){
					if(!this.isFilterChar(word)){
						Element colElement=recordElement.addElement(PdfFormatConstants.COLUMN_ELEMENT);
						colElement.setText(this.formatWord(word));
					}
				}
			}
		}
	}

}
