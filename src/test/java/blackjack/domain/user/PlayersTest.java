package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @DisplayName("이름별로 참여자들을 생성한다.")
    @Test
    void createPlayers() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = new Players(names);

        assertThat(players).isInstanceOf(Players.class);
    }

    @DisplayName("Players 일급 컬렉션을 반환한다.")
    @Test
    void players() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = new Players(names);

        assertThat(players.players()).hasSize(3);
    }

    @DisplayName("각 플레이어에게 초기에 카드 두장을 배분한다.")
    @Test
    void DistributeToEachPlayer() {
        Deck deck = new Deck();
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = new Players(names);
        players.distributeToEachPlayer(deck);

        assertThat(players.players()
                .stream()
                .allMatch(user -> user.cards.cards().size() == 2)).isTrue();
    }

    @DisplayName("각 플레이어의 모든 카드를 보여준다.")
    @Test
    void showCardsByPlayers() {
        Deck deck = new Deck();
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = new Players(names);
        players.distributeToEachPlayer(deck);
        List<Cards> cardsGroup = players.showCardsByPlayers();

        assertThat(cardsGroup.stream()
                .allMatch(cards -> cards.cards().size() == 2)).isTrue();
    }

    @DisplayName("플레이어 이름들을 확인한다.")
    @Test
    void showNames() {
        List<String> names = Arrays.asList("amazzi", "dani", "pobi");
        Players players = new Players(names);
        List<String> namesGroup = players.showNames();

        assertThat(namesGroup).isEqualTo(Arrays.asList("amazzi", "dani", "pobi"));
    }
}
