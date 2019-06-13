package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.util.LinkedList;
import java.util.List;


public class JSONCommand implements DriverCommand{

    List<DriverCommand> driverCommands = new LinkedList<>();

    public JSONCommand(String json) {
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(json);
            JSONObject object = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) object.get("commands");

            for (Object e:
                 jsonArray) {
                int x = Integer.valueOf(((JSONObject) e).get("x").toString());
                int y = Integer.valueOf(((JSONObject) e).get("y").toString());

                if( ( (JSONObject) e).get("command").toString().equals("OperateTo")){
                    driverCommands.add(new OperateToCommand(x, y));
                }else{
                    driverCommands.add(new SetPositionCommand(x, y));
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Job2dDriver driver) {
        driverCommands.forEach((c) -> c.execute(driver));
    }
}
