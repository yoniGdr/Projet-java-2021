package game;

/** Interface, permet de représenter les actions que l'ont peu effectuer
 */
public interface Action {

    /** methode toString 
     * @param character : character
     * @param player : joueur de la partie
     * @param tile : tuile 
     * @param resource : ressource
     * @return string 
     */
    public String toString(
        Character character,
        Player player,
        Tile tile,
        Resource resource
    );

    /** Méthode qui realise les action 
     * @param character : character
     * @param player : joueur
     * @throws Exception toutes les exceptions 
     */
    public void realise(Character character, Player player) throws Exception;
}
