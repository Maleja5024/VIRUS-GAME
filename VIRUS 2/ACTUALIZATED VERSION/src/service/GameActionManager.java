package service;

import model.Card;
import model.Game;
import model.Player;
import model.card.treatment.*;

public class GameActionManager {
    private Game game;

    public void executePlayerAction(Action action, Player player) {
        switch (action.getActionType()) {
            case PLAY_CARD -> {
                if (action.getTargets() != null || !action.getTargets().isEmpty()) {
                    for (Card card : action.getCards()) {
                        switch (card.getClassificationCard().getTypeCard()) {
                            case VIRUS -> {
                                // verify target organ status
                                // action.getTarget().getOrganCards();
                            }
                            case MEDICINE -> {
                            }
                            case ORGAN -> {
                            }
                            case TREATMENT -> {
                                TreatmentCard treatmentCard = (TreatmentCard) card;
                                switch (treatmentCard) {
                                    /*
                                     * case TransplantCard transplantCard ->
                                     * transplantCard.applyEffect(user, target, players);
                                     */
                                    case ContagionCard contagionCard ->
                                        contagionCard.applyEffect(player, action.getTargets());

                                    /*
                                     * case StealOrganCard organThiefCard ->
                                     * organThiefCard.applyEffect(user, target, players);
                                     * 
                                     */
                                    case LatexGloveCard latexGloveCard ->
                                        latexGloveCard.applyEffect(player, action.getTargets());
                                    /*
                                     * case MedicalErrorCard medicalErrorCard ->
                                     * medicalErrorCard.applyEffect(user, target, players);
                                     * 
                                     */

                                    default ->
                                        System.out.println(
                                                "Unknown card type: No specific effect defined for " + card.getName());
                                }
                            }
                        }
                    }
                }
            }
            case DISCARD -> {
                game.getDiscardPile().addCard(action.getCards().getFirst());
            }
        }

    }
}