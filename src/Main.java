import GUI.Main_Frame;
import Perceptrons.PerceptronMonocouche;
import Perceptrons.PerceptronSimple;
import Perceptrons.PerceptronMulticouche;
import Tools_Classes.DataToUse;

public class Main
{
    public static void main(String[] args) {

//    	// test et mise au point avec l'operateur logique ET
//    	DataToUse myData = new DataToUse();
//    	myData.LoadDataFromCSV("Datas\\table_2_1.csv");	
//    	PerceptronSimple.MiseAuPointPorteLogiqueET_2_1(myData);
//    	
//    	// test et mise au point avec l'operateur logique ET + erreur quadratique   -  descente de gradient
//    	DataToUse myData2 = new DataToUse();
//    	myData2.LoadDataFromCSV("Datas\\table_2_3.csv");	
//    	PerceptronSimple.Technique_descente_gradient(myData2);
//    	
//    	// test et mise au point avec l'operateur logique ET + erreur quadratique   -   ADALINE
//    	DataToUse myData3 = new DataToUse();
//    	myData3.LoadDataFromCSV("Datas\\table_2_3.csv");	
//    	PerceptronSimple.Technique_ADALINE(myData3);
//    	
//    	// test 2.9 descente de gradient
//    	DataToUse myData4 = new DataToUse();
//    	myData4.LoadDataFromCSV("Datas\\table_2_9.csv");	
//    	PerceptronSimple.Technique_descente_gradient(myData4);
//    	
//    	// test 2.9 ADALINE
//    	DataToUse myData5 = new DataToUse();
//    	myData5.LoadDataFromCSV("Datas\\table_2_9.csv");	
//    	PerceptronSimple.Technique_ADALINE(myData5);
//    	
//    	// test 2.10 descente de gradient
//    	DataToUse myData6 = new DataToUse();
//    	myData6.LoadDataFromCSV("Datas\\table_2_10.csv");	
//    	PerceptronSimple.Technique_descente_gradient(myData6);
//    	
//    	// test 2.10 ADALINE
//    	DataToUse myData7 = new DataToUse();
//    	myData7.LoadDataFromCSV("Datas\\table_2_10.csv");	
//    	PerceptronSimple.Technique_ADALINE(myData7);
//
//    	// test 2.11 descente de gradient
//    	DataToUse myData8 = new DataToUse();
//    	myData8.LoadDataFromCSV("Datas\\table_2_11.csv");	
//    	PerceptronSimple.Technique_descente_gradient(myData8);
//    	
//    	// test 2.11 ADALINE
//    	DataToUse myData9 = new DataToUse();
//    	myData9.LoadDataFromCSV("Datas\\table_2_11.csv");	
//    	PerceptronSimple.Technique_ADALINE(myData9);
//    	
//    	
//    	// test 3.1 ADALINE (monocouche)
//    	DataToUse myData10 = new DataToUse();
//    	myData10.LoadDataFromCSV("Datas\\table_3_1.csv");	
//    	PerceptronMonocouche.UseIt(myData10);
    	
    	// test 3.5 ADALINE (monocouche)
    	DataToUse myData11 = new DataToUse();
    	myData11.LoadDataFromCSV("Datas\\table_3_5.csv");	
    	PerceptronMonocouche.UseIt(myData11);
    	
    	
//    	// test de la JFrame
//    	Main_Frame frame = new Main_Frame(myData5);
//    	frame.setVisible(true);
    	
    }
}
