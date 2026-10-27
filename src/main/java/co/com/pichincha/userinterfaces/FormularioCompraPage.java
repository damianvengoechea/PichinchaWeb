package co.com.pichincha.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class FormularioCompraPage {

    public static final Target CAMPO_NOMBRE = Target.the("campo nombre").located(By.id("name"));
    public static final Target CAMPO_PAIS = Target.the("campo país").located(By.id("country"));
    public static final Target CAMPO_CIUDAD = Target.the("campo ciudad").located(By.id("city"));
    public static final Target CAMPO_TARJETA = Target.the("campo tarjeta de crédito").located(By.id("card"));
    public static final Target CAMPO_MES = Target.the("campo mes").located(By.id("month"));
    public static final Target CAMPO_ANIO = Target.the("campo año").located(By.id("year"));
    public static final Target BOTON_COMPRAR = Target.the("botón Purchase")
            .located(By.xpath("//div[@id='orderModal']//button[normalize-space(text())='Purchase']"));

    private FormularioCompraPage() {
    }
}
