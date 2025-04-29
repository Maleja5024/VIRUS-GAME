package model;

import java.util.ArrayList;
import java.util.List;

public class DiscardDeck {
    private List<Card> discardedCards;

    public DiscardDeck() {
        this.discardedCards = new ArrayList<>();
    }

    public void add(Card card) {
        discardedCards.add(card);
    }

    public List<Card> getAllCards() {
        return new ArrayList<>(discardedCards); // Retorna una copia para evitar modificaciones externas
    }

    public void clear() {
        discardedCards.clear();
    }

    public boolean isEmpty() {
        return discardedCards.isEmpty();
    }

    public int size() {
        return discardedCards.size();
    }

    // Útil para mezclar descartadas de nuevo al deck
    public List<Card> removeAllCards() {
        List<Card> cardsToReturn = new ArrayList<>(discardedCards);
        discardedCards.clear();
        return cardsToReturn;
    }

    public void addCard(Card card) {
        discardedCards.add(card);
    }
}