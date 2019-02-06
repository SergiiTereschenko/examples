package st.examples.xpservice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class XpToLevelUtils {
//    static String filePath = "src/main/resources/stpToLevels.txt";
    private static Map<Integer, Integer> stpToLevels = new LinkedHashMap<>();
    static {
//        try {
//            Scanner scanner = new Scanner(new FileReader("src/main/resources/stpToLevels.txt"));
////            Path path = FileSystems.getDefault().getPath(filePath);
//            while (scanner.hasNextLine()) {
//                String[] columns = scanner.nextLine().split("\t");
//                stpToLevels.put(Integer.parseInt(columns[0]),Integer.parseInt(columns[1]));
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        // Best
//        try(Stream<String> lines = Files.lines(Paths.get("src/main/resources/stpToLevels.txt"))){
//            Object[] array = lines.filter(line -> line.contains(":")).toArray();
//
//            for (Object line : array) {
//                String[] split = ((String) line).split(":");
//                stpToLevels.put(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        stpToLevels.put(0, 0);
        stpToLevels.put(1, 1);
        stpToLevels.put(2, 2);
        stpToLevels.put(3, 3);
        stpToLevels.put(4, 4);
        stpToLevels.put(5, 5);
        stpToLevels.put(6, 6);
        stpToLevels.put(7, 7);
        stpToLevels.put(8, 8);
        stpToLevels.put(9, 9);
        stpToLevels.put(10, 10);
    }

    public static List<Integer> getLevels(int prevXP, int newXp) {
        List<Integer> levels = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : stpToLevels.entrySet()) {
            if (entry.getKey() > prevXP && entry.getKey() <= newXp) {
                levels.add(entry.getValue());
            }
        }
        return levels;
    }

    public static int getLevel(int xp) {
        List<Integer> list = new ArrayList<>(stpToLevels.keySet());
        if (list.get(0) > xp) return 0;
        if (list.get(list.size() - 1) < xp) return list.size() - 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) < xp && list.get(i) > xp) {
                return i - 1;
            }
        }
        return 0;
    }
}
