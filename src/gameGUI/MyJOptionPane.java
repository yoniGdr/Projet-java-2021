package gameGUI;
import javax.swing.*;
public class MyJOptionPane 
{
	private String nom; 
  MyJOptionPane()
  {
    JFrame frame = new JFrame();
    this.nom = JOptionPane.showInputDialog(frame,"Saisissez la taille de l'armée:");
    
  }
  
  
  /** 
   * @return int
   */
  public int getInt() {
	  int res = Integer.parseInt(this.nom);
	   return res;
  }
  
  /** 
   * @param args
   */
  public static void main(String[] args) 
  {
    new MyJOptionPane();
  }
}