package com.teo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "ItemPhoto.findAll", query = "select t from ItemPhoto as t")
})
@Table(name = "ITEMPHOTO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long itemId;

    private String name;

    @Lob
    private byte[] photo;
}
