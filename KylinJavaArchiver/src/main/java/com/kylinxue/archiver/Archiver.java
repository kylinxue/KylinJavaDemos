package com.Archiverlxx01;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * �鵵�� 
 */
public class Archiver {
	public static void main(String[] args) throws Exception {
		//
		FileOutputStream fos = new FileOutputStream("d:/arch/x.xar");
		fos.write(addFile("D:/arch/a.xls"));
		fos.write(addFile("D:/arch/b.xml"));
		fos.write(addFile("D:/arch/c.jpg"));
		fos.close();
		//
		
	}
	
	/**
	 * path : d:/xxx/xxx/a.jpg
	 * @throws Exception 
	 */
	public static byte[] addFile(String path) throws Exception{
		//�ļ�
		File f = new File(path);
		//�ļ���
		String fname = f.getName();
		//�ļ�������
		byte[] fnameBytes = fname.getBytes() ;
		//�ļ����ݳ���
		int len = (int)f.length();
		
		//�����ܳ���
		int total = 4 + fnameBytes.length + 4 + len ;
		
		//��ʼ��������
		byte[] bytes = new byte[total];
		
		//1.д���ļ�������
		byte[] fnameLenArr = Util.int2Bytes(fnameBytes.length);
		System.arraycopy(fnameLenArr, 0, bytes, 0, 4);
		
		//2.д���ļ�������
		System.arraycopy(fnameBytes, 0, bytes, 4, fnameBytes.length);
		
		//3.д���ļ����ݳ���
		byte[] fcontentLenArr = Util.int2Bytes(len);
		System.arraycopy(fcontentLenArr, 0, bytes, 4 + fnameBytes.length, 4);
		
		//4.д���ļ�����
		//��ȡ�ļ����ݵ�������
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream fis = new FileInputStream(f);
		byte[] buf = new byte[1024];
		int len0 = 0 ;
		while(((len0 = fis.read(buf)) != -1)){
			baos.write(buf, 0, len0);
		}
		fis.close();
		//�õ��ļ�����
		byte[] fileContentArr = baos.toByteArray();
		
		System.arraycopy(fileContentArr, 0, bytes, 4 + fnameBytes.length + 4, fileContentArr.length);
		
		return bytes ;
	}
}
