package com.example;

import com.example.ParseInput.Refineries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public List<Refineries> parseCSVRefineries(String filePath) {
        List<Refineries> refineriesList = new ArrayList<>();
        String line;
        String separator = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(separator);
                Refineries refinery = new Refineries(
                    values[0],
                    values[1],
                    Double.parseDouble(values[2]),
                    Double.parseDouble(values[3]),
                    Double.parseDouble(values[4]),
                    Double.parseDouble(values[5]),
                    Double.parseDouble(values[6]),
                    Double.parseDouble(values[7]),
                    Double.parseDouble(values[8]),
                    Double.parseDouble(values[9]),
                    Double.parseDouble(values[10]),
                    values[11]
                );
                refineriesList.add(refinery);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return refineriesList;
    }
}

