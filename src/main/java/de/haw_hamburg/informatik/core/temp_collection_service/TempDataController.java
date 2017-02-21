package de.haw_hamburg.informatik.core.temp_collection_service;

import de.haw_hamburg.informatik.core.temp_collection_service.database.DBConnect;
import de.haw_hamburg.informatik.core.temp_collection_service.database.DataBaseReturns;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by TimoHäckel on 30.01.2017.
 */
@RestController
public class TempDataController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/tempdata/insert")
    public ReturnValues insertTempData (@RequestParam(value = "srcid") String srcId, @RequestParam(value = "temperature") double temperature){

        DataBaseReturns insertRet = DBConnect.insert(srcId, temperature);
        DBConnect.printRecords();

        return insertRet == DataBaseReturns.SUCCESS ? ReturnValues.SUCCESS : ReturnValues.FAILURE;
    }

    public enum ReturnValues {
        SUCCESS, FAILURE
    }
}
