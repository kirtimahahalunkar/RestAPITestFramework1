package api.endpoints;

/*
 * swagger uri: https://petstore.swagger.io/v2
 * create user (Post):  https://petstore.swagger.io/v2/user
 * get user (Get):      https://petstore.swagger.io/v2/user/{username}
 * update user(Put) :   https://petstore.swagger.io/v2/user/{username}
 * delete user(Delete): https://petstore.swagger.io/v2/user/{username}
 * 
 */

public class Routes {
	
	public static String base_url="https://petstore.swagger.io/v2";
	
	public static String post_url=base_url+"/user";
	public static String get_url=base_url+"/user/{username}";
	public static String put_url=base_url+"/user/{username}";
	public static String delete_url=base_url+"/user/{username}";
	


}
