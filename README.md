# Goldman Sachs Instant Messenger (GSIM)
This project is intended as an exercise to ready myself for work on the ION team at Goldman Sachs.
The prompt for this project is
> Create a WebSocket-based AIM chat messenger client using React for the front-end and a Vert.x Java back-end.
> Upload the project into a GitHub repository [the one your currently viewing] and send it to Adam Fleming for code review.

## How to run
1. Execute `mvn clean package` in the project root directory (requires [Maven](https://maven.apache.org/)). This command will create a JAR file in the **target** directory that can be executed.
2. Execute `java -jar target/goldman-sachs-instant-messenger-1.0-SNAPSHOT-fat.jar` to run the JAR file.

## How to test
1. Execute `mvn clean test` in the project root directory. Test results will be output to the console.

## Helpful Links
[Single Page Application development with React and Vert.x](https://how-to.vertx.io/single-page-react-vertx-howto/)
[My first Vert.x 3 Application](https://vertx.io/blog/my-first-vert-x-3-application/)
