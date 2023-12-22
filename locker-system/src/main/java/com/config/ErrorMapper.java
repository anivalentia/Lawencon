package com.config;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dto.ResponseDto;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ErrorMapper implements ExceptionMapper<Exception>{
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorMapper.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        LOGGER.error("failed to handle request", exception);
        
        ResponseDto response = new ResponseDto();
        response.setMessage(Objects.isNull(exception.getMessage()) ? "general error": exception.getMessage());
        response.setStatus(String.valueOf(Status.INTERNAL_SERVER_ERROR.getStatusCode()));
        response.setPayload(StringUtils.EMPTY);

        return Response.status(Status.OK).entity(response).build();
    }
    
}
