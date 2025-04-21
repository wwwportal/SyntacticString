# Node Manager Log

## 2025-04-19

1. I've got the basic components written. I can add nodes, load and save them from a file, move them around. Now I want to add the ability to link nodes to other nodes.
2. I may need some kind of view mode and edit mode in the program to separate the different set of commands users can issue.

Users should be able to use commands like:
node [target] // views node and links
node move [source] [target]
node remove [source]
node link [source] [target]

nodes
nodes load
nodes save
nodes clear
nodes 
