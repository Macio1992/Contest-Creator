package controllers;

import enums.AccountType;
import enums.SessionKey;
import models.Account;
import play.data.Form;
import play.i18n.Messages;
import play.mvc.*;

import views.html.*;
/** Application controller*/
public class Application extends Controller {

    /** index method */
    public static Result index() {

        Form<Account> formAccount = Form.form(Account.class);

        if(session().get(SessionKey.ACCOUNT_ID.toString()) != null){
            switch (AccountType.valueOf(session().get(SessionKey.ACCOUNT_TYPE.toString()))){
                case ADMIN:
                    return redirect(routes.AdminController.redirectToDashboard());

                case CLIENT:
                    return redirect(routes.ClientController.redirectToDashboard());
            }
        }

        return ok(login.render(formAccount));
    }

}