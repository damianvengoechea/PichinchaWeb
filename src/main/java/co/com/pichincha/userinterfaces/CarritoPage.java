package co.com.pichincha.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CarritoPage {

    public static final Target FILAS_PRODUCTOS = Target.the("filas de productos del carrito")
            .located(By.cssSelector("#tbodyid tr"));
    public static final Target NOMBRES_PRODUCTOS = Target.the("nombres de los productos del carrito")
            .located(By.cssSelector("#tbodyid tr td:nth-child(2)"));
    public static final Target PRECIOS_PRODUCTOS = Target.the("precios de los productos del carrito")
            .located(By.cssSelector("#tbodyid tr td:nth-child(3)"));
    public static final Target TOTAL = Target.the("total del carrito")
            .located(By.id("totalp"));
    public static final Target BOTON_REALIZAR_PEDIDO = Target.the("botón Place Order")
            .located(By.xpath("//button[normalize-space(text())='Place Order']"));

    private CarritoPage() {
    }
}
