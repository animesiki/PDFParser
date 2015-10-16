package com.oocl.frm.pdfts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.oocl.frm.format.TextToXML;


public class ExtractPDFContent {
	
	private static void processPDF(File[] files) throws IOException{
		for(File f:files){
			if(f.isDirectory()){
				processPDF(f.listFiles());
			}else{
			    if(f.getName().endsWith(".pdf")){
			    	String outFolder=f.getParentFile().getAbsolutePath();
			    	String outFileNamePrefix="PDFTextStream_";		    
			    	String fileName=f.getName().substring(0, f.getName().lastIndexOf("."));
			    	String txtOutputPath=outFolder+"\\"+outFileNamePrefix+"Visual_"+fileName+".txt";
			    	String xmlOutputPath=outFolder+"\\"+outFileNamePrefix+"XML_"+fileName+".xml";
			    	//write to txt
			    	ExtractTextAllPages.writeText(f.getAbsolutePath(), txtOutputPath);
			    	//write to xml
			    	ExtractTextToXML.writeText(f.getAbsolutePath(), xmlOutputPath);
			    }
			}
		}
	}
	
	private static void formatTxt(File[] files) throws IOException{
		
		for(File f:files){
			if(f.isDirectory()){
				formatTxt(f.listFiles());
			}else{
			    if(f.getName().endsWith(".txt")){
			    	String outFolder=f.getParentFile().getAbsolutePath();	    
			    	String fileName=f.getName().substring(0, f.getName().lastIndexOf("."));
			    	String xmlOutputPath=outFolder+"\\"+fileName+"_TxtFormat.xml";
			    	File outputFile = new File(xmlOutputPath);
			    	FileOutputStream outputStream = new FileOutputStream(outputFile);
			    	String formatResult =TextToXML.writeHead() +TextToXML.formatText(f) + TextToXML.writeFoot();
					outputStream.write(formatResult.getBytes());
					outputStream.flush();
					outputStream.close();
			    }
			}
		}
	}
	
	

	public static void main(String[] args) throws IOException {
		String folderPath="\\\\sha3\\isdc\\Framework\\study\\techStudy\\XSLTtemplate";
		File file=new File(folderPath);
		File[] listFiles=file.listFiles();
		//processPDF(listFiles);
		formatTxt(listFiles);
		

	}

}
