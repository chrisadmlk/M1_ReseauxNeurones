package Tools_Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataToUse {
	
	// classe pour regrouper toutes les infos utiles sur les datas utilisés
	

	/*  cas de figure possibles : 
	 *	1000 = simple  -   2000 = monocouche   -   3000 = multicouche	
	 *	100 = classification   -   200 = régression
	 *	10 = descente du gradient   -   20 = ADALINE
	 *	1 = exemple du cours   -   2 = autre exemple
	 *
	 *	ex : 	1121 = perceptron simple 		utilisé pour une classification 	selon la technique ADALINE, 					avec les datas de l'exemple du cours
	 *			3112 = perecptron multicouche 	utilisé pour une classification 	selon la technique de la descente du gradient, 	avec des datas imaginées
	 *
	 */

	
	float[][] myData;  // matrice de float de dimensions inconnues
	int cas;
	
	
	

	
	public void LoadDataFromCSV(String path) {
		String row;
		BufferedReader csvReader, csvReaderToCountLine;
		int i = 0;

		try {
			csvReader = new BufferedReader(new FileReader(path));
			csvReaderToCountLine = new BufferedReader(new FileReader(path));
			
			// données nécessaires à l'initialisation du tableau du DataToUse
			int nbColumn = (csvReaderToCountLine.readLine().split(",")).length;
			int nbLine = (int) (csvReaderToCountLine.lines().count() + 1);
			myData = new float[nbColumn][nbLine];
			
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");  System.out.println(data[0] + data[1] + data[2]);
			    
			    for (int j = 0; j < nbColumn; j++) {
			    	myData[i][j] = Float.parseFloat(data[j]);
			    }
			    i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
}
