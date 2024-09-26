@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MessageReward
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.entity.Player

val yankoIcon = IconFonts.getNPC(55);
val playerIcon = "%squests_icon%";
val questID = "other_copper_pet_01"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Other_Copper_Pet_01: Quest(questID,"§7[§d特殊委託§7] §f忠實的夥伴"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val yankoNPC = "§c● §e赫巴斯§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "§x§d§6§c§1§a§e欸欸欸！",
            "§x§d§6§c§1§a§e你過來一下",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼了嗎?",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8公會為了提高傭兵的生存率",
            "&x&d&6&d&0&b&8研究出了將怪物變成寵物的方法",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8這些寵物雖然不能直接戰鬥",
            "&x&d&6&d&0&b&8但會給於主人各種不同的能力",
            "&x&d&6&d&0&b&8能力包含屬性加成、被動效果、主動技能",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e真的嗎！",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8是的",
            "&x&d&6&d&0&b&8公會這邊有提供給各位傭兵一個基礎寵物",
            "&x&d&6&d&0&b&8希望你能好好照顧牠",
        ),
    )


    init {
        cancelAble = false;

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .setInstruction("與 §e赫巴斯 §7對話");


        pushObjective(obj1);
        addRewards(
            MessageReward("&a➯ &7錢幣: &f+100"),
            MessageReward("&a➯ &7傭兵聲望: &f+5"),
            MessageReward("&a➯ &7寵物: &f小樹妖 &7Lv.1"),
            ClassExpReward(200),
        )
        addEndHook({
            Utils.command("money give ${it.name} 100")
            Utils.command("vh add ${it.name} 傭兵聲望 5")
            Utils.command("hp pet give ${it.name} tree-elf")
        })
    }
}

val quest = Other_Copper_Pet_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}