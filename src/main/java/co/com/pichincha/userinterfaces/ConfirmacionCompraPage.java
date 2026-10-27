package co.com.pichincha.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ConfirmacionCompraPage {

    public static final Target TITULO = Target.the("título de confirmación")
            .located(By.cssSelector(".sweet-alert h2"));
    public static final Target DETALLE = Target.the("detalle de la compra")
            .located(By.cssSelector(".sweet-alert p.lead"));
    public static final Target BOTON_OK = Target.the("botón OK")
            .located(By.cssSelector(".sweet-alert button.confirm"));

    private ConfirmacionCompraPage() {
    }
}
