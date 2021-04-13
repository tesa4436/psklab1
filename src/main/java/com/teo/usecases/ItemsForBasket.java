package com.teo.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import com.teo.entities.Item;
import com.teo.interceptors.LoggedInvocation;
import com.teo.persistence.ItemsDAO;
import lombok.Getter;
import lombok.Setter;
import com.teo.entities.Basket;
import com.teo.persistence.BasketsDAO;

@Model
public class ItemsForBasket implements Serializable {

    @Inject
    private ItemsDAO itemsDAO;
    @Inject
    private BasketsDAO basketsDAO;

    @Getter
    @Setter
    private Basket basket;

    @Getter
    @Setter
    private Item itemToAdd = new Item();

    @Getter
    @Setter
    private String itemIdToBeAdded;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String basketIdString = requestParameters.get("basketId");

        if (basketIdString != null) {
            Long basketId = Long.parseLong(basketIdString);
            this.basket = basketsDAO.findOne(basketId);
        }
    }

    public String itemAlreadyInBasket(Item item) {
        return basket.getItems().contains(item) ? "true" : "false";
    }

    public String reload() {
        return "basketDetails?basketId=" + this.basket.getId();
    }

    @Transactional
    @LoggedInvocation
    public String addItem() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        try {
            Long id = Long.parseLong(itemIdToBeAdded);

            Item item = itemsDAO.findOne(id);

            if (item != null && !basket.getItems().contains(item)) {
                basket.getItems().add(item);
                basketsDAO.update(basket);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "basketDetails?basketId=" + this.basket.getId();
        }

       return "basketDetails?basketId=" + this.basket.getId();
    }
}