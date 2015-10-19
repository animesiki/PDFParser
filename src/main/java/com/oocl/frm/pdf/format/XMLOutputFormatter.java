package com.oocl.frm.pdf.format;

import java.io.IOException;



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
	public Document formatPDFText(byte[] pdfContent) throws DocumentException, IOException{
		Document resultDocument=DocumentHelper.createDocument();
		Element rootElement=this.getRooElement(resultDocument);
		Document sourceDocument=DocumentHelper.parseText(this.pdfParser.parsePDF(pdfContent));
		
		Element recordsElement=rootElement.addElement(PdfFormatConstants.RECORDS_ELEMENT);
		return null;
	}
	
	public void treeWalkDoc(Document document){
		this.treeWalkElement(document.getRootElement());
	}
	
	public void treeWalkElement(Element element){
		for(int i=0,size=element.nodeCount();i<size;i++){
			Node node=element.node(i);
			if(node instanceof Element){
			    Element childElement=(Element) node;
			    if(isKeepPage){
			    	
			    }
				treeWalkElement((Element)node);
			}	
		}
	}
	
	private void handleElement(Element element){
		if(isKeepPage){
			if(element.getName().equals("")){
				
			}
		}
	}

}
