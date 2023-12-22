package com.controller;

import com.dto.AccessDto;
import com.dto.BookingDto;
import com.dto.BookingInfoDto;
import com.dto.ResponseDto;
import com.dto.UserDto;
import com.service.LockerService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/locker-service")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LockerCtl {
    @Inject
    LockerService lockerService;
    
    @POST
    @Path("/register")
    public Response registerUser(@Valid UserDto dto){
        return Response.ok(new ResponseDto(String.valueOf(Response.Status.OK.getStatusCode()),Response.Status.OK.getReasonPhrase(),lockerService.registerUser(dto))).build();
    }

    @POST
    @Path("/book-locker")
    public Response bookLocker(BookingDto dto) {
        return Response.ok(new ResponseDto(String.valueOf(Response.Status.OK.getStatusCode()),Response.Status.OK.getReasonPhrase(),lockerService.booking(dto))).build();
    }

    @POST
    @Path("/generate-password")
    public Response generatePassword(BookingInfoDto dto) {
        return Response.ok(new ResponseDto(String.valueOf(Response.Status.OK.getStatusCode()),Response.Status.OK.getReasonPhrase(),lockerService.generatePassword(dto))).build();
    }

    @POST
    @Path("/return-locker")
    public Response returnLocker(BookingInfoDto dto) {
        return Response.ok(new ResponseDto(String.valueOf(Response.Status.OK.getStatusCode()),Response.Status.OK.getReasonPhrase(),lockerService.returnLokers(dto))).build();
    }

    @POST
    @Path("/access-locker")
    public Response accessLocker(AccessDto dto) {
        return Response.ok(new ResponseDto(String.valueOf(Response.Status.OK.getStatusCode()),Response.Status.OK.getReasonPhrase(),lockerService.accessLocker(dto))).build();
    }

    
}
