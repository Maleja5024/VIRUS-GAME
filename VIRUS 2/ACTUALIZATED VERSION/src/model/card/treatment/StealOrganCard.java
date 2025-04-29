package model.card.treatment;

import model.Player;

import java.util.List;

public class StealOrganCard extends TreatmentCard {

    public StealOrganCard() {
        super("Steal Organ", TreatmentType.StealOrgan);
    }

    @Override
    public void applyEffect(Player user, List<Player> targetPlayers) {

    }
}