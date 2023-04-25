package prim_algo;

public class PrimAlgorithmAdjacencyMatrix {

    static class Graph{
        int vertices;
        int matrix[][];

        public Graph(int vertices) {
            this.vertices = vertices;
            matrix = new int[vertices][vertices];
        }

        public void addEdge(int outVertex, int inVertex, int weight) {
            // thêm cạnh đi từ out vertex đến in vertex
            matrix[outVertex][inVertex]=weight;

            // thêm cạnh ngược lại trong đồ thị không trọng số
            matrix[inVertex][outVertex] = weight;
        }

        // trả về đỉnh có trọng số nhỏ nhất chưa nằm trong MST
        int getMinimumVertex(boolean[] mst, int[] key){
            int minKey = Integer.MAX_VALUE;
            int vertex = -1;
            for (int i = 0; i < vertices; i++) {
                if(mst[i] == false && minKey > key[i]){
                    minKey = key[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        class Result {
            // trả về đỉnh cha từ đó mà nó đi tới
            int parent;
            // chứa trọng số của cạnh để tính tổng trọng số của MST
            int weight;
        }

        public void primMST(){
            boolean[] mst = new boolean[vertices];
            Result[] result = new Result[vertices];
            int[] key = new int[vertices];

            // Khởi tạo các ptu mảng key đều là số rất lớn ( Integer.MAX_VALUE)
            // Khởi tạo mảng chứa kết quả với tất cả các đỉnh
            for (int i = 0; i < vertices; i++) {
                key[i] = Integer.MAX_VALUE;
                result[i] = new Result();
            }

            // bắt đầu từ đỉnh 0
            key[0] = 0;
            result[0] = new Result();
            result[0].parent = -1; // đỉnh 0 là đỉnh bắt đầu nên không có cha

            // tạo ra MST
            for (int i = 0; i < vertices; i++) {

                // tìm đỉnh có key bé nhất chưa nằm trong MST
                int vertex = getMinimumVertex(mst, key);

                // thêm đỉnh này vào MST
                mst[vertex] = true;

                // lặp qua tất cả đỉnh kề với đỉnh trên và cập nhật mảng key
                for (int j = 0; j <vertices; j++) {
                    // kiểm tra xem có cạnh đi từ đỉnh vertex đến j ko
                    if(matrix[vertex][j] > 0){
                        // kiểm tra xem đỉnh j có ở trong MST chưa
                        // nếu ko thì kiểm tra xem key có cần cập nhật ko
                        if(mst[j] == false && matrix[vertex][j] < key[j]){
                            // cập nhật key
                            key[j] = matrix[vertex][j];
                            // cập nhật kết quả
                            result[j].parent = vertex;
                            result[j].weight = key[j];
                        }
                    }
                }
            }
            // in ra kết quả
            printMST(result);
        }

        public void printMST(Result[] result){
            int total_min_weight = 0;
            System.out.println("Minimum Spanning Tree: ");
            for (int i = 1; i <vertices ; i++) {
                System.out.println("Edge: " + i + " – " + result[i].parent + " key: " + result[i].weight);
                total_min_weight += result[i].weight;
            }
            System.out.println("Total minimum key: " + total_min_weight);
        }

        public void printAdjacentMatrix() {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 7;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 7);
        graph.addEdge(0, 2, 15);
        graph.addEdge(0, 5, 20);

        graph.addEdge(1, 2, 7);
        graph.addEdge(1, 3, 11);

        graph.addEdge(2, 3, 20);
        graph.addEdge(2, 4, 17);
        graph.addEdge(2, 5, 18);

        graph.addEdge(3, 4, 20);

        graph.addEdge(4, 5, 7);
        graph.addEdge(4, 6, 11);

        graph.addEdge(5, 6, 15);

//        graph.addEdge(0, 1, 2);
//        graph.addEdge(0, 2, 4);
//        graph.addEdge(0, 3, 1);
//
//        graph.addEdge(1, 3, 3);
//        graph.addEdge(1, 4, 10);
//
//        graph.addEdge(2, 5, 5);
//        graph.addEdge(2, 3, 2);
//        graph.addEdge(3, 4, 7);
//
//        graph.addEdge(3, 5, 8);
//
//        graph.addEdge(3, 6, 4);
//        graph.addEdge(4, 6, 6);
//
//        graph.addEdge(5, 6, 1);
        graph.printAdjacentMatrix();
        System.out.println();
        graph.primMST();
    }
}