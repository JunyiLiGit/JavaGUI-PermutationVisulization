
package permutation;

import java.util.ArrayList;

/**
 *
 * @author kaitlyn--Junyi Li
 */

//this file implements heap's permutation algorithm from 
//http://en.wikipedia.org/wiki/Heap%27s_algorithm
public final class HeapPermutation {

    private final ArrayList<int[]> swaps;
    private final ArrayList<int[]> permutationProcess;

    HeapPermutation(int size) {
        swaps = new ArrayList<>();
        permutationProcess = new ArrayList<>();
        
        //initializa the first permutation
        int[] firstPermutaiton = new int[size];
        for(int i =0; i<size;++i){
           firstPermutaiton[i] = i+1;
        }
        permutationProcess.add(0,firstPermutaiton);
       

    }

    //generate all the permutations when the algorithm runs. these permutations will include duplicates
    public ArrayList<int[]> getpermutationProcess() {
        
        return permutationProcess;

    }
    
    //add the swap index into datastructure 
    public ArrayList<int[]> getSwap() {
        int [] tmp= {0,1};
        swaps.add(tmp);
        return swaps;

    }
  
    private void swap(int[] permutation, int i, int j) {
        int t = permutation[i];
        permutation[i] = permutation[j];
        permutation[j] = t;

    }
//function to generate permutations
    public void permutation(int[] currentPermutation, int n) {
        if (n == 1) {
            //System.out.println(Arrays.toString(v));

        } else {

            for (int i = 0; i < n; i++) {

                permutation(currentPermutation, n - 1);

                if (n % 2 == 1) {
                    swap(currentPermutation, 0, n - 1);
                    if (n - 1 != 0) {
                        int[] s = {0, n - 1};
                        swaps.add(s);
                        int [] p = new int[currentPermutation.length];
                        for(int k =0; k< currentPermutation.length; ++k){
                          p[k] = currentPermutation[k]; 
                        }
                        permutationProcess.add(p);

                    }
                } else {
                    swap(currentPermutation, i, n - 1);
                    if (i != n - 1) {
                        int[] s = {i, n - 1};
                        swaps.add(s);
                        int [] copy = new int[currentPermutation.length];
                        for(int k =0; k< currentPermutation.length; ++k){
                          copy[k] = currentPermutation[k]; 
                        }
                        permutationProcess.add(copy);

                    }
                }

            }

        }

    }

    
    
}
