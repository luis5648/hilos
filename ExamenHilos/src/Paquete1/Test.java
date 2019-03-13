package Paquete1;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    
 
public static void main(String[] args) {

        
        Cuenta[] cuentas = new Cuenta[5];
        Cuenta[] cuentas2 = new Cuenta[5];
        Banco[] bancos = new Banco[2];
        Banco bancoPrincipal;
        

        Usuario[] usuarios = new Usuario[10];
       

        for (int i = 0; i < 5; i++) {
            cuentas[i] = new Cuenta(10, 20, "alan"+ i+"bancomer " + i);
            cuentas2[i] = new Cuenta(10, 20, "arturo"+i+" Santander" + i);

        }
        bancos[0] = new Banco(cuentas);
        bancos[1] = new Banco(cuentas2);

        usuarios[0] = new Usuario(cuentas[0], bancos[0],"alan 0");
        usuarios[1] = new Usuario(cuentas[1], bancos[0],"alan 1");
        usuarios[2] = new Usuario(cuentas[2], bancos[0],"alan 2");
        usuarios[3] = new Usuario(cuentas[3], bancos[0],"alan 3");
        usuarios[4] = new Usuario(cuentas[4], bancos[0],"alan 4");

        usuarios[5] = new Usuario(cuentas2[0], bancos[1],"arturo 0");
        usuarios[6] = new Usuario(cuentas2[1], bancos[1],"arturo 1");
        usuarios[7] = new Usuario(cuentas2[2], bancos[1],"arturo 2");
        usuarios[8] = new Usuario(cuentas2[3], bancos[1],"arturo 3");
        usuarios[9] = new Usuario(cuentas2[4], bancos[1],"arturo 4");
        
        for (int i = 0; i < 4; i++) {
           usuarios[i].start();    
    }
 

        

    }


}
