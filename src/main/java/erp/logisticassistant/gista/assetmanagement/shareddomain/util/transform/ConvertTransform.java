package erp.logisticassistant.gista.assetmanagement.shareddomain.util.transform;

import com.google.common.base.CaseFormat;
import erp.logisticassistant.gista.assetmanagement.shareddomain.constant.GlobalConstant;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.dto.response.exception.GlobalPayloadException;
import erp.logisticassistant.gista.assetmanagement.shareddomain.util.enums.StatusAsset;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ConvertTransform {

    @Named("stringToDateConverter")
    default String stringToDateConverter(String date){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate dateTime = LocalDate.parse(date, formatter);
            formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_DATE);
            return dateTime.format(formatter);
        }catch (Exception e){
            return date;
        }
    }

    @Named("localDateTimetoString")
    default String localDateTimetoString(LocalDateTime dateTime){
        if (dateTime != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_TIMESTAMP);
                return dateTime.format(formatter);
            }catch (Exception e){
                return dateTime.toString();
            }
        }
        return null;
    }

    @Named("localDateTimetoStringAsset")
    default String localDateTimetoStringAsset(LocalDateTime dateTime){
        if(dateTime!=null){
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_TIMESTAMP_ASSET);
                return dateTime.format(formatter);
            }catch (Exception e){
                return dateTime.toString();
            }
        }
        return null;
    }

    @Named("stringToLocalDateTime")
    default LocalDateTime stringToLocalDateTime(String formatedString){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_TIMESTAMP);
            return LocalDateTime.parse(formatedString, formatter);
        }catch (Exception e){
            return LocalDateTime.now();
        }
    }

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String formatedString){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_DATE);
            return LocalDate.parse(formatedString, formatter);
        }catch (Exception e){
            return LocalDate.now();
        }
    }

    @Named("instantTimetoString")
    default String instantTimetoString(Instant dateTime){
        if (dateTime != null) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_TIMESTAMP);
                return formatter.format(dateTime);
            }catch (Exception e){
                return dateTime.toString();
            }
        }
        return null;
    }

    @Named("stringToInstant")
    default Instant stringToInstant(String formatedString){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_TIMESTAMP);
            LocalDateTime dateTime = LocalDateTime.parse(formatedString, formatter);
            return dateTime.atZone(ZoneId.of("Asia/Jakarta")).toInstant();
        }catch (Exception e){
            return Instant.now();
        }
    }

    @Named("dateTimeToString")
    default String getTimestamp(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_TIMESTAMP);
        return date.format(formatter);
    }

    @Named("getDate")
    default String getDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(GlobalConstant.FORMAT_DATE);
        return date.format(formatter);
    }

    default GlobalPayloadException filterArrayMessage(String[] arrMessage){
        GlobalPayloadException payloadException = null;
        for(final String errMessage : arrMessage){
            String[] message = errMessage.split(": ");
            if(message.length == 2) {
                payloadException = new GlobalPayloadException(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, message[0]), message[1]);
            }else{
                payloadException = new GlobalPayloadException(null, message[0]);
            }
        }
        return payloadException;
    }

    @Named("mergingUsermameAndName")
    default String mergeUsermameAndName(String username, String name){
        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(name)){
            return username + " - " + name;
        }
        return "";
    }

    @Named("toStatusName")
    default String toStatusName(String statusName){
        if (statusName.equals(StatusAsset.STATUS_DRAFT.getType())){
            return StatusAsset.STATUS_DRAFT.getValue();
        } else if (statusName.equals(StatusAsset.STATUS_AWAITING_APPROVAL.getType())) {
            return StatusAsset.STATUS_AWAITING_APPROVAL.getValue();
        } else if (statusName.equals(StatusAsset.STATUS_CANCELLED.getType())) {
            return StatusAsset.STATUS_CANCELLED.getValue();
        } else if (statusName.equals(StatusAsset.STATUS_REJECTED.getType())) {
            return StatusAsset.STATUS_REJECTED.getValue();
        }else{
            return StatusAsset.STATUS_APPROVED.getValue();
        }

    }
}
