package co.com.pichincha.models;

public record DatosCompra(String nombre, String pais, String ciudad, String tarjeta, String mes, String anio) {

    @Override
    public String toString() {
        return nombre + " (" + ciudad + ", " + pais + ")";
    }
}
