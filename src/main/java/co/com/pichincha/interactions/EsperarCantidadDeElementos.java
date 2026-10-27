package co.com.pichincha.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Espera a que un Target tenga exactamente la cantidad indicada de elementos
 * (útil para tablas que se llenan con peticiones AJAX una fila a la vez, como el carrito).
 */
public class EsperarCantidadDeElementos implements Interaction {

    private static final Duration TIEMPO_MAXIMO = Duration.ofSeconds(15);

    private final Target elementos;
    private final int cantidad;

    public EsperarCantidadDeElementos(Target elementos, int cantidad) {
        this.elementos = elementos;
        this.cantidad = cantidad;
    }

    public static EsperarCantidadDeElementos de(Target elementos, int cantidad) {
        return Tasks.instrumented(EsperarCantidadDeElementos.class, elementos, cantidad);
    }

    @Override
    @Step("{0} espera que #elementos tenga #cantidad elementos")
    public <T extends Actor> void performAs(T actor) {
        new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), TIEMPO_MAXIMO)
                .withMessage(() -> "Se esperaban " + cantidad + " elementos en " + elementos)
                .until(driver -> elementos.resolveAllFor(actor).size() == cantidad);
    }
}
