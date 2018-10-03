import org.junit.Test;

import java.io.File;



public class TestConverter {
	
	@Test
	public void test22(){
		int b = (byte)-0xFF ;
		System.out.println(b);
	}
	
	/**
	 * ������ת���ֽ�����
	 */
	@Test
	public void test1(){
		byte[] arr = int2Bytes(1000);
		System.out.println(bytes2Int(arr));
	}
	
	/**
	 * ����ת�����ֽ�����
	 */
	public byte[] int2Bytes(int i){
		byte[] arr = new byte[4] ;
		arr[0] = (byte)i ;
		arr[1] = (byte)(i >> 8) ;
		arr[2] = (byte)(i >> 16) ;
		arr[3] = (byte)(i >> 24) ;
		return arr ;
	}
	
	/**
	 * �ֽ�����ת��int
	 */
	public int bytes2Int(byte[] bytes){
		int i0= bytes[0] & 0xFF  ;
		int i1 = (bytes[1] & 0xFF) << 8 ;
		int i2 = (bytes[2] & 0xFF) << 16 ;
		int i3 = (bytes[3] & 0xFF) << 24 ;
		return i0 | i1 | i2 | i3 ;
	}
	
	@Test
	public void test2(){
		System.out.println(5 & 10);
		System.out.println(5 | 10);
		System.out.println(-1 | 99999);
		System.out.println(0 & 99999);
		System.out.println(~1);
		System.out.println("=============");
		System.out.println(~1);
		System.out.println(4 ^ 10);
	}
	
	@Test
	public void testFileName(){
		File f = new File("d:/x/a.jpg");
		System.out.println(f.getName());
	}
}
