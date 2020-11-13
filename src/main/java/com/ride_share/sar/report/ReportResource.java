package com.ride_share.sar.report;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.ride_share.sar.accounts.Account;
import com.ride_share.sar.accounts.AccountService;
import com.ride_share.sar.exception.DataNotFoundException;
import com.ride_share.sar.rides.Ride;

@Path("/reports")
public class ReportResource {
	
	ReportService reportService = new ReportService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReport()
	{
		JSONArray jarr = new JSONArray();
		reportService.generateReport();
		if(reportService.getAllReports() != null)
		{
			jarr = new JSONArray(reportService.getAllReports());
		}
		return Response.ok(jarr).build();
	
		
	}
	@GET
	@Path("/{Pid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRides(@PathParam("Pid") long pid, @QueryParam("start_date") String start_date
			, @QueryParam("end_date") String end_date) throws ParseException
	{
//		if(Objects.isNull(reportService.getReportMap().get(pid) == null))
//		{
//			throw new DataNotFoundException("404 (Not found) the ride identified by " + pid + " doesn't exist.");
//		}
		if(reportService.getAllReports() == null)
		{
			reportService.generateReport();	
		}
		
		return Response.ok(reportService.getReport(pid, start_date, end_date), MediaType.APPLICATION_JSON).status(201).build();
		
	}
}

	
	
	

