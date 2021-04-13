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
    	Pereceptron_simple.MiseAuPointPorteLogiqueET_2_3_descente_gradient(myData2);
    	
    	// test et mise au point avec l'operateur logique ET + erreur quadratique   -   ADALINE
    	DataToUse myData3 = new DataToUse();
    	myData3.LoadDataFromCSV("Datas\\table_2_3.csv");	
    	Pereceptron_simple.MiseAuPointPorteLogiqueET_2_3_ADALINE(myData3);
    }
}
