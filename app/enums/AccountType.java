package enums;

import com.avaje.ebean.annotation.EnumValue;

/**
 * Account Type Enum
 * @author Maciej Zbierowski
 */
public enum AccountType {

    /** Admin */
    @EnumValue("ADMIN")
    ADMIN,

    /** Client */
    @EnumValue("CLIENT")
    CLIENT

}