package domain.request.infoRequest;

/**
 * Represents id for InfoRequest
 */
public class InfoRequestId {
    private String infoRequestId;

    /**
     * Id for InfoRequest
     *
     * @param id id
     */
    public InfoRequestId(final String id) {
        this.infoRequestId = id;
    }

    /**
     * @return infoRequest id
     */
    public String getInfoRequestId() {
        return infoRequestId;
    }
}
