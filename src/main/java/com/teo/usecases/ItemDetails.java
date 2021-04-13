package com.teo.usecases;

import com.teo.entities.Basket;
import com.teo.entities.Item;
import com.teo.interceptors.LoggedInvocation;
import com.teo.persistence.ItemsDAO;
import com.teo.services.ImageService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class ItemDetails implements Serializable {

    @Inject
    private ItemsDAO itemsDAO;
    @Inject
    private ImageService imageService;

    @Getter
    @Setter
    private Basket basket;

    @Getter
    @Setter
    private Item item;

    @Getter
    @Setter
    private Long itemIdToBeAdded;

    @Getter
    @Setter
    private Part photoToBeUploaded;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String itemIdString = requestParameters.get("itemId");

        if (itemIdString != null) {
            try {
                Long itemId = Long.parseLong(itemIdString);
                this.item = itemsDAO.findOne(itemId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    @Transactional
    @LoggedInvocation
    public String uploadPhoto() {
        if (photoToBeUploaded != null && item != null) {
            imageService.uploadPhoto(photoToBeUploaded, item);
        }

        return "itemDetails?faces-redirect=true&itemId=" + this.item.getId();
    }

    @Transactional
    @LoggedInvocation
    public String addItem(Item item) {
        itemsDAO.persist(item);
        return "itemDetails?faces-redirect=true&itemId=" + this.item.getId();
    }
}
