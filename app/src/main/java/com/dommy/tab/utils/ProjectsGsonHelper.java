package com.dommy.tab.utils;

import com.dommy.tab.module.Projects;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProjectsGsonHelper {
    public static List<Projects> getProjectsBean(String response){
        List<Projects> projectsBeanList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            String projectsArrayStr = jsonObject.getString("results");
            Type projectsListType = new TypeToken<List<Projects>>(){}.getType();
            Gson gson = new Gson();
            projectsBeanList = gson.fromJson(projectsArrayStr, projectsListType);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return projectsBeanList;
    }
}
