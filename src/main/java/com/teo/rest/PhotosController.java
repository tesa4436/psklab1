package com.teo.rest;

import com.teo.entities.Item;
import com.teo.entities.ItemPhoto;
import com.teo.persistence.ItemPhotosDAO;
import com.teo.persistence.ItemsDAO;
import lombok.*;
import com.teo.rest.contracts.ItemDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/photo")
public class PhotosController {

    @Inject
    private ItemPhotosDAO itemPhotosDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getById(@PathParam("id") final Long id) {
        ItemPhoto photo = itemPhotosDAO.findOne(id);

        if (photo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(photo.getPhoto()).build();
    }
}
