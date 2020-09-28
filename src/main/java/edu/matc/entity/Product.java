package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Product {
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private String price;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "tags")
    private String tags;

    @Column(name = "production_cost")
    private String productionCost;

    @Column(name = "owner_id")
    private int ownerId;

    @Column(name = "customizable")
    private boolean customizable;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int itemId;

    public Product() {

    }

    /**
     * todo need to add getters and setters
     */

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
     * Sets owner's id number
     *
     * @param ownerId
     */
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Gets ownerId
     *
     * @return owner's id number
     */
    public int getOwnerId() {
        return ownerId;
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


}
