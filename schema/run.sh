rm -rf build
rm -rf src
gradle generateSampleJooqSchemaSource
gradle build
mv build/libs/schema.jar ../libs/
