## requirements
* Java 11.0.1

## file import
### file format
* attributes.csv: code;label_1;label_2; ...
* options.csv: code;label_1;label_2; ...;attribute;sort_order

### path setting for imported files
* rest-pierce/src/main/resources/application.properties/import.file.path
* example: import.file.path=C:\\\Users\\\dawid\\\Downloads\\\data\\\

### setting the file name to import
* rest-pierce/src/main/resources/application.properties/import.file.files
* example: import.file.files=attributes.csv;options.csv

### import time set in milliseconds
* import.file.scheduler.rate=600000 = 10 minutes

## curl examples
### languages controller
* get all `curl -H "Content-Type: application/json" -X GET http://localhost:8888/languages`
* get by id `curl -H "Content-Type: application/json" -X GET http://localhost:8888/languages/1`
* post `curl -H "Content-Type: application/json" -X POST -d {\"code\":\""label-zb_ZB\""} http://localhost:8888/languages/`
* delete `curl -H "Content-Type: application/json" -X DELETE http://localhost:8888/languages/18`

### attributes controller
* get all `curl -H "Content-Type: application/json" -X GET http://localhost:8888/attributes`
* get by id `curl -H "Content-Type: application/json" -X GET http://localhost:8888/attributes/1`
* post `curl -H "Content-Type: application/json" -X POST -d {\"code\":\""helmetsize""} http://localhost:8888/attributes/`
* delete `curl -H "Content-Type: application/json" -X DELETE http://localhost:8888/attributes/20`

### attribute translations controller
* get all `curl -H "Content-Type: application/json" -X GET http://localhost:8888/attributes/translations`
* get by id `curl -H "Content-Type: application/json" -X GET http://localhost:8888/attributes/translations/1`
* post `curl -H "Content-Type: application/json" -X POST -d {\"languageId\":\"3\",\"attributeId\":\"3\",\"translate\":\""some translate\""} http://localhost:8888/attributes/translations/`
* delete `curl -H "Content-Type: application/json" -X DELETE http://localhost:8888/attributes/translations/300`

### options controller
* get all `curl -H "Content-Type: application/json" -X GET http://localhost:8888/options/`
* get by id `curl -H "Content-Type: application/json" -X GET http://localhost:8888/attributes/1`
* post `curl -H "Content-Type: application/json" -X POST -d {\"code\":\"3\",\"attributeId\":\"3\",\"sortOrder\":\"1\"} http://localhost:8888/options/`
* delete `curl -H "Content-Type: application/json" -X DELETE http://localhost:8888/options/20`

### option translations controller
* get all `curl -H "Content-Type: application/json" -X GET http://localhost:8888/options/translations`
* get by id `curl -H "Content-Type: application/json" -X GET http://localhost:8888/attributes/translations/1`
* post `curl -H "Content-Type: application/json" -X POST -d {\"languageId\":\"3\",\"optionId\":\"3\",\"translate\":\"example\"} http://localhost:8888/options/translations/`
* delete `curl -H "Content-Type: application/json" -X DELETE http://localhost:8888/options/translations/20`
