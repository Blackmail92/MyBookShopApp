/*
package com.example.MyBookShopApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

class One {
    MyBookShopAppApplicationTests.SendSmsHttp getOperation() {
        return new MyBookShopAppApplicationTests.SendSmsHttp();
    }

    MyBookShopAppApplicationTests.SendSmsHttp getOperation(Class clazz, int a) {
        return new MyBookShopAppApplicationTests.SendSmsHttp();
    }
}

@SpringBootTest
class MyBookShopAppApplicationTests extends One {



    static class SendSmsHttp {
        byte[] toByteArray() {
            return new byte[10];
        }

        SendSmsHttp() {
        }

        int sendSms(SendSmsEx ex) {
            return 1;
        }
    }

    abstract class SendSmsEx {
        abstract String getSpecificParam();

        abstract SendSmsHttp getPhoneTargetBytes();

        abstract SendSmsHttp getOrdPseudoIdListBytes();

        abstract String getOrdPseudoIdList();
    }

    SendSmsHttp getOperationCollector(Class clazz) {
        return new SendSmsHttp();
    }

    class PseudoIdExecututionResult {
        public PseudoIdExecututionResult(String st, Integer a) {
        }
    }

    int getCollectorIdFromResult(int a) {
        return a + 1;
    }
    int maxByteLength = 100;

    PseudoIdExecututionResult test2(SendSmsEx sendSms) {
        // см. OsgiMultiDataSourceDao.DS_COLLECTOR_NOBAL
        int nobalCollIndex = -3;
        String specificParams = sendSms.getSpecificParam();
        // если размер полей в байтовом представлении больше чем 32767,
        // необходимо разбить запрос на несколько подзапросов
        boolean isValidByteLength = sendSms.getPhoneTargetBytes().toByteArray().length <= maxByteLength
                || sendSms.getOrdPseudoIdListBytes().toByteArray().length <= maxByteLength;
        // Если не нужно использовать nobal_ds, и байтовая длина полей валидна, работаем как обычно
        if (isValidByteLength && !specificParams.contains("use_nobal_ds")) {
            Integer collNum = getCollectorIdFromResult(getOperationCollector(SendSmsHttp.class).sendSms(sendSms));
            return new PseudoIdExecututionResult(sendSms.getOrdPseudoIdList(), collNum);
        }
        // Проверяем наличие в specificParams ключа use_nobal_ds
        SendSmsHttp sendSmsHttp = specificParams.contains("use_nobal_ds") ?
                // Если содержится, достаем процедуры по индексу nobal коллектора
                super.getOperation(SendSmsHttp.class, nobalCollIndex) :
                // если нет, работаем с обычным коллектором
                getOperationCollector(SendSmsHttp.class);
        if (sendSmsHttp == null) {
            throw new IllegalStateException("Unexpected exception: DS operation missing");
        }
        // разбиваем переданное сообщение на несколько частей только если байтовый размер полей выше ограничения,
        // иначе - работаем c изначальным сообщением
        if (isValidByteLength) {
            Integer collNum = getCollectorIdFromResult(sendSmsHttp.sendSms(sendSms));
            return new PseudoIdExecututionResult(sendSms.getOrdPseudoIdList(), collNum);
        }
        List<SendSmsEx> smsParts = splitSms(sendSms);
        Integer collNum = null;
        for (SendSmsEx smsPart : smsParts) {
            int collPart = getCollectorIdFromResult(sendSmsHttp.sendSms(smsPart));
            collNum = collNum == null ? collPart : collNum;
        }
        return new PseudoIdExecututionResult(sendSms.getOrdPseudoIdList(), collNum);
    }

    List<SendSmsEx> splitSms(SendSmsEx ex) {
        return new ArrayList<>();
    }

    @Test
    public void test() {

    }
}
*/
