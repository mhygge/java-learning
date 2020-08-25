import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: j8test
 * @description:
 * @author: limeng
 * @create: 2020-08-25 16:42
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080); //监听指定端口
        System.out.println("server is running...");
        for(;;) {
            Socket sock = ss.accept();
            System.out.println("connected from" + sock.getRemoteSocketAddress());
            Thread t = new Handler(sock);
            t.start();
        }
    }
}


