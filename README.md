# Nodes Manager

## Focus
- [ ] List navigation functionality
  - [ ] enable navigation with arrows
  - [ ] enable navigation by typing in search terms
- [ ] Link nodes based on node name
  - [ ] Enable links to be added from the displayed list
      - [ ] Display list
      - [ ] begin to type the name of the node you're looking for and the cursor should move to that node in the list
      - [ ] press enter on the node then type command and arguments
    - [ ] parse input for node names
      - [ ] turn all input characters to lowercase
      - [ ] use quotation marks to distinguish node names
        - [ ] use boolean to indicate the beginning of quotes
        - [ ] then if the boolean is true, and another quotation mark is encountered, it should be the closing quote. Switch inQuotes to false.
      - [ ] prevent duplicate node names
      - [ ] reconcile the fact that there are no node "names" with the user need to type little to find the desired node
- [x] Let the ```node add``` command take strings as arguments.

## ToDo
- [ ] Make a simple spaced-repetition system
   1. [ ] Display nodes that haven't been reviewed recently and are not mastered yet
- [ ] Create a pom.xml configuration file for maven and move the test class to it.
- [ ] Save the nodes to a Json file
