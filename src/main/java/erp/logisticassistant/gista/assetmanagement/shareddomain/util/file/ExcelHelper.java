package erp.logisticassistant.gista.assetmanagement.shareddomain.util.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

@Slf4j
public class ExcelHelper {
    public void autoSizeColumn(Sheet sheet, int totalColumn, int[] valueLegth){
        for (int column = 0 ; column < totalColumn ; column++) {
            sheet.setColumnWidth(column, valueLegth[column] * 256);
        }
    }

    public CellStyle generateStyle(Workbook wb,
                                   Integer fontSize,
                                   boolean isBold,
                                   java.awt.Color bgColor,
                                   java.awt.Color fontColor,
                                   HorizontalAlignment horizontalAlignment,
                                   boolean isString, boolean isDate, boolean wrapText, boolean border){
        return setStyleData(wb, fontSize, isBold, bgColor, fontColor, horizontalAlignment, isString, isDate, wrapText, border);
    }

    public CellStyle setStyleData(Workbook wb, int fontSize, boolean bold,
                                  java.awt.Color foregroundColor, java.awt.Color fontColor, HorizontalAlignment alignment,
                                  boolean formatString, boolean formatDate, boolean wrapText, boolean border){
        XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        if(border) {
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
        }
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) fontSize);
        font.setBold(bold);

        if(formatString) {
            DataFormat fmt = wb.createDataFormat();
            style.setDataFormat(fmt.getFormat("@"));
        }
        else if(formatDate){
            CreationHelper createHelper = wb.getCreationHelper();
            style.setDataFormat(createHelper.createDataFormat().getFormat("DD/MM/yyyy"));
        }

        ((XSSFFont) font).setColor(new XSSFColor(fontColor, null));
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(alignment);
        style.setFillForegroundColor(new XSSFColor(foregroundColor, null));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setWrapText(wrapText);
        return style;
    }

    public String filterResultValue(String data, String valueNegative, String valuePositive){
        return data == null ? valueNegative : valuePositive;
    }

    public String filterResultValue(Boolean data, String valuePositive, String valueNegative){
        return Boolean.TRUE.equals(data) ? valuePositive : valueNegative;
    }

    public CellStyle filterResultStyle(Boolean data, CellStyle stylePositive, CellStyle styleNegative){
        return Boolean.TRUE.equals(data) ? stylePositive : styleNegative;
    }
}
