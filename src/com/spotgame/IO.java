package com.spotgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Seiji Fouquet and Francois Mercier
 * On 03/03/2015.
 */
public class IO
{
    public static final int NB_CHARS_PER_CELL = 5;
    public static final int NB_CHARS = Board.NB_CELLS * NB_CHARS_PER_CELL - 2;
    private static final int NB_BTW_CELL = NB_CHARS_PER_CELL - 1;

    private static final char SEPARATOR = '*';
    private static final char EMPTYCHAR = ' ';
    private static final char MARKED = 'O';

    private char[][] buffer;
    private Scanner scanner;

    /**
     * Default constructor
     */
    public IO()
    {
        scanner = new Scanner(System.in);
        buffer = new char[NB_CHARS][NB_CHARS];
        for (int i = 0; i < NB_CHARS; i++)
            for (int j = 0; j < NB_CHARS; j++)
                if (i % 4 == 0 || j % 4 == 0)
                    buffer[i][j] = SEPARATOR;
        setDefaultBuffer();
    }

    @Override
    protected void finalize() throws Throwable
    {
        scanner.close();
        super.finalize();
    }

    /**
     * Demande la couleur du joueur 1 a l'utilisateur
     *
     * @return la couleur du joueur 1
     */
    public Color askPlayer1Color()
    {
        String resp;
        do
        {
            System.out.print(
                    "Entrez la couleur du Joueur 1 B(leu) / R(ouge): ");
            resp = scanner.nextLine();
        } while (resp.length() == 0 ||
                 (Character.toUpperCase(resp.charAt(0)) != Color.RED.toChar() &&
                 Character.toUpperCase(resp.charAt(0)) != Color.BLUE.toChar()));
        return resp.charAt(0) == Color.RED.toChar() ? Color.RED : Color.BLUE;
    }

    /**
     * Demande a l'utilisateur s'il veut recommencer une partie.
     *
     * @return true si l'utilisateur veut recommencer une partie, false sinon.
     */
    public boolean askRestart()
    {
        String resp;
        do
        {
            System.out.print("Voulez-vous recommencer une partie ? (O / N): ");
            resp = scanner.nextLine();
        } while (resp.length() == 0 ||
                 (Character.toUpperCase(resp.charAt(0)) != 'O' &&
                 Character.toUpperCase(resp.charAt(0)) != 'N'));
        boolean restart = resp.charAt(0) == 'O';
        if (!restart)
            System.out.println("Merci d'avoir joué !");
        return restart;
    }

    /**
     * Demande le mouvement voulant etre effectue par l'utilisateur.
     *
     * @param max limite max des mouvements disponibles
     * @return numero du mouvement (0 <= numero < max)
     */
    public int askMove(int max)
    {
        int mvt = -1;
        do
        {
            System.out.print("Entrez le numero du mouvement: ");
            if (scanner.hasNextInt())
            {
                int tmp = scanner.nextInt();
                scanner.nextLine();
                if (tmp > 0 && tmp <= max)
                    mvt = tmp;
            }
            else
                scanner.nextLine();
        } while (mvt == -1);
        return mvt - 1;
    }

    /**
     * Reinitialise le buffer d'affichage.
     */
    public void setDefaultBuffer()
    {
        for (int i = 0; i < NB_CHARS; i++)
            for (int j = 0; j < NB_CHARS; j++)
                if (i % 4 != 0 && j % 4 != 0)
                    buffer[i][j] = EMPTYCHAR;

        for (int i = 0; i < Board.NB_CELLS; i++)
            buffer[2 + i * NB_BTW_CELL][2 + NB_BTW_CELL * 2] = MARKED;
    }

    /**
     * Positionne les possibilites sur le buffer d'affichage.
     *
     * @param potentials liste des possibilites (pieces)
     */
    public void setPotentials(final ArrayList<Piece> potentials)
    {
        for (int i = 0; i < potentials.size(); i++)
        {
            Piece p = potentials.get(i);
            char value = buffer[2 + p.getOrigin().getLine() * NB_BTW_CELL]
                    [2 + p.getOrigin().getColumn() * NB_BTW_CELL];
            char count = Integer.toString(i + 1).charAt(0);

            if (i == Character.getNumericValue(value))
            {
                buffer[2 + p.getOrigin().getLine() * NB_BTW_CELL]
                        [1 + p.getOrigin().getColumn() * NB_BTW_CELL] = value;
                buffer[2 + p.getOrigin().getLine() * NB_BTW_CELL]
                        [2 + p.getOrigin().getColumn() * NB_BTW_CELL] = '-';
                buffer[2 + p.getOrigin().getLine() * NB_BTW_CELL]
                        [3 + p.getOrigin().getColumn() * NB_BTW_CELL] = count;
            }
            else
                buffer[2 + p.getOrigin().getLine() * NB_BTW_CELL]
                        [2 + p.getOrigin().getColumn() * NB_BTW_CELL] = count;
        }
    }

    /**
     * Positionne les pieces sur le buffer d'affichage, attention ne tient
     * pas compte de ce qui est deja affiche dans le buffer.
     *
     * @param pieces les pieces dessines sur le buffer
     */
    public void setPieces(final Piece... pieces)
    {
        for (Piece p : pieces)
        {
            buffer[2 + p.getOrigin().getLine() * NB_BTW_CELL]
                    [2 + p.getOrigin().getColumn() * NB_BTW_CELL] = p.getColor()
                                                                     .toChar();
            buffer[2 + p.getSecond().getLine() * NB_BTW_CELL]
                    [2 + p.getSecond().getColumn() * 4] = p.getColor().toChar();
        }
    }

    /**
     * Efface la console (affiche 50 nouvelles lignes)
     */
    public void clearConsole()
    {
        for (int i = 0; i < 80; i++)
            System.out.println();
    }

    /**
     * Affiche le joueur courant
     *
     * @param p le joueur
     */
    public void displayPlayerTurn(Player p)
    {
        System.out
                .println("-------------- " + p.toString() + " --------------");
    }

    /**
     * Affiche les resultats a la fin de la partie
     *
     * @param winner le gagnant
     * @param looser le perdant
     */
    public void displayResult(Player winner, Player looser)
    {
        displayPlayersState(winner, looser);
        System.out.println(winner.toString() + " a gagné !");
    }

    /**
     * Affiche les informations des joueurs
     *
     * @param players les joueurs
     */
    public void displayPlayersState(Player... players)
    {
        for (Player player : players)
            System.out.println(
                    player.toString() + " a " + player.getScore() + " points.");
    }

    /**
     * Dessine le buffer d'affichage
     *
     * @param msg message a afficher avant le plateau.
     */
    public void drawBuffer(String msg)
    {
        System.out.println();
        System.out.println(msg);
        for (int i = 0; i < NB_CHARS; i++)
            System.out.println(buffer[i]);
        System.out.println();
    }
}
