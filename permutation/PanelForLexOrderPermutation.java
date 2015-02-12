
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
public class PanelForLexOrderPermutation extends JPanel {

    private final ArrayList<int[]> permutations;
    private final int itemIndex;
    private final int itemIndexForLexInPermutations;
    private final Color[] color;

    private final int counter;

    PanelForLexOrderPermutation(ArrayList<int[]> lexPermutations, int itemIndex, int itemIndexForLexInPermutations, int counter) {
        this.permutations = lexPermutations;
        this.itemIndexForLexInPermutations = itemIndexForLexInPermutations;
        this.itemIndex = itemIndex;
        this.counter = counter;
        color = new Color[]{new Color(0, 0, 0), new Color(0, 0, 205), new Color(176, 224, 230),
            new Color(255, 255, 224), new Color(245, 255, 250), new Color(255, 255, 255)};

    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawPermutations(g2d);
    }

    public void drawPermutations(Graphics2D g2d) {
        int w = getWidth() / 12;
        int h = w;
        int permutationSize = permutations.get(0).length;

        for (int i = 0; i < permutations.size(); ++i) {

            //itemIndex ==800 means the user is not in the search mode. do not draw the rectangle in the view
            if (itemIndex != 800 && itemIndex == i) {
                g2d.setColor(Color.BLACK);
                g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.drawRect(25, 48 + i * (w + 20), (w + w) * (permutationSize) - 23, h + 3);
            }
            //counter ==800 means do not draw the permutation index
            if (counter != 800) {
                g2d.setFont(new Font("bigFont", Font.BOLD, w / 2));
                g2d.setColor(Color.BLUE);
                g2d.drawString(Integer.toString(i + counter * 12), 8, 60 + i * (w + 20));
            }
            for (int j = 0; j < permutations.get(i).length; ++j) {
                int number = permutations.get(i)[j];
                g2d.setColor(color[number - 1]);
                int upperLeftX = 30 + j * (w + 20);
                int upperLeftY = 50 + i * (w + 20);
                g2d.fillOval(upperLeftX, upperLeftY, w, h);
                g2d.setFont(new Font("TimesRoman", Font.BOLD, w / 2));
                if (permutations.get(i)[j] > 2) {
                    g2d.setColor(Color.BLACK);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.drawString(Integer.toString(permutations.get(i)[j]), upperLeftX + w / 2 - 3, upperLeftY + w / 2 + 3);

            }

        }
        //itemIndexForLexInPermutations == 800 means do not draw the searched item index in the view
        if (itemIndexForLexInPermutations != 800) {
            g2d.setFont(new Font("TimesRoman", Font.BOLD, w-3));
            g2d.setColor(Color.black);
            g2d.drawString("Position: "+Integer.toString(itemIndexForLexInPermutations), 80, 600);

        }

    }

}
