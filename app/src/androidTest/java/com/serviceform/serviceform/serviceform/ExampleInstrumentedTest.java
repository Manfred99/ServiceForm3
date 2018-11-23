package com.serviceform.serviceform.serviceform;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void shouldSeeFileListing() throws IOException {
        // Replace userName, password and host with your specific values
        String userName = "dfonse";
        String password = "darkside9030";
        String host = "dfonse@192.168.1.9";
        String path = "/";

        SshClient sshClient = new SshClient();
        List<String> actual = sshClient.listFiles(userName, password, host, path);

        for (String s: actual) {
            System.out.println(s);
        }

        assertFalse("The list should contain a few files", actual.isEmpty());
    }
}
