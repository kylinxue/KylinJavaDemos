package com.kylinxue.archiver;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 解档程序
 */
public class UnArchiver {
    public static void main(String[] args) throws Exception {

        List<FileBean> files = new ArrayList<FileBean>();
        //
        FileInputStream fis = new FileInputStream("C:/Users/user/Desktop/_test/x.xar");

        FileBean fileBean = null ;
        //
        while((fileBean = readNextFile(fis)) != null){
            files.add(fileBean);
        }
        //关闭流
        fis.close();

        FileOutputStream fos = null ;
        //
        for(FileBean fb : files){
            // 注意：需要自己创建目录，目录不存在会抛出异常
            // + 可以先检查目录是否存在，不存在创建；存在执行
            fos = new FileOutputStream("C:/Users/user/Desktop/_test/unarch/" + fb.getFileName());
            fos.write(fb.getFileContent());
            fos.close();
        }
    }

    public static void unArchive(String xarFile, String destPath) throws Exception {

        List<FileBean> files = new ArrayList<FileBean>();
        FileInputStream fin = new FileInputStream(xarFile);
        FileBean fileBean = null;

        while((fileBean = readNextFile(fin))!=null){
            files.add(fileBean);
        }
        fin.close();

        FileOutputStream fout = null;
        for(FileBean fb : files){
            // 指定输出文件对应的输出流，路径+文件名
            // 将字节流写入输出流，文件产生，关闭输出流
            fout = new FileOutputStream(destPath + fb.getFileName());
            fout.write(fb.getFileContent());
            fout.close();
        }

    }

    /**
     * 从流中读取下一个文件
     */
    public static FileBean readNextFile(FileInputStream fis) throws Exception{
        //
        byte[] bytes4 = new byte[4];
        //读取四个字节
        int res = fis.read(bytes4);
        if(res == -1){
            return null ;
        }
        //文件名长度
        int fnameLen = Util.bytes2Int(bytes4);

        //文件名数组
        byte[] fileNameBytes = new byte[fnameLen];
        fis.read(fileNameBytes);

        //得到文件名
        String fname = new String(fileNameBytes);

        //再读取4个字节，作为文件内容的长度
        fis.read(bytes4);
        int fileContLen = Util.bytes2Int(bytes4);

        //读取文件内容
        byte[] fileContBytes = new byte[fileContLen];
        fis.read(fileContBytes);
        return new FileBean(fname,fileContBytes);
    }
}
