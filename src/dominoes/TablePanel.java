package dominoes;

import dominoes.Widgets.BoneWidget;
import dominoes.Widgets.ElipsisWidget;
import dominoes.Widgets.PlayPositionWidget;
import dominoes.players.PlayerType;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: nick
 * Date: 05/03/13
 * Time: 01:08
 */
public class TablePanel extends JPanel {
    Table table;
    int boneSize = 120;
    int boneSpacing = 5;

    // Indicator for play positions and click targets
    PlayPositionWidget leftPlayPosition = new PlayPositionWidget(boneSize);
    PlayPositionWidget rightPlayPosition = new PlayPositionWidget(boneSize);
    private boolean showPlays = false;
    private TurnCoordinator turnCoordinator;

    public TablePanel(TurnCoordinator turnCoordinator) {
        this.turnCoordinator = turnCoordinator;
        this.setLayout(new FlowLayout(FlowLayout.CENTER, boneSpacing, 5));
        this.validate();
    }

    public void setTable(Table t) {
        table = t;
        setUpBones();
    }

    private void setUpBones() {
        // Number of displayed bones depends on size of containing panel
        // Truncate in the middle by displaying elipsis
        this.removeAll();
        if (table != null) {
            Bone[] bones = table.layout();
            // Max number of bones = width of display area divided by bone size + spacing
            int maxBones = this.getSize().width / (boneSize + boneSpacing) - 2;

            if (this.showPlays) {
                this.add(this.leftPlayPosition);
            }
            if (bones.length > maxBones) {
                // Draw elipsis in middle
                for (int i = 0; i < maxBones / 2; i++) {
                    this.add(new BoneWidget(bones[i], PlayerType.None, boneSize));
                }
                this.add(new ElipsisWidget(boneSize));
                for (int i = bones.length - maxBones / 2; i < bones.length; i++) {
                    this.add(new BoneWidget(bones[i], PlayerType.None, boneSize));
                }
            } else {
                // Just draw them all
                for (int i = 0; i < bones.length; i++) {
                    this.add(new BoneWidget(bones[i], PlayerType.None, boneSize));
                }
            }
            if (this.showPlays) {
                this.add(this.rightPlayPosition);
            }
        }
        this.validate();
    }

    public boolean mouseUp(Event e, int x, int y) {
        // If bone selected matches either the left or right position send event to UI
        // Container should not see event
        if (e.target == this.leftPlayPosition) {
            // Send left play event
            turnCoordinator.nextMovePosition(Play.LEFT);
            System.out.println("Left play position clicked");
        } else if (e.target == this.rightPlayPosition) {
            // Send right play event
            turnCoordinator.nextMovePosition(Play.RIGHT);
            System.out.println("Right play position clicked");
        }
        return true;
    }

    // Used by observer to indicate whether play indicators should be shown or hidden at the current time
    public void showPlayIndicators() {
        this.showPlays = true;
        System.out.println("showPlayIndicators: " + this.showPlays);
        setUpBones();
    }

    public void hidePlayIndicators() {
        this.showPlays = false;
        System.out.println("hidePlayIndicators: " + this.showPlays);
        setUpBones();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
    }
}
