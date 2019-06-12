package edu.kis.powp.jobs2d.command;

import edu.kis.powp.jobs2d.Job2dDriver;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.util.LinkedList;
import java.util.List;


public class JSONCommand implements CommandReader{

    private String name ="JSONCcmmand";
    private List<DriverCommand> driverCommands = new LinkedList<>();

    @Override
    public void read(String text) {
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(text);
            JSONObject object = (JSONObject) obj;
            if(object.get("name") != null)
                name = object.get("name").toString();
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
    public List<DriverCommand> getCommandsList() {
        return this.driverCommands;
    }

    @Override
    public String getName() {
        return name;
    }
}
