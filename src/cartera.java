/**
 * @author Ismael LM Pablo VG Badr AZ
 * @version 1.0.0
 * @since 18-10-2022
 */

import java.util.Scanner;
import Exceptions.NegativeException;
import Exceptions.NoHayAcciones;
import java.util.PriorityQueue;
import java.util.Queue;

public class cartera {
    static int id = 0;
    static double capitalTotal;
    static double gananciaTotal;
    static Queue<Accion> cola = new PriorityQueue<Accion>();

    public static void comprar() {
        Scanner tecladoComprar = new Scanner(System.in);
        int opcion = -1;

        do {
            try {
                opcion=-1;
                System.out.println("¿Cuantas acciones desea comprar?");
                int numeroAcciones = tecladoComprar.nextInt();
                System.out.println("Introduce el precio de las acciones");
                int precioAcciones = tecladoComprar.nextInt();
                if(numeroAcciones<0 || precioAcciones<0) {
                    opcion = 0;
                    throw new NegativeException("\nNo puedes intoducir numeros negativos.\n");
                }
                id++;
                cola.add(new Accion(id, numeroAcciones, precioAcciones));
            } catch (NegativeException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != -1);
    }

    
    /** 
     * @param colaPrioridad
     */
    public static void mostrarColaPrioridad(Queue<Accion> colaPrioridad) {
        System.out.println("\n--Lista de Acciones actuales--");
        Queue<Accion> temp = new PriorityQueue<Accion>();
        while (!colaPrioridad.isEmpty()) {
            Accion p = colaPrioridad.remove();
            System.out.println("Paquete: " + p.getId() + " Numero de acciones: " + p.getNumAcciones());
            temp.add(p);
        }
        while (!temp.isEmpty()) {
            Accion p = temp.remove();
            colaPrioridad.add(p);
        }
    }
    
    
    
    /** 
     * @param colaPrioridad
     */
    public static void vender(Queue<Accion> colaPrioridad) {
        
        Scanner tecladoVender = new Scanner(System.in);
        double beneficio;
        int c, opcion = -1, numeroVender = 0, precioVenta = 0;

        do {
            try {
                opcion = -1;
                System.out.println("¿Cuantas acciones quieres vender?");
                numeroVender = tecladoVender.nextInt();
                System.out.println("¿A que precio quieres vender las acciones?");
                precioVenta = tecladoVender.nextInt();
                if(numeroVender<0 || precioVenta<0) {
                    opcion = 0;
                    throw new NegativeException("\nNo puedes intoducir numeros negativos.\n");
                }
            } catch (NegativeException e) {
                System.out.println(e.getMessage());
            }
        } while (opcion != -1);
        
        Queue<Accion> temporal = new PriorityQueue<Accion>();

        try {
            while (!colaPrioridad.isEmpty()) {
                Accion p = colaPrioridad.remove();
                beneficio = precioVenta - p.getPrecio();
                int a = p.getNumAcciones();
                if(numeroVender < a) {
                    gananciaTotal = beneficio * numeroVender;
                } else {
                    gananciaTotal = beneficio * a;
                }
                c = numeroVender - a; // C es lo que nos falta por vender en positivo
                a = a - numeroVender;
                if(a < 0) {
                    beneficio = 0;
                    if(colaPrioridad.isEmpty()) {
                        temporal.add(p);
                        throw new NoHayAcciones("No tienes tantas Acciones para vender.");
                    }
                    Accion p2 = colaPrioridad.remove();
                    beneficio = precioVenta - p2.getPrecio();
                    int b = p2.getNumAcciones();
                    double beneficio2 = beneficio * c;
                    gananciaTotal = gananciaTotal + beneficio2;
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
        } catch (NoHayAcciones e) {
            System.out.println(e.getMessage());
        } finally {
            while (!temporal.isEmpty()) {
                Accion p = temporal.remove();
                colaPrioridad.add(p);
            }
        }
        System.out.println("\nGanancia total es de: " + gananciaTotal);
    }

    public static void actualizar(){
        capitalTotal = capitalTotal + gananciaTotal;
        gananciaTotal = 0;
        if(capitalTotal==0) {
            System.out.println("No has vendido ninguna accion.");
        } else {
            System.out.println("El capital actual es de: " + capitalTotal);
        }
    }
}