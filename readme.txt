===============================================================================
 PRUEBA E2E - FLUJO DE COMPRA EN DEMOBLAZE (https://www.demoblaze.com/)
 Serenity BDD + Screenplay + Cucumber (Gherkin en español) + JUnit 5 + Gradle
===============================================================================

1. DESCRIPCIÓN
--------------
Prueba funcional automatizada (E2E) del flujo de compra de la tienda Demoblaze:
  1. Agregar dos productos al carrito.
  2. Visualizar el carrito (se validan productos y total = suma de precios).
  3. Completar el formulario de compra (Place Order).
  4. Finalizar la compra (Purchase) y validar el mensaje
     "Thank you for your purchase!" y el detalle (monto, tarjeta y nombre).

El escenario es un "Esquema del escenario" que se ejecuta con dos pares de
productos:
  - Samsung galaxy s6 + Nokia lumia 1520  (total esperado 1180 USD)
  - Sony vaio i5      + Iphone 6 32gb     (total esperado 1580 USD)


2. PRERREQUISITOS
-----------------
  - JDK 21 o superior (probado con OpenJDK 27). Variable JAVA_HOME configurada.
  - Google Chrome instalado (probado con Chrome 156).
    El driver lo descarga automáticamente Selenium Manager.
  - Conexión a internet (para el sitio y la descarga de dependencias).
  - NO es necesario instalar Gradle: se usa el wrapper incluido (gradlew).


3. EJECUCIÓN PASO A PASO
------------------------
  1) Clonar el repositorio:
       git clone https://github.com/damianvengoechea/PichinchaWeb
       cd PruebaPichincha

  2) Ejecutar las pruebas:
       Windows (PowerShell / CMD):  gradlew.bat clean test
       Linux / macOS / Git Bash:    ./gradlew clean test

  3) Ejecutar sin interfaz gráfica (headless):
       gradlew.bat clean test -Dheadless.mode=true

  4) Ejecutar solo el escenario por tag:
       gradlew.bat clean test -Dcucumber.filter.tags="@CompraExitosa"

  5) Seleccionar ambiente (definidos en src/test/resources/serenity.conf):
       gradlew.bat clean test -Denvironment=qa

  El reporte de Serenity se genera automáticamente al terminar (tarea
  "aggregate"), incluso si alguna prueba falla.

  También se puede ejecutar desde IntelliJ IDEA abriendo la clase
  src/test/java/co/com/pichincha/runners/CompraRunner.java y dando "Run".


4. REPORTES
-----------
  - Reporte generado por cada ejecución:
       target/site/serenity/index.html
  - Evidencia de la ejecución realizada (incluida en el repositorio):
       reportes/serenity/index.html   -> reporte Serenity con capturas por paso
       reportes/serenity/summary.txt  -> resumen en texto
       reportes/junit-xml/            -> resultados JUnit en XML
  En el reporte, cada escenario incluye el adjunto "Detalle de la compra"
  con el Id, monto, tarjeta, nombre y fecha que devolvió la página.


5. ESTRUCTURA DEL PROYECTO (patrón Screenplay)
----------------------------------------------
src/main/java/co/com/pichincha/
  userinterfaces/  Localizadores (Targets) de cada página:
                   InicioPage, DetalleProductoPage, CarritoPage,
                   FormularioCompraPage, ConfirmacionCompraPage
  tasks/           Tareas de negocio: AbrirTienda, AgregarAlCarrito,
                   VisualizarCarrito, CompletarFormularioDeCompra, FinalizarCompra
  interactions/    Interacciones propias: AceptarAlerta (alert nativo),
                   EsperarQueSeaVisible, EsperarCantidadDeElementos
  questions/       Preguntas: LosProductosDelCarrito, ElTotalDelCarrito,
                   LaConfirmacionDeCompra
  models/          DatosCompra (datos del formulario)
  exceptions/      ValidacionFallidaException (mensajes de error claros)
  utils/           Constantes
src/test/java/co/com/pichincha/
  runners/         CompraRunner (JUnit Platform Suite + Cucumber)
  stepdefinitions/ CompraStepDefinitions, Hooks (escenario, actor, DataTable)
src/test/resources/
  features/compra.feature   Escenario en Gherkin (español)
  serenity.conf             Configuración de navegador, ambientes y capturas


6. VERSIONES
------------
  Serenity BDD 5.4.1 | Cucumber 8.0.4 | Selenium 4.51.0 | JUnit 6.1.3
  Gradle 9.8.0 (wrapper) | Java release 21
