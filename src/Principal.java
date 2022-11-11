/**
 * @author Ismael LM Pablo VG Badr AZ
 * @version 1.0.0
 * @since 18-10-2022
 */


import java.util.Scanner;
import javax.swing.JOptionPane;
import java.util.Queue;

public class Principal {
    /**
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {
        /*
        * opcion servirá para seleccionar la opción en el menú
        * nacciones servirá para saber el número de acciones que se compran
        * pacciones servirá para saber el precio de las acciones a comprar
        */
        int opcion = -1;
        Scanner leer = new Scanner(System.in);
        do {
            System.out.println("1.Comprar acciones\n2.Vender acciones\n3.Ganancias del capital hasta el momento\n4.Salir");
            opcion = leer.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("--Menu de Compra--");
                    cartera.comprar();
                break;
                case 2:
                break;
                case 3:
                break;
                case 4:
                System.out.println("Adios");
                break;
                default:
                JOptionPane.showMessageDialog(null, "Valor incorrecto");
            }
        } while(opcion!=4);
        leer.close();
    }
}
