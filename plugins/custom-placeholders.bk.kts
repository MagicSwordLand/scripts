@file:RequiredPlugins("PlaceholderAPI", "WorldGuard", "WorldEdit", "AltSkill2")

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.protection.regions.ProtectedRegion
import io.github.clayclaw.altskill2.mana.getManaEntity
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import java.util.HashMap
import kotlin.math.roundToInt

val aliasMap: HashMap<String, String> = hashMapOf(
    "test" to "高端地區",
    "noah" to "§x§a§8§e§6§c§f諾亞鎮",
    "mobs01" to "§x§8§7§B§F§B§A水窟洞",
    "mobs02" to "§x§d§b§d§7§c§f彼遺古岸",
    "mobs03" to "§x§6§A§9§0§6§0哥布林部落",
    "mobs04" to "§x§A§5§C§C§A§5毒氣林",
    "mobs05" to "§x§8§9§9§4§9§F盜賊據點",
    "mobs06" to "§x§b§2§d§8§d§8石靈森林",
    "mobs07" to "§x§3§1§7§8§7§3低語森林",
    "mobs08" to "§x§c§7§b§b§c§9艾靈長廊",
    "mobs09" to "§x§f§0§e§2§a§8砂岩灘地",
    "mobs10" to "§x§f§7§c§1§9§b穹頂沙淵",
    "mobs11" to "§x§f§b§e§7§a§1阿努比斯神殿",
    "mobs12" to "§x§x§9§6§6§1§3§d墓地迷域",
    "mobs13" to "§x§5§0§6§9§6§e迷離山岩",
    "mobs14" to "§x§f§c§a§7§8§f紅砂山脈",
    "mobs15" to "§x§a§f§c§f§b§5沙原綠洲",
    "mobs16" to "§x§f§a§f§c§b§d暮光烈谷",
    "mobs17" to "§x§8§d§9§0§3§9蛛菇沙原",
    "mobs18" to "§x§7§2§5§6§7§c紫晶山丘",
    "mobs19" to "§x§a§9§8§9§d§3比莉亞達塔",
    "mobs20" to "§x§a§a§f§f§f§5急流冰川",
    "mobs21" to "§x§b§b§f§0§e§a漂流浮冰",
    "mobs22" to "§x§4§5§8§1§8§E冰雪山谷",
    "mobs23" to "§x§6§F§A§8§D§C寒悚冰宮",
    "mobs24" to "§x§B§0§B§B§C§1笞纂廢墟",
    "mobs25" to "§x§9§8§9§4§8§C廢棄城堡",
    "mobs26" to "§x§B§E§C§D§C§0高原遺跡",
    "mobs27" to "§x§E§F§B§8§8§0鐵巨人遺跡",
    "noahgrape" to "§5大葡萄園",
    "noahgrape2" to "§5小葡萄園",
    "noahmineentrance" to "§7諾亞礦場門口",
    "noahmine" to "§7諾亞礦場",
    "noahdeadvilage" to "§a低語村",
    "noahmine2" to "§7低語礦場",
    "noahpeir" to "§6東部碼頭",
    "noahguild" to "§x§E§F§B§8§8§0月光酒館",
    "sahabar" to "§e薩哈酒館",    
    "noahmine3" to "§7古岸礦場",
    "noahwheat" to "§x§a§8§e§6§c§f諾亞鎮§6大稻田",
    "noahwheat2" to "§x§a§8§e§6§c§f諾亞鎮§6小稻田", 
    "noahwheat3" to "§x§a§8§e§6§c§f諾亞鎮§6小稻田", 
    "noahdeadvillage" to "§a低語村",     
    "noahmine4" to "§7彼遺礦場",   
    "noahmine5" to "§7舊址礦場", 
    "noahbluecliff" to "§a蒼淵幽谷",
    "noaholdtown" to "§7諾亞鎮舊址",   
    "noahmine6" to "§x§b§2§d§8§d§8石靈礦場",   
    "exp" to "§x§b§2§d§8§d§8諾亞溫泉",  
    "sahacity" to "§x§8§d§9§0§3§9阿納斯塔城",
)
/**
 * 優先度設定表
 * 可以設定負值，沒設定的區域預設為 0
 * 最高優先度的會被顯示
 */
val priorityMap: HashMap<String, Int> = hashMapOf(
    "test" to 1,
    "mobs01" to 1,
    "mobs02" to 1,
    "mobs03" to 1,
    "mobs04" to 1,
    "mobs05" to 1,
    "mobs06" to 1,
    "mobs07" to 1,
    "mobs08" to 1,
    "mobs09" to 1,
    "mobs10" to 1,
    "mobs11" to 1,
    "mobs12" to 1,
    "mobs13" to 1,
    "mobs14" to 1,
    "mobs15" to 1,
    "mobs16" to 1,
    "mobs17" to 1,
    "mobs18" to 1,
    "mobs19" to 1,
    "mobs20" to 1,
    "mobs21" to 1,
    "mobs22" to 1,
    "mobs23" to 1,
    "mobs24" to 1,
    "mobs25" to 1,
    "mobs26" to 1,
    "mobs27" to 1,    
    "noahguild" to 1, 
    "noaholdtown" to 1, 
    "noahbluecliff" to 2,    
    "noahmine5" to 1,     
    "noahwheat" to 2,     
    "noahwheat2" to 2,    
    "noahwheat3" to 2,     
    "noahmine4" to 2,     
    "noahmine6" to 2,    
    "noahgrape2" to 2,
    "exp" to 2,
    "sahacity" to 1,
    
)

class ScriptExpansion(
    private val placeholderMap: HashMap<String, (Player) -> String?> = hashMapOf()
): PlaceholderExpansion() {

    override fun getIdentifier(): String {
        return "script"
    }

    override fun getAuthor(): String {
        return "ButterJar#0149"
    }

    override fun getVersion(): String {
        return "0.0.1"
    }

    override fun onPlaceholderRequest(player: Player?, params: String): String? {
        return player?.let { placeholderMap[params]?.invoke(it) }
    }

    operator fun invoke(id: String, registering: (Player) -> String?) {
        placeholderMap[id] = registering
    }

}

val expansion = ScriptExpansion()
expansion("wg-region-alias") { p ->
    aliasMap.getOrDefault(getPlayerRegionId(p) ?: "", "野外")
}
expansion("mana-rounded") { p ->
	val mana = p.getManaEntity()?.getManaStored() ?: 0.0
    "${mana.roundToInt()}"
}
expansion.register()

fun getPlayerRegionId(player: Player): String? {
    val query = WorldGuard.getInstance().platform.regionContainer.createQuery()
    val regions = query.getApplicableRegions(BukkitAdapter.adapt(player.location))?.regions ?: setOf()
    return findHighestPriority(regions)?.id
}

fun findHighestPriority(input: Set<ProtectedRegion>): ProtectedRegion? {
    var highestPriority: Int = Integer.MIN_VALUE
    var bestRegion: ProtectedRegion? = null
    input.forEach {
        val priority = priorityMap.getOrDefault(it.id, 0)
        if(priority >= highestPriority) {
            highestPriority = priority
            bestRegion = it
        }
    }
    return bestRegion
}

fun onDispose() {
    expansion.unregister()
}