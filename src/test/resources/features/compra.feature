# language: es
@FlujoDeCompra
Característica: Flujo de compra en la tienda Demoblaze
  Como cliente de la tienda Demoblaze
  Quiero agregar productos al carrito y pagarlos
  Para recibir mi pedido

  Antecedentes:
    Dado que Damian ingresa a la tienda Demoblaze

  @CompraExitosa
  Esquema del escenario: Compra exitosa de dos productos
    Cuando agrega al carrito los productos "<producto1>" y "<producto2>"
    Y visualiza el carrito de compras
    Entonces debería ver en el carrito los productos "<producto1>" y "<producto2>"
    Y el total del carrito debería ser la suma de los precios de los productos
    Cuando completa el formulario de compra con los datos
      | nombre            | pais     | ciudad   | tarjeta          | mes | anio |
      | Damian Vengoechea | Colombia | Medellín | 4111111111111111 | 10  | 2026 |
    Y finaliza la compra
    Entonces debería ver el mensaje de confirmación "Thank you for your purchase!"
    Y el detalle de la compra debería mostrar el total del carrito, la tarjeta y el nombre del cliente

    Ejemplos:
      | producto1         | producto2        |
      | Samsung galaxy s6 | Nokia lumia 1520 |
      | Sony vaio i5      | Iphone 6 32gb    |
