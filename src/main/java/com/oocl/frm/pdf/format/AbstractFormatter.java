package com.oocl.frm.pdf.format;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.oocl.frm.pdf.constants.PdfFormatConstants;

public abstract class AbstractFormatter {
	
	private int wordSpaceSplitCount;
    private String filterSpecialCharRegex;

	public String getFilterSpecialCharRegex() {
		return filterSpecialCharRegex;
	}

	public void setFilterSpecialCharRegex(String filterSpecialCharRegex) {
		this.filterSpecialCharRegex = filterSpecialCharRegex;
	}

	public int getWordSpaceSplitCount() {
		return wordSpaceSplitCount;
	}

	public void setWordSpaceSplitCount(int wordSpaceSplitCount) {
		this.wordSpaceSplitCount = wordSpaceSplitCount;
	}

	protected String generateSpaceSplitRegex(){
		return PdfFormatConstants.splitWordRegex+"{"+this.getWordSpaceSplitCount()+",}";
	}
	
	protected Element getRooElement(Document document){
		Element rootElement=document.addElement(PdfFormatConstants.ROOT_ELEMENT);
		return rootElement;
	}
	
	protected boolean isFilterChar(String text){
		boolean isNeedFilter=false;
		if(StringUtils.isEmpty(this.getFilterSpecialCharRegex().trim())||text.length()>1){
			return isNeedFilter;
		}
		Pattern pattern=Pattern.compile(this.getFilterSpecialCharRegex());
		Matcher matcher=pattern.matcher(text);
		if(matcher.find()){
			isNeedFilter=true;
		}
		return isNeedFilter;	
	}
	
	protected String formatWord(String word){
		return word.replaceAll(PdfFormatConstants.FORMAT_WORD_REGEX, " ");
		
	}
	
	public abstract Document formatPDFText(byte[] pdfContent) throws IOException, DocumentException;

}
