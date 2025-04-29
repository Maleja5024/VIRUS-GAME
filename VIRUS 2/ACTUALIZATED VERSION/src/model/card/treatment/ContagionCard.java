package model.card.treatment;

import model.*;
import model.card.organ.OrganCard;
import model.card.VirusCard;

import java.util.ArrayList;
import java.util.List;

public class ContagionCard extends TreatmentCard {

    public ContagionCard() {
        super("Contagion", TreatmentType.Contagion);
    }

    public void applyEffect(Player currentPlayer, List<Player> targetPlayers) {
        System.out.println("Applying Contagion: Transferring viruses to target player's free organs...");

        // Step 1: Get all infected organs and associated viruses from the currentPlayer
        List<OrganCard> infectedOrganCardsCurrentPlayer = currentPlayer.getInfectedOrgans();
        if (infectedOrganCardsCurrentPlayer.isEmpty()) {
            System.out.println("Contagion failed: User has no infected organs to transfer viruses from.");
            return;
        }

        // Step 2: Get all free (uninfected, not vaccinated) organs from the target
        // players
        List<OrganCard> freeOrgansForContagion = new ArrayList<>();
        for (Player targetPlayer : targetPlayers) {
            List<OrganCard> targetFreeOrgans = targetPlayer.getFreeOrgans();
            if (targetFreeOrgans.isEmpty()) {
                System.out.println("Contagion failed: Target has no free organs to infect.");
                continue;
            } else {
                freeOrgansForContagion.addAll(targetFreeOrgans);
            }
        }

        // Step 3: Transfer viruses from the currentPlayer's infected organs to the
        // target's free organs
        for (OrganCard infectedOrganCard : infectedOrganCardsCurrentPlayer) {
            VirusCard virus = infectedOrganCard.removeVirus(); // Remove a virus from the organ
            if (virus != null && isNotEmpty(freeOrgansForContagion)) {
                OrganCard targetOrgan = freeOrgansForContagion.removeFirst(); // Infect the first free organ on the
                                                                              // target
                targetOrgan.addVirus(virus);
                System.out.println("Transferred " + virus.getName() + " from " + infectedOrganCard.getName() +
                        " (currentPlayer) to " + targetOrgan.getName() + " (target).");
            }
        }
    }

    private static boolean isNotEmpty(List<OrganCard> freeOrgansForContagion) {
        return !freeOrgansForContagion.isEmpty();
    }
}