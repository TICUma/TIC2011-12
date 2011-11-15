package practica0;

public class CCC extends Codificacion{
	
	//EEEE         OOOO DD NNNNNNNNNN
	//EEEE         Entidad
	//OOOO         Oficina
	//DD 	       Control
	//NNNNNNNNNN   Número de cuenta
	public int aPesos[] = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
	
	

	@Override
	public boolean verificar(String codigo) {
		codigo=codigo.replaceAll("-", "");
		codigo=codigo.replaceAll(" ", "");
		//codigo="00"+codigo;
		
		System.out.println("Codigo entrante: "+codigo);
		
		String correcto =  this.generarCodigoControl(codigo.substring(0, 8)+codigo.substring(10, codigo.length()));
		
		correcto = correcto.replaceAll(" ", "");
		System.out.println("Codigo correcto: "+correcto);
		
		return codigo.equals(correcto);
	}

	@Override
	public String generarCodigoControl(String codigo) {
        codigo = codigo.replaceAll("-", "");
        codigo = codigo.replaceAll(" ", "");
		codigo="00"+codigo;
		
		//System.out.println("Codigo con los 2 ceros: "+codigo);
		
		char[] codigoArray = codigo.toCharArray();
		int suma=0;
		int digitoControl1,digitoControl2;
		
		//Digito de control 1
		for (int i=0;i<10;i++){
			suma += aPesos[i] * Integer.parseInt(""+codigoArray[i]);
		}
		digitoControl1 = 11 - suma%11;
		if (digitoControl1==11){
			digitoControl1=0;
		}else if (digitoControl1==10){
			digitoControl1=1;
		}
		
		suma=0;
		//Digito de control 2
		for (int i=10;i<20;i++){
			suma += aPesos[i-10] * Integer.parseInt(""+codigoArray[i]);
		}
			digitoControl2 = 11 - (suma%11);
		if (digitoControl2==11){
			digitoControl2=0;
		}else if (digitoControl2==10){
			digitoControl2=1;
		}
		
		return (codigo.substring(0, 10)+" "+digitoControl1+digitoControl2+" "+codigo.substring(10, codigo.length())).substring(2);
	}

	@Override
	public String[] corregirDatos(String codigo) {
		codigo = codigo.replaceAll("-", "");
        codigo = codigo.replaceAll(" ", "");
		String primerTrozo = codigo.substring(0, 8);
		String segundoTrozo = codigo.substring(10,codigo.length());
		String control = codigo.substring(8,10);	
		
		String[] resultado = new String[50];		
		
		//Obtenemos el codigo de control de los datos erroneos
		String controlErroneo = this.generarCodigoControl(primerTrozo+segundoTrozo).replaceAll(" ", "").substring(8,10);
		System.out.println("El codigo de control de los datos erroneos es: "+controlErroneo);
		
		if (control.toCharArray()[0]==controlErroneo.toCharArray()[0] && control.toCharArray()[1]==controlErroneo.toCharArray()[1]){
			//No hay error
		}else if (control.toCharArray()[0]!=controlErroneo.toCharArray()[0] && control.toCharArray()[1]==controlErroneo.toCharArray()[1]){
			//Error en el primer trozo
			resultado = this.corrigeTrozo(primerTrozo,segundoTrozo,Integer.parseInt(control.substring(0, 1)),true);
			for (int i=0;i<resultado.length;i++){
				if (resultado[i]!=null) resultado[i] = resultado[i]+" "+control+" "+segundoTrozo;
			}
		}else if (control.toCharArray()[0]==controlErroneo.toCharArray()[0] && control.toCharArray()[1]!=controlErroneo.toCharArray()[1]){
			//Error en el segundo trozo
			resultado = this.corrigeTrozo(primerTrozo,segundoTrozo,Integer.parseInt(control.substring(1, 2)),false);
			for (int i=0;i<resultado.length;i++){
				if (resultado[i]!=null) resultado[i] = primerTrozo +" "+control+" "+resultado[i];
			}
		}else{
			//2 Errores, no sabemos corregirlo
		}
		System.out.println(primerTrozo+" "+control+" "+segundoTrozo);
		System.out.println("Posibles valores correctos:");
		for (int i=0;i<resultado.length;i++){
			if (resultado[i]!=null) System.out.println(resultado[i]);
		}
		return resultado;		
	}
	
	private String[] corrigeTrozo(String trozoUno,String trozoDos,int controlCorrecto,boolean primerTrozo){
		
		String[] resultado = new String[40];
		int indiceResultado = 0;
		char[] trozoArray; 
		
		int suma=0;
		int i,j,moduloObtenido;
		
		/*Lo que hay que hacer es comprobar:
		 * Si la posicion que se está mirando (que es la i), es 1, es modulo obtenido o es divisor de modulo obtenido.
		 * - Ver si se puede restar 1*moduloObtenido, -moduloObtenido o -moduloObtenido/divisor a la posición
		 * Si la posicion que se está mirando (la i) es 1, es (11-moduloObtenido) o es divisor de (11-moduloObtenido)
		 * - Ver si se puede sumar 1*mduloObtenido, +moduloObtenido o moduloObtenido/divisor a la posición.
		*/
		if (primerTrozo){
			j=2;
			System.out.println("Fallo en el primer trozo.");
			trozoArray = trozoUno.toCharArray();
		}else{
			System.out.println("Fallo en el segundo trozo.");
			trozoArray = trozoDos.toCharArray();
			j=0;
		}
		
			for (i=j;i<10;i++){
				suma += aPesos[i] * Integer.parseInt(""+trozoArray[i-j]);			
			}
			moduloObtenido = (suma+controlCorrecto)%11; //Esto es lo que nos sobra o nos (11-moduloObtenido) falta.
			System.out.println("Modulo obtenido: "+moduloObtenido+" Suma: "+suma+" - Control correcto: "+controlCorrecto);
			
			
			for (i=j;i<10;i++){
				if (primerTrozo){
					j=2;
					//System.out.println("Fallo en el primer trozo.");
					trozoArray = trozoUno.toCharArray();
				}else{
					//System.out.println("Fallo en el segundo trozo.");
					trozoArray = trozoDos.toCharArray();
					j=0;
				}
				// Sobra moduloObtenido
				if (aPesos[i] == moduloObtenido){ //Si estamos mirando la posicion de peso moduloObtenido.
					if (Integer.parseInt(""+trozoArray[i-j])-1>=0){
						trozoArray[i-2] = Character.forDigit(Integer.parseInt(""+trozoArray[i-2])-1,10);
						resultado[indiceResultado] = String.copyValueOf(trozoArray);
						indiceResultado++;
					}
				}else if (aPesos[i] == 1){
					if (Integer.parseInt(""+trozoArray[i-j])-moduloObtenido>=0){
						trozoArray[i-j] = Character.forDigit(Integer.parseInt(""+trozoArray[i-j])-moduloObtenido,10);
						resultado[indiceResultado] = String.copyValueOf(trozoArray);
						indiceResultado++;
					}
				}else if (moduloObtenido%aPesos[i]==0){
					if (Integer.parseInt(""+trozoArray[i-j])-moduloObtenido/aPesos[i]>=0){
						trozoArray[i-j] = Character.forDigit(Integer.parseInt(""+trozoArray[i-j])-moduloObtenido/aPesos[i],10);
						resultado[indiceResultado] = String.copyValueOf(trozoArray);
						indiceResultado++;
					}
				}
				
				if (primerTrozo){
					j=2;
					//System.out.println("Fallo en el primer trozo.");
					trozoArray = trozoUno.toCharArray();
				}else{
					//System.out.println("Fallo en el segundo trozo.");
					trozoArray = trozoDos.toCharArray();
					j=0;
				}
				//Falta 11-moduloObtenido
				if (aPesos[i] == (11-moduloObtenido)){ //Si estamos mirando la posicion de peso moduloObtenido.
					if (Integer.parseInt(""+trozoArray[i-j])+1<10){
						trozoArray[i-j] = Character.forDigit(Integer.parseInt(""+trozoArray[i-j])+1,10);
						resultado[indiceResultado] = String.copyValueOf(trozoArray);
						indiceResultado++;
					}
				}else if (aPesos[i] == 1){
					if (Integer.parseInt(""+trozoArray[i-j])+(11-moduloObtenido)<10){
						trozoArray[i-j] = Character.forDigit(Integer.parseInt(""+trozoArray[i-j])+(11-moduloObtenido),10);
						resultado[indiceResultado] = String.copyValueOf(trozoArray);
						indiceResultado++;
					}
				}else if ((11-moduloObtenido)%aPesos[i]==0){
					if (Integer.parseInt(""+trozoArray[i-j])+moduloObtenido/aPesos[i]<10){
						trozoArray[i-j] = Character.forDigit(Integer.parseInt(""+trozoArray[i-j])+(11-moduloObtenido)/aPesos[i],10);
						resultado[indiceResultado] = String.copyValueOf(trozoArray);
						indiceResultado++;
					}
				}
			}				
			
		return resultado;
	}
}
