package models;

import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Admin class
 * @author Maciej Zbierowski
 */
@Entity
public class Admin extends Model{

    /** id */
    @Id
    public Long id;

    /** account*/
    @OneToOne
    @JoinColumn(name="account_id")
    public Account account;
}
