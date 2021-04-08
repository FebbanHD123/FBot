package de.febanhd.fbot.mcnetwork.network;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import de.febanhd.fbot.mcnetwork.fbot.*;
import de.febanhd.fbot.mcnetwork.network.handshake.client.C00Handshake;
import de.febanhd.fbot.mcnetwork.network.login.client.C00PacketLoginStart;
import de.febanhd.fbot.mcnetwork.network.login.client.C01PacketEncryptionResponse;
import de.febanhd.fbot.mcnetwork.network.login.server.S00PacketDisconnect;
import de.febanhd.fbot.mcnetwork.network.login.server.S01PacketEncryptionRequest;
import de.febanhd.fbot.mcnetwork.network.login.server.S02PacketLoginSuccess;
import de.febanhd.fbot.mcnetwork.network.login.server.S03PacketEnableCompression;
import de.febanhd.fbot.mcnetwork.network.play.client.*;
import de.febanhd.fbot.mcnetwork.network.play.server.*;
import de.febanhd.fbot.mcnetwork.network.status.client.C00PacketServerQuery;
import de.febanhd.fbot.mcnetwork.network.status.client.C01PacketPing;
import de.febanhd.fbot.mcnetwork.network.status.server.S00PacketServerInfo;
import de.febanhd.fbot.mcnetwork.network.status.server.S01PacketPong;
import org.apache.logging.log4j.LogManager;

import java.util.Map;

public enum EnumConnectionState {
    HANDSHAKING(-1) {
        {
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00Handshake.class);
        }
    },
    PLAY(0) {
        {
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketKeepAlive.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, PacketInJoinGame.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S02PacketChat.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S03PacketTimeUpdate.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket2.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S05PacketSpawnPosition.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S06PacketUpdateHealth.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket3.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S08PacketPlayerPosLook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S09PacketHeldItemChange.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket4.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket5.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket6.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S0DPacketCollectItem.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket7.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket8.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket9.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket10.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket11.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S13PacketDestroyEntities.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket12.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket13.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket14.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket15.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket16.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket17.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket18.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket19.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket20.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket21.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket22.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S1FPacketSetExperience.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket23.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket24.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket25.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket26.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket27.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S25PacketBlockBreakAnim.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket28.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S27PacketExplosion.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket29.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S29PacketSoundEffect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S2APacketParticles.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S2BPacketChangeGameState.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket30.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S2DPacketOpenWindow.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S2EPacketCloseWindow.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket31.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket32.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S31PacketWindowProperty.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S32PacketConfirmTransaction.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket33.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket34.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S35PacketUpdateTileEntity.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S36PacketSignEditorOpen.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket35.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket36.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket37.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S3APacketTabComplete.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket38.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket39.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket40.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket41.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S3FPacketCustomPayload.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S40PacketDisconnect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket42.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket43.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket44.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket45.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S45PacketTitle.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S46PacketSetCompressionLevel.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S47PacketPlayerListHeaderFooter.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S48PacketResourcePackSend.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, UnknownPacket46.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketKeepAlive.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketChatMessage.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket47.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C03PacketPlayer.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C03PacketPlayer.C04PacketPlayerPosition.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C03PacketPlayer.C05PacketPlayerLook.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C03PacketPlayer.C06PacketPlayerPosLook.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C07PacketPlayerDigging.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket48.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C09PacketHeldItemChange.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C0APacketAnimation.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket49.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C0CPacketInput.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C0DPacketCloseWindow.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket50.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C0FPacketConfirmTransaction.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket51.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C11PacketEnchantItem.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C12PacketUpdateSign.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket52.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C14PacketTabComplete.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket53.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C16PacketClientStatus.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C17PacketCustomPayload.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket54.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, UnknownPacket55.class);
        }
    },
    STATUS(1) {
        {
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketServerQuery.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketServerInfo.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketPing.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S01PacketPong.class);
        }
    },
    LOGIN(2) {
        {
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketDisconnect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S01PacketEncryptionRequest.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S02PacketLoginSuccess.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S03PacketEnableCompression.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketLoginStart.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketEncryptionResponse.class);
        }
    };

    private static int field_181136_e = -1;
    private static int field_181137_f = 2;
    private static final EnumConnectionState[] STATES_BY_ID = new EnumConnectionState[field_181137_f - field_181136_e
            + 1];
    private static final Map<Class<? extends Packet>, EnumConnectionState> STATES_BY_CLASS = Maps
            .<Class<? extends Packet>, EnumConnectionState>newHashMap();
    private final int id;
    private final Map<EnumPacketDirection, BiMap<Integer, Class<? extends Packet>>> directionMaps;

    private EnumConnectionState(int protocolId) {
        this.directionMaps = Maps.newEnumMap(EnumPacketDirection.class);
        this.id = protocolId;
    }

    protected EnumConnectionState registerPacket(EnumPacketDirection direction, Class<? extends Packet> packetClass) {
        BiMap<Integer, Class<? extends Packet>> bimap = (BiMap) this.directionMaps.get(direction);

        if (bimap == null) {
            bimap = HashBiMap.<Integer, Class<? extends Packet>>create();
            this.directionMaps.put(direction, bimap);
        }

        if (bimap.containsValue(packetClass)) {
            String s = direction + " packet " + packetClass + " is already known to ID "
                    + bimap.inverse().get(packetClass);
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        } else {
            bimap.put(Integer.valueOf(bimap.size()), packetClass);
            return this;
        }
    }

    public Integer getPacketId(EnumPacketDirection direction, Packet packetIn) {
        return (Integer) ((BiMap) this.directionMaps.get(direction)).inverse().get(packetIn.getClass());
    }

    public Packet getPacket(EnumPacketDirection direction, int packetId)
            throws InstantiationException, IllegalAccessException {
        Class<? extends Packet> oclass = (Class) ((BiMap) this.directionMaps.get(direction))
                .get(Integer.valueOf(packetId));
        return oclass == null ? null : (Packet) oclass.newInstance();
    }

    public int getId() {
        return this.id;
    }

    public static EnumConnectionState getById(int stateId) {
        return stateId >= field_181136_e && stateId <= field_181137_f ? STATES_BY_ID[stateId - field_181136_e] : null;
    }

    public static EnumConnectionState getFromPacket(Packet packetIn) {
        return (EnumConnectionState) STATES_BY_CLASS.get(packetIn.getClass());
    }

    static {
        for (EnumConnectionState enumconnectionstate : values()) {
            int i = enumconnectionstate.getId();

            if (i < field_181136_e || i > field_181137_f) {
                throw new Error("Invalid protocol ID " + Integer.toString(i));
            }

            STATES_BY_ID[i - field_181136_e] = enumconnectionstate;

            for (EnumPacketDirection enumpacketdirection : enumconnectionstate.directionMaps.keySet()) {
                for (Class<? extends Packet> oclass : (enumconnectionstate.directionMaps.get(enumpacketdirection))
                        .values()) {
                    if (STATES_BY_CLASS.containsKey(oclass) && STATES_BY_CLASS.get(oclass) != enumconnectionstate) {
                        throw new Error("Packet " + oclass + " is already assigned to protocol "
                                + STATES_BY_CLASS.get(oclass) + " - can\'t reassign to " + enumconnectionstate);
                    }

                    try {
                        oclass.newInstance();
                    } catch (Throwable var10) {
                        throw new Error("Packet " + oclass + " fails instantiation checks! " + oclass);
                    }

                    STATES_BY_CLASS.put(oclass, enumconnectionstate);
                }
            }
        }
    }
}