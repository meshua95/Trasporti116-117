package domain.request.serviceRequest;

/**
 * Represents id for ServiceRequest
 */
public class ServiceRequestId {
    private final String serviceRequestId;

    /**
     * Id for ServiceRequest
     *
     * @param serviceRequestId id
     */
    public ServiceRequestId(String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }

    /**
     * @return Id for ServiceRequest
     */
    public String getserviceRequestId() {
        return serviceRequestId;
    }
}