package org.diylc.components.misc;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.ImageIcon;

import org.diylc.components.AbstractComponent;
import org.diylc.core.ComponentState;
import org.diylc.core.IDIYComponent;
import org.diylc.core.IDrawingObserver;
import org.diylc.core.Project;
import org.diylc.core.VisibilityPolicy;
import org.diylc.core.annotations.ComponentDescriptor;
import org.diylc.core.annotations.EditableProperty;

@ComponentDescriptor(name = "Image", author = "Branislav Stojkovic", category = "Misc", description = "User defined image", instanceNamePrefix = "Img", zOrder = IDIYComponent.TEXT, stretchable = false)
public class Image extends AbstractComponent<Void> {

	private static final long serialVersionUID = 1L;
	public static String DEFAULT_TEXT = "Double click to edit text";
	private static ImageIcon ICON;
	private static byte DEFAULT_SCALE = 50;

	static {
		String name = "image.png";
		java.net.URL imgURL = Image.class.getResource(name);
		if (imgURL != null) {
			ICON = new ImageIcon(imgURL, name);
		}
	}

	private Point point = new Point(0, 0);
	private ImageIcon image = ICON;
	private byte scale = 50;

	@Override
	public void draw(Graphics2D g2d, ComponentState componentState, Project project,
			IDrawingObserver drawingObserver) {
		double s = 1d * scale / DEFAULT_SCALE;
		g2d.scale(s, s);
		g2d.drawImage(image.getImage(), (int) (point.x / s), (int) (point.y / s), null);
		if (componentState == ComponentState.SELECTED) {
			g2d.setColor(SELECTION_COLOR);
			g2d.drawRect((int) (point.x / s), (int) (point.y / s), image.getIconWidth(), image
					.getIconHeight());
		}
	}

	@Override
	public void drawIcon(Graphics2D g2d, int width, int height) {
		g2d.drawImage(ICON.getImage(), point.x, point.y, null);
	}

	@Override
	public int getControlPointCount() {
		return 1;
	}

	@Override
	public Point getControlPoint(int index) {
		return point;
	}

	@Override
	public boolean isControlPointSticky(int index) {
		return false;
	}

	@Override
	public VisibilityPolicy getControlPointVisibilityPolicy(int index) {
		return VisibilityPolicy.NEVER;
	}

	@Override
	public void setControlPoint(Point point, int index) {
		this.point.setLocation(point);
	}

	@EditableProperty(defaultable = false)
	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	@EditableProperty(defaultable = false)
	public byte getScale() {
		return scale;
	}

	public void setScale(byte scale) {
		this.scale = scale;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Deprecated
	@Override
	public Void getValue() {
		return null;
	}

	@Override
	public void setValue(Void value) {
	}
}