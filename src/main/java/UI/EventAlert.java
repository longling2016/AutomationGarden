package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by longlingwang on 2/19/16.
 */
public class EventAlert {
//    Function and structure of this class refers to http://stackoverflow.com/questions/14126975/joptionpane-without-button
    public static void show(String content) {
        final JOptionPane optionPane = new JOptionPane(content,
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

        final JDialog dialog = new JDialog();
        dialog.setTitle("Something is happening...check it out");
        dialog.setLocation(500, 400);
        dialog.setModal(true);

        dialog.setContentPane(optionPane);
        dialog.pack();

        Timer timer = new Timer(5000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }
}
