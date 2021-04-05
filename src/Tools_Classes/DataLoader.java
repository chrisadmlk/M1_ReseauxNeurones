package Tools_Classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public  final class DataLoader {

	
	
	
	
	
	// Classe static qui sert à charger les infos contenu dans un fichier csv
	
	static DataToUse LoadFromCSV(String path) {
		
		DataToUse myData = new DataToUse();
		String row;
		BufferedReader csvReader;
		

		try {
			csvReader = new BufferedReader(new FileReader(path));
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
			    // TODO : do something with the data
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return myData;
	}
	
	
	
}
