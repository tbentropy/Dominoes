/*
 * Dominoes implementation by Timothy Baldock, Abbie James and Nick Mackin
 * MSc Computer Science
 * Submitted 31st March 2013
 */

package dominoes;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: timothy
 * Date: 16/03/2013
 * Time: 19:27
 * Description: Widget which represents a bone, draws itself to represent player or table bones
 */
public class BoneWidget extends Canvas {
    private Bone bone;
    private PlayerType playerType;
    private int boneSize = 120;
    private boolean portraitOrientation = false;
    private boolean hidden = false;

    private Color background = Color.black;

    long entered = 0;
    long inComponent = 0;
    private Color backgroundColorSelected = Color.gray;
    private Color backgroundColorDeselected = Color.black;

    public BoneWidget(Bone bone, PlayerType playerType, int boneSize) {
        this.bone = bone;
        this.playerType = playerType;
        this.boneSize = boneSize;

        // Orientation depends on playerType
        if (this.playerType == PlayerType.Human || this.playerType == PlayerType.Computer) {
            portraitOrientation = true;
            this.setSize(boneSize / 2, boneSize);
            if (this.playerType == PlayerType.Computer) {
                hidden = true;
            }
        } else {
            this.setSize(boneSize, boneSize / 2);
        }
    }

    public Bone getBone() {
        return bone;
    }

    public boolean mouseUp(Event e, int x, int y) {
        // Container should see event
        return false;
    }

    public void eventSelected() {
        background = this.backgroundColorSelected;
        repaint();
    }

    public void eventDeselected() {
        background = this.backgroundColorDeselected;
        repaint();
    }

    public void paint(Graphics g) {
        drawBackground(g);
        drawForeground(g);
    }

    private void drawForeground(Graphics g) {
        if (hidden) {
            drawForegroundHidden(g);
        } else {
            drawPips(g);
        }
    }

    private void drawPips(Graphics g) {
        switch (bone.left()) {
            case 1:
                drawOnePip(g, false);
                break;
            case 2:
                drawTwoPips(g, false);
                break;
            case 3:
                drawThreePips(g, false);
                break;
            case 4:
                drawFourPips(g, false);
                break;
            case 5:
                drawFivePips(g, false);
                break;
            case 6:
                drawSixPips(g, false);
                break;
        }
        switch (bone.right()) {
            case 1:
                drawOnePip(g, true);
                break;
            case 2:
                drawTwoPips(g, true);
                break;
            case 3:
                drawThreePips(g, true);
                break;
            case 4:
                drawFourPips(g, true);
                break;
            case 5:
                drawFivePips(g, true);
                break;
            case 6:
                drawSixPips(g, true);
                break;
        }
    }

    private void drawForegroundHidden(Graphics g) {
        g.setColor(Color.white);
        int xpoints[] = {boneSize / 4, boneSize * 3 / 8, boneSize / 4, boneSize / 8};
        int ypoints[] = {boneSize * 3 / 8, boneSize / 2, boneSize * 5 / 8, boneSize / 2};
        g.fillPolygon(xpoints, ypoints, 4);
    }

    private void drawBackground(Graphics g) {
        g.setColor(background);
        if (portraitOrientation) {
            g.fillRoundRect(0, 0, boneSize / 2, boneSize, boneSize / 20, boneSize / 20);
        } else {
            g.fillRoundRect(0, 0, boneSize, boneSize / 2, boneSize / 20, boneSize / 20);
        }
        g.setColor(Color.white);
        if (portraitOrientation) {
            g.drawLine(0, boneSize / 2, boneSize / 2, boneSize / 2);
        } else {
            g.drawLine(boneSize / 2, 0, boneSize / 2, boneSize / 2);
        }
    }

    private Dimension getOffset(boolean offset) {
        if (offset) {
            if (portraitOrientation) {
                return new Dimension(0, boneSize / 2);
            } else {
                return new Dimension(boneSize / 2, 0);
            }
        } else {
            return new Dimension(0, 0);
        }
    }

    private void drawOnePip(Graphics graphics, boolean offset) {
        graphics.setColor(Color.green);
        Dimension off = getOffset(offset);
        graphics.fillOval(off.width + 25, off.height + 25, boneSize / 12, boneSize / 12);
    }

    private void drawTwoPips(Graphics graphics, boolean offset) {
        graphics.setColor(Color.blue);
        Dimension off = getOffset(offset);
        if (portraitOrientation) {
            graphics.fillOval(off.width + 40, off.height + 10, boneSize / 12, boneSize / 12);
            graphics.fillOval(off.width + 10, off.height + 40, boneSize / 12, boneSize / 12);
        } else {
            graphics.fillOval(off.width + 10, off.height + 10, boneSize / 12, boneSize / 12);
            graphics.fillOval(off.width + 40, off.height + 40, boneSize / 12, boneSize / 12);
        }
    }

    private void drawThreePips(Graphics graphics, boolean offset) {
        graphics.setColor(Color.pink);
        Dimension off = getOffset(offset);
        graphics.fillOval(off.width + 25, off.height + 25, boneSize / 12, boneSize / 12);
        if (portraitOrientation) {
            graphics.fillOval(off.width + 40, off.height + 10, boneSize / 12, boneSize / 12);
            graphics.fillOval(off.width + 10, off.height + 40, boneSize / 12, boneSize / 12);
        } else {
            graphics.fillOval(off.width + 10, off.height + 10, boneSize / 12, boneSize / 12);
            graphics.fillOval(off.width + 40, off.height + 40, boneSize / 12, boneSize / 12);
        }
    }

    private void drawFourPips(Graphics graphics, boolean offset) {
        graphics.setColor(Color.red);
        Dimension off = getOffset(offset);
        graphics.fillOval(off.width + 10, off.height + 10, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 40, off.height + 10, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 10, off.height + 40, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 40, off.height + 40, boneSize / 12, boneSize / 12);
    }

    private void drawFivePips(Graphics graphics, boolean offset) {
        graphics.setColor(Color.cyan);
        Dimension off = getOffset(offset);
        graphics.fillOval(off.width + 25, off.height + 25, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 10, off.height + 10, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 40, off.height + 10, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 10, off.height + 40, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 40, off.height + 40, boneSize / 12, boneSize / 12);
    }

    private void drawSixPips(Graphics graphics, boolean offset) {
        graphics.setColor(Color.yellow);
        Dimension off = getOffset(offset);
        graphics.fillOval(off.width + 10, off.height + 10, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 40, off.height + 10, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 10, off.height + 40, boneSize / 12, boneSize / 12);
        graphics.fillOval(off.width + 40, off.height + 40, boneSize / 12, boneSize / 12);
        if (portraitOrientation) {
            graphics.fillOval(off.width + 10, off.height + 25, boneSize / 12, boneSize / 12);
            graphics.fillOval(off.width + 40, off.height + 25, boneSize / 12, boneSize / 12);
        } else {
            graphics.fillOval(off.width + 25, off.height + 10, boneSize / 12, boneSize / 12);
            graphics.fillOval(off.width + 25, off.height + 40, boneSize / 12, boneSize / 12);
        }
    }
}
