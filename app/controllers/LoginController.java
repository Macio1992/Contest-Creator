package controllers;

import models.Account;
import play.Logger;
import play.api.libs.Codecs;
import play.data.Form;
import play.mvc.*;

import views.html.*;
/**
 * Loggin Controller
 * @author Maciej Zbierowski
 */
public class LoginController extends Controller{

    public static Result login(){

        Form<Account> formAccount = Form.form(Account.class, Account.LoginForm.class).bindFromRequest();

        if (formAccount.hasErrors()){
            return badRequest(login.render(formAccount));
        }

        Account account = Account.findByEmailAndAccountStatus(formAccount.get().email, formAccount.get().accountStatus);
        if (account == null) {
            Logger.info("wrong email");
            return badRequest(login.render(formAccount));
        }

        if (!Codecs.md5(formAccount.get().password.getBytes()).equals(account.password)) {
            Logger.info("wrong password");
            return badRequest(login.render(formAccount));
        }

        return ok("Zalogowano");
    }
}
