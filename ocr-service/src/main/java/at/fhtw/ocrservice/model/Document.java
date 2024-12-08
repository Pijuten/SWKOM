package at.fhtw.ocrservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.processing.Generated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

/**
 * Document
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-12-07T17:55:25.345714006+01:00[Europe/Vienna]", comments = "Generator version: 7.10.0")
public class Document {

  private UUID id;

  private String title;

  private String username;

  private String description;

  private MultipartFile file;

  public Document() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Document(String title, String username, String description, MultipartFile file) {
    this.title = title;
    this.username = username;
    this.description = description;
    this.file = file;
  }

  public Document id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Valid
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Document title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */
  @NotNull
  @Schema(name = "title", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Document username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   */
  @NotNull 
  @Schema(name = "username", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("username")
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Document description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  @NotNull 
  @Schema(name = "description", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Document file(MultipartFile file) {
    this.file = file;
    return this;
  }

  /**
   * Get file
   * @return file
   */
  @NotNull @Valid 
  @Schema(name = "file", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("file")
  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.id, document.id) &&
        Objects.equals(this.title, document.title) &&
        Objects.equals(this.username, document.username) &&
        Objects.equals(this.description, document.description) &&
        Objects.equals(this.file, document.file);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, username, description, file);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    file: ").append(toIndentedString(file)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

