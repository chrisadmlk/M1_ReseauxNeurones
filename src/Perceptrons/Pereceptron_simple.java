package Perceptrons;

import Tools_Classes.DataToUse;

public class Pereceptron_simple {

	 
	
	
	
	
	
	public static void TechniqueDescenteGradient(DataToUse myData) {
		
		int nbIterMax = 100; 				// 					TODO :   aller chercher dans fichier de config
		double n = 1; 						//	facteur de correction -  aller chercher dans fichier de config

		
		float[] w; 							// vecteur de poids synaptique
		int k = myData.GetLineNb(); 		// nb d'exemples à itérer
		int nbCol = myData.GetColNb(); 
		float p=0; 							// potentiel
		float y;							// sortie
		float e; 							// erreur
		
		float[] d;							// 	valeur attendue d du cours = myData.GetValueAt(x, nbCol)
		int nbIter=0;
		int nbErreur=0;
		
		float[][] x;						// table des x(i)
		x = new float[k][nbCol];
		
		// initialisation du vecteur de w(i) = 0
		w = new float[nbCol];
		for (int i = 0;  i < nbCol; i++) {
			w[i] = 0;
		}
			
		// initialisation du vecteur de  valeurs attendues
		d = new float[k];
		for (int i = 0;  i < k; i++) {
			d[i] = myData.GetValueAt(i,nbCol-1);
		}
		
		// initialisation du tableau de x(i)
		for (int i = 0;  i < k; i++) {
			x[i][0] = 1;
			for (int j = 0;  j < nbCol-1; j++) {
				x[i][j+1] = myData.GetValueAt(i, j);
			}
		}
		

		
		do {
		
			nbErreur = 0;
			for (int i = 0; i < k; i++) {
				
		
			
				// calcul du potentiel puis de la sortie
				p = 0;
				for (int j = 0; j< nbCol ; j++) {
					p = p + w[j] * x[i][j];  //System.out.println( w[j] + " * " + x[i][j]);
				}

				if (p>=0) y=1;
				else y=0;
				

				// détermination de l'erreur
			
				e = d[i] - y ;   //System.out.println( e +" = " + d[i] + " - " + y);
				if (e != 0) nbErreur++;

				
				
				// correction des poids synaptiques
			
				for (int j = 0; j< nbCol ; j++) {
	
					w[j] = w[j] + ((float)  n * e * x[i][j] );
					
				}

			}
			//System.out.println("Nb Erreur : " + nbErreur + "\n\n\n");
		
			nbIter++;
		
		} while(  (nbIter != nbIterMax) && (nbErreur != 0) );
		
		
		System.out.println(nbIter);
		
		
		
		
		
	}
	
	
	
}
