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
		//
		FileOutputStream fos = new FileOutputStream("d:/arch/xxx.xar");
		// Zip输出流为装饰器模式
		ZipOutputStream zos = new ZipOutputStream(fos);

		// 三个文件-路径+名
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
	 *  压缩过程：文件==>文件输入流==>byte[]==>Zip输出流==>文件输出流==>压缩文件
	 *  注意ZipEntry的作用，记录文件名
	 *  ZipOutputStream需要传入OutputStream，表示压缩的输出流最终输出
	 * 此处是输出到文件输出流
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
	
}
