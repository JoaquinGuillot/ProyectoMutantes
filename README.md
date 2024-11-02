# Proyecto mutantes
Consideraciones:
* La documentación esta presente tanto en el archivo Documentacion mutantes.pdf como dentro del mismo código
* Se ha hecho un deploy en Render para realizar pruebas: https://proyectomutantes.onrender.com
* Aconsejable usar Swagger para verificar los métodos y las interacciones con la API:
  http://proyectomutantes.onrender.com/swagger-ui/index.html 
  El proyecto cuenta con 6 métodos disponibles, 3 get, 1 put 1 delete y 1 post:
* GET/mutant/ (getAll): Este metodo trae todos las personas guardadas en la base de datos en formato json. Ej;
                                                   {
                                                    id:1,
                                                    "nombre": "Pedro",
                                                    "apellido": "Gómez",
                                                    isMutant: true,
                                                    "adn": [
                                                      "ATCAGT",
                                                      "CAGTAC",
                                                      "TTAGCT",
                                                      "AGTCCA",
                                                      "CTAGGA",
                                                      "TCACTG"
                                                    ]
                                                  }
* GET/mutant/{id} (getOne): Este metodo como el anterior trae datos de mutantes, pero solo el especificado por su id
* PUT/mutant/{id} (update): con este método podemos actualizar la información de una persona utilizando su id.Ej:
 {
  "id": 0,
  "nombre": "string",
  "apellido": "string",
  "esMutante": true,
  "adn": [
    "string"
  ]

}
* DELETE/mutant/{id} (delete): este método nos permite eliminar una persona a través de su id.
Agregué estos métodos considerandolos funcionalidades útiles para el sistema, lo que nos lleva a los dos métodos principales:
* POST/mutant/analizar (analizarADN): este método contiene la lógica principal del negocio. nos permite generar una persona y determinar si la misma es un
  mutante o no. Esto es a través de un formato json que requerirá nombre, apellido y el arreglo de adn:
  {
  "nombre": "Roberto",
  "apellido": "Cruz",
  "adn": [
    "ATGCAT",
    "CAGGAA",
    "TTATGT",
    "AGGAGG",
    "CCCTAA",
    "TCACTG"
  ]

}

* GET/mutant/estadisticas (obtenerEstadisticas): este método nos permite obtener la cantidad de mutantes, la cantidad de no mutantes y
  la proporcion de mutantes con respecto a personas.
  
