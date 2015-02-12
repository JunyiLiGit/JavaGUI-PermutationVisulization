
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
public class PanelForFactorial extends JPanel{
    private final ArrayList<int []> permutations;
    private final ArrayList<int []> factorialSequence;
    private final int itemIndex;
    private final Color [] color;
    private final int counter;
    private final int itemIndexForFacInPermutations;
    
    
    PanelForFactorial(ArrayList<int []> factorialPermutations, ArrayList<int []> factorialSequence,
           int itemIndex, int itemIndexForFacInPermutations, int counter){
        this.permutations = factorialPermutations;
        this.factorialSequence = factorialSequence;
        this.itemIndexForFacInPermutations = itemIndexForFacInPermutations;
        this.itemIndex = itemIndex;
        color = new Color[]{Color.GREEN, Color.PINK, Color.CYAN, Color.YELLOW, Color.RED, Color.ORANGE};
        this.counter = counter;
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2d = (Graphics2D) grphcs;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawPermutations(g2d);
    }
    
     public void drawPermutations(Graphics2D g2d){
        int w = getWidth()/12;
        int h = w;
        
        int permutationSize = permutations.get(0).length;
        for(int i =0; i<permutations.size(); ++i){
             //counter ==800 means do not draw the permutation index
            if(counter != 800){
            g2d.setFont(new Font("bigFont", Font.BOLD, w /2));
            g2d.setColor(Color.BLUE);
            g2d.drawString(Integer.toString(i+counter*12), 8, 60 + i * (w + 20));
            
            }
            
            //itemIndex ==800 means the user is not in the search mode. do not draw the rectangle in the view
            if (itemIndex != 800 && itemIndex == i) {
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.drawRect(25, 48 + i * (w + 20), (w+w)*(permutationSize)-23, h+3);                   
                }
            for (int j = 0; j < permutations.get(i).length; ++j){
                int number = permutations.get(i)[j];
                g2d.setColor(color[number-1]);
                int upperLeftX = 30 + j * (w + 20);
                int upperLeftY = 50 + i * (w + 20);
                g2d.fillOval(upperLeftX, upperLeftY, w, h);
                g2d.setFont(new Font("TimesRoman", Font.BOLD, w / 2));
                
                
                g2d.setColor(Color.BLACK);
                g2d.drawString(Integer.toString(factorialSequence.get(i)[j]), upperLeftX + w / 2 - 3, upperLeftY + w / 2 + 25);
                
                g2d.drawString(Integer.toString(permutations.get(i)[j]), upperLeftX + w / 2 - 3, upperLeftY + w / 2 + 3);
                             
            }
           
        
        }
        
         //itemIndexForLexInPermutations == 800 means do not draw the searched item index in the view
        if(itemIndexForFacInPermutations != 800){
            g2d.setFont(new Font("TimesRoman", Font.BOLD, w-3 ));
            g2d.setColor(Color.black);
            g2d.drawString("Position: "+Integer.toString(itemIndexForFacInPermutations), 80,600);                    
        
        }    
     }
}
