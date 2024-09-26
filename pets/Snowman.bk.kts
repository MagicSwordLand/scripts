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

class Snowman: HeroesPet("snowman","聖誕雪人"), UpgradeablePet {


    init {
        display = "聖誕雪人"
        initTier = 2
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 聖誕雪人"
        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTI3Njk1YmVlMmJhYmY5YmFlZjExYWUxOWZlNWY4MTdmYTA4OGNkNTJjNGM0NzNjYzdiNDQxZDVjM2Y1MGZlIn19fQ==")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f聖誕雪人 §7Lv.${pet.level}")
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
            add("§7§l愛玩的孩子們總是在雪地堆出雪人")
            add("§7§l而聖誕的氣息與雪人結合")
            add("§7§l誕生了這個可愛的雪人寵物")
            add("")
            add("&7➤ + ${damageBuff(pet)} 攻擊傷害")
            add("&7➤ + ${expBuff(pet)} 最大血量")
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
        return Math.floor(pet.level/1.5);
    }
    private fun damageBuff(pet: Pet): Double{
        return (pet.level/2.0);
    }
    override fun onTick(pet: SpawnedPet, ticks: Long) {
        // 每個 Tick 要做的事
        if(ticks % (20*20) == 0L){

            if (pet.pet.level>=30){
                pet.owner.addPotionEffect(PotionEffect(PotionEffectType.SPEED,100,0))
                pet.owner.sendMessage("§7[§x§B§E§9§B§7§B寵物§7] §f聖誕雪人施展能力為主人加速")
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
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",expBuff(spawnedPet.pet),modifierKey)
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"ATTACK_DAMAGE",damageBuff(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",modifierKey);
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"ATTACK_DAMAGE",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(Snowman());