

1. line
2. reference
3. write n [1]s
4. number each [1] in ascending order
5. insert a [2] to [1] x in [1] y
6. randomly reorder the [1]s then [4]
7. update the [2]s to reflect the original [2]s

Each line object will include a list of lines. which represent the lines they reference.

public class Line {
	String line;
	Line[] references = new Line[];
}

display: "this is a [34], which is what [45] is made of."


