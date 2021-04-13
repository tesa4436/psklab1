package com.teo.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BASKET")
@Builder
@Getter
@NamedQueries({
        @NamedQuery(name = "Basket.findAll", query = "select a from Basket as a")
})
@AllArgsConstructor
@NoArgsConstructor
public class Basket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(targetEntity = Item.class)
    List<Item> items = new ArrayList<>();
}