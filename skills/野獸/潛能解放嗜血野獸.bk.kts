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
    "潛能解放嗜血野獸", //同時也是這個技能的id
    "&6潛能解放•嗜血野獸",
    3, //最大等級
    20.0, //魔力消耗
    2.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.FIRE_CORAL) // 材質
        .setModelData(0) // model
        .setDisplayName("&6潛能解放•嗜血野獸 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f20 魔力")
                add("&7▪ 冷卻：&f2 秒 &8(開關制)")
                if(data.isEmpty){
                    add("&7▪ 前置：&f野獸之王 Lv.5、血債血還 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f將自身體內嗜血野獸的力量全部解放")
                add("&f能夠大量增加自身的傷害及移動速度")
                add("&f但自身也會因為強大的力量受到不小的傷害")
                add("&c&n※所剩血量需大於40%才能發動技能")
                add("&c&n※若解放期間血量不足20%則無法維持技能")
                add("")
                add("&f開啟技能後獲得以下效果")
                add("&f◆ 立即減少20%血量")
                add("&f◆ 每秒額外減少5%血量")
                add("&f◆ 近戰攻擊時傷害變為2倍")
                add("&f◆ 自身獲得移動速度I")
                add("&f◆ 血債血還冷卻減少至10秒")
                add("&f◆ 嗜血撕咬冷卻減少至15秒")
                add("&f◆ 造成傷害時恢復1%血量(冷卻0.5秒)")
                add("&f")
                add("")
                add("&7Lv2 ➤ &7立即減少20%血量改為10%")
                add("&7Lv2 ➤ &7開啟技能時血債血還冷卻減少至8秒")
                add("&7Lv2 ➤ &7開啟技能時嗜血撕咬冷卻減少至12秒")
                add("&7Lv3 ➤ &7造成傷害時恢復3%血量(冷卻0.5秒)")
                add("&7Lv3 ➤ &7開啟技能時血債血還冷卻減少至3.5秒")
                add("&7Lv3 ➤ &7開啟技能時嗜血撕咬冷卻減少至8秒")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("wildbeast" to 45),
        {it.getSkillData("野獸之王").level >=5&&it.getSkillData("血債血還").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);