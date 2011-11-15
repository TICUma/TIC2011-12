/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

/**
 *
 * @author Administrador
 */
public class Nodo {
    
    private double padre;
    private Nodo hijo1, hijo2=null;
    private int poshijo1,poshijo2=-1;
    
    //nodo padre
    public Nodo(double padre, Nodo hijo1, Nodo hijo2) {
        this.padre = padre;
        this.hijo1 = hijo1;
        this.hijo2 = hijo2;
    }
    
    //Nodo hoja 
    public Nodo(double padre) {
        this.padre = padre;
        
    }

    /**
     * @return the padre
     */
    public double getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(double padre) {
        this.padre = padre;
    }

    /**
     * @return the hijo1
     */
    public Nodo getHijo1() {
        return hijo1;
    }

    /**
     * @param hijo1 the hijo1 to set
     */
    public void setHijo1(Nodo hijo1) {
        this.hijo1 = hijo1;
    }

    /**
     * @return the hijo2
     */
    public Nodo getHijo2() {
        return hijo2;
    }

    /**
     * @param hijo2 the hijo2 to set
     */
    public void setHijo2(Nodo hijo2) {
        this.hijo2 = hijo2;
    }
    
    
    public boolean tieneHijos()
    {
        return hijo1!=null && hijo2!=null;
    }

    /**
     * @return the poshijo1
     */
    public int getPoshijo1() {
        return poshijo1;
    }

    /**
     * @param poshijo1 the poshijo1 to set
     */
    public void setPoshijo1(int poshijo1) {
        this.poshijo1 = poshijo1;
    }

    /**
     * @return the poshijo2
     */
    public int getPoshijo2() {
        return poshijo2;
    }

    /**
     * @param poshijo2 the poshijo2 to set
     */
    public void setPoshijo2(int poshijo2) {
        this.poshijo2 = poshijo2;
    }
    
    
}

