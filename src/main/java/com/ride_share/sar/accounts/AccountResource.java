package com.ride_share.sar.accounts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.ws.rs.PathParam;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONArray;
import org.json.JSONObject;

import com.ride_share.sar.database.DatabaseClass;
import com.ride_share.sar.exception.BadRequestException;
import com.ride_share.sar.exception.DataNotFoundException;

@Path("/accounts")
public class AccountResource {
	
	AccountService accountService= new AccountService();
	//ObjectMapper mapper = new ObjectMapper();
	 
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(Account account)
	{
		if(account.getFirst_name() == null)
		{
			throw new BadRequestException("Invalid first name number", "/accounts");
		}
		if(account.getLast_name() == null)
		{
			throw new BadRequestException("Invalid last name number", "/accounts");
		}
		if(account.getPhone() == null || (account.getPhone().replaceAll("\\-", "").matches("[0-9]+") == false))
		{
			throw new BadRequestException("Invalid phone number", "/accounts");
		}
		if(account.getPicture() == null)
		{
			throw new BadRequestException("Invalid picture number", "/accounts");
		}
		if(account.getIs_active() == null)
		{
			throw new BadRequestException("Invalid value for is_active", "/accounts" );
		}
		
		accountService.addAccount(account);
		long aid = account.getAid();
		idClass idc = new idClass(aid);
		return Response.ok(idc, MediaType.APPLICATION_JSON).status(201).build();
	}
	
	//Activate account ++
	@PUT
	@Path("/{accountID}/status")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateAccount(@PathParam("accountID") long id, Account account)
	{
		if(account.getFirst_name() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid first name number", ("/accounts/" + id + "status"));
		}
		if(account.getLast_name() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid last name number", ("/accounts/" + id + "status"));
		}
		if(account.getPhone() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid phone number", ("/accounts/" + id + "status"));
		}
		if(account.getPicture() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid picture number", ("/accounts/" + id + "status"));
		}
		if(account.getIs_active() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid value for is_active", ("/accounts/" + id + "status"));
		}
		
		if(accountService.getAccount(id) == null)
		{
			throw new DataNotFoundException("404 (Not found) the account identified by " + id + " doesn't exist.");
		}
		
		account.setAid(id);
		accountService.activateAccount(account);
		return Response.status(204).build();
	}
	
	//Update account ++
	@PUT
	@Path("/{accountID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccount(@PathParam("accountID") long id, Account account)
	{
		if(account.getFirst_name() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid first name number", ("/accounts/" + id));
		}
		if(account.getLast_name() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid last name number", ("/accounts/" + id));
		}
		if(account.getPhone() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid phone number", ("/accounts/" + id));
		}
		if(account.getPicture() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid picture number", ("/accounts/" + id));
		}
		if(account.getIs_active() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid value for is_active", ("/accounts/" + id));
		}
		if(accountService.getAccount(id) == null)
		{
			throw new DataNotFoundException("404 (Not found) the account identified by " + id + " doesn't exist.");
		}
		account.setAid(id);
		accountService.activateAccount(account);
		return Response.status(204).build();
	}
	
	//Delete account ++
	@DELETE
	@Path("/{accountID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(@PathParam("accountID") long id)
	{
		if(accountService.getAccount(id) == null)
		{
			throw new DataNotFoundException("404 (Not found) the account identified by " + id + " doesn't exist.");
		}
		accountService.removeAccount(id);
		return Response.status(204).build();
	}
	//View all accounts and Search accounts ++ 
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts(@QueryParam("key") String key)
	{
		List<Account> loa = accountService.getAllAccounts();
		JSONArray jarr = new JSONArray();
		//return accountService.getAllAccounts();
		if(key != null)
		{
			loa = accountService.searchAccountsForKey(key);
		}
		if(loa != null)
		{
			jarr = new JSONArray(loa);
		}
		
		
		return Response.ok(jarr).build();
		
	}
	
	//Rate account ++
	@POST
	@Path("/{accountID}/ratings") //account id of the account to be rated
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response rateAccount(@PathParam("accountID") long id, Rating rating)
	{
		if(Objects.isNull(rating.getRid()))
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid first name number", ("/accounts/" + id + "ratings"));
		}
		if(Objects.isNull(rating.getSent_by_id()))
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid last name number", ("/accounts/" + id + "ratings"));
		}
		if(Objects.isNull(rating.getRating()))
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid phone number", ("/accounts/" + id + "ratings"));
		}
		if(rating.getComment() == null)
		{
			//throw new BadRequestException("Invalid phone number", "/accounts");
			throw new BadRequestException("Invalid picture number", ("/accounts/" + id + "ratings"));
		}
		if(accountService.getAccount(id) == null)
		{
			throw new BadRequestException("No account with id " + id, ("/accounts/" + id) + "ratings");
		}
		if(rating.getRating() < 1 || rating.getRating() > 5)
		{
			throw new BadRequestException("rating is invalid: must be between 1 and 5 " + id, ("/accounts/" + id + "ratings"));
		}
		
		
		accountService.rateAccount(id, rating);
		sidClass idc = new sidClass(id);
		//return Response.ok(idc, MediaType.APPLICATION_JSON).build();
		return Response.ok(idc, MediaType.APPLICATION_JSON).status(201).build();
	}
	
	//View driver ratings ++
	@GET
	@Path("/{accountID}/driver")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountRatings ViewDriverRat(@PathParam("accountID") long id)
	{
		return accountService.ViewAccountRatings(id);
	}
	//View ride ratings ++
	@GET
	@Path("/{accountID}/rider")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountRatings ViewRiderRat(@PathParam("accountID") long id)
	{
		return accountService.ViewAccountRatings(id);
	}
	
	

	
	

}
