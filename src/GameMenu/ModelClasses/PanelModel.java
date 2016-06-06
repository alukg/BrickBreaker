package GameMenu.ModelClasses;

import javax.swing.*;
import java.awt.*;

/**
 * The base structure for all the windows. All the windows extends it.
 */
public class PanelModel extends JSplitPane {

    private final int dividerLocation = 651; //location of the divider.
    protected JPanel mainPanel;
    protected JPanel menuPanel;

    /**
     * Constructor
     */
    public PanelModel(){
        super(JSplitPane.HORIZONTAL_SPLIT);
        setDividerLocation(dividerLocation);
        this.setPreferredSize(new Dimension(820,650));
        this.setDividerSize(3);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(15, 1));
        menuPanel.setBackground(Color.BLACK);
        setRightComponent(menuPanel);
        setLeftComponent(mainPanel);
    }

}
