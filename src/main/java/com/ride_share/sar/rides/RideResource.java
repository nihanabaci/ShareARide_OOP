package com.ride_share.sar.rides;
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
import org.json.JSONObject;

import com.ride_share.sar.accounts.Account;
import com.ride_share.sar.accounts.AccountService;
import com.ride_share.sar.accounts.idClass;
import com.ride_share.sar.database.DatabaseClass;
import com.ride_share.sar.exception.BadRequestException;
import com.ride_share.sar.exception.DataNotFoundException;

@Path("/rides")
public class RideResource {
	
	RideService rideService= new RideService();
	//Create ride
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRide(Ride ride)
	{
		if(Objects.isNull(ride.getAid()))
		{
			throw new BadRequestException("Invalid Aid", "/rides");
		}
		if(ride.getLocation_info() == null)
		{
			throw new BadRequestException("Invalid location information", "/rides");
		}if(ride.getDate_time() == null)
		{
			throw new BadRequestException("Invalid date and time information", "/rides");
		}if(ride.getCar_info() == null)
		{
			throw new BadRequestException("Invalid car", "/rides");
		}
		if(Objects.isNull(ride.getMax_passengers()))
		{
			throw new BadRequestException("Invalid max_passengers", "/rides");
		}if(Objects.isNull(ride.getAmount_per_passenger()))
		{
			throw new BadRequestException("Invalid amound per passenger", "/rides");
		}
		if(ride.getConditions() == null)
		{
			throw new BadRequestException("Invalid conditions", "/rides");
		}
		if(rideService.getAcc(ride.getAid()) == null)
		{
			throw new BadRequestException("Invalid Aid", "/rides");
		}
		
		rideService.addRide(ride);
		long id = ride.getRid();
		ridClass idc = new ridClass(id);
		return Response.ok(idc, MediaType.APPLICATION_JSON).status(201).build();
	}
	//Update Ride
	@PUT
	@Path("/{rideID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRide(@PathParam("rideID") long id, Ride ride)
	{
		if(Objects.isNull(ride.getAid()))
		{
			throw new BadRequestException("Invalid Aid", "/rides/" + id);
		}
		if(ride.getLocation_info() == null)
		{
			throw new BadRequestException("Invalid location information", "/rides" + id);
		}if(ride.getDate_time() == null)
		{
			throw new BadRequestException("Invalid date and time information", "/rides" + id);
		}if(ride.getCar_info() == null)
		{
			throw new BadRequestException("Invalid car", "/rides" + id);
		}
		if(Objects.isNull(ride.getMax_passengers()))
		{
			throw new BadRequestException("Invalid max_passengers", "/rides" + id);
		}if(Objects.isNull(ride.getAmount_per_passenger()))
		{
			throw new BadRequestException("Invalid amound per passenger", "/rides" + id);
		}if(ride.getConditions() == null)
		{
			throw new BadRequestException("Invalid conditions", "/rides" + id);
		}
		if(rideService.getRide(id) == null)
		{
			throw new DataNotFoundException("404 (Not found) the ride identified by " + id + " doesn't exist.");
		}
		ride.setRid(id);
		rideService.updateRide(ride);
		return Response.status(204).build();
	}
	//Delete Ride
	@DELETE
	@Path("/{rideID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRide(@PathParam("rideID") long id)
	{
		if(rideService.getRide(id) == null)
		{
			throw new DataNotFoundException("404 (Not found) the ride identified by " + id + " doesn't exist.");
		}
		rideService.removeAccount(id);
		return Response.status(204).build();
	}
	
	//View all rides and Search Rides
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRides(@QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("date") String date)
	{
		List<Ride> lor = rideService.getAllRides();
		JSONArray jarr = new JSONArray();
		if(!((from == null) && (to == null) && (date == null)))
		{
			lor = rideService.searchRide(from, to, date);
		}
		if(lor != null)
		{
			jarr = new JSONArray(lor);
		}
		
		return Response.ok(jarr).build();
		
	}
	//View ride detail
	@GET
	@Path("/{rideID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ride getRide(@PathParam("rideID") long id)
	{
		if(rideService.getRide(id) == null)
		{
			throw new DataNotFoundException("404 (Not found) the ride identified by " + id + " doesn't exist.");
		}
		return rideService.getRide(id);
	}
	
	//Request to join
	@POST
	@Path("/{rideID}/join_requests")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendJoinRequest(@PathParam("rideID") long rid, JoinRequest join_request)
	{
		if(Objects.isNull(join_request.getAid()))
		{
			throw new BadRequestException("Invalid Aid", "/rides/" + rid + "/join_requests");
		}
		if(Objects.isNull(join_request.getPassengers()))
		{
			throw new BadRequestException("Invalid passengers", "/rides/" + rid + "/join_requests");
		}
		if(join_request.isRide_confirmed() == null)
		{
			throw new BadRequestException("Invalid ride_confirmed", "/rides" + rid + "/join_requests");
		}if(join_request.isPickup_confirmed() == null)
		{
			throw new BadRequestException("Invalid pickup_confirmed", "/rides" + rid + "/join_requests");
		}
		if(rideService.getAcc(join_request.getAid()) == null)
		{
			throw new DataNotFoundException("404 (Not found) the ride identified by " + join_request.getAid() + " doesn't exist.");
		}
		
		join_request.setRid(rid);
		rideService.sendJoinRequest(rid, join_request);
		long jid = join_request.getJid();
		jidClass idc = new jidClass(jid);
		return Response.ok(idc, MediaType.APPLICATION_JSON).status(201).build();
	}
	
	//GETTING JOIN REQUEST
	@GET
	@Path("/{rideID}/join_requests")
	@Produces(MediaType.APPLICATION_JSON)
	public List<JoinRequest> getJoinRequest(@PathParam("rideID") long id)
	{
		
		return rideService.getAllJoinRequests(id);
	}
	
	
	//Confirm deny a join ride request ---
	@PATCH
	@Path("/{rideID}/join_requests/{j_requestID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response patchRow(@PathParam("rideID") long rid, @PathParam("j_requestID") long jid, JoinRequest jr) 
	{
		if(Objects.isNull(jr.getAid()))
		{
			throw new BadRequestException("Invalid Aid", "/rides/" + rid + "/join_requests/" + jid);
		}
		if(jr.isRide_confirmed() == null)
		{
			throw new BadRequestException("Invalid ride_confirmed", "/rides" + rid + "/join_requests/" + jid);
		}
		if(jr.isPickup_confirmed() == null)
		{
			throw new BadRequestException("Invalid pickup_confirmed", "/rides" + rid + "/join_requests/" + jid);
		}
		
		List<JoinRequest> join_r = rideService.getAllJoinRequests(rid);
		JoinRequest wanted_join_r = new JoinRequest();
		for(JoinRequest j_r : join_r)
		{
			if(j_r.getJid() == jid)
			{
				wanted_join_r = j_r;
			}
		}
		if(jr.isPickup_confirmed() == true)
		{
			wanted_join_r.setPickup_confirmed(true);
		}
		if(jr.isRide_confirmed() == true)
		{
			wanted_join_r.setRide_confirmed(true);
		}
		
		return Response.status(200).build();
	}
	
	
	//Add message to ride
	@POST
	@Path("/{rideID}/messages")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendMessage(@PathParam("rideID") long rid, Message message)
	{
		if(Objects.isNull(message.getAid()))
		{
			throw new BadRequestException("Invalid Aid", "/rides/" + rid + "/messages");
		}
		if(message.getMsg() == null)
		{
			throw new BadRequestException("Invalid message", "/rides" + rid + "/messages");
		}
		if(rideService.getRide(rid) == null)
		{
			throw new DataNotFoundException("404 (Not found) the ride identified by " + rid + " doesn't exist.");
		}
		message.setRid(rid);
		rideService.sendMessage(rid, message);
		long mid = message.getMid();
		midClass idc = new midClass(mid);
		return Response.ok(idc, MediaType.APPLICATION_JSON).status(201).build();
	}
	//View all ride messages
	@GET
	@Path("/{rideID}/messages")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages(@PathParam("rideID") long id)
	{
		JSONArray jarr = new JSONArray();
		if(rideService.getRide(id) == null)
		{
			throw new DataNotFoundException("404 (Not found) the ride identified by " + id + " doesn't exist.");
		}
		List<Message> lom = rideService.getAllMessages(id);
		if(lom != null)
		{
			jarr = new JSONArray(lom);
		}
		return Response.ok(jarr).build();
	}
	
	 
}
