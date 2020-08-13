import java.util.Arrays;


public class RotateImage48 {
    public static void main(String[] args) {
        int[][] board1 = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(board1);
        System.out.println(Arrays.deepToString(board1));
    }

    private static void rotate(int[][] matrix) {

        // Number of circular layers in matrix is # rows / 2
        int layers = matrix.length / 2;

        // Iterate through each layer
        for(int i = 0; i < layers; i++) {

            // Iterate through each indices confined within current layer
            // starting at i (cur layer index) ending at matrix.length-i-1 (symmetrical tail)
            for(int j = i; j < matrix.length-i-1; j++) {

                // Pointers of current 4 edge points to swap
                int topLeft = matrix[i][j]; // use start row, iterating col forward
                int topRight = matrix[j][matrix.length-i-1]; // Iterating rows forward, use end col
                int bottomRight = matrix[matrix.length-i-1][matrix.length-j-1]; // use end row, Iterate cols backwards
                int bottomLeft = matrix[matrix.length-j-1][i]; // Iterate rows backwards, use start col

                // Do swaps for rotation
                matrix[i][j] = bottomLeft; // Move bottom left -> top left
                matrix[j][matrix.length-i-1] = topLeft; // Move top left -> top right
                matrix[matrix.length-i-1][matrix.length-j-1] = topRight; // Move top right -> bottom right
                matrix[matrix.length-j-1][i] = bottomRight; // Move bottom right -> bottom left
            }
        }
    }
}
