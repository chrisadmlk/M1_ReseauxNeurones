package Perceptrons;

import Tools_Classes.DataToUse;

public class Pereceptron_monocouche {
	

	public static DataToUse[] DivisionDesDataPargroupe(DataToUse myData) {
		int nbColX = 0;
		int nbColD = 0;
		
		if (myData.getCas() == 301) {
			nbColX = 2;
			nbColD = 3;
		}
		else {
			nbColX = 25;
			nbColD = 4;
		}
		
		

		int k = myData.GetLineNb();
		int nbColXPlus= nbColX+1;
		DataToUse[] dataTable = new  DataToUse[nbColD];
			
		
		for (int i =0; i< nbColD; i++) {
			
			// création d'un objet DataToUse aux bonnes dimensions
			int dataCnt=0;
			int temp = nbColX+i;
			int cntLine=0;
	
			for (int j=0; j<k; j++) {
				if (myData.GetValueAt(j, temp) == 1)    dataCnt++;      	// on part du principe que les sorties d sont de types boolean (vrai=1)
			}
			
			DataToUse myDataTemp = new DataToUse(myData.getCas(), nbColXPlus, dataCnt);
			myDataTemp.CreateDataTable(dataCnt, nbColXPlus);

			
			
			// population de son tableau de données
			for (int j=0; j<k; j++) {
				if (myData.GetValueAt(j, temp) == 1) {
					for (int l=0; l<nbColX; l++) {
						myDataTemp.SetValueAt(cntLine, l, myData.GetValueAt(j, l));    
					}
					myDataTemp.SetValueAt(cntLine, nbColX, myData.GetValueAt(j, temp));
					cntLine++;
				}
			}
			

			dataTable[i] = myDataTemp;
		}
		return dataTable;
	}

	
	
	
	
	public static void UsePerceptrons(DataToUse[] dataDividedByGroup) {
		
		int nbGroup = dataDividedByGroup.length;
		
		for (int i = 0; i<nbGroup; i++) {
			Pereceptron_simple.Technique_ADALINE(dataDividedByGroup[i]);
		}

		
		
	}

	
	public static void UseIt(DataToUse myData) {
		DataToUse[] data = 	DivisionDesDataPargroupe(myData);
		UsePerceptrons(data);
	}
	
}
