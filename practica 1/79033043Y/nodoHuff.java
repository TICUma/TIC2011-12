/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Aku
 */
public class nodoHuff{
    
    double valor;
    List<Integer> sumadoDe;
    nodoHuff(double valor, int pos){
        this.valor = valor;
        sumadoDe = new ArrayList<Integer>();
        sumadoDe.add(pos);
    }
    
    nodoHuff(double valor, List<Integer> sumado1,List<Integer> sumado2){
        this.valor = valor;
        sumadoDe = new ArrayList<Integer>();
        sumadoDe.addAll(sumado1);
        sumadoDe.addAll(sumado2);
//        System.out.println("SUMADO DE:" + sumadoDe);
    }
    
    
   
}
