package co.com.pichincha.tasks;

import co.com.pichincha.interactions.EsperarQueSeaVisible;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.pichincha.userinterfaces.ConfirmacionCompraPage.TITULO;
import static co.com.pichincha.userinterfaces.FormularioCompraPage.BOTON_COMPRAR;

public class FinalizarCompra implements Task {

    public static FinalizarCompra ahora() {
        return Tasks.instrumented(FinalizarCompra.class);
    }

    @Override
    @Step("{0} finaliza la compra")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BOTON_COMPRAR),
                EsperarQueSeaVisible.elElemento(TITULO)
        );
    }
}
