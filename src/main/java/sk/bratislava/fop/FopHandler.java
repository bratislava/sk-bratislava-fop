package sk.bratislava.fop;

import java.io.ByteArrayInputStream;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import sk.bratislava.fop.constants.Errors;
import sk.bratislava.fop.dto.FopDto;
import sk.bratislava.fop.utils.StringUtils;

public class FopHandler implements Handler {
    private FopService service;

    public FopHandler() {
        service = new FopService();
    }

    @Override
    public void handle(Context ctx) throws Exception {
        FopDto body = ctx.bodyValidator(FopDto.class)
                .check(obj -> !StringUtils.IsNullOrEmpty(obj.data) && !StringUtils.IsNullOrEmpty(obj.xslt),
                        Errors.BAD_REQUEST)
                .get();

        byte[] out = service.transform(body.data, body.xslt);

        ctx.contentType("application/pdf").result(new ByteArrayInputStream(out));
    }

}
