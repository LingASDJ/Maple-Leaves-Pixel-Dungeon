package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Healing;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.Potion;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHealing;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;

public class SmallPotion extends Potion {

    {
        image = ItemSpriteSheet.SMALL;
        bones = true;
    }

    @Override
    public void apply( Hero hero ) {
        Buff.affect(hero, Healing.class).setHeal( (2), 0, 0);
        GLog.p( Messages.get(PotionOfHealing.class, "heal") );
    }

    @Override
    public boolean isIdentified() {
        return true;
    }
}

