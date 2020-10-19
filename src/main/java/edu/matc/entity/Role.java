package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Role.
 */
@Entity(name = "Role")
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "role_name")
    private String roleName;

    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "role_user_user_id_fk"))
    private User user;

    public Role(String roleName) {
    }

    public Role(String roleName, String email, User user) {
        this.roleName = roleName;
        this.email = email;
        this.user = user;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets role name.
     *
     * @return the role name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets role name.
     *
     * @param roleName the role name
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(email, role.email) &&
                Objects.equals(user, role.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, email, user);
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
