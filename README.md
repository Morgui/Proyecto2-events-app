## Parte 2 del Proyecto de JUnit y Git: testing sobre el proyecto Event 

1. Importar JUnit 5
2. Importar mockito, copiar dependencia de: https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
3. Testing unitario con JUnit 5 - proceso
 	- Se crea dentro de src/test/java un package com.example.services
 	- Se crean las clases de test, EventTest y EventNotificationServiceImp, para testear, alcanzar la mayor cobertura
	y todas sus pruebas posibles, validando el correcto funcionamiento de cada uno de sus respectivos métodos
4. Se utiliza el framework Mockito para la dependencia EventNotificationService y así poder realizar el test del método notifyAssistans() de la clase Event.
5. Se crea y utiliza el SuiteTest para ejecutar todos las clases de test ya creados a la vez.
6. Se usa Git Flow y se realiza un commit por cada método de test creado y verificado en una rama para una vez finalizado todo hacer un merge a master.
 
