package com.oocl.frm.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;

public class TextToXML {

	public static String writeHead() {
		StringBuilder headBuilder = new StringBuilder();
		headBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-16\"?>\r\n");
		headBuilder.append("<document>\r\n");
		headBuilder.append("<records>\r\n");
		return headBuilder.toString();
	}

	public static String writeFoot() {
		StringBuilder footBuilder = new StringBuilder();
		footBuilder.append("</records>\r\n");
		footBuilder.append("</document>");
		return footBuilder.toString();
	}

	public static String formatText(File file) throws FileNotFoundException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder resultBuilder = new StringBuilder();
		try {
			while ((line = bufferedReader.readLine()) != null) {
				if (!StringUtils.isEmpty(line.trim())) {
					resultBuilder.append("<record>");
					String[] words = line.split("\\s{3,}");
					for (String word : words) {
						if (!StringUtils.isEmpty(word.trim())) {
							if(word.equals(":" )|| word.equals("：")){
								continue;
							}else{
								resultBuilder.append("<column>");
								resultBuilder.append(word);
								resultBuilder.append("</column>");
							}
						}
					}
					resultBuilder.append("</record>");
					resultBuilder.append("\r\n");
				}
			}
			return resultBuilder.toString();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		String filePath = "\\\\sha3\\isdc\\Framework\\study\\techStudy\\XSLTtemplate\\509945iv";
		String fileName = "PDFTextStream_Visual_509945iv";
		File file = new File(filePath + "\\" + fileName + ".txt");
		String formatResult = writeHead() + formatText(file) + writeFoot();
		File outputFile = new File(filePath + "\\" + fileName
				+ "_TxtFormat.xml");
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		outputStream.write(formatResult.getBytes());
		outputStream.flush();
		outputStream.close();

	}

}
