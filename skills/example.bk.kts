@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.skills.MythicActiveSkill
import net.brian.heroesskills.core.skills.PassiveStatSkill
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import java.util.function.BiFunction


var passiveSkill = PassiveStatSkill(
    "技能id",
    "&c血量增幅", //顯示名稱
    10, //最大等級
    "MAX_HEALTH", //素質 ID
    { player, data -> 5.0+data.level*2 }, //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.REDSTONE_BLOCK) // 材質
        .setModelData(0) // model
        .setDisplayName("&c血量增幅 Lv.${data.level}") // 物品名稱
        .addLore( // 物品的 lore
            "&7增加 ${5+data.level*2} 的最大血量",
            "&e${data.level}/10"
        )
    }
);
HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("health" to 9)
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);


var activeSkill = MythicActiveSkill(
    "MythicMobs的技能ID", //同時也是這個技能的id
"顯示名稱",
    5, //最大等級
    10.0, //魔力消耗
    20.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.REDSTONE_BLOCK) // 材質
        .setModelData(0) // model
        .setDisplayName("&c血量增幅 ${data.level}") // 物品名稱
        .addLore( // 物品的 lore
            "&7增加 ${10 + data.level} 的最大血量",
            "&e${data.level}/10"
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);
HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("strength" to 4)
    )
)
