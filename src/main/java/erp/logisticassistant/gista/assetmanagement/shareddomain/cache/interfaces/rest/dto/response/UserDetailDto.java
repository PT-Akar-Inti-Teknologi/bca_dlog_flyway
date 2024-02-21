package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailDto {
  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("pin")
  private String pin;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("private_email")
  private String privateEmail;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("sub_department_code")
  private String subDepartmentCode;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("sub_department_name")
  private String subDepartmentName;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("application_id")
  private String applicationId;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("is_employees")
  private Boolean isEmployees;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("birth_date")
  private String birthDate;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("nik")
  private String nik;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("phone_number")
  private String phoneNumber;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("company_code")
  private String companyCode;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("vendor_code")
  private String vendorCode;

  @JsonInclude(value = JsonInclude.Include.NON_NULL)
  @JsonProperty("company_name")
  private String companyName;
}
