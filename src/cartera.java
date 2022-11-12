import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Queue;

public class cartera {
    static int id = 0;
    static double capitalTotal;
    static double gananciaTotal;
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
    
    
    public static void vender(Queue<Accion> colaPrioridad){
        Scanner tecladoVender = new Scanner(System.in);
        System.out.println("¿Cuantas acciones quieres vender?");
        int numeroVender = tecladoVender.nextInt();
        System.out.println("¿A que precio queires vender las acciones?");
        int precioVenta = tecladoVender.nextInt();
        double beneficio;
        int c;

        Queue<Accion> temporal = new PriorityQueue<Accion>();

        while (!colaPrioridad.isEmpty()) {
            Accion p = colaPrioridad.remove();
            beneficio = precioVenta - p.getPrecio();
            int a = p.getNumAcciones();
            if(numeroVender < a) {
                gananciaTotal = beneficio * numeroVender;
                // System.out.println("La ganancia total es: " + gananciaTotal);
            } else {
                gananciaTotal = beneficio * a;
                // System.out.println("La ganancia total es: " + gananciaTotal);
            }
            c = numeroVender - a; // C es lo que nos falta por vender en positivo
            a = a - numeroVender;
            if(a < 0) {
                beneficio = 0;
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
        while (!temporal.isEmpty()) {
            Accion p = temporal.remove();
            colaPrioridad.add(p);
        }
        System.out.println("\nGanancia total es de: " + gananciaTotal);
    }

    public static void actualizar(){
        capitalTotal = capitalTotal + gananciaTotal;
        gananciaTotal = 0;
        System.out.println("El capital actual es de: " + capitalTotal);
    }
}
