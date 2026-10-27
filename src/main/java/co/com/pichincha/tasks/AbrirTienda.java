package co.com.pichincha.tasks;

import co.com.pichincha.interactions.EsperarQueSeaVisible;
import co.com.pichincha.userinterfaces.InicioPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

import static co.com.pichincha.userinterfaces.InicioPage.LISTA_PRODUCTOS;

public class AbrirTienda implements Task {

    public static AbrirTienda demoblaze() {
        return Tasks.instrumented(AbrirTienda.class);
    }

    @Override
    @Step("{0} abre la tienda Demoblaze")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.browserOn().the(InicioPage.class),
                EsperarQueSeaVisible.elElemento(LISTA_PRODUCTOS)
        );
    }
}
