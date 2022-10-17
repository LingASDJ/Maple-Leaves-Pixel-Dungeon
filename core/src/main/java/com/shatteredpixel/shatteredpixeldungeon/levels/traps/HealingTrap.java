package com.shatteredpixel.shatteredpixeldungeon.levels.traps;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.hero;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

public class HealingTrap extends Trap {

    {
        color = VIOLET;
        shape = DOTS;
    }

    @Override
    public void activate() {
        if(hero.HT==hero.HP) {
           GLog.p("血量已满");
        } else {
            Buff.affect(hero, Healing.class).setHeal(4, 0, 0);
        }
        Sample.INSTANCE.play(Assets.Sounds.READ);
    }
}

