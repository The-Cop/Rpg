package com.thecop.rpg.level.generator;

import com.thecop.rpg.level.LevelMap;

public interface LevelMapGenerator {
    //TODO implement different generators
    // http://gamedevelopment.tutsplus.com/tutorials/create-a-procedurally-generated-dungeon-cave-system--gamedev-10099
    // http://gamedevelopment.tutsplus.com/tutorials/cave-levels-cellular-automata--gamedev-9664
    // http://www.roguebasin.com/index.php?title=Basic_BSP_Dungeon_generation

    //TODO may be additional parameters will be necessary: "game skill", "depth of the level" (deeper is harder),
    // "loot amount", "monsters count", e.t.c.
    //Maybe create a builder for this
    LevelMap generateMap();

}
