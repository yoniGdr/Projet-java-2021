package gameGUI;

import static javax.swing.JOptionPane.showMessageDialog;

import game.*;
import game.Action;
import game.Character;
import game.board.*;
import game.farmGame.*;
import game.util.exception.NotEnoughFoodException;
import gameGUI.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class FarmGameGUI extends GameFrame {

    private static final long serialVersionUID = 1L;
    private int canPlay = 0;

    public FarmGameGUI() throws IOException {
        super("Farm Game");
        /* Musique de fond */
        try {
            this.music =
                new SimpleAudioPlayer(
                    "../assets/sounds/musics/Puzzle-Game.wav"
                );
            this.music.play();
        } catch (Exception e) {
            System.out.println("Fichier audio mehhh");
        }
        UIManager.put("OptionPane.okButtonText", "exit");
        //image du background
        //this.panneau = new ImagePanel("../assets/images/background1.png");
        this.panneau.setLocation(0, 0);
        setContentPane(this.panneau);
        this.gamePanel = new ImagePanel("../assets/images/background3.png");
        /* this.optionsPanel = new OptionsPanel(this); */
        //le board
        this.board = new ClassicBoard(6, 7);

        this.boardPanel = new BoardPanel(this, board);
        this.game = new FarmGame(board);
        this.actionsPanel = new ActionsPanel(this.gamePanel, this);

        FarmPlayer zac = new FarmPlayer("Zac", true);
        FarmPlayer isidore = new FarmPlayer("Isidore", true);
        FarmPlayer emilie = new FarmPlayer("Emilie", true);
        FarmPlayer yoni = new FarmPlayer("Yoni", true);

        this.game.addPlayer(zac);
        this.game.addPlayer(isidore);
        this.game.addPlayer(emilie);
        this.game.addPlayer(yoni);

        this.playersPanel = new PlayersPanel(this.gamePanel, this.game);
        setButtonsActions();

        getContentPane().setLayout(null);
        /* JOptionPane.showMessageDialog( */
        /*     this, */
        /*     "ET MERCÉÉÉÉÉÉ", */
        /*     "test error", */
        /*     JOptionPane.PLAIN_MESSAGE */
        /* ); */
        /* setWarningMsg("thank you for using java"); */
    }

    @Override
    protected void writeRules() {
        this.rules.setText(
                "Tout jeu est constitué d’un plateau, de joueurs et de règles du jeu. Le plateau, de forme rectangulaire, dont la taille " +
                "peut être choisie au lancement du jeu, est divisé en tuiles. Les joueurs manipulent des personnages qu’ils vont pouvoir " +
                "positionner sur les tuiles. Chaque tuile peut produire un type de ressource que récoltent les joueurs à chaque tour de " +
                "jeu. Une tuile peut contenir au maximum un personnage. Les ressources peuvent être converties." +
                "\n" +
                "Le fonctionnement général des jeux qu’on créera suivra toujours le schéma suivant. Chaque joueur joue tour à tour. Le" +
                "nombre de tours de jeu est choisi au lancement du jeu. A chaque tour le joueur :\n i/ réalise des actions (par exemple" +
                "positionner un personnage sur une tuile),\n ii/ récolte des ressources (par exemple du blé),\n iii/ distribue quelque chose à" +
                "ses personnages (par exemple leur verse de l’or)."
            );
    }

    @Override
    protected void setButtonsActions() {
        /* this.menu.addActionToBouton(menu.getRulesButton(), action); */
        this.menu.addActionToBouton(
                menu.getPlayButton(),
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        initGame();
                    }
                }
            );
    }

    @Override
    protected void initGame() {
        setSize(1200, 638);
        menu.setVisible(false);
        this.gamePanel.setVisible(true);
        this.gamePanel.setBounds(0, 0, 1200, 638);
        //System.out.println(this.panneau);
        this.panneau.add(gamePanel);
        //System.out.println(this.panneau);
        validate();
        this.gamePanel.setLayout(null);

        this.gameStaus.setBounds(500, 10, 300, 30);

        this.gameStaus.setEditable(false);
        this.gameStaus.setOpaque(false);
        this.gamePanel.add(this.gameStaus);

        this.gameStausActions.setBounds(
                40,
                43 + this.board.getWidth() * 75,
                this.board.getWidth() * 75,
                30
            );
        this.gameStausActions.setEditable(false);
        this.gameStausActions.setOpaque(false);
        this.gamePanel.add(this.gameStausActions);

        this.actuaRound.setBounds(40, 10, this.board.getWidth() * 75, 30);
        this.actuaRound.setEditable(false);
        this.actuaRound.setOpaque(false);
        this.gamePanel.add(this.actuaRound);

        this.boardPanel.generateBoard();
        this.playersPanel.generatePlayers();

        this.gamePanel.validate();

        this.actionsPanel.generateActions();
        playGame();
        this.gamePanel.validate();
        /* this.actionsPanel.unBlockActions(); */
    }

    /**
     * @param nbPlayer
     */
    public void changeCursor(int nbPlayer) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(
            "../assets/images/farmGame/cursor/farmer_" +
            this.colors.get(nbPlayer) +
            ".png"
        );
        Cursor c = toolkit.createCustomCursor(
            image,
            new Point(getX(), getY()),
            "img"
        );
        setCursor(c);
    }

    /**
     * @param player
     * @param nbPlayer
     * @throws NotEnoughFoodException
     */
    @Override
    public void manageRound(Player player, int nbPlayer)
        throws NotEnoughFoodException {
        HashMap<String, Action> actions = this.game.getActions();

        if (this.actualPlayer.isInterfactive()) {
            try {
                if (this.actualAction.equalsIgnoreCase("D")) {
                    Character farmer = player.createCharcater(
                        this.actualTile,
                        1
                    );
                    actions.get(this.actualAction).realise(farmer, player);
                    this.gameStausActions.setText(
                            player.getName() +
                            " a deployé dans " +
                            this.actualTile.toString() +
                            "{" +
                            this.actualTile.getXposition() +
                            "," +
                            this.actualTile.getYposition() +
                            "}"
                        );
                    //Thread.sleep(1000);
                } else {
                    if ((this.canPlay % 2 != 0)) {
                        this.canPlay++;
                        return;
                    }
                    if (this.actualAction.equals("R")) {
                        this.gameStausActions.setText(
                                this.actualPlayer.getName() + " ne fait rien."
                            );
                    } else {
                        this.gameStausActions.setText(
                                this.actualPlayer.getName() +
                                " echangé ses ressources contre de l'or."
                            );
                    }
                    actions.get(this.actualAction).realise(null, player);
                    this.canPlay++;
                }
            } catch (Exception ex) {}

            this.actionsPanel.unBlockActions();
            setCursor(Cursor.getDefaultCursor());
        } else { //aleatoire
            this.actionsPanel.blockActions();
            ArrayList<String> actionsRandom = new ArrayList<>();
            actionsRandom.add("D");
            actionsRandom.add("R");
            if (player.getResource().size() > 0) {
                actionsRandom.add("E");
            }
            this.actualAction =
                actionsRandom.get(RANDOM.nextInt(actionsRandom.size()));
            try {
                if (this.actualAction.equals("D")) {
                    ArrayList<Tile> keysAsArray = this.board.allUsableTiles();
                    Tile t = keysAsArray.get(
                        RANDOM.nextInt(keysAsArray.size())
                    );

                    setActualTile(t);

                    ImageIcon imgF = new ImageIcon(
                        "../assets/images/farmGame/cursor/farmer_" +
                        getColors().get(getActualNbPlayer()) +
                        ".png"
                    );
                    JButton farmer = new JButton(imgF);
                    farmer.setOpaque(false);
                    farmer.setContentAreaFilled(false);
                    farmer.setBorderPainted(false);
                    this.boardPanel.deployedCharacter.put(t, farmer);

                    Character f = player.createCharcater(this.actualTile, 1);
                    actions.get(this.actualAction).realise(f, player);
                    this.gameStausActions.setText(
                            player.getName() +
                            " a deployé dans " +
                            this.actualTile.toString() +
                            "{" +
                            this.actualTile.getXposition() +
                            "," +
                            this.actualTile.getYposition() +
                            "}"
                        );
                } else {
                    if (this.actualAction.equals("R")) {
                        this.gameStausActions.setText(
                                this.actualPlayer.getName() + " ne fait rien."
                            );
                    } else {
                        this.gameStausActions.setText(
                                this.actualPlayer.getName() +
                                " echangé ses ressources contre de l'or."
                            );
                    }
                    actions.get(this.actualAction).realise(null, player);
                }
            } catch (Exception e) {
                //
            }
        }
        player.harvest();
        player.payDeployedCharacters();
        this.playersPanel.updatePlayers();
        this.actualAction = "";
        this.actualTile = null;

        if (this.board.allUsableTiles().size() == 0) {
            this.endGame();
            return;
        }
        if (this.actualNbPlayer + 1 >= this.playersPanel.getNbPlayers()) {
            this.actualNbPlayer = 0;
            this.actualPlayer = this.playersPanel.getPlayers().get(0);
            this.yArrow = 40;
            this.arrow.setBounds(xArrow, yArrow, 50, 58);
        } else {
            this.actualNbPlayer++;
            this.actualPlayer =
                this.playersPanel.getPlayers().get(this.actualNbPlayer);
            this.yArrow += 120;
            this.arrow.setBounds(xArrow, yArrow, 50, 58);
        }
        if (!this.actualPlayer.isInterfactive()) {
            manageRound(this.actualPlayer, this.actualNbPlayer);
        }
        /* try { */
        /*     Thread.sleep(500); */
        /* } catch (Exception ee) {} */
        this.gameStaus.setText(
                this.getActualPlayer().getName() + " choisissez une action :"
            );
        if (this.actualNbPlayer == 0) this.actuaRound.setText(
                "Round : " + (++this.actuaRoundI) + "/" + this.game.getNbTours()
            );

        if (this.actuaRoundI > this.game.getNbTours()) {
            this.actuaRound.setText(
                    "Round : " +
                    this.game.getNbTours() +
                    "/" +
                    this.game.getNbTours()
                );
            endGame();
            return;
        }
        this.boardPanel.updateBoard();
        this.actionsPanel.unBlockActions();
        /* new Wait(this,2000).execute(); */
    }

    public void playGame() {
        this.actionsPanel.unBlockActions();
        this.xArrow = 820 - 54;
        this.yArrow = 40;
        this.gamePanel.add(this.arrow);
        this.arrow.setBounds(this.xArrow, this.yArrow, 50, 58);
        this.gamePanel.validate();
        this.actualPlayer = this.playersPanel.getPlayers().get(0);
        this.gameStaus.setText(
                this.actualPlayer.getName() + " choisissez une action :"
            );
        this.actuaRound.setText(
                "Round : " + (++this.actuaRoundI) + "/" + this.game.getNbTours()
            );
    }

    public void endGame() {
        try {
            String soundName = "../assets/sounds/game_status/game_over.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                new File(soundName).getAbsoluteFile()
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Fichier audio mehhh");
        }
        this.actionsPanel.blockActions();
        this.actualTile = null;
        this.actualNbPlayer = 0;
        this.actualPlayer = null;
        this.actualAction = null;
        if (this.game.winner() == null) JOptionPane.showMessageDialog(
            this,
            "Game over, pas de gagnant",
            "Game Over",
            JOptionPane.PLAIN_MESSAGE
        ); else JOptionPane.showMessageDialog(
            this,
            "Game Over \nGagnant : " + this.game.winner().getName() + " ",
            "Game Over",
            JOptionPane.PLAIN_MESSAGE
        );
        System.exit(1);
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FarmGameGUI farmGame = new FarmGameGUI();
    }
}
