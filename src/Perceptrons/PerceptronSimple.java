package Perceptrons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import Tools_Classes.DataToUse;

public class PerceptronSimple {

    public static void MiseAuPointPorteLogiqueET_2_1(DataToUse myData) {
        // TODO :   aller chercher dans fichier de config
        int nbIterMax = 1000;
        double facteurCorrection = 0.012;

        double[] vecteurPoidsSynaptique;
        int nbExamplesToIterate = myData.GetLineNb();
        int nbCol = myData.GetColNb();
        double potentiel = 0;
        double sortie;
        double erreur;

        // Valeur attendue "d" du cours = myData.GetValueAt(x, nbCol)
        double[] d;
        int nbIter = 0;
        int nbErreur = 0;

        // Table des x(i)
        double[][] x;
        x = new double[nbExamplesToIterate][nbCol];

        // Initialisation du vecteur de w(i) = 0
        vecteurPoidsSynaptique = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            vecteurPoidsSynaptique[i] = 0;
        }

        // Initialisation du vecteur de  valeurs attendues
        d = new double[nbExamplesToIterate];
        for (int i = 0; i < nbExamplesToIterate; i++) {
            d[i] = myData.GetValueAt(i, nbCol - 1);
        }

        // initialisation du tableau de x(i)
        for (int i = 0; i < nbExamplesToIterate; i++) {
            x[i][0] = 1;
            for (int j = 0; j < nbCol - 1; j++) {
                x[i][j + 1] = myData.GetValueAt(i, j);
            }
        }

        do {
            nbErreur = 0;
            for (int i = 0; i < nbExamplesToIterate; i++) {
                // calcul du potentiel puis de la sortie
                potentiel = 0;
                for (int j = 0; j < nbCol; j++) {
                    potentiel = potentiel + vecteurPoidsSynaptique[j] * x[i][j];  //System.out.println( w[j] + " * " + x[i][j]);
                }

                if (potentiel >= 0) sortie = 1;
                else sortie = 0;

                // Détermination de l'erreur
                erreur = d[i] - sortie;
                //System.out.println( e +" = " + d[i] + " - " + y);
                if (erreur != 0) nbErreur++;

                // correction des poids synaptiques
                for (int j = 0; j < nbCol; j++) {
                    vecteurPoidsSynaptique[j] = vecteurPoidsSynaptique[j] + ((float) facteurCorrection * erreur * x[i][j]);
                }
            }
            //System.out.println("Nb Erreur : " + nbErreur + "\n\n\n");
            nbIter++;
        } while ((nbIter != nbIterMax) && (nbErreur != 0));
        System.out.println("NB iter = " + nbIter);
    }

    public static void Technique_descente_gradient(DataToUse myData) {

        InputStream input;
        Properties prop = null;
        try {
            input = new FileInputStream("config.txt");
            prop = new Properties();
            prop.load(input);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        int nbIterMax = Integer.parseInt(prop.getProperty("nbIterMax"));
        double n = Double.parseDouble(prop.getProperty("n_DG"));        //	facteur de correction
        double errLim = Double.parseDouble(prop.getProperty("s_DG"));    //  seuil

        double[] w;                            // vecteur de poids synaptique
        double[] dw;                        // vecteur des modifications à apporter aux poids synaptiques
        int k = myData.GetLineNb();        // nb d'exemples à itérer
        int nbCol = myData.GetColNb();
        double p = 0;                            // potentiel (ne sert pas ici)
        double[] y;                            // tableaux des sorties
        double[] e;                            // tableau d'erreur locales
        double errMoy;                        // erreur moyenne

        double[] d;                            // 	valeur attendue d du cours = myData.GetValueAt(x, nbCol)
        int nbIter = 0;
        int nbErreur = 0;

        double[][] x;                        // table des x(i)

        // initialisation du vecteur de w(i) = 0
        w = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            w[i] = 0;
        }

        // initialisation du vecteur modification de w(i) = 0
        dw = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            dw[i] = 0;
        }

        // initialisation du vecteur de  valeurs attendues
        d = new double[k];
        for (int i = 0; i < k; i++) {
            d[i] = myData.GetValueAt(i, nbCol - 1);
        }


        // initialisation du tableau de x(i)              TODO : ceci change dans le cas d'une régression
        x = new double[k][nbCol];
        for (int i = 0; i < k; i++) {
            x[i][0] = 1;
            for (int j = 0; j < nbCol - 1; j++) {
                x[i][j + 1] = myData.GetValueAt(i, j);
            }
        }

        // initialisation des erreurs locales
        e = new double[k];
        for (int i = 0; i < k; i++) {
            e[i] = 0;
        }

        // initialisation des sorties
        y = new double[k];
        for (int i = 0; i < k; i++) {
            y[i] = 0;
        }


        do {

            // reset des valeurs à resetter
            nbErreur = 0;
            for (int j = 0; j < nbCol; j++) {
                dw[j] = 0;
            }


            for (int i = 0; i < k; i++) {

                // calcul de la sortie
                y[i] = 0;
                for (int j = 0; j < nbCol; j++) {
                    y[i] = y[i] + w[j] * x[i][j];
                }   //System.out.println("y("+i+") = " + y[i]);

                // détermination de l'erreur LOCALE
                e[i] = d[i] - y[i];   //System.out.println( e +" = " + d[i] + " - " + y);

                // correction des variation de poids synaptiques
                for (int j = 0; j < nbCol; j++) {
                    dw[j] = dw[j] + ((float) n * e[i] * x[i][j]);
                }

            }

            // correction des poids synaptiques
            for (int j = 0; j < nbCol; j++) {
                w[j] = w[j] + dw[j];
            }


            double[] result = CalculateErrMoy(myData, k, nbCol, y, w, x, d, nbErreur);
            errMoy = result[0];
            nbErreur = (int) result[1];


            nbIter++;


        } while (!(TestSortieBoucle(myData, nbIter, nbIterMax, errMoy, errLim, nbErreur)));
        // TODO Dans le cas d'une classification, cette condition devient :     while(  (nbIter != nbIterMax) && (nbErreur!=0) );


        System.out.println(errMoy + "   /   " + errLim);
        System.out.println("NB Iter = " + nbIter);

        myData.SetLearningResult(nbIter, nbErreur, errMoy, w);

    }


    public static void Technique_ADALINE(DataToUse myData) {


        InputStream input;
        Properties prop = null;
        try {
            input = new FileInputStream("config.txt");
            prop = new Properties();
            prop.load(input);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        int nbIterMax = Integer.parseInt(prop.getProperty("nbIterMax"));
        double n = Double.parseDouble(prop.getProperty("n_ADA"));        //	facteur de correction
        double errLim = Double.parseDouble(prop.getProperty("s_ADA"));    //  seuil


        double[] w;                        // vecteur de poids synaptique
        double[] dw;                        // vecteur des modifications à apporter aux poids synaptiques
        int k = myData.GetLineNb();        // nb d'exemples à itérer
        int nbCol = myData.GetColNb();
        double p = 0;                        // potentiel (ne sert pas ici)
        double[] y;                            // tableaux des sorties
        double[] e;                        // tableau d'erreur locales
        double errMoy;                        // erreur moyenne

        double[] d;                            // 	valeur attendue d du cours = myData.GetValueAt(x, nbCol)
        int nbIter = 0;
        int nbErreur = 0;

        double[][] x;                        // table des x(i)

        // initialisation du vecteur de w(i) = 0
        w = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            w[i] = 0;
        }

        // initialisation du vecteur modification de w(i) = 0
        dw = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            dw[i] = 0;
        }

        // initialisation du vecteur de  valeurs attendues
        d = new double[k];
        for (int i = 0; i < k; i++) {
            d[i] = myData.GetValueAt(i, nbCol - 1);
        }

        // initialisation du tableau de x(i)
        x = new double[k][nbCol];
        for (int i = 0; i < k; i++) {
            x[i][0] = 1;
            for (int j = 0; j < nbCol - 1; j++) {
                x[i][j + 1] = myData.GetValueAt(i, j);
            }
        }

        // initialisation des erreurs locales
        e = new double[k];
        for (int i = 0; i < k; i++) {
            e[i] = 0;
        }

        // initialisation des sorties
        y = new double[k];
        for (int i = 0; i < k; i++) {
            y[i] = 0;
        }


        do {

            // reset des valeurs à resetter
            nbErreur = 0;
            for (int j = 0; j < nbCol; j++) {
                dw[j] = 0;
            }


            for (int i = 0; i < k; i++) {

                // calcul de la sortie
                y[i] = 0;
                for (int j = 0; j < nbCol; j++) {
                    y[i] = y[i] + w[j] * x[i][j];
                }   //System.out.println("y("+i+") = " + y[i]);

                // détermination de l'erreur LOCALE
                e[i] = d[i] - y[i];   //System.out.println( e +" = " + d[i] + " - " + y);

                // correction des poids synaptiques
                for (int j = 0; j < nbCol; j++) {
                    w[j] = w[j] + ((float) n * e[i] * x[i][j]);
                }

            }


            // évalution des sorties puis de l'erreur quadratique
            double[] result = CalculateErrMoy(myData, k, nbCol, y, w, x, d, nbErreur);
            errMoy = result[0];
            nbErreur = (int) result[1];


            nbIter++;


        } while (!(TestSortieBoucle(myData, nbIter, nbIterMax, errMoy, errLim, nbErreur)));


        System.out.println(errMoy + "   /   " + errLim);
        System.out.println("NB Iter = " + nbIter);

        myData.SetLearningResult(nbIter, nbErreur, errMoy, w);

    }


    public static boolean TestSortieBoucle(DataToUse myData, int nbIter, int nbIterMax, double errMoy, double errLim, int nbErreur) {

        int cas = myData.getCas();

        if ((cas == 211) || (cas == 417) || (cas == 301) || (cas == 305)) {      // Les cas sont ajouté ici en fonction du cours, et non selon une classification logique
            if (nbIter == nbIterMax) return true;
            if (errMoy < errLim) return true;
        } else if (cas == 210) {
            if (nbIter == nbIterMax) return true;
            if (nbErreur == 3) return true;
        } else {
            if (nbIter == nbIterMax) return true;
            if (nbErreur == 0) return true;
        }

        return false;

    }


    public static double[] CalculateErrMoy(DataToUse myData, int k, int nbCol, double y[], double w[], double x[][], double d[], int nbErreur) {
        int cas = myData.getCas();
        double errMoy = 0;
        double[] rep = new double[2];
        double partialErr = 0;

        if ((cas == 211) || (cas == 417)) {

            for (int i = 0; i < k; i++) {
                for (int j = 0; j < nbCol - 1; j++) {
                    partialErr = d[i] - w[0] - w[j + 1] * x[i][1];    // TODO : vérifier valeurs
                }
                errMoy = errMoy + partialErr * partialErr;
            }
            errMoy = (errMoy / (2 * k));
        } else {

            for (int i = 0; i < k; i++) {
                y[i] = 0;
                for (int j = 0; j < nbCol; j++) {
                    y[i] = y[i] + w[j] * x[i][j];
                }
                errMoy = errMoy + (d[i] - y[i]) * (d[i] - y[i]);

                if ((d[i] >= 0) && (y[i] < 0)) nbErreur++;
                else if ((d[i] < 0) && (y[i] >= 0)) nbErreur++;
            }
            errMoy = (errMoy / (2 * k));
        }

        rep[0] = errMoy;
        rep[1] = nbErreur;
        return rep;
    }


}
 