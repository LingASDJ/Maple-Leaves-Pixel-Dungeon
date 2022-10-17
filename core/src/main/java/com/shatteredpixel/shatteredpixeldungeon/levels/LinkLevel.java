package com.shatteredpixel.shatteredpixeldungeon.levels;

import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.EMPTY;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.PEDESTAL;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.TRAP;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.WALL;
import static com.shatteredpixel.shatteredpixeldungeon.levels.Terrain.WATER;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Gnoll;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.BigPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.SmallPotion;
import com.shatteredpixel.shatteredpixeldungeon.items.keys.GoldenKey;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfFrost;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfHaste;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLevitation;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfLiquidFlame;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfMindVision;
import com.shatteredpixel.shatteredpixeldungeon.items.potions.PotionOfPurity;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfAntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.stones.StoneOfBlink;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrimTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.GrippingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.HealingTrap;
import com.shatteredpixel.shatteredpixeldungeon.levels.traps.Trap;

public class LinkLevel extends Level {

    private static final int W = WALL;
    private static final int S = Terrain.CHASM;
    private static final int R = WATER;
    private static final int G = Terrain.HIGH_GRASS;
    private static final int K = Terrain.BARRICADE;
    private static final int D = Terrain.DOOR;
    private static final int L = Terrain.LOCKED_DOOR;
    private static final int J = EMPTY;
    private static final int O = PEDESTAL;
    private static final int A = Terrain.GRASS;

    //portals. (from, to).

    private static final int WIDTH = 64;
    private static final int HEIGHT = 64;

    private static final int[] code_map = {
            W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W
            ,W,W,W,W,W,W,W,W,W,W,W,
            W,S,S,O,O,K,J,K,J,A,J,O,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,W,W,W,J,J,W,S,S,S,S,J,J,J,O,J,J,J,J
            ,W,J,J,J,W,J,J,O,J,J,W,
            W,S,S,O,O,S,K,S,A,S,J,S,J,S,J,S,J,S,J,S,J,S,J,S,J,S,J,S,J,S,J,S,J,S,J,W,J,J,J,J,J,S,S,S,S,J,J,J,O,O,O,J,J
            ,W,J,J,J,W,O,W,S,A,S,W,
            W,S,S,O,O,S,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,S,J,W,J,W,W,W,W,S,S,S,S,J,J,J,J,W,O,J,J
            ,W,J,J,J,W,A,W,S,S,S,W,
            W,S,S,O,O,S,W,O,O,O,A,A,A,A,A,A,A,A,A,A,A,J,A,A,A,J,A,A,A,A,A,A,W,S,J,W,J,J,J,J,W,J,J,J,J,J,J,W,W,W,O,O,O
            ,W,J,J,J,W,J,W,J,J,J,W,
            W,S,S,O,O,S,W,O,W,W,W,W,S,W,S,W,S,W,S,W,A,J,A,J,A,J,A,W,J,J,J,A,W,S,J,W,W,W,W,W,W,W,S,W,W,W,J,J,J,J,J,S,J
            ,W,S,J,J,W,J,W,W,K,W,W,
            W,S,S,O,O,S,W,O,O,W,J,W,J,W,J,W,J,W,J,W,A,A,A,J,A,J,A,W,A,A,A,A,W,S,S,S,S,W,J,J,J,J,S,W,J,W,S,J,J,S,S,S,A
            ,D,J,J,J,W,A,W,J,S,J,W,
            W,J,S,O,O,S,W,W,O,W,J,W,J,J,J,J,J,J,J,J,J,J,J,J,A,A,A,W,A,W,W,W,W,S,S,S,S,W,J,W,W,W,W,W,J,W,J,J,J,J,J,A,A
            ,W,W,W,W,W,J,W,J,J,J,W,
            W,J,A,O,O,S,W,O,O,W,J,W,S,J,J,J,J,J,J,J,R,R,W,W,J,J,J,W,A,A,O,A,W,S,S,S,S,W,J,J,J,J,J,O,J,W,J,J,J,J,J,A,A
            ,J,J,J,J,J,J,W,J,S,J,W,
            W,J,S,O,O,S,W,J,J,W,J,S,S,S,A,S,A,A,A,A,A,A,J,A,A,A,A,A,A,R,W,A,W,S,S,S,S,W,W,W,W,W,J,W,W,W,J,J,A,A,J,J,J
            ,R,R,R,J,J,J,W,W,D,W,W,
            W,J,S,S,S,S,W,W,W,W,J,W,W,A,A,S,J,W,W,W,W,R,R,J,J,J,J,J,J,A,W,A,W,S,S,S,S,S,S,S,S,W,J,J,J,D,J,J,J,J,W,W,W
            ,W,W,W,W,W,W,W,S,J,S,W,
            W,J,S,S,J,S,S,S,S,W,J,J,J,J,A,A,A,A,A,A,W,J,J,J,J,J,R,J,W,A,W,A,W,S,S,S,S,S,S,S,S,W,W,W,W,W,A,W,J,O,D,J,J
            ,J,O,J,J,J,J,W,S,W,W,W,
            W,J,S,S,J,J,S,S,S,W,J,W,A,J,J,J,J,J,J,A,J,J,J,J,J,A,A,J,A,A,W,A,D,J,J,J,S,S,S,S,S,S,S,J,J,J,J,W,W,W,W,W,W
            ,W,W,W,W,W,W,W,J,J,J,W,
            W,J,S,S,J,J,J,S,S,W,J,J,J,J,J,J,J,A,J,W,O,W,W,W,W,K,W,W,W,W,W,W,W,W,W,W,S,S,S,S,S,W,K,W,W,W,W,W,W,W,S,S,S
            ,S,S,S,S,S,S,S,J,J,J,W,
            W,O,O,O,J,J,J,J,S,W,J,J,W,J,J,J,W,K,W,W,J,W,J,J,J,J,W,J,W,J,W,W,J,W,J,W,S,S,S,S,S,W,J,W,J,W,J,W,J,W,S,S,S
            ,S,S,S,S,S,S,S,S,O,S,W,
            W,O,O,O,J,J,J,J,J,W,W,W,W,J,J,O,W,J,J,W,J,W,J,J,J,J,W,K,W,K,W,W,K,W,K,W,S,S,S,S,S,W,K,W,J,W,O,W,O,W,W,W,W
            ,S,S,S,S,S,S,S,W,O,W,W,
            W,O,O,O,J,J,J,J,J,J,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,A,A,A,J,J,J,J,J,W,S,S,S,S,S,S,J,W,O,W,O,W,O,W,J,J,W
            ,S,S,S,S,S,S,S,W,O,W,W,
            W,O,O,O,O,O,O,O,O,O,O,W,W,S,S,S,S,S,S,S,S,S,S,S,S,S,W,J,J,J,J,J,J,J,J,W,S,S,S,S,S,S,J,W,O,W,J,J,O,W,W,K,W
            ,W,W,W,W,W,W,W,W,O,W,W,
            W,O,O,O,J,J,J,J,J,J,J,J,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,J,J,J,J,J,J,J,J,W,W,W,W,W,W,S,K,W,A,A,J,J,O,O,K,J,J
            ,A,O,O,O,A,O,O,O,O,O,W,
            W,O,J,J,J,J,J,J,J,J,J,J,K,O,J,O,J,O,J,J,O,O,J,J,K,J,D,J,J,J,J,J,J,J,J,K,J,J,O,J,A,A,J,D,J,J,O,J,O,S,S,J,J
            ,A,O,A,O,O,O,O,O,O,O,W,
            W,O,O,O,J,J,J,J,J,J,J,J,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,J,J,J,J,J,J,J,J,W,W,W,W,W,W,W,W,W,J,J,J,J,O,S,S,J,A
            ,A,O,A,O,A,O,O,O,O,O,W,
            W,O,O,O,O,O,O,O,O,O,O,W,W,S,S,S,S,S,S,S,S,S,S,S,S,S,W,J,J,J,J,J,J,J,J,W,S,S,S,S,S,S,S,W,O,S,S,S,S,S,W,W,W
            ,W,W,W,W,W,W,W,O,O,O,W,
            W,O,O,O,J,J,J,J,J,J,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,W,J,J,J,J,J,J,J,J,W,S,S,S,S,S,S,S,W,O,S,O,O,O,O,W,J,J
            ,J,S,S,S,S,S,W,O,O,O,W,
            W,O,O,O,J,J,J,J,J,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,W,K,S,W,K,S,W,K,S,W,S,S,S,S,S,S,S,W,O,S,O,S,J,J,W,S,J
            ,S,S,S,S,S,S,W,O,O,O,W,
            W,O,O,O,J,J,J,J,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,W,J,J,W,J,J,W,J,J,W,S,S,S,S,S,S,S,W,O,O,O,S,J,J,W,S,J
            ,S,S,S,S,S,S,W,O,O,O,W,
            W,J,S,S,J,J,J,S,S,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,S,S
            ,S,S,S,S,S,S,W,R,O,R,W,
            W,J,S,S,J,J,S,S,S,W,A,A,A,A,J,J,J,J,J,J,A,A,A,A,A,A,A,A,A,J,J,J,J,J,A,J,A,W,J,J,J,J,W,J,J,J,J,J,J,W,S,S,S
            ,S,S,S,S,S,S,W,S,J,S,W,
            W,J,S,S,J,S,S,S,S,W,J,J,J,A,J,J,J,J,J,J,J,O,A,R,J,J,J,J,A,J,J,J,J,J,A,A,A,W,W,W,W,K,W,J,J,J,J,J,J,W,S,S,S
            ,S,S,S,S,S,S,W,S,G,S,W,
            W,J,S,S,S,S,S,S,S,W,J,J,A,J,A,A,A,A,A,A,A,J,J,J,J,J,J,W,K,W,J,J,J,J,J,J,A,A,A,A,A,A,A,J,A,A,A,A,J,W,S,S,S
            ,S,S,S,S,S,S,W,S,G,S,W,
            W,J,S,S,S,S,S,S,S,W,J,J,A,J,A,J,J,J,J,J,A,A,A,A,J,J,J,W,J,W,J,A,A,A,A,J,J,J,J,J,J,J,J,J,J,J,J,A,J,W,S,S,S
            ,S,S,S,S,S,S,W,S,O,S,W,
            W,S,S,S,S,S,S,S,S,W,J,J,J,A,J,J,J,J,J,J,J,J,J,A,J,J,J,W,W,W,J,A,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,A,R,W,S,S,J
            ,J,S,S,S,S,S,S,S,J,S,W,
            W,S,S,S,S,S,S,S,S,W,J,J,O,A,J,J,J,J,J,J,J,J,J,A,J,J,J,J,J,J,J,A,J,J,J,J,J,J,A,A,A,J,J,J,J,J,J,A,A,W,S,S,S
            ,S,S,S,S,S,S,W,S,J,S,W,
            W,S,S,S,S,S,S,S,S,W,W,W,W,A,J,J,J,J,J,J,J,J,J,A,J,J,J,J,J,J,J,A,J,J,J,J,J,J,J,J,A,J,J,J,J,J,J,J,A,W,S,S,S
            ,S,S,S,S,S,S,S,S,O,S,W,
            W,S,S,S,S,S,S,S,S,W,J,J,K,A,J,J,J,J,A,A,A,A,A,A,A,A,J,A,A,A,A,A,A,A,J,J,J,J,J,J,A,J,J,J,J,J,J,J,A,W,S,S,S
            ,S,S,S,S,S,S,W,S,J,S,W,
            W,S,S,S,S,S,S,S,S,W,W,W,W,J,J,R,A,A,A,J,J,J,J,J,J,J,J,J,A,J,J,A,J,A,J,J,J,J,J,A,A,A,J,J,A,W,K,W,K,W,S,S,S
            ,S,S,S,S,S,S,S,S,J,S,W,
            W,S,S,S,S,S,S,S,S,W,J,J,J,J,J,J,W,K,W,J,J,J,J,J,J,J,J,J,R,J,J,A,A,A,A,A,A,A,W,K,W,A,A,A,A,W,J,W,J,W,S,S,S
            ,S,S,S,S,S,S,W,S,J,S,W,
            W,S,S,S,S,S,S,S,S,W,J,J,J,J,J,J,W,J,W,J,J,J,J,J,J,J,J,J,J,J,J,J,A,J,J,J,J,J,W,J,W,W,W,W,W,W,J,W,J,W,S,S,S
            ,S,S,S,S,S,S,S,S,J,S,W,
            W,S,S,S,S,S,S,S,S,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,D,W,W,W,W,W,W,W,W,J,J,J,J,W,W,W,W,W,W,W,W
            ,W,W,W,W,W,W,W,S,O,S,W,
            W,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,W,J,W,W,W,J,G,J,J,J,J,W,S,J,S,S,S,S,S,G,S,J
            ,J,J,J,G,W,J,W,S,J,S,W,
            W,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,W,J,W,W,J,J,W,W,W,W,W,W,S,S,W,W,W,W,W,W,W,W
            ,W,W,W,S,W,J,W,S,J,S,W,
            W,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,W,J,W,O,W,O,O,O,W,J,J,W,J,O,D,J,W,W,J,O,W,J
            ,O,J,W,J,W,J,W,S,J,S,W,
            W,J,J,J,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,W,D,W,O,O,O,W,G,J,J,J,D,O,J,W,J,W,J,J,S,D,J
            ,W,J,D,J,W,J,W,S,O,S,W,
            W,J,J,J,S,J,K,O,J,J,J,O,J,J,J,O,J,J,J,O,J,J,J,O,J,J,J,O,O,J,J,R,R,D,O,W,W,W,W,W,W,G,W,J,J,W,W,W,D,W,W,W,D
            ,W,W,W,J,O,J,D,J,J,S,W,
            W,J,J,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,O,W,O,O,G,W,J,J,D,J,O,D,J,J,J,W,J,D,J
            ,W,J,D,J,W,W,W,W,W,W,W,
            W,W,S,W,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,W,O,O,O,O,O,O,J,W,W,O,J,W,W,W,J,O,J,W,J
            ,D,O,W,S,W,J,J,J,J,J,W,
            W,W,D,W,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,O,W,O,W,W,W,W,W,W,W,W,S,W,W,W,S,W,S,W,W,W
            ,W,W,W,J,W,W,W,W,W,J,W,
            W,W,J,D,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,J,O,J,W,J,O,J,J,R,G,O,O,J,J,J,J,J,J,J,J,J,J,J
            ,J,G,J,J,J,J,J,J,J,J,W,
            W,W,J,W,W,S,W,W,W,W,W,W,W,W,S,W,W,W,W,W,W,W,W,S,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W
            ,W,W,W,W,W,W,W,W,W,W,W,
            W,W,J,S,S,S,S,S,S,S,S,J,S,S,S,S,S,S,S,J,S,S,S,S,S,S,S,J,S,S,S,S,S,S,S,J,S,S,S,S,S,S,S,L,J,J,O,O,O,D,G,S,J
            ,W,J,W,O,J,J,J,J,O,J,W,
            W,W,D,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,S,W,W,W,W,W,W,W,W,W,W,W,W,W,W,J
            ,W,J,D,O,O,O,O,O,O,J,W,
            W,J,J,O,O,O,K,J,J,J,J,K,J,J,J,K,A,G,A,A,G,G,O,K,J,W,W,W,W,W,W,J,J,J,J,J,J,S,W,J,J,J,S,J,J,S,S,J,J,O,O,W,J
            ,W,J,W,O,J,J,J,J,O,J,W,
            W,W,D,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,J,W,S,S,S,W,W,S,W,W,W,O,W,J
            ,S,J,W,W,W,W,W,W,O,J,W,
            W,S,J,W,J,W,S,D,O,J,S,W,W,W,S,D,O,J,S,G,O,J,S,G,O,J,S,D,O,J,S,D,O,J,S,D,O,J,J,J,W,J,S,G,J,S,S,J,J,O,O,W,S
            ,S,S,S,S,W,J,O,O,O,J,W,
            W,S,J,W,J,S,S,W,W,S,S,D,O,J,S,W,W,S,S,W,W,S,S,S,W,S,S,W,W,S,S,W,W,S,S,W,W,W,S,W,W,J,S,S,W,W,S,S,W,W,W,W,J
            ,J,J,J,J,W,J,O,O,O,J,W,
            W,S,J,W,J,S,S,D,O,J,S,W,W,S,S,D,O,J,S,O,O,J,S,G,J,J,S,D,O,J,S,D,O,J,S,S,W,W,S,J,J,J,W,J,J,J,J,J,J,J,J,J,J
            ,J,J,W,J,W,J,O,W,W,W,W,
            W,S,J,L,J,S,S,W,W,S,S,D,O,J,S,W,W,S,S,W,W,S,S,S,W,S,S,W,W,S,S,W,W,S,S,S,J,J,S,S,S,S,S,O,W,W,W,W,W,W,J,J,J
            ,J,J,W,O,W,J,O,O,O,J,W,
            W,S,J,W,J,S,S,O,O,J,S,W,W,S,S,D,O,J,S,O,O,J,S,O,O,J,S,G,G,J,S,G,A,J,S,S,W,W,S,J,J,J,W,O,J,J,O,J,J,W,J,W,W
            ,W,W,W,O,W,J,O,O,O,J,W,
            W,S,J,W,J,S,S,W,W,S,S,O,O,J,S,W,W,S,S,S,W,W,S,S,W,S,S,W,W,S,S,W,W,S,S,W,W,W,W,W,W,S,W,W,W,W,W,W,W,W,O,W,J
            ,J,J,W,O,W,J,J,J,J,J,W,
            W,S,J,W,J,W,S,D,O,J,S,W,W,S,S,D,O,J,S,G,O,J,S,G,O,J,S,D,O,J,S,D,O,J,S,S,S,J,J,S,J,J,S,S,S,S,J,J,O,O,O,W,J
            ,J,J,W,O,W,J,J,O,J,J,W,
            W,S,J,W,J,W,W,W,W,W,S,D,O,J,S,W,W,W,W,W,W,W,W,W,W,W,S,W,W,W,S,W,W,W,S,S,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W
            ,D,W,W,O,W,J,O,O,O,J,W,
            W,S,J,W,J,S,S,D,O,O,S,W,W,W,S,D,O,J,S,D,O,J,S,D,O,J,S,D,O,J,S,D,G,J,S,S,S,S,O,L,O,O,O,O,O,J,K,D,J,J,J,W,J
            ,J,J,D,O,W,O,O,O,O,O,W,
            W,S,J,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W
            ,W,W,W,O,W,S,J,J,J,S,W,
            W,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S,S
            ,S,S,S,O,W,S,S,J,S,S,W,
            W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W,W
            ,W,W,W,W,W,W,W,W,W,W,W,
    };

    {
        color1 = 5459774;
        color2 = 12179041;
    }

    public static int[] LingPoisonD = new int[]{
            WIDTH +  3,
            WIDTH*2 +  3,
            WIDTH*3 +  3,
            WIDTH*4 +  3,
            WIDTH*5 +  3,
            WIDTH*6 +  3,
            WIDTH*7 +  3,
            WIDTH*8 +  3,
            WIDTH*9 +  3,

            WIDTH +    4,
            WIDTH*2 +  4,
            WIDTH*3 +  4,
            WIDTH*4 +  4,
            WIDTH*5 +  4,
            WIDTH*6 +  4,
            WIDTH*7 +  4,
            WIDTH*8 +  4,
            WIDTH*9 +  4,
    };

    public static int[] LingPoisonX = new int[]{
            WIDTH*14 +  3,
            WIDTH*14 +  2,
            WIDTH*14 +  1,

            WIDTH*17 +  4,
            WIDTH*17 +  5,
            WIDTH*17 +  6,
            WIDTH*17 +  7,
            WIDTH*17 +  8,
            WIDTH*17 +  9,
            WIDTH*17 +  10,
            WIDTH*17 +  11,

            WIDTH*21 +  4,
            WIDTH*21 +  5,
            WIDTH*21 +  6,
            WIDTH*21 +  7,
            WIDTH*21 +  8,
            WIDTH*21 +  9,
            WIDTH*21 +  10,
            WIDTH*21 +  11,
    };

    public static int[] LingPoison = new int[]{
            WIDTH*20 +  2,
            WIDTH*21 +  2,
            WIDTH*22 +  2,
            WIDTH*23 +  2,
            WIDTH*24 +  2,

            WIDTH*20 +  1,
            WIDTH*21 +  1,
            WIDTH*22 +  1,
            WIDTH*23 +  1,
            WIDTH*24 +  1,
    };

    public static int[] LingPoisonB = new int[]{
            WIDTH*15 +  2,
            WIDTH*16 +  2,
            WIDTH*17 +  2,
            WIDTH*18 +  2,
            WIDTH*19 +  2,

            WIDTH*15 +  1,
            WIDTH*16 +  1,
            WIDTH*17 +  1,
            WIDTH*18 +  1,
            WIDTH*19 +  1,
    };

    public static int[] LingPoisonC = new int[]{
            WIDTH*15 +  3,
            WIDTH*16 +  3,
            WIDTH*17 +  3,
            WIDTH*18 +  3,
            WIDTH*19 +  3,

            WIDTH*20 +  3,
            WIDTH*21 +  3,
            WIDTH*22 +  3,
            WIDTH*23 +  3,
            WIDTH*24 +  3,

            WIDTH*15 +  15,
    };

    public static int[] LingPoisonE = new int[]{
            WIDTH*4 +  7,
            WIDTH*5 +  7,
            WIDTH*6 +  7,
            WIDTH +    11,
            WIDTH*7 +  6,

            WIDTH*6 +  8,
            WIDTH*8 +  8,
            WIDTH*6 +  8,
            WIDTH*9 +  8,

            WIDTH*6 +  9,

            WIDTH*7 +  7,
            WIDTH*7 +  8,
            WIDTH*8 +  7,
            WIDTH*9 +  7,
            WIDTH*9 +  6,

            WIDTH*4 +  8,
            WIDTH*4 +  9,
    };

    public static int[] LingPoisonF = new int[]{
            WIDTH*19+  13,
            WIDTH*19 +  15,
            WIDTH*19 +  17,
            WIDTH*19 +  20,
            WIDTH*19 +  21,
    };

    public static int[] LingPoisonG = new int[]{
            WIDTH*13+  20,
    };

    @Override
    protected boolean build() {
        setSize(WIDTH, HEIGHT);
        map = code_map.clone();
        exit = 0;
        this.entrance = WIDTH*19 + 2;

        //todo 回血陷阱 愿幸运伴随你
        for (int i : LingPoisonG){
            if (map[i] == PEDESTAL) {
                map[i] = Terrain.INACTIVE_TRAP;
                Trap t = new HealingTrap().reveal();
                setTrap(t, i);
                map[i] = TRAP;
            }
        }

        //todo 即死陷阱 都别活着 啊哈哈哈哈哈
        for (int i : LingPoison){
            if (map[i] == PEDESTAL) {
                map[i] = Terrain.INACTIVE_TRAP;
                Trap t = new GrimTrap().reveal();
                setTrap(t, i);
                map[i] = TRAP;
            }
        }

        for (int i : LingPoisonB){
            if (map[i] == PEDESTAL) {
                map[i] = Terrain.INACTIVE_TRAP;
                Trap t = new GrimTrap().reveal();
                setTrap(t, i);
                map[i] = TRAP;
            }
        }

        for (int i : LingPoisonX){
            if (map[i] == PEDESTAL) {
                map[i] = Terrain.INACTIVE_TRAP;
                Trap t = new GrimTrap().reveal();
                setTrap(t, i);
                map[i] = TRAP;
            }
        }

        for (int i : LingPoisonC){
            if (map[i] == PEDESTAL) {
                map[i] = Terrain.INACTIVE_TRAP;
                Trap t = new GrimTrap().reveal();
                setTrap(t, i);
                map[i] = TRAP;
            }
        }

        for (int i : LingPoisonE){
            if (map[i] == PEDESTAL) {
                map[i] = Terrain.INACTIVE_TRAP;
                Trap t = new GrimTrap().reveal();
                setTrap(t, i);
                map[i] = TRAP;
            }
        }

        //todo 捕猎 逮到你了！！！
        for (int i : LingPoisonD){
            if (map[i] == PEDESTAL) {
                map[i] = Terrain.INACTIVE_TRAP;
                Trap t = new GrippingTrap().reveal();
                setTrap(t, i);
                map[i] = TRAP;
            }
        }

        for (int i : LingPoisonF){
            if (map[i] == PEDESTAL) {
                map[i] = Terrain.INACTIVE_TRAP;
                Trap t = new GrippingTrap().reveal();
                setTrap(t, i);
                map[i] = TRAP;
            }
        }

        return true;
    }

    @Override
    protected void createItems() {

        //todo for循环实现 黄金钥匙
        for (int i : GoldKeyPositionsA) {
            drop( new GoldenKey(), i );
        }

        for (int i : GoldKeyPositionsB) {
            drop( new GoldenKey(), i );
        }

        //todo 小生命药剂的放置
        for (int i : SmallPotionA) {
            drop( new SmallPotion(), i );
        }

        for (int i : SmallPotionB) {
            drop( new SmallPotion(), i );
        }

        for (int i : SmallPotionC) {
            drop( new SmallPotion(), i );
        }

        //todo 大生命药剂的放置
        for (int i : BigPotionA) {
            drop( new BigPotion(), i );
        }

        for (int i : BigPotionB) {
            drop( new BigPotion(), i );
        }
        //todo 火焰药剂的放置
        for (int i : FirePotionA) {
            drop( new PotionOfLiquidFlame(), i );
        }

        for (int i : FirePotionB) {
            drop( new PotionOfLiquidFlame(), i );
        }

        //todo 雪药的放置
        for (int i : SnowPotion) {
            drop( new PotionOfFrost(), i );
        }

        drop( new PotionOfHaste(), WIDTH*46+43 );

        for (int i : FlyPotion) {
            drop( new PotionOfLevitation(), i );
        }

        for (int i : ClearPotion) {
            drop( new PotionOfPurity(), i );
        }

        for (int i : EyePotion) {
            drop( new PotionOfMindVision(), i );
        }

        for (int i : StormSrcoll) {
            drop( new ScrollOfPsionicBlast(), i );
        }

        for (int i : SXStone) {
            drop( new StoneOfBlink(), i );
        }

        drop( new ScrollOfAntiMagic(), WIDTH*2+39 );

    }

    public static int[] SXStone = new int[]{
            WIDTH + 38,
            WIDTH + 39,
            WIDTH*2 + 20,
            WIDTH*2 + 52,
            WIDTH*10 + 61,
            WIDTH*15 + 17,
            WIDTH*23 + 52,
            WIDTH*24 + 52,
            WIDTH*30 + 52,
            WIDTH*30 + 53,
            WIDTH*38 + 53,
            WIDTH*42 + 2,
            WIDTH*42 + 5,
            WIDTH*44 + 58,
            WIDTH*48 + 11,
            WIDTH*48 + 19,
            WIDTH*48 + 27,
            WIDTH*48 + 35,
            WIDTH*48 + 45,
            WIDTH*48 + 52,
            WIDTH*50 + 44,
            WIDTH*52 + 9,
            WIDTH*52 + 17,
            WIDTH*52 + 21,
            WIDTH*52 + 25,
            WIDTH*52 + 29,
            WIDTH*52 + 33,
            WIDTH*52 + 37,
            WIDTH*52 + 47,
            WIDTH*52 + 48,
            WIDTH*53 + 13,
            WIDTH*54 + 4,
            WIDTH*54 + 9,
            WIDTH*54 + 17,
            WIDTH*54 + 21,
            WIDTH*54 + 25,
            WIDTH*54 + 29,
            WIDTH*54 + 33,
            WIDTH*56 + 9,
            WIDTH*56 + 17,
            WIDTH*56 + 21,
            //todo 未完成
    };

    public static int[] SmallPotionA = new int[]{
            WIDTH + 51,
            WIDTH*2 + 10,
            WIDTH*2 + 51,
            WIDTH*7 + 44,
            WIDTH*8 + 44,
            WIDTH*11 + 31,
            WIDTH*12 + 60,
            WIDTH*12 + 62,
            WIDTH*14 + 27,
    };

    public static int[] SmallPotionB = new int[]{
            WIDTH*14 + 29,
            WIDTH*15 + 44,
            WIDTH*18 + 46,
            WIDTH*19 + 6,
            WIDTH*19 + 23,
            WIDTH*20 + 46,
            WIDTH*20 + 51,
            WIDTH*23 + 49,
            WIDTH*24 + 34,
    };

    public static int[] SmallPotionC = new int[]{
            WIDTH*24 + 49,
            WIDTH*31 + 61,
            WIDTH*35 + 61,
            WIDTH*36 + 61,
            WIDTH*50 + 17,
    };

    public static int[] BigPotionA = new int[]{
            WIDTH*12 + 61,
            WIDTH*19 + 14,
            WIDTH*19 + 16,
            WIDTH*19 + 19,
            WIDTH*26 + 35,
            WIDTH*24 + 28,
            WIDTH*26 + 38,
            WIDTH*34 + 32,
            WIDTH*35 + 46,
    };

    public static int[] BigPotionB = new int[]{
            WIDTH*36 + 39,
            WIDTH*36 + 48,
    };

    public static int[] FirePotionA = new int[]{
            WIDTH+ 50,
            WIDTH*7 + 60,
            WIDTH*7 + 61,
            WIDTH*7 + 62,
            WIDTH*14 + 44,
            WIDTH*19 + 22,
            WIDTH*19 + 29,
            WIDTH*26 + 39,
            WIDTH*36 + 17,
    };

    public static int[] FirePotionB = new int[]{
            WIDTH*44+ 5,
            WIDTH*44 + 6,
            WIDTH*44 + 10,
            WIDTH*44 + 14,
            WIDTH*44 + 18,
            WIDTH*44 + 21,
            WIDTH*44 + 23,
    };

    public static int[] SnowPotion = new int[]{
            WIDTH*19+36,
            WIDTH*29+28,
    };

    public static int[] FlyPotion = new int[]{
            WIDTH*9+8,
            WIDTH*46+32,
            WIDTH*50+7,
            WIDTH*50+9,
            WIDTH*50+32,
            WIDTH*50+33,
            WIDTH*50+34,
            WIDTH*50+35,
    };

    public static int[] ClearPotion = new int[]{
            WIDTH*44+15,
            WIDTH*44+17,
            WIDTH*44+20,
            WIDTH*44+27,
            WIDTH*50+31,
    };

    public static int[] EyePotion = new int[]{
            WIDTH*57+52,
            WIDTH*58+46,
    };

    public static int[] StormSrcoll = new int[]{
            WIDTH*50+48,
            WIDTH*57+54,
    };

    //todo 黄金钥匙的定位 使用int数集 进行For循环
    //todo width是y轴，后面是x轴 WIDTH就是此地图的长度，并且通过对应坐标进行计算
    //todo 64x64=4096 也就是自定义地图的最大取值 也就是2的12次方
    //todo int单个数集最多为2147483647个
    public static int[] GoldKeyPositionsA = new int[]{
            WIDTH*2 + 38,
            WIDTH*6 + 44,
            WIDTH*9 + 7,
            WIDTH*13 + 61,
            WIDTH*16 + 51,
            WIDTH*23 + 53,
            WIDTH*24 + 31,
            WIDTH*33 + 11,
            WIDTH*57 + 53,
            WIDTH*58 + 53,
    };

    public static int[] GoldKeyPositionsB = new int[]{
            WIDTH*38 + 56,
            WIDTH*26 + 41,
            WIDTH*26 + 40,
            WIDTH*22 + 52,
            WIDTH*50 + 36,
    };

    @Override
    public Mob createMob() {
        return null;
    }

    public static int[] RedGnollGuradA = new int[]{
            WIDTH*15 + 20,
            WIDTH*4 + 38,
            WIDTH*4 + 39,
            WIDTH*6 + 38,
            WIDTH*6 + 39,
            WIDTH*6 + 40,
            WIDTH*6 + 41,
            WIDTH*3 + 54,
            WIDTH*3 + 56,
            WIDTH*4 + 54,
            WIDTH*4 + 56,
            WIDTH*8 + 40,
            WIDTH*8 + 41,
            WIDTH*8 + 56,
            WIDTH*7 + 56,
            WIDTH*13 + 56,
            WIDTH*17 + 20,
            WIDTH*48 + 34,
            WIDTH*50 + 57,
            WIDTH*50 + 58,
            WIDTH*50 + 59,
            WIDTH*50 + 60,
            WIDTH*52 + 57,
            WIDTH*52 + 58,
            WIDTH*52 + 59,
            WIDTH*52 + 60,
            WIDTH*50 + 62,
            WIDTH*51 + 62,
            WIDTH*53 + 62,
            WIDTH*54 + 62,
            WIDTH*55 + 62,
            WIDTH*54 + 58,
            WIDTH*55 + 58,
            WIDTH*56 + 58,
            WIDTH*57 + 58,
            WIDTH*58 + 58,
            WIDTH*59 + 58,
            WIDTH*60 + 58,
            WIDTH*61 + 58,
            WIDTH*59 + 59,
            WIDTH*59 + 60,
            WIDTH*59 + 61,
            WIDTH*60 + 59,
            WIDTH*60 + 61,
    };

    @Override
    protected void createMobs() {

        for (int i : RedGnollGuradA) {
            Gnoll n = new Gnoll();
            n.pos = i;
            mobs.add(n);
        }
//
//        for (int i = 20; i < 25; i+=2){
//            ColdGurad x = new ColdGurad();
//            x.pos = (this.width * i + 10);
//            mobs.add(x);
//        }


    }

    @Override
    public String tilesTex() {
        return Assets.Environment.TILES_CAVES;
    }

    @Override
    public String waterTex() {
        return Assets.Environment.WATER_CAVES;
    }

}



