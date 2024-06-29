package org.reqres.stepDefinition;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.json.JSONObject;

public class UserUpdate extends BaseClass{

    public Logger logger = Logger.getLogger(UserUpdate.class);

    String getUsersResource="/api/users/";
    String updateUserResource="/api/users/";

    @Given("Url is given {string}")
    public void url_is_given(String url) {
        givenRequest(url);
    }

    @When("System gets user ID {int}")
    public void system_gets_user(Integer userID) {
        getRequest(getUsersResource+userID);
    }

    @Then("System verifies user exist {int}")
    public void verify_the_response_code(Integer statusCode) {
        if(verifyResponseCode(response.getStatusCode(), statusCode))
            logger.info("User Exist");
    }
    @Then("The response will return ID {int} and email {string} and first name {string} and last name {string}")
    public void verify_the_user_details(Integer id, String email, String firstName, String lastName) {
        int exp_id = getResponseElementIntValue("data.id");
        String exp_email = getResponseElementValue("data.email");
        String exp_first_name = getResponseElementValue("data.first_name");
        String exp_last_name = getResponseElementValue("data.first_name");
        logger.info( exp_id+"\n"+exp_email+"\n"+exp_first_name+"\n"+exp_last_name+"\n");
        verifyResponseElement("ID", id, exp_id);
        verifyResponseElement("Email",email, exp_email);
        verifyResponseElement("FirstName",firstName, exp_first_name);
        verifyResponseElement("LastName", lastName, exp_last_name);
        logger.info("Get User Details Completed");
    }
    @Then("System updates user details name {string} and job {string} for user ID {int}")
    public void system_updates_user_details(String name , String job , Integer userID) {
        String resource = updateUserResource+userID;
        putRequest(UpdateUserData.updateUserDetails(name,job), resource);
    }
    @Then("The response will return name {string} and job {string} and updatedAt")
    public void verify_the_user_details(String name, String job) {
        String act_name = getResponseElementValue("name");
        String act_job = getResponseElementValue("job");
        String act_updatedAt = getResponseElementValue("updatedAt");

        logger.info( act_name+"\n"+act_job+"\n"+act_updatedAt+"\n");
        verifyResponseElement("Name", name, act_name);
        verifyResponseElement("Job",job, act_job);
        checkResponseElementValueExist( "UpdatedAt",act_updatedAt );

        logger.info("Update User Details Completed");
    }

}
