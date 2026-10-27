package co.com.pichincha.stepdefinitions;

import co.com.pichincha.models.DatosCompra;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.Map;

public class Hooks {

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @ParameterType(".*")
    public Actor actor(String nombre) {
        return OnStage.theActorCalled(nombre);
    }

    @DataTableType
    public DatosCompra datosCompra(Map<String, String> fila) {
        return new DatosCompra(fila.get("nombre"), fila.get("pais"), fila.get("ciudad"),
                fila.get("tarjeta"), fila.get("mes"), fila.get("anio"));
    }
}
