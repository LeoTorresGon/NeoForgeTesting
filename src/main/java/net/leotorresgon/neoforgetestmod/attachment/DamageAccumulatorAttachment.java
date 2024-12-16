package net.leotorresgon.neoforgetestmod.attachment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class DamageAccumulatorAttachment implements INBTSerializable {
    private float accumulatedDamage = 0;

    public void addDamage(float damage) {
        this.accumulatedDamage += damage;
    }

    public float getAccumulatedDamage() {
        return accumulatedDamage;
    }

    public void resetDamage() {
        this.accumulatedDamage = 0;
    }

    // Métodos para salvar e carregar dados
    public void loadFromNBT(CompoundTag nbt) {
        this.accumulatedDamage = nbt.getFloat("accumulatedDamage");
    }

    public void saveToNBT(CompoundTag nbt) {
        nbt.putFloat("accumulatedDamage", this.accumulatedDamage);
    }

    @Override
    public Tag serializeNBT(HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {

    }
}
