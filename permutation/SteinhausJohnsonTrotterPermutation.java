package permutation;

import java.util.ArrayList;

/**
 *
 * @author kaitlyn--Junyi li
 */
//this file implements Steinhaus–Johnson–Trotter permutation algorithm
//get the algorithm from http://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm
public class SteinhausJohnsonTrotterPermutation {

    private final ArrayList<int[]> SJT_permutations;
    private final ArrayList<int[]> SJT_swaps;
    private final int size;

    SteinhausJohnsonTrotterPermutation(int size) {
        SJT_permutations = new ArrayList<>();
        SJT_swaps = new ArrayList<>();
        this.size = size;

    }

    public int getPermutationAmount() {
        return SJT_permutations.size();
    }

    public ArrayList<int[]> getSJT_Permutations() {

        return SJT_permutations;

    }

    public ArrayList<int[]> getSJT_Swaps() {

        return SJT_swaps;

    }

    public void generatePermutation() {

        int[] permutation = new int[size];
        int[] inverse = new int[size];
        int[] direction = new int[size];
        for (int i = 0; i < size; i++) {
            direction[i] = -1;
            permutation[i] = i;
            inverse[i] = i;
        }
        permutationHelper(0, permutation, inverse, direction);
        int[] firstSwap = {0, 1};
        SJT_swaps.add(firstSwap);
    }

    public void permutationHelper(int n, int[] permutation, int[] inverse, int[] direction) {

        // base case - print out permutation
        if (n >= permutation.length) {
            int[] firstPermutation = new int[size];
            for (int i = 0; i < permutation.length; i++) {

                firstPermutation[i] = permutation[i] + 1;

            }
            SJT_permutations.add(firstPermutation);
            return;
        }

        permutationHelper(n + 1, permutation, inverse, direction);
        for (int i = 0; i <= n - 1; i++) {

            // swap 
            int[] tmp1 = {inverse[n], inverse[n] + direction[n]};
            SJT_swaps.add(tmp1);
            int z = permutation[inverse[n] + direction[n]];
            permutation[inverse[n]] = z;
            permutation[inverse[n] + direction[n]] = n;
            inverse[z] = inverse[n];
            inverse[n] = inverse[n] + direction[n];

            permutationHelper(n + 1, permutation, inverse, direction);
        }
        direction[n] = -direction[n];
    }

}
