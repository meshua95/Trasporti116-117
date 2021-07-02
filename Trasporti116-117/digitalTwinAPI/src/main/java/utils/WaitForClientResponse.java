package utils;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.PagedResponse;
import utils.errorCode.QueryTimeOutException;

/**
 * Class that allows to wait for the server response
 */
public class WaitForClientResponse {
    private WaitForClientResponse(){}

    /**
     * Wait a server response
     *
     * @param pageableResponse server response
     * @throws QueryTimeOutException timeout for response
     */
    public static void waitForClientResponse(PagedIterable<?> pageableResponse) throws QueryTimeOutException {
        long startTime = System.currentTimeMillis();
        while(pageableResponse.stream().findFirst().isEmpty()){
            if (System.currentTimeMillis() - startTime > QueryTimeOutException.TIME_OUT)
                throw new QueryTimeOutException();
        }
    }

    /**
     * Wait a server response
     *
     * @param pageableResponse server response
     * @throws QueryTimeOutException timeout for response
     */
    public static void waitForClientResponseIfExist(PagedIterable<?> pageableResponse) throws QueryTimeOutException {
        long startTime = System.currentTimeMillis();

        while(pageableResponse.stream().findFirst().isEmpty()){
            if (System.currentTimeMillis() - startTime > QueryTimeOutException.TIME_OUT ) {
                System.out.println("Timeout");
                break;
            }
        }
       pageableResponse.forEach(System.out::println);
    }

}
