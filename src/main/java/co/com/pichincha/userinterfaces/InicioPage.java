package co.com.pichincha.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/**
 * Sin @DefaultUrl: se abre la URL configurada en webdriver.base.url (serenity.conf) según el ambiente.
 */
public class InicioPage extends PageObject {

    public static final Target LISTA_PRODUCTOS = Target.the("lista de productos")
            .located(By.cssSelector("#tbodyid .card-title a"));
    public static final Target PRODUCTO = Target.the("producto {0}")
            .locatedBy("//a[@class='hrefch' and normalize-space(text())='{0}']");
    public static final Target BOTON_SIGUIENTE = Target.the("botón Next de la paginación")
            .located(By.id("next2"));
    public static final Target ENLACE_INICIO = Target.the("enlace Home")
            .located(By.xpath("//a[@class='nav-link' and contains(text(),'Home')]"));
    public static final Target ENLACE_CARRITO = Target.the("enlace Cart")
            .located(By.id("cartur"));
}
