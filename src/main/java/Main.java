public class Main {
    public static void main(String[] args) {
        int MAX_SIZE = 10;

        Graph graph = new Graph(MAX_SIZE);
        for (int i = 0; i < MAX_SIZE; i++) {
            graph.addVertex((char) ('a' + i));
        }

        graph.addEdge('a', 'b');
        graph.addEdge('a', 'c');
        graph.addEdge('a', 'd');
        graph.addEdge('b', 'e');
        graph.addEdge('b', 'd');
        graph.addEdge('c', 'f');
        graph.addEdge('f', 'g');
        graph.addEdge('g', 'i');
        graph.addEdge('g', 'j');
        graph.addEdge('d', 'h');
        graph.addEdge('h', 'j');

        graph.widthTravers('i');

    }
}
