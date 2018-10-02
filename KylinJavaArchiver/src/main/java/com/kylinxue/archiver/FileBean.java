package com.it18zhang.archiver;

/**
 * ÎÄ¼þBean 
 */
public class FileBean {
	private String fileName;
	private byte[] fileContent;

	public FileBean() {
	}
	
	public FileBean(String fname, byte[] fileContBytes) {
		this.fileName =  fname ;
		this.fileContent = fileContBytes ;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

}
