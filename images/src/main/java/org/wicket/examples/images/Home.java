package org.wicket.examples.images;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.BufferedDynamicImageResource;
import org.apache.wicket.markup.html.image.resource.DefaultButtonImageResource;
import org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class Home extends WebPage {

	private static final long serialVersionUID = 1L;
	
	private static final ResourceReference RESOURCE_REF = new PackageResourceReference(Home.class,"image2.gif");

	/**
     * Constructor
     */
	public Home() {
		// Image as package resource
		add(new Image("images2", new PackageResourceReference(Home.class, "Image2.gif")));
		
		// Dynamically created image. Will re-render whenever resource is asked
        // for.
		add(new Image("image3",new CircleDynamicImageResource(100,100)));
		
		// Simple model
		add(new Image("image4",new Model<>("image2.gif")));	
		
		// Dynamically created buffered image
		add(new Image("image5", getImage5Resource()));
		
		// Add okay button image
		add(new Image("okButton",getOkButtonImage()));
	}
	
	private ResourceReference getOkButtonImage() {
		
		return new ResourceReference("okButton"){

			private static final long serialVersionUID = 1L;

			@Override
			public IResource getResource() {
				return new DefaultButtonImageResource("Ok");
			}
		};
	}

	private ResourceReference getImage5Resource() {
		
		return new ResourceReference(Home.class, "image5"){
			
			private static final long serialVersionUID = 1L;

			@Override
			public IResource getResource(){
				final BufferedDynamicImageResource resource = new BufferedDynamicImageResource();
				final BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
				drawCircle((Graphics2D)image.getGraphics());
				resource.setImage(image);
				return resource;
			}
		};
	}

	private final class CircleDynamicImageResource extends 
	RenderedDynamicImageResource {
		
		private static final long serialVersionUID = 1L;

		public CircleDynamicImageResource(int width, int height) {
			super(width, height);
			
		}

		@Override
		protected boolean render(Graphics2D graphics, Attributes attributes) {
			drawCircle(graphics);
			return true;
		}
		
		
	}
	
	void drawCircle(Graphics2D graphics) {
		 // Compute random size for circle
		final Random random = new Random();
		int dx = Math.abs(10 + random.nextInt(80));
		int dy = Math.abs(10 + random.nextInt(80));
		int x = Math.abs(random.nextInt(100 - dx));
		int y = Math.abs(random.nextInt(100 - dy));
		
		// Draw circle with thick stroke width
		graphics.setStroke(new BasicStroke(5));
		graphics.drawOval(x, y, dx, dy);
	}
	
}
