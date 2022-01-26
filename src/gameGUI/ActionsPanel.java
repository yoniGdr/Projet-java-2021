package gameGUI;

import game.*;
import game.util.exception.NotEnoughFoodException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class ActionsPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected GameFrame gameFrame;
    protected ImagePanel gamePanel;

    //Players icons

    protected static final ImageIcon ACTION = new ImageIcon(
        "../assets/images/actions/Actions.png"
    );
    protected static final ImageIcon DEPLOY = new ImageIcon(
        "../assets/images/actions/deploy.png"
    );
    protected static final ImageIcon DONOTHING = new ImageIcon(
        "../assets/images/actions/donothing.png"
    );

    protected static final ImageIcon EXCHANGE = new ImageIcon(
        "../assets/images/actions/exchange.png"
    );

    protected static final ImageIcon BLOCKED = new ImageIcon(
        "../assets/images/actions/blocked.png"
    );

    protected JButton actionsB;
    protected JButton deployB;
    protected JButton exchangeB;
    protected JButton donothingB;

    protected JButton actionsBlocked;
    protected JButton deployBlocked;
    protected JButton exchangeBlocked;
    protected JButton donothingBlocked;

    /**
     *
     */
    public ActionsPanel(ImagePanel gamePanel, GameFrame gameFrame) {
        super();
        this.gameFrame = gameFrame;
        this.gamePanel = gamePanel;

        this.actionsBlocked = new JButton(this.BLOCKED);
        this.deployBlocked = new JButton(this.BLOCKED);
        this.exchangeBlocked = new JButton(this.BLOCKED);
        this.donothingBlocked = new JButton(this.BLOCKED);

        this.actionsB = new JButton(this.ACTION);
        this.deployB = new JButton(this.DEPLOY);
        this.exchangeB = new JButton(this.EXCHANGE);
        this.donothingB = new JButton(this.DONOTHING);
    }

    /**
     * cette methode va afficher les players
     *
     */
    public void generateActions() {
        this.actionsB.setBounds(600, 460, 120, 25);
        this.actionsB.setOpaque(false);
        this.actionsB.setContentAreaFilled(false);
        this.actionsB.setBorderPainted(false);

        this.deployB.setBounds(500, 500, 90, 90);
        this.deployB.setOpaque(false);
        this.deployB.setContentAreaFilled(false);
        this.deployB.setBorderPainted(false);

        /* JTextPane deployT= new JTextPane(); */
        /* deployT.setText("DEPLOY"); */
        /* deployT.setEditable(false); */
        /* deployT.setBounds(x+125,y+72,30,29); */
        /* deployT.setOpaque(false); */

        this.exchangeB.setBounds(610, 500, 90, 90);
        this.exchangeB.setOpaque(false);
        this.exchangeB.setContentAreaFilled(false);
        this.exchangeB.setBorderPainted(false);

        this.donothingB.setBounds(720, 500, 90, 90);
        this.donothingB.setOpaque(false);
        this.donothingB.setContentAreaFilled(false);
        this.donothingB.setBorderPainted(false);

        /* this.gamePanel.add(deployT); */

        this.deployBlocked.setBounds(500, 500, 90, 90);
        this.deployBlocked.setOpaque(false);
        this.deployBlocked.setContentAreaFilled(false);
        this.deployBlocked.setBorderPainted(false);

        this.exchangeBlocked.setBounds(610, 500, 90, 90);
        this.exchangeBlocked.setOpaque(false);
        this.exchangeBlocked.setContentAreaFilled(false);
        this.exchangeBlocked.setBorderPainted(false);

        this.donothingBlocked.setBounds(720, 500, 90, 90);
        this.donothingBlocked.setOpaque(false);
        this.donothingBlocked.setContentAreaFilled(false);
        this.donothingBlocked.setBorderPainted(false);

        this.gamePanel.add(this.actionsBlocked);
        this.gamePanel.add(this.deployBlocked);
        this.gamePanel.add(this.exchangeBlocked);
        this.gamePanel.add(this.donothingBlocked);

        this.gamePanel.add(this.actionsB);
        this.gamePanel.add(this.deployB);
        this.gamePanel.add(this.exchangeB);
        this.gamePanel.add(this.donothingB);
        setActions();
    }

    public void blockActions() {
        this.deployBlocked.setVisible(true);
        this.donothingBlocked.setVisible(true);
        this.exchangeBlocked.setVisible(true);
    }

    public void unBlockActions() {
        this.deployBlocked.setVisible(false);
        this.donothingBlocked.setVisible(false);
        this.exchangeBlocked.setVisible(false);
    }

    public void blockExchangeB() {
        this.exchangeBlocked.setVisible(true);
    }

    public void blockDonothingB() {
        this.donothingBlocked.setVisible(true);
    }

    public void blockDeployB() {
        this.deployBlocked.setVisible(true);
    }

    public void playActionClick() {
        if (gameFrame.isMuted()) return;
        try {
            String soundName = "../assets/sounds/actions/action_click.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                new File(soundName).getAbsoluteFile()
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {}
    }

    public void setActions() {
        this.deployB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameFrame.setAction("D");
                        blockExchangeB();
                        blockDonothingB();
                        gameFrame.changeCursor(gameFrame.getActualNbPlayer());
                        playActionClick();
                    }
                }
            );
        this.exchangeB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameFrame.setAction("E");
                        try {
                            gameFrame.manageRound(
                                gameFrame.getActualPlayer(),
                                gameFrame.getActualNbPlayer()
                            );
                            if(!gameFrame.isMuted()){
                                
                            String soundName =
                                "../assets/sounds/actions/handleCoins.wav";
                            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                                new File(soundName).getAbsoluteFile()
                            );
                            Clip clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.start();
                            }
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            );
        this.donothingB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameFrame.setAction("R");
                        try {
                            gameFrame.manageRound(
                                gameFrame.getActualPlayer(),
                                gameFrame.getActualNbPlayer()
                            );
                            playActionClick();
                        } catch (NotEnoughFoodException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            );
    }
}
