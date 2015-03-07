package controllers;

import models.Account;
import play.data.Form;
import play.mvc.*;

import views.html.*;
/** Application controller*/
public class Application extends Controller {

    /** index method */
    public static Result index() {

        Form<Account> formAccount = Form.form(Account.class);

        return ok(login.render(formAccount));
    }

}