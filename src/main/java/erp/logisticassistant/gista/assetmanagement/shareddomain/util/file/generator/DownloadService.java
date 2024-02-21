package erp.logisticassistant.gista.assetmanagement.shareddomain.util.file.generator;

import erp.logisticassistant.gista.assetmanagement.management.assetsubmission.interfaces.rest.dto.response.ResponseAssetSubmission;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform.ConvertTransform;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class DownloadService extends ProcessDownloadService {
    public DownloadService(ConvertTransform convertTransform) {
        super(convertTransform);
    }
    public InputStream generateFileTemplateAssetSubmission() throws IOException {
        Workbook workbook;
        workbook = processCreateFile();
        workbook = processCreateSheet(workbook, GlobalConstant.SHEET_UPLOAD_ASSET_SUBMISSION);
        workbook = processCreateHeaderTemplateAssetSubmission(workbook);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    public InputStream generateDownloadFileAssetSubmission(List<ResponseAssetSubmission> data) throws IOException {
        Workbook workbook;
        workbook = processCreateFile();
        workbook = processCreateSheet(workbook, GlobalConstant.SHEET_DWONLOAD_ASSET_SUBMISSION);
        workbook = processCreateHeaderDownloadAssetSubmission(workbook);
        workbook = processDownloadListAssetSubmission(workbook, data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }
}
