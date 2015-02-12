package permutation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author kaitlyn--Junyi Li
 */
public class PermutationModel {

    //when user presses next button or previous button, only show 12 or 14 permutations for each of these algorithms
    private int startForSJT = 0;
    private int endForSJT = 12;

//the heap algorithm process generates repeated permutations, in order to show the user how to get the next permutation
    //I showed the whole process
    private int startForHeap = 0;
    private int endForHeap = 14;

    private int startForLex = 0;
    private int endForLex = 12;

    private int startForFactorial = 0;
    private int endForFactorial = 12;

    //the default permutation size is 4  
    int size = 4;

    //Data structure for Steinhaus–Johnson–Trotter algorithm to store permutations and swap indexes
    private ArrayList<int[]> SJTpermutations;
    private ArrayList<int[]> SJTSwaps;

    //Data structure for Heap-Permutation
    private ArrayList<int[]> HeapSwaps;
    private ArrayList<int[]> HeapPermutationProcess;

    //Data structure for Lexicographic order permutation
    private ArrayList<int[]> LexPermutations;

    //Data structure for Factorial order permutations, and factorial sequences.
    private ArrayList<int[]> FactorialPermutations;
    private ArrayList<int[]> FactorialSequences;

    PermutationModel() {

        //init model using four different algorithms, store the permutations into coressponding data structures
        //Steinhaus Johnson Trotter's algorithm (SJT Algorithm)
        SteinhausJohnsonTrotterPermutation SJTPermutation = new SteinhausJohnsonTrotterPermutation(size);
        SJTPermutation.generatePermutation();
        this.SJTpermutations = SJTPermutation.getSJT_Permutations();
        this.SJTSwaps = SJTPermutation.getSJT_Swaps();

        //Heap's algorithm
        HeapPermutation heapPermutation = new HeapPermutation(size);
        int[] seed = heapPermutation.getpermutationProcess().get(0);
        heapPermutation.permutation(seed, seed.length);
        this.HeapPermutationProcess = heapPermutation.getpermutationProcess();
        this.HeapSwaps = heapPermutation.getSwap();

        //Lexicographic order algorithm
        LexOrderPermutation lexPermutation = new LexOrderPermutation(size);
        lexPermutation.permutation();
        this.LexPermutations = lexPermutation.getPermutations();

        //factorial order algorithm
        FactorialPermutation factorialPermutation = new FactorialPermutation(size);
        factorialPermutation.generateFactorial(size);
        factorialPermutation.generatePermutations();
        this.FactorialSequences = factorialPermutation.getFactorialSequence();
        this.FactorialPermutations = factorialPermutation.getPermutations();

    }

    //get permutations account. For size 4, the permutation account is 24
    public int getPermutationAccount() {
        return SJTpermutations.size();
    }

    //get permutation account for heap's algorithm. The algorithm itself will generate 24 permutations for size 4.
    //But it will generate 2 repeated permutations at the same time for size 4 permutation. I need to show the process to the user in the view
    public int getPermutationAccountForHeap() {
        return HeapPermutationProcess.size();
    }

    //update model,  when user switches permutation size (4,5,6) 
    //regenerate the four permutations groups.  
    public void updateModel(int size) {

        SJTpermutations.clear();
        SJTSwaps.clear();
        SteinhausJohnsonTrotterPermutation SJTPermutation = new SteinhausJohnsonTrotterPermutation(size);
        SJTPermutation.generatePermutation();
        SJTpermutations = SJTPermutation.getSJT_Permutations();
        SJTSwaps = SJTPermutation.getSJT_Swaps();

        HeapPermutationProcess.clear();
        HeapSwaps.clear();
        HeapPermutation heapPermutation = new HeapPermutation(size);
        int[] seed = heapPermutation.getpermutationProcess().get(0);
        heapPermutation.permutation(seed, seed.length);
        HeapPermutationProcess = heapPermutation.getpermutationProcess();
        HeapSwaps = heapPermutation.getSwap();

        LexPermutations.clear();
        LexOrderPermutation lexPermutation = new LexOrderPermutation(size);
        lexPermutation.permutation();
        this.LexPermutations = lexPermutation.getPermutations();

        FactorialSequences.clear();
        FactorialPermutations.clear();
        FactorialPermutation factorialPermutation = new FactorialPermutation(size);
        factorialPermutation.generateFactorial(size);
        factorialPermutation.generatePermutations();
        this.FactorialSequences = factorialPermutation.getFactorialSequence();
        this.FactorialPermutations = factorialPermutation.getPermutations();

    }

    //when the user press next or previous button, set the start index for the permutations 
    public void setStart(int algorithmIndex, int start) {
        switch (algorithmIndex) {

            case 1:
                startForSJT = start;
                break;
            case 2:
                startForHeap = start;
                break;
            case 3:
                startForLex = start;
                break;
            case 4:
                startForFactorial = start;
                break;

        }

    }

    //when the user press next or previous button, set the end index for the permutations 
    public void setEnd(int algorithmIndex, int end) {
        switch (algorithmIndex) {

            case 1:
                endForSJT = end;
                break;
            case 2:
                endForHeap = end;
                break;
            case 3:
                endForLex = end;
            case 4:
                endForFactorial = end;
                break;

        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    //the controller get part of the permutations from the model every time when the user presses next or previous button
    public ArrayList<int[]> getPartOfpermutations(ArrayList<int[]> permutations, int algorithmIndex) {
        ArrayList<int[]> partOfPermutations = new ArrayList<>();
        int startForView = 0, endForView = 0;
        switch (algorithmIndex) {

            case 1:
                startForView = startForSJT;
                endForView = endForSJT;
                break;
            case 2:
                startForView = startForHeap;
                endForView = endForHeap;
                break;
            case 3:
                startForView = startForLex;
                endForView = endForLex;
                break;
            case 4:
                startForView = startForFactorial;
                endForView = endForFactorial;
                break;

        }

        if (startForView < permutations.size() && endForView <= permutations.size()) {
            for (int i = startForView; i < endForView; ++i) {
                int[] tmp = new int[permutations.get(0).length];
                for (int j = 0; j < permutations.get(i).length; ++j) {
                    tmp[j] = permutations.get(i)[j];
                }
                partOfPermutations.add(tmp);

            }
        }
        return partOfPermutations;

    }

    //get permutations for each of these algorithms
    public ArrayList<int[]> getPermutations(int algorithmIndex) {
        switch (algorithmIndex) {
            case 1:
                return getPartOfpermutations(SJTpermutations, 1);
            case 2:
                return getPartOfpermutations(HeapPermutationProcess, 2);
            case 3:
                return getPartOfpermutations(LexPermutations, 3);
            case 4:
                return getPartOfpermutations(FactorialPermutations, 4);
            default:
                return getPartOfpermutations(HeapPermutationProcess, 2);

        }

    }

    //get swaps for SJT Algorithm and Heap's algorithm based on the start and end indexes
    public ArrayList<int[]> getSwaps(int algorithmIndex) {

        switch (algorithmIndex) {
            case 1:
                return getPartOfpermutations(SJTSwaps, 1);
            case 2:
                return getPartOfpermutations(HeapSwaps, 2);
            default:
                return getPartOfpermutations(HeapSwaps, 1);

        }

    }

    //get factorial Sequence based on the start and end indexes
    public ArrayList<int[]> getFactorialSequence() {
        return getPartOfpermutations(FactorialSequences, 4);
    }

    //the user can search a permutation in the permutation set
    public int searchHelper(int[] searchItem, ArrayList<int[]> permutations) {
        int index = 800;
        for (int i = 0; i < permutations.size(); ++i) {

            if (Arrays.equals(permutations.get(i), searchItem)) {
                index = i;
                System.out.println(index);
                break;
            }

        }
        return index;

    }

    // return the searched permutation index in the permutation set
    public int searchPermutation(int[] searchItem, int algorithmIndex) {
        switch (algorithmIndex) {
            case 1:
                return searchHelper(searchItem, SJTpermutations);
            case 2:
                return searchHelper(searchItem, HeapPermutationProcess);
            case 3:
                return searchHelper(searchItem, LexPermutations);
            case 4:
                return searchHelper(searchItem, FactorialPermutations);
            default:
                return 800;

        }

    }

    //get basic information about these four algorithms
    public String getMessage(String inforType) {
        switch (inforType) {
            case "SJT":
                String instruction1
                        = "This stratage uses The Steinhaus–Johnson–Trotter Algorithm, also called plain changes," + "\n"
                        + "It generates all of the permutations of n elements. "
                        + "Each permutation in the sequence that it generates " + "\n"
                        + "differs from the previous permutation by swapping two adjacent elements of the sequence.";
                return instruction1;

            case "Heap":
                String instruction2
                        = "This stratage uses Heap's algorithm " + "\n"
                        + "It generates all of the permutations of n elements. " + "\n"
                        + "Each permutation from the previous one by choosing a pair of elements to interchange. ";
                return instruction2;
            case "LexOrder":
                String instruction3
                        = "This stratage is based on finding the next permutation in lexicographic ordering,.\n "
                        + "It changes the given permutation in-place.\n"
                        + "Find the largest index k such that a[k] < a[k + 1]. If no such index exists, the permutation is the last permutation.\n"
                        + "Find the largest index l greater than k such that a[k] < a[l].\n"
                        + "Swap the value of a[k] with that of a[l].\n"
                        + "Reverse the sequence from a[k + 1] up to and including the final element a[n].";
                return instruction3;
            case "Factorial":
                String instruction4
                        = "This stratage uses factorial number system. " + "\n"
                        + "Convert a decimal number(for example 11) to a factorial system representation(1210), " + "\n"
                        + "Using the inverse factorial number(0121) as inversion vactor. " + "\n"
                        + "The inversion vector and the permutation is 1-1 mapping" + "\n"
                        + "Convert the inversion vector to permutation. Please Google how to do this! "+"\n"
                        +"The indexes on the left of this representation also means the corresponding decimal numbers! "
                        + "The sequence under the permutation in this representation is the corresponding factorial sequence";
                return instruction4;
                
            case  "end":
                String instruction5 = 
                        "You have seen all the permutations for the current size!.";
                return instruction5;
            case "start":
                String instruction6 =
                        "This is the beginning of the permutations, please go back to see more permutations!";
                return instruction6;
            case "no result":
                String instruction7 = 
                        "No result! Please check your input. The input should in the rang of 1-n (n is the size of the permutation)";
                return instruction7;
            default:
                return "error ";
                

        }

    }

}
