package sk.bratislava.fop;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/* config */)
                .get("/", ctx -> ctx.result("Hello World"))
                .start(7000);
    }
}
