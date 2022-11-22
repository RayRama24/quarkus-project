package org.kawahedukasi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="order_item")
public class OrderItem extends PanacheEntityBase {

    @Id
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name = "id", length = 36, nullable = false)
    private String id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price_total", nullable = false)
    private Double priceTotal;

    @Column(name = "note", columnDefinition = "text")
    private String note;
}
