package com.st.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelPoiParser {

    @Test
    public void parseMgapConfigFromXlsx1() throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("MGAP-503-source.xlsx").getFile());
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        Map<String, ReelGroups> reelGroupsMap = new LinkedHashMap<>();

        String groupsKey = null;
        for (Row row : sheet) {
            System.out.println("Row number = " + row.getRowNum() + " ----");
            for (Cell cell : row) {
                CellType cellType = cell.getCellTypeEnum();
                if (cellType == CellType.STRING && cell.getStringCellValue().contains("Multiplier")) {
                    groupsKey = System.lineSeparator() + cell.getStringCellValue();
                    System.out.println();
                    System.out.println(groupsKey);
                    reelGroupsMap.putIfAbsent(groupsKey, ReelGroups.of());
                    break;
                }

                if (cellType == CellType.NUMERIC) {
                    System.out.print("NumberIdx=" + cell.getColumnIndex() + " ");
                    ReelGroups reelGroups = reelGroupsMap.get(groupsKey);
                    boolean isFull = reelGroups.addValue(cell.getNumericCellValue());
                    if (isFull) {
                        break;
                    }
                }

                if (cellType == CellType.BLANK) {
                    System.out.print("EmptyIdx=" + cell.getColumnIndex() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
        writeIntoFile(reelGroupsMap);

    }

    private void writeIntoFile(Map<String, ReelGroups> reelGroupsMap) throws IOException {
        File file = new File("mgapConfigResult.txt");
        String join = StringUtils.join(reelGroupsMap);
        System.out.println(join);
        FileUtils.writeStringToFile(file, join, (String) null);
        System.out.println("---done---");
    }

    static class ReelGroups {
        List<ReelGroup> groups = new ArrayList<>();

        public static ReelGroups of() {
            ReelGroups reelGroups = new ReelGroups();
            reelGroups.add(new ReelGroup());
            return reelGroups;
        }

        public List<ReelGroup> add(ReelGroup reelGroup) {
            groups.add(reelGroup);
            return groups;
        }

        public boolean addValue(double value) {
            if(last().isFull()) {
               add(new ReelGroup());
            }
            ReelGroup last = last();
            last.add((int) value);
            return last.isFull();
        }

        public ReelGroup last() {
            return groups.get(groups.size() - 1);
        }

        @Override
        public String toString() {
             return StringUtils.join(groups, "|");
        }
    }

    static class ReelGroup {
        List<Integer> reels1 = new ArrayList<>();
        List<Integer> reels2 = new ArrayList<>();
        List<Integer> reels3 = new ArrayList<>();

        public static ReelGroup of() {
            return new ReelGroup();
        }

        public boolean isFull() {
            return reels1.size() == 3 &&
                reels2.size() == 3 &&
                reels3.size() == 3;
        }

        public boolean add(int cell) {
            if (reels1.size() < 3)
                return reels1.add(cell);
            if (reels2.size() < 3)
                return reels2.add(cell);
            if (reels3.size() < 3)
                return reels3.add(cell);
            return false;
        }

        @Override
        public String toString() {
            StringJoiner joiner = new StringJoiner(",", "[", "]");
            joiner.add(reelsToString(reels1))
                .add(reelsToString(reels2))
                .add(reelsToString(reels3));
            return joiner.toString();
        }

        private String reelsToString(List<Integer> reels) {
            return reels.stream().map(Object::toString)
                .collect(Collectors.joining(",", "[", "]"));
        }
    }
}
