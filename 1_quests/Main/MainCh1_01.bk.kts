@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.objectives.ConversationObjective
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.objectives.MoveToObjective
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location

val playerIcon = "%squests_icon%";


val questID = "main_ch1_01"

class MainCh1_01: Quest(questID,"§7[§c主線委託§7] §f第一章-見習傭兵"){

    val greyIcon = IconFonts.getNPC(64);
    val yankoIcon = IconFonts.getNPC(5);
    val namoIcon = IconFonts.getNPC(4);
    val minnoIcon = IconFonts.getNPC(14);
    val yuliIcon = IconFonts.getNPC(60);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val greyNPC = "§c● §e葛雷§r:";
    private val yankoNPC = "§c● §e焱恪§r:";
    private val namoNPC = "§c● §e那莫斯克§r:";
    private val minnoNPC = "§c● §e明諾克德§r:";
    private val yuliNPC = "§c● §e尤莉§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8對了，你應該沒來過這吧",
            "&x&d&6&d&0&b&8這裡是諾亞鎮，算是我們的一個中繼站",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e沒想到除了我們村子還會有其他仙境一般的村莊",
        ),
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8哈哈哈，沒想到你會這那麼喜歡",
            "&x&d&6&d&0&b&8等你和我們回到帝國說不定你會更喜歡",
            "&x&d&6&d&0&b&8對了，聽說傭兵公會長已經到酒館等著了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e傭兵公會長?那是誰啊",
        ),
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8去了你就知道了",
        ),
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8不過在那之前",
            "&x&d&6&d&0&b&8我先帶你去幾個傭兵必須知道的地方",
            "&x&d&6&d&0&b&8就先去烘焙店吧！",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8哪來的新面孔...",
            "&x&d&6&d&0&b&8阿!你就是葛雷帶回來的新人吧?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是…是的!會長您好!",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8我問你，你為甚麼來到傭兵團? ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e為了…為了報恩!",
            "§x§d§6§c§1§a§e還有賺到能夠養活自己的錢",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8哈哈哈！真誠實",
            "&x&d&6&d&0&b&8你這小子行阿，合格了",
            "&x&d&6&d&0&b&8做傭兵的好處，你慢慢就能體會了",
        ),
        *Utils.getMessage(yankoIcon,
            "$yankoNPC",
            "&x&d&6&d&0&b&8接下來可以準備去接接任務實習實習",
            "&x&d&6&d&0&b&8那莫斯克，他就交給你了",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8知道了！",
        )
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8看來會長很中意你呢",
            "&x&d&6&d&0&b&8好啦，現在應該先教你身為傭兵應該要做的事",
            "&x&d&6&d&0&b&8首先呢…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e去接懸賞對吧!",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8哦…對，但是對你來說還太早了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e要選哪種好呢~",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8你認真聽我說啦",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不用阿，手冊都有說",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8甚麼手冊??",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就是剛剛那個人給我的…",
            "§x§d§6§c§1§a§e??那個人哪去了",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8所以我說，你還是給我認認真真的聽吧!",
            "&x&d&6&d&0&b&8先選一個對你而言最簡單的懸賞吧",
            "&x&d&6&d&0&b&8我看看…這個怎麼樣",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8替村民尤莉找回失竊的錢包",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼聽起來那麼簡單",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8你可別小瞧任何一個委託任務阿",
            "&x&d&6&d&0&b&8這就是傭兵接觸最多的委託類型",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e總之我們先去找找尤莉吧",
            "§x§d§6§c§1§a§e恩…茫茫人海中我該怎麼找到他阿…",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8還是先帶你去找鎮長熟悉一下村子的環境吧",
        )
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(minnoIcon,
            "$minnoNPC",
            "&x&d&6&d&0&b&8唉呀，這位是…?",
        ),
        *Utils.getMessage(namoIcon,
            "$namoNPC",
            "&x&d&6&d&0&b&8鎮長先生，這位是新來的傭兵",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e鎮長先生你好，我是來自守護者村的傭兵",
        ),
        *Utils.getMessage(minnoIcon,
            "$minnoNPC",
            "&x&d&6&d&0&b&8原來如此阿，找我有甚麼事?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e鎮長我想問問尤莉小姐的住所…",
        ),
        *Utils.getMessage(minnoIcon,
            "$minnoNPC",
            "&x&d&6&d&0&b&8尤莉小姐…他平常都在葡萄園工作呢",
            "&x&d&6&d&0&b&8也許這時間他正在工作",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e啊!謝謝您呀，鎮長先生",
        ),
        *Utils.getMessage(minnoIcon,
            "$minnoNPC",
            "&x&d&6&d&0&b&8希望你能一直保持著這樣的滿腔熱血阿!",
            "&x&d&6&d&0&b&8如果還有甚麼問題都可以來找我",
        )
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問…你是尤莉小姐嗎?",
        ),
        *Utils.getMessage(yuliIcon,
            "$yuliNPC",
            "&x&d&6&d&0&b&8是…!是的! ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我想問問有關你的委託",
        ),
        *Utils.getMessage(yuliIcon,
            "$yuliNPC",
            "&x&d&6&d&0&b&8你是說我的錢包嗎…?",
            "&x&d&6&d&0&b&8先前我在工作時…被一群怪物所襲擊",
            "&x&d&6&d&0&b&8雖然我並未受傷，卻被奪走了錢包…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e為甚麼那些怪物的目標是錢包呢…",
        ),
        *Utils.getMessage(yuliIcon,
            "$yuliNPC",
            "&x&d&6&d&0&b&8也許是因為裡面的護身符",
            "&x&d&6&d&0&b&8那是祖輩世世代代所流傳下來的",
            "&x&d&6&d&0&b&8也是我父親…生前囑託我必須守護的東西",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那..那群怪物的行蹤…",
        ),
        *Utils.getMessage(yuliIcon,
            "$yuliNPC",
            "&x&d&6&d&0&b&8我也不清楚，但也許是往洞穴中前進了",
            "&x&d&6&d&0&b&8雖然他們並不強悍",
            "&x&d&6&d&0&b&8但我們這群手無寸鐵的村民仍對他們毫無還手之力",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e放心的，接下來就交給我們吧",
        )
    )

    private val mes6 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好潮濕的味道阿…",
            "§x§d§6§c§1§a§e真難聞…不過這真的會有甚麼怪物嗎?",
            "§x§d§6§c§1§a§e好像真的有呢…也許東西就在他們身上",
        )
    )

    private val mes8 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e尤莉小姐，是這個錢包沒錯吧",
            "§x§d§6§c§1§a§e這麼一來懸賞任務也完成了",
        ),
        *Utils.getMessage(yuliIcon,
            "$yuliNPC",
            "&x&d&6&d&0&b&8是的!真的非常非常感謝您!",
            "&x&d&6&d&0&b&8這是懸賞所要給予的獎賞",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e謝謝啦~",
            "§x§d§6§c§1§a§e這點歷練還不夠呢，我得多做點懸賞",
            "§x§d§6§c§1§a§e才能賺足夠的錢累積足夠的歷練!",
        )
    )

    private val mes9 = arrayOf(
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8這邊是烘焙店",
            "&x&d&6&d&0&b&8你餓的時候可以來這購買",
            "&x&d&6&d&0&b&8或者委託製作食物",
        ),
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8趕了這麼久的路",
            "&x&d&6&d&0&b&8你應該餓了吧?",
            "&x&d&6&d&0&b&8這些就給你了，不要太感謝我",
        ),
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8接下來就是對面的鐵匠鋪",
        )
    )
    private val mes10 = arrayOf(
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8這邊是鐵匠鋪",
            "&x&d&6&d&0&b&8可以在這強化、洗鍊、修補你的裝備及武器",
            "&x&d&6&d&0&b&8這可是非常重要的！",
        ),
        *Utils.getMessage(greyIcon,
            "$greyNPC",
            "&x&d&6&d&0&b&8接下來我們就去找焱恪吧！",
        )
    )

    init {
        cancelAble = false
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .disableWalk(false)
            .setInstruction("§7與 §e葛雷 §7對話");
        val obj2 = MoveToObjective(this,"obj2", Location(world,-306.26, 68.06,311.65),5.0)
            .setInstruction("前往 烘焙店")
        val obj3 = ListenTalkObjective(this,"obj3",*mes9)
            .setInstruction("查看四周")
            .setEndProcess {
                it.sendTitle("§7[§a獲得物品§7]","§f白麵包x32")
                it.sendMessage("§7[§c提示§7] §f新手飢餓度不夠時可以到烘焙店購買食物，或者到稻草田採集稻草後委託npc製作白麵包")
                Utils.command("get ${it.name} FOOD F001 32")};
        val obj4 = MoveToObjective(this,"obj4", Location(world,-303.27, 68.00,330.65),5.0)
            .setInstruction("前往 鐵匠鋪");
        val obj5 = ListenTalkObjective(this,"obj5",*mes10)
            .setInstruction("查看四周");
        val obj6 = ListenNPCObjective(this,"obj6",5,*mes2)
            .setInstruction("與 §e焱恪 §7對話");
        val obj7 = ListenNPCObjective(this,"obj7",4,*mes3)
            .setInstruction("與 §e那莫斯克 §7對話");
        val obj8 = ListenNPCObjective(this,"obj8",14,*mes4)
            .setInstruction("與 §e明諾克德 §7對話")
            .setEndProcess {
                it.sendMessage("§7[§c提示§7] §f使用/sq list->主線委託->點選任務,即可追蹤任務目標位置")
                it.sendMessage("§7[§c提示§7] §f使用/nv即可查看更多新手提示")};
        val obj9 = ListenNPCObjective(this,"obj9",60,*mes5)
            .setInstruction("前往 葡萄園 與 §e尤莉 §7對話");
        val obj10 = MoveToObjective("obj10",Location(world,-444.82, 58.00, 470.93))
            .setInstruction("前往水窟洞");
        val obj11 = ListenTalkObjective(this,"obj11",*mes6);
        val obj12 = KillMobsObjective(this,"obj12","水窟洞_",10)
            .setInstruction { "擊殺 ${it.amount} / 10 隻 水窟洞中的怪物" }
            .setLocation(Location(world,-444.82, 58.00, 470.93))
            .setEndProcess {
                it.sendTitle("§7[§a獲得物品§7]","§f尤莉的錢包")
                it.sendMessage("若弄丟任務物品，請放棄任務，找葛雷重新接取")
                it.sendMessage("§7[§c提示§7] §f素材可至傭兵公會左側的傭兵交易員販賣")
                Utils.command("mi give QUESTMATERIAL QM010 ${it.name}")

            };
        val obj13 = GiveItemObjective(this,"obj13",60,"QUESTMATERIAL:QM010",1)
            .setInstruction{"交還尤莉的錢包給 §e尤莉 ${it.amount}/1"}
        val obj14 = ListenTalkObjective(this,"obj14",*mes8).disableWalk(true)
            .setInstruction("與 §e尤莉 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12,obj13,obj14)

        addRewards(
            MoneyReward(30), //30錢幣
            QuestExpReward(15), //15傭兵聲望
            ClassExpReward(100),
            MessageReward("&a➯ &7飾品: &c微弱生命的戒指"),
            MessageReward("&a➯ &7裝備: &7有破損的皮帽"),
            MessageReward("&a➯ &7裝備: &7有破損的皮盔甲"),
            MessageReward("&a➯ &7裝備: &7有破損的皮護腿"),
            MessageReward("&a➯ &7裝備: &7有破損的皮鞋"),
            MessageReward("&a➯ &7進階: &7銅牌傭兵"),
            MessageReward("&a➯ &7系統: &7倉庫解鎖16格"),
            MessageReward("&a➯ &7稱號: §7[§x§e§4§d§7§d§1傭兵新人§7]"),
        )
        addEndHook({
            Utils.command("get ${it.name} RING A001 1 0")
            Utils.command("get ${it.name} ARMOR A001 1 0")
            Utils.command("get ${it.name} ARMOR A002 1 0")
            Utils.command("get ${it.name} ARMOR A003 1 0")
            Utils.command("get ${it.name} ARMOR A004 1 0")
            Utils.command("lp user ${it.name} parent add lv1")
            Utils.command("bbroadcast &7[&x&A&9&C&7&E&D階級&7] &7傭兵 &f${it.name} &7成功升階為 &7銅牌傭兵")
            Utils.command("sq start ${it.name} main_ch1_02")
            Utils.command("storage addunlocked ${it.name} 16")
            Utils.command("lp user ${it.name} permission set ps.suffix.1")
        })
    }
}


val quest = MainCh1_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



