package co.com.pichincha.tasks;

import co.com.pichincha.interactions.EsperarCantidadDeElementos;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.pichincha.userinterfaces.CarritoPage.FILAS_PRODUCTOS;
import static co.com.pichincha.userinterfaces.InicioPage.ENLACE_CARRITO;

public class VisualizarCarrito implements Task {

    private final int cantidadProductos;

    public VisualizarCarrito(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public static VisualizarCarrito conProductos(int cantidadProductos) {
        return Tasks.instrumented(VisualizarCarrito.class, cantidadProductos);
    }

    @Override
    @Step("{0} visualiza el carrito con #cantidadProductos productos")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(ENLACE_CARRITO),
                EsperarCantidadDeElementos.de(FILAS_PRODUCTOS, cantidadProductos)
        );
    }
}
