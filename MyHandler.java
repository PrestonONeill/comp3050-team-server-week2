import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {

        // Handle CORS.
        String origin = he.getRequestHeaders().getFirst("Origin");
        he.getResponseHeaders().add("Access-Control-Allow-Origin", origin);
        he.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        he.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        // Set response headers.
        Headers headers = he.getResponseHeaders();
        headers.set("Content-Type", "text/plain");
        headers.set("Connection", "close");

        // Send response body.
        String response = "{\"name\":\"Japan\",\"gold\":27,\"silver\":14,\"bronze\":17,\"total\":58}";
;
        System.out.println(response);
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
