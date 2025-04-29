package model.card.treatment;

import java.util.List;
import model.Player;

public class LatexGloveCard extends TreatmentCard {

    public LatexGloveCard() {
        super("Latex Glove", TreatmentType.LatexGloval);
    }

    /**
     * Applies the Latex Glove effect. Makes all targetPlayers except the user
     * discard their hands.
     *
     * @param user          The player using the card.
     * @param targetPlayers The list of all targetPlayers in the game.
     */

    @Override
    public void applyEffect(Player user, List<Player> targetPlayers) {
        System.out.println(user.getName() + " played Latex Glove!");

        for (Player player : targetPlayers) {
            // Skip the user of the card
            if (!player.equals(user)) {
                // Step 1: Discard all cards in the player's hand
                player.discardAllCards();
                System.out.println(player.getName() + " discarded all cards!");

                // Step 2: Force the player to draw cards (simulate passing the turn)
                player.setNeedsToSkipTurn(true); // Assuming Player has a flag to track skipped turns
            }
        }

        System.out.println("Latex Glove effect applied: All other targetPlayers discarded their hands.");
    }
}