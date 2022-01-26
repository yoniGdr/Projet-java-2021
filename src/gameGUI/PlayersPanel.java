package gameGUI;

import game.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
public class PlayersPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected Game game;
    protected ImagePanel gamePanel;

    //Players icons
    protected static final ImageIcon PLAYER_BLUE = new ImageIcon(
        "../assets/images/players/player_blue.png"
    );
    protected static final ImageIcon PLAYER_PURPLE = new ImageIcon(
        "../assets/images/players/player_purple.png"
    );
    protected static final ImageIcon PLAYER_GREEN = new ImageIcon(
        "../assets/images/players/player_green.png"
    );
    protected static final ImageIcon PLAYER_ORANGE = new ImageIcon(
        "../assets/images/players/player_orange.png"
    );
    protected static final ImageIcon PLAYER_RED = new ImageIcon(
        "../assets/images/players/player_red.png"
    );
    protected static final ImageIcon PLAYER_YELLOW = new ImageIcon(
        "../assets/images/players/player_yellow.png"
    );

    private ArrayList<ImageIcon> playersColors;

    protected static final ImageIcon PLAYERS = new ImageIcon(
        "../assets/images/players/Players.png"
    );

    //gold and food icons
    protected static final ImageIcon GOLD = new ImageIcon(
        "../assets/images/coin.png"
    );
    protected static final ImageIcon FOOD = new ImageIcon(
        "../assets/images/food.png"
    );

    //points

    protected static final ImageIcon POINTS = new ImageIcon(
        "../assets/images/point.png"
    );
    //ressources icons
    protected static final ImageIcon WOOD = new ImageIcon(
        "../assets/images/resources/wood.png"
    );
    protected static final ImageIcon CORN = new ImageIcon(
        "../assets/images/resources/corn.png"
    );
    protected static final ImageIcon SAND = new ImageIcon(
        "../assets/images/resources/sand.png"
    );
    protected static final ImageIcon ROCK = new ImageIcon(
        "../assets/images/resources/rock.png"
    );

    protected List<Player> players;
    protected HashMap<Player, ArrayList<JTextPane>> playersInfo;

    /**
     * 
     * @param gamePanel
     * @param game
     */
    public PlayersPanel(ImagePanel gamePanel, Game game) {
        super();
        this.playersColors = new ArrayList<>();
        this.playersColors.add(this.PLAYER_RED);
        this.playersColors.add(this.PLAYER_BLUE);
        this.playersColors.add(this.PLAYER_GREEN);
        this.playersColors.add(this.PLAYER_YELLOW);
        this.playersColors.add(this.PLAYER_ORANGE);
        this.playersColors.add(this.PLAYER_PURPLE);
        this.playersInfo = new HashMap<>();
        this.game = game;
        this.gamePanel = gamePanel;
        this.players = this.game.getPlayers();
    }

    /**
     * cette methode va afficher les players
     *
     */
    public void generatePlayers() {
        int nbPlayer = this.players.size();

        JButton playersB = new JButton(this.PLAYERS);
        playersB.setBounds(955, 10, 120, 25);
        playersB.setOpaque(false);
        playersB.setContentAreaFilled(false);
        playersB.setBorderPainted(false);
        this.gamePanel.add(playersB);
        int x = 820;
        int y = 30;
        int k = 0;

        for (int i = 0; i < nbPlayer; i++) {
            Player player = this.players.get(i);
            
            JTextPane name = new JTextPane();
            name.setEditable(false);
            name.setBounds(x+10, y + 87, 90, 15);
            name.setOpaque(false);
            name.setText(player.getName());
            
            
            JButton playerB = new JButton(this.playersColors.get(k));
            JButton goldB = new JButton(this.GOLD);

            JButton rockB = new JButton(this.ROCK);
            JButton sandB = new JButton(this.SAND);
            JButton cornB = new JButton(this.CORN);
            JButton woodB = new JButton(this.WOOD);

            JButton pointsB = new JButton(this.POINTS);
            //Player
            playerB.setBounds(x, y, 90, 90);
            playerB.setOpaque(false);
            playerB.setContentAreaFilled(false);
            playerB.setBorderPainted(false);

            //Gold
            goldB.setBounds(x + 93, y + 62, 30, 29);
            goldB.setOpaque(false);
            goldB.setContentAreaFilled(false);
            goldB.setBorderPainted(false);

            JTextPane nbGold = new JTextPane();
            //nbGold.setText("x" + new Integer(player.getGold()).toString());
            nbGold.setEditable(false);
            nbGold.setBounds(x + 125, y + 72, 30, 29);
            nbGold.setOpaque(false);

            //points
            pointsB.setBounds(x + 125+40, y + 62, 30, 30);
            pointsB.setOpaque(false);
            pointsB.setContentAreaFilled(false);
            pointsB.setBorderPainted(false);

            JTextPane nbPoints = new JTextPane();
            //nbGold.setText("x" + new Integer(player.getGold()).toString());
            nbPoints.setEditable(false);
            nbPoints.setBounds(x + 125+40+35, y + 72, 30, 29);
            nbPoints.setOpaque(false);
            //Rock
            rockB.setBounds(x + 100, y + 15, 35, 36);
            rockB.setOpaque(false);
            rockB.setContentAreaFilled(false);
            rockB.setBorderPainted(false);

            JTextPane nbRock = new JTextPane();
            //nbRock.setText("x" + player.showResources().get("Rock").toString());
            nbRock.setEditable(false);
            nbRock.setBounds(x + 100 + 38, y + 30, 30, 29);
            nbRock.setOpaque(false);

            //Corn
            cornB.setBounds(x + 155, y + 15, 35, 36);
            cornB.setOpaque(false);
            cornB.setContentAreaFilled(false);
            cornB.setBorderPainted(false);
            JTextPane nbCorn = new JTextPane();

            //nbCorn.setText("x" + player.showResources().get("Corn").toString());
            nbCorn.setEditable(false);
            nbCorn.setBounds(x + 150 + 38, y + 30, 30, 29);
            nbCorn.setOpaque(false);

            //Wood
            woodB.setBounds(x + 205, y + 15, 35, 36);
            woodB.setOpaque(false);
            woodB.setContentAreaFilled(false);
            woodB.setBorderPainted(false);

            JTextPane nbWood = new JTextPane();
            //nbWood.setText("x" + player.showResources().get("Wood").toString());
            nbWood.setEditable(false);
            nbWood.setBounds(x + 200 + 38, y + 30, 30, 29);
            nbWood.setOpaque(false);

            //Sand
            sandB.setBounds(x + 255, y + 15, 72, 36);
            sandB.setOpaque(false);
            sandB.setContentAreaFilled(false);
            sandB.setBorderPainted(false);
            JTextPane nbSand = new JTextPane();
            //nbSand.setText("x" + player.showResources().get("Sand").toString());
            nbSand.setEditable(false);
            nbSand.setBounds(x + 250 + 73, y + 30, 30, 29);
            nbSand.setOpaque(false);

            this.gamePanel.add(playerB);
            this.gamePanel.add(name);
            
            this.gamePanel.add(goldB);
            this.gamePanel.add(nbGold);

            this.gamePanel.add(pointsB);
            this.gamePanel.add(nbPoints);
            
            this.gamePanel.add(rockB);
            this.gamePanel.add(nbRock);

            this.gamePanel.add(cornB);
            this.gamePanel.add(nbCorn);

            this.gamePanel.add(sandB);
            this.gamePanel.add(nbSand);

            this.gamePanel.add(woodB);
            this.gamePanel.add(nbWood);

            ArrayList<JTextPane> values = new ArrayList<>();
            values.add(nbGold);
            values.add(nbCorn);
            values.add(nbSand);
            values.add(nbRock);
            values.add(nbWood);
            values.add(nbPoints);
            this.playersInfo.put(player, values);

            y += 110;
            k++;
        }
    }

    /**
     * cette methode mettra a jour les stats des players
     */
    public void updatePlayers() {
        for (Player player : this.playersInfo.keySet()) {
            String[] values = {
                Integer.valueOf(player.getGold()).toString(),
                player.showResources().get("Corn").toString(),
                player.showResources().get("Sand").toString(),
                player.showResources().get("Rock").toString(),
                player.showResources().get("Wood").toString(),
                Integer.valueOf(player.countPoint()).toString()
            };
            int i = 0;
            for(JTextPane text : this.playersInfo.get(player)){
                text.setText("x"+values[i++]);
                 
            }
        }
    }

    
    /** 
     * @return List<Player>
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    
    /** 
     * @return int
     */
    public int getNbPlayers() {
        return this.players.size();
    }
}
