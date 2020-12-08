package edu.matc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity(name = "OrderItem")
@Table(name = "order_item")
@Getter @Setter @NoArgsConstructor
@ToString @EqualsAndHashCode
public class OrderItem {
    @ManyToOne
    @JoinColumn(name = "order_number", foreignKey = @ForeignKey(name = "order_number"))
    private WholeOrder wholeOrder;

    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "item_id"))
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id")
    private int id;


    /**
     * Instantiates a new OrderItem.
     *
     * @param product  the product
     * @param wholeOrder    the order
     * @param quantity the quantity
     */
    public OrderItem(Product product, WholeOrder wholeOrder, int quantity) {
        this.product = product;
        this.wholeOrder = wholeOrder;
        this.quantity = quantity;
    }

}
