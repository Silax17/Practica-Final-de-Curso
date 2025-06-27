# Sistema de Reservas de Salas de Reuniones

## Descripción General

Este proyecto es una aplicación Java para gestionar un sistema de reservas de salas de reuniones en una empresa. Permite realizar operacions CRUD (Create, Read, Update, Delete) sobre salas, empleados y reservas, asegurando el control de conflictos en los horarios para evitar reservas dobles.

La aplicación utiliza una base de datos MySQL para almacenar la información, y está getionada mediante Maven par ala construcción y manjeo de dependencias utiles para el proyecto. Tambien incluye pruebas unitarias para garantizar seguridad en el codigo.

## Como Ejecutar el Proyecto

1. **Importar el proyecto en Eclipse con Maven:**

   - Abre Eclipse y selecciona **File > Import> Existing Maven Projects**
   - Navega hasta la carpeta raíz del proyecto y Aplica
   - Eclipse descargará las dependencias definidas en el 'pom.xml' y configurara el proyecto automàticamente (si necesitas más dependencias, tiene que ser manualmente)

2. **Configurar el JDK**

   - Asegurate de tener instalado Java 11 o superior
   - En Eclipse, ve a **Window > Prefences > Java > Installed JRes** y añade la version correcta de Java
   - Configurar el proyecto para usar esa version

3. **Ejecutar:**

   -Ejecuta la clase main desde Eclipse para ver la funcion del proyecto

##Instrucciones para Importar y Usar la Base de Datos**
1. **Instalar MySQL**
   
   - Descargar e instalar MySQL Server

2. **Crear la base de datos y las tablas con el script:**

   - Abre MySQL Workbecnh
   - Ejecuta el script SQL incluido en el archivo `script_proy.sql` para crear el esquema y los datos iniciales
  
3. **Configurar la conexión en la aplicación:**

   - Revisa la clase 'ConexionBD' para asegurarte que los datos de conexión (`URL`,`usuario`,`contraseña`) coincidan con tu configuración local de MySQL juntamente con el servidor
  
4. **Ejecutar la aplicación**

   - Al iniciar, la aplicación se conectara a la base de datos MySQL y permitirá gestionar reservas de salas según el codigo implementado
  


  
   








