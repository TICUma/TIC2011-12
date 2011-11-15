/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



/**
 * Los números de cuentas bancarios actuales están formados por 20 dígitos que tienen la estructura siguiente:
     - El código del banco al que pertenece la cuenta (4 dígitos)
     - El código de la sucursal en el que se abrio la cuenta (4 dígitos)
     - Un número de control, llamado dígito de control, que impide errores de teclado (2 dígitos)
     - Y por último, el número de cuenta (10 dígitos)


 * @author alumno
 */
public class CuentaBancaria extends Codificacion{

    @Override
    public boolean verificar(String codigo) 
    {
      return verificarDC1(codigo) && verificarDC2(codigo);
      
    }

    private boolean verificarDC1(String codigo)
    {
         int[] pesos1= new int[8];
          pesos1[0] = 4;
          pesos1[1] = 8;
          pesos1[2] = 5;
          pesos1[3] = 10;
          pesos1[4] = 9;
          pesos1[5] = 7;
          pesos1[6] = 3;
          pesos1[7] = 6;

           /*Cálculo del primer dígito de control*/
          int control1 = 0;
          for (int i=0; i<=7; i++){
                control1 += Integer.parseInt(codigo.substring(i, i+1)) * pesos1[i];
          }
          control1 = 11 - (control1 % 11);
          if (control1 == 11) control1 = 0;
          else if (control1 == 10) control1 = 1;
          
          return (control1 == Integer.parseInt(codigo.substring(8, 9)));
          
    }
    
    private boolean verificarDC2(String codigo)
    {
        int[] pesos2= new int[10];
      pesos2[0] = 1;
      pesos2[1] = 2;
      pesos2[2] = 4;
      pesos2[3] = 8;
      pesos2[4] = 5;
      pesos2[5] = 10;
      pesos2[6] = 9;
      pesos2[7] = 7;
      pesos2[8] = 3;
      pesos2[9] = 6;
      
      /*Cálculo del segundo dígito de control*/
      int control2 = 0;
      for (int i=0; i<=9; i++){
            control2 += Integer.parseInt(codigo.substring(i+10, i+11)) * pesos2[i];
      }
       control2 = 11 - (control2 % 11);
       if (control2 == 11) control2 = 0;
       else if (control2 == 10) control2 = 1;
       
       return (control2 == Integer.parseInt(codigo.substring(9, 10)));
    }
    
  
        /**
     * La forma de calcular el dígito de control es esta:

    La primera cifra del banco se multiplica por 4.
    La segunda cifra del banco se multiplica por 8.
    La tercera cifra del banco se multiplica por 5.
    La cuarta cifra del banco se multiplica por 10.

    La primera cifra de la entidad se multiplica por 9.
    La segunda cifra de la entidad se multiplica por 7.
    La tercera cifra de la entidad se multiplica por 3.
    La cuarta cifra de la entidad se multiplica por 6.

    Se suman todos los resultados obtenidos.
    Se divide entre 11 y nos quedamos con el resto de la división.
    A 11 le quitamos el resto anterior, y ese el el primer dígito de control, con la salvedad de que si nos da 10, el dígito es 1

    Para obtener el segundo dígito de control:
    La primera cifra de la cuenta se multiplica por 1
    La segunda cifra de la cuenta se multiplica por 2
    La tercera cifra de la cuenta se multiplica por 4
    La cuarta cifra de la cuenta se multiplica por 8
    La quinta cifra de la cuenta se multiplica por 5
    La sexta cifra de la cuenta se multiplica por 10
    La septima cifra de la cuenta se multiplica por 9
    La octava cifra de la cuenta se multiplica por 7
    La novena cifra de la cuenta se multiplica por 3
    La decima cifra de la cuenta se multiplica por 6
    Se suman todos los resultados obtenidos.
    Se divide entre 11 y nos quedamos con el resto de la división.
    A 11 le quitamos el resto anterior, y ese el el segundo dígito de control, con la salvedad de que si nos da 10, el dígito es 1

     */
    
    @Override
    public String generarCodigoControl(String codigo) 
    {

      String codigoRes= codigo.substring(0, 8)+ generarDC1(codigo)+generarDC2(codigo)+codigo.substring(8, 18);
      return codigoRes;
      
      
    }
    
    public String generarDC1(String codigo) 
    {
      
      int[] pesos1= new int[8];
      pesos1[0] = 4;
      pesos1[1] = 8;
      pesos1[2] = 5;
      pesos1[3] = 10;
      pesos1[4] = 9;
      pesos1[5] = 7;
      pesos1[6] = 3;
      pesos1[7] = 6;
      
       /*Cálculo del primer dígito de control*/
      int control1 = 0;
      for (int i=0; i<=7; i++){
            control1 += Integer.parseInt(codigo.substring(i, i+1)) * pesos1[i];
      }
      control1 = 11 - (control1 % 11);
      if (control1 == 11) control1 = 0;
      else if (control1 == 10) control1 = 1;
      
      return String.valueOf(control1);
    }
    
    public String generarDC2(String codigo) 
    {
      int[] pesos2= new int[10];
      pesos2[0] = 1;
      pesos2[1] = 2;
      pesos2[2] = 4;
      pesos2[3] = 8;
      pesos2[4] = 5;
      pesos2[5] = 10;
      pesos2[6] = 9;
      pesos2[7] = 7;
      pesos2[8] = 3;
      pesos2[9] = 6;
      
      /*Cálculo del segundo dígito de control*/
      int control2 = 0;
      for (int i=0; i<=9; i++){
            control2 += Integer.parseInt(codigo.substring(i+8, i+9)) * pesos2[i];
      }
       control2 = 11 - (control2 % 11);
       if (control2 == 11) control2 = 0;
       else if (control2 == 10) control2 = 1;
       
       return String.valueOf(control2);
    }
  

    @Override
    public String[] corregirDatos(String codigo) {
        
        List<String> resultados1 = new ArrayList<String>();
        List<String> resultados2 = new ArrayList<String>();
        List<String> correcciones = new ArrayList<String>();
        
        
        //1ºPosible solución: corregir digitos de control
            correcciones.add(generarCodigoControl(codigo.substring(0,8)+codigo.substring(10,codigo.length())));
        
        //2ºPosible solución: 
            
            if(!verificarDC1(codigo))
            {
               
                resultados1.addAll(resolverES(codigo));
            }
            else
            {
                resultados1.add(codigo.substring(0, 9));
            }
            
            //correcciones.addAll(resultados1);
            
            if(!verificarDC2(codigo))
            {
               
                resultados2.addAll(resolverNC(codigo));
                
            }
            else
            {
                resultados2.add(codigo.substring(9,codigo.length()));
            }
            
            //correcciones.addAll(resultados2);
            
            //Combinaciones de la corrección y primero por el segundo
            for(int i=0;i<resultados1.size();i++)
            {
                for(int j=0;j<resultados2.size();j++)
                {
                    if( !correcciones.contains(resultados1.get(i) +resultados2.get(j)))
                        correcciones.add(resultados1.get(i) +resultados2.get(j));
                }
            }
        
            
            return correcciones.toArray(new String[0]);        
    
    }

    private List<String> resolverES(String codigo) {
        
         List<String> resultados = new ArrayList<String>();
         
         int[] pesos= new int[8];
          pesos[0] = 4;
          pesos[1] = 8;
          pesos[2] = 5;
          pesos[3] = 10;
          pesos[4] = 9;
          pesos[5] = 7;
          pesos[6] = 3;
          pesos[7] = 6;
          
          String ncodigo;
          
        
        for (int i=0; i<8; i++)//posiciones sin contar la del DC
        {
             
            for(int j=0; j<=9; j++)
            {
                if(i==0)
                {
                   
                   ncodigo=String.valueOf(j)+codigo.substring(1, codigo.length());
                    
                   if(verificarDC1(ncodigo))
                   {
                        resultados.add(ncodigo.substring(0,9));
                         
                   }
                }
                else
                {
                    ncodigo=codigo.substring(0,i)+String.valueOf(j)+ codigo.substring(i+1,codigo.length());
                    
                    if(verificarDC1(ncodigo))
                         resultados.add(ncodigo.substring(0,9));
                }
            }
        }
         
         
         
         return resultados;
    }

    private List<String> resolverNC(String codigo) {
         List<String> resultados = new ArrayList<String>();
         
         int[] pesos= new int[10];
          pesos[0] = 1;
          pesos[1] = 2;
          pesos[2] = 4;
          pesos[3] = 8;
          pesos[4] = 5;
          pesos[5] = 10;
          pesos[6] = 9;
          pesos[7] = 7;
          pesos[8] = 3;
          pesos[9] = 6;
      
          String ncodigo;
          
        
        for (int i=10; i<20; i++)//posiciones sin contar la del DC
        {
             
            for(int j=0; j<=9; j++)
            {
                if(i==19)
                {
                   
                   ncodigo=codigo.substring(0,codigo.length()-1)+String.valueOf(j);
                   
                    if(verificarDC2(ncodigo))
                         resultados.add(ncodigo.substring(9,ncodigo.length()));
                }
                else
                {

                   ncodigo=codigo.substring(0,i)+String.valueOf(j)+codigo.substring(i+1, codigo.length());
                   
                   if(verificarDC2(ncodigo))
                   {
                        resultados.add(ncodigo.substring(9,ncodigo.length()));
                   }
                }
            }
        }
         
         return resultados;
    }
}
