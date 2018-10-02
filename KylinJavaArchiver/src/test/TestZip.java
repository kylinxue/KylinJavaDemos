package com.it18zhang.archive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

public class TestZip {
	
	@Test
	public void zip() throws Exception {
		//文件输出流
		FileOutputStream fos = new FileOutputStream("d:/arch/xxx.xar");
		//压缩流
		ZipOutputStream zos = new ZipOutputStream(fos);
		
		String[] arr = {
				"d:/arch/1.jpg",
				"d:/arch/2.txt",
				"d:/arch/3.xml"
		};
		
		for(String s : arr){
			addFile(zos , s);
		}
		zos.close();
		fos.close();
		System.out.println("over");
	}
	
	/**
	 * 循环向zos中添加条目 
	 */
	public static void addFile(ZipOutputStream zos , String path) throws Exception{
		File f = new File(path);
		zos.putNextEntry(new ZipEntry(f.getName()));
		FileInputStream fis = new FileInputStream(f);
		byte[] bytes = new byte[fis.available()];
		fis.read(bytes);
		fis.close();
		
		zos.write(bytes);
		zos.closeEntry();
	}
	
	/**
	 * 解压缩
	 */
	@Test
	public void unzip() throws Exception{
		//
		FileInputStream fis = new FileInputStream("d:/arch/xxx.zip");
		//
		ZipInputStream zis = new ZipInputStream(fis);
		//
		ZipEntry entry = null ;
		byte[] buf = new byte[1024];
		int len = 0 ;
		while((entry = zis.getNextEntry()) != null){
			String name = entry.getName();
			FileOutputStream fos = new FileOutputStream("d:/arch/unzip/" + name);
			while((len = zis.read(buf)) != -1){
				fos.write(buf, 0, len);
			}
			fos.close();
		}
		zis.close();
		fis.close();
	}
	
}
