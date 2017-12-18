package com.interview.remotecar.service;

import com.interview.remotecar.component.CarGrid;
import com.interview.remotecar.contant.Command;
import com.interview.remotecar.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.interview.remotecar.contant.Command.F;
import static com.interview.remotecar.contant.Command.L;
import static com.interview.remotecar.contant.Command.R;

/**
 * Created by naveen on 16/12/17.
 * Get the car position based on starting co-ordinates and input directions
 */

@Service
public class RemoteCarService {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteCarService.class);

    /**
     * Get the car position based on starting co-ordinates and input directions
     *
     * @param inputDirections
     * @return String
     */
    public Node carPosition(String inputDirections) {

        final String[] inputs = inputDirections.split(",|:");
        LOG.info("input command:" + inputs);

        final int startX = Integer.parseInt(inputs[0]);
        final int startY = Integer.parseInt(inputs[1]);

        LOG.info("Starting position:"+ startX + "," + startY);
        final Node node = new Node(startX, startY);
        final CarGrid carGrid = new CarGrid();
        carGrid.setCarPosition(node);
        if(!carGrid.isValidPosition(node)) {
            throw new IllegalArgumentException("Please enter valid starting position");
        }
        final String[] commands = inputs[2].split("");
        LOG.info("input commands:", commands.toString());

        for (String c : commands) {
            Command command = Command.valueOf(c);
            switch (command) {
                case F:
                    carGrid.changeDirection(F);
                    break;
                case R:
                    carGrid.changeDirection(R);
                    break;
                case L:
                    carGrid.changeDirection(L);
                    break;
            }
        }

        final Node newNode = carGrid.getCarPosition();

        if(!carGrid.isValidPosition(newNode)) {
            throw new IllegalArgumentException("Please enter valid input commands within the grid");
        }

        LOG.info("New position:" + newNode.getX() + "," + newNode.getY());

        return newNode;
    }
}
