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
public class ArbolCod {
    Float probabilidad=0f;
    String cad="";
    Character simbolo;
    ArrayList<ArbolCod> hijos=new ArrayList<ArbolCod>();
    ArbolCod padre=null;
    
    public ArbolCod(){
        
    }
    
    public ArbolCod(Float f){
        probabilidad=f;
    }
    
    public ArbolCod(String s){
        cad=s;
    }
    
    public ArbolCod(float f,String s){
        probabilidad=f;
        cad=s;
    }
    
    public ArbolCod(Character c,float f){
        probabilidad=f;
        simbolo=c;
    }
    
    public boolean esHoja(){
        return hijos.isEmpty();
    }
    
    // gets y sets
    public void setProbabilidad(float f){
        probabilidad=f;
    }
    
    public Float getProbabilidad(){
        return probabilidad;
    }
    
    public void setCad(String s){
        cad=s;
    }
    
    public String getCad(){
        return cad;
    }
    
    public void setPadre(ArbolCod p){
        padre=p;
    }
    
    public ArbolCod getPadre(){
        return padre;
    }
    
    public void setSimbolo(Character c){
        simbolo=c;
    }
    
    public Character getSimbolo(){
        return simbolo;
    }
    
    public void setHijos(List<ArbolCod> h){
        hijos=(ArrayList<ArbolCod>) h;
    }
    
    public List<ArbolCod> getHijos(){
        return hijos;
    }
    /*
    public String toString(){
        String res="[ "+this.probabilidad;
        for (ArbolCod a:hijos){
            res=res+a.toString();
        }
        res = res+" ]";
        return res;
    }
    */
    public String toString(){
        if (esHoja())
            return ""+probabilidad;
        else if (hijos.size()==2)
            return "["+hijos.get(0).toString()+ "("+probabilidad+")"+hijos.get(1)+"]";
        // Esta representación no es buena. Buscar una mejor si es posible.            
        else {
            String res="["+probabilidad+" ";
            for (ArbolCod a:hijos)
                    res=res+a.toString()+" ";
            res=res+" ]";
            return res;
        }                
    }

}
