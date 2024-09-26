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

class snowowl: HeroesPet("snowowl","雪地貓頭鷹"), UpgradeablePet {


    init {
        display = "雪地貓頭鷹"
        initTier = 2
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 雪地貓頭鷹"
        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY4Yjk0MTJiY2QxNTYwYzQ2MjlhYzdjZjFkNmUyZDcxZGE2NGUxNzQzOGRlMGQ4OGM5NTIzYmMxMmRlZmM4ZSJ9fX0=")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f雪地貓頭鷹 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &b稀有")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l隨著天氣漸漸變冷")
            add("§7§l住在雪地的生物也漸漸開始活動")
            add("§7§l雪地貓頭鷹則是其中較為特別的生物")
            add("§7§l只要獲得牠認可的主人")
            add("§7§l牠就會提供非比尋常的能力")
            add("")
            add("&7➤ + ${damageBuff(pet)} 攻擊魔傷")
            add("&7➤ + ${expBuff(pet)} 最大魔力")
            if(pet.level>=30){
                add("&7➤ &7等級達30等時每20秒獲得 加速I 5秒")}
            else{
                add("&7➤ &8等級達30等時每20秒獲得 加速I 5秒")
            }

        }


        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }

    private fun expBuff(pet: Pet): Double{
        return (pet.level/2.0);
    }
    private fun damageBuff(pet: Pet): Double{
        return Math.floor(pet.level/3.0);
    }
    override fun onTick(pet: SpawnedPet, ticks: Long) {
        // 每個 Tick 要做的事
        if(ticks % (20*20) == 0L){

            if (pet.pet.level>=30){
                pet.owner.addPotionEffect(PotionEffect(PotionEffectType.SPEED,100,0))
                pet.owner.sendMessage("§7[§x§B§E§9§B§7§B寵物§7] §f雪地貓頭鷹施展能力為主人加速")
            }
        }
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
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MAX_MANA",expBuff(spawnedPet.pet),modifierKey)
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MG_WEAPON_DAMAGE",damageBuff(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MAX_MANA",modifierKey);
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MG_WEAPON_DAMAGE",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(snowowl());