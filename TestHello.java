import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

public class TestHello {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/hello", new HelloHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

}
