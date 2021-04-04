package co.com.merdadolibre.challenge.services;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.merdadolibre.challenge.services.contracts.IDecode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

//@SpringBootTest
@ActiveProfiles("test")
class DecodeMessageTest {

    //@Autowired
    IDecode undertest;

    @BeforeEach
    void SetUp() {
        undertest = new DecodeMessage();
    }

    @Test
    void itShouldDecodeCompleteMessage() {
        String expected = "este es un mensaje secreto del imperio";
        // Given
        String[] m1 = {"este","","","mensaje","", "", ""};
        String[] m2 = {"","es","","","secreto","", ""};
        String[] m3 = {"este","","un","","", "", ""};
        String[] m4 = {"este","","","","", "del", "imperio"};

        List<String[]> messages = Arrays.asList(m2, m3, m1, m4);

        // When
        String result = undertest.getMessage(messages);

        // Then
        assertThat(result).isEqualTo(expected);
    }
}