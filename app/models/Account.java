package models;

import com.avaje.ebean.Ebean;
import enums.AccountStatus;
import enums.AccountType;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Account class.
 * @author Maciej Zbierowski
 */
@Entity
public class Account extends Model{

    /** id. */
    @Id
    public Long id;

    @Required(groups = {LoginForm.class})
    /** email address */
    public String email;

    @Required
    /** first name*/
    public String firstName;

    @Required
    /** last name*/
    public String lastName;

    @Required(groups = {LoginForm.class})
    @MinLength(5)
    /** password*/
    public String password;

    /** account type */
    public AccountType accountType;

    /** account status*/
    public AccountStatus accountStatus;

    public static Account findByEmailAndAccountStatus(String email, AccountStatus accountStatus) {
        accountStatus = accountStatus.ACTIVE;
        return Ebean.find(Account.class).where().eq("email", email).where().eq("accountStatus", accountStatus).findUnique();
    }

    public static interface LoginForm {};
}

