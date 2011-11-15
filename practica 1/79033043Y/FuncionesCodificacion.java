/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aku
 */
public class  FuncionesCodificacion {
    public static double entropiaProbabilidades(int base, List<Double> probabilidades){
        double res = 0;
        
        for (double pi:probabilidades)
            res += pi*logBaseb(1/pi, base);
        
        return res;
    }
    
    private static double logBaseb (double num, int base){
        return Math.log(num) / Math.log(base);
    }
    
    public static double longitudMedia(List<Integer> longitudCadenas, List<Double> probabilidades){
        double res = 0;
        
        for (int i = 0; i < longitudCadenas.size(); i++)
            res += longitudCadenas.get(i)*probabilidades.get(i);
        
        return res;
    }
    
    public static List<Integer> shannonFano(int base, List<Double> probabilidades){
        List<Integer> longitudes = new ArrayList<Integer>();
        int aux = 0;
 //       System.out.println("Se va a calcular las prob con base "+ base);
        for (double pi:probabilidades){
            while(Math.pow(base, aux) < 1/pi)
                aux++;
            longitudes.add(aux);
            aux = 0;
        }
   //     System.out.println("longitudes  "+ longitudes);
        
        return longitudes;
    }
    
    
    private static int indiceMenorNodo (List<nodoHuff> auxProb){
        int menor = 0;
        for (int i = 0; i < auxProb.size(); i++){
            if (auxProb.get(i).valor < auxProb.get(menor).valor)
                menor = i;
        }
        return menor;
    }
    
    public static List<Integer> huffman(List<Double> probabilidades){
        List<Integer> longitudes = new ArrayList<Integer>();
        List<nodoHuff> auxProb = new ArrayList<nodoHuff>();
        
        // Creo la lista inicial
        for (int i = 0; i < probabilidades.size(); i++){
            auxProb.add(new nodoHuff(probabilidades.get(i),i));
            longitudes.add(0);
        }
 //       System.out.println("PROBABILIDADES:" + probabilidades);
//        System.out.println("LONGITUDES:" + longitudes);
        // Voy actualizando
        while (auxProb.size() > 1){
            // cogemos los dos menores
            int aux1 = indiceMenorNodo(auxProb);
            nodoHuff naux1 = auxProb.get(aux1);
            auxProb.remove(aux1);
            int aux2 = indiceMenorNodo(auxProb);
            nodoHuff naux2 = auxProb.get(aux2);
            auxProb.remove(aux2);
            
            // sumamos la frecuencia (longitudes)
            for (int i = 0; i < naux1.sumadoDe.size(); i++)
                longitudes.set(naux1.sumadoDe.get(i), longitudes.get(naux1.sumadoDe.get(i))+1);
            for (int i = 0; i < naux2.sumadoDe.size(); i++)
                longitudes.set(naux2.sumadoDe.get(i), longitudes.get(naux2.sumadoDe.get(i))+1);
            
            // Metemos el nodo que englobe a los borrados
            auxProb.add(new nodoHuff(naux1.valor + naux2.valor,naux1.sumadoDe,naux2.sumadoDe));
            
  //          System.out.println("LONGITUDES:" + longitudes);
        }
        
        return longitudes;
    }
    
    
}
