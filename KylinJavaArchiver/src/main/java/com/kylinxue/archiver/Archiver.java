package com.kylinxue.archiver;

import java.io.*;

/**
 * 归档类
 */
public class Archiver {
	public static void main(String[] args) throws Exception {
		//
		FileOutputStream fos = new FileOutputStream("C:/Users/user/Desktop/_test/x.xar");
		fos.write(addFile("C:/Users/user/Desktop/_test/a.png"));
		fos.write(addFile("C:/Users/user/Desktop/_test/b.txt"));
		fos.write(addFile("C:/Users/user/Desktop/_test/c.jpg"));
		fos.close();
		//
		
	}
	
	/**
	 * @throws Exception
	 */
	public static byte[] addFile(String path) throws Exception{
		//
		File file = new File(path);
		//
		String fname = file.getName();
		// 文件名-字节流
		byte[] fnameBytes = fname.getBytes() ;
		// 文件内容长度
		int len = (int)file.length();
		
		//总长度 = 文件名字节长度+文件名字节+文件内容长度+文件内容
		int total = 4 + fnameBytes.length + 4 + len ;
		
		// 初始化总数组
		byte[] bytes = new byte[total];
		
		//1.写入文件名长度的字节流
		byte[] fnameLenArr = Util.int2Bytes(fnameBytes.length);
		System.arraycopy(fnameLenArr, 0, bytes, 0, 4);
		
		//2.写入文件名-字节数组
		System.arraycopy(fnameBytes, 0, bytes, 4, fnameBytes.length);
		
		//3.写入文件长度的字节流
		byte[] fcontentLenArr = Util.int2Bytes(len);
		System.arraycopy(fcontentLenArr, 0, bytes, 4 + fnameBytes.length, 4);
		
		//4.写入文件内容
		// 读取文件内容到字节数组输出流
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		FileInputStream fin = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len0 = 0 ;
		while(((len0 = fin.read(buf)) != -1)){
			baout.write(buf, 0, len0);
		}
		fin.close();
		//  字节数组输出流 --> 文件内容的字节流
		byte[] fileContentArr = baout.toByteArray();
		System.arraycopy(fileContentArr, 0, bytes, 4 + fnameBytes.length + 4, fileContentArr.length);
		
		return bytes ;
	}

	/**
	 *  将文件内容转换为字节数组
	 *  FileInputStream and ByteArrayOutputStream are needed
	 * @throws IOException
	 */
	private byte[] file2bytes(File file) throws IOException {
		ByteArrayOutputStream baout = new ByteArrayOutputStream();
		FileInputStream fin = new FileInputStream(file);
		byte[] buf = new byte[1024];
		int len1 = 0;
		while ((len1=fin.read(buf))!=-1){
			baout.write(buf,0,len1);
		}
		fin.close();
		return baout.toByteArray();
	}

	private byte[] many2oneBytes(byte[] src1, byte[] src2){
		byte[] dest =new byte[src1.length+src2.length];
		System.arraycopy(src1,0,dest,0,src1.length);
		System.arraycopy(src2,0,dest,src1.length,src2.length);

		return dest;
	}
}
