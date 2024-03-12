package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Outcome;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResultBoard {
    private final Map<PlayerName, GameResult> resultBoard = new HashMap<>();

    public GameResultBoard(Dealer dealer, List<Player> players) {
        Outcome dealerOutcome = dealer.calculateOutcome();
        for (Player player : players) {
            PlayerName playerName = player.getPlayerName();
            Outcome playerOutcome = player.calculateOutcome();
            GameResult gameResult = playerOutcome.compete(dealerOutcome);
            if (playerOutcome.isBusted()) {
                gameResult = GameResult.LOSE;
            }
            resultBoard.put(playerName, gameResult);
        }
    }

    public GameResult getGameResult(Player player) {
        return resultBoard.get(player.getPlayerName());
    }

    public Map<GameResult, Integer> getDealerResult() {
        return Map.of(
                GameResult.WIN, getDealerWinCount(),
                GameResult.DRAW, getDealerDrawCount(),
                GameResult.LOSE, getDealerLoseCount()
        );
    }

    private int getDealerWinCount() {
        return (int) resultBoard.values().stream()
                .filter(playerResult -> playerResult.equals(GameResult.LOSE))
                .count();
    }

    private int getDealerLoseCount() {
        return (int) resultBoard.values().stream()
                .filter(playerResult -> playerResult.equals(GameResult.WIN))
                .count();
    }

    private int getDealerDrawCount() {
        return (int) resultBoard.values().stream()
                .filter(playerResult -> playerResult.equals(GameResult.DRAW))
                .count();
    }
}
