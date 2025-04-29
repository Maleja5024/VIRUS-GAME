package model.card.organ;

import model.Card;
import model.card.ClassificationCard;
import model.card.ColorCard;
import model.card.VirusCard;

public class OrganCard extends Card {

    private OrganState organState;
    private VirusCard virus;

    public OrganCard(String name, ClassificationCard classificationCard, OrganState organState) {
        super(name, classificationCard);
        this.organState = organState != null ? organState : OrganState.Healthy;
    }

    public OrganState getOrganState() {
        return organState;
    }

    public void setOrganState(OrganState organState) {
        this.organState = organState;
    }

    public VirusCard removeVirus() {
        VirusCard removed = this.virus;
        this.virus = null;
        this.organState = OrganState.Healthy;
        return removed;
    }

    public void addVirus(VirusCard virus) {
        if (this.organState == OrganState.Inmunized) {
            throw new IllegalArgumentException("Cannot add virus to an inmunized organ.");
        }
        this.virus = virus;
        if (this.organState == OrganState.Healthy) {
            this.organState = OrganState.Infected;
        } else if (this.organState == OrganState.Infected) {
            this.organState = OrganState.Destroyed;
        }
    }

    public VirusCard getVirus() {
        return this.virus;
    }

    // Métodos de fábrica
    public static OrganCard createHeartCard() {
        return new OrganCard("Heart", new ClassificationCard(ColorCard.Red, ClassificationCard.TypeCard.ORGAN), OrganState.Healthy);
    }

    public static OrganCard createStomachCard() {
        return new OrganCard("Stomach", new ClassificationCard(ColorCard.Green, ClassificationCard.TypeCard.ORGAN), OrganState.Healthy);
    }

    public static OrganCard createBrainCard() {
        return new OrganCard("Brain", new ClassificationCard(ColorCard.Blue, ClassificationCard.TypeCard.ORGAN), OrganState.Healthy);
    }

    public static OrganCard createBoneCard() {
        return new OrganCard("Bone", new ClassificationCard(ColorCard.Yellow, ClassificationCard.TypeCard.ORGAN), OrganState.Healthy);
    }

    public static OrganCard createWildCard() {
        return new OrganCard("Wild Organ", new ClassificationCard(ColorCard.Multicolor, ClassificationCard.TypeCard.ORGAN), OrganState.Healthy);
    }

    @Override
    public void applyEffect(OrganCard organ) {
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }
}
