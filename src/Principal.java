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
        Queue<Accion> cola = cartera.cola;
        Scanner leer = new Scanner(System.in);
        do {
            System.out.println("\n1.Comprar acciones\n2.Vender acciones\n3.Ganancias del capital hasta el momento\n4.Salir");
            opcion = leer.nextInt();

            switch (opcion){
                case 1:
                    System.out.println("\n--Menu de Compra--");
                    cartera.comprar();
                break;
                case 2:
                    try {
                        if(cola.isEmpty()) {
                            throw new EmptyException("No hay acciones para poder vender.");
                        } else {
                            System.out.println("\n--Información de las acciones actuales--");
                            if(cola.isEmpty()) {
                                System.out.println("La cola esta vacia.");
                            } else {
                                cartera.mostrarColaPrioridad(cola);
                            }
                            System.out.println("\n--Menu de Venta--");
                            cartera.vender(cola);
                        }
                    } catch (EmptyException e) {
                        System.out.println("\n" + e.getMessage());
                    }
                break;
                case 3:
                    System.out.println("\n--Capital Total--");
                    cartera.actualizar();
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
