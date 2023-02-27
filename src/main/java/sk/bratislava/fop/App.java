package sk.bratislava.fop;

import io.javalin.Javalin;
import io.javalin.core.validation.ValidationException;
import sk.bratislava.fop.dto.ErrorDto;

public class App {
    public static int getPort() {
        int port = 7000;
        String envPort = System.getenv("PORT");
        if (envPort != null) {
            try {
                port = Integer.parseInt(envPort);
            } catch (Exception ex) {

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

        app.exception(ValidationException.class, (e, ctx) -> {
            ctx.json(e.getErrors()).status(400);
        }).exception(Exception.class, (e, ctx) -> {
            ctx.json(new ErrorDto(e)).status(500);
        });
    }
}
