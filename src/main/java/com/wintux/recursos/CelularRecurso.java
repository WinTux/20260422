package com.wintux.recursos;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

@Path("/api/v1/celular")
public class CelularRecurso {
    private List<String> listaCelulares = new ArrayList<>();
    @GET
    public List<String> getListaCelulares() {
        return listaCelulares;
    }
    @POST
    public void addNumeroCelular(String num){
        listaCelulares.add(num);
    }
}
