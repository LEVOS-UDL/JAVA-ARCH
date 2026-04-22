/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;
import com.example.dao.MockDatabase;
import com.example.exception.LinkedResourceNotFoundException;
import com.example.model.Sensor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
/**
 *
 * @author joe
 */
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {
    @GET
    public Collection<Sensor> getSensors(@QueryParam("type") String type) {
        if (type == null) return MockDatabase.SENSORS.values();

        List<Sensor> filtered = new ArrayList<>();
        for (Sensor s : MockDatabase.SENSORS.values()) {
            if (s.getType().equalsIgnoreCase(type)) {
                filtered.add(s);
            }
        }
        return filtered;
    }

    @POST
    public void addSensor(Sensor sensor) {
        if (!MockDatabase.ROOMS.containsKey(sensor.getRoomId())) {
            throw new LinkedResourceNotFoundException("Room does not exist"); 
        }

        MockDatabase.SENSORS.put(sensor.getId(), sensor);
        MockDatabase.ROOMS.get(sensor.getRoomId()).getSensorIds().add(sensor.getId());
    }

    @Path("/{id}/readings")
    public SensorReadingResource getReadingResource() {
        return new SensorReadingResource();
    }
}
