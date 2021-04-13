package com.teo.persistence;

import com.teo.entities.Item;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ItemsDAO {

    @Inject
    private EntityManager em;

    public List<Item> loadAll() {
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Item item){
        this.em.persist(item);
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public Item update(Item item){
        return em.merge(item);
    }
}
