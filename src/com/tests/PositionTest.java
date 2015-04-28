package com.tests;

import com.spotgame.Board;
import com.spotgame.Position;
import org.junit.Assert;
import org.junit.Test;

public class PositionTest
{
    @Test
    public void testIsCorrect() throws Exception
    {
        Position p1 = new Position(2, 3);
        Assert.assertFalse(p1.isCorrect(Board.NB_CELLS));

        p1 = new Position(2, 2);
        Assert.assertTrue(p1.isCorrect(Board.NB_CELLS));
    }

    @Test
    public void testEquals() throws Exception
    {
        Position p1 = new Position(1, 1);
        Position p2 = new Position(2, 2);
        Assert.assertFalse(p1.equals(p2));

        p2 = new Position(1, 1);
        Assert.assertTrue(p1.equals(p2));

        p1 = new Position(3, 3);
        p2 = new Position(p1);
        Assert.assertTrue(p1.equals(p2));
    }
}