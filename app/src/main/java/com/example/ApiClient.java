package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
        // Start the session

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

            String jsonInputString = "";

            if (i == 0) {
                jsonInputString = "{"
                        + "\"day\":" + i + ","
                        + "\"movements\":["
                        + "{"
                        + "\"connectionId\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"," // replace with actual connectionId as needed
                        + "\"amount\":0" // modify this amount as necessary
                        + "}"
                        + "]"
                        + "}";
            }
            else {
                // jsonInputString = outputJson();
                jsonInputString = "{"
                        + "\"day\":" + i + ","
                        + "\"movements\":["
                        + "{"
                        + "\"connectionId\":\"3fa85f64-5717-4562-b3fc-2c963f66afa6\"," // replace with actual connectionId as needed
                        + "\"amount\":0" // modify this amount as necessary
                        + "}"
                        + "]"
                        + "}";
            }

            String response = playSession(i, sessionId, jsonInputString);
            
        }

        // End the session
        endSession();
    }

    private static String startSession() throws Exception {
        String response = sendPostRequest(POST_START, null, null);
        // Extract session ID from response
        return response.trim();
    }


    private static String playSession(int day, String sessionId, String jsonInputString) throws Exception {
        // Create your JSON payload for the play call
    
        // Send the POST request
        String response = sendPostRequest(POST_PLAY, jsonInputString, sessionId); // Pass sessionId as a parameter
        System.out.println("Response from play call for day " + day + ": " + response);
        return response;
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