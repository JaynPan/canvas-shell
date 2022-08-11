# Canvas Shell

## Build

### Create the executable jar file

```
mvn package -Dmaven.test.skip
```

### Execute the jar file

```
java -jar ./target/demo-0.0.1-SNAPSHOT.jar
```

## Usage

| Command       | Description                                                                                                                                                                       |
|---------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| C w h         | Should create a new canvas of width w and height h.                                                                                                                               |
| L x1 y1 x2 y2 | Should create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character. |
| R x1 y1 x2 y2 | Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character.         |
| B x y c       | Should fill the entire area connected to (x,y) with "colour" c. The behaviour of this is the same as that of the "bucket fill" tool in paint programs.                            |
| Q             | Should quit the program.                                                                                                                                                          |

## __Sample I/O__

Below is a sample run of the program. User input is prefixed with enter command:

<img width="244" alt="image" src="https://user-images.githubusercontent.com/40001097/184064395-ebc70cb7-4bdf-4f5e-b880-5660fcbf6704.png">
