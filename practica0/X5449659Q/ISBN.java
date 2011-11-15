/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica0;

/**
 *
 * @author 
 */
public class ISBN extends Codificacion {

    private static final int MODULO = 11;


private int SumarDigitos(String codigo) { 
	  int res = 0 ; 
	  for (int i = 0; i < codigo.length(); i++) {
  		res += Integer.parseInt(codigo.substring(i, i+1))*(i+1);
  	  }
	  return res ; 
}
    
    
/*************
 * 
 * @param codigo
 * @return
 */
    public boolean verificar(String codigo) {
    	
    	/** 
    	 * Posible modificacion seria comprobar si tiene 10 digitos la nueva cadena
    	 * si es asi comprobamos sino salimos del tiron 
    	 * 
    	 */
    	boolean res = false ;
        codigo = codigo.replaceAll("-", "");
        int resultado = 0;
        if (codigo.length()==10){
        	resultado = SumarDigitos(codigo);
            res = (resultado % MODULO == 0) ; 	
        }
        return res;
    }

/***************
 * 
 * @param codigo
 * @return
 */
    @Override
    public String generarCodigoControl(String codigo) {

     codigo = codigo.replaceAll("-", "");
     int resultado = SumarDigitos(codigo);
     int remainder = resultado % MODULO ; 
     
     if (remainder != 10) {
    	 codigo = codigo.substring(0, codigo.length())+ Integer.toString(remainder) ;	 
     }else { 
    	 codigo = codigo.substring(0, codigo.length())+ "X" ;
     }
      
     return codigo ;
    
    }

    private int[] Divisores(int num) { 
    	int[] res = new int[9];
    	inicializar(res) ;
    	for(int i = 0 ; i < res.length ; i++) { 
    		if ((num % (i+1))==0) res[i] = num /(i+1) ;  
    	}
    	return res ; 
    }
    
   
    
    private void inicializar(int[] res) {
		// TODO Auto-generated method stub
    	for (int i = 0 ; i < res.length ;i++) { 
    		res[i] = 0 ;
    	}
	}


	@Override
    public String[] corregirDatos(String codigo) {
		String[] res = new String[20] ;
		
		if(!verificar(codigo)) { 
			codigo = codigo.replaceAll("-", "") ;
			int Suma = SumarDigitos(codigo);
			int remainder = Suma % MODULO ; 
			/** 
			 *  Remainder porque seguro que no falta o sobra 
			 *  algo para llegar a los 11 
			 */
			int falta = MODULO-remainder;
		    modificarCodigo(res,codigo,remainder,falta);
			
			
		}
		return res ; 
    }


	private void modificarCodigo(String[] res, String codigo, int remainder,
			int falta) {
		// TODO Auto-generated method stub
		
		int[] sobre = Divisores(remainder);
		int[] falte = Divisores(falta);
		int i = 0;
		int j = 0 ;
		int k = 0 ; 
		int aux = 0; 
		String cad = "" ;
		
		while((j<res.length)&&(i<sobre.length)){
				if(sobre[i]!= 0 ) { 
					// es un divisor y encima se la posicion donde tengo
					// que reeemplazar 
					aux = Integer.parseInt(codigo.substring(i, i+1));
					if(sobre[i]<=aux){ 
					   aux = aux - sobre[i] ;
					   cad = codigo.substring(0, i)+Integer.toString(aux)+codigo.substring(i+1, codigo.length());
					   res[j] = cad ;
					   j++;
					}
				}
				i++;
		}
		while((j<res.length)&&(k<falte.length)){
			if(falte[k]!= 0 ) { 
				// es un divisor y encima se la posicion donde tengo
				// que reeemplazar 
				aux = Integer.parseInt(codigo.substring(k, k+1));
				if(10>falte[k]+aux){ 
				   aux = aux + falte[k] ;
				   cad = codigo.substring(0, k)+Integer.toString(aux)+codigo.substring(k+1, codigo.length());
				   res[j] = cad ;
				   j++;
				}
			}
			k++;
			 
		}
	}

}

