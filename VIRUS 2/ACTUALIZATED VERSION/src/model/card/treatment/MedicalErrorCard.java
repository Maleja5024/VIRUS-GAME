package model.card.treatment;

import model.Player;

import java.util.List;

public class MedicalErrorCard extends TreatmentCard {

    public MedicalErrorCard() {
        super("Medical Error", TreatmentType.MedicalError);
    }

    @Override
    public void applyEffect(Player user, List<Player> targetPlayers) {

    }
}