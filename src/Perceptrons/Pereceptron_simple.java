package Perceptrons;

import Tools_Classes.DataToUse;

public class Pereceptron_simple {

	 
	
	public static void MiseAuPointPorteLogiqueET_2_1(DataToUse myData) {
		
		int nbIterMax = 100; 				// 					TODO :   aller chercher dans fichier de config
		double n = 1; 						//	facteur de correction -  aller chercher dans fichier de config

		
		double[] w; 							// vecteur de poids synaptique
		int k = myData.GetLineNb(); 		// nb d'exemples � it�rer
		int nbCol = myData.GetColNb(); 
		double p=0; 							// potentiel
		double y;							// sortie
		double e; 							// erreur
		
		double[] d;							// 	valeur attendue d du cours = myData.GetValueAt(x, nbCol)
		int nbIter=0;
		int nbErreur=0;
		
		double[][] x;						// table des x(i)
		x = new double[k][nbCol];
		
		// initialisation du vecteur de w(i) = 0
		w = new double[nbCol];
		for (int i = 0;  i < nbCol; i++) {
			w[i] = 0;
		}
			
		// initialisation du vecteur de  valeurs attendues
		d = new double[k];
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
			
				
				// d�termination de l'erreur
				
				e = d[i] - y ;   			//System.out.println( e +" = " + d[i] + " - " + y);
				if (e != 0) nbErreur++;

				
				
				// correction des poids synaptiques
			
				for (int j = 0; j< nbCol ; j++) {

					w[j] = w[j] + ((float)  n * e * x[i][j] );
					
				}
				
			}
			
			
			//System.out.println("Nb Erreur : " + nbErreur + "\n\n\n");
			nbIter++;
		
		} while(  (nbIter != nbIterMax) && (nbErreur != 0) );
		
		System.out.println("NB iter = " + nbIter);

	}
	

	
	public static void MiseAuPointPorteLogiqueET_2_3_descente_gradient(DataToUse myData) {
		
		int nbIterMax = 100; 				// 					TODO :   aller chercher dans fichier de config
		double n = 0.2;						//	facteur de correction -  aller chercher dans fichier de config
		double errLim = 0.125001;			//  seuil				  -  aller chercher dans fichier de config

		double[] w; 							// vecteur de poids synaptique
		double[] dw; 						// vecteur des modifications � apporter aux poids synaptiques
		int k = myData.GetLineNb(); 		// nb d'exemples � it�rer
		int nbCol = myData.GetColNb(); 
		double p=0; 							// potentiel (ne sert pas ici)
		double[] y;							// tableaux des sorties
		double[] e; 							// tableau d'erreur locales
		double errMoy;						// erreur moyenne
		
		double[] d;							// 	valeur attendue d du cours = myData.GetValueAt(x, nbCol)
		int nbIter=0;
		int nbErreur=0;
		
		double[][] x;						// table des x(i)
		
		// initialisation du vecteur de w(i) = 0
		w = new double[nbCol];
		for (int i = 0;  i < nbCol; i++) {
			w[i] = 0;
		}
			
		// initialisation du vecteur modification de w(i) = 0
		dw = new double[nbCol];
		for (int i = 0;  i < nbCol; i++) {
			dw[i] = 0;
		}
		
		// initialisation du vecteur de  valeurs attendues
		d = new double[k];
		for (int i = 0;  i < k; i++) {
			d[i] = myData.GetValueAt(i,nbCol-1);
		}
		 
		// initialisation du tableau de x(i)
		x = new double[k][nbCol];
		for (int i = 0;  i < k; i++) {
			x[i][0] = 1;
			for (int j = 0;  j < nbCol-1; j++) {
				x[i][j+1] = myData.GetValueAt(i, j);
			}
		}
		
		// initialisation des erreurs locales
		e = new double[k];
		for (int i = 0;  i < k; i++) {
			e[i] = 0;
		}
		
		// initialisation des sorties
		y = new double[k];
		for (int i = 0;  i < k; i++) {
			y[i] = 0;
		}
		
		
		do {
		
			// reset des valeurs � resetter
			nbErreur = 0;
			for (int j = 0; j< nbCol ; j++) {
				dw[j] = 0;
			}
			
			
			for (int i = 0; i < k; i++) {
				
				// calcul de la sortie
				y[i] = 0;
				for (int j = 0; j< nbCol ; j++) {
					y[i] = y[i] + w[j] * x[i][j];  
				}   //System.out.println("y("+i+") = " + y[i]);

				// d�termination de l'erreur LOCALE 
				e[i] = d[i] - y[i] ;   //System.out.println( e +" = " + d[i] + " - " + y);
	
				
				// correction des variation de poids synaptiques
				for (int j = 0; j< nbCol ; j++) {
					dw[j] = dw[j] + ((float)  n * e[i] * x[i][j] );		
				}  
				
			}
			
			// correction des poids synaptiques
			for (int j = 0; j< nbCol ; j++) {
				w[j] = w[j] + dw[j];
			}
			
			// �valution des sorties puis de l'erreur quadratique
			errMoy = 0;
			for (int i = 0; i < k; i++) {
				y[i] = 0;
				for (int j = 0; j< nbCol ; j++) {
					y[i] = y[i] + w[j] * x[i][j];  
				}  
				errMoy = errMoy + (d[i] - y[i]) *  (d[i] - y[i])   ;
				
			}
			errMoy =  (errMoy / (2*k) );   
			
		
			nbIter++;
		
		} while(  (nbIter != nbIterMax) && (errMoy > errLim) );
		
		System.out.println("NB Iter = " +nbIter);
		
		
	}

	
	
	

	
	public static void MiseAuPointPorteLogiqueET_2_3_ADALINE(DataToUse myData) {
		
		int nbIterMax = 10000; 				// 					TODO :   aller chercher dans fichier de config
		double n = 0.03;						//	facteur de correction -  aller chercher dans fichier de config
		double errLim = 0.1251;				//  seuil				  -  aller chercher dans fichier de config

		double[] w; 							// vecteur de poids synaptique
		double[] dw; 						// vecteur des modifications � apporter aux poids synaptiques
		int k = myData.GetLineNb(); 		// nb d'exemples � it�rer
		int nbCol = myData.GetColNb(); 
		double p=0; 							// potentiel (ne sert pas ici)
		double[] y;							// tableaux des sorties
		double[] e; 							// tableau d'erreur locales
		double errMoy;						// erreur moyenne
		
		double[] d;							// 	valeur attendue d du cours = myData.GetValueAt(x, nbCol)
		int nbIter=0;
		int nbErreur=0;
		
		double[][] x;						// table des x(i)
		
		// initialisation du vecteur de w(i) = 0
		w = new double[nbCol];
		for (int i = 0;  i < nbCol; i++) {
			w[i] = 0;
		}
			
		// initialisation du vecteur modification de w(i) = 0
		dw = new double[nbCol];
		for (int i = 0;  i < nbCol; i++) {
			dw[i] = 0;
		}
		
		// initialisation du vecteur de  valeurs attendues
		d = new double[k];
		for (int i = 0;  i < k; i++) {
			d[i] = myData.GetValueAt(i,nbCol-1);
		}
		 
		// initialisation du tableau de x(i)
		x = new double[k][nbCol];
		for (int i = 0;  i < k; i++) {
			x[i][0] = 1;
			for (int j = 0;  j < nbCol-1; j++) {
				x[i][j+1] = myData.GetValueAt(i, j);
			}
		}
		
		// initialisation des erreurs locales
		e = new double[k];
		for (int i = 0;  i < k; i++) {
			e[i] = 0;
		}
		
		// initialisation des sorties
		y = new double[k];
		for (int i = 0;  i < k; i++) {
			y[i] = 0;
		}
		
		
		do {
		
			// reset des valeurs � resetter
			nbErreur = 0;
			for (int j = 0; j< nbCol ; j++) {
				dw[j] = 0;
			}
			
			
			for (int i = 0; i < k; i++) {
				
				// calcul de la sortie
				y[i] = 0;
				for (int j = 0; j< nbCol ; j++) {
					y[i] = y[i] + w[j] * x[i][j];  
				}   //System.out.println("y("+i+") = " + y[i]);

				// d�termination de l'erreur LOCALE 
				e[i] = d[i] - y[i] ;   //System.out.println( e +" = " + d[i] + " - " + y);
	
				
				// correction des poids synaptiques
				for (int j = 0; j< nbCol ; j++) {
					w[j] = w[j] + ((float)  n * e[i] * x[i][j] );		
				}  
				
			}
			

			
			// �valution des sorties puis de l'erreur quadratique
			errMoy = 0;
			for (int i = 0; i < k; i++) {
				y[i] = 0;
				for (int j = 0; j< nbCol ; j++) {
					y[i] = y[i] + w[j] * x[i][j];  
				}  
				errMoy = errMoy + (d[i] - y[i]) *  (d[i] - y[i])   ;
				
			}
			errMoy =  (errMoy / (2*k) );   
			
		
			nbIter++;
		
		} while(  (nbIter != nbIterMax) && (errMoy > errLim) );
		
		System.out.println("NB Iter = " +nbIter);
		
		
	}
	
	
}
 