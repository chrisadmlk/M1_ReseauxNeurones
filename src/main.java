import Perceptrons.Pereceptron_simple;
import Tools_Classes.DataToUse;

public class main
{
    public static void main(String[] args) {

    	
    	// test et mise au point avec l'operateur logique ET
    	DataToUse myData = new DataToUse();
    	myData.LoadDataFromCSV("Datas\\table_2_3.csv");
    	
    	Pereceptron_simple.TechniqueDescenteGradient(myData);
    }
}
