package permutation;

import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

/**
 *
 * @author kaitlyn--Junyi Li
 */
public class PermutationController implements java.awt.event.ActionListener {
    
    private PermutationModel model;
    private PermutationView view;

    //counter keeps track of the times that next and previous button are pressed.
    private int counter = 0;

    //the searched item index in different permutation group
    int itemIndexForSJT = 0;
    int itemIndexForHeap = 0;
    int itemIndexForLex = 0;
    int itemIndexForFac = 0;

    //default permutation size is 4
    private Integer permutationSize = 4;
    
    public PermutationController() {
        
    }
    
    public void addModel(PermutationModel m) {
        this.model = m;
    }

    //add view to the controller
    public void addView(PermutationView v) {
        this.view = v;
    }

    //init view
    public void initView() {
        
        view.initView(model.getPermutations(1), model.getSwaps(1),
                model.getPermutations(2), model.getSwaps(2),
                model.getPermutations(3),
                model.getPermutations(4), model.getFactorialSequence());
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JRadioButton) {
            view.setCounter(0);
            JRadioButton src = (JRadioButton) e.getSource();
            permutationSize = Integer.parseInt(src.getText());
            
            model.updateModel(permutationSize);
            model.setStart(1, 0);
            model.setEnd(1, 12);
            model.setStart(2, 0);
            model.setEnd(2, 14);
            model.setStart(3, 0);
            model.setEnd(3, 12);
            model.setStart(4, 0);
            model.setEnd(4, 12);
            view.setSearchBoxSize(permutationSize);
            view.updateView();
            view.setRedraw();
            //if the itemIndexInview and ItemIndexInPermutation are 800, do not draw the item itemIndex in the view
            //when the two value are not 800, draw the item index in the view for search function
            view.showPermutations(model.getPermutations(1), model.getSwaps(1), 800, 800,
                    model.getPermutations(2), model.getSwaps(2), 800, 800,
                    model.getPermutations(3), 800, 800,
                    model.getPermutations(4), model.getFactorialSequence(), 800, 800);
        }
        
        if (null != e.getActionCommand()) {
            switch (e.getActionCommand()) {
                //show next 12 permutations
                case "Next":
                    
                    if ((permutationSize == 4 && counter >= 0 && counter < 1)
                            || (permutationSize == 5 && counter >= 0 && counter < 9)
                            || (permutationSize == 6 && counter >= 0 && counter < 59)) {
                        
                        counter++;
                        view.setCounter(counter);
                        
                        model.setStart(1, counter * 12);
                        model.setEnd(1, (counter + 1) * 12);
                        model.setStart(2, counter * 14);
                        model.setEnd(2, (counter + 1) * 14);
                        model.setStart(3, counter * 12);
                        model.setEnd(3, (counter + 1) * 12);
                        model.setStart(4, counter * 12);
                        model.setEnd(4, (counter + 1) * 12);
                        
                        view.setRedraw();
                        view.showPermutations(model.getPermutations(1), model.getSwaps(1), 800, 800,
                                model.getPermutations(2), model.getSwaps(2), 800, 800,
                                model.getPermutations(3), 800, 800,
                                model.getPermutations(4), model.getFactorialSequence(), 800, 800);
                        
                    } else {
                        view.showMessage(model.getMessage("end"), "Helper");
                
                    }
                    
                    break;

                //show previous 12 permutations
                case "Previous":
                    
                    if (permutationSize == 4 && counter == 1
                            || (permutationSize == 5 && counter > 0 && counter < 10)
                            || (permutationSize == 6 && counter > 0 && counter < 60)) {
                        
                        counter--;
                        view.setCounter(counter);
                        model.setStart(1, (counter) * 12);
                        model.setEnd(1, (counter + 1) * 12);
                        model.setStart(2, counter * 14);
                        model.setEnd(2, (counter + 1) * 14);
                        model.setStart(3, (counter) * 12);
                        model.setEnd(3, (counter + 1) * 12);
                        model.setStart(4, counter * 12);
                        model.setEnd(4, (counter + 1) * 12);
                        view.setRedraw();
                        view.showPermutations(model.getPermutations(1), model.getSwaps(1), 800, 800,
                                model.getPermutations(2), model.getSwaps(2), 800, 800,
                                model.getPermutations(3), 800, 800,
                                model.getPermutations(4), model.getFactorialSequence(), 800, 800);
                        
                    } else {
                        view.showMessage(model.getMessage("start"), "Helper");
                
                    }
                    
                    break;

                //search a permutation. 
                //Based on the permutation index inside the four permutation groups, 
                //show permutations that is before and after that searched permutation
                case "Search":
                    view.setCounter(800);
                    if (view.getText()[0] != 0) {
                        itemIndexForSJT = model.searchPermutation(view.getText(), 1);
                        itemIndexForHeap = model.searchPermutation(view.getText(), 2);
                        itemIndexForLex = model.searchPermutation(view.getText(), 3);
                        itemIndexForFac = model.searchPermutation(view.getText(), 4);
                        if (itemIndexForSJT != 800 && itemIndexForHeap != 800 && itemIndexForLex != 800 && itemIndexForFac!=800) {
                            view.disableNextAndPrevious();
                            int startForSJT = 0, endForSJT = 12, startForHeap = 0, endForHeap = 14,
                                    startForLex = 0, endForLex = 12, startForFac = 0, endForFac = 12,
                                    indexForSJT = 0, indexForHeap = 0, indexForLex = 0, indexForFac = 0;
                            if (itemIndexForSJT - 6 >= 0 && itemIndexForSJT + 6 <= model.getPermutationAccount()) {
                                
                                startForSJT = itemIndexForSJT - 6;
                                endForSJT = itemIndexForSJT + 6;
                                indexForSJT = 6;
                                
                            }
                            if (itemIndexForLex - 6 >= 0 && itemIndexForLex + 6 <= model.getPermutationAccount()) {
                                
                                startForLex = itemIndexForLex - 6;
                                endForLex = itemIndexForLex + 6;
                                indexForLex = 6;
                            }
                            if (itemIndexForFac - 6 >= 0 && itemIndexForFac + 6 <= model.getPermutationAccount()) {
                                
                                startForFac = itemIndexForFac - 6;
                                endForFac = itemIndexForFac + 6;
                                indexForFac = 6;
                            }
                            
                            if (itemIndexForHeap - 6 >= 0 && itemIndexForHeap + 6 <= model.getPermutationAccountForHeap()) {
                                startForHeap = itemIndexForHeap - 6;
                                endForHeap = itemIndexForHeap + 6;
                                indexForHeap = 6;
                            }
                            
                            if (itemIndexForSJT - 6 >= 0 && itemIndexForSJT + 6 >= model.getPermutationAccount()) {
                                
                                startForSJT = itemIndexForSJT - 6;
                                endForSJT = model.getPermutationAccount();
                                indexForSJT = 6;
                            }
                            if (itemIndexForLex - 6 >= 0 && itemIndexForLex + 6 >= model.getPermutationAccount()) {
                                
                                startForLex = itemIndexForLex - 6;
                                endForLex = model.getPermutationAccount();
                                indexForLex = 6;
                            }
                            if (itemIndexForFac - 6 >= 0 && itemIndexForFac + 6 >= model.getPermutationAccount()) {
                                
                                startForFac = itemIndexForFac - 6;
                                endForFac = model.getPermutationAccount();
                                indexForFac = 6;
                            }
                            
                            if (itemIndexForHeap - 6 >= 0 && itemIndexForHeap + 6 >= model.getPermutationAccountForHeap()) {
                                
                                startForHeap = itemIndexForHeap - 6;
                                endForHeap = model.getPermutationAccountForHeap();
                                indexForHeap = 6;
                            }
                            
                            if (itemIndexForSJT - 6 <= 0 && itemIndexForSJT + 6 <= model.getPermutationAccount()) {
                                
                                startForSJT = 0;
                                endForSJT = 12;
                                indexForSJT = itemIndexForSJT;
                            }
                            if (itemIndexForLex - 6 <= 0 && itemIndexForLex + 6 <= model.getPermutationAccount()) {
                                startForLex = 0;
                                endForLex = 12;
                                indexForLex = itemIndexForLex;
                                
                            }
                            if (itemIndexForFac - 6 <= 0 && itemIndexForFac + 6 <= model.getPermutationAccount()) {
                                startForFac = 0;
                                endForFac = 12;
                                indexForFac = itemIndexForFac;
                                
                            }
                            if (itemIndexForHeap - 6 <= 0 && itemIndexForHeap + 6 <= model.getPermutationAccountForHeap()) {
                                startForHeap = 0;
                                endForHeap = 12;
                                indexForHeap = itemIndexForHeap;
                            }
                            
                            model.setStart(1, startForSJT);
                            model.setEnd(1, endForSJT);
                            model.setStart(2, startForHeap);
                            model.setEnd(2, endForHeap);
                            model.setStart(3, startForLex);
                            model.setEnd(3, endForLex);
                            model.setStart(4, startForFac);
                            model.setEnd(4, endForFac);
                            view.setRedraw();
                            view.showPermutations(model.getPermutations(1), model.getSwaps(1), indexForSJT, itemIndexForSJT,
                                    model.getPermutations(2), model.getSwaps(2), indexForHeap, itemIndexForHeap,
                                    model.getPermutations(3), indexForLex, itemIndexForLex,
                                    model.getPermutations(4), model.getFactorialSequence(), indexForFac, itemIndexForFac);
                            
                        }
                        else {
                        view.showMessage(model.getMessage("no result"), "Helper");
                
                    }
                        
                        
                    }
                    
                    break;
                case "Clear":
                    model.setStart(1, 0);
                    model.setEnd(1, 12);
                    model.setStart(2, 0);
                    model.setEnd(2, 14);
                    model.setStart(3, 0);
                    model.setEnd(3, 12);
                    model.setStart(4, 0);
                    model.setEnd(4, 12);
                    
                    view.enableNextAndPrevious();
                    view.clearSearchBox();
                    view.setCounter(0);
                    view.setRedraw();
                    
                    view.showPermutations(model.getPermutations(1), model.getSwaps(1), 800, 800,
                            model.getPermutations(2), model.getSwaps(2), 800, 800,
                            model.getPermutations(3), 800, 800,
                            model.getPermutations(4), model.getFactorialSequence(), 800, 800);
                    break;
                
                case "Factorial Order":
                    
                    view.showMessage(model.getMessage("Factorial"), "Factorial Order");
                    
                    break;
                case "Swap Neighbours":
                    
                    view.showMessage(model.getMessage("SJT"), "Swap Neighbours");
                    
                    break;
                
                case "Swap Pairs":
                    
                    view.showMessage(model.getMessage("Heap"), "Swap Pairs");
                    
                    break;
                
                case "Lexicographic Order":
                    
                    view.showMessage(model.getMessage("LexOrder"), "Lexicographic Order");
                    
                    break;
                
            }
        }
        
    }
    
}
