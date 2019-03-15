package Paquete1;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Banco {

    Cuenta cuentas[];
    Banco bancos[];
    private Lock cierrebanco = new ReentrantLock();
    private Condition SaldoSuficiente;
    private Lock cierrebanco2 = new ReentrantLock();
    private Condition SaldoSuficiente2;
   // private Editor ed= new Editor();
    public Banco(Cuenta[] cuentas) {
        this.cuentas = cuentas;
        this.bancos = bancos;
        SaldoSuficiente = cierrebanco.newCondition();
        SaldoSuficiente2 = cierrebanco2.newCondition();
    }

   

    public void Trasnferir(Cuenta origen, int destino, int monto) throws InterruptedException {
        cierrebanco.lock();
        try {
            String s=null;
            System.out.println("************************** trasnferencia Dentro del mismo banco **************************");
            System.out.println("*************************" + origen.getNombre() + "***********");
            System.out.println("la cuenta: " + cuentas[destino].getNombre() + " recibira: " + monto);
            int cuenta = origen.getSaldo();

            int cuenta2 = cuentas[destino].getSaldo();
          
            while (cuenta < monto || cuenta2+monto>20) {
                SaldoSuficiente.await();

            }
            System.out.println("despues de esperar");
            origen.setSaldo(cuenta -= monto);
            cuentas[destino].setSaldo(cuenta2 += monto);
            System.out.println("saldo  de la cuenta origen despues detransferir: " + origen.getSaldo() + " Saldo de la cuenta a la que se transfirio: " + cuentas[destino].getSaldo());       
            SaldoSuficiente.signalAll();
           //ed.guardarArchivo(s, s);
        } finally {
            cierrebanco.unlock();
        }
        
    }

    public void transferirOtro(Cuenta cuenta, int monto, int destino) throws InterruptedException {
        cierrebanco2.lock();
        try{
            System.out.println("************************** trasnferencia interbancaria **************************");
            System.out.println("*************************" + cuenta.getNombre() + "***********");
            System.out.println("la cuenta: " + cuentas[destino].getNombre() + " recibira :" + monto);
            int cuentaR = cuenta.getSaldo();

            int cuenta2 = cuentas[destino].getSaldo();

            while (cuentaR < monto || cuenta2+monto>20) {
                SaldoSuficiente2.await();

            }
            System.out.println("despues de esperar");
            cuenta.setSaldo(cuentaR -= monto);
            cuentas[destino].setSaldo(cuenta2 += monto);
            System.out.println("saldo  de la cuenta origen despues detransferir: " + cuenta.getSaldo() + " Saldo de la cuenta a la que se transfirio: " + cuentas[destino].getSaldo());
            SaldoSuficiente2.signalAll();
            
            
        }finally{
            cierrebanco2.unlock();
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
