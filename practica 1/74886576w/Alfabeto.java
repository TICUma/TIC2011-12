/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolás
 */
public class Alfabeto {
    ArrayList<Character> alfabeto;
    
    public Alfabeto(){
        alfabeto = new ArrayList<Character>();
    }
    
    public Alfabeto(ArrayList<Character> lista){
        alfabeto = lista;
    }
    
    public void setAlfabeto(ArrayList<Character> lista){
        alfabeto = lista;
    }
    
    public ArrayList<Character> getAlfabeto(){
        return alfabeto;
    }
    
    public Character getN (int n){
        return alfabeto.get(n);
    }
    
    public Character getI (int i){
        return alfabeto.get(i);
    }
    
    public ArrayList<Float> sortBy(List<Float> list){
        Character aux;
        Float temp;
        int mayor;
        ArrayList<Float> p=(ArrayList<Float>) list;
        //System.out.println("Sortby: "+p);
        /*
        for (int i=0;i<alfabeto.size();i++){
            mayor=buscaMayor(i,p);
            System.out.println("El mayor es el elemento "+mayor+" con valor "+p.get(mayor));
            alfabeto.add(0,alfabeto.get(mayor));
            alfabeto.remove(mayor+1);
            p.add(0,p.get(mayor));
            p.remove(mayor+1);
        }
        */
        
        for (int i=0;i<alfabeto.size();i++){
            mayor=buscaMayor(i,p);
            //System.out.println("El mayor es el elemento "+mayor+" con valor "+p.get(mayor));
            aux=alfabeto.get(i);
            alfabeto.set(i, alfabeto.get(mayor));
            alfabeto.set(mayor, aux);
            temp=p.get(i);
            p.set(i, p.get(mayor));
            p.set(mayor, temp);
        }
        
        return p;
    }
    
    public int buscaMayor(int i,ArrayList<Float> lista){
        int res=i;
        //Number menor=lista.get(res);
        float mayor=lista.get(res);
        i++;
        while (i<lista.size()){
            //if (lista.get(i).floatValue()<menor.floatValue()){
            if (lista.get(i)>mayor){
                mayor=lista.get(i);
                res=i;
            }
            i++;
        }
        return res;
    }
    
    public int size(){
        return alfabeto.size();
    }
    
    @Override
    public String toString(){
        return this.alfabeto.toString();
    }
    
    
}
