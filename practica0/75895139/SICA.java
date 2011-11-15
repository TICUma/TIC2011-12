package practica0;

public class SICA extends Codificacion {
	
	private static final int MODULO = 11;
	Integer digitoControl1, digitoControl2;
	
	public String generarCodigoControl(String codigo) {
		
		codigo = codigo.replaceAll("-", "");
		
		//Obtenemos el primer dígito de control
		
		int res1 = 0;
		String banco = codigo.substring(0,4);
		String entidad = codigo.substring(4,8);
		res1 += Integer.parseInt(banco.substring(0,1))*4 +
					Integer.parseInt(banco.substring(1,2))*8 +
					Integer.parseInt(banco.substring(2,3))*5 + 
					Integer.parseInt(banco.substring(3,4))*10;
		
		res1 += Integer.parseInt(entidad.substring(0,1))*9 +
				Integer.parseInt(entidad.substring(1,2))*7 +
				Integer.parseInt(entidad.substring(2,3))*3 + 
				Integer.parseInt(entidad.substring(3,4))*6;
		
		digitoControl1 = MODULO - (res1 % MODULO);
		if (digitoControl1 == 10)
			digitoControl1 = 1;
		if(digitoControl1 == 11)
			digitoControl1 = 0;
		
		//Obtenemos el segundo dígito de control
		
		int res2 = 0;
		String cuenta = codigo.substring(8,18);
		res2 += Integer.parseInt(cuenta.substring(0,1))*1 +
				Integer.parseInt(cuenta.substring(1,2))*2 +
				Integer.parseInt(cuenta.substring(2,3))*4 + 
				Integer.parseInt(cuenta.substring(3,4))*8 +
				Integer.parseInt(cuenta.substring(4,5))*5 +
				Integer.parseInt(cuenta.substring(5,6))*10 +
				Integer.parseInt(cuenta.substring(6,7))*9 + 
				Integer.parseInt(cuenta.substring(7,8))*7 +
				Integer.parseInt(cuenta.substring(8,9))*3 +
				Integer.parseInt(cuenta.substring(9,10))*6;
		
		digitoControl2 = MODULO - (res2 % MODULO);
		if (digitoControl2 == 10)
			digitoControl2 = 1;
		if(digitoControl2 == 11)
			digitoControl2 = 0;
		
		//Formamos el control con los dígitos obtenidos
		//String resultado = banco + "-" + entidad + "-" + digitoControl1.toString() + digitoControl2.toString() + "-" + cuenta;
		String ccontrol = digitoControl1.toString() + digitoControl2.toString();
		
		return ccontrol;
	}

	@Override
	public boolean verificar(String codigo) {
		//La idea es coger el código dividirlo en sus trozos, sacar
		//el código de control, concatener el resto y compara el codigo
		//de control con generarControl del resto
        
        String codControl = codControl(codigo);
        String codSinControl = codSinControl(codigo);
        
		if (codControl.compareTo(generarCodigoControl(codSinControl))==0)
			return true;
		else
			return false;
	}

	@Override
	public String[] corregirDatos(String codigo) {
		// TODO Auto-generated method stub
		
		codigo = codigo.replaceAll("-", "");
		
		String[] correccion= new String[20];
		String codControl = codControl(codigo);
		
		//Si está bien el código de control y mal otro bit del mensaje
		/*Primero vemos en qué parte del código está el error, si en la cuenta o en el banco y cuenta
		 * Para ello comparamos cúal de los dos dígitos de control no coincide*/
		int control1 = Integer.parseInt(codControl.substring(0,1));
		int res1 = 0;
		
		String banco = codigo.substring(0,4);
		String entidad = codigo.substring(4,8);
		res1 += Integer.parseInt(banco.substring(0,1))*4 +
					Integer.parseInt(banco.substring(1,2))*8 +
					Integer.parseInt(banco.substring(2,3))*5 + 
					Integer.parseInt(banco.substring(3,4))*10;
		
		res1 += Integer.parseInt(entidad.substring(0,1))*9 +
				Integer.parseInt(entidad.substring(1,2))*7 +
				Integer.parseInt(entidad.substring(2,3))*3 + 
				Integer.parseInt(entidad.substring(3,4))*6;
		
		if (control1 == MODULO - (res1 % MODULO)){ //No hay error en el número de banco y entidad
			
		}else{ //Hay error en el número de banco y entidad
			int falta = MODULO - (res1 % MODULO); //Bit de control que saldría si este mensaje estuviese bien
			
			//Algún bit del mensaje tiene menos valor del que debiera
			int diferencia;
			/*
			 * Calculo la diferencia que existe entre el código de control correcto 
			 * y el que sale con el código de datos erróneo. Dicha diferencia será lo que tengo que
			 * sumar de más a la ecuación, y lo que hago es sumar uno a la cifra que queda multiplicada por dicho valor
			 */
			if (falta - control1 >= 0)
				diferencia = falta - control1;
			else
				diferencia = MODULO - (control1 - falta); //Esto lo hago por si la resta sale negativa considerar los casos tambien
			
			if(diferencia == 1){ //Ejemplo 9-8 = 1 y 11 -(9-8) = 10
				int p2 = Integer.parseInt(banco.substring(3,4)) - 1;
				Integer i2 = p2;
				correccion[0] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + i2.toString() + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4); 
			}else if(diferencia == 2){
				int p2 = Integer.parseInt(entidad.substring(0,1)) - 1;
				Integer i2 = p2;
				correccion[0] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + i2.toString() +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4); 
				
			}else if (diferencia == 3){
				int p1 = Integer.parseInt(entidad.substring(2,3)) + 1;
				Integer i1 = p1;
				correccion[0] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + i1.toString() + entidad.substring(3,4); 
				
				int p2 = Integer.parseInt(banco.substring(1,2)) - 1;
				Integer i2 = p2;
				correccion[1] = banco.substring(0,1) + i2.toString() + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4);
			}else if (diferencia == 4){
				int p1 = Integer.parseInt(banco.substring(0,1)) + 1;
				Integer i1 = p1;
				correccion[0] = i1.toString() + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4); 
				
				int p2 = Integer.parseInt(entidad.substring(1,2)) - 1;
				Integer i2 = p2;
				correccion[1] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				i2.toString() + entidad.substring(2,3)+ entidad.substring(3,4);
			}else if (diferencia == 5){
				int p1 = Integer.parseInt(banco.substring(2,3)) + 1;
				Integer i1 = p1;
				correccion[0] = banco.substring(0,1) + banco.substring(1,2) + i1.toString() + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4); 
				
				int p2 = Integer.parseInt(entidad.substring(3,4)) - 1;
				Integer i2 = p2;
				correccion[1] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ i2.toString(); 
			}else if (diferencia == 6){
				int p1 = Integer.parseInt(entidad.substring(3,4)) + 1;
				Integer i1 = p1;
				correccion[0] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ i1.toString(); 
				
				int p2 = Integer.parseInt(banco.substring(2,3)) - 1;
				Integer i2 = p2;
				correccion[1] = banco.substring(0,1) + banco.substring(1,2) + i2.toString() + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4);
			}else if (diferencia == 7){
				int p1 = Integer.parseInt(entidad.substring(1,2)) + 1;
				Integer i1 = p1;
				correccion[0] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				i1.toString() + entidad.substring(2,3)+ entidad.substring(3,4); 
				
				int p2 = Integer.parseInt(banco.substring(0,1)) - 1;
				Integer i2 = p2;
				correccion[1] = i2.toString() + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4); 
			}else if (diferencia == 8){
				int p1 = Integer.parseInt(banco.substring(1,2)) + 1;
				Integer i1 = p1;
				correccion[0] = banco.substring(0,1) + i1.toString() + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4); 
				
				int p2 = Integer.parseInt(entidad.substring(2,3)) - 1;
				Integer i2 = p2;
				correccion[1] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + i2.toString() + entidad.substring(3,4); 
			}else if (diferencia == 9){
				int p1 = Integer.parseInt(entidad.substring(0,1)) + 1;
				Integer i1 = p1;
				correccion[0] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + banco.substring(3,4) + "-" + i1.toString() +
				entidad.substring(1,2) + entidad.substring(2,3) + entidad.substring(3,4); 
			}else if (diferencia == 10){
				int p2 = Integer.parseInt(banco.substring(3,4)) + 1;
				Integer i2 = p2;
				correccion[0] = banco.substring(0,1) + banco.substring(1,2) + banco.substring(2,3) + i2.toString() + "-" + entidad.substring(0,1) +
				entidad.substring(1,2) + entidad.substring(2,3)+ entidad.substring(3,4); 
			}
		}
		
		//Número de cuenta
		int control2 = Integer.parseInt(codControl.substring(1,2));
		int res2 = 0;
		
		String cuenta = codigo.substring(10,20);
		res2 += Integer.parseInt(cuenta.substring(0,1))*1 +
				Integer.parseInt(cuenta.substring(1,2))*2 +
				Integer.parseInt(cuenta.substring(2,3))*4 + 
				Integer.parseInt(cuenta.substring(3,4))*8 +
				Integer.parseInt(cuenta.substring(4,5))*5 +
				Integer.parseInt(cuenta.substring(5,6))*10 +
				Integer.parseInt(cuenta.substring(6,7))*9 + 
				Integer.parseInt(cuenta.substring(7,8))*7 +
				Integer.parseInt(cuenta.substring(8,9))*3 +
				Integer.parseInt(cuenta.substring(9,10))*6;
		
		if (control2 == MODULO - (res2 % MODULO)){ //No hay error en el número de cuenta
			
		}else{ //Hay error en el número de cuenta
			int falta = MODULO - (res2 % MODULO); //Bit de control que saldría si este mensaje estuviese bien
			
			//Algún bit del mensaje tiene menos valor del que debiera
			int diferencia;
			/*
			 * Calculo la diferencia que existe entre el código de control correcto 
			 * y el que sale con el código de datos erróneo. Dicha diferencia será lo que tengo que
			 * sumar de más a la ecuación, y lo que hago es sumar uno a la cifra que queda multiplicada por dicho valor
			 */
			if (falta - control2 >= 0)
				diferencia = falta - control2;
			else
				diferencia = MODULO - (control2 - falta); //Esto lo hago por si la resta sale negativa considerar los casos tambien
			
			if(diferencia == 1){ //Ejemplo 9-8 = 1 y 11 -(9-8) = 10
				int p1 = Integer.parseInt(cuenta.substring(0,1)) + 1;
				Integer i1 = p1;
				correccion[0] = i1.toString() + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(5,6)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + i2.toString() +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
			}else if(diferencia == 2){
				int p1 = Integer.parseInt(cuenta.substring(1,2)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + i1.toString() + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(6,7)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				i2.toString() + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10);
				
			}else if (diferencia == 3){
				int p1 = Integer.parseInt(cuenta.substring(8,9)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ i1.toString() + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(3,4)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + i2.toString() + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
			}else if (diferencia == 4){
				int p1 = Integer.parseInt(cuenta.substring(2,3)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + cuenta.substring(1,2) + i1.toString() + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(7,8)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + i2.toString() + cuenta.substring(8,9) + cuenta.substring(9,10);
			}else if (diferencia == 5){
				int p1 = Integer.parseInt(cuenta.substring(4,5)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + i1.toString() + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(9,10)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + i2.toString(); 
			}else if (diferencia == 6){
				int p1 = Integer.parseInt(cuenta.substring(9,10)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + i1.toString(); 
				
				int p2 = Integer.parseInt(cuenta.substring(4,5)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + i2.toString() + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10);
			}else if (diferencia == 7){
				int p1 = Integer.parseInt(cuenta.substring(7,8)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + i1.toString() + cuenta.substring(8,9) + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(2,3)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + cuenta.substring(1,2) + i2.toString() + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10);
			}else if (diferencia == 8){
				int p1 = Integer.parseInt(cuenta.substring(3,4)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + i1.toString() + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(8,9)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ i2.toString() + cuenta.substring(9,10); 
			}else if (diferencia == 9){
				int p1 = Integer.parseInt(cuenta.substring(6,7)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				i1.toString() + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(1,2)) - 1;
				Integer i2 = p2;
				correccion[1] = cuenta.substring(0,1) + i2.toString() + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10);
			}else if (diferencia == 10){
				int p1 = Integer.parseInt(cuenta.substring(5,6)) + 1;
				Integer i1 = p1;
				correccion[0] = cuenta.substring(0,1) + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + i1.toString() +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
				
				int p2 = Integer.parseInt(cuenta.substring(0,1)) - 1;
				Integer i2 = p2;
				correccion[1] = i2.toString() + cuenta.substring(1,2) + cuenta.substring(2,3) + cuenta.substring(3,4) + cuenta.substring(4,5) + cuenta.substring(5,6) +
				cuenta.substring(6,7) + cuenta.substring(7,8)+ cuenta.substring(8,9) + cuenta.substring(9,10); 
			}
			
			
		}
		
		
		return correccion;
	}
	
	/*codSinControl devuelve el código sin los dígitos de control, sólo el mensaje de bits*/
	private String codSinControl(String codigo){
		codigo = codigo.replaceAll("-", "");
		String banco = codigo.substring(0,4);
		String entidad = codigo.substring(4,8);
		String cuenta = codigo.substring(10,20);
		
		String cod_sincontrol = banco + "-" + entidad + "-" + cuenta;
		return cod_sincontrol;
	}
	
	/*codControl devuelve el código de control de un código completo dado*/
	private String codControl(String codigo){
		codigo = codigo.replaceAll("-", "");
		String cod = codigo.substring(8,10);
		return cod;
	}

}
