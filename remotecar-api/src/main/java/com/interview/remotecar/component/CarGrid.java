package com.interview.remotecar.component;

import com.interview.remotecar.contant.Command;
import com.interview.remotecar.contant.Direction;
import com.interview.remotecar.model.Node;
import org.springframework.stereotype.Component;

import static com.interview.remotecar.contant.Command.F;
import static com.interview.remotecar.contant.Command.L;
import static com.interview.remotecar.contant.Command.R;

/**
 * Created by madhuranaveen on 16/12/17.
 */
@Component
public class CarGrid {


    // initial direction is NORTH
    private Direction currentDirection;
    private int DEFAULT_GRID_WIDTH;
    private int DEFAULT_GRID_HEIGHT;


    private Node carPosition;
    private Node newPosition;

    public CarGrid(Direction currentDirection, int DEFAULT_GRID_WIDTH, int DEFAULT_GRID_HEIGHT) {
        this.currentDirection = currentDirection;
        this.DEFAULT_GRID_WIDTH = DEFAULT_GRID_WIDTH;
        this.DEFAULT_GRID_HEIGHT = DEFAULT_GRID_HEIGHT;
    }

    public CarGrid() {
        this.currentDirection = Direction.N;
        this.DEFAULT_GRID_WIDTH = 15;
        this.DEFAULT_GRID_HEIGHT = 15;
    }

    public Node changeDirection(Command command) {
        int headX = this.carPosition.getX();
        int headY = this.carPosition.getY();

        switch (command) {
            case L:
                if (this.currentDirection.name().equalsIgnoreCase(Direction.N.name())) {
                    //change direction to - y axis
                    setCurrentDirection(Direction.W);

                } else if (this.currentDirection.name().equalsIgnoreCase(Direction.E.name())) {
                    // change direction to +x axis
                    setCurrentDirection(Direction.N);
                }else if (this.currentDirection.name().equalsIgnoreCase(Direction.W.name())) {
                    // change direction to -x axis
                    setCurrentDirection(Direction.S);
                }else if(this.currentDirection.name().equalsIgnoreCase(Direction.S.name())){
                    //change direction to + y axis
                    setCurrentDirection(Direction.E);
                }
                break;
            case R:
                if (this.currentDirection.name().equalsIgnoreCase(Direction.N.name())) {
                    //change direction to + y axis
                    setCurrentDirection(Direction.E);

                } else if (this.currentDirection.name().equalsIgnoreCase(Direction.E.name())) {
                    // change direction to - x axis
                    setCurrentDirection(Direction.S);
                }else if (this.currentDirection.name().equalsIgnoreCase(Direction.W.name())) {

                    // change direction to +x axis
                    setCurrentDirection(Direction.N);
                }else if(this.currentDirection.name().equalsIgnoreCase(Direction.S.name())){

                    //change direction to - y axis
                    setCurrentDirection(Direction.W);
                }
                break;
            case F:
                if (this.currentDirection.name().equalsIgnoreCase(Direction.N.name())) {
                    // increment +x axis
                    newPosition = new Node(this.getCarPosition().getX() + 1 , this.getCarPosition().getY());
                    setCarPosition(newPosition);
                    break;
                } else if (this.currentDirection.name().equalsIgnoreCase(Direction.E.name())) {
                    //increment + y axis
                    newPosition = new Node(this.getCarPosition().getX(), this.getCarPosition().getY() + 1);
                    setCarPosition(newPosition);
                    break;
                }else if (this.currentDirection.name().equalsIgnoreCase(Direction.W.name())) {
                    //decrement - y axis
                    newPosition = new Node(this.getCarPosition().getX() , this.getCarPosition().getY() - 1);
                    setCarPosition(newPosition);
                    break;
                }else if(this.currentDirection.name().equalsIgnoreCase(Direction.S.name())){
                    // decrement -x axis
                    newPosition = new Node(this.getCarPosition().getX() - 1 , this.getCarPosition().getY() );
                    setCarPosition(newPosition);
                    break;
                }
                break;
        }

        return carPosition;
    }

    public boolean isValidPosition(Node carPosition) {
        if (carPosition.getY() > DEFAULT_GRID_HEIGHT || carPosition.getX() > DEFAULT_GRID_WIDTH
                || carPosition.getX() < 1 || carPosition.getY() < 1) {
            return false;
        }
        return true;
    }

    public Node getCarPosition() {
        return carPosition;
    }

    public void setCarPosition(Node carPosition) {
        this.carPosition = carPosition;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }


}
