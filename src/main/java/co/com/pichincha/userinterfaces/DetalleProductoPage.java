package co.com.pichincha.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DetalleProductoPage {

    public static final Target NOMBRE_PRODUCTO = Target.the("nombre del producto")
            .located(By.cssSelector("#tbodyid h2.name"));
    public static final Target BOTON_AGREGAR_AL_CARRITO = Target.the("botón Add to cart")
            .located(By.xpath("//a[normalize-space(text())='Add to cart']"));

    private DetalleProductoPage() {
    }
}
