
package permutation;

import java.util.ArrayList;

/**
 *
 * @author kaitlyn--Junyi Li
 */

// this file implement an algorithm that generates the next permutation lexicographically after a given permutation
//get the algorithm from http://en.wikipedia.org/wiki/Permutation

public class LexOrderPermutation {
     
    private final ArrayList<int[]> Lex_permutations;
    private final int size;
    
    LexOrderPermutation(int size){
        Lex_permutations = new ArrayList<>();
        this.size = size;
    
    }
    
    //store the permutations into the data structure
    public void store(int[] a) {
        int[] copy = new int[a.length];
        for (int j = 0; j < a.length; ++j) {
            copy[j] = a[j];
        }
        Lex_permutations.add(copy);
            
    }

    //swap two elements
    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    
    public boolean hasNextPermutation(int[] permutation) {
        int length = permutation.length;

        //Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.
       
        int k;
        for (k = length - 2; k >= 0; k--) {
            if (permutation[k] < permutation[k + 1]) {
                break;
            }
        }
        if (k < 0) {
            return false;
        }

        // find rightmost element a[j] that is larger than a[k]
        int j = length - 1;
        while (permutation[k] > permutation[j]) {
            j--;
        }
        
        //Swap the value of a[k] with that of a[j].
        swap(permutation, j, k);

        //Reverse the sequence from a[k + 1] up to and including the final element a[n]
        for (int end = length - 1, start = k + 1; end > start; end--, start++) {
            swap(permutation, end, start);
            
        }
        

        return true;
    }

    public  void permutation() {

        // initialize permutation
        int[] firstPermutation = new int[size];
        for (int i = 0; i < size; i++) {
            firstPermutation[i] = i + 1;
        }

        // store permutations
        store(firstPermutation);  
        while (hasNextPermutation(firstPermutation)) {
            store(firstPermutation);
        }
    }
    
    public ArrayList<int []> getPermutations(){
       return Lex_permutations;
    
    }    
    
   
       
   
}
    

