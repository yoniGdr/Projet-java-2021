package gameGUI;

import game.*;
import game.board.*;
import game.tile.*;
import game.util.exception.NotEnoughFoodException;
import gameGUI.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.text.Style;

/**
 * Classe Abstraite qui represente un jeu
 */
public abstract class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    protected final Random RANDOM = new Random();
    protected String actualAction; // action actuelle
    protected Tile actualTile; //tuile actuelmle
    protected int actualNbPlayer; //nb du player actuel
    protected Player actualPlayer; // player qui joue le round actuel
    protected JTextPane actuaRound; //actual round
    protected int actuaRoundI;
    protected Game game; // attribut jeu
    protected MenuPanel menu; // le menu
    protected RulesPanel rules; // les regles du jeu
    protected OptionsPanel options;
    protected BoardPanel boardPanel; // le board
    protected PlayersPanel playersPanel; //les players
    protected ActionsPanel actionsPanel; // les actions
    protected ImagePanel panneau; // le panneau de base (BACKGROUND)
    protected Boolean soundMuted; // attribut pour le son
    protected Boolean musiqueMuted; // attribut pour la musiqe
    /* protected static final Sound SOUND = new Sound(); // tous les sons et musiques */
    protected ImagePanel gamePanel; //le fond
    protected JTextPane gameStaus; //description du round
    protected JTextPane gameStausActions; //description des actions
    protected JButton pauseButton; //button de pause
    protected JButton arrow; //fleche du joueur actuel
    protected int yArrow; // position y d'arrow
    protected int xArrow; // position x d'arrow
    protected ClassicBoard board; // le board
    protected ArrayList<String> colors; // les 6 couleurs disponible placés dans l'orde
    protected JButton gamePaused;
    protected SimpleAudioPlayer music;
    protected OptionsPanel pausePanel;
    private final ImageIcon pauseImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/Pause.png")
            .getImage()
            .getScaledInstance(90, 50, Image.SCALE_DEFAULT)
    );
    private final ImageIcon resumeImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/Resume.png")
            .getImage()
            .getScaledInstance(90, 50, Image.SCALE_DEFAULT)
    );
    protected final ImageIcon exitImg = new ImageIcon(
        new ImageIcon("../assets/images/buttons/Exit.png")
            .getImage()
            .getScaledInstance(90, 50, Image.SCALE_DEFAULT)
    );
    private final ImageIcon gamePausedImg = new ImageIcon(
        "../assets/images/GAME-PAUSED.png"
    );
    private final ImageIcon arrowImg = new ImageIcon(
        "../assets/images/arrow.png"
    );

    /** Constructeur du jeu
     *
     * @param name nom du jeu
     */
    public GameFrame(String name) throws IOException {
        super(name);
        this.menu = new MenuPanel();
        this.rules = new RulesPanel(this);
        this.options = new OptionsPanel(this);
        this.panneau = new ImagePanel("../assets/images/background1.png");
        this.panneau.setLocation(0, 0);
        setContentPane(this.panneau);
        this.soundMuted = false;
        this.musiqueMuted = false;
        Font font = new Font("Serif", Font.ITALIC, 18);

        //texte qui affiche le tour de chaque player
        this.gameStaus = new JTextPane();
        this.gameStaus.setFont(font);

        //
        this.gameStausActions = new JTextPane();
        this.gameStausActions.setFont(font);
        this.gameStausActions.setForeground(Color.white.brighter());

        this.actuaRound = new JTextPane();
        //this.actuaRound.setForeground(Color.red.brighter());
        this.actuaRound.setFont(new Font("Serif", Font.ITALIC, 24));
        this.actuaRoundI = -1;
        this.actualTile = null;
        this.actualAction = "";
        this.actualNbPlayer = 0;
        this.actualPlayer = null;
        this.pauseButton = new JButton(pauseImg);
        this.arrow = new JButton(arrowImg);

        this.colors =
            new ArrayList(
                Arrays.asList(
                    "red",
                    "blue",
                    "green",
                    "yellow",
                    "orange",
                    "purple"
                )
            );
        this.pauseButton.setOpaque(false);
        this.pauseButton.setContentAreaFilled(false);
        this.pauseButton.setBorderPainted(false);

        this.arrow.setOpaque(false);
        this.arrow.setContentAreaFilled(false);
        this.arrow.setBorderPainted(false);

        this.pausePanel = new OptionsPanel(this);
        this.pausePanel.setBackground(Color.GRAY);
        this.pausePanel.getBackButton().setIcon(exitImg);
        this.pausePanel.getBackButton()
            .addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playButtonclick();
                        System.exit(0);
                    }
                }
            );
        //exit lorsque qu'on click sur la croix rouge de la fenetre
        WindowListener l = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(l);
        //panneau de background
        setSize(400, 425); //taille de la fenetre
        setResizable(false);
        setVisible(true);
        getContentPane().validate();

        //menu principal
        getContentPane().add(menu);
        menu.setLocation(150, 100); //emplacement du menu
        getContentPane().validate();
        getContentPane().setLayout(null);

        this.writeRules();

        //action de quitter lors du click sur bouton exit
        this.menu.addActionToBouton(
                this.menu.getExitButton(),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playButtonclick();
                        System.exit(0);
                    }
                }
            );

        //click sur le boutonrules
        this.menu.addActionToBouton(
                this.menu.getRulesButton(),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playButtonclick();
                        setSize(700, 485);
                        menu.setVisible(false);
                        rules.setVisible(true);
                        rules.setBounds(0, 0, 700, 485);
                        panneau.add(rules);
                        validate();
                    }
                }
            );
        //click sur Options
        this.menu.addActionToBouton(
                this.menu.getOptionsButton(),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playButtonclick();
                        menu.setVisible(false);
                        options.setVisible(true);
                        options.setBounds(150, 50, 110, 425);
                        panneau.add(options);
                        validate();
                    }
                }
            );

        this.gamePaused = new JButton();
        this.gamePaused.setOpaque(false);
        this.gamePaused.setContentAreaFilled(false);
        this.gamePaused.setBorderPainted(false);
        this.gamePaused.setIcon(gamePausedImg);
        this.gamePaused.setBounds(350, 140, 492, 71);
        add(gamePaused);
        this.gamePaused.setVisible(false);
        /* background.setIcon(backgroundPauseImg); */
        //click sur play
        this.menu.addActionToBouton(
                this.menu.getPlayButton(),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playButtonclick();
                        pauseButton.setBounds(15, 550, 90, 50);
                        add(pauseButton);
                        pausePanel.setVisible(false);
                        pausePanel.setBounds(525, 200, 175, 250);

                        /* JButton audio = new JButton(audioOnImg); //mute audio */
                        /* JButton music = new JButton(musicOnImg); //mute sound */

                        add(pausePanel);
                        validate();
                        pauseButton.addActionListener(
                            new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    playButtonclick();
                                    if (pausePanel.isVisible()) {
                                        pausePanel.setVisible(false);
                                        pauseButton.setIcon(pauseImg);
                                        actionsPanel.unBlockActions();
                                        gamePaused.setVisible(false);
                                    } else {
                                        actionsPanel.blockActions();
                                        pauseButton.setIcon(resumeImg);
                                        pausePanel.setVisible(true);
                                        gamePaused.setVisible(true);
                                    }
                                }
                            }
                        );
                        initGame();
                    }
                }
            );
    }

    /** getter pour le menu
     * @return le menu du jeu
     */
    public MenuPanel getMenu() {
        return this.menu;
    }

    /** cette methode renvoie si le jeu est muté
     * @return renvoie true si le jeu est muté ou false sinon.
     */
    public boolean ismuted() {
        return this.soundMuted;
    }

    /** cette methode mute le jeu
     */
    public void mute() {
        this.soundMuted = true;
    }

    /** cette methode  enleve le mute du jeu
     */
    public void unMute() {
        this.soundMuted = false;
    }

    /** cette methode joue la musique
     */
    public void musicOn() {
        this.musiqueMuted = false;
        try {
            this.music.resumeAudio();
        } catch (Exception e) {}
    }

    /** cette methode arrete la musique
     */
    public void musicOff() {
        this.musiqueMuted = true;
        this.music.pause();
    }

    /**getter pour le gamePanel
     * @return le pannel du jeu
     */
    public ImagePanel getGamePanel() {
        return this.gamePanel;
    }

    /**getter pour le game
     *
     * @return le jeu
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * @param tile
     */
    public void setActualTile(Tile tile) {
        this.actualTile = tile;
    }

    /**
     *  getter du nuemro du player actuel
     * @return
     */
    public int getActualNbPlayer() {
        return this.actualNbPlayer;
    }

    /**getter du player actuel
     *
     * @return
     */
    public Player getActualPlayer() {
        return this.actualPlayer;
    }

    /**
     * @return String
     */
    public String getActualAction() {
        return this.actualAction;
    }

    /**
     * getter de la tuile actuel
     * @return
     */
    public Tile getActualTile() {
        return this.actualTile;
    }

    /**
     * @param action
     */
    /** cette methode nous permet d'ecrire les regles dans le RulesPanel
     * */
    protected abstract void writeRules();

    /**
     * @param action
     */
    /** Cette methode nous permet d'atrribuer toutes les actions aux boutons
     */
    protected abstract void setButtonsActions();

    /**
     * @param action
     */
    /** cette methode designe le jeu dans la fenetre puis elle fait un this.game.play()
     *
     */
    protected abstract void initGame();

    /**
     * methode qui change l'action acutelle
     * @param action
     */
    public void setAction(String action) {
        this.actualAction = action;
    }

    /**
     * @param player
     * @param getColors(
     * @throws NotEnoughFoodException;public abstract void changeCursor(int nbPlayer);public ArrayList getColors()
     */
    /** cette methode permet de gerer le tour de chaque player
     * @throws NotEnoughFoodException
     */
    public abstract void manageRound(Player player, int nbPlayer)
        throws NotEnoughFoodException;

    /**
     * @param getColors(
     */
    public abstract void changeCursor(int nbPlayer);

    /**
     * @return ArrayList<String>
     */
    public ArrayList<String> getColors() {
        return this.colors;
    }

    public JTextPane getGameStatus() {
        return this.gameStaus;
    }

    public JTextPane getGameStatusActions() {
        return this.gameStausActions;
    }

    public void playButtonclick() {
        if(this.soundMuted)
            return;
        try {
            String soundName = "../assets/sounds/gui/click.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                new File(soundName).getAbsoluteFile()
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {}
    }
    public boolean isMuted(){
        return this.soundMuted;
    }
}
