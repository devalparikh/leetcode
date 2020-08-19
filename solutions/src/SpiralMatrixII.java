public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {

        int[][] matrix = new int[n][n];
        // need four boundaries for iteration inside cols and rows
        int top = 0;
        int left = 0;
        int right = n-1;
        int bottom = n-1;
        int num = 1;
        while(left <=right && top <=bottom ){
            // do first row
            for(int i = left; i<= right; i++){
                matrix[top][i] = num;
                num++;
            }
            // move the rirst row up after processing
            top++;

            // do right column
            for(int i = top; i<= bottom; i++){
                matrix[i][right] = num;
                num++;
            }
            // move the right col left after processing
            right--;

            // do bottom row
            for(int i = right; i>=left; i--){
                matrix[bottom][i]=num;
                num++;
            }
            // move bottom row up after processing
            bottom--;

            for(int i = bottom; i>=top; i--){
                matrix[i][left] = num;
                num++;
            }
            // move the left column right after processing
            left++;
        }

        return matrix;
    }
}
