@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import java.util.function.Function

HeroesSkills.getInstance().mainPathGui.registerPath(
    "wildbeast",
    29,
    "野獸天賦",
    Function{

        if(it.player.hasPermission("hs.path.wildbeast"))
            return@Function Icon()
                .setDisplayName("&7野性天賦 &8(%hs_path_wildbeast_unlocked%/%hs_path_wildbeast_max%)")
                .addLore(
                    "",
                    "&f人類的體內存在著一股潛力",
                    "&f這股力量可以讓人短暫擁有媲美野獸的能力",
                    "&f但平時只有在危急的時候才能釋放",
                    "&f釋放後身體會獲得極大的力量",
                    "&f但對身體也會造成一定的負擔",
                    "",
                    "&7[&e點擊開啟技能樹&7]"
                )
        else return@Function Icon()
            .setDisplayName("&c此天賦尚未開放，敬請期待")
    },
    mapOf(
        8 to java.util.function.Function{
            Icon()
                .setMaterial(Material.OAK_SIGN)
                .setDisplayName("&a野性天賦")
                .addLore(
                    "",
                    "&f人類的體內存在著一股潛力",
                    "&f這股力量可以讓人短暫擁有媲美野獸的能力",
                    "&f但平時只有在危急的時候才能釋放",
                    "&f釋放後身體會獲得極大的力量",
                    "&f但對身體也會造成一定的負擔",
                    "",
                    "&f剩餘天賦點: &7%hs_skillpoint_current%&8/&7%hs_skillpoint_max%",
                    "&7<&a點擊技能圖示升級或解鎖技能天賦&7>"
                )
        }
    )
)
HeroesSkills.getInstance().mainPathGui.registerPath(
    "totem",
    33,
    "圖騰天賦",
    Function{

        if(it.player.hasPermission("hs.path.totem"))
            return@Function Icon()
                .setDisplayName("&7圖騰天賦 &8(%hs_path_totem_unlocked%/%hs_path_totem_max%)")
                .addLore(
                    "",
                    "&f在場上留下帶有圖騰的物體",
                    "&f圖騰的力量能夠帶給各種不同的能力",
                    "",
                    "&7[&e點擊開啟技能樹&7]"
                )
        else return@Function Icon()
            .setDisplayName("&c此天賦尚未開放，敬請期待")
    },
    mapOf(
        8 to java.util.function.Function{
            Icon()
                .setMaterial(Material.OAK_SIGN)
                .setDisplayName("&a圖騰天賦")
                .addLore(
                    "",
                    "&f在場上留下帶有圖騰的物體",
                    "&f圖騰的力量能夠帶給各種不同的能力",
                    "",
                    "&f剩餘天賦點: &7%hs_skillpoint_current%&8/&7%hs_skillpoint_max%",
                    "&7<&a點擊技能圖示升級或解鎖技能天賦&7>"
                )
        }
    )
)
HeroesSkills.getInstance().mainPathGui.registerPath(
    "fencing",
    21,
    "劍術天賦",
    Function{
        Icon()
            .setDisplayName("&7劍術天賦 &8(%hs_path_fencing_unlocked%/%hs_path_fencing_max%)")
            .addLore(
                "",
                "&f劍術是基本的揮劍技巧",
                "&f樸實無華，卻能夠造成不錯的傷害",
                "&f強大的劍術能夠有不輸給魔法的破壞力",
                "",
                "&7[&e點擊開啟技能樹&7]"
            )
    },
    mapOf(
        8 to java.util.function.Function{
            Icon()
                .setMaterial(Material.OAK_SIGN)
                .setDisplayName("&e劍術天賦")
                .addLore(
                    "",
                    "&7劍術是基本的揮劍技巧",
                    "&7樸實無華，卻能夠造成不錯的傷害",
                    "&7強大的劍術能夠有不輸給魔法的破壞力",
                    "",
                    "&f剩餘天賦點: &7%hs_skillpoint_current%&8/&7%hs_skillpoint_max%",
                    "&7<&a點擊技能圖示升級或解鎖技能天賦&7>"
                )
        }
    )
)

HeroesSkills.getInstance().mainPathGui.registerPath(
    "element",
    23,
    "&7元素天賦",
    Function{
        Icon()
            .setDisplayName("&7元素天賦 &8(%hs_path_element_unlocked%/%hs_path_element_max%)")
            .addLore(
                "",
                "&f與元素精靈溝通達成契約",
                "&f借助元素精靈的力量施展元素魔法",
                "&f元素魔法在戰鬥中有著不錯的破壞力",
                "&f但需要消耗魔力是一大缺點",
                "",
                "&7[&e點擊開啟技能樹&7]"
            )
    },
    mapOf(
        8 to java.util.function.Function{
            Icon()
                .setMaterial(Material.OAK_SIGN)
                .setDisplayName("&e元素天賦")
                .addLore(
                    "",
                    "&f與元素精靈溝通達成契約",
                    "&f借助元素精靈的力量施展元素魔法",
                    "&f元素魔法在戰鬥中有著不錯的破壞力",
                    "&f但需要消耗魔力是一大缺點",
                    "",
                    "&f剩餘天賦點: &7%hs_skillpoint_current%&8/&7%hs_skillpoint_max%",
                    "&7<&a點擊技能圖示升級或解鎖技能天賦&7>"
                )
        }
    )
)