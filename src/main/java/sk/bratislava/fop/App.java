package sk.bratislava.fop;

import io.javalin.Javalin;

public class App {
    public static int getPort() {
        int port = 7000;
        String envPort = System.getenv("PORT");
        if (envPort != null) {
            try {
                port = Integer.parseInt(envPort);
            } finally {

            }
        }
        return port;
    }

    public static void main(String[] args) {
        int port = getPort();

        Javalin app = Javalin.create(/* config */)
                .get("/", ctx -> ctx.result("Hello World"))
                .post("/fop", new FopHandler())
                .start(port);
    }
}
