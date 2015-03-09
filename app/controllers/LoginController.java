package controllers;

import enums.AccountStatus;
import enums.SessionKey;
import models.Account;
import play.Logger;
import play.api.libs.Codecs;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.*;

import views.html.*;
/**
 * Loggin Controller
 * @author Maciej Zbierowski
 */
public class LoginController extends Controller {

    /** login method
     * @author Maciej Zbierowski
     * */
    public static Result login(){

        Form<Account> formAccount = Form.form(Account.class, Account.LoginForm.class).bindFromRequest();

        if (formAccount.hasErrors()){
            return badRequest(login.render(formAccount));
        }

        Account account = Account.findByEmail(formAccount.get().email);
        if (account == null) {
            Logger.info("wrong email");
            flash("error", Messages.get("login.loginEmailOrPassword"));
            return redirect(routes.Application.index());
        }

        if (account.accountStatus == AccountStatus.NOT_CONFIRMED) {
            Logger.info("account not confirmed");
            flash("error", Messages.get("login.loginNotActive"));
            return redirect(routes.Application.index());
        }

        if (account.accountStatus == AccountStatus.DELETED) {
            Logger.info("account deleted");
            flash("error", Messages.get("login.loginDeleted"));
            return redirect(routes.Application.index());
        }

        if (!Codecs.md5(formAccount.get().password.getBytes()).equals(account.password)) {
            Logger.info("wrong password");
            flash("error", Messages.get("login.loginEmailOrPassword"));
            return badRequest(login.render(formAccount));
        }

        session(SessionKey.ACCOUNT_ID.toString(), account.id.toString());
        session(SessionKey.ACCOUNT_EMAIL.toString(), account.email.toString());
        session(SessionKey.ACCOUNT_FIRST_NAME.toString(), account.firstName.toString());
        session(SessionKey.ACCOUNT_LAST_NAME.toString(), account.lastName.toString());
        session(SessionKey.ACCOUNT_TYPE.toString(), account.accountType.toString());

        switch(account.accountType) {

            case ADMIN:
                flash("success", Messages.get("login.loginSuccess"));
                return redirect(routes.AdminController.redirectToDashboard());

            case CLIENT:
                flash("success", Messages.get("login.loginSuccess"));
                return redirect(routes.ClientController.redirectToDashboard());

        }

        return ok("Zalogowano");
    }

    /** logout method
     * @author Maciej Zbierowski
     * @return{ @link }
     */
    public static Result logout(){
        session().clear();
        flash("success", Messages.get("logout.logoutSuccess"));
        return redirect(routes.Application.index());
    }

}
