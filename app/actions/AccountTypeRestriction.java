package actions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import enums.AccountType;
import play.mvc.With;

/**
 * Annotacja na restrykcję akcji tylko dla danego adresu typu konta. * @author Maciej Zbierowski *
 */
@With(AccountTypeRestrictionAction.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountTypeRestriction {
    AccountType[] roles();
}