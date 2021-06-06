# MANUAL ANALISIS DINAMICO DE APLICACIONES MONOLITICAS (A.D.A.M)
En el presente manual se presentará los diversos pasos para configurar y ejecutar de una forma correcta a ADAM, Sin embargo ADAM se podrá ejecutar de dosmaneras diferentes, las cuales se podrán ejecutar dependiendo del contexto en elque se quiera realizar el análisis din ́amico y así obtener los resultados esperados.

El  primer  método  es  mediante  un  agente  estático  en  el  cual  es  lanzado  casial mismo tiempo que es lanzado el JVM de la aplicación que se quiere analizar; por otro lado el segundo método de análisis es ejecutado por medio de un agentedinámico el cual es lanzado después del JVM de la aplicación a analizar, ademas para ejecutar este agente se debe tener el PID de la correspondiente aplicación

#Configuración y ejecución

En primer lugar, Los ejecutables del ADAM se encuentran ubicados en un repositorio de GitHub, el cual se encuentra en la siguiente dirección web: https://github.com/williamABR/A.D.A.M ,Una vez en la página web, deberá asegurarse que se encuentra en la rama master y así proceder a descargar el proyecto en un .zip en el directorio local de preferencia, en el cual estará contenido los dos métodos de análisis dinámico, y algunos script de generación de carga de las aplicaciones que se tuvieron en cuenta para las pruebas del sistema ADAM.
