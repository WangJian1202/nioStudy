import org.junit.Test;
import sun.awt.windows.WBufferStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description TODO nio
 * @Date 2019/10/7 17:22
 * @Created by WangJian
 */
public class TestNIO {
    private static final String path="C:\\Users\\admin\\Desktop\\nio\\test.txt";
    private static final String pathCopy="C:\\Users\\admin\\Desktop\\nio\\test_copy.txt";
    /**
     * @Author WangJian
     * @Description //TODO 写入数据到本地文件
     * @Date 17:35 2019/10/7
     **/
    @Test
    public void testWriteByNIO() throws Exception {
//        1.创建输出流
        FileOutputStream fos= new FileOutputStream(path);
//        2.从流中获取通道
        FileChannel fc =fos.getChannel();
//        3.提供一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
//        4.往缓冲区中存入数据
        String str="hello nio";
        buffer.put(str.getBytes());
        buffer.flip();
//        5.将缓冲区写入通道
        fc.write(buffer);
//        6.关闭
        fos.close();
    }
    /**
     * @Author WangJian
     * @Description //TODO 从本地文件中读取数据
     * @Date 17:35 2019/10/7
     **/
    @Test
    public void testReadByNIO() throws Exception {
        File file =new File(path);
//        1.创建输入流
        FileInputStream fis =new FileInputStream(path);
//        2.得到一个通道
        FileChannel fc =fis.getChannel();
//        3.准备一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
//        4.从通道里读取数据并存到缓冲区中
        fc.read(buffer);
        System.out.println(new String(buffer.array()));
//        5.关闭
        fis.close();
    }
    /**
     * @Author WangJian
     * @Description //TODO 使用NIO实现文件复制
     * @Date 17:43 2019/10/7
     **/
    @Test
    public void test() throws Exception {
//        1.创建两个流
        FileInputStream fis =new FileInputStream(path);
        FileOutputStream fos =new FileOutputStream(pathCopy);
//        2.得到两个通道
        FileChannel sourceChannel = fis.getChannel();
        FileChannel targetChannel = fos.getChannel();
//        3.复制(特别适合复制大文件) 两种方式
        targetChannel.transferFrom(sourceChannel,0,sourceChannel.size());
         sourceChannel.transferTo(targetChannel.size(),0,targetChannel);
//        4.关闭
        fis.close();
        fos.close();
    }
}
