@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location

val playerIcon = "%squests_icon%";
val NPC1Icon = IconFonts.getNPC(110);

val questID = "side_silver_05"

class Side_Silver_05: Quest(questID,"§7[§b銀牌支線§7] §f被搶走的商會貨物"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e墨里斯§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8唉...真是傷腦經",
            "&x&d&6&d&0&b&8恩?",
            "&x&d&6&d&0&b&8等等！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e叫我嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8看你的樣子應該是傭兵吧?",
            "&x&d&6&d&0&b&8我這裡有個大委託",
            "&x&d&6&d&0&b&8你有沒有興趣?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e甚麼委託?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8是這樣的...",
            "&x&d&6&d&0&b&8前幾天我們商會訂購了一大批貨物",
            "&x&d&6&d&0&b&8商會花了一大筆的錢在這批貨",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8沒想到卻在半路中被盜賊搶走了",
            "&x&d&6&d&0&b&8現在那批貨應該在盜賊的據點了..,",
            "&x&d&6&d&0&b&8你能去幫我們搶回來嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8放心，我一定會給你豐富的獎勵",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好吧,交給我了",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太感謝了！",
            "&x&d&6&d&0&b&8如果這批貨物遺失",
            "&x&d&6&d&0&b&8對商會來說可是重大的打擊",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這些是給你的獎勵",
            "&x&d&6&d&0&b&8另外！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我用我自己的權利",
            "&x&d&6&d&0&b&8破例讓你不用考核加入灰狼商會",
            "&x&d&6&d&0&b&8成為灰狼商會的傭兵",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這樣商會中的商會委託",
            "&x&d&6&d&0&b&8你就可以接取了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e真的嗎!太好了",
        ),
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",110,*mes1)
            .setInstruction("§7與 §e墨里斯 §7對話");
        val obj2 = GiveMultiItemObjective(this,"obj2",110,
            mapOf(
                "QUESTMATERIAL:QM014" to 32,
            ))
            .setInstruction{"§7將 §7${it.get("QUESTMATERIAL:QM014")}/32 個 灰狼商會的貨物 交給 §e墨里斯 §8(放置副手繳交)§r"}
        val obj3 = ListenNPCObjective(this,"obj3",110,*mes2)
            .setInstruction("§7與 §e墨里斯 §7對話");
        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(300), //30錢幣
            QuestExpReward(50), //15傭兵聲
            ClassExpReward(1000),
            MessageReward("&a➯ &7前稱: §7灰狼商會"),
            MessageReward("&a➯ &7權限: §f可以開始跟灰狼商會會長接取商會委託了"),
        )
        addEndHook({
            Utils.command("lp user ${it.name} permission set ps.prefix.8")
            Utils.command("lp user ${it.name} permission set wolf_chamber")
        })
    }
}


val quest = Side_Silver_05();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



