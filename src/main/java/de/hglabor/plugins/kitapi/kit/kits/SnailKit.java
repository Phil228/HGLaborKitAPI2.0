package de.hglabor.plugins.kitapi.kit.kits;

import de.hglabor.plugins.kitapi.kit.AbstractKit;
import de.hglabor.plugins.kitapi.kit.config.KitSettings;
import de.hglabor.plugins.kitapi.player.KitPlayer;
import de.hglabor.plugins.kitapi.util.ChanceUtils;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SnailKit  extends AbstractKit {

    private final static SnailKit instance = new SnailKit();

    protected SnailKit() {
        super("Snail", Material.SOUL_SOIL);
        addSetting(KitSettings.EFFECT_DURATION,4);
        addSetting(KitSettings.LIKELIHOOD, 25);
    }

    public static SnailKit getInstance() {
        return instance;
    }

    @Override
    public void onPlayerAttacksLivingEntity(EntityDamageByEntityEvent event, KitPlayer attacker, LivingEntity entity) {
        if (ChanceUtils.roll(getSetting(KitSettings.LIKELIHOOD))) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (Integer) getSetting(KitSettings.EFFECT_DURATION) * 20, 3, true,true));
            entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 10, 1);
        }
    }
}
