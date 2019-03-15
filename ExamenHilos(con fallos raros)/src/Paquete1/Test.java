package Paquete1;

public class Test {

    public static void main(String[] args) {
        Cuenta[] cuentas = new Cuenta[5];
        Cuenta[] cuentas2 = new Cuenta[5];
        Banco[] bancos = new Banco[2];
        
        
        Banco bancoPrincipal;

        Usuario[] usuarios = new Usuario[10];
        
        String users[] = {"Abel","Luis","Alan","Mirsha","Pancho"};
       

        for (int i = 0; i < users.length; i++) {
            cuentas[i] = new Cuenta(10, 20, users[i]+" banamex " + i,0);
            cuentas2[i] = new Cuenta(10, 20, "josé "+i+" banorte " + i,1);

        }
        bancos[0] = new Banco(cuentas);
        bancos[1] = new Banco(cuentas2);
       
        
        

        usuarios[0] = new Usuario(cuentas[0], bancos[0],users[0],bancos[1]);
        usuarios[1] = new Usuario(cuentas[1], bancos[0],users[1],bancos[1]);
        usuarios[2] = new Usuario(cuentas[2], bancos[0],users[2],bancos[1]);
        usuarios[3] = new Usuario(cuentas[3], bancos[0],users[3],bancos[1]);
        usuarios[4] = new Usuario(cuentas[4], bancos[0],users[4],bancos[1]);

        usuarios[5] = new Usuario(cuentas2[0], bancos[1],"josé 0",bancos[0]);
        usuarios[6] = new Usuario(cuentas2[1], bancos[1],"josé 1",bancos[0]);
        usuarios[7] = new Usuario(cuentas2[2], bancos[1],"josé 2",bancos[0]);
        usuarios[8] = new Usuario(cuentas2[3], bancos[1],"josé 3",bancos[0]);
        usuarios[9] = new Usuario(cuentas2[4], bancos[1],"josé 4",bancos[0]);

        for (int i = 0; i < 10; i++) {
           usuarios[i].start();    
    }
        
        

    }
}
