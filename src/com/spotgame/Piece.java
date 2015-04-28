package com.spotgame;

/**
 * Created by Seiji Fouquet and Francois Mercier
 * On 03/02/15.
 */
public class Piece
{
    private Color color;
    private Position origin;
    private Position second;

    /**
     * Default constructor
     *
     * @param color       la couleur de la piece
     * @param origin      la position d'origine de la piece
     * @param orientation l'orientation de la piece ( horizontal / vetical)
     */
    public Piece(Color color, Position origin, Orientation orientation)
    {
        this.color = color;
        this.origin = new Position(origin);
        if (orientation.equals(Orientation.Horizontal))
            second = new Position(origin.getLine(), origin.getColumn() + 1);
        else
            second = new Position(origin.getLine() - 1, origin.getColumn());
    }

    /**
     * Verifie si une piece a une position correct
     *
     * @return true si la piece est correct, false sinon
     */
    public boolean isCorrect()
    {
        return origin.isCorrect(Board.NB_CELLS) &&
               second.isCorrect(Board.NB_CELLS);
    }

    /**
     * Verifie si la piece est en intersection avec une autre piece.
     *
     * @param p la piece teste
     * @return true si les pieces se collisionnent, false sinon.
     */
    public boolean intersect(Piece p)
    {
        return origin.equals(p.origin) || second.equals(p.second) ||
               origin.equals(p.second) || p.origin.equals(second);
    }

    /**
     * Verifie si la piece est en intersection avec une position.
     *
     * @param p la position teste
     * @return true si la piece collisionne la position, false sinon.
     */
    public boolean intersect(Position p)
    {
        return origin.equals(p) || second.equals(p);
    }

    /**
     * Change la position de la piece a partir des coordonnes d'une autre piece.
     *
     * @param p la piece original
     */
    public void setPositions(Piece p)
    {
        if (!p.isCorrect())
            throw new RuntimeException("The piece is out of board.");
        this.origin = new Position(p.getOrigin());
        this.second = new Position(p.getSecond());
    }

    public boolean equals(Piece p)
    {
        return p != null && color.equals(p.color) && origin.equals(p.origin) &&
               second.equals(p.second);
    }

    public Color getColor()
    {
        return color;
    }

    public Position getOrigin()
    {
        return origin;
    }

    public Position getSecond()
    {
        return second;
    }

    public static enum Orientation
    {
        Horizontal, Vertical
    }
}
