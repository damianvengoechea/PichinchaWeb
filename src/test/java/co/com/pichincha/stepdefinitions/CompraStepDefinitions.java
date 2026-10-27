package co.com.pichincha.stepdefinitions;

import co.com.pichincha.exceptions.ValidacionFallidaException;
import co.com.pichincha.models.DatosCompra;
import co.com.pichincha.questions.ElTotalDelCarrito;
import co.com.pichincha.questions.LaConfirmacionDeCompra;
import co.com.pichincha.questions.LosProductosDelCarrito;
import co.com.pichincha.tasks.AbrirTienda;
import co.com.pichincha.tasks.AgregarAlCarrito;
import co.com.pichincha.tasks.CompletarFormularioDeCompra;
import co.com.pichincha.tasks.FinalizarCompra;
import co.com.pichincha.tasks.VisualizarCarrito;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;

import java.util.List;

import static co.com.pichincha.exceptions.ValidacionFallidaException.DETALLE_COMPRA_INCORRECTO;
import static co.com.pichincha.exceptions.ValidacionFallidaException.MENSAJE_CONFIRMACION_INCORRECTO;
import static co.com.pichincha.exceptions.ValidacionFallidaException.PRODUCTOS_CARRITO_INCORRECTOS;
import static co.com.pichincha.exceptions.ValidacionFallidaException.TOTAL_CARRITO_INCORRECTO;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class CompraStepDefinitions {

    private static final String TOTAL_CARRITO = "totalCarrito";
    private static final String DATOS_COMPRA = "datosCompra";

    @Dado("que {actor} ingresa a la tienda Demoblaze")
    public void ingresaALaTienda(Actor actor) {
        actor.wasAbleTo(AbrirTienda.demoblaze());
    }

    @Cuando("agrega al carrito los productos {string} y {string}")
    public void agregaAlCarritoLosProductos(String producto1, String producto2) {
        theActorInTheSpotlight().attemptsTo(
                AgregarAlCarrito.elProducto(producto1),
                AgregarAlCarrito.elProducto(producto2)
        );
    }

    @Cuando("visualiza el carrito de compras")
    public void visualizaElCarrito() {
        theActorInTheSpotlight().attemptsTo(VisualizarCarrito.conProductos(2));
    }

    @Entonces("debería ver en el carrito los productos {string} y {string}")
    public void deberiaVerEnElCarritoLosProductos(String producto1, String producto2) {
        theActorInTheSpotlight().should(
                seeThat(LosProductosDelCarrito.listados(), containsInAnyOrder(producto1, producto2))
                        .orComplainWith(ValidacionFallidaException.class, PRODUCTOS_CARRITO_INCORRECTOS)
        );
    }

    @Entonces("el total del carrito debería ser la suma de los precios de los productos")
    public void elTotalDeberiaSerLaSumaDeLosPrecios() {
        Actor actor = theActorInTheSpotlight();
        Integer sumaPrecios = actor.asksFor(ElTotalDelCarrito.calculadoConLosPrecios());
        actor.should(
                seeThat(ElTotalDelCarrito.mostrado(), equalTo(sumaPrecios))
                        .orComplainWith(ValidacionFallidaException.class, TOTAL_CARRITO_INCORRECTO)
        );
        actor.remember(TOTAL_CARRITO, sumaPrecios);
    }

    @Cuando("completa el formulario de compra con los datos")
    public void completaElFormularioDeCompra(List<DatosCompra> datos) {
        DatosCompra datosCompra = datos.get(0);
        theActorInTheSpotlight().remember(DATOS_COMPRA, datosCompra);
        theActorInTheSpotlight().attemptsTo(CompletarFormularioDeCompra.con(datosCompra));
    }

    @Cuando("finaliza la compra")
    public void finalizaLaCompra() {
        theActorInTheSpotlight().attemptsTo(FinalizarCompra.ahora());
    }

    @Entonces("debería ver el mensaje de confirmación {string}")
    public void deberiaVerElMensajeDeConfirmacion(String mensajeEsperado) {
        theActorInTheSpotlight().should(
                seeThat(LaConfirmacionDeCompra.mensaje(), equalTo(mensajeEsperado))
                        .orComplainWith(ValidacionFallidaException.class, MENSAJE_CONFIRMACION_INCORRECTO)
        );
    }

    @Entonces("el detalle de la compra debería mostrar el total del carrito, la tarjeta y el nombre del cliente")
    public void elDetalleDeLaCompraDeberiaMostrarLosDatos() {
        Actor actor = theActorInTheSpotlight();
        Integer totalCarrito = actor.recall(TOTAL_CARRITO);
        DatosCompra datosCompra = actor.recall(DATOS_COMPRA);
        Serenity.recordReportData().withTitle("Detalle de la compra")
                .andContents(actor.asksFor(LaConfirmacionDeCompra.detalle()));
        actor.should(
                seeThat(LaConfirmacionDeCompra.detalle(), allOf(
                        containsString("Amount: " + totalCarrito + " USD"),
                        containsString("Card Number: " + datosCompra.tarjeta()),
                        containsString("Name: " + datosCompra.nombre())
                )).orComplainWith(ValidacionFallidaException.class, DETALLE_COMPRA_INCORRECTO)
        );
    }
}
