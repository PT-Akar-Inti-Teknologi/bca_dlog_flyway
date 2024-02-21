package erp.logisticassistant.gista.assetmanagement.shareddomain.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChecksumUtils {
    private ChecksumUtils(){}

    @SuppressWarnings("java:S5542")
    public static String calculateChecksum(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        byte[] fileBytes = file.getBytes();

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] checksumBytes = md.digest(fileBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : checksumBytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    public static boolean compareChecksums(String checksum1, String checksum2) {
        return checksum1.equals(checksum2);
    }
}
