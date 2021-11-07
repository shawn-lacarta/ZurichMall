package my.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {

    IO io;

    @Test
    void readFloor() {
        //System.out.println(io.readFloor("firstFloor.txt").length());
        assertEquals(io.readFloor("firstFloor.txt").length(), 1344);

    }

    @BeforeEach
    void setUp() {
        io = new IO(null, null);
    }
}