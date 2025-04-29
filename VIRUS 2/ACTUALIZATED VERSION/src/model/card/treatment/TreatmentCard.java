package model.card.treatment;

import model.Card;
import model.Player;
import model.card.ClassificationCard;
import model.card.ColorCard;
import model.card.organ.OrganCard;

import java.util.List;

public abstract class TreatmentCard extends Card {
    final static ClassificationCard.TypeCard TYPE_CARD = ClassificationCard.TypeCard.TREATMENT;
    private TreatmentType treatmentType;

    public TreatmentCard(String name, TreatmentType contagion) {
        super("Treatment", null);
        this.treatmentType = treatmentType;
    }

    public static TreatmentCard createContagionCard() {
        return new ContagionCard();
    }

    public static TreatmentCard createLatexGloveCard() {
        return new LatexGloveCard();
    }

    public static TreatmentCard createMedicalErrorCard() {
        return new MedicalErrorCard();
    }

    public static TreatmentCard createTransplantCard() {
        return new TransplantCard();
    }

    public static TreatmentCard createStealOrganCard() {
        return new StealOrganCard();
    }

    // Removed duplicate constructor to resolve the error

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public abstract void applyEffect(Player user, List<Player> targetPlayers); // This is only for Treatment (could be
                                                                               // for VIRUS REVIEW IT)

    public void applyEffect(OrganCard organ) {
        // is NOT REQUIRED in Treatment review it in Card
    }
}