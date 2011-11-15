/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package practica0;

/**
*
* @author 
*/
public class NIF extends Codificacion {


    private static final String NIF_TABLA = "TRWAGMYFPDXBNJZSQVHLCKE";


  

    @Override
    public boolean verificar(String nif) {
       int dni = Integer.parseInt(nif.substring(0, nif.length()-1));
       try {
           return NIF_TABLA.charAt(dni % NIF_TABLA.length()) == nif.charAt(nif.length()-1);
       } catch (NumberFormatException e) {
           return false;
       }
    }


    /*************
     * 
     * @param codigo
     * @return
     */
    public String generarCodigoControl(String codigo) {
       
       if (codigo!=null){
        int dni = Integer.parseInt(codigo, 10);
        return codigo+ NIF_TABLA.charAt(dni % NIF_TABLA.length());
       }
       else return null;
    }

    
    public String [] corregirDatos(String codigo) {
        String[] res = new String[8];
        /**  
         * como maximo 8 alternativas
         * 
         */
        // saber la posicion de codigo control en la tabla 
        int longitud = codigo.length() ;
        int ind = NIF_TABLA.indexOf(codigo.substring(longitud-1,longitud));
         
        buscar_equivalente(res,codigo,ind);
               
        return res ; 
    }


	private void buscar_equivalente(String[] res, String codigo, int ind) {
		// TODO Auto-generated method stub
		
		for(int j = 0;j<codigo.length()-1;j++){
			String codAux = codigo.substring(0, codigo.length()-1);
			for(int k = 0 ; k < 10 ; k++) { 
				codAux = codAux.substring(0, j)+Integer.toString(k)+codAux.substring(j+1, codAux.length());
				int num = Integer.parseInt(codAux,10)% NIF_TABLA.length();
				if(num == ind) { 
					res[j] = codAux+NIF_TABLA.charAt(ind);
				}
			}
		}
	}
}
