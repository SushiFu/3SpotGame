package com.spotgame;

public class Main
{
    /**
     * Point d'entrée de l'app.
     *
     * @param args non utilisé.
     */
    public static void main(String[] args)
    {
        while (new Game().run()) ;
    }
}