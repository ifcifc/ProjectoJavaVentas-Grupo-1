# **Gestor de ventas Grupo A**
**Integrantes:**
 - Castellanos, Ignacio ( **ifcifc** )
 - Herr, Santiago ( **san-102** )

 **Consignas**
 Realizar un programa en Java Web, el cual cuente con las siguientes funcionalidades:
1. ABM artículos: Cargar artículos, editarlos y eliminarlos.

Los artículos deberán contener, cuanto mínimo, los siguientes datos: Código de Articulo, Nombre/descripción y precio. 

2. Stock: Se deberá poder ver y editar la cantidad de cada uno de los artículos cargados en el sistema.

 3. Usuarios: Para utilizar el sistema, se deberá en primer lugar ingresar con un usuario el cual, cuanto mínimo, posea un nombre de usuario y una contraseña.

Los mismos deberán ser catalogados en Cliente y Empleado.
Los clientes solo podrán utilizar las funcionalidades 4 , 5 y 6.
(Para el 6 solo sus compras) IMPORTANTE:  Debe existir una tercer categoria llamada MASTER que puede hacer ambas cosas.   .- 

4. Carrito de compra: Se deberá poder ver el listado de artículos en el sistema, agregarlos a un carrito, calcular su precio total y generar una factura.
Agregarlos a un carrito y decidir cuándo finalizar.
Una vez terminada la venta, el usuario deberá poder completar la compra. La cantidad comprada, deberá verse reflejada automáticamente en el stock.
La compra deberá guardarse en el sistema y deberíamos poder ver un historial de las mismas.
(Se puede considerar una compra por parte del usuario, pero se trataría de una venta para la empresa que utiliza el sistema, el stock debería reducirse). 

5. Módulo de Saldo: Contar con un gestor de dinero por cuenta, el cual permita agregar dinero y transferir a otro usuario. Si cuando se agrega saldo el importe es $1432 se tiene que aumentar el doble.
A su vez, si se aplica este módulo, en caso de que el usuario realice una compra, el sistema deberá descontar el valor total de la misma al dinero que posee.

6. Historial de ventas: Deberá existir una pantalla donde ver el listado de las ventas realizadas, y una opción para ver el detalle de cada una de ellas.


Apartado técnico:
El programa deberá ser realizado con MVC (Modelo vista controlador).
Deberá tener al menos tres controladores
La informacion se debera guardar en memoria (Podra ser gestionada con un singleton)
El programa deberá estar armado para ser levantado en Tomcat.
No se podrá utilizar ningún Framework, pero se permite importar librerías externas.
Condiciones de entrega:
- La entrega deberá hacerse por grupos de tres o cuatro integrantes.

- Deberá ser entregado por un solo miembro del grupo en un comprimido con el nombre de todos los integrantes.

- Dentro del comprimido, deberán encontrarse el código fuente del programa y un txt con los integrantes del equipo.

- El archivo subido deberá ser un comprimido de todo el proyecto y su nombre deberá respetar el siguiente formato:
TecnoJava-TPFinal-Grupo0- ApellidoIntegrante1- ApellidoIntegrante2- ApellidoIntegrante3.zip

Fecha de entrega:
La entrega deberá realizarse el 26/10/2023 antes de las 23:00 Hs.

Respecto a la autenticidad del trabajo:
- Si se entregan dos trabajos iguales, se calificará con la nota 1 (Uno) al grupo que se haya copiado, siempre y cuando se pueda demostrar cual es el trabajo original. En caso contrario se calificará con la nota 1 (Uno) a ambos.

- En caso de entregar un trabajo el cual fue extraído de internet, se calificará al grupo en cuestión con la nota 1 (Uno).

Consideraciones:
No es obligatorio en guardado en base de datos, pueden simplemente utilizar un Singleton.
Las validaciones solo tienen que esta en el controlador (Pueden retornar un Error 400 si algo no tiene el formato correcto)
Las vistar pueden o no ser un formulario único con las acciones posibles para un recurso