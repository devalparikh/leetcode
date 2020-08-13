import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

// https://leetcode.com/problems/search-suggestions-system/
public class SearchSuggestionsSystem1268 {
    public static void main(String[] args) {
        String[] products = new String[] {"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";

//        suggestedProducts(products, searchWord);
        System.out.println(toString(suggestedProducts(products, searchWord)));

//         System.out.println("prefix".indexOf("pre")); // Note: this checks prefix in java
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {

        Arrays.sort(products);

        List<List<String>> results = new ArrayList<>();

        // Sorted Map of Product String -> Product Index
        TreeMap<String, Integer> productMap = new TreeMap<>();

        // Convert products list to arraylist for .sublist() method
        List<String> productsList = Arrays.asList(products);

        // Create map, iterate through each product
        for (int i = 0; i < products.length; i++) {
            // Add product -> product index
            productMap.put(products[i], i);
        }

        // Current built up string
        String key = "";

        // Iterate through each character in searchWord
        for (char c : searchWord.toCharArray()) {

            // Build up current string by appending current word
            key += c;

            // If a is sorted and    a[i] is prefix to a[j]
            // then a[i] is prefix to all a[i+1] .... a[j]

            // Get next product (least key after given key) - a[i+1]
            String ceiling = productMap.ceilingKey(key); // left pointer

            System.out.print(key);
            System.out.print(" c-> ");
            System.out.println(ceiling);

            // Get the last product (largest key) a[j]
            String floor = productMap.floorKey(key + "~"); // right pointer
            System.out.print(key);
            System.out.print(" f-> ");
            System.out.println(floor);

            // If both either are null exit loop
            if (ceiling == null || floor == null) break;

            // Add products in between index of ceiling and min(ceiling + 3, index of floor + 1)

            // The min is for in case there are more than 3 products that match prefix
            results.add(productsList.subList(productMap.get(ceiling), Math.min(productMap.get(ceiling) + 3, productMap.get(floor) + 1)));
        }

        // Populate upon no more results being found
        // Iterate for empty spots in results
        while (results.size() < searchWord.length()) {
            results.add(new ArrayList<>());
        }

        return results;

    }




    // Does not handle duplicates
    // O(n * s log s + n * s log n + s^2 * n)
    public static List<List<String>> suggestedProductsBruteForce(String[] products, String searchWord) {

        // O(n * s log s + n * s log n)
        Arrays.sort(products);

        List<List<String>> results = new ArrayList<>();
        char[] searchWordChars = searchWord.toCharArray();
        StringBuilder builtUpString = new StringBuilder();

        // O(s^2 * n)

        // O(s)
        for(int i = 0; i < searchWordChars.length; i++) {
            builtUpString.append(searchWordChars[i]);

            List<String> curResults = new ArrayList<>();

            // O(n)
            for(String product : products) {
                int matched = 1;
                char[] productChars = product.toCharArray();

                // O(s)
                for(int j = 0; j < builtUpString.length(); j++) {
                    if(builtUpString.charAt(j) != productChars[j]) {
                        matched = 0;
                        break;
                    }
                }

                if(matched == 1) {
                    curResults.add(product);
                    if(curResults.size() == 3) break;
                }
            }
            results.add(curResults);
        }

        return results;
    }

    public static String toString(List<List<String>> myBoard){
        String result = "";
        for(int i = 0; i < myBoard.size(); i++){
            for(int j = 0; j < myBoard.get(i).size(); j++){
                result += myBoard.get(i).get(j) + " ";
            }
            // System.out.println();
            result += "\n";
        }
        return result;
    }
}


//  A B C D E F G H I J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
//  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26