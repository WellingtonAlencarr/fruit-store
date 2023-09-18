package com.wellington.test.fruits;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/fruits")
@ApplicationScoped
public class FruitController {

    private final FruitService fruitService;

    @Inject
    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GET
    public List<Fruit> list() {
        return fruitService.list();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        return Response.ok(fruitService.findById(id)).build();
    }

    @POST
    public Fruit create(@Valid Fruit fruit) {
        fruitService.create(fruit);
        return fruit;
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") long id, @Valid Fruit fruit) {
        fruitService.update(id, fruit);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
        fruitService.delete(id);
    }
}
