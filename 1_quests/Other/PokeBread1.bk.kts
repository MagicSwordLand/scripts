@file:RequiredPlugins("ScriptedQuests","PlayerDataSync")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.playerdatasync.util.IridiumColorAPI
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.conditions.FinishedQuestsCondition
import net.brian.scriptedquests.api.player.PlayerCompleter
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mmoitems.UseItemObjective
import net.brian.scriptedquests.compability.vault.EconomyProvider
import net.brian.scriptedquests.conversation.*;
import net.brian.scriptedquests.data.PlayerQuestDataImpl
import net.brian.scriptedquests.objectives.*;
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import org.bukkit.Bukkit
import org.bukkit.Location
import net.brian.scriptedquests.utils.Utils


val questID = "other_pokemonbread1"

val mainNPCQ = "§b● §e浦島太: "
val mainNPC = "§c● §e浦島太§r:"
val comicShop = "§b● &e盧克§r:"
val npc2Q = "§b● §e麵寶: "
val npc2= "§c● §e麵寶§r:"
val playerName = "§c● §f%player_name%§r:"
val world = Bukkit.getWorld("2k");
val mainNPCIcon = IconFonts.getNPC(50);
class PokeBread1: Quest(questID,"§7[§d特殊委託§7] §f神獸寶貝") {
    val playerIcon = "%squests_icon%";
    val mainNPCIcon = IconFonts.getNPC(50);
    val npc2Icon = IconFonts.getNPC(66);
    val comicShopIcon = IconFonts.getNPC(84);

    val mes1 = arrayOf(

        *Utils.getMessage(mainNPCIcon,
            "$mainNPC",
            "&x&d&6&d&0&b&8太好了",
            "&x&d&6&d&0&b&8[神受寶貝麵包] (附贈貼紙)就要隆重上架了",
            "&x&d&6&d&0&b&8我記得是以<神受寶貝>作爲藍本哦",
        ),
        *Utils.getMessage(mainNPCIcon,
            "$mainNPC",
            "&x&d&6&d&0&b&8你先去雜貨店買一本<神受寶貝> 帶給麵寶",
            "&x&d&6&d&0&b&8再跟他買一個 [神受寶貝麵包] 來看看怎麽樣吧",
        )
    )

    val mes2 = arrayOf(
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8神受寶貝兒~ 神受~神受~寶貝兒~傭兵們的最愛",
            "&x&d&6&d&0&b&8和主角曉智還有上百種神受們一起踏上旅途吧~",
            "&x&d&6&d&0&b&8咳咳，不好意思太興奮了，你要買些麵包嗎",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好啊，可以給我三塊麵包嗎？",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8好的，一共30個銅幣",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我擦，這麽貴，搶劫吖！？",
        )
    )


    val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e...",
            "§x§d§6§c§1§a§e怎麽回事啊！連續拿到了三張曉智的貼紙！",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8怎麽了，那不是很好嗎？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好個毛啊！曉智的貼紙到底占了幾成啊！",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8大概十成而已",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這不是神受世界嗎？怎麽會沒有神受的貼紙呢！",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8因為我除了曉智什麽都畫不好嘛",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e真敢往自己身上攬哪？一般不都是找專業的來畫麽？",
            "§x§d§6§c§1§a§e你個業餘的膽挺肥啊！",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8滾犢子，我可不是業餘的！",
            "&x&d&6&d&0&b&8去年還出過<神受寶貝>的同人志呢",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e真的假的，那你倒是給我畫點神受啊？",
            "§x§d§6§c§1§a§e神受才是這部漫畫的重點啊！不然誰會回購啊！",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8我心目中的<神受寶貝>只有曉智一個人的世界",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你心目中的世界觀根本就是個bug啊！",
        )
    )

    val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e另外還有一個事，請你看看這部漫畫...",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8啊~ 這不是<神受寶貝>嗎？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e翻開來，請你看看這一頁",
            "§x§d§6§c§1§a§e你瞅瞅，這是你畫的的貼紙上的曉智",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e然後呢...這是漫畫上的曉智",
            "§x§d§6§c§1§a§e我總覺得...不像...啊",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8我畫的曉智怎麽了？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e一點也不像啊，傭兵們看了會很失望啊！",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8這個沒關係，傭兵們對神受更感性趣，不是嗎？",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e知道的話，就多畫點神受的貼紙啊！",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8要是畫我不想畫的東西",
            "&x&d&6&d&0&b&8我就沒法把生命力表現出來~",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你就不能照著原版的漫畫來畫嗎？",
        ),
        *Utils.getMessage(npc2Icon,
            "$npc2",
            "&x&d&6&d&0&b&8可是如果畫得太像的話",
            "&x&d&6&d&0&b&8我們會因爲侵權被下架唷",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e下架嗎...確實搞不住啊...",
            "§x§d§6§c§1§a§e我也差不多回去找浦島太了 掰啦",
        )
    )

    val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你的麵包，贈送的貼紙只有曉智",
            "§x§d§6§c§1§a§e我怕這樣到時候銷售會很差",
        ),
        *Utils.getMessage(mainNPCIcon,
            "$mainNPC",
            "&x&d&6&d&0&b&8真的假的！曉智是我的Only Pick",
            "&x&d&6&d&0&b&8誰不喜歡他呢！一定會大賣的，放心~",
            "&x&d&6&d&0&b&8真希望他能做我男朋友啊",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e額…我是説誰不想啊…他這麽優秀",
            "§x§d§6§c§1§a§e那個…還有什麽可以幫忙的嗎？",
        ),
    )

    init {
        val start = ListenTalkObjective(this,"obj1",*mes1).disableWalk(true);

        val buyComic = ConversationObjective("BuyComic",84){
            NPCQuestion(*Utils.getMessage(comicShopIcon,
                "$comicShop",
                "§7小子來買漫畫的嗎？")).addPlayerOptions(
                PlayerOption("&7[&a對話選項&7] &f對阿，請給我一本 <神獸寶貝> 的漫畫 &8[消耗10$]")
                    .setResult { p->
                        if(EconomyProvider.withdraw(p,10f)){
                            Utils.giveItem(p,"QUESTMATERIAL","QM002",1)
                            it.finish(PlayerCompleter.getInstance(p))
                        }
                        else p.sendMessage("你沒有足夠的錢錢")
                    },
                PlayerOption("&7[&a對話選項&7] &f沒有，我再看一看")
            )
        }
            .setStartInstantly(false);
        buyComic.setInstruction("§7前往雜貨店購買一本神獸寶貝漫畫")


        val listenTalk1 = ListenNPCObjective(this,"obj4",66,*mes2)
            .setInstruction("§7前往麵包店購買3個<神獸寶貝麵包>")


        val obj5 = ConversationObjective("obj5",66){
            NPCQuestion(*Utils.getMessage(npc2Icon,
                "$npc2Q",
                "§7所以你是要還是不要")).addPlayerOptions(
                PlayerOption("&7[&a對話選項&7] &f好啦 我買 &8[消耗30$]")
                    .setResult { p->
                        if(EconomyProvider.withdraw(p,30f)){
                            Utils.giveItem(p,"QUESTMATERIAL","QM003",3)
                            it.finish(PlayerCompleter.getInstance(p))
                        }
                        else p.sendMessage("你沒有足夠的錢")
                    },
                PlayerOption("&7[&a對話選項&7] &f不要").setNpcResponse("$npc2 遺憾")
            )
        }
        obj5.setInstruction("§7向麵寶購買3個<神獸寶貝麵包>")
            .setEndProcess { it.sendMessage("§c● §f${it.name}: §7 一口氣買了三個，到底會抽到什麽樣的貼紙呢...") }
            .setInstruction("§7向麵寶購買3個神獸麵包")

        val eatBread = UseItemObjective("obj6","QUESTMATERIAL","QM003",3)
            .setInstruction { data -> "§7吃掉 ${data.amount}/3 個神獸麵包" }

        val listenTalk2 = ListenTalkObjective(this,"obj7",*mes3).disableWalk(true);

        val giveBook = GiveItemObjective(this,"giveBook",66,"QUESTMATERIAL:QM002", 1)
            .setInstruction("§7給麵寶看神獸寶貝漫畫")

        val listenTalk3 = ListenTalkObjective(this,"listen3",*mes4)
            .disableWalk(true)
            .setInstruction("§7與麵寶聊天")


        val listenTalk4 = ListenNPCObjective(this,"listen4",50,*mes5)
            .setInstruction("§7回去找蒲島太")

        val acceptBringBack = ConversationObjective("acceptBring",50){
            NPCQuestion(*Utils.getMessage(mainNPCIcon,
                "$mainNPCQ",
                "§7製作貼紙需要一些染料、粘稠劑和紙張",
                "§7可以帶一點給我嗎？"))
                .addPlayerOptions(
                    PlayerOption("&7[&a對話選項&7] &f包在我身上")
                        .setResult { player-> it.finish(PlayerCompleter.getInstance(player)) }
                        .setNpcResponse(*Utils.getMessage(mainNPCIcon,
                            "$mainNPCQ",
                            "§7太好了!我總共需要",
                            "§716 個染料、2 個粘稠劑、128 張",
                            "§7就再麻煩你了！")),
                    PlayerOption("&7[&a對話選項&7] &f不要 = =")
                        .setNpcResponse(*Utils.getMessage(mainNPCIcon,
                            "$mainNPCQ",
                            "§7好吧...",
                            "§7你願意的時候再來找我"))
                )
        }.setStartInstantly(true);
        acceptBringBack
            .setInstruction("§7接受蒲島太的請求")

        val bringPaper = GiveMultiItemObjective(this,"bringPaper",50,
            mapOf(
                "QUESTMATERIAL:QM005" to 16,
                "MATERIAL:M010" to 2,
                "QUESTMATERIAL:QM007" to 128
            )).setInstruction {
            "帶給蒲島太 ${it.get("QUESTMATERIAL:QM005")}/16 個染料 " +
                    "${it.get("MATERIAL:M010")}/2 個粘稠劑 " +
                    "${it.get("QUESTMATERIAL:QM007")}/128 張紙"
        }



        pushObjective(start,buyComic,listenTalk1,obj5,eatBread,listenTalk2,giveBook,listenTalk3,listenTalk4,
            acceptBringBack,bringPaper);
        addRewards(
            MoneyReward(60), //30錢幣
            QuestExpReward(20), //15傭兵聲望
            ClassExpReward(200),
        )
    }





}

val quest = PokeBread1();
ScriptedQuests.getInstance().questManager.register(quest);

ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(
    50,
    NPCQuestion(*Utils.getMessage(mainNPCIcon,
        "$mainNPC",
        "§7你是來試試新產品的嗎",
    )).addPlayerOptions(
        quest.getStartOption("&7[&a對話選項&7] &f是惹",false)
))

fun onDispose(){
    quest.unregister();
}