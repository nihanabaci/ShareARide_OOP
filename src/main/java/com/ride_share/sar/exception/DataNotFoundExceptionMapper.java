package com.ride_share.sar.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		//ErrorMessage errorMessage = new ErrorMessage(ex., 404, "link");
		return Response.status(Status.NOT_FOUND)
				.entity(ex.getMessage())
				.build();
	}

}
