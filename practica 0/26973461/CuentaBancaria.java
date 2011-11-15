package practica0;

import java.util.LinkedList;

public class CuentaBancaria extends Codificacion{

	private static final int MODULO=11;
	private static final int FACTORES[]={1,2,4,8,5,10,9,7,3,6};
	
	@Override
	public String[] corregirDatos(String codigo) {
		codigo=codigo.replaceAll("-", "");
		String cuenta=codigo.substring(10);
		String entidadOficina=codigo.substring(0, 8);
		LinkedList<String> posibles = new LinkedList<String>();
		String tmp;
		if(Integer.parseInt(codigo.substring(8,9))!=calculaDigito(entidadOficina)){
			for(int i=0;i<entidadOficina.length();i++){
				for(int j=0;j<10;j++){
					tmp=entidadOficina.substring(0, i);
					tmp+=j;
					tmp+=entidadOficina.substring(i+1);
					if(Integer.parseInt(codigo.substring(8,9))==calculaDigito(tmp)){
						tmp+=codigo.substring(8);
						posibles.add(tmp);
					}
				}
			}
		}
		if(Integer.parseInt(codigo.substring(9,10))!=calculaDigito(cuenta)){
			for(int i=0;i<cuenta.length();i++){
				for(int j=0;j<10;j++){
					tmp=cuenta.substring(0, i);
					tmp+=j;
					tmp+=cuenta.substring(i+1);
					if(Integer.parseInt(codigo.substring(9,10))==calculaDigito(tmp)){
						posibles.add(codigo.substring(0,10)+tmp);
					}
				}
			}
		}
		
		String res[];
		res = new String[posibles.size()];
        int i=0;
        for (String posible : posibles){
        	res[i]=new String(posible);
        	i++;
        }
		return res;
	}

	@Override
	public String generarCodigoControl(String codigo) {
		codigo=codigo.replaceAll("-", "");
		String cuenta = codigo.substring(10);
		String entidadOficina = codigo.substring(0, 8);
		
		return entidadOficina+calculaDigito(entidadOficina)+calculaDigito(cuenta)+cuenta;
	}

	@Override
	public boolean verificar(String codigo) {
		codigo=codigo.replaceAll("-", "");
		String cuenta = codigo.substring(10);
		String entidadOficina = codigo.substring(0, 8);;
		
		int d1,d2;
		try{
			d1=calculaDigito(entidadOficina);
			d2=calculaDigito(cuenta);
		} catch(NumberFormatException e) {
            return false;
        }
		return (Integer.parseInt(codigo.substring(8, 9))==d1 && (Integer.parseInt(codigo.substring(9,10))==d2));
	}
	private int calculaDigito(String codigo){
		if (codigo.length()<10){
			codigo="00"+codigo;
		}
		int res=0;
		for(int i=0;i<codigo.length();i++){
			res+=Integer.parseInt(codigo.substring(i,i+1))*FACTORES[i];
		}
		res=(MODULO - (res%MODULO));
		if (res == 10) res=1;
		if (res == 11) res=0;
		return res;
	}
}
