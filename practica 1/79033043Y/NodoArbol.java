/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Aku
 */
public class NodoArbol extends DefaultMutableTreeNode{
    
    boolean ocupado, usado;
    String texto;
    
    public NodoArbol(String texto)
    {
        super(texto);
        this.ocupado = false;
        this.usado   = false;
        this.texto   = texto;
    }
    
    public NodoArbol(String texto, boolean ocupado)
    {
        super(texto);
        this.ocupado = ocupado;
        this.usado   = false;
        this.texto = texto;
    }
    
    public boolean getOcupado(){
        return ocupado;
    }
    
    public void setOcupado(boolean ocup){
        this.ocupado = ocup;
    }
    
    public boolean getUsado(){
        return usado;
    }
    
    public void setUsado(boolean usado){
        this.usado = usado;
    }
    
    public String getTexto(){
        return texto;
    }
    
    public void setTexto(String texto){
        this.texto = texto;
    }
}
