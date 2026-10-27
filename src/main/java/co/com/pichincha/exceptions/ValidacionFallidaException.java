package co.com.pichincha.exceptions;

public class ValidacionFallidaException extends AssertionError {

    public static final String PRODUCTOS_CARRITO_INCORRECTOS = "Los productos del carrito no son los esperados";
    public static final String TOTAL_CARRITO_INCORRECTO = "El total del carrito no corresponde a la suma de los precios";
    public static final String MENSAJE_CONFIRMACION_INCORRECTO = "El mensaje de confirmación de compra no es el esperado";
    public static final String DETALLE_COMPRA_INCORRECTO = "El detalle de la compra no contiene los datos esperados";
    public static final String ALERTA_INCORRECTA = "El mensaje de la alerta no es el esperado";

    public ValidacionFallidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ValidacionFallidaException(String mensaje) {
        super(mensaje);
    }
}
