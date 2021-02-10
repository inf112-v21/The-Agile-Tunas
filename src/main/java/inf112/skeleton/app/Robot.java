package inf112.skeleton.app;

import com.badlogic.gdx.math.Vector2;

public class Robot implements IRobot{
    Vector2 position;
    boolean flag = false;

    public Robot(Vector2 start_pos) {
        this.position = start_pos;
    }

    @Override
    public Vector2 get_position() {
        return position;
    }

    @Override
    public boolean Has_Visited_a_Flag() {
        return flag;
    }

    @Override
    public void Visit_Flag() {
        this.flag = true;
    }

    @Override
    public void moveUp() {
        position.y +=1;
    }

    @Override
    public void moveDown() {
        position.y -= 1;
    }

    @Override
    public void moveLeft() {
        position.x +=1;
    }

    @Override
    public void moveRight() {
        position.x -= 1;
    }

}
