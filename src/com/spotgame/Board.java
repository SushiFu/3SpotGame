package com.spotgame;

import java.util.ArrayList;

/**
 * Created by Seiji Fouquet and Francois Mercier
 * On 03/02/15.
 */
public class Board
{
    public static final int NB_CELLS = 3;
    private static final int NB_PIECES = 3;
    private Piece[] pieces;

    /**
     * Default constructor
     * RuntimeException si une piece est en dehors du plateau.
     */
    public Board()
    {
        pieces = new Piece[3];
        for (int i = 0; i < NB_PIECES; i++)
        {
            pieces[i] = new Piece(Color.values()[i],
                                  new Position(i, NB_CELLS - 2),
                                  Piece.Orientation.Horizontal);
            if (!pieces[i].isCorrect())
                throw new RuntimeException("The piece is out of board.");
        }
    }

    /**
     * Genere la liste des positions (pieces) possibles pour une piece
     *
     * @param original la piece a deplace
     * @return la liste des possibilites (pieces)
     */
    public ArrayList<Piece> getPiecePotentials(Piece original)
    {
        ArrayList<Piece> possibilities = new ArrayList<Piece>();
        for (int i = 0; i < NB_CELLS; i++)
        {
            for (int j = 0; j < NB_CELLS; j++)
            {
                boolean doAdd = true;
                Piece hor = new Piece(original.getColor(), new Position(i, j),
                                      Piece.Orientation.Horizontal);
                Piece vert = new Piece(original.getColor(), new Position(i, j),
                                       Piece.Orientation.Vertical);

                if (vert.isCorrect() && !vert.equals(original))
                {
                    for (Piece piece : pieces)
                        if (piece.getColor() != original.getColor())
                            doAdd &= !vert.intersect(piece);
                    if (doAdd)
                        possibilities.add(vert);
                }
                doAdd = true;
                if (hor.isCorrect() && !hor.equals(original))
                {
                    for (Piece piece : pieces)
                        if (piece.getColor() != original.getColor())
                            doAdd &= !hor.intersect(piece);
                    if (doAdd)
                        possibilities.add(hor);
                }
            }
        }
        return possibilities;
    }

    /**
     * Retourne le nombre le nombre de points d'une piece a un instant.
     *
     * @param p la piece
     * @return le nombre de points
     */
    public int getPointsNumber(Piece p)
    {
        int counter = 0;
        // verifie l'intersection avec la colonne la plus a droite du plateau.
        for (int i = 0; i < NB_CELLS; i++)
            counter += p.intersect(new Position(i, NB_CELLS - 1)) ? 1 : 0;
        return counter;
    }

    /**
     * Retourne la piece du plateau a partir de sa couleur
     *
     * @param color la couleur
     * @return la piece
     */
    public Piece getPiece(Color color)
    {
        return pieces[color.ordinal()];
    }
}
