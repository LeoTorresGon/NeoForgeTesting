package net.leotorresgon.neoforgetestmod.attachment;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class DashAttachment implements INBTSerializable {
    private int maxDashs = 1;
    private int dash = 0;

    public int getDash() {
        return dash;
    }

    public void addDash() {
        this.dash++;
    }

    public void setMaxDashs(int dash) {
        this.maxDashs = dash;
    }

    public int getMaxDashs() {
        return maxDashs;
    }

    public void resetDashs() {
        this.dash = 0;
    }

    public void loadFromNBT(CompoundTag nbt) {
        this.dash = nbt.getInt("dash");
    }

    public void saveToNBT(CompoundTag nbt) {
        nbt.putInt("dash", this.dash);
    }

    @Override
    public Tag serializeNBT(HolderLookup.Provider provider) {
        return null;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, Tag nbt) {

    }
}
