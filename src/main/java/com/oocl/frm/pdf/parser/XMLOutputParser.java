package com.oocl.frm.pdf.parser;

import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import pdfts.examples.XMLOutputTarget;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XMLOutputParser implements IPdfParser {

    public String parsePDF(byte[] pdfContent) throws IOException {
        Document pdf = PDF.open(new ByteArrayInputStream(pdfContent), null);
        XMLOutputTarget xmlOutputTarget = new XMLOutputTarget();
        pdf.pipe(xmlOutputTarget);
        pdf.close();
        return xmlOutputTarget.getXMLAsString();
    }

}
