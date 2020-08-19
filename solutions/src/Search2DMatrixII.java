public class Search2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix == null || matrix.length == 0)
            return false;

        int totalRow = matrix.length;
        int totalCol = matrix[0].length;

        int startRow = totalRow - 1;
        int startCol = 0;

        while(startRow >= 0 && startRow < totalRow && startCol >= 0 && startCol < totalCol){

            if(matrix[startRow][startCol] == target)
                return true;

            if(matrix[startRow][startCol] > target){
                startRow--;
            }
            else
                startCol++;
        }
        return false;
    }
}
