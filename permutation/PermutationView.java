/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package permutation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author kaitlyn--Junyi Li
 */
public final class PermutationView extends JPanel {

    private final JFrame frame;
    private final JPanel mainPanel;
    private final JScrollPane scrollPanel;

    private final JPanel toolBar;

    private final JPanel searchBar;
    private final JButton searchButton;
    private final JButton next;
    private final JButton previous;
    private final JButton clear;

    private final JButton factorial;
    private final JButton miniChangeSJT;
    private final JButton miniChangeHeap;
    private final JButton lexOrder;

    private boolean redraw = false;
    int size = 4;

    //counter for showing the permutation indexes
    int counter = 0;

    JRadioButton sizeButton1 = new JRadioButton("4");
    JRadioButton sizeButton2 = new JRadioButton("5");
    JRadioButton sizeButton3 = new JRadioButton("6");

    JTextField[] searchBox = {new JTextField(2), new JTextField(2), new JTextField(2), new JTextField(2)};

    public PermutationView() {

        frame = new JFrame("Permutation");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();

        scrollPanel = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        GridBagLayout layout = new GridBagLayout();

        mainPanel.setLayout(layout);

        toolBar = new JPanel();

        searchBar = new JPanel();

        searchButton = new JButton("Search");
        searchButton.setToolTipText("Click this button to search a specific permutation. The permutation position will show up at the buttom");
        
        next = new JButton("Next");
        next.setToolTipText("Click this button to see the next group of permutations!.");
        
        previous = new JButton("Previous");
        previous.setToolTipText("Click this button to see the previous group of permutations!.");
        
        clear = new JButton("Clear");
        clear.setToolTipText("Click this button to clear search result and start from the beginning!.");

        factorial = new JButton("Factorial Order");
        factorial.setToolTipText("Click this button to see stratage details!.");
        
        miniChangeSJT = new JButton("Swap Neighbours");
        miniChangeSJT.setToolTipText("Click this button to see stratage details!.");
        
        miniChangeHeap = new JButton("Swap Pairs");
        miniChangeHeap.setToolTipText("Click this button to see stratage details!.");

        lexOrder = new JButton("Lexicographic Order");
        lexOrder.setToolTipText("Click this button to see stratage details!.");
        

        sizeButton1.setActionCommand("4");
        sizeButton1.setSelected(true);

        sizeButton2.setActionCommand("5");

        sizeButton3.setActionCommand("6");

        ButtonGroup bG = new ButtonGroup();
        bG.add(sizeButton1);
        bG.add(sizeButton2);
        bG.add(sizeButton3);

        toolBar.add(sizeButton1);
        toolBar.add(sizeButton2);
        toolBar.add(sizeButton3);

        searchBar.add(searchButton);

        for (int i = 0; i < size; ++i) {
            searchBar.add(searchBox[i]);
        }

        searchBar.add(previous);
        searchBar.add(next);
        searchBar.add(clear);

        frame.getContentPane().add(toolBar, BorderLayout.NORTH);

        frame.getContentPane().add(searchBar, BorderLayout.PAGE_END);

        frame.getContentPane().add(scrollPanel);  // Add scrollPanel P to JFrame frame
        
        String instructions = 
                "You are not going to see the instruction again once you close this window!"+"\n"
                +"You can move your mouse to a button and see the function of that button"+"\n"
                +"Once you close this window, you will see four representations for permutation of size 4,5,6"+"\n"
                +"You can choose the radio buttons to choose the permutation size."+"\n"
                +"You can press the buttons above each representation to see the stratage that generate coresponding permutations"+"\n"
                +"You can press next or previous buttons to see the previous and next group of permutations"+"\n"
                +"You can search a permutation in these representations by typing that permutation inside the text boxes"+ "\n"
                + "One number each box, number should be from 1 to permutation size"+"\n"
                +"You can press clear button to clear the current search result and current representation and start from the beginning.";
         JOptionPane.showMessageDialog(null,instructions, "Permutation Reprentation Instructions", JOptionPane.INFORMATION_MESSAGE);

        
        frame.pack();
        
        frame.setVisible(true);
        
        
        

    }

    //init view 
    public void initView(ArrayList<int[]> SJTPermutations, ArrayList<int[]> SJTSwaps,
            ArrayList<int[]> HeapPermutations, ArrayList<int[]> HeapSwaps,
            ArrayList<int[]> LexPermutations,
            ArrayList<int[]> FactorPermutations, ArrayList<int[]> FactorSequence) {

        showPermutations(SJTPermutations, SJTSwaps, 800, 800,
                HeapPermutations, HeapSwaps, 800, 800,
                LexPermutations, 800, 800,
                FactorPermutations, FactorSequence, 800, 800);
    }

    public void setCounter(int c) {
        counter = c;

    }

    public void setSearchBoxSize(int size) {

        this.size = size;

    }

    //update the view based on the user's actions
    public void updateView() {
        searchBar.removeAll();
        searchBar.revalidate();
        searchBar.repaint();
        searchBar.add(searchButton);
        Arrays.fill(searchBox, null);
        searchBox = new JTextField[size];
        for (int i = 0; i < size; ++i) {
            JTextField t = new JTextField(2);
            searchBox[i] = t;
            searchBar.add(t);
        }

        searchBar.add(previous);
        searchBar.add(next);
        searchBar.add(clear);

    }

    public void clearSearchBox() {
        for (int i = 0; i < size; ++i) {
            searchBox[i].setText("");

        }

    }

    //add controller as action listener
    public void addController(ActionListener controller) {

        searchButton.addActionListener(controller);
        sizeButton1.addActionListener(controller);
        sizeButton2.addActionListener(controller);
        sizeButton3.addActionListener(controller);

        next.addActionListener(controller);
        previous.addActionListener(controller);
        clear.addActionListener(controller);

        factorial.addActionListener(controller);
        miniChangeSJT.addActionListener(controller);
        miniChangeHeap.addActionListener(controller);
        lexOrder.addActionListener(controller);

    }

    //when redraw is true, redraw the whole panel
    public void setRedraw() {
        redraw = true;
    }

    //get permutation that the user searches
    public int[] getText() {
        int[] tmp = new int[size];
        for (int i = 0; i < size; ++i) {
            if (!searchBox[i].getText().isEmpty()) {
                int value = Integer.parseInt(searchBox[i].getText());

                tmp[i] = value;
            }

        }

        return tmp;

    }

    // when the user is in search mode, disable the next and previous button
    public void disableNextAndPrevious() {
        next.setEnabled(false);
        previous.setEnabled(false);

    }

    public void enableNextAndPrevious() {
        next.setEnabled(true);
        previous.setEnabled(true);

    }

    //show help messages
    public void showMessage(String inforMessage, String titleBar) {

        JOptionPane.showMessageDialog(null, inforMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);

    }

   //show permutation in the view. I used four different panels to draw the four different representations,
    //when the user change the permutation size, I redraw the panel
    // itemIndexForSJTInView, itemIndexForHeapInView,itemIndexForLexInView,itenIndexForFacInView : the item index in the view
    //itemIndexForSJTInPermutations, itemIndexForHeapInPermutations, itemIndexForLexInPermutations,itemIndexForFacInPermutations: the item index in the coresponding permutation group
    public void showPermutations(ArrayList<int[]> permutationsForSJT, ArrayList<int[]> swapsForSJT, int itemIndexForSJTInView, int itemIndexForSJTInPermutations,
            ArrayList<int[]> permutationProcessForHeap, ArrayList<int[]> swapsForHeap, int itemIndexForHeapInView, int itemIndexForHeapInPermutations,
            ArrayList<int[]> permutationForLexOrder, int itemIndexForLexInView, int itemIndexForLexInPermutations,
            ArrayList<int[]> permutationForFactor, ArrayList<int[]> factorSequences, int itenIndexForFacInView, int itemIndexForFacInPermutations
    ) {
        if (!redraw) {

            PanelForFactorial panelForFactorial = new PanelForFactorial(permutationForFactor,
                    factorSequences, itenIndexForFacInView, itemIndexForFacInPermutations, counter);
            panelForFactorial.add(factorial, BorderLayout.NORTH);
            panelForFactorial.setPreferredSize(new Dimension(280, 650));
            panelForFactorial.setBorder(new LineBorder(Color.black, 5));
            mainPanel.add(panelForFactorial);

            PanelForMinChangeSJT panelForMinChangeSJT;
            panelForMinChangeSJT = new PanelForMinChangeSJT(permutationsForSJT, swapsForSJT, itemIndexForSJTInView, itemIndexForSJTInPermutations, counter);
            panelForMinChangeSJT.add(miniChangeSJT, BorderLayout.NORTH);
            panelForMinChangeSJT.setPreferredSize(new Dimension(280, 650));
            panelForMinChangeSJT.setBorder(new LineBorder(Color.black, 5));
            mainPanel.add(panelForMinChangeSJT);

            PanelForMinChangeHeap panelForMinChange = new PanelForMinChangeHeap(permutationProcessForHeap, swapsForHeap,
                    itemIndexForHeapInView, itemIndexForHeapInPermutations, counter);
            panelForMinChange.add(miniChangeHeap, BorderLayout.NORTH);
            panelForMinChange.setPreferredSize(new Dimension(280, 650));
            panelForMinChange.setBorder(new LineBorder(Color.black, 5));
            mainPanel.add(panelForMinChange);

            PanelForLexOrderPermutation panelForLexOrderPermutation = new PanelForLexOrderPermutation(permutationForLexOrder, itemIndexForLexInView, itemIndexForLexInPermutations, counter);
            panelForLexOrderPermutation.setPreferredSize(new Dimension(280, 650));
            panelForLexOrderPermutation.add(lexOrder, BorderLayout.NORTH);
            panelForLexOrderPermutation.setBorder(new LineBorder(Color.black, 5));
            mainPanel.add(panelForLexOrderPermutation);

        } else if (redraw) {

            mainPanel.removeAll();
            mainPanel.revalidate();
            mainPanel.repaint();

            PanelForFactorial panelForFactorial = new PanelForFactorial(permutationForFactor,
                    factorSequences, itenIndexForFacInView, itemIndexForFacInPermutations, counter);
            panelForFactorial.add(factorial, BorderLayout.NORTH);
            panelForFactorial.setPreferredSize(new Dimension(280, 650));
            panelForFactorial.setBorder(new LineBorder(Color.black, 5));
            mainPanel.add(panelForFactorial);

            PanelForMinChangeSJT panelForMinChangeSJT = new PanelForMinChangeSJT(permutationsForSJT, swapsForSJT,
                    itemIndexForSJTInView, itemIndexForSJTInPermutations, counter);
            panelForMinChangeSJT.add(miniChangeSJT, BorderLayout.NORTH);
            panelForMinChangeSJT.setPreferredSize(new Dimension(280, 650));
            panelForMinChangeSJT.setBorder(new LineBorder(Color.black, 5));
            mainPanel.add(panelForMinChangeSJT);

            PanelForMinChangeHeap panelForMinChange = new PanelForMinChangeHeap(permutationProcessForHeap, swapsForHeap,
                    itemIndexForHeapInView, itemIndexForHeapInPermutations, counter);
            panelForMinChange.add(miniChangeHeap, BorderLayout.NORTH);
            panelForMinChange.setPreferredSize(new Dimension(280, 650));
            panelForMinChange.setBorder(new LineBorder(Color.black, 5));
            mainPanel.add(panelForMinChange);

            PanelForLexOrderPermutation panelForLexOrderPermutation = new PanelForLexOrderPermutation(permutationForLexOrder, itemIndexForLexInView, itemIndexForLexInPermutations, counter);
            panelForLexOrderPermutation.add(lexOrder, BorderLayout.NORTH);
            panelForLexOrderPermutation.setPreferredSize(new Dimension(280, 650));
            panelForLexOrderPermutation.setBorder(new LineBorder(Color.black, 5));
            mainPanel.add(panelForLexOrderPermutation);

        }

    }

}
