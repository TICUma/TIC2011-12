/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

public abstract class Codificacion {

    /**
     * Comprueba si el código es válido.
     *
     * @param codigo    Código a comprobar.
     * @return          Verdadero si es código es válido, falso en otro caso.
     */
    public abstract boolean verificar(String codigo);

    /**
     * Devuelve el código válido a partir del código incorrecto que recibe como parámetro.
     *
     * @param codigo    Código incorrecto.
     * @return          Código corregido.
     */
    public String corregirControl(String codigo) {
        if (verificar(codigo)) {
            return codigo;
        } else {
            return generarCodigoControl(codigo);
        }

    }

    ;

    /****************
     * Genera el código control a partir de los datos
     * @param codigo
     * @return
     */
public abstract String generarCodigoControl(String codigo);

    /************
     * Supuestamente el código de control está bien buscar donde es el error.
     * @param codigo
     * @return
     */
    public abstract String[] corregirDatos(String codigo);
}
