package gameGUI;

import game.Tile;
import game.board.*;
import game.tile.*;
import java.awt.Image;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class BoardPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private int width;
    private int height;
    private ClassicBoard board;
    private ImagePanel gamePanel;
    private GameFrame gameFrame;
    protected HashMap<Tile, JButton> tilesButtons;
    protected HashMap<Tile, JButton> deployedCharacter;
    private static final ImageIcon ocean = new ImageIcon(
        new ImageIcon("../assets/images/tiles/ocean.png")
            .getImage()
            .getScaledInstance(75, 75, Image.SCALE_DEFAULT)
    );
    private static final ImageIcon plain = new ImageIcon(
        new ImageIcon("../assets/images/tiles/plain.png")
            .getImage()
            .getScaledInstance(75, 75, Image.SCALE_DEFAULT)
    );
    private static final ImageIcon forest = new ImageIcon(
        new ImageIcon("../assets/images/tiles/forest.png")
            .getImage()
            .getScaledInstance(75, 75, Image.SCALE_DEFAULT)
    );
    private static final ImageIcon desert = new ImageIcon(
        new ImageIcon("../assets/images/tiles/desert.png")
            .getImage()
            .getScaledInstance(75, 75, Image.SCALE_DEFAULT)
    );
    private static final ImageIcon mountain = new ImageIcon(
        new ImageIcon("../assets/images/tiles/mountain.png")
            .getImage()
            .getScaledInstance(75, 75, Image.SCALE_DEFAULT)
    );

    //pour gerer le click de deploy
    /* protected HashMap<JButton,HashMap<Integer,Integer>> tiles; */

    /** Constructeur BoardPanel
     *
     * @param gamePanel
     * @param board
     */
    public BoardPanel(GameFrame gameFrame, ClassicBoard board) {
        super();
        this.gameFrame = gameFrame;
        this.gamePanel = gameFrame.getGamePanel();
        this.board = board;
        this.width = this.board.getWidth();
        this.height = this.board.getHeight();
        this.tilesButtons = new HashMap<>();
        this.deployedCharacter = new HashMap<>();
        /* this.tiles= new HashMap<JButton,HashMap<Integer,Integer>>(); */

    }

    /** Cette designe le board sur le gamePanel
     */
    public void generateBoard() {
        int x = 40;
        int y = 40;
        Tile tile = null;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tile = board.getTile(i, j);
                if (tile instanceof Mountain) {
                    JButton mountainB = new JButton(mountain);
                    mountainB.setBounds(x, y, 75, 75);
                    mountainB.setBorder(null);
                    this.gamePanel.add(mountainB);
                    Tile t = tile;
                    mountainB.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (
                                    gameFrame
                                        .getActualAction()
                                        .equalsIgnoreCase("D")
                                )                                 {
                                gameFrame.setActualTile(t);

                                    try {
                                    ImageIcon imgF = new ImageIcon(
                                        "../assets/images/farmGame/cursor/farmer_" +
                                        gameFrame
                                            .getColors()
                                            .get(
                                                gameFrame.getActualNbPlayer()
                                            ) +
                                        ".png"
                                    );
                                    JButton farmer = new JButton(imgF);
                                    farmer.setOpaque(false);
                                    farmer.setContentAreaFilled(false);
                                    farmer.setBorderPainted(false);
                                    mountainB.add(farmer);
                                    deployedCharacter.put(t, farmer);
                                    gameFrame.manageRound(
                                        gameFrame.getActualPlayer(),
                                        gameFrame.getActualNbPlayer()
                                    );
                                } catch (Exception ex) {}
                            }
                            }
                        }
                    );
                    this.tilesButtons.put(tile, mountainB);
                } else if (tile instanceof Desert) {
                    JButton desertB = new JButton(desert);
                    desertB.setBounds(x, y, 75, 75);
                    desertB.setBorder(null);
                    this.gamePanel.add(desertB);
                    Tile t = tile;
                    desertB.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (
                                    gameFrame
                                        .getActualAction()
                                        .equalsIgnoreCase("D")
                                ) {
                                    gameFrame.setActualTile(t);
                                    try {
                                        ImageIcon imgF = new ImageIcon(
                                            "../assets/images/farmGame/cursor/farmer_" +
                                            gameFrame
                                                .getColors()
                                                .get(
                                                    gameFrame.getActualNbPlayer()
                                                ) +
                                            ".png"
                                        );
                                        JButton farmer = new JButton(imgF);

                                        farmer.setOpaque(false);
                                        farmer.setContentAreaFilled(false);
                                        farmer.setBorderPainted(false);
                                        desertB.add(farmer);
                                        deployedCharacter.put(t, farmer);
                                        gameFrame.manageRound(
                                            gameFrame.getActualPlayer(),
                                            gameFrame.getActualNbPlayer()
                                        );
                                    } catch (Exception ex) {}
                                }
                            }
                        }
                    );
                    this.tilesButtons.put(tile, desertB);
                    /* HashMap<Integer,Integer> cords = new HashMap<>(); */
                    /* cords.put(tile.getXposition(),tile.getYposition()); */
                    /* this.tiles.put(desertB,cords);  */

                } else if (tile instanceof Forest) {
                    JButton forestB = new JButton(forest);
                    forestB.setBounds(x, y, 75, 75);
                    forestB.setBorder(null);
                    this.gamePanel.add(forestB);
                    Tile t = tile;
                    forestB.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (
                                    gameFrame
                                        .getActualAction()
                                        .equalsIgnoreCase("D")
                                ) {
                                    gameFrame.setActualTile(t);
                                    try {
                                        ImageIcon imgF = new ImageIcon(
                                            "../assets/images/farmGame/cursor/farmer_" +
                                            gameFrame
                                                .getColors()
                                                .get(
                                                    gameFrame.getActualNbPlayer()
                                                ) +
                                            ".png"
                                        );
                                        JButton farmer = new JButton(imgF);

                                        farmer.setOpaque(false);
                                        farmer.setContentAreaFilled(false);
                                        farmer.setBorderPainted(false);
                                        forestB.add(farmer);
                                        deployedCharacter.put(t, farmer);
                                        gameFrame.manageRound(
                                            gameFrame.getActualPlayer(),
                                            gameFrame.getActualNbPlayer()
                                        );
                                    } catch (Exception ex) {}
                                }
                            }
                        }
                    );
                    this.tilesButtons.put(tile, forestB);
                    /* HashMap<Integer,Integer> cords = new HashMap<>(); */
                    /* cords.put(tile.getXposition(),tile.getYposition()); */
                    /* this.tiles.put(forestB,cords);  */

                } else if (tile instanceof Plain) {
                    JButton plainB = new JButton(plain);
                    plainB.setBounds(x, y, 75, 75);
                    plainB.setBorder(null);
                    this.gamePanel.add(plainB);
                    Tile t = tile;
                    plainB.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (
                                    gameFrame
                                        .getActualAction()
                                        .equalsIgnoreCase("D")
                                ) {
                                    gameFrame.setActualTile(t);
                                    try {
                                        ImageIcon imgF = new ImageIcon(
                                            "../assets/images/farmGame/cursor/farmer_" +
                                            gameFrame
                                                .getColors()
                                                .get(
                                                    gameFrame.getActualNbPlayer()
                                                ) +
                                            ".png"
                                        );
                                        JButton farmer = new JButton(imgF);

                                        farmer.setOpaque(false);
                                        farmer.setContentAreaFilled(false);
                                        farmer.setBorderPainted(false);
                                        plainB.add(farmer);
                                        deployedCharacter.put(t, farmer);
                                        //gameFrame.getGame().getActions().get("D").realise(gameFrame.actualPlayer.createCharcater(t, 1), gameFrame.actualPlayer);

                                        gameFrame.manageRound(
                                            gameFrame.getActualPlayer(),
                                            gameFrame.getActualNbPlayer()
                                        );
                                    } catch (Exception ex) {}
                                }
                            }
                        }
                    );
                    this.tilesButtons.put(tile, plainB);
                    /* HashMap<Integer,Integer> cords = new HashMap<>(); */
                    /* cords.put(tile.getXposition(),tile.getYposition()); */
                    /* this.tiles.put(plainB,cords);  */

                } else {
                    JButton oceanB = new JButton(ocean);
                    oceanB.setBounds(x, y, 75, 75);
                    oceanB.setBorder(null);
                    this.gamePanel.add(oceanB);
                    /* Tile t = tile; */
                    /* oceanB.addActionListener( */
                    /*     new ActionListener() { */
                    /*         public void actionPerformed(ActionEvent e) { */
                    /*             if(gameFrame.getActualAction().equalsIgnoreCase("D")){ */
                    /*             gameFrame.setActualTile(t); */
                    /*             try { */
                    /*                  */
                    /*             gameFrame.manageRound(gameFrame.getActualPlayer(), gameFrame.getActualNbPlayer());    */
                    /*             } catch (Exception ex) { */
                    /*              */
                    /*             }} */
                    /*         } */
                    /*     } */
                    /* ); */
                    this.tilesButtons.put(tile, oceanB);
                    /* HashMap<Integer,Integer> cords = new HashMap<>(); */
                    /* cords.put(tile.getXposition(),tile.getYposition()); */
                    /* this.tiles.put(oceanB,cords);  */

                }
                x += 75;
            }
            x = 40;
            y += 75;
        }
    }

    /**
     * @return HashMap<Tile, JButton>
     */
    public HashMap<Tile, JButton> getTilesButtons() {
        return this.tilesButtons;
    }

    /**
     *
     */
    public int getHeight() {
        return this.height;
    }

    /**
     *
     *
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * @return ClassicBoard
     */
    public ClassicBoard getBoard() {
        return this.getBoard();
    }

    /**getter des characters deployé
     * @return
     */
    public HashMap<Tile, JButton> getDeployedCharacters() {
        return this.deployedCharacter;
    }

    public void updateBoard() {
        ArrayList<Tile> all =new ArrayList<>(this.deployedCharacter.keySet()); 
        for (int i=0;i<all.size();i++) {
            Tile tile = all.get(i);
            if (!tile.isBusy() && tile instanceof UsableTile) {
                this.deployedCharacter.get(tile).setVisible(false);
                /* this.gameFrame.getGameStatusActions() */
                /*     .setText( */
                /*         gameFrame.getActualPlayer().getName()+ */
                /*         " n'a pas assez d'argent pour payer ses ouvrier." */
                /*     ); */
                this.deployedCharacter.remove(tile);
            }
        }
    }
}
