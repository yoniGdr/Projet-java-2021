package gameGUI;

import static javax.swing.JOptionPane.showMessageDialog;

import game.*;
import game.Action;
import game.Character;
import game.board.*;
import game.farmGame.*;
import game.tile.*;
import game.util.exception.NoPlayersInGameException;
import game.util.exception.NotEnoughFoodException;
import gameGUI.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import game.warGame.Army;
import game.warGame.WarGame;
import game.warGame.WarPlayer;
public class WarGameGUI extends GameFrame{
	
	//private JPanel panneau;
	private int canPlay =0;
    public WarGameGUI() throws IOException {

        super("War Game");

        this.gamePanel = new ImagePanel("../assets/images/background3.png");
        
        //le board
        
        this.board = new ClassicBoard(5, 5);

        this.boardPanel = new BoardPanel(this, board);
        this.game = new WarGame(board);
        this.actionsPanel = new ActionsPanel(this.gamePanel, this);

        WarPlayer zac = new WarPlayer("Zac", true);
        WarPlayer isidore = new WarPlayer("Isidore", true);
        WarPlayer yoni = new WarPlayer("Yoni", true);

        this.game.addPlayer(zac );
        this.game.addPlayer(isidore );
        this.game.addPlayer(yoni );

        this.playersPanel = new PlayersPanel(this.gamePanel, this.game);
        setButtonsActions();

        getContentPane().setLayout(null);
        
    }
    
    
 
    
    /** 
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException{
    	WarGameGUI game = new WarGameGUI();
    }



	@Override
	protected void writeRules() {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void setButtonsActions() {
		// TODO Auto-generated method stub
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

        this.gameStaus.setBounds(500, 10, 300, 20);
        
        this.gameStaus.setEditable(false);
        this.gameStaus.setOpaque(false);
        this.gamePanel.add(this.gameStaus);
        this.boardPanel.generateBoard();
        this.playersPanel.generatePlayers();

        this.gamePanel.validate();

        this.actionsPanel.generateActions();
        playGame();
        this.gamePanel.validate();
		
	}
	
    /** 
     * @param player
     * @param nbPlayer
     * @throws NotEnoughFoodException
     */
    @Override
    public void manageRound(Player player, int nbPlayer) throws NotEnoughFoodException {
        
        HashMap<String, Action> actions = this.game.getActions();
        if (this.actualPlayer.isInterfactive()) {
            try {
                if (this.actualAction.equalsIgnoreCase("D")) {
                    /* MyJOptionPane res = new MyJOptionPane(); */
                	Character army = player.createCharcater(this.actualTile, 1);
                    actions
                        .get(this.actualAction)
                        .realise(army
                            ,
                            player
                        );
                } else {
                    if ((this.canPlay%2!=0)){
                        this.canPlay++;
                        return;
                    }
                    actions.get(this.actualAction).realise(null, player);
                    this.canPlay++;
                }
            } catch (Exception ex) {
            	System.out.println(ex.getMessage());
            }

            this.actionsPanel.unBlockActions();
            setCursor(Cursor.getDefaultCursor());
        } else { //aleatoire
            this.actionsPanel.blockActions();
            ArrayList<String> actionsRandom = new ArrayList<>();
            actionsRandom.add("D");
            actionsRandom.add("R");
            if (player.getResource().size() > 0) actionsRandom.add("E");
            this.actualAction =
                actionsRandom.get(RANDOM.nextInt(actionsRandom.size()));
            try {
                if (this.actualAction.equalsIgnoreCase("D")) {}
                actions.get(this.actualAction).realise(null, player);
            } catch (Exception e) {
                //
            }
        }
        player.feedAllCharacter();
        player.harvest();
        //player.payDeployedCharacters();
        this.game.addPoints(player);
        this.boardPanel.updateBoard();
        this.playersPanel.updatePlayers();
        this.actualAction = "";
        this.actualTile = null;
        
        if (this.board.allUsableTiles().size() == 0){
            
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
            this.yArrow += 100;
            this.arrow.setBounds(xArrow, yArrow, 50, 58);
        }
        if (!this.actualPlayer.isInterfactive()) {
            manageRound(this.actualPlayer, this.actualNbPlayer);
        }
        this.gameStaus.setText(this.getActualPlayer().getName()+" choisissez une action :");
        try{
            Thread.sleep(500);
        }catch(Exception ee){
        
        } 

        /* new Wait(this,2000).execute(); */
    }
    
    /**
     * 
     */
	public void playGame() {
        /* this.playersPanel.updatePlayers(); */
        this.actionsPanel.unBlockActions();
        /* this.gamePanel.add(gameStaus); */
        /* this.gamePanel.validate(); */
        this.xArrow = 820 - 54;
        this.yArrow = 40;
        this.gamePanel.add(this.arrow);
        this.arrow.setBounds(this.xArrow, this.yArrow, 50, 58);
        this.gamePanel.validate();
        this.actualPlayer = this.playersPanel.getPlayers().get(0);
        this.gameStaus.setText(this.actualPlayer.getName() + " choisissez une action :");
    }
    /**
     * 
     */
	public void endGame() {
        this.actionsPanel.blockActions();
        this.actualTile = null;
        this.actualNbPlayer = 0;
        this.actualPlayer = null;
        this.actualAction = null;
    
        JOptionPane.showMessageDialog(
            this,
            "Game Over \n\tGagnant : " +this.game.winner().getName()+" ",
            "Game Over",
            JOptionPane.PLAIN_MESSAGE
        );
        /* setWarningMsg("thank you for using java"); */
    }



	
    /** 
     * @param nbPlayer
     */
    @Override
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
}

