import java.util.Scanner;
import javax.swing.JOptionPane; //Quitar cuando se elimine el main de prueba
import javax.swing.JSpinner.NumberEditor;

import java.util.PriorityQueue;
import java.util.Queue;

public class cartera {
    static int id = 0;
    static Queue<Accion> cola = new PriorityQueue<Accion>();

    public static void comprar() {
        id++;
        Scanner tecladoComprar = new Scanner(System.in);
        System.out.println("¿Cuantas acciones desea comprar?");
        int numeroAcciones = tecladoComprar.nextInt();
        System.out.println("Introduce el precio de las acciones");
        int precioAcciones = tecladoComprar.nextInt();

        cola.add(new Accion(id, numeroAcciones, precioAcciones));
    }

    public static void mostrarColaPrioridad(Queue<Accion> colaPrioridad) {
        System.out.println("mostrar");
        Queue<Accion> temp = new PriorityQueue<Accion>();
        while (!colaPrioridad.isEmpty()) {
            Accion p = colaPrioridad.remove();
            System.out.println("Numero de acciones: " + p.getNumAcciones());
            temp.add(p);
        }
        while (!temp.isEmpty()) {
            Accion p = temp.remove();
            colaPrioridad.add(p);
        }
    }
    
    
    public static void vender(Queue<Accion> colaPrioridad){

        Scanner tecladoVender = new Scanner(System.in);
        System.out.println("¿Cuantas acciones quieres vender?");
        int numeroVender = tecladoVender.nextInt();
        System.out.println("¿A que precio queires vender las acciones?");
        int precioVenta = tecladoVender.nextInt();
        int ganaciaTotal;

        Queue<Accion> temporal = new PriorityQueue<Accion>();

        while (!colaPrioridad.isEmpty()) {
            Accion p = colaPrioridad.remove();
            int a = p.getNumAcciones();
            a = a - numeroVender;
            if(a < 0) {
                Accion p2 = colaPrioridad.remove();
                int b = p2.getNumAcciones();
                b = b + a;
                p2.setNumAcciones(b);
                if(b!=0) {
                    temporal.add(p2);
                }
            } else {
                p.setNumAcciones(a);
                if(a!=0) {
                    temporal.add(p);
                }
            }
        }
        while (!temporal.isEmpty()) {
            Accion p = temporal.remove();
            colaPrioridad.add(p);
        }
    }

    public void actualizar(){
        
    }

    public static void main(String[] args) throws Exception { //Main de prueba//
        int opcion = -1;
        Scanner leer = new Scanner(System.in);
        do {
            System.out.println("1.Comprar acciones\n2.Vender acciones\n3.Ganancias del capital hasta el momento\n4.Salir");
            opcion = leer.nextInt();

            if(cola.isEmpty()) {
                System.out.println("La cola esta vacia.");
            } else {
                cartera.mostrarColaPrioridad(cola);
            }

            switch (opcion){
                case 1:
                    System.out.println("--Menu de Compra--");
                    cartera.comprar();
                break;
                case 2:
                    System.out.println("--Venta--");
                    cartera.vender(cola);
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
