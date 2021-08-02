package domain.request.serviceRequest;

/**
 * Represents id for ServiceRequest
 */
public class ServiceRequestId {
    private final String serviceRequestId;

    /**
     * Id for ServiceRequest
     *
     * @param id ServiceRequest id
     */
    public ServiceRequestId(final String id) {
        this.serviceRequestId = id;
    }

    /**
     * @return Id for ServiceRequest
     */
    public String getserviceRequestId() {
        return serviceRequestId;
    }
}
