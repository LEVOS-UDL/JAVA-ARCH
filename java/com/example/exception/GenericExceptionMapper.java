/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.exception;
import com.example.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 *
 * @author joe
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
    @Override
    public Response toResponse(Throwable ex) {
        return Response.status(500)
                .entity(new ErrorMessage("", 500, ""))
                .build();
    }
}
