
package goodsprice.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Raitis
 */
public class ProgressBar {

    private final JFrame f;

    public ProgressBar(String title) {
        
        f = new JFrame(title);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Container content = f.getContentPane();
        JProgressBar progressBar = new JProgressBar();
        //progressBar.setValue(25);
        progressBar.setIndeterminate(true);
        // progressBar.setStringPainted(true);
        TitledBorder border = BorderFactory.createTitledBorder("Please wait...");
        progressBar.setBorder(border);
        content.add(progressBar, BorderLayout.NORTH);
        f.setSize(350, 100);
        f.setAlwaysOnTop(true);
        //f.setVisible(true);
    }
    public JFrame show(){
         f.setVisible(true);
         return f;
    }
    
    
    
}
