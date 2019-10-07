import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description TODO 网络客户端程序
 * @Date 2019/10/7 20:14
 * @Created by WangJian
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
//        1.得到一个网络通道
        SocketChannel channel = SocketChannel.open();
//        2.设置阻塞方式
        channel.configureBlocking(false);
//        3.提供服务器断的ip地址和端口号
        InetSocketAddress address= new InetSocketAddress("127.0.0.1",9999);
//        4.连接服务器端
        if(!channel.connect(address)){
            while(!channel.finishConnect()){
//                noi作为非阻塞式的优势
                System.out.println("Client:连接服务器端的同时，可以做别的一些事情");
            }
//        5.得到缓冲区，并存入数据
            String str="Hello Server";
            ByteBuffer writeBuf = ByteBuffer.wrap(str.getBytes());
//        6.发送数据
            channel.write(writeBuf);
//        7.先不关闭网络通道，因为网络通道一旦关闭，服务器端会抛异常(这里采取临时措施)
            System.in.read();
        }
    }
}
