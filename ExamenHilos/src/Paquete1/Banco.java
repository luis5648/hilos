package Paquete1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Banco {

    Cuenta cuentas[];
    Banco bancos[];
    private Lock cierrebanco= new ReentrantLock();
    private Condition SaldoSuficiente;

    public Banco(Cuenta[] cuentas) {
        this.cuentas = cuentas;
        this.bancos = bancos;
        SaldoSuficiente=cierrebanco.newCondition();
    }



    public  void Trasnferir(Cuenta origen, int destino, int monto) throws InterruptedException {
        cierrebanco.lock();
        try {
            System.out.println("*************************"+origen.getNombre()+"***********");
            System.out.println("");
            System.out.println("la cuenta: "+cuentas[destino].getNombre()+" recibira :   "+monto);
            int cuenta = origen.getSaldo();
            
            int cuenta2 = cuentas[destino].getSaldo();
            System.out.println("");
            System.out.println("saldo actual"+cuentas[destino].getSaldo());
            
            while (cuenta <monto ) {
               SaldoSuficiente.await();
             
            }
            System.out.println("despues de esperar");
            origen.setSaldo(cuenta -= monto);
            cuentas[destino].setSaldo(cuenta2 += monto);
            System.out.println("");
            System.out.println("saldo  de la cuenta origen"+origen.getSaldo()+" final despues de  trasnferir:"+cuentas[destino].getSaldo());
            SaldoSuficiente.signalAll();
        } finally {
            cierrebanco.unlock();
        }
    }

    public synchronized void transferirOtro(Cuenta destino1,Cuenta destino2, int posicion1,int posicion2, int monto) {
        cierrebanco.lock();
        try {
            System.out.println("**************la transferencia se genera del  usuario "+destino1.getNombre()+" hacia el usuario"+destino2+"***********");
            System.out.println("");
            System.out.println("la cuenta: "+cuentas[posicion1].getNombre()+" transferira el monto de   :   $"+monto +"$  hacia la cuenta"+cuentas[posicion2].getNombre());
            System.out.println("");
            int cuenta = destino1.getSaldo();
            int cuenta2 = destino2.getSaldo();
            
            
            while (cuenta <monto ) {
                try {
                    SaldoSuficiente.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
                }
             
            }
            System.out.println("");
            System.out.println("saldo actual del usuario que va ah realizar la transferencia  $$"+cuenta);
            System.out.println("");
            System.out.println("saldo actual del usuario recibira la transferencia $$"+cuenta2);
            
            
            System.out.println("despues de esperar");
            destino1.setSaldo(cuenta -= monto);
            cuentas[posicion2].setSaldo(cuenta2 += monto);
            System.out.println("");
            System.out.println("saldo  de la cuenta origen"+destino1.getSaldo()+" final despues de  trasnferir:"+cuentas[posicion1].getSaldo());
            System.out.println("");
            System.out.println("");
            System.out.println("saldo de la cuenta "+destino1.getSaldo()+" al recibir la transferencia:"+cuentas[posicion2].getSaldo());
            SaldoSuficiente.signalAll();
        } finally {
            cierrebanco.unlock();
        }
        
        
        

    }

    public synchronized Cuenta[] getCuentas() {
        return cuentas;
    }

    public synchronized void setCuentas(Cuenta[] cuentas, int monto) {

        this.cuentas = cuentas;
    }

    public synchronized Banco[] getBancos() {
        return bancos;
    }

    public synchronized void setBancos(Banco[] bancos) {
        this.bancos = bancos;
    }

}
