package seminars.s2.lastlection.common;

import seminars.s2.lastlection.common.MainCanvas;

import java.awt.*;

public interface CanvasRepaintListener {
    void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime);
}