package dominoes;

import dominoes.players.Player;

/**
 * Created with IntelliJ IDEA.
 * User: timothy
 * Date: 18/03/2013
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public interface TurnCoordinator {
    // Interface for turns
    // Called by PlayerHandPanel when player selects a bone to play
    void nextMoveBoneSelected(Bone bone);

    // Called by TablePanel when player selects play position
    void nextMovePosition(int position);

    // Called by Player when it requires a move from the UI
    void getPlayerMove(Player player, CubbyHole nextMove);

    // Called to indicate the start of the AI's turn to the UI
    void aiMoveBegins(Player player);

    // Called to indicate the end of the AI's turn to the UI
    void aiMoveEnds();
}
