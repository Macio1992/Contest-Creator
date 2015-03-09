package actions;

import enums.AccountType;
import play.Logger;
import play.libs.F;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import enums.SessionKey;

/**
 * Restrykcja tylko dla danego typu Konta.
 * @author Maciej Zbierowski *
 */
public class AccountTypeRestrictionAction extends Action<AccountTypeRestriction> {
    @Override
    public F.Promise<Result> call(Http.Context ctx) throws Throwable {
        if (ctx.session().get(SessionKey.ACCOUNT_TYPE.toString()) == null) {
            return Promise.promise(new Function0<Result>() {
                public Result apply() {
                    return forbidden();
                }
            });
        } else {
            AccountType[] accountRole = configuration.roles();
            boolean invalidAction = true;
            AccountType role = AccountType.valueOf(ctx.session().get(SessionKey.ACCOUNT_TYPE.toString()));
            for (int i = 0; i < accountRole.length; i++) {
                if (!accountRole[i].equals("")) {
                    Logger.debug("Restricting action for account: {}, role: {}", ctx.session().get(SessionKey.ACCOUNT_ID.toString()), accountRole[i]);
                    if (accountRole[i].equals(role)) {
                        invalidAction = false;
                    }
                }
            }
            if (invalidAction) {
                Logger.warn("Invalid action for role : {}" + role);
                return Promise.promise(new Function0<Result>() {
                    public Result apply() {
                        return forbidden();
                    }
                });

            } else {
                return delegate.call(ctx);
            }
        }
    }
}