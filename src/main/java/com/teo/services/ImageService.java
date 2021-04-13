package com.teo.services;

import com.teo.entities.Item;
import com.teo.entities.ItemPhoto;
import com.teo.persistence.ItemsDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class ImageService {

    @Inject
    private ItemsDAO itemsDAO;

    public BufferedImage resize(BufferedImage image) {
        return null;
    }

    public void uploadPhoto(Part image, Item item) {
        try {
            byte[] blob = image.getInputStream().readAllBytes();
            List<ItemPhoto> currentPhotos = item.getPhotos();

            currentPhotos.add(ItemPhoto.builder()
                    .itemId(item.getId())
                    .photo(blob)
                    .build());

            item.setPhotos(currentPhotos);
            itemsDAO.update(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
