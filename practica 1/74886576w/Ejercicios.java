/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Nicolás
 */
public class Ejercicios {
    ArrayList<Character> a=new ArrayList<Character>();
    Alfabeto alfa;
    ArrayList<Float> p=new ArrayList<Float>();
    Fuente f;
    CodeBook cb;
    SimbolosCodificados sc;
    long timei,timef;
    
    public Ejercicios(){
        
    }
    
    private void inicializa(){
        a=new ArrayList<Character>();
        p=new ArrayList<Float>();
    }
    
    public void ejercicio8(){        
        inicializa();
        System.out.println("Ejercicio 8:");
        a.add('a');
        p.add(0.25f);
        a.add('b');
        p.add(0.10f);
        a.add('c');
        p.add(0.15f);
        a.add('d');
        p.add(0.05f);
        a.add('e');
        p.add(0.20f);
        a.add('f');
        p.add(0.25f);
        alfa=new Alfabeto(a);
        f = new Fuente(alfa,p);
        System.out.println(f);
        System.out.println("Codificacion normal:");
        cb=f.getCodificacion();
        System.out.println(cb);
        System.out.println("Longitud de palabra media: "+f.longitudMedia(cb));
        System.out.println("");
        System.out.println("Codificacion Huffman:");
        cb=f.getOptimalCodificacion();
        System.out.println(cb);
        System.out.println("Longitud de palabra media: "+f.longitudMedia(cb));
        System.out.println("");
        
        sc=f.getSimbolosCodificadosOptimos(6);
        System.out.println(sc);
        System.out.println("");
    }
    
    public void ejercicio9(){
        inicializa();
        System.out.println("Ejercicio 9:");
        a.add('a');
        p.add(0.5f);
        a.add('b');
        p.add(0.3f);
        a.add('c');
        p.add(0.2f);
        alfa=new Alfabeto(a);
        f = new Fuente(alfa,p);
        System.out.println(f);
        f.setAlfabetoCodificación(3);
        System.out.println("Codificacion normal:");
        cb=f.getCodificacion();
        System.out.println(cb);
        System.out.println("Longitud de palabra media: "+f.longitudMedia(cb));
        System.out.println("");
        System.out.println("Codificacion Huffman:");
        
        
        Calendar calendario = new GregorianCalendar();
        System.out.println("Start: " + calendario.get(Calendar.HOUR) + ":" +
                calendario.get(Calendar.MINUTE) + ":" + calendario.get(Calendar.SECOND) +
                ":" + calendario.get(Calendar.MILLISECOND));  
        
        timei=System.currentTimeMillis();
        cb=f.getOptimalCodificacion();
        timef=System.currentTimeMillis();
        
        Calendar calendario2 = new GregorianCalendar();
        System.out.println("End: " + calendario2.get(Calendar.HOUR) + ":" +
                    calendario2.get(Calendar.MINUTE) + ":" + calendario2.get(Calendar.SECOND) +
                    ":" + calendario2.get(Calendar.MILLISECOND));

        long diff = calendario2.getTimeInMillis() - calendario.getTimeInMillis();
        Calendar diferencia = new GregorianCalendar();
        diferencia.setTimeInMillis(diff);
        System.out.println("Time: " + diferencia.get(Calendar.MINUTE) + "min. " +
                    diferencia.get(Calendar.SECOND) + "sec. " + diferencia.get(Calendar.MILLISECOND) + "millisec."); 
        
        
        System.out.println("Tiempo tardado: "+(timef-timei));
        System.out.println("Tiempo tardado: "+timef+" - "+timei);
        //System.out.println("Tiempo tardado: "+(System.currentTimeMillis()-time));
        System.out.println(cb);
        System.out.println("Longitud de palabra media: "+f.longitudMedia(cb));
        System.out.println("");
    }
    
    public void ejercicio11(){
        inicializa();
        System.out.println("Ejercicio 11:");
        a.add('a');
        p.add(0.4f);
        a.add('b');
        p.add(0.3f);
        a.add('c');
        p.add(0.2f);
        a.add('d');
        p.add(0.1f);
        alfa=new Alfabeto(a);
        f = new Fuente(alfa,p);
        System.out.println(f);
        //
        ArrayList<Character> lc=new ArrayList<Character>();
        lc.add('x');
        lc.add('y');
        Alfabeto alf=new Alfabeto(lc);
        f.setAlfabetoCodificacion(alf);
        //
        System.out.println("Codificacion normal:");
        cb=f.getCodificacion();
        System.out.println(cb);
        System.out.println("Longitud de palabra media: "+f.longitudMedia(cb));
        System.out.println("");
        System.out.println("Codificacion Huffman:");
        cb=f.getOptimalCodificacion();
        System.out.println(cb);
        System.out.println("Longitud de palabra media: "+f.longitudMedia(cb));
        System.out.println("");
    }
    
    public void ejercicioLargo(){
        inicializa();
        System.out.println("Ejercicio Largo:");
        float r=1;
        a.add('a');
        p.add(0.1253f);
        r=r-0.1253f;
        a.add('b');
        p.add(0.0142f);
        r=r-0.0142f;
        a.add('c');
        p.add(0.0468f);
        r=r-0.0468f;
        a.add('d');
        p.add(0.0586f);
        r=r-0.0586f;
        a.add('e');
        p.add(0.1368f);
        r=r-0.1368f;
        a.add('f');
        p.add(0.0069f);
        r=r-0.0069f;
        a.add('g');
        p.add(0.0101f);
        r=r-0.0101f;
        a.add('h');
        p.add(0.0070f);
        r=r-0.0070f;
        a.add('i');
        p.add(0.0625f);
        r=r-0.0625f;
        a.add('j');
        p.add(0.0044f);
        r=r-0.0044f;
        a.add('k');
        p.add(0.0001f);
        r=r-0.0001f;
        
        a.add('q');
        p.add(0.0088f);
        r=r-0.0088f;
        a.add('r');
        p.add(0.0687f);
        r=r-0.0687f;
        a.add('s');
        p.add(0.0798f);
        r=r-0.0798f;
        a.add('t');
        p.add(0.0463f);
        r=r-0.0463f;
        a.add('u');
        p.add(0.0393f);
        r=r-0.0393f;
        a.add('v');
        p.add(0.0090f);
        r=r-0.0090f;
        a.add('w');
        p.add(0.0002f);
        r=r-0.0002f;
        a.add('x');
        p.add(0.0022f);
        r=r-0.0022f;
        a.add('y');
        p.add(0.0090f);
        r=r-0.0090f;
        a.add('z');
        p.add(0.0052f);
        r=r-0.0052f;
        
        a.add('l');
        p.add(2*r/10);
        a.add('m');
        p.add(1.5f*r/10);
        a.add('n');
        p.add(2*r/10);
        a.add('o');
        p.add(3*r/10);
        a.add('p');
        p.add(1.5f*r/10);
        
        alfa=new Alfabeto(a);
        f = new Fuente(alfa,p);
        System.out.println(f);
        
        f.setAlfabetoCodificación(2);
        //f.setAlfabetoCodificación(10);
        
        r=0;
        for (float n:p)
            r+=n;
        System.out.println(r+" - "+p.size());
        
        System.out.println("Codificacion normal:");
        cb=f.getCodificacion();
        System.out.println(cb);
        System.out.println("Longitud de palabra media: "+f.longitudMedia(cb));
        System.out.println("");
        System.out.println("Codificacion Huffman:");
        
        
        Calendar calendario = new GregorianCalendar();
        System.out.println("Start: " + calendario.get(Calendar.HOUR) + ":" +
                calendario.get(Calendar.MINUTE) + ":" + calendario.get(Calendar.SECOND) +
                ":" + calendario.get(Calendar.MILLISECOND));  
        
        timei=System.currentTimeMillis();
        cb=f.getOptimalCodificacion();
        timef=System.currentTimeMillis();
        
        Calendar calendario2 = new GregorianCalendar();
        System.out.println("End: " + calendario2.get(Calendar.HOUR) + ":" +
                    calendario2.get(Calendar.MINUTE) + ":" + calendario2.get(Calendar.SECOND) +
                    ":" + calendario2.get(Calendar.MILLISECOND));

        
        //System.out.println("Tiempo tardado: "+(System.currentTimeMillis()-time));
        System.out.println(cb);
        
        System.out.println("Longitud de palabra media: "+f.longitudMedia(cb));
        System.out.println("");
        
        long diff = calendario2.getTimeInMillis() - calendario.getTimeInMillis();
        Calendar diferencia = new GregorianCalendar();
        diferencia.setTimeInMillis(diff);
        System.out.println("Time: " + diferencia.get(Calendar.MINUTE) + "min. " +
                    diferencia.get(Calendar.SECOND) + "sec. " + diferencia.get(Calendar.MILLISECOND) + "millisec."); 
        
        
        System.out.println("Tiempo tardado: "+(timef-timei));
        System.out.println("Tiempo tardado: "+timef+" - "+timei);
    }
    
    public void pruebasVarias(){
        ArrayList<Character> a1 = new ArrayList<Character>();
        a1.add('a');
        a1.add('b');
        //a1.add('c');
        a1.add('d');
        a1.add('e');
        
        System.out.println(a1);
        a1.add(2, 'c');
        System.out.println(a1);
        
        Alfabeto alfa1 = new Alfabeto(a1);
        System.out.println("Alfabeto1: "+alfa1);
        
        
        Fuente f1 = new Fuente(alfa1);
        System.out.println("Tamaño alfabeto Fuente1: "+f1.getTamañoAlfabeto());
        System.out.println("Fuente1: \n"+f1);
        
        ArrayList<Float> prob1 = new ArrayList<Float>();
        prob1.add(0.25f);
        prob1.add(0.15f);
        prob1.add(0.2f);
        prob1.add(0.1f);
        prob1.add(0.3f);
        
        Fuente f2 = new Fuente(alfa1,prob1);
        System.out.println("Tamaño alfabeto Fuente2: "+f2.getTamañoAlfabeto());
        System.out.println("Fuente2: \n"+f2);
        
        System.out.println("getsimbolos(3) de fuente 2:");
        System.out.println(f2.getSimbolos(3));
        
        System.out.println("");
        System.out.println("Probando cosas:");
        
        ArrayList<Character> a2 = new ArrayList<Character>();
        a2.add('a');
        a2.add('e');
        a2.add('i');
        a2.add('o');
        a2.add('u');
        Alfabeto alfa2 = new Alfabeto(a2);
        ArrayList<Float> prob2 = new ArrayList<Float>();
        prob2.add(0.4f);
        prob2.add(0.2f);
        prob2.add(0.2f);
        prob2.add(0.1f);
        prob2.add(0.1f);
        
        Fuente f3 = new Fuente(alfa2,prob2);
        System.out.println("Tamaño alfabeto Fuente 3: "+f3.getTamañoAlfabeto());
        System.out.println("Fuente 3: \n"+f3);
        System.out.println("");
        System.out.println("La entropia deberia ser 2.12");
        System.out.println("Y segun este bicho es "+f3.entropia());
        System.out.println("");
        System.out.println("Además, Y deberia ser [2,3,3,4,4]");
        System.out.println("Y segun la cosa esta es "+f3.obtenerYi());
        System.out.println("");
        System.out.println("por otra parte, N deberia ser [0,1,2,2,0]");
        System.out.println("Y segun la cosa esta es "+f3.obtenerNi(f3.obtenerYi()));
        System.out.println("");
        
        
        System.out.println(f3.getCodificacion());
        SimbolosCodificados sc = f3.getSimbolosCodificados(5);
        System.out.println("Simbolos Org: "+sc.getSimbolos());
        System.out.println("Simbolos Cod: "+sc.getSimbolosCodificados());
        
        System.out.println("");
        
        ArrayList<ArbolCod> l = new ArrayList<ArbolCod>();
        l.add(new ArbolCod(0.25f));
        l.add(new ArbolCod(0.2f));
        l.add(new ArbolCod(0.15f));
        l.add(new ArbolCod(0.10f));
        
        System.out.println(f3.getOptimalCodificacion());
        System.out.println("L = "+f3.longitudMedia(f3.getOptimalCodificacion()));
    }
    
}
