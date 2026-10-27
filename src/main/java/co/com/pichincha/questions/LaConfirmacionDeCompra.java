package co.com.pichincha.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static co.com.pichincha.userinterfaces.ConfirmacionCompraPage.DETALLE;
import static co.com.pichincha.userinterfaces.ConfirmacionCompraPage.TITULO;

public class LaConfirmacionDeCompra implements Question<String> {

    private final boolean detalle;

    private LaConfirmacionDeCompra(boolean detalle) {
        this.detalle = detalle;
    }

    public static LaConfirmacionDeCompra mensaje() {
        return new LaConfirmacionDeCompra(false);
    }

    /** Texto con Id, Amount, Card Number, Name y Date de la compra. */
    public static LaConfirmacionDeCompra detalle() {
        return new LaConfirmacionDeCompra(true);
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(detalle ? DETALLE : TITULO).answeredBy(actor).trim();
    }
}
