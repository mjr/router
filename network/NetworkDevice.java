package network;

public abstract class NetworkDevice {
    private String IP;
    private String MAC;

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }
}
