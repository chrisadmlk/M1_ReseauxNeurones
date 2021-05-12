
package Tools_Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataToUse {
	
	// classe pour regrouper toutes les infos utiles sur les datas utilis�s
	

	
	double[][] myData;  // matrice de float de dimensions inconnues
	int cas;  // correspond au num des tables de data dans le cours
	int nbColumn, nbLine;
	int nbIter;
	int nbErr;
	double errMoy;
	double[] wFinal;
	double facteurCorr;
	
	
	public DataToUse() {

	}
	
	public DataToUse(int _cas, int _nbColumn, int _nbLine) {
		cas = _cas;
		nbColumn = _nbColumn;
		nbLine = _nbLine;
	}
	
	public void LoadDataFromCSV(String path) {
		String row;
		BufferedReader csvReader, csvReaderToCountLine;
		int i = 0;

		
		if (path.equals("Datas\\table_2_1.csv")) cas = 201;
		else if (path.equals("Datas\\table_2_3.csv")) cas = 203;
		else if (path.equals("Datas\\table_2_9.csv")) cas = 209;
		else if (path.equals("Datas\\table_2_10.csv")) cas = 210;
		else if (path.equals("Datas\\table_2_11.csv")) cas = 211;
		else if (path.equals("Datas\\table_3_1.csv")) cas = 301;
		else if (path.equals("Datas\\table_3_5.csv")) cas = 305;
		else if (path.equals("Datas\\table_4_3.csv")) cas = 403;
		else if (path.equals("Datas\\table_4_12.csv")) cas = 412;
		else if (path.equals("Datas\\table_4_14.csv")) cas = 414;
		else if (path.equals("Datas\\table_4_17.csv")) cas = 417;
		
		else if (path.equals("Datas\\exemple_class_1.csv")) cas = 209;
		else if (path.equals("Datas\\pommes_class_2.csv")) cas = 414;
		else if (path.equals("Datas\\boulons_class_2.csv")) cas = 412;
		else if (path.equals("Datas\\boulons_class.csv")) cas = 412;
		else if (path.equals("Datas\\trees_reg.csv")) cas = 211;


		
		try {
			csvReader = new BufferedReader(new FileReader(path));
			csvReaderToCountLine = new BufferedReader(new FileReader(path));
			
			// donn�es n�cessaires � l'initialisation du tableau du DataToUse
			nbColumn = (csvReaderToCountLine.readLine().split(";")).length;
			nbLine = (int) (csvReaderToCountLine.lines().count() + 1);
			myData = new double[nbLine][nbColumn];
			
			while ((row = csvReader.readLine()) != null) {
			   
				String[] data = row.split(";");  //System.out.println(data[0] + data[1] + data[2]);
			    for (int j = 0; j < nbColumn; j++) {
			    	myData[i][j] = Float.parseFloat(data[j]);
			    }
			    i++;
 
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void CreateDataTable(int nbLine, int nbCol) {
		myData = new double[nbLine][nbCol];
	}
	
	public void SetCas(int _cas) {
		cas = _cas;
	}
	
	

	public int GetColNb() {
		return nbColumn;
	}
	
	public int GetLineNb() {
		return nbLine;
	}

	public double GetValueAt(int i, int j) {
		return myData[i][j];
	}

	public void SetValueAt(int i, int j, double val) {
		myData[i][j] = val;
	}

	
	public void SetLearningResult(int _nbIter, int _nbErr, double _errMoy, double[] _wFinal, double fact) {
		nbIter = _nbIter;
		nbErr = _nbErr;
		errMoy = _errMoy;
		wFinal = _wFinal;
		facteurCorr = fact;
	}

	
	public double getFacteurCorr() {
		return facteurCorr;
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
	
	
	
	public double[][] getRawData() {
		return myData;
	}
	
	
	public double[] getFinalw() {
		return wFinal;
	}
	
	
	public double getErrMoy() {
		return errMoy;
	}
	
	
	public int getNbIter() {
		return nbIter;
	}


}