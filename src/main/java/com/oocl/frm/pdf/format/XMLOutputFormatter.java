package com.oocl.frm.pdf.format;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.oocl.frm.pdf.constants.PdfFormatConstants;
import com.oocl.frm.pdf.parser.IPdfParser;
import com.oocl.frm.pdf.parser.XMLOutputParser;

public class XMLOutputFormatter extends AbstractFormatter {

	private IPdfParser pdfParser;
	private boolean isKeepPage;

	public boolean isKeepPage() {
		return isKeepPage;
	}

	public void setKeepPage(boolean isKeepPage) {
		this.isKeepPage = isKeepPage;
	}

	public XMLOutputFormatter(XMLOutputParser xmlParser) {
		this.pdfParser = xmlParser;

	}

	@Override
	public Document formatPDFText(byte[] pdfContent) throws DocumentException,
			IOException {
		Document formatedDoc = DocumentHelper.createDocument();
		Element formatedRoot = this.getRooElement(formatedDoc);
		
		Document sourceDocument = DocumentHelper.parseText(this.pdfParser
				.parsePDF(pdfContent));
		Element sourceRoot=sourceDocument.getRootElement();
		if(this.isKeepPage()){
			@SuppressWarnings("unchecked")
			List<Element> pageElements=sourceRoot.selectNodes(PdfFormatConstants.PAGE_XPATH_EXP);
			for(Element page:pageElements){
				Element recordsElement=formatedRoot.addElement(PdfFormatConstants.RECORDS_ELEMENT);
				recordsElement.addAttribute(PdfFormatConstants.PAGE_NO_ATTR,page.attributeValue(PdfFormatConstants.PAGE_ATTR));
				treeWalkBlockElement(page, recordsElement,PdfFormatConstants.BLOCK_XPAHT_BY_PAGE_EXP);
			}				
		}else{
			Element recordsElement = formatedRoot
			.addElement(PdfFormatConstants.RECORDS_ELEMENT);
			treeWalkBlockElement(sourceRoot, recordsElement,PdfFormatConstants.BLOCK_XPATH_EXP);
		}
		return formatedDoc;
	}

	private void treeWalkBlockElement(Element sourceElement, Element recordsElement,String xpath) {
		@SuppressWarnings("unchecked")
		List<Element> elementsList = sourceElement.selectNodes(xpath);
		for (int i = 0, size = elementsList.size(); i < size; i++) {
			Element node = elementsList.get(i);
			if (node.attribute(PdfFormatConstants.BLOCK_TYPE_ATTR) != null
					&& node.attributeValue(PdfFormatConstants.BLOCK_TYPE_ATTR)
							.equals(PdfFormatConstants.TABLE_TYPE_VALUE)) {		
			    handleTableBlock(recordsElement,node);
			} else {
				handleBasicInfoBlock(recordsElement, node);
			}

		}
	}

	private void handleBasicInfoBlock(Element recordsElement, Element element) {
		// handle basic info block
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if ((node instanceof Element)
					&& (((Element) node).getName()
							.equals(PdfFormatConstants.TEXT_ELEMENT))) {
			this.splitTextToCol(recordsElement, ((Element) node).getText());
			}
		}
	}
	
	private void splitTextToCol(Element recordsElement,String text){
		String[] wordLines=text.split(PdfFormatConstants.CR_CHAR);
		for(String line:wordLines){
			if(!StringUtils.isEmpty(line.trim())){
				Element recordElement=recordsElement.addElement(PdfFormatConstants.RECORD_ELEMENT);
				String[] wordsPerLine=line.split(this.generateSpaceSplitRegex());
				for(String word:wordsPerLine){
					if(!StringUtils.isEmpty(word)){
						Element colElement=recordElement.addElement(PdfFormatConstants.COLUMN_ELEMENT);
						colElement.setText(this.formatWord(word));
					}
				}
			}
		}
	}

	private void handleTableBlock(Element recordsElement,Element element) {
		Element tablElement=recordsElement.addElement(PdfFormatConstants.TABLE_ELEMENT);
		@SuppressWarnings("unchecked")
		LinkedHashSet<String> rowYposSet=this.getRowYposForTable(element);
		Iterator<String> rowYposIterator=rowYposSet.iterator();
		while(rowYposIterator.hasNext()){
			Element recordElement=tablElement.addElement(PdfFormatConstants.RECORD_ELEMENT);
			@SuppressWarnings("unchecked")
			List<Element> rowBlockElements=element.selectNodes("./block[@ypos="+rowYposIterator.next()+"]");
			for(Element colBlock:rowBlockElements){
				Element colElement=recordElement.addElement(PdfFormatConstants.COLUMN_ELEMENT);
				colElement.setText(colBlock.valueOf("./block/text"));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private LinkedHashSet getRowYposForTable(Element tableBlock){
		@SuppressWarnings("unchecked")
		List<Element> blockList=this.getTableBlock(tableBlock);
		LinkedHashSet<String> rowYposSet=new LinkedHashSet<String>();
		for(Element block:blockList){
			String value=block.valueOf(PdfFormatConstants.BLOCK_YPOS_XPATH_EXP);
			rowYposSet.add(value);
		}
		return rowYposSet;
	}
	
	@SuppressWarnings("rawtypes")
	private List getTableBlock(Element tableBlock){
		@SuppressWarnings("unchecked")
		List<Element> blockList=tableBlock.selectNodes(PdfFormatConstants.CELL_BLOCK_XPATH_EXP);
		return blockList;
	}
}
