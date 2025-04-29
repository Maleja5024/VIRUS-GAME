package model.card;

import model.Card;
import model.card.organ.OrganCard;
import model.card.organ.OrganState;

public class MedicineCard extends Card {

    public MedicineCard(String name, ClassificationCard classificationCard) {
        super(name, classificationCard);
    }

    public static MedicineCard createBrainMedicineCard() {
        return new MedicineCard("Brain Medicine", new ClassificationCard(ColorCard.Blue, ClassificationCard.TypeCard.MEDICINE));
    }

    public static MedicineCard createHeartMedicineCard() {
        return new MedicineCard("Heart Medicine", new ClassificationCard(ColorCard.Red, ClassificationCard.TypeCard.MEDICINE));
    }

    public static MedicineCard createBoneMedicineCard() {
        return new MedicineCard("Bone Medicine", new ClassificationCard(ColorCard.Yellow, ClassificationCard.TypeCard.MEDICINE));
    }

    public static MedicineCard createStomachMedicineCard() {
        return new MedicineCard("Stomach Medicine", new ClassificationCard(ColorCard.Green, ClassificationCard.TypeCard.MEDICINE));
    }

    public static MedicineCard createWildMedicineCard() {
        return new MedicineCard("Wild Medicine", new ClassificationCard(ColorCard.Multicolor, ClassificationCard.TypeCard.MEDICINE));
    }

    @Override
    public ColorCard getColorCard() {
        return this.classificationCard.getColorCard();
    }

    @Override
    public void applyEffect(OrganCard organ) {
        if (this.classificationCard.getColorCard() == organ.getClassificationCard().getColorCard()
            || this.classificationCard.getColorCard() == ColorCard.Multicolor) {
            switch (organ.getOrganState()) {
                case Infected -> organ.setOrganState(OrganState.Healthy);
                case Healthy -> organ.setOrganState(OrganState.Protected);
                case Protected -> organ.setOrganState(OrganState.Inmunized);
                default -> throw new IllegalArgumentException("The medicine cannot be applied at this organ state.");
            }
        } else {
            throw new IllegalArgumentException("The Medicine is not affecting the organ.");
        }
    }
}
