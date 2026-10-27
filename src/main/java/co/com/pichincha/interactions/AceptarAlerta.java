package co.com.pichincha.interactions;

import co.com.pichincha.exceptions.ValidacionFallidaException;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static co.com.pichincha.exceptions.ValidacionFallidaException.ALERTA_INCORRECTA;

/**
 * Espera la alerta nativa del navegador (window.alert), valida su mensaje y la acepta.
 */
public class AceptarAlerta implements Interaction {

    private static final Duration TIEMPO_MAXIMO = Duration.ofSeconds(15);

    private final String mensajeEsperado;

    public AceptarAlerta(String mensajeEsperado) {
        this.mensajeEsperado = mensajeEsperado;
    }

    public static AceptarAlerta conMensaje(String mensajeEsperado) {
        return Tasks.instrumented(AceptarAlerta.class, mensajeEsperado);
    }

    @Override
    @Step("{0} acepta la alerta con el mensaje '#mensajeEsperado'")
    public <T extends Actor> void performAs(T actor) {
        Alert alerta = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), TIEMPO_MAXIMO)
                .until(ExpectedConditions.alertIsPresent());
        String mensaje = alerta.getText();
        alerta.accept();
        if (!mensaje.contains(mensajeEsperado)) {
            throw new ValidacionFallidaException(ALERTA_INCORRECTA + ": '" + mensaje + "'");
        }
    }
}
