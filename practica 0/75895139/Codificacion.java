/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

public abstract class Codificacion {

    /**
     * Comprueba si el c�digo es v�lido.
     *
     * @param codigo    C�digo a comprobar.
     * @return          Verdadero si es c�digo es v�lido, falso en otro caso.
     */
    public abstract boolean verificar(String codigo);

    /**
     * Devuelve el c�digo v�lido a partir del c�digo incorrecto que recibe como par�metro.
     *
     * @param codigo    C�digo incorrecto.
     * @return          C�digo corregido.
     */
    public String corregirControl(String codigo){
         if (verificar(codigo)) return codigo;
         else return generarCodigoControl(codigo);
        
    };

    /****************
     * Genera el c�digo control a partir de los datos
     * @param codigo
     * @return
     */
public abstract String generarCodigoControl(String codigo);

    /************
     * Supuestamente el c�digo de control est� bien buscar donde es el error.
     * @param codigo
     * @return
     */
    public abstract String[] corregirDatos(String codigo);
}
