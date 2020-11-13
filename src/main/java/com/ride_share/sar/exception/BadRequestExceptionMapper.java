package com.ride_share.sar.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

	@Override
	public Response toResponse(BadRequestException exception) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage("http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation", "Your request data didn't pass validation", exception.getMessage(), 400, exception.getInstance());
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
