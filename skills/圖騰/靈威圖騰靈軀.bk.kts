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
    "靈威圖騰靈軀", //同時也是這個技能的id
    "&6靈威圖騰•靈軀",
    3, //最大等級
    30.0, //魔力消耗
    0.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.FIRE_CHARGE) // 材質
        .setModelData(0) // model
        .setDisplayName("&6靈威圖騰•靈軀 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f30 魔力")
                add("&7▪ 冷卻：&f30 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f圖騰守護神 Lv.5、靈威圖騰•靈傷 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f10秒內自身減傷15%")
                add("&f並增加10%魔法傷害加成")
                add("")
                add("&7Lv2 ➤ &7技能時間增加至15秒")
                add("&7Lv2 ➤ &7魔法傷害加成增加至20%")
                add("&7Lv2 ➤ &7魔法傷害加成增加至30%")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("totem" to 33),
        {it.getSkillData("圖騰守護神").level >=5&&it.getSkillData("靈威圖騰靈傷").level >=1}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);