import java.util.*;
//
public class LargestConnectedGraph {

    public static void main(String args[]) {
        List<List<String>> graph = new ArrayList<>();

        List<String> v = new ArrayList<>();
        v.add("p1");
        v.add("p2");
        v.add("p3");
        graph.add(v);

        v = new ArrayList<>();
        v.add("p5");
        v.add("p2");
        graph.add(v);

        v = new ArrayList<>();
        v.add("p6");
        v.add("p7");
        graph.add(v);

        v = new ArrayList<>();
        v.add("p8");
        v.add("p7");
        graph.add(v);

        List<String> largestConnected = findLargestConnected(graph);
        System.out.println(largestConnected);
    }


    private static Map<String, Set<String>> buildAdjList(List<List<String>> graph) {
        Map<String, Set<String>> result = new HashMap<>();

        for(List<String> strings : graph) {

            Set<String> curStrings = new HashSet<>(strings);

            for(String str : strings) {
                if(result.containsKey(str)) {
                    result.get(str).addAll(curStrings);
                } else {
                    result.put(str, curStrings);
                }
            }
        }

        // Print adjacency list
        for(String key : result.keySet()) {
            System.out.print(key + ": "); System.out.println(result.get(key));
        }

        return result;
    }

    public static List<String> findLargestConnected(List<List<String>> graph) {

        List<String> result = new ArrayList<>();

        Map<String, Set<String>> al = buildAdjList(graph);

        Set<String> largest = new HashSet<>();
        int largestSize = 0;
        for(String key : al.keySet()) {
            System.out.print(key + ": "); System.out.println(al.get(key));
            if(al.get(key).size() > largestSize) {
                largestSize = al.get(key).size();
                largest = al.get(key);
            }
        }

        return new ArrayList<>(largest);

    }


}
