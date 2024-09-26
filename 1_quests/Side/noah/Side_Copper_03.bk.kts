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
val NPC1Icon = IconFonts.getNPC(55);
val NPC2Icon = IconFonts.getNPC(114);

val questID = "side_copper_03"

class Side_Copper_03: Quest(questID,"§7[§b銅牌支線§7] §f礦區歷練裡的金生金"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● &e赫巴斯§r:";
    private val NPC2 = "§c● &e卡克拉§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8諾亞島上有很多問題...",
            "&x&d&6&d&0&b&8見過礦場的卡克拉了嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8因為這傢伙很有能力",
            "&x&d&6&d&0&b&8所以礦場都是歸他管",
            "&x&d&6&d&0&b&8你去找他，讓他給你一點事情做",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8挖挖礦，順便賺點零花錢",
            "&x&d&6&d&0&b&8總比做個無聊的殺怪機器更好吧",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8是基地的傭兵家夥讓你來歷練的嗎？",
            "&x&d&6&d&0&b&8他們都把這裏當成什麽了，金庫嗎？",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8算了，你去前面這個礦場裏面幫我忙",
            "&x&d&6&d&0&b&8帶給我 16個原碳 和 8個鑌礦 就可以了",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8怎麽？沒有鎬子嗎？",
            "&x&d&6&d&0&b&8這把送你吧！",
        )

    )
    private val mes3 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8鎬子挖壞了嗎？",
            "&x&d&6&d&0&b&8如果挖壞了，推薦你去集市那邊買",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8不過我建議你自己用素材做會比較划算",
            "&x&d&6&d&0&b&8這樣壞了也可以用比較少的錢幣找 肯特 修",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8這是你完成試煉的憑證",
            "&x&d&6&d&0&b&8證明你會挖礦賺錢...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8回去找委托你任務的傭兵",
            "&x&d&6&d&0&b&8老樣子，他會給你獎勵的",
        )
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8你是從礦場回來了嗎",
            "&x&d&6&d&0&b&8怎麽樣，卡克拉説了什麽？",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8噢噢，原來已經完成任務了",
            "&x&d&6&d&0&b&8那麼跟你說一些小知識吧",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8挖到的礦物可以到旁邊的 恩費 那裡販賣",
            "&x&d&6&d&0&b&8礦物描述裡都有顯示價格",
            "&x&d&6&d&0&b&8這就是這次委託的獎勵",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",55,*mes1)
            .setInstruction("§7與 §e赫巴斯 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",124,*mes2)
            .setInstruction("§7與 §e卡克拉 §7對話")
            .setEndProcess {
                it.sendTitle("§7[§a獲得物品§7]","§f木稿")
                Utils.command("get ${it.name} TOOL P001 1")
            }
        val obj3 = GiveMultiItemObjective(this,"obj3",124,
            mapOf(
                "COLLECTMATERIAL:M004" to 16,
                "COLLECTMATERIAL:M001" to 8
            ))
            .setInstruction{"§7將 §7${it.get("COLLECTMATERIAL:M004")}/16 個原炭§7、${it.get("COLLECTMATERIAL:M001")}/8 個 §7鑌礦 §7交給 §e卡克拉§r"}
        val obj4 = ListenNPCObjective(this,"obj4",124,*mes3)
            .setInstruction("§7與 §e卡克拉 §7對話");
        val obj5 = ListenNPCObjective(this,"obj5",55,*mes4)
            .setInstruction("§7與 §e赫巴斯 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5)

        addRewards(
            MoneyReward(20), //30錢幣
            ClassExpReward(200),
            QuestExpReward(8), //15傭兵聲
            MessageReward("&a➯ &7稱號: §7[§f\uE21A§x§e§4§d§7§d§1小心翼翼的§f\uE21A§7]")
        )
        addEndHook({
            Utils.command("lp user ${it.name} permission set ps.suffix.6")
        })
    }
}


val quest = Side_Copper_03();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



