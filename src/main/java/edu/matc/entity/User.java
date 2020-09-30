package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "user")
public class User {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "user_id")
    private int userId;

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Instantiates a new User.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param password  the password
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /**
     * Sets first name
     *
     * @param firstName user's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets first name
     *
     * @return user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets last name
     *
     * @param lastName user's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets last name
     *
     * @return user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets email
     *
     * @param email user's email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets email
     *
     * @return user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets password
     *
     * @param password user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets password
     *
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user id
     *
     * @param userId user's ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets user id
     *
     * @return user's id
     */
    public int getUserId() {
        return userId;
    }


}
