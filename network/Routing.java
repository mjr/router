package network;

import java.util.Queue;

public interface Routing {
    public Port routing(Packet packet, int line, int column, Router routers[][], Queue<Packet> input);
}
