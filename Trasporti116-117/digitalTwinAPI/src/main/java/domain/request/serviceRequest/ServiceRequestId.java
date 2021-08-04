package domain.request.serviceRequest;

/**
 * Represents id for ServiceRequest
 */
public class ServiceRequestId {
    private final String id;

    /**
     * Id for ServiceRequest
     *
     * @param value ServiceRequest id
     */
    public ServiceRequestId(final String value) {
        this.id = value;
    }

    /**
     * @return Id for ServiceRequest
     */
    public String getId() {
        return id;
    }
}
