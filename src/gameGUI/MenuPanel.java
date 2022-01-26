package gameGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 *
 */
public class MenuPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    //les boutons
    private JButton playButton;
    private JButton optionsButton;
    private JButton exitButton;
    private JButton rulesButton;

    public MenuPanel() {
        super();
        setPreferredSize(new Dimension(90, 350));
        generateButtons();
        setOpaque(false); 
    }

    /** cette methode genere les boutons
     *
     * */
    private void generateButtons() {
        //background des boutons
        ImageIcon playImg =  new ImageIcon( new ImageIcon("../assets/images/buttons/Play.png").getImage().getScaledInstance(90, 45, Image.SCALE_DEFAULT));
        ImageIcon rulesImg =  new ImageIcon( new ImageIcon("../assets/images/buttons/Rules.png").getImage().getScaledInstance(90, 45, Image.SCALE_DEFAULT));
        ImageIcon optionsImg =  new ImageIcon( new ImageIcon("../assets/images/buttons/Options.png").getImage().getScaledInstance(90, 45, Image.SCALE_DEFAULT));
        ImageIcon exitImg =  new ImageIcon( new ImageIcon("../assets/images/buttons/Exit.png").getImage().getScaledInstance(90, 50, Image.SCALE_DEFAULT));

        JButton empty = new JButton(); 
        empty.setPreferredSize(new Dimension(90, 50));
        empty.setVisible(true);
        empty.setOpaque(false);
        empty.setContentAreaFilled(false);
        empty.setBorderPainted(false);

        

        //creation des boutons
        
        this.playButton = new JButton(playImg);
        /* this.playButton.setIcon(playImg); */
        this.playButton.setPreferredSize(new Dimension(90, 50));
        this.playButton.setVisible(true);
        this.playButton.setOpaque(false);
        this.playButton.setContentAreaFilled(false);
        this.playButton.setBorderPainted(false);

        this.rulesButton = new JButton(rulesImg);
        this.rulesButton.setPreferredSize(new Dimension(90, 50));
        this.rulesButton.setVisible(true);
        this.rulesButton.setOpaque(false);
        this.rulesButton.setContentAreaFilled(false);
        this.rulesButton.setBorderPainted(false);

        this.optionsButton = new JButton(optionsImg);
        this.optionsButton.setPreferredSize(new Dimension(90, 50));
        this.optionsButton.setVisible(true);
        this.optionsButton.setOpaque(false);
        this.optionsButton.setContentAreaFilled(false);
        this.optionsButton.setBorderPainted(false);

        this.exitButton = new JButton(exitImg);
        this.exitButton.setPreferredSize(new Dimension(90, 50));
        this.exitButton.setVisible(true);
        this.exitButton.setOpaque(false);
         this.exitButton.setContentAreaFilled(false);
        this.exitButton.setBorderPainted(false);
    
        this.add(empty);
        this.add(playButton);
        this.add(rulesButton);
        this.add(optionsButton);
        this.add(exitButton);
        this.validate();
    }

    /*** cette methode permet de rajouter une a action au bouton passé en parametre
     * @param button bouton qui sera affecté
     * @param action l'action
     */
    public void addActionToBouton(JButton button, ActionListener action) {
        button.addActionListener(action);
    }

    /** getter pour le bouton Play
     * @return le button playButton
     */
    public JButton getPlayButton() {
        return this.playButton;
    }

    /** getter pour le bouton exit
     * @return le bouton exitButton
     */
    public JButton getExitButton() {
        return this.exitButton;
    }

    /** getter pour le bouton options
     * @return le bouton optionsButton
     */
    public JButton getOptionsButton() {
        return this.optionsButton;
    }

    /** getter pour le bouton rules
     * @return le bouton rulesButton
     */
    public JButton getRulesButton() {
        return this.rulesButton;
    }
}
