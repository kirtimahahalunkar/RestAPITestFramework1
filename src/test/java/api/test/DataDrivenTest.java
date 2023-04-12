package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import api.utilities.DataProviderClass;
import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority=1 , dataProvider="Data" , dataProviderClass= DataProviderClass.class)
	public void testPostUserDD(String Id,String UserName,String Fname , String Lname, String Email, String PhoneNumber, String Password)
	{
		Faker faker = new Faker();
		User userPayload=new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(UserName); 
		userPayload.setFirstName(Fname);
		userPayload.setLastName(Lname);
		userPayload.setEmail(Email);
		userPayload.setPhone(PhoneNumber);
		userPayload.setPassword(Password);
		
		Response response = UserEndpoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		UserEndpoints.readUser(UserName).then().log().all();
		
	}
	
	
	@Test(priority=1 , dataProvider="UserNames" , dataProviderClass= DataProviderClass.class)
	public void testDeleteUserDD(String UserName)
	{
		Response response = UserEndpoints.deleteUser(UserName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
