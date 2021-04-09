import GUI.Main_Frame;
import Perceptrons.Pereceptron_simple;
import Tools_Classes.DataToUse;

public class main
{
    public static void main(String[] args) {

    	
    	// test et mise au point avec l'operateur logique ET
    	DataToUse myData = new DataToUse();
    	myData.LoadDataFromCSV("Datas\\table_2_1.csv");	
    	Pereceptron_simple.MiseAuPointPorteLogiqueET_2_1(myData);
    	
    	// test et mise au point avec l'operateur logique ET + erreur quadratique   -  descente de gradient
    	DataToUse myData2 = new DataToUse();
    	myData2.LoadDataFromCSV("Datas\\table_2_3.csv");	
    	Pereceptron_simple.Technique_descente_gradient(myData2);
    	
    	// test et mise au point avec l'operateur logique ET + erreur quadratique   -   ADALINE
    	DataToUse myData3 = new DataToUse();
    	myData3.LoadDataFromCSV("Datas\\table_2_3.csv");	
    	Pereceptron_simple.Technique_ADALINE(myData3);
    	
    	// test 2.9 descente de gradient
    	DataToUse myData4 = new DataToUse();
    	myData4.LoadDataFromCSV("Datas\\table_2_9.csv");	
    	Pereceptron_simple.Technique_descente_gradient(myData4);
    	
    	// test 2.9 ADALINE
    	DataToUse myData5 = new DataToUse();
    	myData5.LoadDataFromCSV("Datas\\table_2_9.csv");	
    	Pereceptron_simple.Technique_ADALINE(myData5);
    	
    	// test 2.10 descente de gradient
    	DataToUse myData6 = new DataToUse();
    	myData6.LoadDataFromCSV("Datas\\table_2_10.csv");	
    	Pereceptron_simple.Technique_descente_gradient(myData6);
    	
    	// test 2.10 ADALINE
    	DataToUse myData7 = new DataToUse();
    	myData7.LoadDataFromCSV("Datas\\table_2_10.csv");	
    	Pereceptron_simple.Technique_ADALINE(myData7);
    	
    	
    	
    	// test de la JFrame
    	
    	Main_Frame frame = new Main_Frame(myData5);
    	frame.setVisible(true);
    	
    }
}
