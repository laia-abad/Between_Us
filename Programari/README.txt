
Al obrir el projecte, s'han de importar dues llibreries (gson-2.8.2 i mysql-connector-java-5.1.47-bin). 
Per fer-ho:

 	1. A File, obrim el Project Structure
	2. Anem a la pestanya Libraries
	3. Clickem al + i seleccionem Java.
	4. Dintre del projecte busquem la carpeta resources i seleccionem una de les llibreries i cliquem OK.
	5. Seleccionem tots els moduls arrastrant el ratoli sobre tots.
	6. Repetim els passos del 3 al 5 i importem l'altra llibreria.



Una vegada importades les llibreries hem de conectar la base de dades descarregant-nos uns drivers.
Per fer-ho:
	
	1. A View -> Tools Windows, cliquem a Database.
	2. S'obrirà una finestra, arrestrem la base de dades que es diu Between_Us que es troba a resources.
	3. Fem click a Data Source Properties (el tercer icone de la barra a sota d'on posa database) i després anem a DDL Data Sources i ens descarreguem els drivers que es troben a MySQL i MySQL for 5.1.
	4. Cliquem Apply i ja tindrem la base de dades connectada


Per a executar el client, es necessari que el server estigui en execució.