package com.wellington.test.fruits;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

import java.time.LocalDate;

@Path("test")
@ApplicationScoped
public class TestController {

    @GET
    public String test(@QueryParam("name") String name, @QueryParam("age") int age) {
        return "Olá " + name + ", Você vai morrer em: " + (76 - age + LocalDate.now().getYear());
    }
}
