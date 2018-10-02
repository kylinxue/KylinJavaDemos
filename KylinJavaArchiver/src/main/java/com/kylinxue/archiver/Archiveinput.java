package com.Archiverlxx01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class Archiveinput {
	public static void main(String[] args) {
		FileInputStream  fis=null;
	try {
		fis=new FileInputStream("d:/arch/x.xar");
	} catch (FileNotFoundException e) {
	
		System.out.println("文件地址没有找到");
	}
	}
}
