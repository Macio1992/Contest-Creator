package controllers;


import enums.SessionKey;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Contlorer Autoryzacji.
 * @author Przemyslaw Zynis
 *
 */
public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get(SessionKey.ACCOUNT_ID.toString());
    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.index());
    }

}

