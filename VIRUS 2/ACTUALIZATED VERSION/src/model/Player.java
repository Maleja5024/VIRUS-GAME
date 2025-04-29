package model;

import model.card.organ.OrganCard;
import model.card.organ.OrganState;
import service.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    protected String name;
    protected List<Card> handCards;
    protected List<OrganCard> organCards;
    protected boolean needsToSkipTurn;

    public Player(String name) {
        this.name = name;
        this.handCards = new ArrayList<>();
        this.organCards = new ArrayList<>();
        this.needsToSkipTurn = false; // ask if have sense
    }

    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        handCards.add(card);
    }

    public void play(Action action) {
        if (handCards.containsAll(action.getCards())) {
            handCards.remove(action.getCards());
            // new GameController().executePlayerAction(action);
        } else {
            throw new IllegalArgumentException("Card not in hand");
        }
    }

    public boolean hasWon() {
        long count = organCards.stream()
                .filter(org -> org.getOrganState() == OrganState.Healthy
                        || org.getOrganState() == OrganState.Protected || org.getOrganState() == OrganState.Inmunized)
                .count();
        return count == 4;
    }

    public void addOrganCard(OrganCard organ) {
        organCards.add(organ);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public List<OrganCard> getOrganCards() {
        return organCards;
    }

    public void setOrganCards(List<OrganCard> organCards) {
        this.organCards = organCards;
    }

    public List<OrganCard> getInfectedOrgans() {
        // organCards.stream().filter(c -> c.getOrganState() ==
        // OrganState.Infected).toList();
        List<OrganCard> infectedOrgans = new ArrayList<>();

        for (OrganCard organCard : organCards) {
            if (organCard.getOrganState() == OrganState.Infected) {
                infectedOrgans.add(organCard);
            }
        }
        return infectedOrgans;
    }

    public List<OrganCard> getFreeOrgans() {
        // organCards.stream().filter(c -> c.getOrganState() ==
        // OrganState.Healthy).toList();
        List<OrganCard> freeOrgans = new ArrayList<>();

        for (OrganCard organCard : organCards) {
            if (organCard.getOrganState() == OrganState.Healthy) {
                freeOrgans.add(organCard);
            }
        }
        return freeOrgans;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(handCards, player.handCards)
                && Objects.equals(organCards, player.organCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, handCards, organCards);
    }

    public void discardAllCards() {
        handCards.clear(); // Simply clear the list (cards should go to the game's discard pile)
    }

    public void setNeedsToSkipTurn(boolean needsToSkipTurn) {
        this.needsToSkipTurn = needsToSkipTurn;
    }

    public boolean isNeedsToSkipTurn() {
        return needsToSkipTurn;
    }
}