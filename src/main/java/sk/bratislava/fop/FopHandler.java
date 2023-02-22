package sk.bratislava.fop;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import sk.bratislava.fop.constants.Errors;
import sk.bratislava.fop.dto.FopDto;
import sk.bratislava.fop.utils.StringUtils;

public class FopHandler implements Handler {

    @Override
    public void handle(Context ctx) {
        FopDto data = ctx.bodyValidator(FopDto.class)
                .check(obj -> !StringUtils.IsNullOrEmpty(obj.data), Errors.BAD_REQUEST)
                .get();
    }

}
