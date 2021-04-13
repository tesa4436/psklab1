package com.teo.persistence;

import com.teo.entities.Basket;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class BasketsDAO {

    @Inject
    private EntityManager em;

    public List<Basket> loadAll() {
        return em.createNamedQuery("Basket.findAll", Basket.class).getResultList();
    }

    public void persist(Basket basket){
        this.em.persist(basket);
    }

    public Basket findOne(Long id){
        return em.find(Basket.class, id);
    }

    public Basket update(Basket basket){
        return em.merge(basket);
    }
}
