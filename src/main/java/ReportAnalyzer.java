import minpq.MinPQ;
import minpq.OptimizedHeapMinPQ;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/**
 * Display the most commonly-reported WCAG recommendations.
 */
public class ReportAnalyzer {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("data/wcag.tsv");
        Map<String, String> wcagDefinitions = new LinkedHashMap<>();
        Scanner scanner = new Scanner(inputFile);
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\t", 2);
            String index = "wcag" + line[0].replace(".", "");
            String title = line[1];
            wcagDefinitions.put(index, title);
        }

        Pattern re = Pattern.compile("wcag\\d{3,4}");
        List<String> wcagTags = Files.walk(Paths.get("data/reports"))
                .map(path -> {
                    try {
                        return Files.readString(path);
                    } catch (IOException e) {
                        return "";
                    }
                })
                .flatMap(contents -> re.matcher(contents).results())
                .map(MatchResult::group)
                .toList();

        // TODO: Display the most commonly-reported WCAG recommendations using MinPQ
        MinPQ<String> organizedList = new OptimizedHeapMinPQ<>();

        for(String tag : wcagTags) {
            if (!organizedList.contains(tag)) {
                organizedList.add(tag, -1);
            } else {
                organizedList.changePriority(tag, organizedList.getPriority(tag)-1);
            }
        }

        String[] results = new String[3];

        for (int i = 0; i < 3; i++) {
            results[i] = organizedList.removeMin();
            System.out.println(wcagDefinitions.get(results[i]));
        }

        throw new UnsupportedOperationException();
    }
}