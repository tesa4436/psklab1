package com.teo.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ITEM")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "select a from Item as a")
})
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Long amount;
    private Date creationDate;
    private String description;
    private BigDecimal price;

    @OneToMany(targetEntity = ItemPhoto.class, cascade=CascadeType.ALL)
    private List<ItemPhoto> photos = new ArrayList<>();
}
