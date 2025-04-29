package model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private Deck deck;
    private DiscardDeck discardPile; // el jugador tiene su lista de cartas y organos y un estado adicional en la
                                     // zona de juego
    private int currentPlayerIndex;

    public Game(List<String> playerNames) {
        this.players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        this.deck = new Deck();
        this.discardPile = new DiscardDeck();
        this.currentPlayerIndex = 0;
    }

    public void startGame() {
        deck.shuffle();
        handOutCardsToPlayers(players);
    }

    public void handOutCardsToPlayers(List<Player> playersToAssign) {
        for (Player player : playersToAssign) {
            for (int i = 0; i < 3; i++) {
                player.drawCard(deck);
            }
        }
    }

    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Player checkWinner() {
        for (Player player : players) {
            if (player.hasWon())
                return player;
        }
        return null;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public DiscardDeck getDiscardPile() {
        return discardPile;
    }

    public void setDiscardPile(DiscardDeck discardPile) {
        this.discardPile = discardPile;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

}