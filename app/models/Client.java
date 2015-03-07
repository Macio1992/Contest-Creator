package models;

import play.db.ebean.Model;

import javax.persistence.*;

/**
 * Client class
 * @author Maciej Zbierowski
 */
@Entity
public class Client extends Model{

    /** id */
    @Id
    public Long id;

    /** account*/
    @OneToOne
    @JoinColumn(name="account_id")
    public Account account;
}
