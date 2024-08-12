package org.products.controller;

import org.products.entity.ProductEntity;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.products.service.ProductService;

import java.util.UUID;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @POST
    @Transactional
    public Response create(ProductEntity product) {
        return Response.ok(service.create(product)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") UUID productId, ProductEntity product) {
        return Response.ok(service.update(productId, product)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") UUID productId) {
        service.delete(productId);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID productId) {
        return Response.ok(service.findById(productId)).build();
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        return Response.ok(service.findAll(page, pageSize)).build();
    }
}
