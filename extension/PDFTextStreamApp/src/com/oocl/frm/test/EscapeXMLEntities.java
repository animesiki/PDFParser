package com.oocl.frm.test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;


public class EscapeXMLEntities {
	public static final Map ENTITYMAP_MAP=new HashMap<String, String>();
	
	static{
		ENTITYMAP_MAP.put('<', "&lt;");
		ENTITYMAP_MAP.put(">", "&gt;");
		ENTITYMAP_MAP.put('&', "&amp;");
		ENTITYMAP_MAP.put("\'", "&aops;");
		ENTITYMAP_MAP.put("\"", "&quot;");	   
	}
	
	public static String escapeXMLEntity(String xmlString){
		StringBuilder resultBuilder=new StringBuilder();
		for(int i=0;i<xmlString.length();i++){
			if(ENTITYMAP_MAP.containsKey(xmlString.charAt(i))){
				resultBuilder.append(ENTITYMAP_MAP.get(xmlString.charAt(i)));
			}else{
			    resultBuilder.append(xmlString.charAt(i));
			}
		}
		return resultBuilder.toString();	
	}
	
	
	public static void main(String[] args){
		String xml="<name>江小斌'\"《地方 </name>";
		//System.out.println(XmlStringEscapeUtils.escapeXml(xml));
	}

}
