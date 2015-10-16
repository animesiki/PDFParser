package com.oocl.frm.pdfts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import pdfts.examples.XMLOutputTarget;

import com.snowtide.PDF;

public class ExtractTextToXML {
	
	public static String format(String xml){
		
		try {
            final InputSource src = new InputSource(new StringReader(xml));
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
            final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));
            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            final LSSerializer writer = impl.createLSSerializer();
            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); // Set this to true if the output needs to be beautified.
            writer.getDomConfig().setParameter("xml-declaration", keepDeclaration); // Set this to true if the declaration is needed to be outputted.
            return writer.writeToString(document);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		
	}
	
	public static void writeText(String pdfPath, String outputPath) throws IOException{
			
		File localFile1 = new File(pdfPath);
		if (!(localFile1.exists()))
			System.out.println("No such file: ");
		if (!(localFile1.canRead()))
			System.out.println("Cannot read file (check permissions): "
					);
		File localFile2 = new File(outputPath);

		com.snowtide.pdf.Document localDocument = PDF.open(localFile1);

		XMLOutputTarget localXMLOutputTarget = new XMLOutputTarget();
		localDocument.pipe(localXMLOutputTarget);

		OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(
				new FileOutputStream(localFile2), "UTF-8");
		localOutputStreamWriter
				.write(format(localXMLOutputTarget.getXMLAsString()));
		localOutputStreamWriter.flush();
		localOutputStreamWriter.close();
		localDocument.close();
		
	}

}
