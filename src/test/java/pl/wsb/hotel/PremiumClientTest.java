package pl.wsb.hotel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PremiumClientTest {

    private PremiumClient client;

    @BeforeEach
    void setUp() {
        // given
        client = new PremiumClient(
                "Harry",
                "Potter",
                LocalDate.of(1990, 7, 30),
                "+90 654-121-010",
                "harry.potter@hogwart.com",
                PremiumClient.PremiumAccountType.PREMIUM_PLUS);
    }

    @Test
    void printInformation() {
        // when
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        client.printInformation();
        System.setOut(originalOut);

        // then
        String expectedOutput = "\n" + "Client ID: " + client.getId() + "\n" +
                "Client full name: [premium] Harry Potter\n" +
                "Client age: 33\n" +
                "Client phone number: +90 654-121-010\n" +
                "Client email address: harry.potter@hogwart.com\n" +
                "Premium Account Type: PREMIUM_PLUS\n";

        String[] expectedLines = expectedOutput.split("\\R");
        String[] actualLines = outputStream.toString().split("\\R");

        assertEquals(expectedLines.length, actualLines.length);

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals(expectedLines[i], actualLines[i]);
        }
    }

    @Test
    void getFullName() {
        // when
        String fullName = client.getFullName();
        // then
        assertEquals("[premium] Harry Potter", fullName);
    }
}