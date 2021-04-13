package com.teo.usecases;

import com.teo.entities.Item;
import com.teo.services.IdService;
import lombok.Getter;
import lombok.Setter;
import com.teo.entities.Basket;
import com.teo.persistence.BasketsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Baskets {

    @Inject
    private BasketsDAO basketsDAO;
    @Inject
    private IdService idService;

    @Getter
    @Setter
    private Basket basketToCreate;

    @Getter
    private List<Basket> allBaskets;

    @PostConstruct
    public void init() {
        basketToCreate = new Basket();
        loadAllBaskets();
    }

    @Transactional
    public String createBasket(){
        this.basketsDAO.persist(basketToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllBaskets(){
        this.allBaskets = basketsDAO.loadAll();
    }
}
