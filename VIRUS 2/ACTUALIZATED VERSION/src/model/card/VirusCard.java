package model.card;

import model.Card;
import model.card.organ.OrganCard;
import model.card.organ.OrganState;

public class VirusCard extends Card {

    public VirusCard(String name, ClassificationCard classificationCard) {
        super(name, classificationCard);
    }

    public static VirusCard createBrainVirusCard() {
        return new VirusCard("Brain Virus", new ClassificationCard(ColorCard.Blue, ClassificationCard.TypeCard.VIRUS));
    }

    public static VirusCard createBoneVirusCard() {
        return new VirusCard("Bone Virus", new ClassificationCard(ColorCard.Yellow, ClassificationCard.TypeCard.VIRUS));
    }

    public static VirusCard createHeartVirusCard() {
        return new VirusCard("Heart Virus", new ClassificationCard(ColorCard.Red, ClassificationCard.TypeCard.VIRUS));
    }

    public static VirusCard createStomachVirusCard() {
        return new VirusCard("Stomach Virus", new ClassificationCard(ColorCard.Green, ClassificationCard.TypeCard.VIRUS));
    }

    public static VirusCard createWildVirusCard() {
        return new VirusCard("Virus Wild", new ClassificationCard(ColorCard.Multicolor, ClassificationCard.TypeCard.VIRUS));
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
                case Protected -> organ.setOrganState(OrganState.Healthy);
                case Healthy -> organ.setOrganState(OrganState.Infected);
                case Infected -> organ.setOrganState(OrganState.Destroyed);
                case Inmunized -> throw new IllegalArgumentException("The organ is INMUNIZED. Virus doesn't work.");
            }
        } else {
            throw new IllegalArgumentException("The Virus is not affecting the organ.");
        }
    }
}
