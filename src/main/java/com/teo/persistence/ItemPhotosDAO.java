package com.teo.persistence;

import com.teo.entities.ItemPhoto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ItemPhotosDAO {

    @Inject
    private EntityManager em;

    public List<ItemPhoto> loadAll() {
        return em.createNamedQuery("ItemPhoto.findAll", ItemPhoto.class).getResultList();
    }

    public void persist(ItemPhoto itemPhoto){
        this.em.persist(itemPhoto);
    }

    public ItemPhoto findOne(Long id){
        return em.find(ItemPhoto.class, id);
    }

    public ItemPhoto update(ItemPhoto itemPhoto){
        return em.merge(itemPhoto);
    }
}
