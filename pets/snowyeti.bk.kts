@file:RequiredPlugins("HeroesPets","PlayerDataSync","HeroesSkills","HeroesPetUpgrading")
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroespet.upgrading.models.UpgradeablePet
import net.brian.heroespet.upgrading.utils.PetExpGlobalWhiteList
import net.brian.heroespets.HeroesPets
import net.brian.heroespets.api.pets.HeroesPet
import net.brian.heroespets.api.pets.Pet
import net.brian.heroespets.api.pets.SpawnedPet
import net.brian.heroespets.bukkit.compatible.mythiclib.MythicStats
import net.brian.heroespets.bukkit.configs.configs.Language
import net.brian.heroespets.utils.SkullBuilder
import net.brian.playerdatasync.util.IridiumColorAPI
import net.kyori.adventure.title.Title
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class snowyeti: HeroesPet("snowyeti","凜冬雪怪"), UpgradeablePet {


    init {
        display = "凜冬雪怪"
        initTier = 2
        height = 4.0
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 凜冬雪怪"
        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY4Yjk0MTJiY2QxNTYwYzQ2MjlhYzdjZjFkNmUyZDcxZGE2NGUxNzQzOGRlMGQ4OGM5NTIzYmMxMmRlZmM4ZSJ9fX0=")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f凜冬雪怪 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &b稀有")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l有著強大生命力的雪怪")
            add("§7§l被傭兵公會發現後進行培育")
            add("§7§l後被發現有著濃厚的凜冬氣息")
            add("§7§l有著能使主人快速成長的能力")
            add("")
            add("&7➤ + ${expBuff(pet)}% 玩家經驗獲取")
            add("&7➤ + ${healBuff(pet)} 最大血量")

        }


        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }

    private fun expBuff(pet: Pet): Double{
        return Math.floor(pet.level/2.0);
    }
    private fun healBuff(pet: Pet): Double{
        return pet.level*1.5;
    }
    override fun onTick(pet: SpawnedPet, ticks: Long) {
    }
    override fun getWhiteLists(): MutableList<String> {
        val list = mutableListOf<String>();
        list.addAll(PetExpGlobalWhiteList.get());//全局通用果实，如果不要通用吃，就删掉这行
        list.add("A003");//专门吃
        list.add("A004");//专门吃
        list.add("A005");//专门吃
        return  list;
    }
    val modifierKey = "heroes-pet";
    override fun onSpawn(spawnedPet: SpawnedPet) {
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"ADD_CLASS_EXP",expBuff(spawnedPet.pet),modifierKey)
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",healBuff(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"ADD_CLASS_EXP",modifierKey);
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(snowyeti());