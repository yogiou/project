##### **ReadMe**

###### _How to run the program?_
Install Java SDK 13.
find out the jar "project-1.0.0.RELEASE.jar" under "project" folder, open terminal and input command "java -jar project-1.0.0.RELEASE.jar"

This is a standard Java project, after importing the project
via _IntelliJ IDEA_, just run the main method in class 'main.Application'.

Or you can go to path _'TechCodeChallenge/out/production/TechCodeChallenge'_ and run
command _java main/Application_, then you should see a command prompt _'enter command:'_

###### _Unit test_
All unit tests were place in 'test' package

###### _Assumptions & Error handling_
The program will allow the user to draw line or rectangle over previous painting. e.g.
If you draw a line on (1, 1), (1, 4), then you can still draw the rectangle on
(1, 1), (3, 4).

The program will handle the invalid point error, all the user inputted points
which is out of the canvas or on the edge of the canvas will be treated as invalid points.

Invalid command or invalid command format errors will be also handled. And the command names
are case-sensitive and each argument is separated by exact one space
For example, below commands are not correct:

B

B 1     1   c

L 1     1     1    1

c 20 4

Thanks a lot!



*** The Problem ***

__Description__

You're given the task of writing a simple console version of a drawing program.
At this time, the functionality of the program is quite limited but this might change in the future.
In a nutshell, the program should work as follows:
1. Create a new canvas
2. Start drawing on the canvas by issuing various commands
3. Quit


Command 		Description
C w h           Should create a new canvas of width w and height h.
L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only
horizontal or vertical lines are supported. Horizontal and vertical lines
will be drawn using the 'x' character.
R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and
lower right corner is (x2,y2). Horizontal and vertical lines will be drawn
using the 'x' character.
B x y c         Should fill the entire area connected to (x,y) with "colour" c. The
behavior of this is the same as that of the "bucket fill" tool in paint
programs.
Q               Should quit the program.

__Sample I/O__

Below is a sample run of the program. User input is prefixed with enter command:

enter command: C 20 4
----------------------
|                    |
|                    |
|                    |
|                    |
----------------------

enter command: L 1 2 6 2
----------------------
|                    |
|xxxxxx              |
|                    |
|                    |
----------------------

enter command: L 6 3 6 4
----------------------
|                    |
|xxxxxx              |
|     x              |
|     x              |
----------------------

enter command: R 14 1 18 3
----------------------
|             xxxxx  |
|xxxxxx       x   x  |
|     x       xxxxx  |
|     x              |
----------------------

enter command: B 10 3 o
----------------------
|oooooooooooooxxxxxoo|
|xxxxxxooooooox   xoo|
|     xoooooooxxxxxoo|
|     xoooooooooooooo|
----------------------

enter command: Q

