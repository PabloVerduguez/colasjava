/**
 * @author Ismael LM Pablo VG Badr AZ
 * @version 1.0.0
 * @since 18-10-2022
 */

public class Accion implements Comparable<Accion> {
    private int id;
    private double precio;
    private int NumAcciones;

    //Getters and Setters
    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * @return double
     */
    public double getPrecio() {
        return precio;
    }

    
    /** 
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    /** 
     * @return int
     */
    public int getNumAcciones() {
        return NumAcciones;
    }

    
    /** 
     * @param numAcciones
     */
    public void setNumAcciones(int numAcciones) {
        NumAcciones = numAcciones;
    }

    Accion(int id, int NumAcciones, double precio) {
        this.id = id;
        this.NumAcciones = NumAcciones;
        this.precio = precio;
        
    }


    @Override
    public int compareTo(Accion o) {
        if (id < o.getId()) {
            return 1;
        } else if (id > o.getId()) {
            return -1;
        } else {
            return 0;
        }
    }
}