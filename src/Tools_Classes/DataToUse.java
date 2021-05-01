package Tools_Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataToUse {
	
	// Classe pour regrouper toutes les infos utiles sur les datas utilis�s

	// matrice de float de dimensions inconnues
	double[][] myData;
	// correspond au num des tables de data dans le cours
	int cas;
	int nbColumn, nbLine;
	int nbIter;
	int nbErr;
	double errMoy;
	double[] wFinal;
<<<<<<< HEAD

	public DataToUse() {}
	
	public DataToUse(int _cas, int _nbColumn, int _nbLine) {
		cas = _cas;
		nbColumn = _nbColumn;
		nbLine = _nbLine;
	}
=======
	

>>>>>>> parent of d542c39 (Perceptron monocouche OK)
	
	public void LoadDataFromCSV(String path) {
		String row;
		BufferedReader csvReader, csvReaderToCountLine;
		int i = 0;

		switch (path) {
			case "Datas\\table_2_1.csv":
				cas = 201;
				break;
			case "Datas\\table_2_3.csv":
				cas = 203;
				break;
			case "Datas\\table_2_9.csv":
				cas = 209;
				break;
			case "Datas\\table_2_10.csv":
				cas = 210;
				break;
			case "Datas\\table_2_11.csv":
				cas = 211;
				break;
			case "Datas\\table_3_1.csv":
				cas = 301;
				break;
			case "Datas\\table_3_5.csv":
				cas = 305;
				break;
			case "Datas\\table_4_12.csv":
				cas = 412;
				break;
			case "Datas\\table_4_14.csv":
				cas = 414;
				break;
			case "Datas\\table_4_17.csv":
				cas = 417;
				break;
		}
		//System.out.println(cas);
		
		try {
			csvReader = new BufferedReader(new FileReader(path));
			csvReaderToCountLine = new BufferedReader(new FileReader(path));
			
			// donn�es n�cessaires � l'initialisation du tableau du DataToUse
			nbColumn = (csvReaderToCountLine.readLine().split(",")).length;
			nbLine = (int) (csvReaderToCountLine.lines().count() + 1);
			myData = new double[nbLine][nbColumn];
			
			while ((row = csvReader.readLine()) != null) {
			    String[] data = row.split(",");
			    //System.out.println(data[0] + data[1] + data[2]);
			    
			    for (int j = 0; j < nbColumn; j++) {
			    	myData[i][j] = Float.parseFloat(data[j]);
			    }
			    i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
<<<<<<< HEAD
	public void CreateDataTable(int nbLine, int nbCol) {
		myData = new double[nbLine][nbCol];
	}

=======
	
>>>>>>> parent of d542c39 (Perceptron monocouche OK)
	public int GetColNb() {
		return nbColumn;
	}
	
	public int GetLineNb() {
		return nbLine;
	}

	public double GetValueAt(int i, int j) {
		return myData[i][j];
	}

<<<<<<< HEAD
	public void SetValueAt(int i, int j, double val) {
		myData[i][j] = val;
	}
=======
>>>>>>> parent of d542c39 (Perceptron monocouche OK)
	
	public void SetLearningResult(int _nbIter, int _nbErr, double _errMoy, double[] _wFinal) {
		nbIter = _nbIter;
		nbErr = _nbErr;
		errMoy = _errMoy;
		wFinal = _wFinal;
	}

	public int getCas() {
		return cas;
	}

	public int getNbErreur() {
		return nbErr;
	}

	public int getNbColumn() {
		return nbColumn;
	}

	public int getNbLine() {
		return nbLine;
	}

	public int getNbIter() {
		return nbIter;
	}

	public double getErrMoy() {
		return errMoy;
	}

	public double[] getwFinal() {
		return wFinal;
	}
}
