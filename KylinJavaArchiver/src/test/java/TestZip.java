import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;

public class TestZip {
	
	@Test
	public void zip() throws Exception {
		// 指定压缩后的文件名
		FileOutputStream fos = new FileOutputStream("e:/test/xxx.xar");
		// Zip输出流为装饰器模式
		ZipOutputStream zos = new ZipOutputStream(fos);

		// 三个文件-路径+名
		String[] arr = {
				"e:/test/1.txt",
				"e:/test/2.jpg",
				"e:/test/3.xml"
		};
		
		for(String s : arr){
			addFile(zos , s);
		}
		// 注意：ZipOutputStream首先关闭，然后文件输出流才关闭，否则会报错。
		zos.close();
		fos.close();
		System.out.println("over");
	}

	/**
	 *  压缩过程：文件==>文件输入流==>byte[]==>Zip输出流==>文件输出流==>压缩文件
	 *  注意ZipEntry的作用，记录文件名
	 *  ZipOutputStream需要传入OutputStream，表示压缩的输出流最终输出
	 * 此处是输出到文件输出流
	 */
	public  void addFile(ZipOutputStream zos , String path) throws Exception{
		File f = new File(path);
		zos.putNextEntry(new ZipEntry(f.getName()));
		FileInputStream fis = new FileInputStream(f);
		byte[] bytes = new byte[fis.available()];
		fis.read(bytes);
		fis.close();
		
		zos.write(bytes);
		zos.closeEntry();
	}


	@Test
	public void unzip() throws Exception{
		// 文件输入流
		FileInputStream fin = new FileInputStream("d:/arch/xxx.zip");
		//  将Zip文件 通过文件输入流 传入 Zip输入流
		ZipInputStream zin = new ZipInputStream(fin);

		ZipEntry entry = null ;
		byte[] buf = new byte[1024];
		int len = 0 ;

		/**
		 * 将Zip文件传入Zip输入流，输入流可以解析出里面有多少个文件，也就多少个ZipEntry
		 * Zip输入流将解压缩后的字节流按照ZipEntry一个一个 传给 文件输出流，通过 buf
		 */
		while((entry = zin.getNextEntry()) != null){
			String name = entry.getName();
			FileOutputStream fout = new FileOutputStream("d:/arch/unzip/" + name);
			while((len = zin.read(buf)) != -1){
				fout.write(buf, 0, len);
			}
			fout.close();
		}
		zin.close();
		fin.close();
	}

	/**
	 *  压缩单个文件
	 * @throws IOException
	 */
	@Test
	public void zipSingleFile() throws IOException {
		String src = "e:/test/1.txt";

		File file = new File(src);
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream("e:/test/z.zip");
		ZipOutputStream zos = new ZipOutputStream(fos);
		ZipEntry entry = new ZipEntry(file.getName());
		zos.putNextEntry(entry);
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = fis.read(buf)) != -1){
			zos.write(buf, 0, len);
		}
		zos.closeEntry();
		fis.close();zos.close();fos.close();
		System.out.println("over");
	}

	/**
	 * 解压缩由单个文件组成的zip格式
	 */
	@Test
	public void testUnzipSingleFile(){
		try {
			unzipSingleFile("e:/test/1.zip","e:/test/1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void unzipSingleFile(String src, String dest) throws IOException {
		FileInputStream fis = new FileInputStream(src);
		ZipInputStream zis = new ZipInputStream(fis);
		FileOutputStream fos = new FileOutputStream(dest);
		ZipEntry entry = zis.getNextEntry();
		byte[] buf = new byte[1024];
		int len = 0;
		if(entry != null){
			while((len = zis.read(buf)) != -1){
				fos.write(buf, 0, len);
			}
		}
		fis.close();zis.close();fos.close();
		System.out.println("over");
	}
}
