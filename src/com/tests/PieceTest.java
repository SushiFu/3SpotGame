package com.tests;

import com.spotgame.Color;
import com.spotgame.Piece;
import com.spotgame.Position;
import org.junit.Assert;
import org.junit.Test;

public class PieceTest
{
    @Test
    public void testPiece() throws Exception
    {
        Position tmp = new Position(1, 1);
        Piece p = new Piece(Color.RED, tmp, Piece.Orientation.Horizontal);
        Assert.assertTrue(p.getSecond().equals(new Position(tmp.getLine(),
                                                            tmp.getColumn()
                                                            + 1)));

        p = new Piece(Color.RED, tmp, Piece.Orientation.Vertical);
        Assert.assertTrue(p.getSecond().equals(new Position(tmp.getLine() - 1,
                                                            tmp.getColumn())));

        tmp = new Position(2, 2);
        Assert.assertFalse(p.getOrigin().equals(tmp));
    }

    @Test
    public void testIsCorrect() throws Exception
    {
        Piece p = new Piece(Color.RED, new Position(0, 2), Piece.Orientation
                .Vertical);
        Assert.assertFalse(p.isCorrect());

        p = new Piece(Color.RED, new Position(0, 2), Piece.Orientation
                .Horizontal);
        Assert.assertFalse(p.isCorrect());
    }

    @Test
    public void testIntersectWithPiece() throws Exception
    {
        Piece p1 = new Piece(Color.RED, new Position(1, 1),
                             Piece.Orientation.Vertical);
        Piece p2 = new Piece(Color.RED, new Position(0, 0),
                             Piece.Orientation.Horizontal);
        Assert.assertTrue(p1.intersect(p2));

        p2 = new Piece(Color.RED, new Position(2, 0),
                       Piece.Orientation.Horizontal);
        Assert.assertFalse(p1.intersect(p2));
    }

    @Test
    public void testIntersectWithPosition() throws Exception
    {
        Piece p1 = new Piece(Color.RED, new Position(1, 1),
                             Piece.Orientation.Vertical);
        Position pos = new Position(0, 1);
        Assert.assertTrue(p1.intersect(pos));

        pos = new Position(2, 0);
        Assert.assertFalse(p1.intersect(pos));
    }

    @Test
    public void testEquals() throws Exception
    {
        Piece p1 = new Piece(Color.RED, new Position(1, 1),
                             Piece.Orientation.Vertical);
        Piece p2 = new Piece(Color.BLUE, new Position(1, 1),
                             Piece.Orientation.Vertical);

        Assert.assertTrue(p1.equals(p1));
        Assert.assertFalse(p1.equals(p2));

        p2 = new Piece(Color.RED, new Position(1, 1),
                       Piece.Orientation.Vertical);

        Assert.assertTrue(p1.equals(p2));
    }

    @Test
    public void testSetPositions() throws Exception
    {
        Piece p1 = new Piece(Color.RED, new Position(2, 1),
                             Piece.Orientation.Vertical);
        Piece p2 = new Piece(Color.RED, new Position(1, 1),
                             Piece.Orientation.Vertical);
        p1.setPositions(p2);
        Assert.assertTrue(p1.equals(p2));

        p2.setPositions(new Piece(Color.RED, new Position(2, 1),
                                  Piece.Orientation.Vertical));
        Assert.assertFalse(p1.equals(p2));
    }
}