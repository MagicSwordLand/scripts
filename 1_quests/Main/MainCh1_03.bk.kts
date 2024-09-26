@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.MoveToObjective
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player




val questID = "main_ch1_03"

class MainCh1_03: Quest(questID,"§7[§c主線委託§7] §f第一章-高級任務"){


    val playerIcon = "%squests_icon%";
    val namoIcon = IconFonts.getNPC(4);
    val seanIcon = IconFonts.getNPC(6);
    val minnoIcon = IconFonts.getNPC(14);
    val mayleeIcon = IconFonts.getNPC(59);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val minnoNPC = "§c● §e明諾克德§r:";
    private val mayleeNPC = "§c● §e梅里§r:";
    private val namoNPC = "§c● §e那莫斯克§r:";
    private val seanNPC = "§c● §e肯特§r:";

    private val mes1 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那莫斯克",
                    "§x§d§6§c§1§a§e我又完成好多任務啦~",
            ),
            *Utils.getMessage(namoIcon,
                    "$namoNPC",
                    "&x&d&6&d&0&b&8挺快的嘛",
                    "&x&d&6&d&0&b&8感覺怎麼樣",
                    "&x&d&6&d&0&b&8好玩嗎?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e怎麼樣~",
                    "§x§d§6§c§1§a§e有沒有更像一個合格的傭兵啦?",
            ),
            *Utils.getMessage(namoIcon,
                    "$namoNPC",
                    "&x&d&6&d&0&b&8哈哈哈",
                    "&x&d&6&d&0&b&8果然你們守護者一族都是這樣",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e甚麼意思??",
            ),
            *Utils.getMessage(namoIcon,
                    "$namoNPC",
                    "&x&d&6&d&0&b&8沒什麼",
                    "&x&d&6&d&0&b&8接下來要不要試著接高級一點的任務?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e高級一點的任務阿…",
                    "§x§d§6§c§1§a§e感覺會很有難度呢！",
                    "§x§d§6§c§1§a§e想想就覺得刺激",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那就這個吧",
                    "§x§d§6§c§1§a§e鐵匠的委託",
                    "§x§d§6§c§1§a§e好像要收集東西呢",
            ),
            *Utils.getMessage(namoIcon,
                    "$namoNPC",
                    "&x&d&6&d&0&b&8喔?肯特阿",
                    "&x&d&6&d&0&b&8他就在酒館外的鐵匠鋪喔",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e太好了，我這就去找他!",
            )
    )
    private val mes2 = arrayOf(
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8恩?是新來的傭兵嗎?",
                    "&x&d&6&d&0&b&8要幹嘛?買武器嗎?",
                    "&x&d&6&d&0&b&8還是要強化裝備",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e不…",
                    "§x§d§6§c§1§a§e我是來向您打聽關於委託任務的",
            ),
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8喔?那個委託任務嗎",
                    "&x&d&6&d&0&b&8你應該對三國的戰爭有所耳聞吧?",
            ),
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8由於戰爭波及到我們這些周邊的城鎮",
                    "&x&d&6&d&0&b&8導致一些素材急遽短缺",
                    "&x&d&6&d&0&b&8也讓我們這些工匠的材料供不應求了",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e具體…是甚麼材料阿?",
            ),
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8是一種特殊黏液",
                    "&x&d&6&d&0&b&8他來自於毒氣林的一種怪物",
            ),
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8就在出村後的右側",
                    "&x&d&6&d&0&b&8由於有著毒氣的影響",
                    "&x&d&6&d&0&b&8許多傭兵都不敢前往",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e原來如此…",
                    "§x§d§6§c§1§a§e是甚麼怪物阿?",
            ),
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8其實我也不了解",
                    "&x&d&6&d&0&b&8通常都是由供貨商提供的",
                    "&x&d&6&d&0&b&8但如今供貨商手上的材料也不夠了",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這樣啊..",
                    "§x§d§6§c§1§a§e但是了解不足很容易出現意外的",
                    "§x§d§6§c§1§a§e我先去探查一下情報吧",
            )
    )
    private val mes3 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e鎮長，我想打聽有關毒氣林的事情",
            ),
            *Utils.getMessage(minnoIcon,
                    "$minnoNPC",
                    "&x&d&6&d&0&b&8毒氣林嗎...",
                    "&x&d&6&d&0&b&8那有著讓人神經麻痺的草藥和毒氣呢",
                    "&x&d&6&d&0&b&8也有著身富劇毒的怪物",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那有沒有那種會產生黏液的怪物呢?",
            ),
            *Utils.getMessage(minnoIcon,
                    "$minnoNPC",
                    "&x&d&6&d&0&b&8黏液嘛…",
                    "&x&d&6&d&0&b&8啊!有的",
                    "&x&d&6&d&0&b&8是一種方方正正的怪物",
            ),
            *Utils.getMessage(minnoIcon,
                    "$minnoNPC",
                    "§x§d§6§c§1§a§e你要…去毒氣林嘛?",
                    "§x§d§6§c§1§a§e那你要特別小心",
                    "§x§d§6§c§1§a§e在那的毒素會令你神經麻痺",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e知道了！",
                    "§x§d§6§c§1§a§e鎮長，太謝謝你了！",
            )
    )
    private val mes4 = arrayOf(
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8不錯阿小子",
                    "&x&d&6&d&0&b&8沒想到你能完成這個委託",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e哈哈哈，那有甚麼",
                    "§x§d§6§c§1§a§e太簡單啦!",
            ),
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8你口氣不小阿",
                    "&x&d&6&d&0&b&8不過你可真幫了我大忙阿",
            ),
            *Utils.getMessage(seanIcon,
                    "$seanNPC",
                    "&x&d&6&d&0&b&8以後有任何裝備問題和需求",
                    "&x&d&6&d&0&b&8都可以來找我，我可是專業的！",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e真是太謝謝你了",
            )
    )
    private val mes5 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e梅里~我又來啦",
                    "§x§d§6§c§1§a§e想看看有沒有甚麼更厲害的委託~!",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8村子裡暫時也沒什麼更大的委託了",
                    "&x&d&6&d&0&b&8對了，你想不想跟行商隊伍一起去薩哈鎮看看",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8那是一個隸屬於尤葛帝國最大的沙漠城鎮",
                    "&x&d&6&d&0&b&8沙漠有著更高級的戰利品也有更強大的敵人！",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那感覺再適合不過啦!",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8不過行商的過程還是很危險的",
                    "&x&d&6&d&0&b&8等你更強一點再來找我吧！",
            )
    )
    init {
        cancelAble = false
        val obj0 = ReachLevelObjective("obj0", 5)
                .setInstruction("§75等後將繼續任務");
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",4,*mes1)
                .setInstruction("§7與 §e那莫斯克 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",6,*mes2)
                .setInstruction("§7與 §e肯特 §7對話");
        val obj3 = ListenNPCObjective(this,"obj3",14,*mes3)
                .setInstruction("§7與 §e明諾克德 §7對話");
        val obj4 = MoveToObjective(this,"obj4",Location(world,-453.44, 87.00, 417.70),5.0)
                .setInstruction("§7前往毒氣林");
        val obj5 = GiveItemObjective(this,"obj5",6,"MATERIAL:M005",8)
                .setInstruction("§7將 製毒黏液x8 交給 §e肯特")
        val obj6 = ListenNPCObjective(this,"obj6",6,*mes4)
                .setInstruction("§7與 §e肯特 §7對話");
        val obj7 = ListenNPCObjective(this,"obj7",59,*mes5)
                .setInstruction("§7與 §f梅里 §7對話");

        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7)

        addRewards(
                MoneyReward(50), //30錢幣
                QuestExpReward(20), //
                ClassExpReward(100),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch2_01")
        })

    }
}


val quest = MainCh1_03();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



