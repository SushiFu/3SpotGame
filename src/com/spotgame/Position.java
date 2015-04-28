package com.spotgame;

/**
 * Created by Seiji Fouquet and Francois Mercier
 * On 04/02/15.
 */
public class Position
{
    private int line;
    private int column;

    /**
     * Copy Constructor
     *
     * @param p la position a copie
     */
    public Position(Position p)
    {
        this(p.getLine(), p.getColumn());
    }

    /**
     * Default Constructor
     *
     * @param line   la ligne
     * @param column la colone
     */
    public Position(int line, int column)
    {
        this.line = line;
        this.column = column;
    }

    /**
     * Verifie si une position est correct.
     *
     * @param max la limite de la position
     * @return true si la position est correct, false sinon.
     */
    public boolean isCorrect(int max)
    {
        return getColumn() >= 0 && getColumn() < max &&
               getLine() >= 0 && getLine() < max;
    }

    public boolean equals(Position obj)
    {
        return obj != null && this.line == obj.line &&
               this.column == obj.column;
    }

    public int getLine()
    {
        return line;
    }

    public int getColumn()
    {
        return column;
    }
}
