import java.util.*;

class Pair{
    int totalNodes, totalSum;
    Pair(int total, int sum){
        this.totalNodes = total;
        this.totalSum = sum;
    }
}

class MaxTenureFinder
{
    public static Pair findHighestTenure(HashMap<Integer, ArrayList<Integer>> hmap, int V){
        if(hmap.get(V).size() == 0){
            return new Pair(1, V);
        }
        else{
            int totalNodesCount = 1;
            int totalSum = V;
            for(int i=0;i<hmap.get(V).size();i++){
                Pair temp = findHighestTenure(hmap, hmap.get(V).get(i));
                totalNodesCount += temp.totalNodes;
                totalSum += temp.totalSum;
            }

            if(totalSum * maxSum.totalNodes >= maxSum.totalSum * totalNodesCount){ // logic to avoid precision error
                maxSum.totalNodes = totalNodesCount;
                maxSum.totalSum = totalSum;
                maxTenureNode = V;
            }

            return new Pair(totalNodesCount, totalSum);
        }
    }

    public static int maxTenureNode;
    public static Pair maxSum;

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<> ();

        for(int i=0;i<n;i++){
            int parent = sc.nextInt();
            int child = sc.nextInt();
            if(hmap.containsKey(parent)){
                hmap.get(parent).add(child);
            }
            else{
                ArrayList<Integer> temp = new ArrayList<> ();
                temp.add(child);
                hmap.put(parent, temp);
            }
            if(!hmap.containsKey(child)){
                hmap.put(child, new ArrayList<> ());
            }
        }
        int parentNode = sc.nextInt();
        maxSum = new Pair(0, 0);
        maxTenureNode = -1;
        findHighestTenure(hmap, parentNode);
        System.out.println(maxTenureNode);
    }
}