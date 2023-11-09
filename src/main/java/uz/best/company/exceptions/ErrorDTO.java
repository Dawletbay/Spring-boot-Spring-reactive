package uz.best.company.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 26.07.2023 13:05
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ErrorDTO implements Serializable {

    String title;

    String detail;

    int status;

    Integer customStatus;

    String timestamp;

    String path;

    String url;

    List<FieldErrorVM> errorFields;
}
