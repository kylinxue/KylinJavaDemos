package com.kylinxue.archiver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class Archiveinput {
	public static void main(String[] args) {
		FileInputStream  fis=null;
	try {
		fis=new FileInputStream("C:/Users/user/Desktop/_test/x.xar");
	} catch (FileNotFoundException e) {
	
		System.out.println("�ļ���ַû���ҵ�");
	}
	}
}
