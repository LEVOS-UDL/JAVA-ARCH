package com.example.resource;
import com.example.dao.MockDatabase;
import com.example.exception.DataNotFoundException;
import com.example.exception.RoomNotEmptyException;
import com.example.model.Room;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import javax.ws.rs.ApplicationPath;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joe
 */
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    @GET
    public Collection<Room> getAllRooms() {
        return MockDatabase.ROOMS.values();
    }

    @POST
    public String addRoom(Room room) {
    if (room == null) {
        return "ROOM IS NULL";
    }

    if (room.getId() == null) {
        return "ROOM ID IS NULL";
    }

    MockDatabase.ROOMS.put(room.getId(), room);

    return "ROOM SAVED: " + MockDatabase.ROOMS.size();
}

    @GET
    @Path("/{id}")
    public Room getRoom(@PathParam("id") String id) {
        Room room = MockDatabase.ROOMS.get(id);
        if (room == null) throw new DataNotFoundException("Room not found");
        return room;
    }

    @DELETE
    @Path("/{id}")
    public void deleteRoom(@PathParam("id") String id) {
        Room room = MockDatabase.ROOMS.get(id);

        if (room == null) throw new DataNotFoundException("Room not found");

        if (!room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room not empty"); 
        }

        MockDatabase.ROOMS.remove(id);
    }
   
    @ApplicationPath("/api/v1")
    public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("");
        register(JacksonFeature.class);
    }
}
}
