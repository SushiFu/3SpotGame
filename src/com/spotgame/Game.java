package com.spotgame;

import java.util.ArrayList;

/**
 * Created by Seiji Fouquet and Francois Mercier
 * On 03/02/15.
 */
public class Game
{
    //Conditions de victoires
    private static int MIN_SCORE = 6;
    private static int MAX_SCORE = 12;

    private IO io;
    private int currentPlayer;
    private Player p1;
    private Player p2;
    private Board board;

    /**
     * Default constructor
     */
    public Game()
    {
        io = new IO();
        board = new Board();
        currentPlayer = 0;
    }

    /**
     * Methode permettant de lancer une partie.
     *
     * @return true si les joueurs veulent recommencer, false sinon.
     */
    public boolean run()
    {
        io.clearConsole();

        managePlayersColor();
        io.clearConsole();
        while (true)
        {
            makePlayerRound();
            io.clearConsole();
            if (isEnd())
                break;
            makeWhiteRound();
            io.clearConsole();
            switchCurrent();
        }
        manageEndGame();
        return io.askRestart();
    }

    private void managePlayersColor()
    {
        Color player1Color = io.askPlayer1Color();
        if (player1Color.equals(Color.RED))
        {
            p1 = new Player(board.getPiece(Color.RED));
            p2 = new Player(board.getPiece(Color.BLUE));
        }
        else
        {
            p1 = new Player(board.getPiece(Color.BLUE));
            p2 = new Player(board.getPiece(Color.RED));
        }
    }

    private void manageEndGame()
    {
        if (p1.getScore() >= MAX_SCORE)
            if (p2.getScore() >= MIN_SCORE)
                io.displayResult(p1, p2);
            else
                io.displayResult(p2, p1);
        else if (p1.getScore() >= MIN_SCORE)
            io.displayResult(p2, p1);
        else
            io.displayResult(p1, p2);
    }

    private Player getCurrentPlayer()
    {
        return currentPlayer == 0 ? p1 : p2;
    }

    private Player getCurrentOpponent()
    {
        return currentPlayer == 0 ? p2 : p1;
    }

    private void switchCurrent()
    {
        currentPlayer = currentPlayer == 0 ? 1 : 0;
    }

    private void makePlayerRound()
    {
        io.displayPlayerTurn(getCurrentPlayer());
        // Affiche Plateau
        io.setDefaultBuffer();
        io.setPieces(p1.getPiece(), p2.getPiece(), board.getPiece(Color.WHITE));
        io.drawBuffer("Plateau actuel :");
        io.displayPlayersState(p1, p2);
        // Affiche Mouvement pour piece
        io.setDefaultBuffer();
        ArrayList<Piece> potentials = board
                .getPiecePotentials(getCurrentPlayer().getPiece());
        io.setPieces(getCurrentOpponent().getPiece(),
                     board.getPiece(Color.WHITE));
        io.setPotentials(potentials);
        io.drawBuffer("Déplacements possibles (Pièce " + getCurrentPlayer()
                .getPiece().getColor().toString() + "):");
        // Recupere entree utilisateur et met a jour le score si necessaire
        int mvt = io.askMove(potentials.size());
        getCurrentPlayer().getPiece().setPositions(potentials.get(mvt));
        getCurrentPlayer().updateScore(board);
    }

    private void makeWhiteRound()
    {
        io.displayPlayerTurn(getCurrentPlayer());

        // Affiche Plateau
        io.setDefaultBuffer();
        io.setPieces(p1.getPiece(), p2.getPiece(), board.getPiece(Color.WHITE));
        io.drawBuffer("Plateau actuel :");
        io.displayPlayersState(p1, p2);
        // Affiche Mouvement pour piece
        io.setDefaultBuffer();
        ArrayList<Piece> potentials = board
                .getPiecePotentials(board.getPiece(Color.WHITE));
        io.setPieces(getCurrentOpponent().getPiece(),
                     getCurrentPlayer().getPiece());
        io.setPotentials(potentials);
        io.drawBuffer("Déplacements possibles (Pièce " + Color.WHITE.toString()
                      + "):");
        // Recupere entree utilisateur et met a jour le score si necessaire
        int mvt = io.askMove(potentials.size());
        board.getPiece(Color.WHITE).setPositions(potentials.get(mvt));
    }

    private boolean isEnd()
    {
        return p1.getScore() >= MAX_SCORE || p2.getScore() >= MAX_SCORE;
    }
}