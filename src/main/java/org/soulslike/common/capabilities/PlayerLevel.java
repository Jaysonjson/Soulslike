package org.soulslike.common.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.SerializationUtils;
import org.soulslike.network.packet.ModMessages;
import org.soulslike.network.packet.PlayerLevelDataSyncS2CPacket;

import java.util.HashMap;

public class PlayerLevel {

    protected static String NBT_TAG_LEVEL = "level";
    protected static String NBT_TAG_ATTRIBUTES = "attributes";

    public static String ATTRIBUTE_VITALITY = "vit";
    public static String ATTRIBUTE_STRENGTH = "str";
    public static String ATTRIBUTE_DEXTERITY = "dex";
    public static String ATTRIBUTE_LUCK = "lck";
    public static String ATTRIBUTE_RESISTANCE = "rsnc";

    public PlayerLevel() {
        attributes_.put(ATTRIBUTE_VITALITY, 1);
        attributes_.put(ATTRIBUTE_STRENGTH, 1);
        attributes_.put(ATTRIBUTE_DEXTERITY, 1);
        attributes_.put(ATTRIBUTE_LUCK, 1);
        attributes_.put(ATTRIBUTE_RESISTANCE, 1);
    }

    public HashMap<String, Integer> attributes_ = new HashMap<>();

    int level_ = 1;


    //https://minecraft.fandom.com/wiki/Attribute
    public void setPlayerAttributes(Player player) {
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(19 + getAttributeLevel(PlayerLevel.ATTRIBUTE_VITALITY));
        player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(4.0 + (getAttributeLevel(PlayerLevel.ATTRIBUTE_DEXTERITY) / 200.0));
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(player.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue() + (getAttributeLevel(PlayerLevel.ATTRIBUTE_DEXTERITY) / 1175.0));
        player.getAttribute(Attributes.LUCK).setBaseValue(player.getAttribute(Attributes.LUCK).getBaseValue() + (getAttributeLevel(PlayerLevel.ATTRIBUTE_LUCK) * 10.2));
        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2 + (getAttributeLevel(PlayerLevel.ATTRIBUTE_STRENGTH) / 18.5));
    }

    public HashMap<String, Integer> getAttributes() {
        return attributes_;
    }

    public int getAttributeLevel(String attribute) {
        return attributes_.getOrDefault(attribute, 1);
    }

    public int getLevel() {
        return level_;
    }

    public void saveData(CompoundTag tag) {
        tag.putInt(NBT_TAG_LEVEL, level_);
        tag.putByteArray(NBT_TAG_ATTRIBUTES, SerializationUtils.serialize(attributes_));
    }

    public void loadData(CompoundTag tag) {
        level_ = tag.getInt(NBT_TAG_LEVEL);
        attributes_ = SerializationUtils.deserialize(tag.getByteArray(NBT_TAG_ATTRIBUTES));
    }

    public void sync(ServerPlayer player) {
        ModMessages.sendToPlayer(new PlayerLevelDataSyncS2CPacket(this), player);
    }

}
