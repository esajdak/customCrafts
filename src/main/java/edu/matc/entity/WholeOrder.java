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
@Entity(name = "WholeOrder")
@Table(name = "whole_order")
@Getter @Setter @NoArgsConstructor
@ToString @EqualsAndHashCode
public class WholeOrder {
    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id"))
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "order_number")
    private int orderNumber;

    @OneToMany(mappedBy = "wholeOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude @EqualsAndHashCode.Exclude private Set<OrderItem> orderItems = new HashSet<>();

    /**
     * Instantiates a new WholeOrder.
     *
     * @param user
     */
    public WholeOrder(User user) {
        this.user = user;
    }


    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }



    /**
     * Add product.
     *
     * @param product the product
     */
//    public void addProduct(Product product) {
//        products.add(product);
//        product.setUser(this);
//    }

    /**
     * Remove product.
     *
     * @param product the product
     */
//    public void removeProduct(Product product) {
//        products.remove(product);
//        product.setUser(null);
//    }

    /**
     * Add orderItem.
     *
     * @param orderItem the orderItem
     */
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setWholeOrder(this);
    }

    /**
     * Remove orderItem.
     *
     * @param orderItem the orderItem
     */
    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setWholeOrder(null);
    }
}
