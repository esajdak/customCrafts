package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * A class to represent a product.
 *
 * @author Elizabeth Sajdak
 */
@Entity(name = "Product")
@Table(name = "product") // case sensitive!
public class Product {
    // don't need to annotate if column names are the same.
    private String title;
    private String price;
    private String image;
    private String description;
    private String tags;

    @Column(name = "production_cost")
    private String productionCost;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id"))
    private User user;

    @Column(name = "customizable")
    private boolean customizable;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "item_id")
    private int itemId;

    public Product() {

    }


    // todo need different constructor to deal with optional values?
    public Product(String description, User user, String image, String tags, String productionCost, String price, int Customizable, String title) {
        this.user = user;
        this.description = description;
        this.image = image;
        this.tags = tags;
        this.productionCost = productionCost;
        this.price = price;
        this.customizable = customizable;
        this.title = title;
    }

    /**
     * Sets product's id number
     *
     * @param itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Gets itemId
     *
     * @return product's id number
     */
    public int getItemId() {
        return itemId;
    }


    /**
     * Sets product image
     *
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets image
     *
     * @return product's image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets product's description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets description
     *
     * @return product's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets tags for product
     *
     * @param tags
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * Gets tags
     *
     * @return tags for product
     */
    public String getTags() {
        return tags;
    }

    /**
     * Sets production cost of product
     *
     * @param productionCost
     */
    public void setProductionCost(String productionCost) {
        this.productionCost = productionCost;
    }

    /**
     * Gets production cost of product
     *
     * @return productionCost
     */
    public String getProductionCost() {
        return productionCost;
    }

    /**
     * Sets price of product
     *
     * @param price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Gets price of product
     *
     * @return price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets title of product
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets title
     *
     * @return title of product
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets customizable
     *
     * @param customizable
     */
    public void setCustomizable(boolean customizable) {
        this.customizable = customizable;
    }

    /**
     * Gets customizable
     *
     * @return customizable
     */
    public boolean getCustomizable() {
        return customizable;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return customizable == product.customizable &&
                itemId == product.itemId &&
                Objects.equals(title, product.title) &&
                Objects.equals(price, product.price) &&
                Objects.equals(image, product.image) &&
                Objects.equals(description, product.description) &&
                Objects.equals(tags, product.tags) &&
                Objects.equals(productionCost, product.productionCost) &&
                Objects.equals(user, product.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, image, description, tags, productionCost, user, customizable, itemId);
    }


//    @Override
//    public String toString() {
//        return "Product{" +
//                "title='" + title + '\'' +
//                ", price='" + price + '\'' +
//                ", image='" + image + '\'' +
//                ", description='" + description + '\'' +
//                ", tags='" + tags + '\'' +
//                ", productionCost='" + productionCost + '\'' +
//                ", user=" + user +
//                ", customizable=" + customizable +
//                ", itemId=" + itemId +
//                '}';
//    }


}
