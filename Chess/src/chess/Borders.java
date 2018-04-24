package chess;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Borders {
    public static Border SELECTED = BorderFactory.createBevelBorder(0, Color.green, Color.GREEN);
    public static Border AVAILABLE = BorderFactory.createBevelBorder(0, Color.white, Color.white);
    public static Border CHECK = BorderFactory.createBevelBorder(0, Color.red, Color.RED);
    public static Border CLEAR = BorderFactory.createBevelBorder(0, new Color(255, 255, 255, 0), new Color(255, 255, 255, 0));
}
