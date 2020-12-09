package edu.matc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The type User.
 */
@Entity(name = "User")
@Table(name = "user")
@Getter @Setter @NoArgsConstructor
@ToString @EqualsAndHashCode
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude @EqualsAndHashCode.Exclude private Set<Product> products = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude @EqualsAndHashCode.Exclude private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude @EqualsAndHashCode.Exclude private Set<WholeOrder> orders = new HashSet<>();

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
     * Add product.
     *
     * @param product the product
     */
    public void addProduct(Product product) {
        products.add(product);
        product.setUser(this);
    }

    /**
     * Remove product.
     *
     * @param product the product
     */
    public void removeProduct(Product product) {
        products.remove(product);
        product.setUser(null);
    }

    /**
     * Add role.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        roles.add(role);
        role.setUser(this);
    }

    /**
     * Remove role.
     *
     * @param role the role
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.setUser(null);
    }

    /**
     * Add wholeOrder.
     *
     * @param wholeOrder the wholeOrder
     */
    public void addWholeOrder(WholeOrder wholeOrder) {
        orders.add(wholeOrder);
        wholeOrder.setUser(this);
    }

    /**
     * Remove wholeOrder.
     *
     * @param wholeOrder the wholeOrder
     */
    public void removeWholeOrder(WholeOrder wholeOrder) {
        orders.remove(wholeOrder);
        wholeOrder.setUser(null);
    }
}
