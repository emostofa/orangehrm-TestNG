package utils;

import config.EmpModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
private static final String file="./src/test/resources/employees.json";
//firstname, lastname, employeeid, username and password
public static void saveEmployeeInfo(EmpModel model) throws IOException, ParseException {
    JSONArray jsonArray = null ;
    File myFile = new File(file);

    if (myFile.exists()) {
        boolean isEmpty = myFile.length() == 0;

        if (!isEmpty) {

            FileReader fileReader = new FileReader(file);
            JSONParser parser = new JSONParser();
            jsonArray = (JSONArray) parser.parse(fileReader);
            fileReader.close();
        }
        else{
            jsonArray = new JSONArray();
        }
    }


    JSONObject employeeObj = new JSONObject();
    employeeObj.put("firstName",model.getFirstName());
    employeeObj.put("lastName",model.getLastName());
    employeeObj.put("employeeID",model.getEmployeeID());
    employeeObj.put("username",model.getUsername());
    employeeObj.put("password",model.getPassword());
    jsonArray.add(employeeObj);
    FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
//        fileWriter.close();



    }
}
