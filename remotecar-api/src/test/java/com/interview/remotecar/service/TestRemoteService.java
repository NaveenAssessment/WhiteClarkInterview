package com.interview.remotecar.service;

import com.interview.remotecar.RemoteControlApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by naveen on 18/12/17.
 */
@RunWith(SpringRunner.class)
public class TestRemoteService {
    private static final Logger LOG = LoggerFactory.getLogger(TestRemoteService.class);

    @Test
    public void testValidInput() {
        String input = "5,5:FLFLFFRFFF";
        RemoteCarService remoteCarService = new RemoteCarService();
        com.interview.remotecar.model.Node newCarPosition = remoteCarService.carPosition(input);
        assertNotNull(newCarPosition);

        //x value
        assertEquals(4, newCarPosition.getX());

        //y value
        assertEquals(1, newCarPosition.getY());
        LOG.info(newCarPosition.toString());
    }


    @Test
    public void testInvalidStartingNode() {
        //out of grid starting node
        String input = "25,25:FLFLFFRFFF";
        RemoteCarService remoteCarService = new RemoteCarService();
        try {
            com.interview.remotecar.model.Node newCarPosition = remoteCarService.carPosition(input);
        }
        catch(IllegalArgumentException exc) {
            assertThat(exc.getMessage(), is("Please enter valid starting position"));
        }
    }

    @Test
    public void testInvalidCommandsOutOfGrid() {
        //out of grid starting node
        String input = "5,5:FLFLFFRFFFFFFFFFFFFFFFFF";
        RemoteCarService remoteCarService = new RemoteCarService();
        try {
            com.interview.remotecar.model.Node newCarPosition = remoteCarService.carPosition(input);
        }
        catch(IllegalArgumentException exc) {
            assertThat(exc.getMessage(), is("Please enter valid input commands within the grid"));
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput() {
        String input = "5,5:FLFLFF RFFF";
        RemoteCarService remoteCarService = new RemoteCarService();
        com.interview.remotecar.model.Node newCarPosition = remoteCarService.carPosition(input);
    }
}
