package com.wintux.recursos;

import com.wintux.Modelos.NumCelular;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/v1/celulares")
public class CelularesRecurso {
    List<NumCelular> listaCelulares = new ArrayList<>();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaCelulares(){
        return Response.ok(listaCelulares).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCelular(NumCelular celular){
        listaCelulares.add(celular);
        return Response.created(URI.create("/api/v1/celulares/"+celular.getId())).build();
    }
    @PUT
    @Path("/{actual}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarCelular(NumCelular celular, @PathParam("actual") Integer id){
        listaCelulares = listaCelulares.stream().map(cel -> {
            if(cel.getId()==id){
                celular.setId(id);
                return celular;
            }else{
                return cel;
            }
        }).collect(Collectors.toList());
        return Response.ok(listaCelulares).build();
    }
    @DELETE
    @Path("/{actual}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response eliminarCelular(@PathParam("actual") Integer id){
        if(listaCelulares.removeIf(num -> num.getId()==id)){
            return Response.ok("ELIMINADO").build();
        }else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
