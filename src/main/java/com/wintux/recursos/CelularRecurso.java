package com.wintux.recursos;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/v1/celular")
public class CelularRecurso {
    private List<String> listaCelulares = new ArrayList<>();
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getListaCelulares() {
        return Response.ok(listaCelulares).build();
    }
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addNumeroCelular(String num){
        listaCelulares.add(num);
        return Response.created(URI.create("/api/v1/celular/"+num)).build();
    }
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{actual}") // http://localhost:8080/api/v1/celular/1234?num=4321
    public Response modificarNumCelular(@PathParam("actual") String actual, @QueryParam("num") String nuevo){
        listaCelulares.forEach(System.out::println);
        System.out.println("Actual: " + actual);
        listaCelulares = listaCelulares.stream().map(cel -> {
            if(cel.equals(actual)){
                return nuevo;
            }else {
                return cel;
            }
        }).collect(Collectors.toList());
        return Response.ok(listaCelulares).build();
    }
    @DELETE
    @Path("/{actual}")
    public Response eliminarNumeroCelular(@PathParam("actual") String num){
        if(listaCelulares.remove(num))
            return Response.noContent().build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}