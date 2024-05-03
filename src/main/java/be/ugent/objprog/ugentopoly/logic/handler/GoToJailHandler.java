package be.ugent.objprog.ugentopoly.logic.handler;

import be.ugent.objprog.ugentopoly.logic.TurnManager;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.TileType;

public class GoToJailHandler {
    private final TurnManager turnManager;

    public GoToJailHandler(TurnManager turnManager) {
        this.turnManager = turnManager;
    }

    public boolean handle(Player player, int diceResult){
        if(player.getGameManager().getBoardManager().getBoard().getTiles().stream()
                .anyMatch(tile -> tile.getPosition() == player.getPosition().getPos()+diceResult && tile.getType() == TileType.GO_TO_JAIL)){
            player.setInJail(true);
            System.out.println("Player "+player.getName()+" went to jail");
            return true;
        }
        return false;
    }
}
