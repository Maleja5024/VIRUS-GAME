package view;

import model.*;
import service.*;
import model.card.MedicineCard;
import model.card.VirusCard;
import model.card.organ.OrganCard;
import model.card.treatment.TreatmentCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class TerminalGameRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al juego VIRUS!\n¿Cuántos jugadores participarán? (2-6):");
        int numPlayers = 0;
        while (numPlayers < 2 || numPlayers > 6) {
            System.out.print("Ingresa un número válido: ");
            numPlayers = scanner.nextInt();
            scanner.nextLine();
        }

        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Nombre del jugador " + i + ": ");
            String name = scanner.nextLine();
            playerNames.add(name);
        }

        Game game = new Game(playerNames);
        game.startGame();
        GameController controller = new GameController(game); // Idu where the controller comes from
        controller.playGame();
    }
}

class GameController {
    private Game game;
    private Scanner scanner;

    public GameController(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    public void playGame() {
        while (game.checkWinner() == null) {
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println("\n--- Turno de " + currentPlayer.getName() + " ---");

            showOrgansInTable();
            showCards(currentPlayer);

            if (currentPlayer.getHandCards().isEmpty()) {
                System.out.println("No tienes cartas. Robando una...");
                currentPlayer.drawCard(game.getDeck());
                game.nextTurn();
                continue;
            }

            System.out.println("Elige una carta por índice (-1 para cambiar carta):");
            int choice = scanner.nextInt();

            if (choice == -1) {
                System.out.println("Elige una carta para descartar:");
                showCards(currentPlayer);
                int discardIndex = scanner.nextInt();
                Card selected = currentPlayer.getHandCards().get(discardIndex);
                currentPlayer.getHandCards().remove(selected);
                currentPlayer.drawCard(game.getDeck());
                game.nextTurn();
                continue;
            }

            if (choice < 0 || choice >= currentPlayer.getHandCards().size()) {
                System.out.println("Índice inválido. Turno perdido.");
                game.nextTurn();
                continue;
            }

            Card selected = currentPlayer.getHandCards().get(choice);

            if (selected instanceof OrganCard) {
                currentPlayer.addOrganCard((OrganCard) selected);
                currentPlayer.getHandCards().remove(selected);
                System.out.println("Órgano jugado en mesa.");
                currentPlayer.drawCard(game.getDeck());
            } else if (selected instanceof VirusCard || selected instanceof MedicineCard
                    || selected instanceof TreatmentCard) {
                List<OrganWithOwner> objetivos = ObtainAllOrgans();

                if (objetivos.isEmpty()) {
                    System.out.println("No hay órganos en juego para aplicar el efecto.");
                } else {
                    System.out.println("Elige el órgano al que deseas aplicar el efecto:");
                    for (int i = 0; i < objetivos.size(); i++) {
                        OrganWithOwner organ = objetivos.get(i);
                        System.out.println(i + ": " 
                            + organ.organ.getName() 
                            + " (" + organ.organ.getClassificationCard().getColorCard() 
                            + ", Estado: " + organ.organ.getOrganState() 
                            + "). Jugador: " + organ.owner.getName());
                    }
                    int targetIndex = scanner.nextInt();
                    if (targetIndex >= 0 && targetIndex < objetivos.size()) {
                        try {
                            selected.applyEffect(objetivos.get(targetIndex).organ);
                            currentPlayer.getHandCards().remove(selected);
                            System.out.println("Efecto aplicado con éxito.");
                            currentPlayer.drawCard(game.getDeck());
                        } catch (Exception e) {
                            System.out.println("No se pudo aplicar el efecto: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Índice inválido. No se aplica el efecto.");
                    }
                }
            } else {
                System.out.println("Esa carta no se puede jugar directamente todavía en esta demo.");
            }
            
            game.nextTurn();
        }

        System.out.println("\n¡Ganó: " + game.checkWinner().getName() + "!");
        scanner.close();
    }

    private void showCards(Player player) {
        List<Card> hand = player.getHandCards();
        System.out.println("Your cards in hand:");
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            String extra = "";
    
            if (card instanceof OrganCard) {
                OrganCard organ = (OrganCard) card;
                extra = " - Organ (" + organ.getClassificationCard().getColorCard() + ")";
            } else if (card instanceof VirusCard) {
                VirusCard virus = (VirusCard) card;
                extra = " - Virus (" + virus.getColorCard() + ")";
            } else if (card instanceof MedicineCard) {
                MedicineCard medicine = (MedicineCard) card;
                extra = " - Medicine (" + medicine.getColorCard() + ")";
            } else if (card instanceof TreatmentCard treatment) {
                extra = " - Treatment (" + treatment.getTreatmentType() + ")";
            }
    
            System.out.println(i + ": " + card.getName() + extra);
        }
    }

    private void showOrgansInTable() {
        List<Player> players = game.getPlayers();
        List<OrganCard> organCards = new ArrayList<>();
        for (Player p : players) {
            if (p.getOrganCards() != null) {
                organCards.addAll(p.getOrganCards());
            }
        }

        System.out.println("Órganos en mesa:");
        System.out.print(String.format("%-12s", "| Órgano"));

        for (Player p : players) {
            System.out.print(String.format("| %-8s", p.getName()));
        }

        System.out.println("|");
        System.out.println("-".repeat(12 + (players.size() * 10)));

        for (OrganCard organ : organCards) {
            System.out.print(String.format("| %-12s", organ.getName() + " (" + organ.getClassificationCard().getColorCard() + ")"));
            for (Player p : players) {
                if (p.getOrganCards().contains(organ)) {
                    System.out.print(String.format("| %-8s", organ.getOrganState()));
                } else {
                    System.out.print(String.format("| %-8s", " "));
                }
            }
            System.out.println("|");
        }
    }

    private List<OrganWithOwner> ObtainAllOrgans() {
        List<OrganWithOwner> todos = new ArrayList<>();
        for (Player p : game.getPlayers()) {
            for (OrganCard organ : p.getOrganCards()) {
                todos.add(new OrganWithOwner(p, organ));
            }
        }
        return todos;
    }
}

class OrganWithOwner {
    Player owner;
    OrganCard organ;

    OrganWithOwner(Player owner, OrganCard organ) {
        this.owner = owner;
        this.organ = organ;
    }
}

