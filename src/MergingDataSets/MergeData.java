package MergingDataSets;

import java.io.*;
import java.util.*;

public class MergeData 
{
	//function to read csv file and store it in hasmap
	public static HashMap<String, List<String>> readCSV(String filePath) throws IOException {
        HashMap<String, List<String>> dataMap = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        // Read the header line (if any)
        String[] header = br.readLine().split(",");

        // Read data lines
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            String key = values[0];  // Assuming the first column is the unique key
            dataMap.put(key, new ArrayList<>(Arrays.asList(values)));
        }

        // Close the BufferedReader when done
        br.close();

        return dataMap;
    }
	
	//storing our data using function
	public static void main(String[] args) throws IOException 
	{
        // Paths to the two CSV files
        String counts_csv = "/Users/maitreepatel/Desktop/AdvProgramming/Project/Tables/TableS8_T2T_KrakenUniq_BIO_Fullset.csv";
        String metadata_csv = "/Users/maitreepatel/Desktop/AdvProgramming/Project/Tables/TableS9_metadata_KrakenUniq_BIO_Fullset.csv";
        
        // Read both CSV files into HashMaps (using ArrayList instead of arrays)
        HashMap<String, List<String>> countsData = readCSV(counts_csv);
        HashMap<String, List<String>> metaData = readCSV(metadata_csv);
        
        // Create a merged HashMap
        HashMap<String, List<String>> mergedData = new HashMap<>();
        
        for (String key : countsData.keySet()) {
            List<String> valuesFromCsv1 = countsData.get(key);
            List<String> valuesFromCsv2 = metaData.get(key);

            // Create a new List to store merged values
            List<String> mergedValues = new ArrayList<>(valuesFromCsv1);

            // Add values from csv2 (skipping the key in csv2)
            if (valuesFromCsv2.size() > 1) {
                mergedValues.addAll(valuesFromCsv2.subList(1, valuesFromCsv2.size()));
            }

            // Store the merged data
            mergedData.put(key, mergedValues);
        }
        
        // Write the merged data to a new CSV file
        writeCSV(mergedData, "/Users/maitreepatel/Desktop/AdvProgramming/Project/mergedFullData.csv");
	}
	
	public static void writeCSV(HashMap<String, List<String>> dataMap, String outputFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
            List<String> values = entry.getValue();
            writer.write(String.join(",", values));
            writer.newLine();
        }
        writer.close();
    }
}
