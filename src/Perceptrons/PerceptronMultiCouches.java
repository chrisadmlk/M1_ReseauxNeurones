package Perceptrons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import Tools_Classes.DataToUse;

public class PerceptronMultiCouches {
	
/* 
	nbNeurCC= nbNeurCoucheCachee
	x(i)(j) = entrées pour chaque tour
	u(i,j)  = tableau des poids synaptiques des neurones de la couche cachée 		tableau de nbNeurCC ligne sur nbentrées +1 colonnes
	w(i,j)  = tableau des poids synaptiques des neurones de sorties				tableau de nbsorties ligne sur nbNeurCC +1 colonnes
	du(i,j) = tableau de variation des poids synaptiques des neurones de la couche cachée 		
	dw(i,j)  = tableau de variation des poids synaptiques des neurones de sorties	
	k(i) 	= vecteur des potentiels des neurones de la CC
	y(i)	= vecteur des sorties des neurones de la CC
	p(i) 	= vecteur des potentiels des neurones de la CS
	z(i)	= vecteur des sorties des neurones de la CS
	d(i)	= vecteur des données attendues en sortie
	E 		= erreur quadratique 
	ds(i) 	= signal d'erreur des neurones de la CS
	dc(i) 	= signal d'erreur des neurones de la CC
*/
	
	// déclaration et initialisations des variables
	int nbTour, nbCol, nbIter=0, nbErreur=0;
	int nbIterMax;
	double facteurCorrection, errLim, erreurMoyenne=0;
	int nbColVal=0, nbColSortie;
	double[][] x;
    double[][] u;
    double[][] w;
    double[][] du;
    double[][] dw;
    double[] k;
    double[] y;
    double[] p;
    double[] z;
    double[][] d;
    double E=0;
    double[] ds;
    double[] dc;
    DataToUse myData;
	int nbNeurCC;
	

	// TODO : reporter les modifications du call full batch à celui ci
	
	public void stratADALINE(int _nbNeurCC, DataToUse _myData) {
		int cnt=0;
		nbNeurCC = _nbNeurCC;
		myData = _myData;
		nbTour = myData.GetLineNb();
		nbCol = myData.GetColNb();
		if (myData.getCas() == 403)  nbColVal=nbCol-1;
		if (myData.getCas() == 412)  nbColVal=nbCol-1;
		if (myData.getCas() == 414)  nbColVal=nbCol-3;
		if (myData.getCas() == 417)  nbColVal=nbCol-1;  			// TODO : vérifier cas régression linéaire
		nbColSortie = nbCol - nbColVal;
		
		System.out.println(nbCol + " " + nbColVal + " " + nbColSortie);
		// lecture dans le fichier de config
        InputStream input;
        Properties prop = null;
        try {
            input = new FileInputStream("config.txt");
            prop = new Properties();
            prop.load(input);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        nbIterMax = Integer.parseInt(prop.getProperty("nbIterMax"));
        facteurCorrection = Double.parseDouble(prop.getProperty("n_multi"));
        errLim = Double.parseDouble(prop.getProperty("s_multi"));    //  seuil

    
		// création des tableaux initialisés
    	x = new double[nbTour][nbColVal+1];
    	for (int i =0; i<nbTour; i++) {
    		x[i][0] = 1;
    		for (int j =1; j<nbColVal+1; j++) {
	    		x[i][j] = myData.GetValueAt(i, j-1);				
	    	}
		}
    	u = new double[nbNeurCC][nbColVal+1];
    	for (int i =0; i<nbNeurCC; i++) {
    		for (int j =0; j<nbColVal+1; j++) {
    			Random rnd = new Random();
	    		u[i][j] = rnd.nextGaussian();
	    	}
		}
    	w = new double[nbColSortie][nbNeurCC+1];
    	for (int i =0; i<nbColSortie; i++) {
    		for (int j =0; j<nbNeurCC+1; j++) {
    			Random rnd = new Random();
	    		w[i][j] = rnd.nextGaussian();
	    	}
		}
//        for (int i =0; i<nbColSortie; i++) {
//    		for (int j =0; j<nbNeurCC+1; j++) {
//    			System.out.println("w["+i+"]["+j+"] +" + w[i][j]);
//	    	}
//		}
    	k = new double[nbNeurCC];
    	y = new double[nbNeurCC+1];
    	p = new double[nbColSortie];
    	z = new double[nbColSortie];
    	d = new double[nbTour][nbColSortie];  							
    	for (int i =0; i<nbTour; i++) {
    		for (int j =0; j<nbColSortie; j++) {
	    		d[i][j] = myData.GetValueAt(i, j+nbColVal);						
	    	}
		}
    	ds = new double[nbColSortie];
    	dc = new double[nbNeurCC];
    	

    	
    	do {
    		

//    		cnt++;System.out.println(cnt);
    		
    		for (int i = 0; i<nbTour; i++) {		
    		

    			// calcul des potentiels k(i) et des sorties y(i) des neurones de la CC
    			for (int l=0; l<nbNeurCC; l++) {
    				double tempK=0;
    				for (int c=0; c<nbColVal+1; c++) {
	    				tempK = tempK + x[i][c] * u[l][c] ;		     				
	    				}
    				k[l]=  tempK;
    			}
    			for (int l=0; l<nbNeurCC+1; l++) {
    				if (l==0) y[l] = 1;
    				else y[l] = phi(k[l-1]);	
    			}

    			
	    		// calcul des potentiels p(i) et des sorties z(i) des neurones de la CS	
    			for (int l=0; l<nbColSortie; l++) {
    				double tempP=0;
    				for (int c=0; c<nbNeurCC+1; c++) {
	    				tempP = tempP + y[c] * w[l][c] ;		  							// possible erreur : w[c][l]
	    				}
    				p[l]=  tempP;
    				
    				z[l] = phi(p[l]);		 												
    			}
	    		
	    		
	    		// comparaison des sorties z(i) avec les valeurs attendues d(i) et calcul de l'erreur quadratique
	    		E=0;
    			for (int l=0; l<nbColSortie; l++) {									
    				double diff = d[i][l] - z[l];
		 			E = E + diff*diff;	
    			}
    			E = E/2;																	// TODO : checker par printf
	 			//System.out.println("E : " + E + "\n");

    			
	    		// calcul des signaux d'erreur ds(i) de la CS et  dc(i) de la CC
    			for (int l=0; l<nbColSortie; l++) {									
    				ds[l] = (d[i][l] - z[l]) * z[l] * (1- z[l]) ;    //System.out.println(ds[l]+" = ("+d[i][l]+" - "+z[l]+")*" +z[l]+" * (1- "+z[l]+")");
    			}

    			for (int l=1; l<nbNeurCC+1; l++) {		// +1 car y0 = 1  et x0=1  
    				double tempPhi = y[l] * (1-y[l]);    //System.out.println("tempPhi = " + tempPhi +" = " + y[l] + "*" + (1-y[l]));
    				double temp=0;
    				for (int c = 0; c<nbColSortie; c++) {
    					temp = temp + ds[c] * w[c][l] ;			//System.out.println("ds[c] * w[c][l]  " + ds[c] + "   " + w[c][l]);
    				}
    				dc[l-1] = tempPhi * temp; 		//System.out.println(" ===>  dc[l-1]  =   " + dc[l-1] );		
    			}


    		
    			// correction des poids synaptiques w(i) de la CS et u(i) de la CC
    	    	for (int l =0; l<nbColSortie; l++) {
    	    		for (int c =0; c<nbNeurCC+1; c++) {
    	    			//System.out.println("w[l][c] avant : " + w[l][c] );
    		    		w[l][c] = w[l][c] + facteurCorrection*ds[l] * y[c];    
    		    		//System.out.println("w[l][c] après : " + w[l][c] );
    		    	}
    			}
    	    	for (int l =0; l<nbNeurCC; l++) {
    	    		for (int c =0; c<nbColVal+1; c++) {
    	    			//System.out.println("u[l][c] avant : " + u[l][c] );
    		    		u[l][c] = u[l][c] + facteurCorrection*dc[l] * x[i][c];
    		    		//System.out.println("u[l][c] après : " + u[l][c] );
    		    	}
    			}
    	    	

    		}
    		

    		// détermination de l'erreur moyenne et du nombre d'erreur (pour test de sortie de boucle)
    		double[] result = CalculateErrMoy();
            erreurMoyenne = result[0];
            nbErreur = (int) result[1];
            nbIter++;
    		
    		

    	} while( !(TestSortieBoucle(myData, nbIter, nbIterMax, erreurMoyenne, errLim, nbErreur)));
    	
    	System.out.println("nbErreur = " + nbErreur);
        System.out.println(erreurMoyenne + "   /   " + errLim);
        System.out.println("NB Iter = " + nbIter);
//        for (int i =0; i<nbColSortie; i++) {
//    		for (int j =0; j<nbNeurCC+1; j++) {
//    			System.out.println("w["+i+"]["+j+"] +" + w[i][j]);
//	    	}
//		}
		
	}
	
	
	
	
	

	public void stratFULLBATCH(int _nbNeurCC, DataToUse _myData) {
	
		nbNeurCC = _nbNeurCC;
		myData = _myData;
		nbTour = myData.GetLineNb();
		nbCol = myData.GetColNb();
		if (myData.getCas() == 403)  nbColVal=nbCol-1;
		if (myData.getCas() == 412)  nbColVal=nbCol-1;
		if (myData.getCas() == 414)  nbColVal=nbCol-3;
		if (myData.getCas() == 417)  nbColVal=nbCol-1;  			// TODO : vérifier cas régression linéaire
		nbColSortie = nbCol - nbColVal;
		
		
//		System.out.println(nbCol + " " + nbColVal + " " + nbColSortie + " " +myData.getCas() );
		
		// lecture dans le fichier de config
        InputStream input;
        Properties prop = null;
        try {
            input = new FileInputStream("config.txt");
            prop = new Properties();
            prop.load(input);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        nbIterMax = Integer.parseInt(prop.getProperty("nbIterMax"));
        facteurCorrection = Double.parseDouble(prop.getProperty("n_multi"));
        errLim = Double.parseDouble(prop.getProperty("s_multi"));    //  seuil

    
		// création des tableaux initialisés
    	x = new double[nbTour][nbColVal+1];
    	for (int i =0; i<nbTour; i++) {
    		x[i][0] = 1;
    		for (int j =1; j<nbColVal+1; j++) {
	    		x[i][j] = myData.GetValueAt(i, j-1);				
	    	}
		}
    	u = new double[nbNeurCC][nbColVal+1];
    	for (int i =0; i<nbNeurCC; i++) {
    		for (int j =0; j<nbColVal+1; j++) {
    			Random rnd = new Random();
	    		u[i][j] = rnd.nextGaussian();
	    	}
		}
    	du = new double[nbNeurCC][nbColVal+1];
    	w = new double[nbColSortie][nbNeurCC+1];
    	for (int i =0; i<nbColSortie; i++) {
    		for (int j =0; j<nbNeurCC+1; j++) {
    			Random rnd = new Random();
	    		w[i][j] = rnd.nextGaussian();
	    	}
		}
//        for (int i =0; i<nbColSortie; i++) {
//    		for (int j =0; j<nbNeurCC+1; j++) {
//    			System.out.println("w["+i+"]["+j+"] +" + w[i][j]);
//	    	}
//		}
    	dw = new double[nbColSortie][nbNeurCC+1];
    	k = new double[nbNeurCC];
    	y = new double[nbNeurCC+1];
    	p = new double[nbColSortie];
    	z = new double[nbColSortie];
    	d = new double[nbTour][nbColSortie];  							
    	for (int i =0; i<nbTour; i++) {
    		for (int j =0; j<nbColSortie; j++) {
	    		d[i][j] = myData.GetValueAt(i, j+nbColVal);						
	    	}
		}
    	ds = new double[nbColSortie];
    	dc = new double[nbNeurCC];
    	

    	do {
    		// réinitialisations des vecteurs de corrections de poids synaptiques et de nbErreur
        	du = new double[nbNeurCC][nbColVal+1];
        	for (int i =0; i<nbNeurCC; i++) {
        		for (int j =0; j<nbColVal+1; j++) {
    	    		du[i][j] = 0;
    	    	}
    		}
        	dw = new double[nbColSortie][nbNeurCC+1];
        	for (int i =0; i<nbColSortie; i++) {
        		for (int j =0; j<nbNeurCC+1; j++) {
    	    		dw[i][j] = 0;
    	    	}
    		}
        	nbErreur = 0;
    		
        	
    		for (int i = 0; i<nbTour; i++) {			
    		
    			// calcul des potentiels k(i) et des sorties y(i) des neurones de la CC
    			for (int l=0; l<nbNeurCC; l++) {
    				double tempK=0;
    				for (int c=0; c<nbColVal+1; c++) {
	    				tempK = tempK + x[i][c] * u[l][c] ;		     				
	    				}
    				k[l]=  tempK;
    			}
    			for (int l=0; l<nbNeurCC+1; l++) {
    				if (l==0) y[l] = 1;
    				else y[l] = phi(k[l-1]);	
    			}

    			
	    		// calcul des potentiels p(i) et des sorties z(i) des neurones de la CS	
    			for (int l=0; l<nbColSortie; l++) {
    				double tempP=0;
    				for (int c=0; c<nbNeurCC+1; c++) {
	    				tempP = tempP + y[c] * w[l][c] ;		  							// possible erreur : w[c][l]
	    				}
    				p[l]=  tempP;
    				
    				z[l] = phi(p[l]);		 												
    			}
	    		
	    		
	    		// comparaison des sorties z(i) avec les valeurs attendues d(i) et calcul de l'erreur quadratique
	    		E=0;
    			for (int l=0; l<nbColSortie; l++) {									
    				double diff = d[i][l] - z[l];
		 			E = E + diff*diff;	
    			}
    			E = E/2;																	
//	 			System.out.println("E : " + E + "\n");

    			
	    		// calcul des signaux d'erreur ds(i) de la CS et  dc(i) de la CC
    			for (int l=0; l<nbColSortie; l++) {									
    				ds[l] = (d[i][l] - z[l]) * z[l] * (1- z[l]) ;    //System.out.println(ds[l]+" = ("+d[i][l]+" - "+z[l]+")*" +z[l]+" * (1- "+z[l]+")");
    			}

    			for (int l=1; l<nbNeurCC+1; l++) {		// +1 car y0 = 1  et x0=1  
    				double tempPhi = y[l] * (1-y[l]);    //System.out.println("tempPhi = " + tempPhi +" = " + y[l] + "*" + (1-y[l]));
    				double temp=0;
    				for (int c = 0; c<nbColSortie; c++) {
    					temp = temp + ds[c] * w[c][l] ;			//System.out.println("ds[c] * w[c][l]  " + ds[c] + "   " + w[c][l]);
    				}
    				dc[l-1] = tempPhi * temp; 		//System.out.println(" ===>  dc[l-1]  =   " + dc[l-1] );		
    			}

    		
    			// modifications des variations de poids synaptiques dw(i) de la CS et du(i) de la CC
    	    	for (int l =0; l<nbColSortie; l++) {
    	    		for (int c =0; c<nbNeurCC+1; c++) {
    	    			//System.out.println("dw[l][c] avant : " + dw[l][c] );
    		    		dw[l][c] = dw[l][c] + facteurCorrection*ds[l] * y[c];    
    		    		//System.out.println("dw[l][c] après : " + dw[l][c] );
    		    	}
    			}
    	    	for (int l =0; l<nbNeurCC; l++) {
    	    		for (int c =0; c<nbColVal+1; c++) {
    	    			//System.out.println("du[l][c] avant : " + du[l][c] );
    		    		du[l][c] = du[l][c] + facteurCorrection*dc[l] * x[i][c];
    		    		//System.out.println("du[l][c] après : " + du[l][c] );
    		    	}
    			}

    		}
    		
    		
    		
	

    		// modifications des poids synaptiques w(i) et d(i)
	    	for (int l =0; l<nbColSortie; l++) {
	    		for (int c =0; c<nbNeurCC+1; c++) {
	    			//System.out.println("w[l][c] avant : " + w[l][c] );
		    		w[l][c] = w[l][c] + dw[l][c];
		    		//System.out.println("w[l][c] après : " + w[l][c] );
		    	}
			}
	    	for (int l =0; l<nbNeurCC; l++) {
	    		for (int c =0; c<nbColVal+1; c++) {
	    			//System.out.println("u[l][c] avant : " + u[l][c] );
		    		u[l][c] = u[l][c] + du[l][c];
		    		//System.out.println("u[l][c] après : " + u[l][c] );
		    	}
			}
    		
	    	
			//System.out.println("\n\n");
    		
    		// détermination de l'erreur moyenne et du nombre d'erreur (pour test de sortie de boucle)
    		double[] result = CalculateErrMoy();
            erreurMoyenne = result[0];
            nbErreur = (int) result[1];
            nbIter++;
    		

    	} while( !(TestSortieBoucle(myData, nbIter, nbIterMax, erreurMoyenne, errLim, nbErreur)));
    	
    	
        System.out.println(erreurMoyenne + "   /   " + errLim);
        System.out.println("NB Iter = " + nbIter);
//        for (int i =0; i<nbColSortie; i++) {
//    		for (int j =0; j<nbNeurCC+1; j++) {
//    			System.out.println("w["+i+"][+"+j+"] +" + w[i][j]);
//	    	}
//		}
        
        
	}
	
	
	
	
	
	

	
    public static boolean TestSortieBoucle(DataToUse myData, int nbIter, int nbIterMax, double errMoy, double errLim, int nbErreur) {
        
    	// vu les exemples du cours, ceci suffit
    	if (nbIter == nbIterMax) return true;
        else if (errMoy < errLim) return true;
        else return false;
  
    }


	 
	

    public double[] CalculateErrMoy() {
        int cas = myData.getCas();
        double[] rep = new double[2];
        double errMoy = 0;
        int nbErreur = 0;

        // même façon de tester l'erreur quadratique moyenne pour chaque cas
 
        	E=0;
    		for (int i = 0; i<nbTour; i++) {			
    			
    			// calcul des potentiels k(i) et des sorties y(i) des neurones de la CC
    			for (int l=0; l<nbNeurCC; l++) {
    				double tempK=0;
    				for (int c=0; c<nbColVal+1; c++) {
	    				tempK = tempK + x[i][c] * u[l][c] ;		  
	    				}
    				k[l]=  tempK;
    			}
    			for (int l=0; l<nbNeurCC+1; l++) {
    				if (l==0) y[l] = 1;
    				else y[l] = phi(k[l-1]);		 										
    			}

	    		// calcul des potentiels p(i) et des sorties z(i) des neurones de la CS	
    			for (int l=0; l<nbColSortie; l++) {
    				double tempP=0;
    				for (int c=0; c<nbNeurCC+1; c++) {
	    				tempP = tempP + y[c] * w[l][c] ;		  					
	    				}
    				p[l]=  tempP;
    				z[l] = phi(p[l]);		 										
    			}
	    		
	    		// comparaison des sorties z(i) avec les valeurs attendues d(i) et calcul de l'erreur quadratique
    			for (int l=0; l<nbColSortie; l++) {									
    				double diff = d[i][l] - z[l];
    				if (diff > 0.5) nbErreur++;										// propre à ce cas de figure 4.7
		 			E = E + diff*diff;							
    			}
    		}
    		errMoy = E / (2*nbTour*nbColSortie);		// TODO : possible source d'erreur : ne pas plutôt utiliser 2 * nbTour 
       

        rep[0] = errMoy;
        rep[1] = nbErreur;
        return rep;
    }
	
	
	
	
	
	
	public static double phi(double val) {
		double result = 1 / ( 1 + Math.exp(-val));
		return result;
	}
	
	
	
	
	
	public int classThisData(double x1, double x2) {
		
//		for (int i=0; i<nbColSortie; i++) {		
//		}
		
		double[] localx = {1, x1, x2};

		// calcul des potentiels k(i) et des sorties y(i) des neurones de la CC
		for (int l=0; l<nbNeurCC; l++) {
			double tempK=0;
			for (int c=0; c<nbColVal+1; c++) {
				tempK = tempK + localx[c] * u[l][c] ;		     				
				}
			k[l]=  tempK;
		}
		for (int l=0; l<nbNeurCC+1; l++) {
			if (l==0) y[l] = 1;
			else y[l] = phi(k[l-1]);	
		}

		
		// calcul des potentiels p(i) et des sorties z(i) des neurones de la CS	
		for (int l=0; l<nbColSortie; l++) {
			double tempP=0;
			for (int c=0; c<nbNeurCC+1; c++) {
				tempP = tempP + y[c] * w[l][c] ;		  							// possible erreur : w[c][l]
				}
			p[l]=  tempP;
			
			z[l] = phi(p[l]);		 												
		}
		
		
		// seuillage et renvoie de la valeur (num du groupe ou 0 si 0 pour tout les groupes)
		for (int l=0; l<nbColSortie; l++) {
//			System.out.println(z[l]);
			if (z[l]>0.5) {
//				System.out.println("renvoit " +(l+1));
				return (l+1); 	}											
		}
//		System.out.println("renvoit null");
		return 0;
	}
	
	
	
public double getYForThisX(double x1) {
		

		double[] localx = {1, x1};

		// calcul des potentiels k(i) et des sorties y(i) des neurones de la CC
		for (int l=0; l<nbNeurCC; l++) {
			double tempK=0;
			for (int c=0; c<nbColVal+1; c++) {
				tempK = tempK + localx[c] * u[l][c] ;		     				
				}
			k[l]=  tempK;
		}
		for (int l=0; l<nbNeurCC+1; l++) { 
			if (l==0) y[l] = 1;
			else y[l] = phi(k[l-1]);	
		}

		
		// calcul des potentiels p(i) et des sorties z(i) des neurones de la CS	
		double tempP=0;
		for (int c=0; c<nbNeurCC+1; c++) {
			tempP = tempP + y[c] * w[0][c] ;		  							// possible erreur : w[c][l]
			}
		p[0]=  tempP;
			
		z[0] = phi(p[0]);		 												
		
		

		return z[0];
	}
	
	


}
