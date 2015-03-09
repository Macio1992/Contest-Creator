package enums;

import com.avaje.ebean.annotation.EnumMapping;

/**
 * Account Status Enum
 * @author Maciej Zbierowski
 */
@EnumMapping(nameValuePairs = "ACTIVE=A, NOT_CONFIRMED=N, DELETED=D")
public enum AccountStatus {

    /** Active status*/
    ACTIVE,

    /** Not confirmed status*/
    NOT_CONFIRMED,

    /** Deleted status*/
    DELETED
}