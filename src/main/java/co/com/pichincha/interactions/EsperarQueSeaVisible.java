package co.com.pichincha.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EsperarQueSeaVisible implements Interaction {

    private static final Duration TIEMPO_MAXIMO = Duration.ofSeconds(15);

    private final Target elemento;

    public EsperarQueSeaVisible(Target elemento) {
        this.elemento = elemento;
    }

    public static EsperarQueSeaVisible elElemento(Target elemento) {
        return Tasks.instrumented(EsperarQueSeaVisible.class, elemento);
    }

    @Override
    @Step("{0} espera que #elemento sea visible")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(elemento, isVisible()).forNoMoreThan(TIEMPO_MAXIMO)
        );
    }
}
