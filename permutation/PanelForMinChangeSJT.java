
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
 * @author kaitlyn--Junyi Li
 */
public class PanelForMinChangeSJT extends JPanel {

    private final ArrayList<int[]> permutations;
    private final ArrayList<int[]> swaps;
    private final Color[] color;
    private final int itemNumber;
    private final int itemNumberInPermutation;
    private final int counter;

    public PanelForMinChangeSJT(ArrayList<int[]> permutations, ArrayList<int[]> swaps, int itemNumber, int itemNumberInPermutation, int counter) {
        color = new Color[]{Color.GREEN, Color.PINK, Color.CYAN, Color.YELLOW, Color.RED, Color.ORANGE};
        this.permutations = permutations;
        this.swaps = swaps;
        this.itemNumber = itemNumber;
        this.itemNumberInPermutation = itemNumberInPermutation;
        this.counter = counter;

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
        int permutationSize = permutations.get(0).length;

        for (int i = 0; i < permutations.size(); ++i) {

            //counter ==800 means do not draw the permutation index
            if (counter != 800) {
                g2.setFont(new Font("bigFont", Font.BOLD, w / 2));
                g2.setColor(Color.BLUE);
                g2.drawString(Integer.toString(i + counter * 12), 8, 60 + i * (w + 20));

            }
            //itemNumber ==800 means the user is not in the search mode. do not draw the rectangle in the view
            if (itemNumber != 800 && itemNumber == i) {
                g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawRect(25, 48 + i * (w + 20), (w + w) * (permutationSize) - 23, h + 3);

            }

            for (int j = 0; j < permutations.get(i).length; ++j) {

                int number = permutations.get(i)[j];
                if (number >= 1) {
                    g2.setColor(color[number - 1]);

                    int upperLeftX = 30 + j * (w + 20);
                    int upperLeftY = 50 + i * (w + 20);
                    g2.fillOval(upperLeftX, upperLeftY, w, h);

                    if (i < permutations.size() - 1 && j == Math.min(swaps.get(i)[1], swaps.get(i)[0])) {
                        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        g2.drawLine(upperLeftX + w / 2, upperLeftY + w / 2, 25 + (j + 1) * (w + 20) + w / 2, 40 + 12 + (i + 1) * (w + 20) + w / 2);
                    }
                    if (i < permutations.size() - 1 && j == Math.max(swaps.get(i)[1], swaps.get(i)[0])) {
                        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        g2.drawLine(upperLeftX + w / 2, upperLeftY + w / 2, 25 + (j - 1) * (w + 20) + w / 2, 40 + 12 + (i + 1) * (w + 20) + w / 2);
                    }

                    g2.setFont(new Font("TimesRoman", Font.BOLD, w / 2));
                    g2.setColor(Color.black);
                    g2.drawString(Integer.toString(permutations.get(i)[j]), upperLeftX + w / 2 - 3, upperLeftY + w / 2 + 3);
                }
            }

        }
        //itemIndexForLexInPermutations == 800 means do not draw the searched item index in the view
        if (itemNumberInPermutation != 800) {
            g2.setFont(new Font("TimesRoman", Font.BOLD, w-3));
            g2.setColor(Color.black);
            g2.drawString("Position : "+Integer.toString(itemNumberInPermutation), 80, 600);

        }

    }

}
