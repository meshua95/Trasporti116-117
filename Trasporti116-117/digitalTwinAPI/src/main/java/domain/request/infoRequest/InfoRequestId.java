package domain.request.infoRequest;

/**
 * Represents id for InfoRequest
 */
public class InfoRequestId {
    String infoRequestId;

    /**
     * Id for InfoRequest
     *
     * @param infoRequestId id
     */
    public InfoRequestId(String infoRequestId) {
        this.infoRequestId = infoRequestId;
    }

    /**
     * @return infoRequest id
     */
    public String getInfoRequestId() {
        return infoRequestId;
    }
}
