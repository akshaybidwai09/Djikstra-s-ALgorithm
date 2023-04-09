public class Main {
    private static final int NO_PARENT = -1;

    private static void dijkstra(int[][] adjacencyMatrix, int startVertex) {
        int nVertices = adjacencyMatrix[0].length;
        int[] shortestDistances = new int[nVertices];
        boolean[] added = new boolean[nVertices];
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }
        shortestDistances[startVertex] = 0;
        int[] parents = new int[nVertices];
        parents[startVertex] = NO_PARENT;
        for (int i = 1; i < nVertices; i++) {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            added[nearestVertex] = true;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex + 1;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }
        printSolution(shortestDistances, parents);
    }

    private static void printSolution(int[] distances, int[] parents) {
        int nVertices = distances.length;
        System.out.print("Distance Vector:");
        for(int i = 0; i < nVertices; i++) {
            System.out.print(" " + distances[i] + " " );
        }
        System.out.println();
        System.out.print("Precedence Vector:");
        nVertices = parents.length;
        for(int i = 0; i < nVertices; i++) {
            System.out.print(" " + parents[i] + " " );
        }
    }

    public static void main(String[] args) {
        int[][] adjacencyMatrix = { { 0, 1, 2, 0, 0, 0},
                { 0, 0, 5, 7, 0, 0},
                { 0, 0, 0, 1, 2, 0},
                { 0, 0, 0, 0, 0, 2},
                { 0, 0, 8, 3, 0, 5},
                { 0, 0, 0, 9, 0, 0}};
        dijkstra(adjacencyMatrix, 0);
    }
}
