package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.example.ParseInput.Connections;
import com.example.ParseInput.Customers;
import com.example.ParseInput.Demands;
import com.example.ParseInput.Refineries;
import com.example.ParseInput.Tanks;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.cdimascio.dotenv.Dotenv;

public class ApiClient {
    
    private static final Dotenv dotenv = Dotenv.load();

    private static final String API_KEY = dotenv.get("API_KEY");
    private static final String POST_START = dotenv.get("POST_START");
    private static final String POST_PLAY = dotenv.get("POST_PLAY");
    private static final String POST_END = dotenv.get("POST_END");
    private static String sessionId;

    public static void main(String[] args) throws Exception {
        System.out.println("Starting API client...");
        
        CSVParser parser = new CSVParser();
        String filePathRefineries = "app/src/main/java/com/example/CSV/refineries.csv"; 
        String filePathConnections = "app/src/main/java/com/example/CSV/connections.csv";
        String filePathCustomers = "app/src/main/java/com/example/CSV/customers.csv";
        String filePathDemands = "app/src/main/java/com/example/CSV/demands.csv";
        String filePathTanks = "app/src/main/java/com/example/CSV/tanks.csv";
        
        List<Refineries> refineriesList = parser.parseCSVRefineries(filePathRefineries);
        List<Connections> connectionsList = parser.parseCSVConnections(filePathConnections);
        List<Customers> customersList = parser.parseCSVCustomers(filePathCustomers);
        List<Demands> demandsList = parser.parseCSVDemands(filePathDemands);
        List<Tanks> tanksList = parser.parseCSVTanks(filePathTanks);

        System.err.println("API_KEY: " + API_KEY);
        System.out.println("POST_START: " + POST_START);
        System.out.println("POST_PLAY: " + POST_PLAY);
        System.out.println("POST_END: " + POST_END);

        System.out.println("Starting session...");

        sessionId = startSession();
        System.out.println("Session started: " + sessionId);

        // Play in a loop for 42 times
        for (int i = 0; i < 42; i++) {

            System.out.println("Playing session for day " + i);
            playSession(i, sessionId);
        }

        // End the session
        endSession();
    }

    private static String startSession() throws Exception {
        String response = sendPostRequest(POST_START, null, null);
        // Extract session ID from response
        return response.trim();
    }

    private static void playSession(int day, String sessionId) throws Exception {
        // Create the directory if it doesn't exist
        String folderPath = "app/responses";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        
        // Create your JSON payload for the play call
        String jsonInputString;
        if (day == 0){
            jsonInputString = "{"
                    + "\"day\":" + day + ","
                    + "\"movements\":["
                    + "{"
                    + "\"connectionId\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\","
                    + "\"amount\":0"
                    + "}"
                    + "]"
                    + "}";
        }
        else{
            jsonInputString = "{"
                    + "\"day\":" + day + ","
                    + "\"movements\":["
                    + "]"
                    + "}";
            //TODO - Implement the logic to create the JSON payload for subsequent days
        }
        
        // Send the POST request
        String response = sendPostRequest(POST_PLAY, jsonInputString, sessionId); // Pass sessionId as a parameter
        // System.out.println("Response from play call for day " + day + ": " + response);
        
        // Save the response to a JSON file in the folder
        saveResponseToFile(day, response, folderPath);
    }
    
    private static void saveResponseToFile(int day, String response, String folderPath) {
        try {
            String filePath = folderPath + "/response_day_" + day + ".json";
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(response);
            fileWriter.close();
            System.out.println("Response saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the response to a file.");
            e.printStackTrace();
        }
    }

    private static void endSession() throws Exception {
        String response = sendPostRequest(POST_END, null, null);
        System.out.println("Session ended: " + response);
    }

    private static String sendPostRequest(String apiUrl, String jsonInputString, String sessionId) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("API-KEY", API_KEY);
        if (sessionId != null)
            conn.setRequestProperty("SESSION-ID", sessionId); // Set the session ID in the request header
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        
        if (jsonInputString != null) {
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }
    
        int responseCode = conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
    
        System.out.println("POST Response Code: " + responseCode);
        return response.toString();
    }
    

    private static String extractSessionId(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        return jsonObject.get("session_id").getAsString(); // Adjust according to the actual response structure
    }
}