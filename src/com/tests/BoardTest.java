package com.tests;

import com.spotgame.Board;
import com.spotgame.Color;
import com.spotgame.Piece;
import com.spotgame.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BoardTest
{
    @Test
    public void testBoard() throws Exception
    {
        Board board = new Board();
        for (Color c : Color.values())
            Assert.assertNotNull(board.getPiece(c));
    }

    @Test
    public void testGetPiecePossibilities() throws Exception
    {
        Board board = new Board();
        Piece blue = board.getPiece(Color.BLUE);
        Piece red = board.getPiece(Color.RED);

        ArrayList<Piece> potentials = board.getPiecePotentials(blue);
        Assert.assertEquals(potentials.size(), 3);

        Assert.assertTrue(potentials.get(0).getOrigin().equals(
                new Position(1, 0)));
        Assert.assertTrue(potentials.get(0).getSecond().equals(
                new Position(0, 0)));
        Assert.assertTrue(potentials.get(1).getOrigin().equals(
                new Position(2, 0)));
        Assert.assertTrue(potentials.get(1).getSecond().equals(
                new Position(1, 0)));
        Assert.assertTrue(potentials.get(2).getOrigin().equals(
                new Position(2, 0)));
        Assert.assertTrue(potentials.get(2).getSecond().equals(
                new Position(2, 1)));

        potentials = board.getPiecePotentials(red);
        Assert.assertEquals(potentials.size(), 3);

        Assert.assertTrue(potentials.get(0).getOrigin().equals(
                new Position(0, 0)));
        Assert.assertTrue(potentials.get(0).getSecond().equals(
                new Position(0, 1)));
        Assert.assertTrue(potentials.get(1).getOrigin().equals(
                new Position(1, 0)));
        Assert.assertTrue(potentials.get(1).getSecond().equals(
                new Position(0, 0)));
        Assert.assertTrue(potentials.get(2).getOrigin().equals(
                new Position(2, 0)));
        Assert.assertTrue(potentials.get(2).getSecond().equals(
                new Position(1, 0)));
    }

    @Test
    public void testGetPointsNumber() throws Exception
    {
        Board board = new Board();
        Piece red = board.getPiece(Color.RED);

        Assert.assertEquals(board.getPointsNumber(red), 1);

        red.setPositions(new Piece(Color.RED, new Position(2, 2),
                                   Piece.Orientation.Vertical));

        Assert.assertEquals(board.getPointsNumber(red), 2);
    }
}