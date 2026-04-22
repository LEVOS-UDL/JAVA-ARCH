/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author joe
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryResource {
    @GET
    public Map<String, String> getInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("rooms", "/api/v1/rooms");
        info.put("sensors", "/api/v1/sensors");
        return info;
    }
}
