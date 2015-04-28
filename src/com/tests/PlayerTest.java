package com.tests;

import com.spotgame.*;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest
{
    @Test
    public void testPlayer() throws Exception
    {
        Piece piece = new Piece(Color.BLUE, new Position(0, 0),
                                Piece.Orientation.Horizontal);
        Player p = new Player(piece);

        Assert.assertTrue(p.getPiece().equals(piece));
        Assert.assertTrue(p.getScore() == 0);

        piece.setPositions(new Piece(Color.RED, new Position(2, 1),
                                     Piece.Orientation.Vertical));
        Assert.assertTrue(p.getPiece().equals(piece));
    }

    @Test
    public void testUpdateScore() throws Exception
    {
        Board board = new Board();
        Player p = new Player(board.getPiece(Color.BLUE));
        Player p2 = new Player(board.getPiece(Color.RED));

        p.updateScore(board);
        Assert.assertEquals(p.getScore(), 1);

        p2.updateScore(board);
        p2.updateScore(board);
        Assert.assertEquals(p2.getScore(), 2);
    }
}