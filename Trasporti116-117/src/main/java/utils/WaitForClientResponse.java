package utils;

import com.azure.core.http.rest.PagedIterable;
import utils.errorCode.QueryTimeOutException;

public class WaitForClientResponse {
    private WaitForClientResponse(){}

    public static void waitForClientResponse(PagedIterable<?> pageableResponse) throws QueryTimeOutException {
        long startTime = System.currentTimeMillis();

        while(pageableResponse.stream().findFirst().isEmpty()){
            if (System.currentTimeMillis() - startTime > QueryTimeOutException.TIME_OUT)
                throw new QueryTimeOutException();
        }
    }


}
