package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards(List<Card> cards) {
        this.cards = cards;
    }

    public void append(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int score = cards.stream()
                .mapToInt(Card::getScore)
                .sum();

        int aceCount = getAceCount();

        // TODO: 인덴트 줄이기, 로직 개선
        for (int i = 0; i < aceCount; i++) {
            if (score <= 21) {
                break;
            }
            score -= 10;
        }

        return score;
    }

    private int getAceCount() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    public List<Card> getCards() {
        return cards;
    }
}
