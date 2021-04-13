package com.teo.usecases;

import com.teo.entities.Basket;
import com.teo.entities.Item;
import com.teo.persistence.ItemsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Items {

    @Inject
    private ItemsDAO itemsDAO;

    @Getter
    @Setter
    private Item itemToCreate;

    @Getter
    private List<Item> allItems;

    @PostConstruct
    public void init(){
        itemToCreate = new Item();
        loadAllItems();
    }

    @Transactional
    public String createItem(){
        this.itemsDAO.persist(itemToCreate);
        return "items?faces-redirect=true";
    }

    private void loadAllItems(){
        this.allItems = itemsDAO.loadAll();
    }
}
