//package Solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MaxUnitsInBoxesInTrucks {
    public static void main(String[] args) {

        // ===================================================
        int num = 2;
        ArrayList<Integer> boxes = new ArrayList<>(List.of(2, 2));
        int unitSize = 2;
        ArrayList<Integer> unitsPerBox = new ArrayList<>(List.of(3, 1));
        int truckSize = 3;
        // ===================================================

        System.out.println(getMaxUnit(num,boxes,unitSize,unitsPerBox,truckSize));

    }

    public static int getMaxUnit(int num, ArrayList<Integer> boxes, int unitSize, ArrayList<Integer> unitsPerBox, long truckSize){
        PriorityQueue<Integer> PQ = new PriorityQueue<>();

        // Go through each product
        for(int i=0;i<num;i++){

            // Obtain product's number of boxes
            int n = boxes.get(i);

            // Go through each box
            for(int j=0;j<n;j++){

                // Get units current box can fit
                // Add that value to PQ (min heap)
                PQ.add(unitsPerBox.get(i));

                // Check if size is over now
                // If it exceeded trucksize, remove min value
                if(PQ.size()>truckSize) PQ.remove();
            }
        }
        int max=0;
        while(!PQ.isEmpty()){
            max += PQ.remove();
        }
        return max;
    }

}



