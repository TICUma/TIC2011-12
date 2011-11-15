package practica1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Fuente {
    private Alfabeto alfabetoOrigen, alfabetoDestino;
    private List<Double> probabilidades;
    private CodeBook shannon;
    private CodeBook huffman;
    private boolean actualizado = false;

    public Fuente(Alfabeto alfabeto, List probabilidades) {
        alfabetoOrigen = alfabeto;
        this.probabilidades = probabilidades;
        alfabetoDestino = new AlfabetoBinario();
        construirShannon();
        construirHuffman();
    }

    public Fuente(Alfabeto alfabeto) {
        alfabetoOrigen = alfabeto;
        alfabetoDestino = new AlfabetoBinario();
        this.probabilidades = new ArrayList<Double>(alfabeto.size());
        double equiTamanyo = 1.0 / alfabeto.size();
        for (int i = 0; i < alfabeto.size(); i++) {
            probabilidades.add(equiTamanyo);
        }
        construirShannon();
        construirHuffman();
    }

    /**
     * Metodo que genera la lista de sinbolos
     * del alfabeto segun sus probabilidades
     * 
     * @param numeroSimbolos
     * @return
     */
    public List getSimbolos(int numeroSimbolos) {
        ArrayList<Character> resultado = new ArrayList<Character>(numeroSimbolos);
        Character[] alfabetoArray = alfabetoOrigen.toArray();
        double aleatorio;
        int j;
        double acumulada;

        for (int i = 0; i < numeroSimbolos; i++) {
            aleatorio = Math.random();
            j = 0;
            acumulada = probabilidades.get(0);
            while (j < probabilidades.size() - 1 && aleatorio >= acumulada) {
                j++;
                acumulada += probabilidades.get(j);
            }
            resultado.add(alfabetoArray[j]);
        }

        return resultado;
    }

    /**
     * Metodo encargado de obtener un codebook binario
     * segun Shanon 
     * 
     * @return
     */
    public CodeBook getCodificacion() {
        if (!actualizado) {
            construirShannon();
            construirHuffman();
        }
        return shannon;
    }

    /**
     * Metodo encargado de obtener un codebook al
     * alfabeto binario segun Huffman
     * 
     * @param alfabetoDestino
     * @return
     */
    public CodeBook getOptimalCodificacion() {
        if (!actualizado) {
            construirShannon();
            construirHuffman();
        }
        return huffman;
    }

    /**
     * Metodo encargado de devolver una lista de 
     * numSimbolos del alfabeto origen
     * y sus correspondientes codificaciones binarias según Shannon-Fano.
     * 
     * @param numSimbolos
     * @return
     */
    public SimbolosCodificados getSimbolosCodificados(int numSimbolos) {
        return getSimbolosCodificados(numSimbolos,shannon);
    }

    /**
     * Metodo encargado de devolver una lista de 
     * numSimbolos del alfabeto origen
     * y sus correspondientes codificaciones binarias en el
     * alfabeto destino segun hufman
     * 
     * @param numSimbolos
     * @return
     */
    public SimbolosCodificados getSimbolosCodificadosOptimos(int numSimbolos) {
        return getSimbolosCodificados(numSimbolos,huffman);
    }
    
    public double entropia(){
        double resultado = 0.0;
        
        for (Double probabilidad : probabilidades) {
            resultado += probabilidad * (Math.log10(1.0/probabilidad)/Math.log10(alfabetoDestino.size()));
        }
        
        return resultado;
    }
    
    public double longitudMedia(){
        return longitudMedia(probabilidades,shannon.codes());
    }
    
    public double longitudMediaOptima(){
        return longitudMedia(probabilidades,huffman.codes());
    }
    
    //======================metodos auxiliares===================
    private void construirShannon() {
        actualizado = true;

        int base = alfabetoDestino.size();
        int[] cant = new int[probabilidades.size()];
        double fraccion;
        int j;
        

        for (int i = 0; i < probabilidades.size(); i++) {
            fraccion = 1 / probabilidades.get(i);
            j = 1;
            while (Math.pow(base, j) < fraccion) {
                j++;
            }
            cant[i] = j;
        }
        
        shannon = new CodeBook();
        Character[] alfabetoArray = alfabetoOrigen.toArray();
        ArbolShannon arbol = new ArbolShannon(alfabetoDestino);
        for (int i = 0; i < alfabetoOrigen.size(); i++) {
            shannon.add(alfabetoArray[i], arbol.getLeafOfSize(cant[i]));
        }
    }

    private void construirHuffman() {
        actualizado = true;
        
        ArbolHuffman arbol = new ArbolHuffman(alfabetoOrigen, alfabetoDestino, probabilidades);
        
        huffman = arbol.getCodificacion();
    }
    
    private SimbolosCodificados getSimbolosCodificados(int numSimbolos, CodeBook codebook){
        SimbolosCodificados resultado;
        
        List simbolos = getSimbolos(numSimbolos);
        List codificaciones = new LinkedList();
        
        for (Object simbolo : simbolos) {
            codificaciones.add(codebook.get(((Character) simbolo).charValue()));
        }
        resultado = new SimbolosCodificados(simbolos, codificaciones);
        return resultado;
    }
    
    private double longitudMedia(List<Double> probs, List<String> cadenas){
        double resultado = 0.0;
        
        for (int i = 0; i < probs.size(); i++) {
            resultado += cadenas.get(i).length()*probs.get(i);
        }
        
        return resultado;
    }


    //====================get and set========================
    public Alfabeto getAlfabeto() {
        return alfabetoOrigen;
    }

    public void setAlfabeto(Alfabeto alfabeto) {
        alfabetoOrigen = alfabeto;
        actualizado = false;
    }

    public void setAlfabetoCodificacion(Alfabeto alfabeto) {
        alfabetoDestino = alfabeto;
        actualizado = false;
    }

    public List getProbabilidades() {
        return probabilidades;
    }

    public void setProbabilidades(List probabilidades) {
        this.probabilidades = probabilidades;
        actualizado = false;
    }
}
