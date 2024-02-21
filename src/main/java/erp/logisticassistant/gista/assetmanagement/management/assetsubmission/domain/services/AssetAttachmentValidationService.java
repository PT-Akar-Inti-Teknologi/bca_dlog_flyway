package erp.logisticassistant.gista.assetmanagement.management.assetsubmission.domain.services;

import erp.logisticassistant.gista.assetmanagement.shareddomain.util.ChecksumUtils;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AssetAttachmentValidationService {
    public void validateChecksum(MultipartFile file , String checksum) throws IOException, NoSuchAlgorithmException {
        String calcChecksum = ChecksumUtils.calculateChecksum(file);
        compareChecksum(calcChecksum, checksum);
    }
    private void compareChecksum(String checkSumBackEnd, String checksumFrontEnd){
        if(!ChecksumUtils.compareChecksums(checkSumBackEnd,checksumFrontEnd)){
            throw new GlobalException(StatusCode.BAD_REQUEST, "Invalid Checksum");
        }
    }
}
