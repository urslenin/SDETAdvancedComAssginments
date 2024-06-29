package utils;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class CommonFunctions {

    Logger logger = Logger.getLogger(CommonFunctions.class);
    public RequestSpecification request;
    public Response response;
    String BASE_URL = ConfigFileReader.getInstance().getProperty("url");

    public void givenRequest(String givenURL){
        if(givenURL.contains("http"))
            RestAssured.baseURI = givenURL;
        else
            RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
    }
    public void getRequest(String resource){
        response = request.get(resource);
        String jsonString = response.prettyPrint();
    }
    public void putRequest(JSONObject requestParams , String resource){
        response = request.header("Content-Type", "application/json")
                .body(requestParams.toString())
                .put(resource);
        String jsonString = response.prettyPrint();
    }


    public String getResponseElementValue(String key) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        if (key != null) {
            return jsonPathEvaluator.get(key);
        }
        return null;
    }
    public int getResponseElementIntValue(String key) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        if (key != null) {
            return jsonPathEvaluator.get(key);
        }
        return 0;
    }

    public boolean verifyResponseElement(String elementName, Integer expected, Integer actual){
        if(expected.equals(actual)){
            //Assert.assertEquals(expected , actual);
            logger.info( elementName + "as expected");
            return true;
        }
        return false;
    }
    public boolean verifyResponseElement(String elementName, String expected, String actual){
        if(expected.equals(expected)){
            //Assert.assertEquals(expected , actual);
            logger.info( elementName + "as expected");
            return true;
        }
        return false;
    }
    public boolean checkResponseElementValueExist(String element, String value){
        if(!value.isEmpty()){
            //Assert.assertEquals(expected , actual);
            logger.info( element +"value: " + value + "as expected");
            return true;
        }
        return false;
    }
    public boolean verifyResponseCode(Integer expected, Integer actual){
        if(expected.equals(actual)){
            //Assert.assertEquals(expected , actual);
            logger.info( "Response Code as expected"+ expected);
            return true;
        }
        return false;
    }
   
}