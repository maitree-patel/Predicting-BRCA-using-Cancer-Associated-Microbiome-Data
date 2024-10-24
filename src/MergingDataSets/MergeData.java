package MergingDataSets;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class MergeData 
{

    // Function to read a CSV and store in a HashMap, returns a Map that contains both the header and data
    public static Map<String, Object> readCSV(String filePath) throws IOException 
    {
    	
        HashMap<String, List<String>> mergedMap = new HashMap<>();
        
        List<String> header = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        // Read the header line and store it in the header List
        String headerLine = br.readLine(); // Read the header line
        if (headerLine != null) 
        {
            header = Arrays.asList(headerLine.split(",")); // Store the header
        }

        // Read data lines
        String line;
        while ((line = br.readLine()) != null) 
        {
        	if (line.trim().isEmpty()) {
                // Skip empty lines
                continue;
            }
        	
        	String[] values = line.split(",");
        	
        	if (values.length > 0) {
        	    String key = values[0];
        	    mergedMap.put(key, new ArrayList<>(Arrays.asList(values)));
        	}
        }

        // Close the BufferedReader when done
        br.close();

        // Create a map to store both the header and the data
        Map<String, Object> result = new HashMap<>();
        result.put("header", header);
        result.put("data", mergedMap);

        return result;
    }

    public static void main(String[] args) throws Exception 
    {
    	
        // Paths to the two CSV files
        String countDataCsv = "/Users/maitreepatel/Desktop/AdvProgramming/Project/Tables/TableS8_T2T_KrakenUniq_BIO_Fullset.csv";
        String metaDataCsv = "/Users/maitreepatel/Desktop/AdvProgramming/Project/Tables/TableS9_metadata_KrakenUniq_BIO_Fullset.csv";

        // Read both CSV files, extracting both header and data from each
        Map<String, Object> countDataResults = readCSV(countDataCsv);
        Map<String, Object> metaDataResults = readCSV(metaDataCsv);

     // Extract header from both CSVs
        List<String> countDataHeader = (List<String>) countDataResults.get("header");
        List<String> metaDataHeader = (List<String>) metaDataResults.get("header");

        // Extract data from both CSV files
        HashMap<String, List<String>> countData = (HashMap<String, List<String>>) countDataResults.get("data");
        HashMap<String, List<String>> metaData = (HashMap<String, List<String>>) metaDataResults.get("data");
        
     // Create a merged header: header from CSV1 + header from CSV2 (excluding the first common column)
        List<String> mergedHeader = new ArrayList<>(countDataHeader); 
        mergedHeader.addAll(metaDataHeader.subList(1, metaDataHeader.size()));  // Exclude the first column from the second CSV's header

        // Create a merged HashMap
        HashMap<String, List<String>> mergedData = new HashMap<>();

        // Merging the data based on keys
        for (String key : countData.keySet()) 
        {
        	List<String> countDataValues = (List<String>) countData.get(key);
            List<String> metaDataValues = (List<String>) metaData.getOrDefault(key, new ArrayList<>());

            // Create a new List to store merged values
            List<String> mergedValues = new ArrayList<>(countDataValues);
            
            // Add values from csv2 (skipping the key in csv2)
            if (metaDataValues != null && metaDataValues.size() > 1) {
                mergedValues.addAll(metaDataValues.subList(1, metaDataValues.size()));
            }

                // Store the merged data
                mergedData.put(key, mergedValues);
        }

            // Write the merged data to a new CSV file, including the header
            writeCSV(mergedData, "/Users/maitreepatel/Desktop/AdvProgramming/Project/mergedDataFull.csv", mergedHeader);
    }

    // Function to write HashMap data to CSV, including the header
    public static void writeCSV(HashMap<String, List<String>> dataMap, String outputFile, List<String> header) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        // Write the header to the output file
        writer.write(String.join(",", header));
        writer.newLine();

        // Write the data rows
        for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
            List<String> values = entry.getValue();
            writer.write(String.join(",", values));
            writer.newLine();
        }

        // Close the BufferedWriter when done
        writer.close();
    }
}
