package org.reqres.stepDefinition;

import org.json.JSONObject;

public class UpdateUserData {

    public String name;
    public String job;

    public static JSONObject updateUserDetails(String name, String job) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", name);
        requestParams.put("job", job);
        return requestParams;
    }
}
