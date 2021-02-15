# ussd-gateway
API that handles a session of ussd requests to complete an enduser transaction.

###info
Written in Java, using Springboot and an in-memory h2 database

###environment setup
1) Ensure maven (https://maven.apache.org/) is installed on deployment server/local machine
2) From a terminal/command line, navigate to project root (you should see pom.xml file)
3) Execute 'mvn clean install'

###deployment - linux
From the project root, execute `./mvnw spring-boot:run`.
###deployment - windows
From the project root, execute `mvnw.cmd spring-boot:run`.

###how to use api
* <b>url:</b> http://{host_name}:8083/ussd (eg. http://localhost:8083/ussd)
* <b>expected body:</b> application/json payload
* <b>application/json payload: </b> String sessionId, String msisdn, String userEntry [optional]
* <b>example payload:</b> {
                            "sessionId": "AA1234",
                            "msisdn": "27821234567",
                            "userEntry": "3"
                          }