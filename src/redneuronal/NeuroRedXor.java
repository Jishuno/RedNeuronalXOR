/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redneuronal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Jonatan
 */
public class NeuroRedXor {

    double inp1, inp2;
    String Result;
    

    public NeuroRedXor(double inp1, double inp2) {
        this.inp1 = inp1;
        this.inp2 = inp2;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public double getInp1() {
        return inp1;
    }

    public void setInp1(double inp1) {
        this.inp1 = inp1;
    }

    public double getInp2() {
        return inp2;
    }

    public void setInp2(double inp2) {
        this.inp2 = inp2;
    }

    public void CalcularRed(double input1, double input2) throws IOException {

//        double[][] XOR = {
//            {1, 1, 0}, {1, 0, 1}, {0, 1, 1}, {0, 0, 0}
//        };
        
        double[][] XOR = CalcXor();
        double P1x1;
        double P2x1;
        double P1x2;
        double P2x2;
        double P1Y1;
        double P1Y2;
        double BiasN1;
        double BiasN2;
        double BiasN3;

        double Apend = 0.9;

        double errorD1 = 0;
        double errorD2 = 0;
        double errorD3 = 0;
        double y1 = 0;
        double y2 = 0;
        double y3 = 0;
        int iteraciones = 1;
        int Resulta = 0;

        y1 = 0;
        y2 = 0;
        y3 = 0;
        errorD1 = 0;
        errorD2 = 0;
        errorD3 = 0;
        iteraciones = 0;

        //Generación de los pesos
        P1x1 = new Random().nextDouble();
        P2x1 = new Random().nextDouble();
        P1x2 = new Random().nextDouble();
        P2x2 = new Random().nextDouble();
        P1Y1 = new Random().nextDouble();
        P1Y2 = new Random().nextDouble();
        BiasN1 = new Random().nextDouble();
        BiasN2 = new Random().nextDouble();
        BiasN3 = new Random().nextDouble();
        
        
        

        while (iteraciones <= 30000) {

            //Calculo del resultado de los Nodos de la capa oculta.
            y1 = (input1 * P1x1) + (input2 * P1x2) + (1 * BiasN1);
            y2 = (input1 * P2x1) + (input2 * P2x2) + (1 * BiasN2);
            
            //Implementa la función de activación
            y1 = 1.0 / (1 + Math.pow(Math.E, (-1) * y1));
            y2 = 1.0 / (1 + Math.pow(Math.E, (-1) * y2));
            
            //Calcula el resultado de la neurona de salida
            y3 = (y1 * P1Y1) + (y2 * P1Y2) + (1 * BiasN3);
            
            //Implementa la función de activación
            y3 = 1.0 / (1 + Math.pow(Math.E, (-1) * y3));

            int ju = 0;
            while (ju <= 3) {

                if (input1 == XOR[ju][0] && input2 == XOR[ju][1]) {
                    //Calcula el error del resultado del nodo de salida.
                    errorD3 = (y3 * (1 - y3) * (XOR[ju][2] - y3));
                    
                    //Ajusta los pesos de la neurona de salida.
                    
                    
                    P1Y1 = P1Y1 + (Apend * errorD3 * y1);
                    P1Y2 = P1Y2 + (Apend * errorD3 * y2);
                    BiasN3 = BiasN3 + (errorD3);
                    
                    //Calcula el error de las neuronas de la capa oculta
                    errorD1 = (y1 * (1 - y1)) * errorD3 - P1Y1;
                    errorD2 = (y2 * (1 - y2)) * errorD3 - P1Y2;
                    
                    //Ajusta los pesos de la primera neurona 
                    P1x1 = P1x1 + (Apend * errorD1 * XOR[ju][0]);
                    P1x2 = P1x2 + (Apend * errorD1 * XOR[ju][1]);
                    BiasN1 = BiasN1 + errorD1;
                    //Ajusta los pesos de la segunda neurona
                    P2x1 = P2x1 + (Apend * errorD2 * XOR[ju][0]);
                    P2x2 = P2x2 + (Apend * errorD2 * XOR[ju][1]);
                    BiasN2 = BiasN2 + errorD2;
                }
                ju++;
            }
            
            iteraciones++;

        }
        //Almacena el resultado como una cadena.
        int ju = 0;
        while (ju <= 3) {
            if (input1 == XOR[ju][0] && input2 == XOR[ju][1]) {
                this.Result = "\n" + (int) XOR[ju][0] + "\tXOR\t" + (int) XOR[ju][1] + " = " + (int) XOR[ju][2] + "\tCalculado\t" + y3;
            }
            ju++;
        }
    }

    public double[][] CalcXor() throws IOException {
        
        //Proceso de lectura del archivo plano para los datos de entrenamiento.
        String sCadena;
        double[][] XOR = new double[4][3];
        try {
            File fichero = new File("C:\\Users\\Jonat\\Documents\\NetBeansProjects\\RedNeuronalTest\\src\\redneuronal\\datos.txt");
            FileReader fr = new FileReader(fichero);
            BufferedReader bf = new BufferedReader(fr);
            double ayu = 0;
            while ((sCadena = bf.readLine()) != null) {
                ayu = Double.parseDouble(bf.readLine());
                XOR[0][0] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[0][1] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[0][2] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[1][0] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[1][1] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[1][2] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[2][0] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[2][1] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[2][2] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[3][0] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[3][1] = ayu;
                ayu = Double.parseDouble(bf.readLine());
                XOR[3][2] = ayu;
            }
            bf.close();
        } catch (FileNotFoundException e) {
        }
        

        return XOR;
    }
}
