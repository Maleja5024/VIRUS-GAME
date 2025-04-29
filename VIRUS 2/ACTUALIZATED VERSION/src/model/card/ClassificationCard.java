package model.card;

public class ClassificationCard {

    public ClassificationCard(ColorCard colorCard, TypeCard typeCard) {
        this.colorCard = colorCard;
        this.typeCard = typeCard;
    }

    ColorCard colorCard;
    TypeCard typeCard;

    public enum TypeCard {
        VIRUS,
        MEDICINE,
        ORGAN,
        TREATMENT
    }

    public ColorCard getColorCard() {
        return colorCard;
    }

    public void setColorCard(ColorCard colorCard) {
        this.colorCard = colorCard;
    }

    public TypeCard getTypeCard() {
        return typeCard;
    }

    public void setTypeCard(TypeCard typeCard) {
        this.typeCard = typeCard;
    }
}