package model.card.treatment;

import model.Player;

import java.util.List;

public class TransplantCard extends TreatmentCard {

    public TransplantCard() {
        super("Transplant", TreatmentType.Transplant);
    }

    @Override
    public void applyEffect(Player user, List<Player> targetPlayers) {

    }
}