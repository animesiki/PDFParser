package com.oocl.frm.pdf;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.DocumentException;


import com.oocl.frm.pdf.format.AbstractFormatter;
import com.oocl.frm.pdf.format.OutputDecorator;
import com.oocl.frm.pdf.format.TextOutputFormatter;
import com.oocl.frm.pdf.parser.TextOutputParser;

public class TextFormatterTest {
	
	public static void main(String[] args) throws IOException, DocumentException{
		byte[] pdfContent=toByteArray("\\\\sha3\\isdc\\Framework\\study\\techStudy\\XSLTtemplate\\CPT\\Container_Service_Bill_Bouquet_OIPL1_20150620.pdf");
		AbstractFormatter formatter=new TextOutputFormatter(new TextOutputParser());
		formatter.setWordSpaceSplitCount(4);
		formatter.setFilterSpecialCharRegex(";|：");
		OutputDecorator decorator=new OutputDecorator(formatter);
		String result=decorator.generateResultDoc(pdfContent);
		File outputFile=new File("CPT_TextFormatter.xml");
		FileOutputStream outputStream=new FileOutputStream(outputFile);
		outputStream.write(result.getBytes());
		outputStream.flush();
		outputStream.close();
		
	}
	
	public static byte[] toByteArray(String filename) throws IOException {  
		  
        File f = new File(filename);  
        if (!f.exists()) {  
            throw new FileNotFoundException(filename);  
        }  
  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());  
        BufferedInputStream in = null;  
        try {  
            in = new BufferedInputStream(new FileInputStream(f));  
            int buf_size = 1024;  
            byte[] buffer = new byte[buf_size];  
            int len = 0;  
            while (-1 != (len = in.read(buffer, 0, buf_size))) {  
                bos.write(buffer, 0, len);  
            }  
            return bos.toByteArray();  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        } finally {  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            bos.close();  
        }  
    }  

}
