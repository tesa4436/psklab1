package com.teo.usecases;


import com.teo.interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import com.teo.entities.Item;
import com.teo.persistence.ItemsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateBasket implements Serializable {

    private Item item;

    @Inject
    private ItemsDAO itemsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateItem init() called");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long playerId = Long.parseLong(requestParameters.get("itemId"));
        this.item = itemsDAO.findOne(playerId);
    }

    /*
    @Transactional
    @LoggedInvocation
    public String updatePlayerJerseyNumber() {
        try{
            itemsDAO.update(this.item);
        } catch (OptimisticLockException e) {
            return "/itemDetails.xhtml?faces-redirect=true&playerId=" + this.item.getId() + "&error=optimistic-lock-exception";
        }
        return "basketDetails.xhtml?teamId=" + this.player.getTeam().getId() + "&faces-redirect=true";
    }

     */
}
