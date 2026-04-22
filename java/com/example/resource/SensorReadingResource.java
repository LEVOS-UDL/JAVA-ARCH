/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.resource;
import com.example.dao.MockDatabase;
import com.example.exception.DataNotFoundException;
import com.example.exception.SensorUnavailableException;
import com.example.model.Sensor;
import com.example.model.SensorReading;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author joe
 */
@Path("/sensors/{sensorId}/readings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
    @PathParam("sensorId")
    private String sensorId;

    @GET
    public List<SensorReading> getReadings() {
        if (!MockDatabase.SENSORS.containsKey(sensorId)) {
            throw new DataNotFoundException("Sensor not found: " + sensorId);
        }
        return MockDatabase.READINGS.getOrDefault(sensorId, new ArrayList<>());
    }

    @POST
    public void addReading(SensorReading reading) {
        Sensor sensor = MockDatabase.SENSORS.get(sensorId);
        if (sensor == null) {
            throw new DataNotFoundException("Sensor not found: " + sensorId);
        }

        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor is under maintenance: " + sensorId);
        }

        
        reading.setId(UUID.randomUUID().toString());

        
        MockDatabase.READINGS.putIfAbsent(sensorId, new ArrayList<>());
        MockDatabase.READINGS.get(sensorId).add(reading);

        
        sensor.setCurrentValue(reading.getValue());
    }
}
