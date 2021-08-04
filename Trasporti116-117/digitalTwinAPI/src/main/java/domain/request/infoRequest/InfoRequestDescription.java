package domain.request.infoRequest;


/**
 * Represents a request for information from a user
 */
public class InfoRequestDescription {
    private final String description;

    /**
     * Request for information from a user
     *
     * @param d description
     */
    public InfoRequestDescription(final String d) {
        this.description = d;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }
}
