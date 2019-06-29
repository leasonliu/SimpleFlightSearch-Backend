package com.ua.SimpleFlightSearch;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    private String flights = "[{\"flightNumber\":\"2005\",\"carrier\":\"UA\",\"origin\":\"IAH\",\"departure\":\"2018-01-31T05:10:00\",\"destination\":\"ORD\",\"arrival\":\"2018-01-31T07:57:00\",\"aircraft\":\"Airbus A320\",\"distance\":925,\"travelTime\":\"02:47\",\"status\":\"Arrived at Gate\"},{\"flightNumber\":\"0638\",\"carrier\":\"UA\",\"origin\":\"IAH\",\"departure\":\"2018-01-31T07:45:00\",\"destination\":\"ORD\",\"arrival\":\"2018-01-31T10:18:00\",\"aircraft\":\"Boeing 737-900\",\"distance\":925,\"travelTime\":\"02:33\",\"status\":\"Arrived at Gate\"},{\"flightNumber\":\"1160\",\"carrier\":\"UA\",\"origin\":\"IAH\",\"departure\":\"2018-01-31T12:05:00\",\"destination\":\"ORD\",\"arrival\":\"2018-01-31T14:38:00\",\"aircraft\":\"Boeing 737-800\",\"distance\":925,\"travelTime\":\"02:41\",\"status\":\"Arrived at Gate\"},{\"flightNumber\":\"1256\",\"carrier\":\"UA\",\"origin\":\"IAH\",\"departure\":\"2018-01-31T14:22:00\",\"destination\":\"ORD\",\"arrival\":\"2018-01-31T17:01:00\",\"aircraft\":\"Boeing 737-800\",\"distance\":925,\"travelTime\":\"02:41\",\"status\":\"On Time\"},{\"flightNumber\":\"2153\",\"carrier\":\"UA\",\"origin\":\"IAH\",\"departure\":\"2018-01-31T16:24:00\",\"destination\":\"ORD\",\"arrival\":\"2018-01-31T19:27:00\",\"aircraft\":\"Boeing 737-800\",\"distance\":925,\"travelTime\":\"02:47\",\"status\":\"On Time\"},{\"flightNumber\":\"2155\",\"carrier\":\"UA\",\"origin\":\"IAH\",\"departure\":\"2018-01-31T18:10:00\",\"destination\":\"ORD\",\"arrival\":\"2018-01-31T20:49:00\",\"aircraft\":\"Boeing 737-900\",\"distance\":925,\"travelTime\":\"02:39\",\"status\":\"On Time\"},{\"flightNumber\":\"2131\",\"carrier\":\"UA\",\"origin\":\"IAH\",\"departure\":\"2018-01-31T19:49:00\",\"destination\":\"ORD\",\"arrival\":\"2018-01-31T22:00:00\",\"aircraft\":\"Boeing 737-900\",\"distance\":925,\"travelTime\":\"02:31\",\"status\":\"On Time\"},{\"flightNumber\":\"2043\",\"carrier\":\"UA\",\"origin\":\"ORD\",\"departure\":\"2018-01-31T06:08:00\",\"destination\":\"IAH\",\"arrival\":\"2018-01-31T08:42:00\",\"aircraft\":\"Boeing 737-800\",\"distance\":925,\"travelTime\":\"02:34\",\"status\":\"Arrived at Gate\"},{\"flightNumber\":\"0748\",\"carrier\":\"UA\",\"origin\":\"ORD\",\"departure\":\"2018-01-31T08:10:00\",\"destination\":\"IAH\",\"arrival\":\"2018-01-31T10:44:00\",\"aircraft\":\"Boeing 737-900\",\"distance\":925,\"travelTime\":\"02:34\",\"status\":\"Arrived at Gate\"},{\"flightNumber\":\"2166\",\"carrier\":\"UA\",\"origin\":\"ORD\",\"departure\":\"2018-01-31T10:15:00\",\"destination\":\"IAH\",\"arrival\":\"2018-01-31T12:53:00\",\"aircraft\":\"Airbus A320\",\"distance\":925,\"travelTime\":\"02:34\",\"status\":\"Arrived at Gate\"},{\"flightNumber\":\"2171\",\"carrier\":\"UA\",\"origin\":\"ORD\",\"departure\":\"2018-01-31T12:45:00\",\"destination\":\"IAH\",\"arrival\":\"2018-01-31T15:34:00\",\"aircraft\":\"Airbus A320\",\"distance\":925,\"travelTime\":\"02:39\",\"status\":\"On Time\"},{\"flightNumber\":\"1128\",\"carrier\":\"UA\",\"origin\":\"ORD\",\"departure\":\"2018-01-31T14:05:00\",\"destination\":\"IAH\",\"arrival\":\"2018-01-31T16:52:00\",\"aircraft\":\"Boeing 737-800\",\"distance\":925,\"travelTime\":\"02:41\",\"status\":\"On Time\"}]";

    private JsonArray jsonArray;

    public SearchController() {
        jsonArray = new JsonParser().parse(flights).getAsJsonArray();
    }

    @RequestMapping(value = "/")
    @CrossOrigin
    public String search(@RequestBody String data) {
        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
        boolean usingFlightNumber;
        if (jsonObject.get("flightNumber") != null) {
            usingFlightNumber = true;
        } else {
            usingFlightNumber = false; // origin & destination
        }
        JsonArray resJsonArray = new JsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObj = jsonArray.get(i).getAsJsonObject();
            if (usingFlightNumber) {
                if (jsonObj.get("flightNumber").equals(jsonObject.get("flightNumber")) && jsonObj.get("departure").toString().substring(0, 11).equals(jsonObject.get("date").toString().substring(0, 11))) {
                    resJsonArray.add(jsonArray.get(i));
                }
            } else {
                if (jsonObj.get("origin").equals(jsonObject.get("from")) && jsonObj.get("destination").equals(jsonObject.get("to")) && jsonObj.get("departure").toString().substring(0, 11).equals(jsonObject.get("date").toString().substring(0, 11))) {
                    resJsonArray.add(jsonArray.get(i));
                }
            }
        }
        return resJsonArray.toString();
    }
}
