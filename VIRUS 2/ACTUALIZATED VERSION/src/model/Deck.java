
package model;

import model.card.*;
import model.card.organ.OrganCard;
import model.card.organ.OrganState;
import model.card.treatment.TreatmentCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    // Constructor
    public Deck() {
        cards = new ArrayList<>(); // Initialize the deck
        initializeDeck(); // Create cards for the deck
        shuffle(); // Shuffle the cards

    }

    // Method to create all cards and add them to the deck
    private void initializeDeck() {
        // Add Organ cards
        for (int i = 0; i < 5; i++) {
            cards.add(OrganCard.createHeartCard());
            cards.add(OrganCard.createStomachCard());
            cards.add(OrganCard.createBrainCard());
            cards.add(OrganCard.createBoneCard());
        }
        cards.add(OrganCard.createWildCard());
        // Add Virus cards
        for (int i = 0; i < 4; i++) {
            cards.add(VirusCard.createHeartVirusCard());
            cards.add(VirusCard.createStomachVirusCard());
            cards.add(VirusCard.createBrainVirusCard());
            cards.add(VirusCard.createBoneVirusCard());
        }
        cards.add(VirusCard.createWildVirusCard());

        // Add Medicine cards
        for (int i = 0; i < 4; i++) {
            cards.add(MedicineCard.createHeartMedicineCard());
            cards.add(MedicineCard.createStomachMedicineCard());
            cards.add(MedicineCard.createBrainMedicineCard());
            cards.add(MedicineCard.createBoneMedicineCard());

        }
        for (int i = 0; i < 4; i++) {
            cards.add(MedicineCard.createWildMedicineCard());
        }
        // Add Treatment cards
        for (int i = 0; i < 10; i++) {
            cards.add(TreatmentCard.createContagionCard());
            cards.add(TreatmentCard.createLatexGloveCard());
            cards.add(TreatmentCard.createMedicalErrorCard());
            cards.add(TreatmentCard.createTransplantCard());
            cards.add(TreatmentCard.createStealOrganCard());
        }
    }

    // Shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);// Shuffle cards
    }

    // Directly remove a card without abstraction (e.g., drawing)
    public void removeCard(int index) {
        if (!cards.isEmpty() && index >= 0 && index < cards.size()) {
            System.out.println("Removing card: " + cards.get(index));
            cards.remove(index); // Remove the card at the given index
        } else {
            System.out.println("Invalid index or deck is empty.");
        }
    }

    public Card drawCard() { // TODO takeCard?
        if (cards.isEmpty()) {
            throw new IllegalStateException("No more cards in the deck!");
        }
        return cards.remove(0);
    }

    public int getRemainingCards() {
        return cards.size();
    }
}
