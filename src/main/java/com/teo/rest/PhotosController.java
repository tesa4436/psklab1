package com.teo.rest;

import com.teo.entities.ItemPhoto;
import com.teo.interceptors.LoggedInvocation;
import com.teo.persistence.ItemPhotosDAO;
import com.teo.persistence.ItemsDAO;
import com.teo.services.IPreMethodCall;
import com.teo.services.NameService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
@Path("/photo")
public class PhotosController implements IPreMethodCall {

    @Inject
    private ItemPhotosDAO itemPhotosDAO;
    @Inject
    private ItemsDAO itemsDAO;
    @Inject
    private NameService nameService;

    @Override
    public void preCall() {
        System.out.println("pre call in photos controller");
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response getById(@PathParam("id") final Long id) {
        preCall();
        ItemPhoto photo = itemPhotosDAO.findOne(id);

        if (photo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(photo.getPhoto()).build();
    }

    @Path("/{id}/name")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getName(@PathParam("id") final Long id) {
        ItemPhoto photo = itemPhotosDAO.findOne(id);

        if (photo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(photo.getName()).build();
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @LoggedInvocation
    public Response postPhoto(InputStream requestBody, @PathParam("id") final Long id) {

        byte[] photo;

        try {
            photo = requestBody.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        if (photo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (itemsDAO.findOne(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        ItemPhoto newPhoto = ItemPhoto.builder()
                .photo(photo)
                .itemId(id)
                .name(nameService.generateRandomName())
                .build();

        itemPhotosDAO.persist(newPhoto);

        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public Response changePhotoName(@PathParam("id") final Long id, final String name) {
        ItemPhoto photo = itemPhotosDAO.findOne(id);

        if (photo == null || name == null || name.equals("")) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        photo.setName(name);
        itemPhotosDAO.update(photo);

        return Response.ok().build();
    }
}
