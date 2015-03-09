package controllers;

import actions.AccountTypeRestriction;
import enums.AccountType;
import play.mvc.Result;
import play.mvc.Controller;
import play.mvc.Security;
import views.html.clientDashboard;

/**
 * client controller class
 * @author Maciej Zbierowski
 */
@Security.Authenticated(Secured.class)
@AccountTypeRestriction(roles = AccountType.CLIENT)
public class ClientController extends Controller {

    /** redirect to client dashboard
     * @author Maciej Zbierowski
     * @return {@link}
     */
    public static Result redirectToDashboard() {
        return ok(clientDashboard.render());
    }
}
