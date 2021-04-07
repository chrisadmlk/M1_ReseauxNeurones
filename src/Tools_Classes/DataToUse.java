package Tools_Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataToUse {
	
	// classe pour regrouper toutes les infos utiles sur les datas utilis�s
	

	/*  cas de figure possibles : 
	 *	1000 = simple  -   2000 = monocouche   -   3000 = multicouche	
	 *	100 = classification   -   200 = r�gression
	 *	10 = descente du gradient   -   20 = ADALINE
	 *	1 = exemple du cours   -   2 = autre exemple
	 *
	 *	ex : 	1121 = perceptron simple 		utilis� pour une classification 	selon la technique ADALINE, 					avec les datas de l'exemple du cours
	 *			3112 = perecptron multicouche 	utilis� pour une classification 	selon la technique de la descente du gradient, 	avec des datas imagin�es
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
			
			// donn�es n�cessaires � l'initialisation du tableau du DataToUse
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
