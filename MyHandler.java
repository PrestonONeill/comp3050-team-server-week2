import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange he) throws IOException {

        // Handle CORS
        String origin = he.getRequestHeaders().getFirst("Origin");
        he.getResponseHeaders().add("Access-Control-Allow-Origin", origin != null ? origin : "*");
        he.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        he.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        if (he.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            he.sendResponseHeaders(204, -1);
            return;
        }

        String query = he.getRequestURI().getQuery();  
        String code = "";
        if (query != null && query.startsWith("code=")) {
            code = query.split("=")[1].toUpperCase();
        }

        String response;
        switch (code) {
            case "NOR": response = "{\"code\":\"NOR\", \"tally\":[18,12,11]}"; break;
            case "USA": response = "{\"code\":\"USA\", \"tally\":[12,12,9]}";  break;
            case "NED": response = "{\"code\":\"NED\", \"tally\":[10,7,2]}";   break;
            case "ITA": response = "{\"code\":\"ITA\", \"tally\":[10,6,14]}";  break;
            default:    response = "{\"code\":\"unknown\", \"tally\":[0,0,0]}"; break;
        }

        he.getResponseHeaders().set("Content-Type", "application/json");
        he.sendResponseHeaders(200, response.length());

        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}