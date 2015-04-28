package com.spotgame;

/**
 * Created by Seiji Fouquet and Francois Mercier
 * On 03/03/2015.
 */
public enum Color
{
    RED('R'),
    WHITE('W'),
    BLUE('B');

    private char value;

    private Color(char c)
    {
        value = c;
    }

    public char toChar()
    {
        return value;
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case BLUE:
                return "Bleu";
            case RED:
                return "Rouge";
            case WHITE:
                return "Blanche";
            default:
                return null;
        }
    }
}
