package domain.request.infoRequest;

/**
 * Represents id for InfoRequest
 */
public class InfoRequestId {
    private final String id;

    /**
     * Id for InfoRequest
     *
     * @param value the id
     */
    public InfoRequestId(final String value) {
        this.id = value;
    }

    /**
     * @return infoRequest id
     */
    public String getInfoRequestId() {
        return id;
    }
}
