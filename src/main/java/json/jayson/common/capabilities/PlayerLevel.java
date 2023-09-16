package json.jayson.common.capabilities;

import json.jayson.network.packet.ModMessages;
import json.jayson.network.packet.PlayerLevelDataSyncS2CPacket;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.SerializationUtils;

import java.util.HashMap;

public class PlayerLevel {

    protected static String NBT_TAG_LEVEL = "level";
    protected static String NBT_TAG_ATTRIBUTES = "attributes";

    public static final String ATTRIBUTE_VITALITY = "vit";
    public static final String ATTRIBUTE_STRENGTH = "str";
    public static final String ATTRIBUTE_DEXTERITY = "dex";
    public static final String ATTRIBUTE_LUCK = "lck";
    public static final String ATTRIBUTE_RESISTANCE = "rsnc";

    public static final int MAX_LEVEL = 99 * 5;

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
        player.getAttribute(Attributes.MAX_HEALTH).setBaseValue(getLevelifiedAttribute(player, Attributes.MAX_HEALTH));
        player.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(getLevelifiedAttribute(player, Attributes.ATTACK_SPEED));
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(getLevelifiedAttribute(player, Attributes.MOVEMENT_SPEED));
        player.getAttribute(Attributes.LUCK).setBaseValue(getLevelifiedAttribute(player, Attributes.LUCK));
        player.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(getLevelifiedAttribute(player, Attributes.ATTACK_DAMAGE));
    }

    public long requiredSouls() {
        return requiredSouls(level_);
    }

    public static long requiredSouls(int level) {
        if(level / MAX_LEVEL * 100 > 74) return (int) (523 * (level * level) * 4.3);
        if(level / MAX_LEVEL * 100 > 44) return (int) (523 * (level * level) * 2.7);
        if(level / MAX_LEVEL * 100 > 24) return (int) (523 * (level * level) * 2.3);
        return (int) (523 * (level) * 2.3);
    }


    public double getLevelifiedAttribute(Player player, Attribute attribute) {
        if(attribute == Attributes.MAX_HEALTH) return 19 + getAttributeLevel(PlayerLevel.ATTRIBUTE_VITALITY);
        if(attribute == Attributes.ATTACK_SPEED) return 4.0 + (getAttributeLevel(PlayerLevel.ATTRIBUTE_DEXTERITY) / 200.0);
        if(attribute == Attributes.MOVEMENT_SPEED) return player.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue() + (getAttributeLevel(PlayerLevel.ATTRIBUTE_DEXTERITY) / 1175.0);
        if(attribute == Attributes.LUCK) return player.getAttribute(Attributes.LUCK).getBaseValue() + (getAttributeLevel(PlayerLevel.ATTRIBUTE_LUCK) * 10.2);
        if(attribute == Attributes.ATTACK_DAMAGE) return 2 + (getAttributeLevel(PlayerLevel.ATTRIBUTE_STRENGTH) / 18.5);
        return attribute.getDefaultValue();
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
