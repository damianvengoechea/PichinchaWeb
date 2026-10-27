package co.com.pichincha.tasks;

import co.com.pichincha.interactions.EsperarQueSeaVisible;
import co.com.pichincha.models.DatosCompra;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.com.pichincha.userinterfaces.CarritoPage.BOTON_REALIZAR_PEDIDO;
import static co.com.pichincha.userinterfaces.FormularioCompraPage.CAMPO_ANIO;
import static co.com.pichincha.userinterfaces.FormularioCompraPage.CAMPO_CIUDAD;
import static co.com.pichincha.userinterfaces.FormularioCompraPage.CAMPO_MES;
import static co.com.pichincha.userinterfaces.FormularioCompraPage.CAMPO_NOMBRE;
import static co.com.pichincha.userinterfaces.FormularioCompraPage.CAMPO_PAIS;
import static co.com.pichincha.userinterfaces.FormularioCompraPage.CAMPO_TARJETA;

public class CompletarFormularioDeCompra implements Task {

    private final DatosCompra datos;

    public CompletarFormularioDeCompra(DatosCompra datos) {
        this.datos = datos;
    }

    public static CompletarFormularioDeCompra con(DatosCompra datos) {
        return Tasks.instrumented(CompletarFormularioDeCompra.class, datos);
    }

    @Override
    @Step("{0} completa el formulario de compra con los datos de #datos")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BOTON_REALIZAR_PEDIDO),
                EsperarQueSeaVisible.elElemento(CAMPO_NOMBRE),
                Enter.theValue(datos.nombre()).into(CAMPO_NOMBRE),
                Enter.theValue(datos.pais()).into(CAMPO_PAIS),
                Enter.theValue(datos.ciudad()).into(CAMPO_CIUDAD),
                Enter.theValue(datos.tarjeta()).into(CAMPO_TARJETA),
                Enter.theValue(datos.mes()).into(CAMPO_MES),
                Enter.theValue(datos.anio()).into(CAMPO_ANIO)
        );
    }
}
