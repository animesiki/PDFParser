package com.oocl.frm.pdf.constants;

public class PdfFormatConstants {
	public static final String splitWordRegex = "\\s";
	public static final String ROOT_ELEMENT = "pdf";
	public static final String RECORDS_ELEMENT = "records";
	public static final String RECORD_ELEMENT = "record";
	public static final String COLUMN_ELEMENT = "column";
	public static final String TABLE_ELEMENT = "table";
	public static final String FORMAT_WORD_REGEX = "\\s{2,}";
	public static final String PAGE_ELEMENT = "page";
	public static final String PAGE_ATTR = "number";
	public static final String PAGE_NO_ATTR = "pageNo";
	public static final String BLOCK_ELEMENT = "block";
	public static final String BLOCK_TYPE_ATTR = "type";
	public static final String TABLE_TYPE_VALUE = "table";
	public static final String TEXT_ELEMENT = "text";
	public static final String BLOCK_XPATH_EXP = ".//page/block";
	public static final String BLOCK_XPAHT_BY_PAGE_EXP = "./block";
	public static final String PAGE_XPATH_EXP = "//page";
	public static final String CELL_BLOCK_XPATH_EXP = "./block";
	public static final String BLOCK_YPOS_XPATH_EXP = "./@ypos";
	public static final String CR_CHAR = "&#13;";
}
