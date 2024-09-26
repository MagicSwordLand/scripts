@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player




val questID = "main_ch2_02"

class MainCh2_02: Quest(questID,"§7[§c主線委託§7] §f第二章-女巫的訴求"){


    val playerIcon = "%squests_icon%";
    val crayliaIcon = IconFonts.getNPC(105);
    val craymiaIcon = IconFonts.getNPC(105);
    val jeebooIcon = IconFonts.getNPC(121);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val crayliaNPC = "§c● §e克雷利亞§r:";
    private val craymiaNPC = "§c● §e克雷米亞§r:";
    private val jeebooNPC = "§c● §e吉布§r:";
    private val commustonNPC = "§c● §e克雷利亞(通訊石)§r:";


    private val mes1 = arrayOf(
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8少年，你怎麼會出現在這?",
                    "&x&d&6&d&0&b&8我們認識嗎?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e我是替雇主...尤爾達先生前來",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8尤爾達?那個行商的蠢貨?",
                    "&x&d&6&d&0&b&8他是叫你來跟我討毒藥的嗎?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e阿...是的",
                    "§x§d§6§c§1§a§e他說這毒藥對他的用處很大",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8哼，廢話",
                    "&x&d&6&d&0&b&8不過也不能讓你們白拿",
                    "&x&d&6&d&0&b&8這樣吧！",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8你們得替我去石靈之森",
                    "&x&d&6&d&0&b&8那裡的元素魔像身上存在巨石核素",
                    "&x&d&6&d&0&b&8把他取回來給我",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e元素魔像...聽上去挺強的",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8確實，但他只是笨重的石頭",
                    "&x&d&6&d&0&b&8並沒有想像中的可怕",
                    "&x&d&6&d&0&b&8這個給你",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8這是通訊石，你沒學過傳聲魔法吧",
                    "&x&d&6&d&0&b&8這可以讓我向你傳達消息",
                    "&x&d&6&d&0&b&8我也能看到你附近的視角",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e痾...恩...",
                    "§x§d§6§c§1§a§e好吧！就交給我了",
            ),
    )

    private val mes2 = arrayOf(
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8看來今天那些小傢伙都很不安分阿",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e怎麼那麼多的山巨人和猛牛阿!",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8聽我說",
                    "&x&d&6&d&0&b&8你得解決他們才能見到元素魔像",
            ),
    )
    private val mes3 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e感覺怪物都越來越難纏了呢...",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8別廢話了，接下來就得解決元素魔像",
                    "&x&d&6&d&0&b&8解決它並從它身上得到核心吧！",
            ),
    )
    private val mes4 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e拿到了！",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8太好了！再來幫我拿去給我妹妹",
                    "&x&d&6&d&0&b&8克雷米亞，他是沉寂女巫",
                    "&x&d&6&d&0&b&8就住在低語之森外的一棟小屋",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這...好吧...",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8他獨自住在低語森林外，救贖那些迷失的亡魂",
                    "&x&d&6&d&0&b&8你見到她之後，她會再交代你剩下的事",
            ),
    )

    private val mes5 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這裡可沒想像中的陰森阿?",
                    "§x§d§6§c§1§a§e有沒有人在阿!",
                    "§x§d§6§c§1§a§e沒有人回答...",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8上來吧(小聲)",
            )
    )
    init {
        cancelAble = false
        //val obj0 = ReachLevelObjective(this,"obj0", 999)
        //  .setInstruction("§c該任務尚未開放，開放後將自動解鎖");
        val obj0 = ReachLevelObjective("obj0", 12)
                .setInstruction("§712等後將繼續任務");
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",100,*mes1)
                .setInstruction("§7與 &e克雷利亞 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,-224.44, 64.00, 13.70),10.0)
                .setInstruction("§7前往石靈之森");
        val obj3 = ListenTalkObjective(this,"obj3",*mes2)
                .setInstruction("§7聆聽通訊時發出的聲音");
        val obj4 = KillMobsObjective(this,"obj4","石靈森林_",10)
                .setInstruction { "擊殺 ${it.amount} / 10 個石靈森林的怪物" };
        val obj5 = ListenTalkObjective(this,"obj5",*mes3)
                .setInstruction("§7聆聽通訊時發出的聲音");
        val obj6 = KillMobsObjective(this,"obj6","石靈森林_元素魔像",1)
                .setInstruction { "擊殺 ${it.amount} / 1 個元素魔像" }
                .setEndProcess {
                    it.sendTitle("§7[§a獲得物品§7]","§f巨石核素")
                    it.sendMessage("§7[§c提示§7] §f若弄丟任務物品，元素魔像也會掉落相同物品")
                    Utils.command("mi give BOSSMATERIAL M004 ${it.name}")};
        val obj7 = ListenTalkObjective(this,"obj7",*mes4)
                .setInstruction("§7聆聽通訊時發出的聲音");
        val obj8 = MoveToObjective(this,"obj8",Location(world,-484.66, 66.00, 92.56),5.0)
                .setInstruction("§7前往克雷米亞的住所");
        val obj9 = ListenTalkObjective(this,"obj9",*mes5)
                .setInstruction("§7搜索一下周遭");

        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9)

        addRewards(
                MoneyReward(50), //30錢幣
                QuestExpReward(30), //15傭兵聲望
                ClassExpReward(200),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch2_03")
        } )

    }
}


val quest = MainCh2_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



