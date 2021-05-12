package Perceptrons;

import Tools_Classes.ChartMaker;
import Tools_Classes.DataToUse;

public class PerceptronMonocouche {


    public static DataToUse[] DivisionDesDataPargroupe(DataToUse myData) {
        int nbColX;
        int nbColD;

        if (myData.getCas() == 301) {
            nbColX = 2;
            nbColD = 3;
        } else {
            nbColX = 25;
            nbColD = 4;
        }

        int k = myData.GetLineNb();
        int nbColXPlus = nbColX + 1;
        DataToUse[] dataTable = new DataToUse[nbColD];
        
        System.out.println("!!!!! K : " +k);

        for (int i = 0; i < nbColD; i++) {
        	System.out.println("!!!!! cnt :" +i);
            // création d'un objet DataToUse aux bonnes dimensions
            int temp = nbColX + i;

            DataToUse myDataTemp = new DataToUse(myData.getCas(), nbColXPlus, k);  
            myDataTemp.CreateDataTable(k, nbColXPlus);

            // population de son tableau de données
            for (int j = 0; j < k; j++) {
            	for (int l = 0; l < nbColX; l++) {
                    myDataTemp.SetValueAt(j, l, myData.GetValueAt(j, l));
                }
                if (myData.GetValueAt(j, temp) == 1)   myDataTemp.SetValueAt(j, nbColX, 1);
                else myDataTemp.SetValueAt(j, nbColX, -1);
            }
            dataTable[i] = myDataTemp;
            
            System.out.println("\n\n\n");
        }
        return dataTable;
    }

    
    
    

    public static double[][] UseIt(DataToUse myData) {
    	
        DataToUse[] dataDividedByGroup = DivisionDesDataPargroupe(myData);
        int nbGroup = dataDividedByGroup.length;
        
        double[][] w_all = new double[nbGroup][];

        for (int i = 0; i < nbGroup; i++) {
            PerceptronSimple.Technique_ADALINE(dataDividedByGroup[i]);
            if( myData.getCas() == 301)  w_all[i] = dataDividedByGroup[i].getFinalw();
            else {
            	double[] repToReturn = {dataDividedByGroup[i].getNbIter(),dataDividedByGroup[i].getErrMoy()};
            	w_all[i] = repToReturn;
            }
        }
        
        // nécessaire pour passer test sur la taille
        double[] temp = {0,0,0};
        myData.SetLearningResult(0, 0, 0, temp, 0);
        return w_all;
    }
}



