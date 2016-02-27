package com.j0ach1mmall3.jlib.player.tagchanger;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.PlayerInfoData;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.integration.protocollib.ProtocolLibHook;
import com.j0ach1mmall3.jlib.methods.General;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 17/02/16
 */
public final class TagChanger extends PacketAdapter {
    private static final Map<UUID, String> TAGS = new HashMap<>();

    private final Main plugin;

    /**
     * Initialises the TagChanger
     * @param plugin Main plugin
     */
    public TagChanger(Main plugin) {
        super(plugin, PacketType.Play.Server.PLAYER_INFO);
        this.plugin = plugin;
    }

    /**
     * Inits the TagChanger
     */
    public void init() {
        new ProtocolLibHook().addPacketAdapter(this);
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        PacketContainer packet = event.getPacket();
        if (packet.getPlayerInfoAction().read(0) != EnumWrappers.PlayerInfoAction.ADD_PLAYER) return;

        List<PlayerInfoData> newPlayerInfo = new ArrayList<>();
        for (PlayerInfoData playerInfo : packet.getPlayerInfoDataLists().read(0)) {
            if (playerInfo == null || playerInfo.getProfile() == null || !TAGS.containsKey(playerInfo.getProfile().getUUID()))
                newPlayerInfo.add(playerInfo);
            else {
                UUID uuid = playerInfo.getProfile().getUUID();
                newPlayerInfo.add(new PlayerInfoData(new WrappedGameProfile(uuid, TAGS.get(uuid)), playerInfo.getPing(), playerInfo.getGameMode(), playerInfo.getDisplayName()));
            }
        }

        packet.getPlayerInfoDataLists().write(0, newPlayerInfo);
    }

    /**
     * Registers the nametag for a UUID
     * @param uuid The UUID
     * @param tag The nametag
     */
    public static void registerTag(UUID uuid, String tag) {
        TAGS.put(uuid, tag);
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.hidePlayer(General.getPlayerByUuid(uuid));
            p.showPlayer(General.getPlayerByUuid(uuid));
        }
    }

    /**
     * Unregisters the nametag for a UUID
     * @param uuid The UUID
     */
    public static void unregisterTag(UUID uuid) {
        TAGS.remove(uuid);
    }
}
