package edu.kis.powp.jobs2d.json;
import org.json.*;

import java.util.ArrayList;

public class InputCommandJsonParser {

    InputCommandJsonParser(String jsonStrong){


        JSONObject obj = new JSONObject(jsonStrong);
//        String x = obj.getJSONObject("coordinates").getString("x");

        JSONArray commandArr = obj.getJSONArray("command");



        for(int i = 0; i < commandArr.length(); i++)
        {
            int x = commandArr.getJSONObject(i).getInt("x");

            int y = commandArr.getJSONObject(i).getInt("y");
            String command = commandArr.getJSONObject(i).getString("command");
            System.out.println(x + y + command);
        }
    }



    public static void main(String[] args) {
        String temp = " {\n"
                      + "        \"command\":[{\n"
                      + "        \"x\": 10,\n"
                      + "        \"y\": 20,\n"
                      + "        \"operator\":\"OperateTo\"}]\n"
                      + "        }";

        InputCommandJsonParser par = new InputCommandJsonParser(temp);

    }
}

//{
//        "command":[{
//        "x": 10,
//        "y": 20,
//        "operator":"OperateTo"}]
//        }
