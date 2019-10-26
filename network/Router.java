package network;

import java.util.Queue;

import management.File;

public class Router extends NetworkDevice implements Routing {
    private Port networkPort;
    private Port portOne;
    private Port portTwo;
    private Port portThree;
    private Port portFour;

    public Router() {
        networkPort = new Port();
        portOne = new Port();
        portTwo = new Port();
        portThree = new Port();
        portFour = new Port();
    }

    public Port getNetworkPort() {
        return networkPort;
    }

    public Port routing(Packet packet, int line, int column, Router routers[][], Queue<Packet> input) {
        for (int lineRouters = 0; lineRouters<routers.length; lineRouters++) {
            for (int columnRouters = 0; columnRouters<routers[0].length; columnRouters++) {
                if (packet.getDestiny().equals(routers[lineRouters][columnRouters].networkPort.getOutput().getDestiny())) {
                    int lineDestiny = lineRouters;
                    int columnDestiny = columnRouters;
                    Router router = routers[lineDestiny][column];
                    while (true) {
                        if (columnDestiny > column) {
                            router.portTwo.setOutput(packet);
                            router = routers[lineDestiny][columnDestiny + 1];
                            router.portFour.setInput(input);
                        } else if (columnDestiny<column) {
                            router.portFour.setOutput(packet);
                            router = routers[lineDestiny][columnDestiny - 1];
                            router.portTwo.setInput(input);
                        } else if (line > lineDestiny) {
                            router.portOne.setOutput(packet);
                            router = routers[lineDestiny + 1][columnDestiny];
                            router.portThree.setInput(input);
                        } else if (line<lineDestiny) {
                            router.portThree.setOutput(packet);
                            router = routers[lineDestiny - 1][columnDestiny];
                            router.portOne.setInput(input);
                        } else {
                            break;
                        }
                    }
                    String pathname = "src/output/" + packet.getDestiny() + ".txt";
                    File.write(pathname, packet.getData() + "\n");
                    return router.networkPort;
                }
            }
        }
        Port defaultPort = new Port();
        return defaultPort;
    }
}
