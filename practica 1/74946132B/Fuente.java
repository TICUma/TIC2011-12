package practica1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Fuente {
	
	private Alfabeto alfabeto;
	private List<Double> probabilidades = new ArrayList<Double>();
        private Alfabeto alfabetoCod; //binario si no se especifica lo contrario
        
	
	public Fuente(Alfabeto alfabeto, List probabilidades) {
            this.alfabeto=alfabeto;
            this.probabilidades=probabilidades;
            this.alfabetoCod= new AlfabetoBinario();
	}
	
	public Fuente(Alfabeto alfabeto) {
            this.alfabeto=alfabeto;
            //Se supone que tienen la misma probabilidad;
            calculoProbabilidades(alfabeto.getN_simbolos());
            this.alfabetoCod= new AlfabetoBinario();
	}
		
	/**
	 * Metodo que genera la lista de sinbolos
	 * del alfabeto segun sus probabilidades
	 * 
	 * @param numeroSimbolos
	 * @return
	 */
	public List getSimbolos(int numeroSimbolos){
            
            Random rd= new Random(1);
            //cogemos la hora como semilla para generar numeros aleatorios distintos
            rd.setSeed(new Date().getTime()); 
            Double n;
            List<String> cadena = new ArrayList();
            int j;
            
            for(int i=0; i<numeroSimbolos; i++){
                
                
                //Numero aleatorio
                n=(rd.nextInt(100)/100.0);
                
                j=0;
                while(n>=0.0 && j<probabilidades.size())
                {
                    
                    n=n-probabilidades.get(j);
                    j++;
                }
                
                if(n<0.0)
                {
                    cadena.add(alfabeto.getSimbolos().get(j-1).toString());
                }
            
            }
            
            return cadena;
	}	
	
	/**
	 * Metodo encargado de obtener un codebook binario
	 * segun Shanon 
	 * 
	 * @return
	 */
	public CodeBook getCodificacion(){
            
            CodeBook codificacion = new CodeBook();
            
            codificacion= reglaShanonFano();
            
            double l = this.longitudMedia(codificacion);
            System.out.println("Longitud media Shannon-Fano = "+l);
            
            return codificacion;
	}
	
	/**
	 * Metodo encargado de obtener un codebook al
	 * alfabeto binario segun Huffman
	 * 
	 * @param alfabetoDestino
	 * @return
	 */
	public CodeBook getOptimalCodificacion(){
            
            CodeBook codificacion = new CodeBook();
            
            codificacion= reglaHuffman();
            
            double l = this.longitudMedia(codificacion);
            System.out.println("Longitud media Huffman = "+l);
            
            return codificacion;
	}
	
	/**
	 * Metodo encargado de devolver una lista de 
	 * numSimbolos del alfabeto origen
	 * y sus correspondientes codificaciones binarias según Shannon-Fano.
	 * 
	 * @param numSimbolos
	 * @return
	 */
	public SimbolosCodificados getSimbolosCodificados(int numSimbolos){
            
            SimbolosCodificados sc= null;
            
            List<String> loriginal= this.getSimbolos(numSimbolos);
            CodeBook codeBook= this.reglaShanonFano();
            List<String> lcodificada= new ArrayList<String>();
            char[] cadena;
            
            for(int i=0; i<loriginal.size();i++)
            {
                cadena= loriginal.get(i).toCharArray();
                if(cadena.length==1);
                    lcodificada.add(codeBook.get(cadena[0]));
            }
            
            sc= new SimbolosCodificados(loriginal,lcodificada);
            
            return sc;
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
	public SimbolosCodificados getSimbolosCodificadosOptimos(int numSimbolos){
            
            SimbolosCodificados sc= null;
            
            List<String> loriginal= this.getSimbolos(numSimbolos);
            CodeBook codeBook= this.reglaHuffman();
            
            List<String> lcodificada= new ArrayList<String>();
            char[] cadena;
            
            for(int i=0; i<loriginal.size();i++)
            {
                cadena= loriginal.get(i).toCharArray();
                if(cadena.length==1);
                    lcodificada.add(codeBook.get(cadena[0]));
            }
            
            sc= new SimbolosCodificados(loriginal,lcodificada);
            
            return sc;
	}
	
	
	//======================metodos auxiliares===================

	private void calculoProbabilidades(int numSimbolos) {

            double prob = 1/numSimbolos;

            for(int i=0; i<numSimbolos; i++)
            {
                probabilidades.add(prob);
            }
        }
        
        private double longitudMedia(CodeBook c) {
            
            List<String> cod = c.codes();
            double l=-1;
            for (int i=0; i<cod.size();i++)
            {
                l+= cod.get(i).length()* probabilidades.get(i);
            }
                
            
            return l;
        }

        public double entropia(int base) {
            
            double res=0.0;
            for (int i=0; i<probabilidades.size();i++)
            {
                res+= probabilidades.get(i)* logBaseN(1/probabilidades.get(i),base);
            }
            return res;
        }
        
        
        
        private double logBaseN(double val, int base){
            return java.lang.Math.log10(val)/java.lang.Math.log10(base);
        }
        
        
        private CodeBook reglaShanonFano()
        {
            CodeBook code=null;
            List<Integer> params= new ArrayList<Integer>();
            int y;
            
            //buscamos los parametros requeridos ni
            for (int i=0; i<alfabeto.getN_simbolos();i++)
            {
                
                y=0;
                //buscamos el yi 
                while (Math.pow(2, y)< (1/probabilidades.get(i)))
                {
                    y++;
                    
                }
                
                params.add(y);
            }
            try {              
                code= construirCodigo(metodoArbol(params));
            } catch (Exception ex) {
                Logger.getLogger(Fuente.class.getName()).log(Level.SEVERE, null, ex);
            }

            return code;
        }
        
 
        private List<String> metodoArbol(List<Integer> params) throws Exception {
            
            List<String> codes = new ArrayList();
           
            //Inicializamos Ej: arb_exp=[0 1]         
            Stack<String> arb_exp= expandir("", new Stack<String>());            
            Stack<String> arb_exp_aux= new Stack<String>();
            int n,l;
            String c;
            Boolean palabraEncontrada;
            
            for(int i=0; i<params.size();i++)
            {
                palabraEncontrada=false;
                n=params.get(i);
                c=arb_exp.lastElement();
                
                while(!palabraEncontrada)
                {

                    //La palabra más grande se encuentra la primera, por tanto solo es necesario comprobar la priemra pos
                    if(n>c.length())
                    {
                        arb_exp= expandir(arb_exp.pop(),arb_exp);
                        c=arb_exp.lastElement();
                    }
                    else if(n<c.length())
                    {
                        
                        arb_exp_aux.push(arb_exp.pop());
                        c=arb_exp.lastElement();
                       
                    }
                    else if(n==c.length())
                    {
                      
                        codes.add(arb_exp.pop());
                        palabraEncontrada=true;

                        
                       //reestructurra el arbol
                       l=arb_exp_aux.size();
                       for (int j=0;j<l;j++)
                       {
                           arb_exp.push(arb_exp_aux.pop());
                       }
                      
                    }
                
                }

            }   
            
            return codes;
        
        }


        private Stack<String> expandir(String c,Stack<String> arbol) 
        {
            
            int n_simb=alfabetoCod.getN_simbolos();
            String nc;
            for (int i=n_simb-1; i>=0; i--) //añadimos tantas nuevas palabras como simbolos podamos añadir
            {
                
                nc=c+i;
                arbol.push(nc);//insertamos a la cabeza la nueva expansion

            }
            
            return arbol;
        }
        
        
        private CodeBook construirCodigo(List<String> words) {
            
             CodeBook code=new CodeBook();
  
             char[] c;
             for (int i=0; i<alfabeto.getN_simbolos(); i++)
             {
               
                c = alfabeto.getSimbolos().get(i).toString().toCharArray();
                
                //suponiendo que solo nos pasara un caracter
                code.add(c[0],words.get(i));
                
                
             }
             return code;
 
        }
        
        private CodeBook reglaHuffman() {
        
            CodeBook code=new CodeBook();;
            //List<Nodo> prob= new ArrayList<Nodo>();
            try {
                //Algoritmo Huffman
                
                code=construirCodigoHuffman(h2(h1(this.probabilidades)));
                
            } catch (Exception ex) {
                Logger.getLogger(Fuente.class.getName()).log(Level.SEVERE, null, ex);
            }

                return code;

        }
        
        private Nodo h1(List<Double> prob) throws Exception {
             
            //List<Nodo> l= new ArrayList<Nodo>();//CAMBIARRR
            Nodo n1, n2,nodoP=null;
            double p1,p2;
            
            //Creamos una lista auxiliar para manejar mas comodamente lso elementos
            LinkedList<Double> aux= new LinkedList<Double>();
            aux.addAll(prob);
            
            if(aux.size()>1)
            {
                //ordenamos lista probabilidades
                Collections.sort(aux);

                p1=aux.removeFirst();
               
                n1= new Nodo(p1); //nodo hoja sin hijos
                //l.add(n);
                
                p2=aux.removeFirst();
                
                n2= new Nodo(p2);//nodo hoja sin hijos
                //l.add(n);
                
                aux.addFirst(p1+p2);//actualizamos la lista aux
               
                nodoP= new Nodo(p1+p2,n1,n2);
                
                //l.add(n3);
                
                
                while(aux.size()>1)//haya al menso dos valores que sumar
                {
                   p1=aux.removeFirst(); //anterior p1+p2 correspondiente al n3 ya creado
            
                   p2=aux.removeFirst(); 
                  
                   n2= new Nodo(p2);//sin  hijos

                   aux.addFirst(p1+p2);
                   
                   
                   nodoP= new Nodo(p1+p2,nodoP,n2);//creamos el nuevo padre, teniendo como hijo el padre anterior;
                   
                }
            }
            else if(aux.size()<1){
                throw new Exception("La distribución de probabilidades es vacia");
            }

            return nodoP;
        }

        
        private List<String> h2(Nodo n) {
             
            List<String> codes = new ArrayList();
           
            if(n!=null)
            {
                //Inicializamos Ej: arb_exp=[0 1]         
                Stack<String> arb_exp= expandir("", new Stack<String>());    
                n=n.getHijo1();
                 
                while (n.tieneHijos())
                {
                    n=n.getHijo1(); //el segundo hijo siempre será nodo hoja
                    arb_exp= expandir(arb_exp.pop(),arb_exp);
                }

                //el arbol de expansion rsultante contendra las palabras asignadas a cada prob
                codes.addAll(arb_exp);
            }
            return codes;
        }
        
        private CodeBook construirCodigoHuffman(List<String> words) {
            
            
            
            //calculo de posiciones de mayor prob a menor
            LinkedList<Double> prob=new LinkedList<Double>();
            List <Integer> posiciones= new ArrayList();
            prob.addAll(this.probabilidades);
            double p;
            int pos;
            for(int k=0; k<prob.size();k++){
                
                p=prob.get(0);
                pos=0;
                for (int i=1; i<prob.size();i++)
                {

                    if(p<prob.get(i)){
                        
                        p=prob.get(i); 
                        pos=i;
                    }
                   
                }
                posiciones.add(pos);
                
                prob.set(pos, -1.0);
                
                
            }
            
            
            
            char[] c;
            CodeBook code=new CodeBook();
            for(int j=0 ; j<posiciones.size();j++)
            {
                 c = alfabeto.getSimbolos().get(posiciones.get(j)).toString().toCharArray();
                
                //suponiendo que solo nos pasara un caracter
                code.add(c[0],words.get(j));
            }
            
            
            return code;
        }

        
	//====================get and set========================
	
	public Alfabeto getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(Alfabeto alfabeto) {
            this.alfabeto=alfabeto;
	}
        
        
	public void setAlfabetoCodificacion(Alfabeto alfabeto) {
            this.alfabetoCod= alfabeto;
            
	}
	
	public List getProbabilidades() {
		return probabilidades;
	}


	public void setProbabilidades(List probabilidades) {
            this.probabilidades=probabilidades;
	}

    
    

    
    

    

    

    
        


}

