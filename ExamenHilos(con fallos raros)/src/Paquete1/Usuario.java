
package Paquete1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Usuario  extends Thread {
      private  Cuenta cuenta;
       private Banco banco;
       private Banco banco2;
       private String  nombre;
       Random numeroAleatorios;
       int numero;
       
       
       
       

    public Usuario(Cuenta cuenta, Banco banco,String nombre,Banco banco2) {
        super();
        this.cuenta = cuenta;
        this.banco = banco;
        this.nombre=nombre;
        this.banco2=banco2;
        
        
    }

    public String getNombre() {
        return nombre;
    }
  
       
       
       
       
    @Override
    public void run() {
        int i=0,monto,cuentadestino=0,intercuentadestino=0,banco=0;
        numeroAleatorios= new Random();
       
        while(i<6){
              try{  
                  numero=1+numeroAleatorios.nextInt(5);
              // do{
                //   numero=1+numeroAleatorios.nextInt(5);
               //}while(numero!=4 && numero!=5 );
  
              switch(numero){
                case 1:
                   monto=numeroAleatorios.nextInt(10);
                   Thread.sleep((int)(Math.random()*1000));
                   cuenta.Depositar(monto,nombre);
  
                    break;
                case 2: 
                    monto=numeroAleatorios.nextInt(10);
                   Thread.sleep((int)(Math.random()*1000));
                    cuenta.Retirar(monto,nombre);
   
                    break;
                case 3: 
                    Thread.sleep((int)(Math.random()*1000));
                    cuenta.Consultar(nombre);
            
  
                    break;
                case 4: 
                    Thread.sleep((int) (Math.random()*1000));
                    cuentadestino=numeroAleatorios.nextInt(5);
                    monto=numeroAleatorios.nextInt(10);
                   this.banco.Trasnferir(cuenta,cuentadestino , monto);
                     
                    break;
                case 5: 
                    Thread.sleep((int) (Math.random()*1000));                                        
                    monto=numeroAleatorios.nextInt(10);
                   if(cuenta.getBanco2()==0){
                      // System.out.println(cuenta.getBanco2());
          intercuentadestino=numeroAleatorios.nextInt(5);
                      // System.out.println(" el random es:"+numero);
                    banco2.transferirOtro(cuenta,monto ,intercuentadestino);
                    
                    }
                   else if(cuenta.getBanco2()==1){
                       //System.out.println(cuenta.getBanco2());
                                           do{
                        
                    
                       intercuentadestino=numeroAleatorios.nextInt(10);
                    }while(intercuentadestino!=0 || intercuentadestino!=1 || intercuentadestino!=2
                            || intercuentadestino!=3 || intercuentadestino!=4);
                      
                       //System.out.println("el ranmdon es:"+numero);
                       this.banco.transferirOtro(cuenta, monto,intercuentadestino);
                   }
                    break;
                default:
                    System.out.println(" dato erroneo");
                    break;
            }
               
            
            i++;
             } catch (InterruptedException ex) {
                    Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        
    }
   
      
       
}
