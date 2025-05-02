# Nodes Manager

## Focus
1. [ ] List navigation functionality
   1. [ ] enable navigation with arrows
   2. [ ] enable navigation by typing in search terms
2. [ ] Link nodes based on node name
   1. [ ] Enable links to be added from the displayed list
      1. [ ] Display list
      2. [ ] begin to type the name of the node you're looking for and the cursor should move to that node in the list
      3. [ ] press enter on the node then type command and arguments
   2. [ ] parse input for node names
      1. [ ] turn all input characters to lowercase
      2. [ ] user quotation marks to distinguish node names
         1. [ ] use boolean to indicate the beginning of quotes
         2. [ ] then if the boolean is true, and another quotation mark is encountered, it should be the closing quote. Switch inQuotes to false.
      3. [ ] prevent duplicate node names
3. [x] Let the ```node add``` command take strings as arguments.

## ToDo
1. [ ] Make a spaced-repetition system
   1. [ ] Display nodes that haven't been reviewed recently and are not mastered yet
2. [ ] Create a pom.xml configuration file for maven and move the test class to it.
3. [ ] Save the nodes to a Json file