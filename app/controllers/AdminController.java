package controllers;

import actions.AccountTypeRestriction;
import enums.AccountType;
import play.mvc.Result;
import play.mvc.Controller;

import play.mvc.Security;
import views.html.adminDashboard;

/**
 * admin controller classuthor Maciej Zbierowski
 */
@Security.Authenticated(Secured.class)
@AccountTypeRestriction(roles = AccountType.ADMIN)
public class AdminController extends Controller {

    /** redirect to admin dashboard
     * @author Maciej Zbierowski
     * @return {@link}
     */
    public static Result redirectToDashboard() {
        return ok(adminDashboard.render());
    }
}
