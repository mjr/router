package network;

import java.util.Queue;

public class Port {
    private Queue<Packet> input;
    private Packet output;

    public Port() {
        this.output = new Packet();
    }

    public Queue<Packet> getInput() {
        return input;
    }

    public void setInput(Queue<Packet> input) {
        this.input = input;
    }

    public Packet getOutput() {
        return output;
    }

    public void setOutput(Packet output) {
        this.output = output;
    }
}
