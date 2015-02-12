
package permutation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author kaitlyn
 */
public class PanelForMinChangeHeap extends JPanel {

    private final ArrayList<int[]> swaps;
    private final ArrayList<int[]> permutationProcess;
    private final Color[] color;
    private final int itemNumber;
    private final int itemNumberInPermutation;
    private final int counter;

    PanelForMinChangeHeap(ArrayList<int[]> permutationProcess, ArrayList<int[]> swaps, int itemNumber, int itemNumberInPermutation, int counter) {

        this.swaps = swaps;
        this.permutationProcess = permutationProcess;
        this.itemNumber = itemNumber;
        this.counter = counter;
        color = new Color[]{Color.GREEN, Color.PINK, Color.CYAN, Color.YELLOW, Color.RED, Color.ORANGE};
        this.itemNumberInPermutation = itemNumberInPermutation;

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawPermutations(g2d);

    }

    public void drawPermutations(Graphics2D g2) {
        int w = getWidth() / 12;
        int h = w;
        int permutationSize = permutationProcess.get(0).length;
        int index = 0;
        for (int i = 0; i < permutationProcess.size(); ++i) {

            //itemNumber ==800 means the user is not in the search mode. do not draw the rectangle in the view
            if (itemNumber != 800 && itemNumber == i) {
                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawRect(25, 48 + i * (w + 20), (w + w) * (permutationSize) - 23, h + 3);
            }
            //counter ==800 means do not draw the permutation index
            if (counter != 800) {
                g2.setFont(new Font("bigFont", Font.BOLD, w / 2));
                g2.setColor(Color.BLUE);
                g2.drawString(Integer.toString(i + counter * 14), 8, 60 + i * (w + 20));
            }

            int swapIndex1 = swaps.get(i)[0];
            int swapIndex2 = swaps.get(i)[1];

            for (int j = 0; j < permutationProcess.get(i).length; ++j) {

                int number = permutationProcess.get(i)[j];
                if (number >= 1) {
                    g2.setColor(color[number - 1]);

                    int upperLeftX = 30 + j * (w + 20);
                    int upperLeftY = 50 + i * (w + 20);

                    if (i < permutationProcess.size()) {
                        g2.fillOval(upperLeftX, upperLeftY, w, h);
                    }

                    if (i < permutationProcess.size() - 1 && (j == swapIndex1 || j == swapIndex2)) {

                        if (Math.abs(swapIndex1 - swapIndex2) == 1) {
                            if (j == Math.min(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX + 10 + w / 2, upperLeftY + 10 + w / 2, 18 + (j + 1) * (w + 20) + w / 2, 42 + (i + 1) * (w + 20) + w / 2);

                            } else if (j == Math.max(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX - 10 + w / 2, upperLeftY + 10 + +w / 2, 38 + (j - 1) * (w + 20) + w / 2, 42 + (i + 1) * (w + 20) + w / 2);

                            }

                        }

                        if (Math.abs(swapIndex1 - swapIndex2) == 2) {
                            if (j == Math.min(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX + 10 + w / 2, upperLeftY + 10 + w / 2, 18 + (j + 2) * (w + 20) + w / 2, 50 + (i + 1) * (w + 20) + w / 2);

                            } else if (j == Math.max(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX - 10 + w / 2, upperLeftY + 10 + w / 2, 38 + (j - 2) * (w + 20) + w / 2, 50 + (i + 1) * (w + 20) + w / 2);

                            }
                        }
                        if (Math.abs(swapIndex1 - swapIndex2) == 3) {
                            if (j == Math.min(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX + 10 + w / 2, upperLeftY + 10 + w / 2, 18 + (j + 3) * (w + 20) + w / 2, 50 + (i + 1) * (w + 20) + w / 2);
                            } else if (j == Math.max(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX - 10 + w / 2, upperLeftY + 10 + w / 2, 38 + (j - 3) * (w + 20) + w / 2, 50 + (i + 1) * (w + 20) + w / 2);

                            }
                        }
                        if (Math.abs(swapIndex1 - swapIndex2) == 4) {
                            if (j == Math.min(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX + 10 + w / 2, upperLeftY + 10 + w / 2, 18 + (j + 4) * (w + 20) + w / 2, 50 + (i + 1) * (w + 20) + w / 2);
                            } else if (j == Math.max(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX - 10 + w / 2, upperLeftY + 10 + w / 2, 38 + (j - 4) * (w + 20) + w / 2, 50 + (i + 1) * (w + 20) + w / 2);

                            }

                        }
                        if (Math.abs(swapIndex1 - swapIndex2) == 5) {
                            if (j == Math.min(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX + 10 + w / 2, upperLeftY + 10 + w / 2, 18 + (j + 5) * (w + 20) + w / 2, 10 + (i + 1) * (w + 20) + w / 2);
                            } else if (j == Math.max(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX - 10 + w / 2, upperLeftY + 10 + w / 2, 38 + (j - 5) * (w + 20) + w / 2, 10 + (i + 1) * (w + 20) + w / 2);

                            }

                        }
                        if (Math.abs(swapIndex1 - swapIndex2) == 6) {
                            if (j == Math.min(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX + 10 + w / 2, upperLeftY + 10 + w / 2, 18 + (j + 6) * (w + 20) + w / 2, 10 + (i + 1) * (w + 20) + w / 2);
                            } else if (j == Math.max(swaps.get(i)[1], swaps.get(i)[0])) {
                                g2.setColor(Color.BLACK);
                                g2.drawLine(upperLeftX - 10 + w / 2, upperLeftY + 10 + w / 2, 38 + (j - 6) * (w + 20) + w / 2, 10 + (i + 1) * (w + 20) + w / 2);

                            }

                        }

                    }

                    g2.setFont(new Font("TimesRoman", Font.BOLD, w / 2));
                    g2.setColor(Color.black);
                    g2.drawString(Integer.toString(permutationProcess.get(i)[j]), upperLeftX + w / 2 - 3, upperLeftY + w / 2 + 3);
                    if (itemNumber == 800 && j == permutationProcess.get(i).length - 1
                            && (((Math.abs(swapIndex1 - swapIndex2) == 1) && (Math.min(swapIndex2, swapIndex1) > 0))
                            || ((Math.abs(swapIndex1 - swapIndex2) == 2) && (Math.min(swapIndex2, swapIndex1) > 0))
                            || (Math.abs(swapIndex1 - swapIndex2) > 2))) {

                        g2.setColor(Color.RED);
                        g2.drawLine(upperLeftX + w + w / 2, upperLeftY, upperLeftX + 2 * w, upperLeftY + w);
                        g2.drawLine(upperLeftX + 2 * w, upperLeftY, upperLeftX + w + w / 2, upperLeftY + w);

                    }
                }
            }

        }

        //itemIndexForLexInPermutations == 800 means do not draw the searched item index in the view
        if (itemNumberInPermutation != 800) {
            g2.setFont(new Font("TimesRoman", Font.BOLD, w-3));
            g2.setColor(Color.black);
            g2.drawString("Position: "+Integer.toString(itemNumberInPermutation), 80, 600);

        }

    }

}
