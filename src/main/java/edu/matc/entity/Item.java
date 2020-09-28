package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Item {
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
    private String ownerId;

    @Column(name = "customizable")
    private boolean customizable;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int itemId;

    public Item() {

    }

    /**
     * todo need to add getters and setters
     */
}
