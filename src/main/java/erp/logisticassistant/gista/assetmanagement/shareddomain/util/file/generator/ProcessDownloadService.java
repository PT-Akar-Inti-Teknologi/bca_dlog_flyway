package erp.logisticassistant.gista.assetmanagement.shareddomain.util.file.generator;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.file.ExcelHelper;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessDownloadService extends ExcelHelper {
    private final ConvertTransform convertTransform;
    protected static final int ROW_HEADER = 0;
    protected static final int ROW_HEADER_HEIGHT = 600;
    protected static final int ROW_SUB_HEADER = 1;
    protected static final int ROW_SUB_HEADER_HEIGHT = 500;


    protected Workbook processCreateFile(){
        return new XSSFWorkbook();
    }

    protected Workbook processCreateSheet(Workbook wb, String sheetName){
        wb.createSheet(sheetName);
        return wb;
    }

    protected Workbook processCreateHeaderTemplateAssetSubmission(Workbook wb){
        Sheet sheetProfile = wb.getSheet(GlobalConstant.SHEET_UPLOAD_ASSET_SUBMISSION);
        Row headerProfile = sheetProfile.createRow(ROW_HEADER);
        headerProfile.setHeight((short) ROW_HEADER_HEIGHT);

        CellStyle styleHeaderAssetSubmission =  generateStyle(wb,11, true,
                new java.awt.Color(0, 232, 206),
                new java.awt.Color(0, 0, 0), HorizontalAlignment.CENTER,
                true, false, false, true);
        int colSubHeaderAssetSubmission= 0;
        Row subHeaderProfile = sheetProfile.createRow(ROW_HEADER);
        subHeaderProfile.setHeight((short) ROW_SUB_HEADER_HEIGHT);

        for(String value : GlobalConstant.HEADER_DOWNLOAD_TEMPLATE_DATA_ASSET_SUBMISSION){
            Cell cellHeaderAssetSubmission = subHeaderProfile.createCell(colSubHeaderAssetSubmission);
            cellHeaderAssetSubmission.setCellValue(value);
            cellHeaderAssetSubmission.setCellStyle(styleHeaderAssetSubmission);
            colSubHeaderAssetSubmission++;
        }
        autoSizeColumn(sheetProfile, 27, new int[]{15, 20, 15, 15, 25, 25, 25, 20, 15, 20, 15, 25, 15, 25, 25, 25, 25, 25, 25, 25, 25, 25, 20, 20, 25, 25, 25, 25});
        return wb;
    }

    protected Workbook processCreateHeaderDownloadAssetSubmission(Workbook wb){
        Sheet sheet = wb.getSheet(GlobalConstant.SHEET_DWONLOAD_ASSET_SUBMISSION);
        Row header = sheet.createRow(ROW_HEADER);
        header.setHeight((short) ROW_HEADER_HEIGHT);

        CellStyle styleSubHeaderExternal = generateStyle(wb, 10, true,
                new java.awt.Color(0, 232, 206),
                new java.awt.Color(0, 0, 0), HorizontalAlignment.CENTER,
                true, false, false, true);

        int colSubHeader= 0;
        Row subHeader = sheet.createRow(ROW_SUB_HEADER);
        subHeader.setHeight((short) ROW_SUB_HEADER_HEIGHT);
        for(String value : GlobalConstant.HEADER_DOWNLOAD_LIST_DATA_ASSET_SUBMISSION){
            Cell cellHeaderSub = subHeader.createCell(colSubHeader);
            cellHeaderSub.setCellValue(value);
            cellHeaderSub.setCellStyle(styleSubHeaderExternal);
            colSubHeader++;
        }
        return wb;
    }

    protected Workbook processDownloadListAssetSubmission(Workbook wb, List<ResponseAssetSubmission> dataAssetSubmission) {
        Sheet sheet = wb.getSheetAt(0);
        int start = 1;
        int number = 0;

        for (ResponseAssetSubmission data : dataAssetSubmission) {
            start++;
            number++;
            Row rowContent = sheet.createRow(start);
            rowContent.setHeight((short) 500);

            Cell cellAssetSubmissionNumber = rowContent.createCell(0);
            cellAssetSubmissionNumber.setCellValue(number);

            Cell cellAssetSubmissionDocumentNo = rowContent.createCell(1);
            cellAssetSubmissionDocumentNo.setCellValue(data.getNomorRequest());

            Cell cellAssetSubmissionTotalAsset = rowContent.createCell(2);
            cellAssetSubmissionTotalAsset.setCellValue(data.getJumlahAsset());

            Cell cellAssetSubmissionSubmittedBy = rowContent.createCell(3);
            cellAssetSubmissionSubmittedBy.setCellValue(data.getRequesterByUserName() + " - " + data.getRequesterByName());

            Cell cellAssetSubmissionStatus = rowContent.createCell(4);
            cellAssetSubmissionStatus.setCellValue(convertTransform.toStatusName(data.getStatus()));
        }
        autoSizeColumn(sheet, 5, new int[]{8, 25, 15, 30, 30});
        return wb;
    }

}
