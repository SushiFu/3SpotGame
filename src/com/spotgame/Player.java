package com.spotgame;

/**
 * Created by Seiji Fouquet and Francois Mercier
 * On 15/02/15.
 */
public class Player
{
    private static int count = 0;

    private Piece piece;
    private int score;
    private String name;

    /**
     * Default constructor, associe le joueur avec sa piece
     *
     * @param piece la piece du joueur
     */
    public Player(Piece piece)
    {
        count++;
        name = "Joueur " + count;
        this.piece = piece;
        score = 0;
    }

    /**
     * Met a jour le score du joueur.
     *
     * @param board le plateau du jeu en cours
     */
    public void updateScore(Board board)
    {
        score += board.getPointsNumber(getPiece());
    }

    public int getScore()
    {
        return score;
    }

    public Piece getPiece()
    {
        return piece;
    }

    @Override
    public String toString()
    {
        return "Le " + name + " (" + piece.getColor().toString() + ")";
    }
}
