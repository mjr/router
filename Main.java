import management.File;

import network.Packet;
import network.Router;

import java.util.Queue;
import java.util.ArrayDeque;

public class Main {
    public static int NUMBER_OF_LINES = 3;
    public static int NUMBER_OF_COLUMNS = 3;

    public static int SOURCE_IP_POSITION = 0;
    public static int DESTINY_IP_POSITION = 1;
    public static int QUANTITY_PACKAGES_POSITION = 2;
    public static int DATA_POSITION = 3;

    public static void main(String[] args) {
        File.cleanDirectory("src/output/");

        String identifiersStr = File.read("src/ips.txt");
        String identifiers[];
        identifiers = identifiersStr.split("\n");
        Router routers[][] = new Router[NUMBER_OF_LINES][NUMBER_OF_COLUMNS];

        for (int line = 0; line < routers.length; line++) {
            for (int column = 0; column < routers[0].length; column++) {
                Router router = new Router();
                router.setIP(identifiers[(routers.length * line) + column]);
                routers[line][column] = router;
            }
        }

        String communicationsStr = File.read("src/comunicacao.txt");
        String communications[];
        communications = communicationsStr.split("\n");
        Queue<Packet> input = new ArrayDeque<>();

        for (String communication: communications) {
            String[] communicationArray = communication.split(" ");
            String sourceIP = communicationArray[SOURCE_IP_POSITION];
            String destinyIP = communicationArray[DESTINY_IP_POSITION];;
            int quantityPackages = Integer.parseInt(communicationArray[QUANTITY_PACKAGES_POSITION]);
            String data = communicationArray[DATA_POSITION];

            for (int indexPackage = 0; indexPackage < quantityPackages; indexPackage++) {
                Packet packet = new Packet();
                if (indexPackage == 0)
                    packet.setHeader(1);
                if (indexPackage == quantityPackages - 1)
                    packet.setFooter(1);
                packet.setData(data);
                packet.setDestiny(destinyIP);
                input.add(packet);
            }

            for (int line = 0; line < routers.length; line++) {
                for (int column = 0; column < routers[0].length; column++) {
                    if (sourceIP.equals(routers[line][column].getIP())) {
                        routers[line][column].getNetworkPort().setInput(input);
                        while (routers[line][column].getNetworkPort().getInput().isEmpty() == false) {
                            Packet packet = routers[line][column].getNetworkPort().getInput().remove();
                            routers[line][column].getNetworkPort().setOutput(packet);
                            routers[line][column].routing(packet, line, column, routers, input);
                        }
                        break;
                    }
                }
            }
        }
    }
}
