package com.kylinxue.archiver;

public class Util {
	/**
	 * int转换为bytes[]，int的低位在前，即对应着低地址【小端】
	 */
	public static byte[] int2Bytes(int i){
		byte[] arr = new byte[4] ;
		arr[0] = (byte)i ;
		arr[1] = (byte)(i >> 8) ;
		arr[2] = (byte)(i >> 16) ;
		arr[3] = (byte)(i >> 24) ;
		return arr ;
	}
	
	/**
	 * byte类型进行位操作默认转为int类型
	 * 如果byte第1位为1，则为负数，转为的int前面24位则都是1
	 * 故与0xFF进行与操作，将前24位变为0
	 */
	public static int bytes2Int(byte[] bytes){
		int i0= bytes[0]& 0xFF;
		int i1 = (bytes[1] & 0xFF) << 8 ;
		int i2 = (bytes[2] & 0xFF) << 16 ;
		int i3 = (bytes[3] & 0xFF) << 24 ;
		return i0 | i1 | i2 | i3 ;
	}
}
