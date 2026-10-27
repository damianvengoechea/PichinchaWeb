package co.com.pichincha.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.pichincha.userinterfaces.CarritoPage.PRECIOS_PRODUCTOS;
import static co.com.pichincha.userinterfaces.CarritoPage.TOTAL;

public class ElTotalDelCarrito implements Question<Integer> {

    private final boolean calcularDesdePrecios;

    private ElTotalDelCarrito(boolean calcularDesdePrecios) {
        this.calcularDesdePrecios = calcularDesdePrecios;
    }

    /** Total mostrado por la página en el carrito. */
    public static ElTotalDelCarrito mostrado() {
        return new ElTotalDelCarrito(false);
    }

    /** Suma de los precios de cada fila del carrito. */
    public static ElTotalDelCarrito calculadoConLosPrecios() {
        return new ElTotalDelCarrito(true);
    }

    @Override
    public Integer answeredBy(Actor actor) {
        if (calcularDesdePrecios) {
            return Text.ofEach(PRECIOS_PRODUCTOS).answeredBy(actor).stream()
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .sum();
        }
        return Integer.parseInt(Text.of(TOTAL).answeredBy(actor).trim());
    }
}
