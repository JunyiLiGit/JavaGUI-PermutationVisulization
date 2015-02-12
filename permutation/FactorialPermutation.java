package permutation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author kaitlyn--Junyi Li
 */


//this file implements a permutation algorithm based on the factorial number system
public class FactorialPermutation {

    ArrayList<int[]> factorialSequence;
    ArrayList<int[]> permutations;

    FactorialPermutation(int size) {
        factorialSequence = new ArrayList<>();
        permutations = new ArrayList<>();

    }

    //generate permutation from factorial numbers.
    public void generatePermutations() {
        //find the right most 0, put down the biggest number 
        // check the right side first, check from the end of the array to the previous availiable position. 
        //find the right most availiable position
        //if there is no availiable position for the current number, check the left side
        //when check the left side, find the right most avaliable position

        for (int p = 0; p < factorialSequence.size(); ++p) {

            int[] factorialNumber = factorialSequence.get(p);

            int biggest = factorialNumber.length;
            int currentIndex = 0;
            int[] permutation = new int[factorialNumber.length];
            boolean[] visit = new boolean[factorialNumber.length];

            //find the position for the biggest number. It should locat at the right most 0
            for (int i = factorialNumber.length - 1; i >= 0; --i) {
                if (factorialNumber[i] == 0) {
                    permutation[i] = biggest;
                    currentIndex = i;
                    visit[i] = true;
                    biggest = biggest - 1;
                    break;
                }
            }

            //find the position for the rest of numbers, from bigger numbers to smaller numbers
            for (int index = 1; index < factorialNumber.length; ++index) {

                boolean find = false;
                if (!find) {

                    //check the avaliable position on the right side first
                    for (int rightSideStart = factorialNumber.length - 1; rightSideStart > currentIndex; --rightSideStart) {
                        int biggerNumberBefore = factorialNumber[currentIndex];
                        int biggerNumberAfter = 0;
                        int expectedValue = 0;
                        if (!visit[rightSideStart]) {
                            for (int k = currentIndex + 1; k < rightSideStart; ++k) {
                                if (permutation[k] > biggest) {

                                    biggerNumberAfter++;
                                }

                            }

                            expectedValue = biggerNumberBefore + 1 + biggerNumberAfter;

                            if (expectedValue == factorialNumber[rightSideStart]) {

                                find = true;
                                permutation[rightSideStart] = biggest;
                                visit[rightSideStart] = true;
                                currentIndex = rightSideStart;
                                biggest--;
                                break;
                            }
                        }

                    }

                    //If there is no availiable position on the right side, check the left side
                    if (!find) {
                        for (int leftSideStart = currentIndex - 1; leftSideStart >= 0; --leftSideStart) {

                            int biggerNumberBefore = 0;
                            int expectedValue = 0;
                            if (!visit[leftSideStart]) {
                                for (int k = 0; k < leftSideStart; ++k) {
                                    if (permutation[k] > biggest) {
                                        biggerNumberBefore++;

                                    }

                                }

                                expectedValue = biggerNumberBefore;
                                if (expectedValue == factorialNumber[leftSideStart]) {
                                    permutation[leftSideStart] = biggest;
                                    visit[leftSideStart] = true;
                                    currentIndex = leftSideStart;
                                    biggest--;
                                    break;

                                }

                            }

                        }

                    }
                }
            }

            permutations.add(permutation);
        }
    }

    public ArrayList<int[]> getFactorialSequence() {
        return factorialSequence;

    }

    public ArrayList<int[]> getPermutations() {
        return permutations;

    }

    //function to caculate the factorial of a given number
    public int caculateFacorial(int size) {
        int factorial = 1;
        for (int i = 1; i <= size; ++i) {
            factorial = factorial * i;

        }

        return factorial;

    }

    //generate factorial sequences from a given number, store the factorial sequence into data structure
    public void generateFactorial(int size) {

        for (int p = 0; p < caculateFacorial(size); ++p) {

            int seed = p;

            int i = 1;
            int[] factorial = new int[size];
            int index = size;
            if (seed == 0) {
                Arrays.fill(factorial, 0);

            } else {
                while (seed / i != 0) {
                    int reminder = seed % i;
                    seed = seed / i;
                    factorial[i - 1] = reminder;
                    ++i;

                }
                factorial[i - 1] = seed % i;

            }

            factorialSequence.add(factorial);

        }

    }

}
