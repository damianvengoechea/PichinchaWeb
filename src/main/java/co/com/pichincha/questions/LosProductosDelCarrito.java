package co.com.pichincha.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

import static co.com.pichincha.userinterfaces.CarritoPage.NOMBRES_PRODUCTOS;

public class LosProductosDelCarrito implements Question<List<String>> {

    public static LosProductosDelCarrito listados() {
        return new LosProductosDelCarrito();
    }

    @Override
    public List<String> answeredBy(Actor actor) {
        return Text.ofEach(NOMBRES_PRODUCTOS).answeredBy(actor).stream()
                .map(String::trim)
                .toList();
    }
}
