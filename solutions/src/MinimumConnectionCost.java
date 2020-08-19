import java.util.*;

class Pair1{
    char from, to;
    int cost;
    Pair1(char from, char to, int cost){
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

class Node1{
    int data;
    Node1 parent;
    Node1(int data){
        this.data = data;
        this.parent = this;
    }
}

class MinimumConnectionCost
{
    public static boolean isValid(int i, int size){
        if(i < 0 || i >= size){
            return false;
        }
        return true;
    }

    public static void heapify(ArrayList<Pair1> heap, int i){
        if(i >= heap.size()){
            return ;
        }
        int lChild = 2 * i + 1;
        int rChild = 2* i + 2;
        if(isValid(lChild, heap.size()) && isValid(rChild, heap.size())){
            if(heap.get(lChild).cost <= heap.get(rChild).cost){
                if(heap.get(lChild).cost < heap.get(i).cost){
                    Pair1 temp = heap.get(lChild);
                    heap.set(lChild, heap.get(i));
                    heap.set(i, temp);
                    heapify(heap, lChild);
                }
            }
            else{
                if(heap.get(rChild).cost < heap.get(i).cost){
                    Pair1 temp = heap.get(rChild);
                    heap.set(rChild, heap.get(i));
                    heap.set(i, temp);
                    heapify(heap, rChild);
                }
            }
        }
        else if(isValid(lChild, heap.size())){
            if(heap.get(lChild).cost < heap.get(i).cost){
                Pair1 temp = heap.get(lChild);
                heap.set(lChild, heap.get(i));
                heap.set(i, temp);
                heapify(heap, lChild);
            }
        }
        else if(isValid(rChild, heap.size())){
            if(heap.get(rChild).cost < heap.get(i).cost){
                Pair1 temp = heap.get(rChild);
                heap.set(rChild, heap.get(i));
                heap.set(i, temp);
                heapify(heap, rChild);
            }
        }
        return ;
    }

    public static void formHeap(ArrayList<Pair1> heap){
        for(int i=heap.size()/2;i>=0;i--){
            heapify(heap, i);
        }
        return ;
    }

    public static Pair1 extractMin(ArrayList<Pair1> heap){
        Pair1 temp = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        heapify(heap, 0);
        return temp;
    }

    public static Node1 findParent(Node1 node, ArrayList<Node1> path){
        if(node.parent == node){
            for(int i=0;i<path.size();i++){
                path.get(i).parent = node;
            }
            return node;
        }
        path.add(node);
        return findParent(node.parent, path);
    }

    public static ArrayList<Pair1> findMinimumCostToConnectServers(ArrayList<Pair1> heap){
        ArrayList<Pair1> res = new ArrayList<> ();

        formHeap(heap);

        Node1 node[] = new Node1[26];
        for(int i=0;i<26;i++){
            node[i] = new Node1(i);
        }

        while(heap.size() > 0){
            Pair1 ele = extractMin(heap);

            Node1 parentFrom = findParent(node[ele.from - 'A'], new ArrayList<> ());
            Node1 parentTo = findParent(node[ele.to - 'A'], new ArrayList<> ());

            if(parentFrom != parentTo){
                parentTo.parent = parentFrom;
                res.add(new Pair1(ele.from, ele.to, ele.cost));
            }
        }
        return res;
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        ArrayList<Pair1> a = new ArrayList<> ();
        for(int i=0;i<n;i++){
            a.add(new Pair1(sc.next().charAt(0), sc.next().charAt(0), sc.nextInt()));
        }
        ArrayList<Pair1> ans = findMinimumCostToConnectServers(a);
        for(int i=0;i<ans.size();i++){
            System.out.println(ans.get(i).from+" "+ans.get(i).to+" "+ans.get(i).cost);
        }
    }
}