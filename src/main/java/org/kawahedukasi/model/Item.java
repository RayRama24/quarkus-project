package org.kawahedukasi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.kawahedukasi.model.base.UpdateBase;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item extends UpdateBase {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "descriptioin" , nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "category" , nullable = false, length = 20)
    private String category;

    @Column(name = "price" , nullable = false)
    private Double price;

    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne(targetEntity = User.class)
    @JsonIgnore
    @JoinColumn(name = "update_by", nullable = false)
    private User updateBy;

    public Item() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }
}
