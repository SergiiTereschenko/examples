package com.st.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class LevelUpExcelParser {

    @Test
    public void parseLevelUpConfigFromXlsx() throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File file = new File(classLoader.getResource("LevelUps-Atlantis.xlsx").getFile());
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<LevelUp> levels = new ArrayList<>(sheet.getLastRowNum());

        for (Row row : sheet) {
//            System.out.println("Row number = " + row.getRowNum() + " ----");
            Cell cell1 = row.getCell(0);
            CellType cellType = cell1.getCellTypeEnum();
            if (cellType == CellType.STRING) {
                continue;
            }

            if (cellType == CellType.NUMERIC) {
                int level = (int) cell1.getNumericCellValue();
                Cell cell2 = row.getCell(1);
                int packageId = (int) cell2.getNumericCellValue();
                levels.add(new LevelUp(level, packageId));
            }

            if (cellType == CellType.BLANK) {
                System.out.print("EmptyIdx=" + cell1.getColumnIndex() + " ");
                break;
            }
        }
        System.out.println();
        writeIntoFile(levels);

    }

    private void writeIntoFile(List<LevelUp> levels) throws IOException {
        File file = new File("LevelUpSqlResult.sql");
        String join = StringUtils.join(levels, StringUtils.LF);
        System.out.println(join);
        FileUtils.writeStringToFile(file, join, (String) null);
        System.out.println("---done---");
    }

    static class LevelUp {
        int level;
        int skuId = 43;
        int segment = 113768473;
        int priority = 0;
        int packageId;

        public LevelUp(int level, int packageId) {
            this.level = level;
            this.packageId = packageId;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d, %d, %d, '{\"packageId\":%d,\"triggerType\":\"LEVEL_UP\"}'),",
                level, skuId, segment, priority, packageId);
        }
    }

}
