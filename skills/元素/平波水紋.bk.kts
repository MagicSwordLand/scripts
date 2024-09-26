@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.gui.paths.conditions.SkillLevelCondition
import net.brian.heroesskills.core.skills.MythicActiveSkill
import net.brian.heroesskills.core.skills.PassiveMultipleStatSkill
import net.brian.heroesskills.core.skills.PassiveStatSkill
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import java.util.function.BiFunction

var activeSkill = MythicActiveSkill(
    "平波水紋", //同時也是這個技能的id
    "&6平波水紋",
    3, //最大等級
    40.0, //魔力消耗
    15.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.NETHER_SPROUTS) // 材質
        .setModelData(0) // model
        .setDisplayName("&6平波水紋 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f40 魔力")
                add("&7▪ 冷卻：&f15 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f心如止水 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f向前方釋放出一個緩慢且平靜的水紋")
                add("&f水紋內隊友獲得 三秒移動加速II")
                add("&f水紋內敵人獲得 三秒移動緩速II 及造成")
                add("&f&f攻擊魔傷*1.6+智慧*2")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*2+智慧*2.5")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊魔傷*2.5+智慧*3.5")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("element" to 36),
        {it.getSkillData("心如止水").level >=5}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);