package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.ui.Component;

public class Timer extends Component {

    private BitmapText timerText;
    private BitmapText goalText;
    public BitmapText pbText;

    private final int GREEN = 0x8AFF8A;
    private final int RED = 0xFF8A8A;

    @Override
    protected void createChildren() {
        super.createChildren();
        timerText = new BitmapText(PixelScene.pixelFont);
        add(timerText);
        if (SPDSettings.numberOfRuns(Dungeon.category) > 0) {
            goalText = new BitmapText(PixelScene.pixelFont);
            add(goalText);
            pbText = new BitmapText(PixelScene.pixelFont);
            pbText.text(timeToString(SPDSettings.personalBest(Dungeon.category)));
            add(pbText);
        }
    }

    @Override
    protected void layout() {
        super.layout();
        timerText.x = x;
        timerText.y = y;
        if (SPDSettings.numberOfRuns(Dungeon.category) > 0) {
            goalText.x = x;
            goalText.y = y + 10;
            pbText.x = x;
            pbText.y = y + 20;
        }
    }

    @Override
    public void update() {
        super.update();
        if (Dungeon.finishTime != -1) return;
        timerText.text( timeToString(Dungeon.currentTime) );
        timerText.hardlight(GREEN);
    }

    public static String timeToString(long time) {
        int minutes = (int)(time / 60000);
        time -= minutes * 60000L;
        int seconds = (int)(time / 1000);
        time -= seconds * 1000L;
        int hundredths = (int)(time / 10);
        return String.format("%s:%s.%s",
                addLeadingZeroes(minutes, 2),
                addLeadingZeroes(seconds, 2),
                addLeadingZeroes(hundredths, 2)
        );
    }

    private static String addLeadingZeroes(long number, int length) {
        String numberString = Long.toString(number);
        int numberLength = numberString.length();
        if (numberLength >= length) return numberString;
        int leadingZeroes = length - numberLength;
        return new String(new char[leadingZeroes]).replace("\0", "0") + numberString;
    }
}
