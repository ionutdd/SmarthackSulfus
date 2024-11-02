package com.example;

import com.example.ParseInput.Refineries;
import com.example.ParseInput.Connections;
import com.example.ParseInput.Customers;
import com.example.ParseInput.Demands;
import com.example.ParseInput.Tanks;

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

    public List<Connections> parseCSVConnections(String filePath) {
        List<Connections> connectionsList = new ArrayList<>();
        String line;
        String separator = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(separator);

                Connections connection = new Connections(
                    values[0],
                    values[1],
                    values[2],
                    Double.parseDouble(values[3]),
                    Double.parseDouble(values[4]),
                    values[5],
                    Double.parseDouble(values[6])
                );

                connectionsList.add(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return connectionsList;
    }

    public List<Demands> parseCSVDemands(String filePath) {
        List<Demands> demandsList = new ArrayList<>();
        String line;
        String separator = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(separator);

                Demands demand = new Demands(
                    values[0],
                    values[1],
                    Double.parseDouble(values[2]),
                    Integer.parseInt(values[3]),
                    Integer.parseInt(values[4]),
                    Integer.parseInt(values[5])
                );

                demandsList.add(demand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return demandsList;
    }

    public List<Customers> parseCSVCustomers(String filePath) {
        List<Customers> customersList = new ArrayList<>();
        String line;
        String separator = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(separator);

                Customers customer = new Customers(
                    values[0],
                    values[1],
                    Double.parseDouble(values[2]),
                    Double.parseDouble(values[3]),
                    Double.parseDouble(values[4]),
                    Double.parseDouble(values[5]),
                    values[6]
                );

                customersList.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return customersList;
    }

    public List<Tanks> parseCSVTanks(String filePath) {
        List<Tanks> tanksList = new ArrayList<>();
        String line;
        String separator = ";";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(separator);

                Tanks tanks = new Tanks(
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
                    values[10]
                );

                tanksList.add(tanks);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return tanksList;
    } 
}

