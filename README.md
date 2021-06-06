# MANUAL ANALISIS DINAMICO DE APLICACIONES MONOLITICAS (A.D.A.M)
En el presente manual se presentará los diversos pasos para configurar y ejecutar de una forma correcta a ADAM, Sin embargo ADAM se podrá ejecutar de dosmaneras diferentes, las cuales se podrán ejecutar dependiendo del contexto en elque se quiera realizar el análisis din ́amico y así obtener los resultados esperados.

El  primer  método  es  mediante  un  agente  estático  en  el  cual  es  lanzado  casial mismo tiempo que es lanzado el JVM de la aplicación que se quiere analizar; por otro lado el segundo método de análisis es ejecutado por medio de un agentedinámico el cual es lanzado después del JVM de la aplicación a analizar, ademas para ejecutar este agente se debe tener el PID de la correspondiente aplicación

# CONFIGURACION Y EJECUCCION

En primer lugar, Los ejecutables del ADAM se encuentran ubicados en un repositorio de GitHub, el cual se encuentra en la siguiente dirección web: https://github.com/williamABR/A.D.A.M ,Una vez en la página web, deberá asegurarse que se encuentra en la rama master y así proceder a descargar el proyecto en un .zip en el directorio local de preferencia, en el cual estará contenido los dos métodos de análisis dinámico, y algunos script de generación de carga de las aplicaciones que se tuvieron en cuenta para las pruebas del sistema ADAM.

Agente estático 
Una vez descargado el proyecto, deberá dirigirse a la carpeta ''Analisis-Dinamico'' y modificar el script "scriptADAM.sh", en el cual se encuentran los comandos para ejecutar el sistema ADAM. Adicionalmente, también encontrará la opción de ejecutar un script de JMeter el cual generará algún tipo de carga funcional sobre la aplicación que se va a analizar.

En el script, el usuario podrá modificar el comando para ejecutar algún generador de carga configurado en JMeter, agregando la ruta respectiva del script con la extinción de .jmx, cabe aclarar que este paso es opcional ya que el sistema ADAM no necesita de un generador de carga para la realización del análisis dinámico; no obstante, un generador de carga maximizará la calidad de la información recogida por ADAM.

El siguiente comando que se debe modificar es el que pone en ejecución el sistema ADAM, en el cual se debe poner la ruta del archivo .jar de ADAM, el nombre del paquete raíz, es decir el paquete general que contiene a las clases que se quiere observar; y por último se pone la ruta del .jar de la aplicación que se va a analizar.

Una vez modificado el archivo, el usuario debe dirigirse por la línea de comandos a la ubicación donde se encuentra ubicado el archivo scriptADAM.sh y ejecutar los siguientes comandos en su respectivo orden: 
  1) chmod +x scriptADAM.sh
  2) ./scriptADAM.sh
