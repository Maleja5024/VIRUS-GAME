package model;

import java.util.Objects;
import model.card.ClassificationCard;
import model.card.ColorCard;
import model.card.organ.OrganCard;

public abstract class Card {

    protected String name;
    protected ClassificationCard classificationCard;

    public Card(String name, ClassificationCard classificationCard) {
        this.name = name;
        this.classificationCard = classificationCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassificationCard getClassificationCard() {
        return classificationCard;
    }

    public void setClassificationCard(ClassificationCard classificationCard) {
        this.classificationCard = classificationCard;
    }

    // Nuevo: obtener el color de la carta si aplica
    public ColorCard getColorCard() {
        return null; // Por defecto, no tiene color
    }

    public abstract void applyEffect(OrganCard organ);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return Objects.equals(name, card.name) && Objects.equals(classificationCard, card.classificationCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, classificationCard);
    }
}
