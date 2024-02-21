package erp.logisticassistant.gista.assetmanagement.shareddomain.cache.interfaces.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDto extends AuditableCacheDto{
    
    @JsonProperty("menu_id")
    private String menuId;

    @JsonProperty("application")
    private ApplicationDto application;

    @JsonProperty("sub_category")
    private String subCategory;

    @JsonProperty("name")
    private String name;

    @JsonProperty("parent")
    private Boolean parent;

    @JsonProperty("parent_menu")
    private MenuDto menuParent;

    @JsonProperty("endpoint")
    private String endpoint;
  
    @JsonProperty("create")
    private Boolean create;
  
    @JsonProperty("read")
    private Boolean read;
  
    @JsonProperty("update")
    private Boolean update;

    @JsonProperty("delete")
    private Boolean delete;

    @JsonProperty("private")
    private Boolean pvt;
}
