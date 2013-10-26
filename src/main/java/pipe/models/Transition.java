package pipe.models;

import pipe.gui.Constants;
import pipe.gui.ZoomController;
import pipe.views.viewComponents.RateParameter;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

/*
 * @author yufei wang(minor changes)
 */
public class Transition extends Connectable implements Serializable
{
    private int priority;
	private String rateExpr;
    private int orientation = 0;
    private boolean timed = false;
    private boolean infiniteServer = false;
    private double nameXOffset = 0;
    private double nameYOffset = 0;
    private int angle = 0;
    private boolean timedTransition;
    private RateParameter rateParameter;

    public static final int TRANSITION_HEIGHT = Constants.PLACE_TRANSITION_HEIGHT;
    public static final int TRANSITION_WIDTH = TRANSITION_HEIGHT / 3;
    private static final double ROOT_THREE_OVER_TWO = 0.5 * Math.sqrt(3);

    public Transition(String id, String name)
    {
        this(id, name, "1", 1);
    }

    @Override
    public int getHeight() {
        return TRANSITION_HEIGHT;
    }

    @Override
    public int getWidth() {
        return TRANSITION_WIDTH;
    }

    @Override
    public double getCentreX() {
        return getX() + getWidth()/2;
    }

    @Override
    public double getCentreY() {
        return getY() + getHeight()/2;
    }

    @Override
    public Point2D.Double getArcEdgePoint(double angle) {
        double half_height = getHeight()/2;
        double centre_x = x + half_height; //Use height since the actual object is a square, width is just the displayed width
        double centre_y = y + half_height;

        Point2D.Double connectionPoint = new Point2D.Double(centre_x, centre_y);

        double half_width = getWidth()/2;

        if (connectToTop(angle)) {
            connectionPoint.y -= half_height;
        } else if (connectToBottom(angle)) {
            connectionPoint.y += half_height;
        } else if (connectToLeft(angle)) {
            connectionPoint.x -= half_width;
        } else { //connectToRight
            connectionPoint.x += half_width;
        }

        AffineTransform transform = AffineTransform.getRotateInstance(this.angle);
        Point2D.Double rotatedPoint = new Point2D.Double();
        transform.transform(connectionPoint, rotatedPoint);
        return rotatedPoint;

        //Rotation angle
//        AffineTransform transform = AffineTransform.getRotateInstance(this.angle);
//        Point2D.Double transformed = new Point2D.Double();
//
//        if (Math.cos(angle) > rootThreeOverTwo) //Top
//        {
//            transform.transform(new Point2D.Double(1, 0.5 * connectable.getHeight()), transformed);
//        } else if (Math.cos(angle) < -rootThreeOverTwo)  //Bottom
//        {
//            transform.transform(new Point2D.Double(0, -0.5 * connectable.getHeight()), transformed);
//        } else if (Math.sin(angle) > 0) //Left
//        {
//            transformed.x += connectable.getWidth();
//            transform.transform(new Point2D.Double(-0.5 * connectable.getWidth(), 1), transformed);
//        } else     //Right
//        {
//            transformed.x -= connectable.getWidth();
//            transform.transform(new Point2D.Double(0.5 * connectable.getWidth(), 0), transformed);
//        }
//
//        //double half_width = connectable.getWidth()/2;
//        double half_height = connectable.getHeight() / 2;
//        double x = ZoomController.getZoomedValue(connectable.getX() + half_height + transformed.x, _zoomPercentage);
//        double y = ZoomController.getZoomedValue(connectable.getY() + half_height + transformed.y, _zoomPercentage);
//
//        Point2D.Double coord = new Point2D.Double(x, y);
//        return coord;
    }

    /**
     * Return true if an arc connecting to this should
     * connect to the left
     * @param angle in radians
     * @return
     */
    private boolean connectToLeft(double angle) {
        return (Math.sin(angle) < 0);
    }

    /**
     * Return true if an arc connecting to this should
     * connect to the bottom
     * @param angle in radians
     * @return
     */
    private boolean connectToBottom(double angle) {
        return Math.cos(angle) < -ROOT_THREE_OVER_TWO;
    }

    /**
     * Return true if an arc connecting to this should
     * connect to the top
     * @param angle in radians
     * @return
     */
    private boolean connectToTop(double angle) {
        return Math.cos(angle) > ROOT_THREE_OVER_TWO;
    }

    public Transition(String id, String name, String rateExpr, int priority)
    {
        super(id, name);
        this.rateExpr =rateExpr;
        this.priority = priority;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

	public String getRateExpr() {
		return rateExpr;
	}

	public void setRateExpr(String string) {
		rateExpr = string;
	}
	public void setRateExpr(double expr) {
		rateExpr = Double.toString(expr);
	}


   public double getNameXOffset() {
        return nameXOffset;
    }

    public double getNameYOffset() {
        return nameYOffset;
    }

    public int getAngle() {
        return angle;
    }

    public int getOrientation() {
        return orientation;
    }

    public boolean isTimed() {
        return timed;
    }

    public boolean isInfiniteServer() {
        return infiniteServer;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public void setTimed(boolean timed) {
        this.timed = timed;
    }

    public void setInfiniteServer(boolean infiniteServer) {
        this.infiniteServer = infiniteServer;
    }

    public void setNameXOffset(double nameXOffset) {
        this.nameXOffset = nameXOffset;
    }

    public void setNameYOffset(double nameYOffset) {
        this.nameYOffset = nameYOffset;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setTimedTransition(boolean timedTransition) {
        this.timedTransition = timedTransition;
    }

    public boolean isTimedTransition() {
        return timedTransition;
    }

    public RateParameter getRateParameter() {
        return rateParameter;
    }

    public void setRateParameter(RateParameter rateParameter) {
        this.rateParameter = rateParameter;
    }
}
