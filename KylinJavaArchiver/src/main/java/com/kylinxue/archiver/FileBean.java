package com.kylinxue.archiver;

/**
 * 文件Bean ，表示文件的相关属性
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
