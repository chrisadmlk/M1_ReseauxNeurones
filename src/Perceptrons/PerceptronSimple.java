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
        double facteurCorrection = Double.parseDouble(prop.getProperty("n_DG"));
        double errLim = Double.parseDouble(prop.getProperty("s_DG"));    //  seuil

        double[] vecteurPoidsSynaptique;
        // Vecteur des modifications à apporter aux poids synaptiques
        double[] vecteurModifications;
        int nbExamplesToIterate = myData.GetLineNb();
        int nbCol = myData.GetColNb();
        double[] sorties;
        double[] erreursLocales;
        double erreurMoyenne;

        // 	Valeur attendue d du cours = myData.GetValueAt(x, nbCol)
        double[] d;
        int nbIter = 0;
        int nbErreur = 0;
        // Table des x(i)
        double[][] x;

        // initialisation du vecteur de w(i) = 0
        vecteurPoidsSynaptique = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            vecteurPoidsSynaptique[i] = 0;
        }

        // initialisation du vecteur modification de w(i) = 0
        vecteurModifications = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            vecteurModifications[i] = 0;
        }

        // initialisation du vecteur de  valeurs attendues
        d = new double[nbExamplesToIterate];
        for (int i = 0; i < nbExamplesToIterate; i++) {
            d[i] = myData.GetValueAt(i, nbCol - 1);
        }


        // initialisation du tableau de x(i)              TODO : ceci change dans le cas d'une régression
        x = new double[nbExamplesToIterate][nbCol];
        for (int i = 0; i < nbExamplesToIterate; i++) {
            x[i][0] = 1;
            for (int j = 0; j < nbCol - 1; j++) {
                x[i][j + 1] = myData.GetValueAt(i, j);
            }
        }

        // initialisation des erreurs locales
        erreursLocales = new double[nbExamplesToIterate];
        for (int i = 0; i < nbExamplesToIterate; i++) {
            erreursLocales[i] = 0;
        }

        // initialisation des sorties
        sorties = new double[nbExamplesToIterate];
        for (int i = 0; i < nbExamplesToIterate; i++) {
            sorties[i] = 0;
        }

        do {
            // reset des valeurs à resetter
            nbErreur = 0;
            for (int j = 0; j < nbCol; j++) {
                vecteurModifications[j] = 0;
            }

            for (int i = 0; i < nbExamplesToIterate; i++) {
                // calcul de la sortie
                sorties[i] = 0;
                for (int j = 0; j < nbCol; j++) {
                    sorties[i] = sorties[i] + vecteurPoidsSynaptique[j] * x[i][j];
                }
                //System.out.println("y("+i+") = " + y[i]);

                // Détermination de l'erreur LOCALE
                erreursLocales[i] = d[i] - sorties[i];
                //System.out.println( e +" = " + d[i] + " - " + y);

                // correction des variation de poids synaptiques
                for (int j = 0; j < nbCol; j++) {
                    vecteurModifications[j] = vecteurModifications[j] + ((float) facteurCorrection * erreursLocales[i] * x[i][j]);
                }
            }

            // correction des poids synaptiques
            for (int j = 0; j < nbCol; j++) {
                vecteurPoidsSynaptique[j] = vecteurPoidsSynaptique[j] + vecteurModifications[j];
            }


            double[] result = CalculateErrMoy(myData, nbExamplesToIterate, nbCol, sorties, vecteurPoidsSynaptique, x, d, nbErreur);
            erreurMoyenne = result[0];
            nbErreur = (int) result[1];

            nbIter++;
        } while (!(TestSortieBoucle(myData, nbIter, nbIterMax, erreurMoyenne, errLim, nbErreur)));
        // TODO Dans le cas d'une classification, cette condition devient :     while(  (nbIter != nbIterMax) && (nbErreur!=0) );

        System.out.println(erreurMoyenne + "   /   " + errLim);
        System.out.println("NB Iter = " + nbIter);

        myData.SetLearningResult(nbIter, nbErreur, erreurMoyenne, vecteurPoidsSynaptique);
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
        double facteurCorrection = Double.parseDouble(prop.getProperty("n_ADA"));
        double errLim = Double.parseDouble(prop.getProperty("s_ADA"));    //  seuil


        double[] vecteurPoidsSynaptique;
        double[] vecteurModifications;
        int nbreExamplesToIterate = myData.GetLineNb();
        int nbCol = myData.GetColNb();
        double[] sorties;
        double[] erreursLocales;
        double erreurMoyenne;

        //  Valeur attendue d du cours = myData.GetValueAt(x, nbCol)
        double[] d;
        int nbIter = 0;
        int nbErreur = 0;

        // table des x(i)
        double[][] x;

        // initialisation du vecteur de w(i) = 0
        vecteurPoidsSynaptique = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            vecteurPoidsSynaptique[i] = 0;
        }

        // initialisation du vecteur modification de w(i) = 0
        vecteurModifications = new double[nbCol];
        for (int i = 0; i < nbCol; i++) {
            vecteurModifications[i] = 0;
        }

        // initialisation du vecteur de  valeurs attendues
        d = new double[nbreExamplesToIterate];
        for (int i = 0; i < nbreExamplesToIterate; i++) {
            d[i] = myData.GetValueAt(i, nbCol - 1);
        }

        // initialisation du tableau de x(i)
        x = new double[nbreExamplesToIterate][nbCol];
        for (int i = 0; i < nbreExamplesToIterate; i++) {
            x[i][0] = 1;
            for (int j = 0; j < nbCol - 1; j++) {
                x[i][j + 1] = myData.GetValueAt(i, j);
            }
        }

        // initialisation des erreurs locales
        erreursLocales = new double[nbreExamplesToIterate];
        for (int i = 0; i < nbreExamplesToIterate; i++) {
            erreursLocales[i] = 0;
        }

        // initialisation des sorties
        sorties = new double[nbreExamplesToIterate];
        for (int i = 0; i < nbreExamplesToIterate; i++) {
            sorties[i] = 0;
        }

        do {
            // reset des valeurs à resetter
            nbErreur = 0;
            for (int j = 0; j < nbCol; j++) {
                vecteurModifications[j] = 0;
            }

            for (int i = 0; i < nbreExamplesToIterate; i++) {
                // calcul de la sortie
                sorties[i] = 0;
                for (int j = 0; j < nbCol; j++) {
                    sorties[i] = sorties[i] + vecteurPoidsSynaptique[j] * x[i][j];
                }
                //System.out.println("y("+i+") = " + y[i]);

                // détermination de l'erreur LOCALE
                erreursLocales[i] = d[i] - sorties[i];
                //System.out.println( e +" = " + d[i] + " - " + y);

                // correction des poids synaptiques
                for (int j = 0; j < nbCol; j++) {
                    vecteurPoidsSynaptique[j] = vecteurPoidsSynaptique[j] + ((float) facteurCorrection * erreursLocales[i] * x[i][j]);
                }
            }

            // évalution des sorties puis de l'erreur quadratique
            double[] result = CalculateErrMoy(myData, nbreExamplesToIterate, nbCol, sorties, vecteurPoidsSynaptique, x, d, nbErreur);
            erreurMoyenne = result[0];
            nbErreur = (int) result[1];
            nbIter++;
        } while (!(TestSortieBoucle(myData, nbIter, nbIterMax, erreurMoyenne, errLim, nbErreur)));

        System.out.println(erreurMoyenne + "   /   " + errLim);
        System.out.println("NB Iter = " + nbIter);

        myData.SetLearningResult(nbIter, nbErreur, erreurMoyenne, vecteurPoidsSynaptique);
    }


    public static boolean TestSortieBoucle(DataToUse myData, int nbIter, int nbIterMax, double errMoy, double errLim, int nbErreur) {
        int cas = myData.getCas();
        // Les cas sont ajouté ici en fonction du cours, et non selon une classification logique
        if ((cas == 211) || (cas == 417) || (cas == 301) || (cas == 305)) {
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
        }
        errMoy = (errMoy / (2 * k));

        rep[0] = errMoy;
        rep[1] = nbErreur;
        return rep;
    }


}
 