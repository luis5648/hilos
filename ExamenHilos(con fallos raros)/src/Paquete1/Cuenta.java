package Paquete1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cuenta {

    private int saldo;
    private int Limite;
    private String nombre;
    private int banco2;
    private Lock cierrecuenta = new ReentrantLock();
    private Condition SaldoSuficiente;
    private Editor ed= new Editor();
    public Cuenta(int saldo, int Limite, String nombre, int banco2) {
        this.saldo = saldo;
        this.Limite = Limite;
        this.nombre = nombre;
        this.banco2 = banco2;
        SaldoSuficiente=cierrecuenta.newCondition();
    }
  
    public   void Depositar(int monto, String nombre) throws InterruptedException {
        cierrecuenta.lock();
        try {
            String s = null;
            System.out.println("**********************************" + nombre + "**********************************");
            s+="**********************************" + nombre + "**********************************\n";
      
            
            int suma = 0;

            suma = getSaldo() + monto;
            while(suma>=21){
                SaldoSuficiente.await();
            }

            setSaldo(suma);
            System.out.println("monto ah depositar: " + monto);
            s+="\n monto ah depositar: " + monto;
          

            System.out.println("el saldo final es: " + getSaldo());
            s+="\n el saldo final es: " + getSaldo();
            SaldoSuficiente.signalAll();
            ed.guardarArchivo(nombre, s);
        } finally {
            cierrecuenta.unlock();
        }
    }

    public  void Retirar(int monto, String Nombre) throws InterruptedException {
        cierrecuenta.lock();
        try {
            String s = null;
            System.out.println("****************************" + Nombre + "**********************************");
            s+="****************************" + Nombre + "**********************************\n";
        
            System.out.println("Monto ah retirar" + monto);
            s+="Monto ah retirar" + monto;
            int resta = 0;
            
            resta = getSaldo() - monto;
            while(resta<0){
                SaldoSuficiente.await();
            }
            
            setSaldo(resta);
        
            
            
            System.out.println("el saldo final es:" + getSaldo());
            s+="\n el saldo final es:" + getSaldo();
           ed.guardarArchivo(Nombre, s); 
        } finally {
            SaldoSuficiente.signalAll();
            cierrecuenta.unlock();
        }
    }

    public synchronized void Consultar(String nombre) {
        String s = null;
        System.out.println("****************************" + nombre + "**********************************");
        s+="****************************" + nombre + "**********************************";
        System.out.println("Consulta");
        s+="\n Consulta";
        s+="\n";
        System.out.println(" la consulta del saldo  final es: " + getSaldo());
        s+="la consulta del saldo  final es: " + getSaldo();
        ed.guardarArchivo(nombre, s);
    }

    public int getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getLimite() {
        return Limite;
    }

    public synchronized void setLimite(int Limite) {
        this.Limite = Limite;
    }

    public String getNombre() {
        return nombre;
    }

    public synchronized void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getBanco2() {
        return banco2;
    }

    public void setBanco2(int banco2) {
        this.banco2 = banco2;
    }


}
