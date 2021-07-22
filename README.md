# Test in Docker DDB
https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/GettingStarted.Java.html
## Steps
1. You need to have valid aws credentials locally under `~/.aws/credentials`
2. Start dynamoDB inside docker folder with `docker-compose up`

# Test in DynamoDBLocal
https://www.baeldung.com/dynamodb-local-integration-tests

## Steps
1. Add DynamoDBLocal, sqlite4java dependencies
2. Add a build plugin to copy the lib to native-libs
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-dependency-plugin</artifactId>
    <version>2.10</version>
    <executions>
        <execution>
            <id>copy</id>
            <phase>test-compile</phase>
            <goals>
                <goal>copy-dependencies</goal>
            </goals>
            <configuration>
                <includeScope>test</includeScope>
                <includeTypes>so,dll,dylib</includeTypes>
                <outputDirectory>${project.basedir}/native-libs</outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
```
3. Run `mvn test-compile` once
4. `System.setProperty("sqlite4java.library.path", "native-libs");` is needed. See `DynamoDBLocalTest.java` file.
