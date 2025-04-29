package service;

import model.Card;
import model.Player;

import java.util.List;
import java.util.Objects;

public class Action {

    public enum ActionType {
        PLAY_CARD,
        DISCARD
    }

    ActionType actionType;
    List<Card> cards;
    List<Player> targets;

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Player> getTargets() {
        return targets;
    }

    public void setTargets(List<Player> targets) {
        this.targets = targets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Action action = (Action) o;
        return actionType == action.actionType && Objects.equals(cards, action.cards)
                && Objects.equals(targets, action.targets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionType, cards, targets);
    }
}