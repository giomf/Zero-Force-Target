import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.ParsedPlacement;
import net.Graph;
import net.ParsedBlock;

public class Main {

    private static final int ROWS = 40;
    private static final int COLS = 40;

    public static void main(String[] args) throws IOException {

        List<ParsedBlock> blocks = ParsedBlock.Parse("Benchmarks/net/alu4.net");
        Map<String, ParsedPlacement> fixedPads = ParsedPlacement.Parse("Benchmarks/pads/alu4.pad");
        Graph graph = new Graph(blocks, fixedPads);
        FPGA fpga = new FPGA(graph, ROWS, COLS);
        fpga.placeRandom();
        graph.getLogicBlocks().forEach((block) -> {
            block.getZFT();
        });
        Graph.WriteToFile("./out/output.placement", graph);
        System.out.println("end.");
    }
}
