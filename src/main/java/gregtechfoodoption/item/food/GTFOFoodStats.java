package gregtechfoodoption.item.food;

import gregtech.api.items.metaitem.stats.IFoodBehavior;
import gregtech.api.items.metaitem.stats.IItemBehaviour;
import gregtech.api.util.GTUtility;
import gregtech.api.util.RandomPotionEffect;
import gregtechfoodoption.GTFOValues;
import gregtechfoodoption.potion.CyanidePoisoningPotion;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import gregtechfoodoption.integration.applecore.GTFOAppleCoreCompat;
import gregtechfoodoption.utils.GTFOUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class GTFOFoodStats implements IFoodBehavior, IItemBehaviour { // These names suck
    protected int foodLevel;
    protected float saturation;
    protected boolean isDrink;
    protected boolean alwaysEdible;
    protected RandomPotionEffect[] potionEffects;
    protected Supplier<ItemStack> stackSupplier;;
    protected int eatingDuration = 32;
    protected int toxinConcentration;

    @Deprecated
    public GTFOFoodStats(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible, Supplier<ItemStack> itemStackSupplier, RandomPotionEffect... potionEffects) {
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.isDrink = isDrink;
        this.alwaysEdible = alwaysEdible;
        this.stackSupplier = itemStackSupplier;
        this.potionEffects = potionEffects;
    }

    @Deprecated
    public GTFOFoodStats(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible, ItemStack stack, RandomPotionEffect... potionEffects) {
        this.foodLevel = foodLevel;
        this.saturation = saturation;
        this.isDrink = isDrink;
        this.alwaysEdible = alwaysEdible;
        this.stackSupplier = () -> stack;
        this.potionEffects = potionEffects;
    }
    @Deprecated
    public GTFOFoodStats(int foodLevel, float saturation, boolean isDrink, boolean alwaysEdible) {
        this(foodLevel, saturation, isDrink, alwaysEdible, ItemStack.EMPTY);
    }


    public GTFOFoodStats(int foodLevel, float saturation) {
        this(foodLevel, saturation, false, false);
    }

    public EnumAction getFoodAction(ItemStack itemStack) {
        return this.isDrink ? EnumAction.DRINK : EnumAction.EAT;
    }

    public int getFoodLevel(ItemStack itemStack, EntityPlayer player) {
        return this.foodLevel;
    }

    public float getSaturation(ItemStack itemStack, EntityPlayer player) {
        return this.saturation;
    }

    public boolean alwaysEdible(ItemStack itemStack, EntityPlayer player) {
        return this.alwaysEdible;
    }

    public ItemStack onFoodEaten(ItemStack itemStack, EntityPlayer player) {
        NBTTagCompound nbtStats = itemStack.getSubCompound("gtfoStats");
        if (nbtStats != null) {
            if (nbtStats.getBoolean("5dkcap/2/4/")) { // Cyanide
                player.addPotionEffect(new PotionEffect(CyanidePoisoningPotion.INSTANCE, 500, 0));
            }
        }
        if (Loader.isModLoaded(GTFOValues.MODID_AP)) {
            itemStack.grow(1);
            GTFOAppleCoreCompat.sendEatenEvent(player, itemStack, getFoodLevel(itemStack, player), getSaturation(itemStack, player));
            itemStack.shrink(1);
        }
        if (!player.world.isRemote) {
            for (RandomPotionEffect potionEffect : this.potionEffects) {
                if (Math.random() * 100.0D > (double) potionEffect.chance) {
                    player.addPotionEffect(GTUtility.copyPotionEffect(potionEffect.effect));
                }
            }

            if (this.stackSupplier != null) {
                ItemStack containerItem = stackSupplier.get().copy();
                if (player == null || !player.capabilities.isCreativeMode) {
                    if (itemStack.isEmpty()) {
                        return containerItem;
                    }

                    if (player != null && !player.inventory.addItemStackToInventory(containerItem)) {
                        player.dropItem(containerItem, false, false);
                    }
                }
            }
        }


        return itemStack;
    }

    @Override
    public void addInformation(ItemStack itemStack, List<String> list) {
        if (this.potionEffects.length > 0) {
            GTFOUtils.addPotionTooltip(Arrays.asList(potionEffects), list);
        }
        list.add(new TextComponentTranslation("gregtechfoodoption.tooltip.food.lacing").getFormattedText());
    }

    public GTFOFoodStats setEatingDuration(int duration) {
        this.eatingDuration = duration;
        return this;
    }

    public int getEatingDuration() {
        return eatingDuration;
    }
}
