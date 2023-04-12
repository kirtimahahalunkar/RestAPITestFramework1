package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	Logger logger;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username()); 
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setPassword(faker.internet().password(5,10));	
		
		//logs
		logger = LogManager.getLogger(this.getClass());
	}
	
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("************creating user**************");
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	@Test(priority=2)
	public void testGetUser()
	{
		logger.info("************get user**************");
		Response response = UserEndpoints.readUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	@Test(priority=3)
	public void testPutUser()
	{
		logger.info("************updating user**************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		Response response = UserEndpoints.updateUser(userPayload.getUsername(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		UserEndpoints.readUser(userPayload.getUsername()).then().log().all();
		
		
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("************deleting user**************");
		Response response = UserEndpoints.deleteUser(userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("************user is deleted **************");
		
	}

}
