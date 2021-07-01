package domain.request.infoRequest;


/**
 * Represents a request for information from a user
 */
public class InfoRequestDescription {
    String description;

    /**
     * Request for information from a user
     *
     * @param description description
     */
    public InfoRequestDescription(String description) {
        this.description = description;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
