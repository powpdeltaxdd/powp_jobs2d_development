package edu.kis.powp.jobs2d.json;
import org.json.*;

public class InputCommandJsonParser {

    InputCommandJsonParser(){
        JSONObject obj = new JSONObject(" .... ");
        String pageName = obj.getJSONObject("pageInfo").getString("pageName");

        JSONArray arr = obj.getJSONArray("posts");
        for(int i = 0; i < arr.length(); i++)
        {
            String post_id = arr.getJSONObject(i).getString("post_id");
        }
    }

}
