
package tarea.estadistica;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

public class TablaDeFrecuencias {
    public static void main(String[] args) {
        String nombreArchivo = "C:\\Users\\Usuario\\Documents\\yamil\\txt\\numeros1.txt"; // ingresamos archivo aca
        int clases = 7; // cuantas clases hay, la cual nosotros modificamos desde el codigo
        
        ArrayList<Integer> numeros = leerNumeros(nombreArchivo);// Lee los números del archivo y calcular la tabla de frecuencias
        
        if (numeros != null) {
            calcularTablaDeFrecuencias(numeros, clases);
        } else {
            System.out.println("No se pudo leer ningún número del archivo.");
        }
    }
    
    public static ArrayList<Integer> leerNumeros(String nombreArchivo) {//Función para leer los números del archivo
        ArrayList<Integer> numeros = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(nombreArchivo));
            while (scanner.hasNextInt()) {
                numeros.add(scanner.nextInt());
            }
            scanner.close();
            return numeros;
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encuentra.");
            return null;
        }
    }
    
    public static void calcularTablaDeFrecuencias(ArrayList<Integer> numeros, int clases) { // Función para calcular la tabla de frecuencias
        
        int minimo = Collections.min(numeros);// Calculamos el mínimo y máximo de los números
        int maximo = Collections.max(numeros);
        
        System.out.println("// El numero de clases es : " + clases); // Imprimimos información sobre el rango y tamaño del intervalo
        System.out.println("// Maximo: " + maximo);
        System.out.println("// Minimo: " + minimo);
        double rango = (double) (maximo - minimo);
        System.out.println("// El rango es : " + rango);
        double anchoClase = Math.ceil(rango / clases);
        System.out.println("// El tamaño del intervalo es ( sin redondear ) : " + (rango / clases));
        System.out.println("// El tamaño del intervalo es : " + anchoClase);
        
        
        
        //double rango = (double) (maximo - minimo);
        //double anchoClase = Math.ceil(rango / clases);
        
        System.out.println("Li-1 - Li\t\t  xi\t\t  ni\t\t  Ni\t\t  hi (%)\t\t  Hi (%)"); // Imprimimos los encabezados de la tabla de frecuencias
        
        int limiteInferior = minimo;
        int limiteSuperior;
        int frecuenciaAcumulada = 0;
        
        ArrayList<String> histograma = new ArrayList<>();// Almacena líneas del histograma

        for (int i = 0; i < clases; i++) { //  Calculá las frecuencias para cada clase e imprimir la tabla de frecuencias
            limiteSuperior = (int) (limiteInferior + anchoClase);

            double marcaClase = (limiteInferior + limiteSuperior) / 2.0;

            int frecuencia = contarFrecuencia(numeros, limiteInferior, limiteSuperior);
            frecuenciaAcumulada += frecuencia;

            double frecuenciaRelativa = (double) frecuencia * 100 / numeros.size();
            double frecuenciaRelativaAcumulada = (double) frecuenciaAcumulada * 100 / numeros.size();

            // Construí la línea del histograma
            StringBuilder histogramaLinea = new StringBuilder(); // operaciones de concatenación frecuentes
            histogramaLinea.append("[");
            histogramaLinea.append(limiteInferior);
            histogramaLinea.append(" - ");
            histogramaLinea.append(limiteSuperior);
            histogramaLinea.append(") : ");
            for (int j = 0; j < frecuencia; j++) {
                histogramaLinea.append("*");
            }
            histograma.add(histogramaLinea.toString());
            
            // Imprimimos línea de la tabla de frecuencias
            System.out.println("[" + limiteInferior + " - " + limiteSuperior + ")\t  " + marcaClase + "\t\t" + frecuencia + "\t\t" + frecuenciaAcumulada + "\t\t" + frecuenciaRelativa + "\t\t" + frecuenciaRelativaAcumulada);

            limiteInferior = limiteSuperior;
        }

        // Imprimimos el histograma
        System.out.println("\nHistograma:");
        for (String linea : histograma) {
            System.out.println(linea);
        }
    }
        
    public static int contarFrecuencia(ArrayList<Integer> numeros, int limiteInferior, int limiteSuperior) { // esta es una función para contar la frecuencia de números en un rango dado
        int frecuencia = 0;
        for (int numero : numeros) {
            if (numero >= limiteInferior && numero < limiteSuperior) {
                frecuencia++;
            }
        }
        return frecuencia;
    }
}