package org.kawahedukasi.controller;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.kawahedukasi.model.dto.UserRequest;
import org.kawahedukasi.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.security.NoSuchAlgorithmException;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @Inject
    JsonWebToken jwt;


    @POST
    @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema (implementation = UserRequest.class)))
    @APIResponses({
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, example = "{}"))
    })
    public Response post(UserRequest request) throws NoSuchAlgorithmException {
        return userService.post(request);
    }

    @GET
    @RolesAllowed("user")
    public Response get(){
      return userService.get(jwt.getSubject());
    }
}
