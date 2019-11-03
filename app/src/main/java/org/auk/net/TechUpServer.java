package org.auk.net;

import com.google.gson.Gson;
import org.auk.data.StudentDao;
import spark.ResponseTransformer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.auk.utils.Utils.print;
import static spark.Spark.*;

public class TechUpServer {

    private final static int PORT = 4567;

    public @interface ServerType {
        int SOCKET = 1;
        int SPARK = 2;
    }

    public TechUpServer(@ServerType int type) throws IOException {
        switch (type) {
            case ServerType.SOCKET -> init();
            case ServerType.SPARK -> initWithRoutes();
        }
    }

    private void initWithRoutes() {
        get("/hello", (req, res) -> "Hello Spark, my Love!");
        get("/hello/:name", (req, res) -> {
            String name = req.params(":name");
            return "Hello " + name;
        });

        path("/students", () -> {
            Gson gson = new Gson();
            get("/", "application/json", (res, req) -> new StudentDao().getAll(), gson::toJson);
            get("/:id", "application/json", (req, res) -> {
                int studentId = Integer.parseInt(req.params(":id"));
                return new StudentDao().findById(studentId);
            }, new JsonTransformer());
        });

        notFound("Hope you'll be luckier next time!");
    }

    private void init() throws IOException {
        final ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server is listening on port " + PORT);

        while (true) {
            try (final Socket client = server.accept()) {
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                // START of the received headers
                print("");
                String line = reader.readLine();
                while (!line.isEmpty()) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                // END of the received headers
                print("");

                // Send the response
                // Send the headers
                print("HTTP/1.0 200 OK");
                print("Content-Type: text/html");
                print("Server: TechUp");
                // this blank line signals the end of the headers
                print("");
                // Send the HTML page
                print("<H1>Welcome to the Ultra Mini-WebServer</H2>");
                out.flush();

                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                client.getOutputStream().write(httpResponse.getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }

    private static class JsonTransformer implements ResponseTransformer {

        private Gson gson = new Gson();

        @Override
        public String render(Object model) {
            return gson.toJson(model);
        }

    }
}
