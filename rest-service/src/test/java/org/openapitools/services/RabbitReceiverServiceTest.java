package org.openapitools.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class RabbitReceiverServiceTest {

    @Test
    void spiltAround_() {
        RabbitReceiverService receiverService = new RabbitReceiverService(null);
        String inputString = "Hello-World_Whats Up";
        String string1 = "Hello-World";
        String string2 = "Whats Up";
        String[] result = receiverService.spiltAround_(inputString);
        Assertions.assertEquals(string1, result[0]);
        Assertions.assertEquals(string2, result[1]);
    }
}