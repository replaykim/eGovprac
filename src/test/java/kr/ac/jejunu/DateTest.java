package kr.ac.jejunu;

import kr.ac.jejunu.util.DateUtility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by blue on 2017-06-15.
 */

public class DateTest {
    DateUtility dateUtility =new DateUtility();

    @Test
    public void dateOperTest() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = formatter.parse("2017-06-15 10:43:58");

        System.out.println(dateUtility.compareDate(date));
    }
}
