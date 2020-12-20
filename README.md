# ImageChatter

The app offers a change to create an anonym chat for any picture online. For example, you can paste an image URL to ImageChatter which opens the chat about the image. The user will se all the messages added before plus additional information about the image's history on the app (when added first by who etc.). The source of the image does not matter, only the rgb-values. This is especially useful today because lots of disinformative pictures are circluating on social media and there exists no common place to talk about them.

## Commands for command line

### Testing

Run this command from the ImageChatter repository to test.

```
mvn test
```

Run this command from the ImageChatter repository to generate test report. 

```
mvn jacoco:report
```

This report can be found from target/site/jacoco/index.html.

### Creating a jar file

You can generate a jar file with all dependencies by running the following command from the ImageChatter repository.

```
mvn clean compile assembly:single
```

And if you want to run the file. Run the following command.

```
java -jar ImageChatter-1.0-SNAPSHOT.jar
```

### Creating a checkstyle report

You can generate a checkstyle report with the following command.

```
 mvn jxr:jxr checkstyle:checkstyle
```

The report can be found from target/site/checkstyle.html

### Creating a Javadoc

You can generate a Javadoc with the following command

```
mvn javadoc:javadoc
```
The report can be found in target/site/apidocs/index.html.


## Documentation for the app


[Specifications](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Architecture](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Working hours](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/työaikakirjanpito.md)

[User instructions](https://github.com/kallioaa/ot-harjoitustyo/blob/master/dokumentaatio/instructions.md)

[Release](https://github.com/kallioaa/ot-harjoitustyo/releases/tag/viikko5)

