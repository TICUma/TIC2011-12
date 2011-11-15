/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Nicolás
 */
public class Fuente {
    private Alfabeto alfabeto;
    private List<Float> probabilidades = new ArrayList();
    private int base=2;
    Alfabeto alfabetoD=null;

    public Fuente(Alfabeto alfabeto, List probabilidades) {
        this.alfabeto=alfabeto;
        if (probabilidades.size()==alfabeto.size()){
            this.probabilidades=this.alfabeto.sortBy(probabilidades);
        }
        else{
            System.out.println("La cardinalidad de ambas listas no coinciden. Se utilizará una distribución equiprobable");
            ArrayList<Float> prob = new ArrayList<Float>();
            float p = 1/(float)alfabeto.size();
            for (int i=0;i<alfabeto.size();i++)
                prob.add(p);
            this.probabilidades=prob;
        }
        
    }

    public Fuente(Alfabeto alfabeto) {
        ArrayList<Float> prob = new ArrayList<Float>();
        
        this.alfabeto=alfabeto;
        float p = 1/(float)alfabeto.size();
        for (int i=0;i<alfabeto.size();i++)
            prob.add(p);
        
        probabilidades=prob;
    }
    
    /*
     * Devuelve n símbolos aleatorios del alfabeto, en función de su probabilidad.
     */
    public List getSimbolos(int numSimbolos){
        ArrayList<Character> res = new ArrayList<Character>();
        Random r = new Random();
        float n,acum=0;
        int j;
        
        for (int i=0;i<numSimbolos;i++){
            n=r.nextFloat();
            j=0;
            acum=0;
            while ((acum<n)&&(j<probabilidades.size())){
                acum+=probabilidades.get(j);
                j++;
            }
            res.add(alfabeto.getI(j-1));
        }
        return res;
    }
    
    public float entropia(){
        float res=0;
        
        for (float i:probabilidades){
            res+=i*(Math.log((double) (1f/i))/Math.log(base));
        } 
        return res;
    }
    
    /*
     * Devuelve un CodeBook obtenido mediante la regla Shannon-Fano
     */
    public CodeBook getCodificacion(){
        CodeBook res=new CodeBook();
        
        //Se obtiene los valores ni
        ArrayList<Integer> n=obtenerNi(obtenerYi());
        
        //Se asigna a cada carácter un código
        ArrayList<String> candidatos;
        int i=0;
        int cont_alfa=0;
        int nd=0;
        
        // Se comprueba para cada ni si es mayor que 0, en cuyo caso, se generan
        // los posibles códigos de longitud i que quedan libres y se asignan.
        while (i<n.size()){
            nd=nd*base;
            int cont=n.get(i);
            if (cont>0){
                candidatos=generaCod(i+1,nd);
                int cod=0;
                while (cont>0){
                    res.add(alfabeto.getN(cont_alfa), candidatos.get(cod));
                    nd++;
                    cont_alfa++;
                    cod++;
                    cont--;
                }
            }
            i++;
        } 
        return res;
    }
    
    /*
     * Devuelve dos listas de caracteres. La primera contiene n símbolos del
     * alfabeto generados al azar en función de la probabilidad asociada.
     * La segunda contiene el resultado de codificar los símbolos de la primera
     * lista mediante una codeficación obtenida mediante Shannon-Fano.
     */
    public SimbolosCodificados getSimbolosCodificados(int n){
        ArrayList<Character> simb=(ArrayList<Character>) getSimbolos(n);
        String sc;
        ArrayList<Character> simbcod=new ArrayList<Character>();
        CodeBook book=getCodificacion();
        
        for (int i=0;i<n;i++){
            sc=book.get(simb.get(i));
            for (int j=0;j<sc.length();j++)
                simbcod.add(sc.charAt(j));
        }
        return new SimbolosCodificados(simb,simbcod);
    }
    
    /*
     * Devuelve dos listas de caracteres. La primera contiene n símbolos del
     * alfabeto generados al azar en función de la probabilidad asociada.
     * La segunda contiene el resultado de codificar los símbolos de la primera
     * lista mediante una codeficación obtenida mediante Huffman.
     */
    public SimbolosCodificados getSimbolosCodificadosOptimos(int n){
        ArrayList<Character> simb=(ArrayList<Character>) getSimbolos(n);
        String sc;
        ArrayList<Character> simbcod=new ArrayList<Character>();
        CodeBook book=getOptimalCodificacion();
        
        for (int i=0;i<n;i++){
            sc=book.get(simb.get(i));
            for (int j=0;j<sc.length();j++)
                simbcod.add(sc.charAt(j));
        }
        return new SimbolosCodificados(simb,simbcod);
    }
    
    /*
     * Devuelve una lista con los códigos de longitud l que es posible asignar
     */
    private ArrayList<String> generaCod(int l,int cd){
        ArrayList<String> res=new ArrayList<String>();
        ArrayList<String> temp=new ArrayList<String>();
        for (int i=0;i<base;i++)
            if (alfabetoD==null)
                res.add(""+i);
            else
                res.add(""+alfabetoD.getI(i));
        for (int i=l-1;i>0;i--){
            temp=res;
            res=new ArrayList<String>();
            for (String s:temp){
                for (int j=0;j<base;j++)
                    if (alfabetoD==null)
                        res.add(s+j);
                    else
                        res.add(s+alfabetoD.getI(j));
            }
        }
        for (int i=0;i<cd;i++)
            res.remove(0);
        return res;
    }
    
    public int suma(ArrayList<Integer> lista){
        int cont=0;
        for (Integer i:lista)
            cont++;
        return cont;    
    }
    
    /*
     * Calcula los valores Yi y los devuelve en una lista.
     */
    public ArrayList<Integer> obtenerYi(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        int cont;
        
        for (float p:probabilidades){
            cont=0;
            while (Math.pow(base, cont)<1/p)
                cont++;
            res.add(cont);
        }
        return res;
    }
    
    /*
     * Calcula los valores Ni y los devuelve en una lista.
     */
    public ArrayList<Integer> obtenerNi(ArrayList<Integer> listay){
        ArrayList<Integer> res=new ArrayList<Integer>();
        
        for (int i=0;i<listay.size();i++){
            res.add(contar(listay,i+1));
        }
        return res;
    }
    
    
    private int contar(List<Integer> lista,int n){
        int res=0;
        for (int i:lista){
            if (i==n)
                res++;
        }
        return res;
    }
    
    /*
     * Devuelve un CodeBook obtenido mediante el algoritmo Huffman.
     */
    public CodeBook getOptimalCodificacion(){
        ArrayList<ArbolCod> listaA=new ArrayList<ArbolCod>();
        
        // Se inicializan los árboles con las probabilidades.
        for (int i=0;i<probabilidades.size();i++)
            listaA.add(new ArbolCod(alfabeto.getI(i),probabilidades.get(i)));
        
        return codHuffman(listaA);
    }
    
    /*
     * Obtiene el CodeBook del algoritmo de Huffman a partir de la lista de
     * árboles con las probabilidades iniciales de cada símbolo.
     */
    public CodeBook codHuffman(ArrayList<ArbolCod> listaA){
        int temp;
        
        // Mientras tengamos más de un arbol independiente, se buscan los n 
        // elementos menores y se unen.
        while (listaA.size()>1){
            int[] menores;
            if (base==2){
                menores=buscarMenores(listaA);
                if (menores[0]>menores[1]){
                    temp=menores[0];
                    menores[0]=menores[1];
                    menores[1]=temp;
                }
                unirArboles2(listaA,menores[0],menores[1]);
            }
            else {
                menores=ordenarIndices(listaA);
                for (int i=0;i<base;i++){
                    for (int j=i+1;j<base;j++){
                        if ((i<menores.length)&&(j<menores.length)&&(menores[i]>menores[j])){
                            temp=menores[i];
                            menores[i]=menores[j];
                            menores[j]=temp;
                        }
                    }
                }
                unirArboles(listaA,menores);
            }
        }
        return desarrollarArbol(listaA.get(0));
    }
    
    /*
     *  Desarrolla un arbol previamente agrupado (según algoritmo Huffman)
     *  y asigna los códigos, introduciendolos en el CodeBook que devuelve.
     */
    private CodeBook desarrollarArbol(ArbolCod arbol){
        CodeBook cb = new CodeBook();
        desarrollarAux(cb,arbol,"");
        return cb;
    }
     
    private void desarrollarAux(CodeBook cb,ArbolCod a,String cod){
        if (a.esHoja()){
            cb.add(a.getSimbolo(), cod);
        }
        else {
            for (int j=0;j<a.getHijos().size();j++){
                if (alfabetoD==null)
                    desarrollarAux(cb,a.getHijos().get(j),cod+j);
                else
                    desarrollarAux(cb,a.getHijos().get(j),cod+alfabetoD.getI(j));
            }
        }        
    }
    
    /*
     * Devuelve una lista con los índices de los elementos de la lista de
     * entrada ordenados.
     */
    private int[] ordenarIndices(ArrayList<ArbolCod> lista){
        int[] res=new int[lista.size()];
        int temp;
        float aux[]=new float[lista.size()];
        float t;
        
        for (int i=0;i<res.length;i++)
            res[i]=i;
        for (int i=0;i<aux.length;i++)
            aux[i]=lista.get(i).getProbabilidad();
        for (int i=0;i<lista.size();i++){
            for (int j=i+1;j<lista.size();j++){
                if (aux[i]>aux[j]){  
                    temp=res[i];
                    res[i]=res[j];
                    res[j]=temp;
                    
                    t=aux[i];
                    aux[i]=aux[j];
                    aux[j]=t;
                }
            }
        }
        return res;
    }
    
    /*
     * Devuelve los indices de los dos menores elementos de la lista.
     */
    private int[] buscarMenores(ArrayList<ArbolCod> lista){
        int temp;
        int m1=1;
        int m2=0;
        float vm1,vm2,aux;
        
        vm1=lista.get(m1).getProbabilidad();
        vm2=lista.get(m2).getProbabilidad();
        if (vm1<=vm2){
            temp=m1;
            m1=m2;
            m2=temp;
        }   
        for (int i=2;i<lista.size();i++){
            vm1=lista.get(m1).getProbabilidad();
            vm2=lista.get(m2).getProbabilidad();
            aux=lista.get(i).getProbabilidad();
            if (aux<=vm2){
                m1=m2;
                m2=i;
            } 
            else if (aux<=vm1){
                m1=i;
            }
        }
        int[] res=new int[2];
        res[0]=m1;
        res[1]=m2;
        return res;
    }
    
    /*
     * Combina varios árboles en un único nodo, dejando los árboles originales
     * como hijos del nuevo nodo.
     */
    private void unirArboles(ArrayList<ArbolCod> lista, int[] menores){
        List<ArbolCod> aux=new ArrayList<ArbolCod>();
        ArbolCod borrar=new ArbolCod();
        
        for (int i=0;i<base;i++){
            if (i<menores.length)
                aux.add(lista.get(menores[i]));
        }       
        float f=lista.get(menores[0]).getProbabilidad();
        for (int i=1;i<base;i++){
            if (i<lista.size())
                f+=lista.get(menores[i]).getProbabilidad();
        }
        lista.set(menores[0], new ArbolCod(f));        
        lista.get(menores[0]).setHijos(aux);    
        for (int i=menores[0]+1;i<menores[0]+base;i++)
            if (i<lista.size())
                lista.set(i, borrar);
        
        while (lista.remove(borrar));
    }
    
    /*
     * Combina dos árboles en un único nodo, dejando los árboles originales
     * como hijos del nuevo nodo.
     */
    private void unirArboles2(ArrayList<ArbolCod> lista, int m1, int m2){
        List<ArbolCod> aux=new ArrayList<ArbolCod>();
       
        aux.add(lista.get(m1));
        aux.add(lista.get(m2));        
        lista.set(m1, new ArbolCod(lista.get(m1).getProbabilidad()+lista.get(m2).getProbabilidad()));       
        lista.get(m1).setHijos(aux);
        lista.remove(m2);
    }
    
    public float longitudMedia(CodeBook cb){
        float res=0;
        for (int i=0;i<probabilidades.size();i++)
            res+=probabilidades.get(i)*cb.get(alfabeto.getI(i)).length();
         
        return res;
    }
    
    public int getTamañoAlfabeto(){
        return alfabeto.size();
    }
    
    public void setDistribucion(List<Float> p){
        if (p.size()==alfabeto.size())
            this.probabilidades=this.alfabeto.sortBy(p);
        else
            System.out.println("Error: La cardinalidad debe ser la misma que la del alfabeto ("+alfabeto.size()+")");
    }
    
    public List<Float> getProbabilidades(){
        return probabilidades;
    }
    
    public void setAlfabetoCodificacion(Alfabeto a){
        base=a.size();
        alfabetoD=a;
    }
    
    public void setAlfabetoCodificación(int base){
        this.base=base;
        alfabetoD=null;
    }
    
    @Override
    public String toString(){
        return alfabeto.toString()+"\n"+probabilidades.toString();
    }
    
}
