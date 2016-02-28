package com.j0ach1mmall3.jlib.player.tagchanger;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;
import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.integration.protocollib.ProtocolLibHook;
import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/02/16
 */
public final class TagChanger extends PacketAdapter {
    private static final Map<UUID, String> TAGS = new HashMap<>();

    /**
     * Initialises the TagChanger
     * @param plugin Main plugin
     */
    public TagChanger(Main plugin) {
        super(plugin, PacketType.Play.Server.PLAYER_INFO);
        new ProtocolLibHook().addPacketAdapter(this);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        if (packet.getPlayerInfoAction().read(0) != EnumWrappers.PlayerInfoAction.ADD_PLAYER) return;

        UUID receiverUUID = event.getPlayer().getUniqueId();
        ArrayList<PlayerInfoData> playerInfoDatas = new ArrayList<>();
        for(PlayerInfoData data : packet.getPlayerInfoDataLists().read(0)) {
            UUID uuid = data.getProfile().getUUID();
            String name = data.getProfile().getName();
            if(!TAGS.containsKey(uuid)) {
                playerInfoDatas.add(data);
                continue;
            }
            WrappedGameProfile gameProfile = new WrappedGameProfile(uuid, receiverUUID.equals(uuid) ? name : TAGS.get(uuid));
            for(Map.Entry<String, WrappedSignedProperty> entry : data.getProfile().getProperties().entries()) {
                gameProfile.getProperties().put(entry.getKey(), entry.getValue());
            }
            playerInfoDatas.add(new PlayerInfoData(gameProfile, data.getPing(), data.getGameMode(), WrappedChatComponent.fromText(name.length() > 16 ? name.substring(0, 16) : name)));
        }

        packet.getPlayerInfoDataLists().write(0, playerInfoDatas);
        event.setPacket(packet);
    }

    /**
     * Registers the nametag for a UUID
     * @param uuid The UUID
     * @param tag The nametag
     */
    public static void registerTag(UUID uuid, String tag) {
        TAGS.put(uuid, tag);
        Player player = General.getPlayerByUuid(uuid);
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
            p.showPlayer(player);
        }
    }

    /**
     * Unregisters the nametag for a UUID
     * @param uuid The UUID
     */
    public static void unregisterTag(UUID uuid) {
        TAGS.remove(uuid);
        Player player = General.getPlayerByUuid(uuid);
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(player);
            p.showPlayer(player);
        }
    }

    public void cleanup() {
        for(UUID uuid : new HashSet<>(TAGS.keySet())) {
            unregisterTag(uuid);
        }
    }
}
