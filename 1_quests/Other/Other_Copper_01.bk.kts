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

val yankoIcon = IconFonts.getNPC(5);
val playerIcon = "%squests_icon%";
val questID = "other_copper_01"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Other_Copper_01: Quest(questID,"§7[§d特殊委託§7] §f了解傭兵"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val yankoNPC = "§c● §e焱恪§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e傭兵主要是要幹嘛?",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8傭兵並沒有規定一定要做甚麼",
            "&x&d&6&d&0&b&8傭兵的身分只是讓你能更好的接受適合自己的委託",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8在這個世界你想要活下來",
            "&x&d&6&d&0&b&8最主要還是要錢啊...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e當傭兵很賺錢嗎?",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8當然，資深傭兵接受的委託可是非常賺的",
            "&x&d&6&d&0&b&8但同時委託不管大小都非常危險",
            "&x&d&6&d&0&b&8因此需要傭兵們透過委託獲得傭兵聲望",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8傭兵聲望到達一定程度後",
            "&x&d&6&d&0&b&8傭兵公會將會提供進階委託",
            "&x&d&6&d&0&b&8完成後傭兵公會將承認你有進階的實力",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8這樣就可以接受更難的委託了！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e傭兵階級?有甚麼階級阿?",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8傭兵分為",
            "&x&d&6&d&0&b&8§rಆ §7銅牌傭兵、§rಇ §f銀牌傭兵、§rಈ §6金牌傭兵",
            "&x&d&6&d&0&b&8§rಉ §x§c§7§e§c§f§f白金傭兵、§rಌ §x§7§8§4§2§8§6黑曜傭兵",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8但還有一個階級 §rಎ §x§e§c§d§8§e§9神話傭兵",
            "&x&d&6&d&0&b&8這是只存在傳說中的階級",
            "&x&d&6&d&0&b&8據說整個帝國只有幾個人",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e聽起來真棒！目標神話！",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8哈哈哈！有志氣！",
            "&x&d&6&d&0&b&8小夥子，我看好你",
            "&x&d&6&d&0&b&8給你個禮物吧",
        )
    )


    init {
        cancelAble = false;

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .setInstruction("與 §e焱格 §7對話");


        pushObjective(obj1);
        addRewards(
            MessageReward("&a➯ &7錢幣: &f+100"),
            MessageReward("&a➯ &7傭兵聲望: &f+5"),
            MessageReward("&a➯ &7飾品: &b微弱魔力的戒指"),
            ClassExpReward(200),
        )
        addEndHook({
            Utils.command("money give ${it.name} 100")
            Utils.command("vh add ${it.name} 傭兵聲望 5")
            Utils.command("get ${it.name} RING A004")
        })
    }
}

val quest = Other_Copper_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}