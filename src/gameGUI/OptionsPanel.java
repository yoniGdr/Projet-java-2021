package gameGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

public class OptionsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JButton backButton; //bouton pour revenir en arriere
    private final ImageIcon backImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/Back.png")
            .getImage()
            .getScaledInstance(90, 50, Image.SCALE_DEFAULT)
    );
    private final ImageIcon audioOnImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/audioOn.png")
            .getImage()
            .getScaledInstance(50, 50, Image.SCALE_DEFAULT)
    );
    private final ImageIcon musicOnImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/musicOn.png")
            .getImage()
            .getScaledInstance(50, 50, Image.SCALE_DEFAULT)
    );
    private final ImageIcon audioOffImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/audioOff.png")
            .getImage()
            .getScaledInstance(50, 50, Image.SCALE_DEFAULT)
    );
    private final ImageIcon musicOffImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/musicOff.png")
            .getImage()
            .getScaledInstance(50, 50, Image.SCALE_DEFAULT)
    );
    private final ImageIcon playersImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/Back.png")
            .getImage()
            .getScaledInstance(50, 50, Image.SCALE_DEFAULT)
    );
    private GameFrame gameGUI; // le jeu, pour pouvoir recuperer le menu et l'afficfer lors du click avec backButton

    private JButton audio; //bouton le son
    private JButton music; //bouton la music

    /**
     *
     */
    public OptionsPanel(GameFrame gameGUI) {
        super(); // Jpanel avec un background
        this.gameGUI = gameGUI; // le jeu
        this.backButton = new JButton(backImg); //bouton back
        this.audio = new JButton(audioOnImg); //mute audio
        this.music = new JButton(musicOnImg); //mute sound
        setOpaque(false);
        generateButtons();
        validate();
    }

    /** Cette methode genere le text et le bouton pour revenir en arriere
     */
    public void generateButtons() {

        this.audio.setOpaque(false);
        this.audio.setContentAreaFilled(false);
        this.audio.setBorderPainted(false);
        this.add(audio);

        this.music.setOpaque(false);
        this.music.setContentAreaFilled(false);
        this.music.setBorderPainted(false);
        this.add(music);

        this.backButton.setOpaque(false);
        this.backButton.setContentAreaFilled(false);
        this.backButton.setBorderPainted(false);
        this.add(backButton);

        //changer la taille de la fenetre, cacher le RulesPanel et afficher le menu
        this.backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameGUI.playButtonclick();
                        gameGUI.setSize(400, 425); //taille de la fenetre
                        gameGUI.getMenu().setVisible(true);
                        setVisible(false);
                        validate();
                    }
                }
            );
        this.audio.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gameGUI.soundMuted){
                            gameGUI.unMute();;
                            audio.setIcon(audioOnImg);
                        }else{
                            audio.setIcon(audioOffImg);
                            gameGUI.mute();
                        }
                    }
                }
            );
    
        this.music.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gameGUI.musiqueMuted){
                            gameGUI.musicOn();
                            music.setIcon(musicOnImg);
                        }else{
                            music.setIcon(musicOffImg);
                            gameGUI.musicOff();
                        }

                        

                    }
                }
            );
    }

    /** getter pour le bouton Back
     * @return le bouton back pour revenir en arriere
     */
    public JButton getBackButton() {
        return this.backButton;
    }
    /**
     *
     * @return
     */
    /* public JButton getSoundButton() { */
    /*     return this.soundButton; */
    /* } */
    /*  */
    /**
     *
     * @return
     */
    /* public JButton getMusicButton() { */
    /*     return this.musicButton; */
    /* } */
}
