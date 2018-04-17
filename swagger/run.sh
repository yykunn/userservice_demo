rm -rf build
rm -rf src
rm -rf server
java -jar ../../swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate   -i api.yaml -l spring  -c server.json -o server
cd server
mvn clean package
mv target/*.jar ../../libs/

cd ..
java -jar ../../swagger-codegen/modules/swagger-codegen-cli/target/swagger-codegen-cli.jar generate   -i api.yaml  -c config.json -l java  -o java
cd java
mvn clean package
