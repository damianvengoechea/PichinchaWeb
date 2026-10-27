package co.com.pichincha.tasks;

import co.com.pichincha.interactions.AceptarAlerta;
import co.com.pichincha.interactions.EsperarQueSeaVisible;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.com.pichincha.userinterfaces.DetalleProductoPage.BOTON_AGREGAR_AL_CARRITO;
import static co.com.pichincha.userinterfaces.DetalleProductoPage.NOMBRE_PRODUCTO;
import static co.com.pichincha.userinterfaces.InicioPage.ENLACE_INICIO;
import static co.com.pichincha.userinterfaces.InicioPage.LISTA_PRODUCTOS;
import static co.com.pichincha.userinterfaces.InicioPage.PRODUCTO;
import static co.com.pichincha.utils.Constantes.MENSAJE_PRODUCTO_AGREGADO;

/**
 * Abre el detalle del producto desde el catálogo, lo agrega al carrito y regresa al inicio.
 */
public class AgregarAlCarrito implements Task {

    private final String producto;

    public AgregarAlCarrito(String producto) {
        this.producto = producto;
    }

    public static AgregarAlCarrito elProducto(String producto) {
        return Tasks.instrumented(AgregarAlCarrito.class, producto);
    }

    @Override
    @Step("{0} agrega al carrito el producto '#producto'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                EsperarQueSeaVisible.elElemento(PRODUCTO.of(producto)),
                Click.on(PRODUCTO.of(producto)),
                EsperarQueSeaVisible.elElemento(NOMBRE_PRODUCTO),
                Click.on(BOTON_AGREGAR_AL_CARRITO),
                AceptarAlerta.conMensaje(MENSAJE_PRODUCTO_AGREGADO),
                Click.on(ENLACE_INICIO),
                EsperarQueSeaVisible.elElemento(LISTA_PRODUCTOS)
        );
    }
}
