import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PublicHttpServer {
    public static void main(String[] args) throws IOException {
        int port = 8080; // Port server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // Handler untuk file statis (index.html)
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String filePath = "index.html"; // File utama
                byte[] response = Files.readAllBytes(Paths.get(filePath));
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server berjalan di http://0.0.0.0:" + port);
    }
}
