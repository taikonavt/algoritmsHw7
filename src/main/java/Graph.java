import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    private class Vertex{
        char label;
        boolean wasVisited;

        Vertex(char label){
            this.label = label;
            wasVisited = false;
        }

        @Override
        public String toString() {
            return "V: " + label;
        }
    }

    private final int MAX_VERTICES;
    private Vertex[] vertices;
    private int[][] adjMatrix;
    private int size;

    public Graph(int maxSize) {
        this.MAX_VERTICES = maxSize;
        vertices = new Vertex[MAX_VERTICES];
        adjMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        size = 0;
    }

    public void addVertex(char label) {
        vertices[size++] = new Vertex(label);
    }

    public void addEdge(char start, char end){
        int s;
        int e;
        int offset = getOffset(start);
        s = start - offset;
        e = end - offset;

        adjMatrix[s][e] =  1;
        adjMatrix[e][s] = 1;
    }

    private int getOffset(char ch){
        int offset;
        if (ch > 64 && ch < 91) {
            offset = 65;
        } else if (ch > 96 && ch < 123) {
            offset = 97;
        } else
            throw new RuntimeException("Wrong vertex label");
        return offset;
    }

    public void showVertex(int v){
        System.out.println(vertices[v]);
    }

    private int getUnvisitedVertex(int ver){
        for (int i = 0; i < size; i++) {
            if (adjMatrix[ver][i] == 1 && !vertices[i].wasVisited)
                return i;
        }
        return -1;
    }

    private void resetFlags(){
        for (int i = 0; i < size; i++) {
            vertices[i].wasVisited = false;
        }
    }

    public void widthTravers(char target){
        HashMap<Integer, Integer> ancestors = new HashMap<Integer, Integer>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        vertices[0].wasVisited = true;
//        showVertex(0);
        queue.add(0);
        ancestors.put(0, -1);
        int vCurr = -1;
        while (!queue.isEmpty()){
            vCurr = queue.remove();
            if (vertices[vCurr].label == target)
                break;
            int vNext;
            while ((vNext = getUnvisitedVertex(vCurr)) != -1){
                vertices[vNext].wasVisited = true;
//                showVertex(vNext);
                queue.add(vNext);
                ancestors.put(vNext, vCurr);
            }
        }
        resetFlags();

        Stack<Integer> stack = new Stack<Integer>();
        while (vCurr != -1){
            stack.push(vCurr);
            vCurr = ancestors.get(vCurr);
        }
        while (!stack.empty()){
            System.out.println(vertices[stack.pop()]);
        }
    }
}
